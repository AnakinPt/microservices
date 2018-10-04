package pt.com.hugodias.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UnknownCustomerException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UnknownCustomerException(String id) {
		super("Unable to find customer with id "+id);
	}

}
