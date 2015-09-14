package by.pauliukevich.model;

import java.io.Serializable;

public class ModelMessage implements Serializable {

	private static final long serialVersionUID = -718626748751709944L;

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}

}
