package pt.com.hugodias.customer.data;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="customers")
public class Headquarters implements Serializable, Customer {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@Indexed
	private String name;
		
	private String contactPerson;
	
	private String phoneNumber;
	
	private String email;
}
