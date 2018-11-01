package pt.com.hugodias.customer.data;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="customers")
public interface Customer {

	public String getId();
	
	public void setId(String id);
	
	public String getName();
	
	public void setName(String name);
	
	public String getContactPerson();
	
	public void setContactPerson(String contactPerson);
	
	public String getPhoneNumber();
	
	public void setPhoneNumber(String phoneNumber);
	
	public String getEmail();
	
	public void setEmail(String email);
	
}
