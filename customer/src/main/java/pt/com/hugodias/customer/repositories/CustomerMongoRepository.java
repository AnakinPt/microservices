package pt.com.hugodias.customer.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import pt.com.hugodias.customer.data.Customer;
import pt.com.hugodias.customer.data.Delegation;
import pt.com.hugodias.customer.data.Headquarters;

@CrossOrigin(origins = "http://localhost:4200")
public interface CustomerMongoRepository extends MongoRepository<Customer,String> {

	List<Headquarters> findByName(String name);
	
	@Query("{ 'headQuarters._id' :  ?0  }")
	List<Delegation> findByHeadQuarters(String headQuarters);
}
