package br.org.tutty.apis.messages.validators;

import java.util.ArrayList;
import java.util.List;

import br.org.tutty.apis.messages.exceptions.IDMessageRepeatedException;
import br.org.tutty.apis.messages.exceptions.NotFoundDefaultMessageException;
import br.org.tutty.apis.messages.models.Message;

public class IdMessageRepeatedValidator implements Validator {
	
	private List<Message> messages;

	public IdMessageRepeatedValidator(List<Message> messages) {
		this.messages = messages;
	}

	@Override
	public void validate() throws NotFoundDefaultMessageException {
		List<Message> resultList = new ArrayList<Message>();

		for (Message message : messages) {
			if (!resultList.contains(message)) {
				resultList.add(message);
			} else {
				throw new IDMessageRepeatedException();
			}
		}
	}

}
