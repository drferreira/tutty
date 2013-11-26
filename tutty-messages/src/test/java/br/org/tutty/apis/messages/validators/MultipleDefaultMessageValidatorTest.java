package br.org.tutty.apis.messages.validators;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import br.org.tutty.apis.messages.exceptions.MultipleDefaultMessagesException;
import br.org.tutty.apis.messages.models.Message;

@RunWith(PowerMockRunner.class)
public class MultipleDefaultMessageValidatorTest {
	@Mock
	private Message message;
	
	
	@Test(expected = MultipleDefaultMessagesException.class)
	public void multipleDefaultMessageShouldBeThrowMultipleDefaultMessagesException(){
		List<Message> messages = new ArrayList<Message>();
		messages = new ArrayList<Message>();
		messages.add(message);
		messages.add(message);
		
		Mockito.when(message.isDefault()).thenReturn(true);
		
		MultipleDefaultMessageValidator defaultMessageValidator = new MultipleDefaultMessageValidator(messages);
		defaultMessageValidator.validate();
	}
	
	@Test
	public void multipleDefaultMessage(){
		List<Message> messages = new ArrayList<Message>();
		messages = new ArrayList<Message>();
		messages.add(message);
		
		Mockito.when(message.isDefault()).thenReturn(true);
		
		MultipleDefaultMessageValidator defaultMessageValidator = new MultipleDefaultMessageValidator(messages);
		
		defaultMessageValidator.validate();
	}

}
