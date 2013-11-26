package br.org.tutty.apis.messages.validators;

import java.util.List;

import br.org.tutty.apis.messages.exceptions.NotFoundDefaultMessageException;
import br.org.tutty.apis.messages.models.Message;

public class DefaultMessageEmptyValidator implements Validator{

	private List<Message> messages;

	public DefaultMessageEmptyValidator(List<Message> messages) {
		this.messages = messages;
	}

	@Override
	public void validate() throws NotFoundDefaultMessageException {
		Message defaultMessage = null;

		for (Message message : messages) {
			if (message.isDefault()) {
				defaultMessage = message;
			}
		}
		
		if (defaultMessage == null) {
			throw new NotFoundDefaultMessageException();
		}
	}
}
