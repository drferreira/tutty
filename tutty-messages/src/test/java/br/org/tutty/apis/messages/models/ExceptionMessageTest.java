package br.org.tutty.apis.messages.models;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.org.tutty.apis.messages.models.ExceptionMessage;
import br.org.tutty.apis.messages.models.Message;

@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings({"rawtypes", "unchecked"})
public class ExceptionMessageTest {
	
	private ExceptionMessage exceptionMessage;
	private List<Message> messages;

	@Mock
	private Message defaultMessage;
	@Mock
	private Message message;

	@Before
	public void setUp() {
		exceptionMessage = new ExceptionMessage();
		messages = new ArrayList<Message>();

		Mockito.when(defaultMessage.isDefault()).thenReturn(true);
		Mockito.when(message.isDefault()).thenReturn(false);
	}
	
	@Test
	public void shouldBeReturnDefaultMessage(){
		Mockito.when(message.isDefault()).thenReturn(true);
		
		messages.add(new Message());
		messages.add(new Message());
		messages.add(message);
		exceptionMessage.setMessages(messages);
		
		Message result = exceptionMessage.getDefaultMessage();
		Assert.assertNotNull(result);
	}
	
	@Test
	public void shouldBeReturnNullWhenDontHaveDefaultMessage(){
		Mockito.when(message.isDefault()).thenReturn(false);
		
		messages.add(message);
		exceptionMessage.setMessages(messages);
		
		Message result = exceptionMessage.getDefaultMessage();
		Assert.assertNull(result);
	}
	
	@Test
	public void shouldBeReturnMessageById(){
		String idMessage = "id_message";
		
		Mockito.when(message.getIdMessage()).thenReturn(idMessage);
		messages.add(message);
		exceptionMessage.setMessages(messages);
		
		Message resultMessage = exceptionMessage.getMessage(idMessage);
		Assert.assertEquals(message, resultMessage);
	}
	
	@Test
	public void shouldBeReturnNullWhenNotFoundMessageById(){
		String idMessage = "id_message";
		String idMessageFail = "id_message_fail";
		
		Mockito.when(message.getIdMessage()).thenReturn(idMessage);
		messages.add(message);
		exceptionMessage.setMessages(messages);
		
		Message resultMessage = exceptionMessage.getMessage(idMessageFail);
		Assert.assertNull(resultMessage);
	}


}
