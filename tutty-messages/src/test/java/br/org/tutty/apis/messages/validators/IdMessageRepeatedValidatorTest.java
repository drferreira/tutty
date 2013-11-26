package br.org.tutty.apis.messages.validators;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import br.org.tutty.apis.messages.exceptions.IDMessageRepeatedException;
import br.org.tutty.apis.messages.models.Message;

@RunWith(PowerMockRunner.class)
public class IdMessageRepeatedValidatorTest {
	private static final String ID_MESSAGE = "ID_MESSAGE";

	private Message message;

	@Test(expected = IDMessageRepeatedException.class)
	public void idMessageRepeatedShouldBeThrowIDMessageRepeatedException() {
		List<Message> messages = new ArrayList<Message>();
		messages = new ArrayList<Message>();

		message = new Message();
		message.setIdMessage(ID_MESSAGE);

		messages.add(message);
		message.setIdMessage(ID_MESSAGE);

		messages.add(message);
		message.setIdMessage(ID_MESSAGE + "_THIRD");
		messages.add(message);

		IdMessageRepeatedValidator idMessageRepeatedValidator = new IdMessageRepeatedValidator(messages);

		idMessageRepeatedValidator.validate();
		;
	}

	@Test
	public void idMessageRepeatedShould() {
		List<Message> messages = new ArrayList<Message>();
		messages = new ArrayList<Message>();

		Message message = new Message();
		message.setIdMessage(ID_MESSAGE);
		messages.add(message);

		Message messageSecond = new Message();
		messageSecond.setIdMessage(ID_MESSAGE + "_SECOND");
		messages.add(messageSecond);

		Message messageThird = new Message();
		messageThird.setIdMessage(ID_MESSAGE + "_THIRD");
		messages.add(messageThird);

		IdMessageRepeatedValidator idMessageRepeatedValidator = new IdMessageRepeatedValidator(messages);

		idMessageRepeatedValidator.validate();
	}
}
