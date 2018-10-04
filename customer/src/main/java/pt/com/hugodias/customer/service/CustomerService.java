package pt.com.hugodias.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.val;
import pt.com.hugodias.customer.data.Headquarters;
import pt.com.hugodias.customer.dto.CustomerDto;
import pt.com.hugodias.customer.dto.CustomerMapper;
import pt.com.hugodias.customer.dto.DelegationDto;
import pt.com.hugodias.customer.exception.UnknownCustomerException;
import pt.com.hugodias.customer.repositories.CustomerMongoRepository;

@RefreshScope
@RestController
@RequestMapping("/customer")
public class CustomerService {
	
	@Autowired
	private CustomerMongoRepository customerRepository;
	
	@Autowired
	private CustomerMapper mapper;
	
	@GetMapping(path = "/{id}")
	public CustomerDto getCustomer(@PathVariable String id) throws UnknownCustomerException {
		return mapper.map(getCustomerById(id));
	}
	
	private Headquarters getCustomerById(String id) throws UnknownCustomerException {
		val customer = customerRepository.findById(id);
		if (!customer.isPresent()) {
			throw new UnknownCustomerException(id);
		}
		if (customer.get() instanceof Headquarters)
			return (Headquarters) customer.get();
		throw new UnknownCustomerException(id);
	}
	
	@GetMapping(path = "/{id}/delegations")
	public List<DelegationDto> getDelegations(@PathVariable String id) throws UnknownCustomerException {
		val customer = getCustomerById(id);
		return mapper.map(customerRepository.findByHeadQuarters(customer.getId()), customer);
		
	}

	@PostMapping
	public String addCustomer(@RequestBody CustomerDto dto) {
		val customer = mapper.map(dto);
		customerRepository.save(customer);
		return customer.getId();
	}
	
	@PostMapping(path="/{id}")
	public String addDelegation(@PathVariable String id, @RequestBody DelegationDto dto) {
		val customer = getCustomerById(id);
		val delegation = mapper.map(dto, customer);
		customerRepository.save(delegation);
		return delegation.getId();
	}
	
	@GetMapping
	@CrossOrigin(origins = "http://localhost:4200")
	public List<CustomerDto> getAllCustomers(){
		return mapper.map(customerRepository.findAll());
	}
}
