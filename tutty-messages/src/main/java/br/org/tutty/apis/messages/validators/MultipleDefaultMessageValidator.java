package br.org.tutty.apis.messages.validators;

import java.util.List;

import br.org.tutty.apis.messages.exceptions.MultipleDefaultMessagesException;
import br.org.tutty.apis.messages.exceptions.NotFoundDefaultMessageException;
import br.org.tutty.apis.messages.models.Message;

public class MultipleDefaultMessageValidator implements Validator{
	
	private List<Message> messages;

	public MultipleDefaultMessageValidator(List<Message> messages) {
		this.messages = messages;
	}

	@Override
	public void validate() throws NotFoundDefaultMessageException {
		Message defaultMessage = null;

		for (Message message : messages) {
			if (message.isDefault()) {
				if (defaultMessage == null) {
					defaultMessage = message;
				} else {
					throw new MultipleDefaultMessagesException();
				}
			}
		}
		
	}
}
