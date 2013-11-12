package br.org.tutty.apis.messages.validators;

import br.org.tutty.apis.messages.exceptions.NotFoundDefaultMessageException;

public interface Validator {

	void validate() throws NotFoundDefaultMessageException;

}
