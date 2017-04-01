package be.gdusart.europarltools.app.ui.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class Error404Exception extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7665485550086614589L;

	public Error404Exception() {
		super();
	}

	public Error404Exception(String message, Throwable cause) {
		super(message, cause);
	}

	public Error404Exception(String message) {
		super(message);
	}

}
