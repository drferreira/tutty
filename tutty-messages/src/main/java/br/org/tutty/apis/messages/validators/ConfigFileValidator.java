package br.org.tutty.apis.messages.validators;

import java.util.ArrayList;
import java.util.List;

import br.org.tutty.apis.messages.exceptions.DefaultMessagesRepeatedException;
import br.org.tutty.apis.messages.exceptions.IDMessageRepeatedException;
import br.org.tutty.apis.messages.exceptions.NotFoundDefaultMessageException;
import br.org.tutty.apis.messages.models.MessagesConfigFile;

public class ConfigFileValidator implements Validator {

	private List<Validator> validators;

	public ConfigFileValidator(MessagesConfigFile messagesConfigFile) {
		validators = new ArrayList<Validator>();
		validators.add(new MessageExceptionValidator(messagesConfigFile.getExceptionMessages()));
	}

	@Override
	public void validate() throws IDMessageRepeatedException, DefaultMessagesRepeatedException, NotFoundDefaultMessageException {
		for (Validator validator : validators) {
			validator.validate();
		}
	}
}
