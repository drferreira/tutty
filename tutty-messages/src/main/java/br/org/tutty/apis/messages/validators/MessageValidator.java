package br.org.tutty.apis.messages.validators;

import java.util.ArrayList;
import java.util.List;

import br.org.tutty.apis.messages.exceptions.NotFoundDefaultMessageException;
import br.org.tutty.apis.messages.models.Message;

public class MessageValidator implements Validator{

	private List<Validator> validators;
	
	public MessageValidator(List<Message> messages) {
		validators = new ArrayList<Validator>();
		validators.add(new DefaultMessageEmptyValidator(messages));
		validators.add(new MultipleDefaultMessageValidator(messages));
		validators.add(new IdMessageRepeatedValidator(messages));
	}
	
	@Override
	public void validate() throws NotFoundDefaultMessageException {
		for (Validator validator : validators) {
			validator.validate();
		}
	}

	public List<Validator> getValidators() {
		return validators;
	}
}
