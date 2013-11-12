package br.org.tutty.apis.messages.validators;

import java.util.ArrayList;
import java.util.List;

import br.org.tutty.apis.messages.exceptions.DefaultMessagesRepeatedException;
import br.org.tutty.apis.messages.exceptions.IDMessageRepeatedException;
import br.org.tutty.apis.messages.exceptions.NotFoundDefaultMessageException;
import br.org.tutty.apis.messages.models.Message;

public class MessageValidator implements Validator{

	private List<Message> defaultMessages;
	
	public MessageValidator(List<Message> messages) {
		this.defaultMessages = messages;
	}

	@Override
	public void validate() throws NotFoundDefaultMessageException {
		defaultMessageEmpty(defaultMessages);
		defaultMessageRepeated(defaultMessages);
		idMessageRepeated(defaultMessages);
	}

	public void defaultMessageRepeated(List<Message> defaultMessages){
		Message defaultMessage = null;

		for (Message message : defaultMessages) {
			if (message.isDefault()) {
				if (defaultMessage == null) {
					defaultMessage = message;
				} else {
					throw new DefaultMessagesRepeatedException();
				}
			}
		}
	}

	public void defaultMessageEmpty(List<Message> defaultMessages) throws NotFoundDefaultMessageException {
		Message defaultMessage = null;

		for (Message message : defaultMessages) {
			if (message.isDefault()) {
				defaultMessage = message;
			}
		}
		
		if (defaultMessage == null) {
			throw new NotFoundDefaultMessageException();
		}
	}

	public void idMessageRepeated(List<Message> messages) {
		List<Message> resultList = new ArrayList<Message>();
		
		for (Message message : messages) {
			if (!resultList.contains(message)) {
				resultList.add(message);
			}else {
				throw new IDMessageRepeatedException();
			}
		}
	}

}
