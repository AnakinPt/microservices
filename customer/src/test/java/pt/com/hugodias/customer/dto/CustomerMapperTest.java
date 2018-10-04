package pt.com.hugodias.customer.dto;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

import lombok.val;
import lombok.var;
import pt.com.hugodias.customer.data.Customer;
import pt.com.hugodias.customer.data.Delegation;
import pt.com.hugodias.customer.data.Headquarters;

@RunWith(MockitoJUnitRunner.class)
public class CustomerMapperTest {

	@InjectMocks
	private CustomerMapper mapper;
	
	@Test
	public void testMapCustomer() {
		Headquarters customer = new Headquarters();
		customer.setId("1");
		customer.setName("Headquarters");
		
		Delegation delegation = new Delegation();
		delegation.setId("2");
		delegation.setName("Delegation");
		delegation.setHeadQuarters(customer);
		
		var dto = mapper.map(customer);
		assertThat(dto.getId(), is("1"));
		assertThat(dto.getName(), is("Headquarters"));
		
		var delegationDto = mapper.map(delegation, customer);
		assertThat(delegationDto.getId(), is("2"));
		assertThat(delegationDto.getName(), is("Delegation"));
		assertThat(delegationDto.getHeadQuarters(), is(dto));
	}

	@Test
	public void testMapIterableOfCustomer() {
		Headquarters customer = new Headquarters();
		customer.setId("1");
		customer.setName("Headquarters");
		
		Delegation dependency = new Delegation();
		dependency.setId("2");
		dependency.setName("Delegation");
		dependency.setHeadQuarters(customer);
		
		List<Customer> customers = Arrays.asList(customer, dependency);
		List<CustomerDto> dtos = mapper.map(customers);
		
		assertThat(dtos.size(), is(customers.size()));
		assertThat(dtos.get(0).getId(), is("1"));
		assertThat(dtos.get(0).getName(), is("Headquarters"));
		assertThat(dtos.get(1).getId(), is("2"));
		assertThat(dtos.get(1).getName(), is("Delegation"));
		
		
	}

	@Test
	public void testMapCustomerDto() {
		val dto = new CustomerDto();
		dto.setName("Customer");
		dto.setId("1");
		val headquarters = mapper.map(dto);
		assertThat(headquarters.getId(), is("1"));
		assertThat(headquarters.getName(), is("Customer"));
		
		val delegationDto = new DelegationDto();
		delegationDto.setId("2");
		delegationDto.setName("Delegation");
		delegationDto.setHeadQuarters(dto);
		
		val delegation = mapper.map(delegationDto, headquarters);
		assertThat(delegation.getId(), is("2"));
		assertThat(delegation.getName(), is("Delegation"));
		assertThat(delegation.getHeadQuarters(), is(headquarters));
	}
	
	@Test
	public void testMapIterableOfDelegations() {
		val customer = new Headquarters();
		customer.setId("1");
		customer.setName("Headquarters");
		
		val customerDto = mapper.map(customer);
		
		val dependency1 = new Delegation();
		dependency1.setId("2");
		dependency1.setName("Delegation");
		dependency1.setHeadQuarters(customer);
		
		val dependency2 = new Delegation();
		dependency2.setId("3");
		dependency2.setName("Delegation2");
		dependency2.setHeadQuarters(customer);
		
		val dependency3 = new Delegation();
		dependency3.setId("4");
		dependency3.setName("Delegation3");
		dependency3.setHeadQuarters(customer);
		
		List<Delegation> customers = Arrays.asList(dependency1, dependency2, dependency3);
		val dtos = mapper.map(customers, customer);
		
		assertThat(dtos.size(), is(customers.size()));
		assertThat(dtos.get(0).getId(), is("2"));
		assertThat(dtos.get(0).getName(), is("Delegation"));
		assertThat(dtos.get(0).getHeadQuarters(), is(customerDto));
		assertThat(dtos.get(1).getId(), is("3"));
		assertThat(dtos.get(1).getName(), is("Delegation2"));
		assertThat(dtos.get(1).getHeadQuarters(), is(customerDto));
		assertThat(dtos.get(2).getId(), is("4"));
		assertThat(dtos.get(2).getName(), is("Delegation3"));
		assertThat(dtos.get(2).getHeadQuarters(), is(customerDto));
	
	}

}
