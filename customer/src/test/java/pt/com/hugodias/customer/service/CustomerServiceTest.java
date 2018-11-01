package pt.com.hugodias.customer.service;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

import static org.mockito.Mockito.when;

import pt.com.hugodias.customer.data.Customer;
import pt.com.hugodias.customer.data.Delegation;
import pt.com.hugodias.customer.data.Headquarters;
import pt.com.hugodias.customer.dto.CustomerDto;
import pt.com.hugodias.customer.dto.CustomerMapper;
import pt.com.hugodias.customer.dto.DelegationDto;
import pt.com.hugodias.customer.exception.UnknownCustomerException;
import pt.com.hugodias.customer.repositories.CustomerMongoRepository;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

	@InjectMocks
	private CustomerService customerService;
	
	@Mock
	private CustomerMongoRepository repository;
	
	@Mock
	private Headquarters headQuartersMock;

	@Mock
	private Headquarters savedHeadQuartersMock;

	@Mock
	private Delegation delegationMock;

	@Mock
	private Delegation savedDelegationMock;
	
	@Mock
	private CustomerMapper mapper;
	
	
	@Mock
	private CustomerDto headQuartersDtoMock;
	
	@Mock
	private DelegationDto delegationDtoMock;

	@Mock 
	private List<CustomerDto> customersDtoMock;
	
	@Mock 
	private List<Customer> customersMock;
	
	@Mock 
	private List<Delegation> delegations;
	
	@Mock 
	private List<DelegationDto> delegationsDto;
	
	@Test
	public void getCustomer() {
		when(repository.findById("1")).thenReturn(Optional.of(headQuartersMock));
		when(mapper.map(headQuartersMock)).thenReturn(headQuartersDtoMock);
		
		CustomerDto customer = customerService.getCustomer("1");
		
		assertThat(customer, is(headQuartersDtoMock));
	}
	
	@Test(expected=UnknownCustomerException.class)
	public void getCustomer_notFound() {
		when(repository.findById("1")).thenReturn(Optional.ofNullable(null));
		customerService.getCustomer("1");
	}
	
	@Test(expected=UnknownCustomerException.class)
	public void getCustomer_notAnHeadquarters() {
		when(repository.findById("1")).thenReturn(Optional.of(delegationMock));
		customerService.getCustomer("1");
	}
	
	@Test
	public void getAllCustomers() {
		when(repository.findAll()).thenReturn(customersMock);
		when(mapper.map(customersMock)).thenReturn(customersDtoMock);
		List<CustomerDto> allCustomers = customerService.getAllCustomers();
		assertThat(allCustomers, is(customersDtoMock));
	}

	@Test
	public void getDelegations() {
		when(repository.findById("1")).thenReturn(Optional.of(headQuartersMock));
		when(repository.findByHeadQuarters(headQuartersMock.getId())).thenReturn(delegations);
		when(mapper.map(delegations, headQuartersMock)).thenReturn(delegationsDto);
		List<DelegationDto> dtos = customerService.getDelegations("1");
		
		assertThat(dtos, is(delegationsDto));
		
	}
	
	@Test
	public void addCustomer() {
		when(mapper.map(headQuartersDtoMock)).thenReturn(headQuartersMock);
		when(repository.save(headQuartersMock)).thenReturn(savedHeadQuartersMock);
		when(savedHeadQuartersMock.getId()).thenReturn("1");
		assertThat(customerService.addCustomer(headQuartersDtoMock), is("1"));
	}
	
	@Test
	public void addDelegation() {
		when(repository.findById("1")).thenReturn(Optional.of(headQuartersMock));
		when(mapper.map(delegationDtoMock, headQuartersMock)).thenReturn(delegationMock);
		when(repository.save(delegationMock)).thenReturn(savedDelegationMock);
		when(savedDelegationMock.getId()).thenReturn("2");
		assertThat(customerService.addDelegation("1", delegationDtoMock), is("2"));
	}

}
