package pt.com.hugodias.customer.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import pt.com.hugodias.customer.data.Headquarters;
import pt.com.hugodias.customer.dto.CustomerDto;
import pt.com.hugodias.customer.dto.CustomerMapper;

public class CustomerItemProcessor implements ItemProcessor<CustomerDto, Headquarters> {

	@Autowired
	private CustomerMapper mapper;
	
	@Override
	public Headquarters process(CustomerDto item) throws Exception {
		return mapper.map(item);
	}

}
