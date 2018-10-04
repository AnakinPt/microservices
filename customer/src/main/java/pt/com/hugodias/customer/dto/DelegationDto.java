package pt.com.hugodias.customer.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)

public class DelegationDto extends CustomerDto{
	private CustomerDto headQuarters;

}
