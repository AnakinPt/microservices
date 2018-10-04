package pt.com.hugodias.customer.dto;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.val;
import pt.com.hugodias.customer.data.Headquarters;
import pt.com.hugodias.customer.data.Customer;
import pt.com.hugodias.customer.data.Delegation;

@Service
public class CustomerMapper {

	public CustomerDto map(Headquarters customer) {
		val dto = new CustomerDto();
		setDtoFields(customer, dto);
		return dto;
	}

	public DelegationDto map(Delegation delegation, Headquarters customer) {
		val dto = new DelegationDto();
		setDtoFields(delegation, dto);
		dto.setHeadQuarters(map(customer));
		return dto;
	}

	private CustomerDto setDtoFields(Customer customer, final CustomerDto dto) {
		dto.setId(customer.getId());
		dto.setName(customer.getName());
		dto.setContactPerson(customer.getContactPerson());
		dto.setPhoneNumber(customer.getPhoneNumber());
		dto.setEmail(customer.getEmail());
		return dto;
	}


	public List<CustomerDto> map(Iterable<Customer> customers){
		val dtos = new LinkedList<CustomerDto>();
		for(Customer customer: customers) {
			CustomerDto dto = null;
			if (customer instanceof Headquarters) {
				dto = new CustomerDto();
			}else {
				dto = new DelegationDto();
				val customerDto = map(((Delegation) customer).getHeadQuarters());
				((DelegationDto)dto).setHeadQuarters(customerDto);
			}
			setDtoFields(customer, dto);
			dtos.add(dto);
		}
		return dtos;
	}

	public List<DelegationDto> map(Iterable<Delegation> delegations, Headquarters customer){
		val dtos = new LinkedList<DelegationDto>();
		for(Delegation delegation: delegations) {
			dtos.add(map(delegation, customer));
		}
		return dtos;
	}

	public Headquarters map(CustomerDto dto) {
		val customer = new Headquarters();
		setCustomerFields(dto, customer);
		return customer;
	}

	private void setCustomerFields(CustomerDto dto, final Customer customer) {
		customer.setId(dto.getId());
		customer.setName(dto.getName());
		customer.setContactPerson(dto.getContactPerson());
		customer.setPhoneNumber(dto.getPhoneNumber());
		customer.setEmail(dto.getEmail());
	}
	
	public Delegation map(DelegationDto dto, Headquarters customer) {
		val delegation = new Delegation();
		setCustomerFields(dto, delegation);
		delegation.setHeadQuarters(customer);
		return delegation;
	}


}
