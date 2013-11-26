package br.org.tutty.apis.messages.validators;

import java.util.List;

import br.org.tutty.apis.messages.exceptions.EmptyRegisterException;
import br.org.tutty.apis.messages.exceptions.NotFoundDefaultMessageException;
import br.org.tutty.apis.messages.models.ExceptionMessage;

@SuppressWarnings({"rawtypes", "unchecked"})
public class MessageExceptionValidator implements Validator{
	
	private List<ExceptionMessage> exceptionMessages;

	public MessageExceptionValidator(List<ExceptionMessage> exceptionMessages) {
		this.exceptionMessages = exceptionMessages;
	}

	@Override
	public void validate() throws NotFoundDefaultMessageException{
		emptyRegisterException();
		validateMessages();
	}

	private void validateMessages() {
		MessageValidator messageValidator;
		
		for (ExceptionMessage exceptionMessage : exceptionMessages) {
			messageValidator = new MessageValidator(exceptionMessage.getMessages());
			messageValidator.validate();
		}
	}
	
	private void emptyRegisterException(){
		for (ExceptionMessage exceptionMessage : exceptionMessages) {
			if (exceptionMessage.getException() == null) {
				throw new EmptyRegisterException();
			}
		}
	}

}
