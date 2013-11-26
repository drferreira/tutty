package br.org.tutty.apis.messages.validators;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import br.org.tutty.apis.messages.exceptions.NotFoundDefaultMessageException;
import br.org.tutty.apis.messages.models.Message;

@RunWith(PowerMockRunner.class)
public class DefaultMessageEmptyValidatorTest {
	@Mock
	private Message message;
	
	@Test(expected = NotFoundDefaultMessageException.class)
	public void defaultMessageEmptyShouldBeThrowNotFoundDefaultMessageException(){
		List<Message> messages = new ArrayList<Message>();
		Mockito.when(message.isDefault()).thenReturn(false);
		
		messages.add(message);
		
		DefaultMessageEmptyValidator defaultMessageEmptyValidator = new DefaultMessageEmptyValidator(messages);
		
		defaultMessageEmptyValidator.validate();
	}
	
	@Test
	public void existDefaultMessage(){
		List<Message> messages = new ArrayList<Message>();
		Mockito.when(message.isDefault()).thenReturn(true);
		
		messages.add(message);
		
		DefaultMessageEmptyValidator defaultMessageEmptyValidator = new DefaultMessageEmptyValidator(messages);
		
		defaultMessageEmptyValidator.validate();
	}

}
