package br.org.tutty.apis.messages.validators;

import java.util.ArrayList;
import java.util.List;

import br.org.tutty.apis.messages.exceptions.MultipleDefaultMessagesException;
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
	public void validate() throws IDMessageRepeatedException, MultipleDefaultMessagesException, NotFoundDefaultMessageException {
		for (Validator validator : validators) {
			validator.validate();
		}
	}

	public List<Validator> getValidators() {
		return validators;
	}

	public void setValidators(List<Validator> validators) {
		this.validators = validators;
	}
}
