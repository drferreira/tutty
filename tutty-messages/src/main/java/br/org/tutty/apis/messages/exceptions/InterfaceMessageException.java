package br.org.tutty.apis.messages.exceptions;

@SuppressWarnings("rawtypes")
public class InterfaceMessageException extends RuntimeException {

	private static final long serialVersionUID = -4023489575637981834L;
	
	private Class exception;
	private String idMessage;
	
	public InterfaceMessageException(Class exception, String idMessage) {
		this.exception = exception;
		this.idMessage = idMessage;
	}
	
	public InterfaceMessageException(Class exception) {
		this.exception = exception;
		this.idMessage = null;
	}

	public Class getException() {
		return exception;
	}

	public String getIdMessage() {
		return idMessage;
	}
}
