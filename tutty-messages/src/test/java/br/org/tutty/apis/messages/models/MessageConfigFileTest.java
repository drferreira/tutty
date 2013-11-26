package br.org.tutty.apis.messages.models;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import br.org.tutty.apis.messages.exceptions.DataNotFoundException;
import br.org.tutty.apis.messages.exceptions.MessageNotFoundException;

@RunWith(PowerMockRunner.class)
@SuppressWarnings("rawtypes")
public class MessageConfigFileTest {

	private MessagesConfigFile messagesConfigFile;
	private List<ExceptionMessage> exceptionMessages;
	
	@Mock
	private ExceptionMessage exceptionMessage;
	@Mock
	private ExceptionMessage exceptionMessageDataNotFound;
	
	@Before
	public void setUp(){
		exceptionMessages = new ArrayList<ExceptionMessage>();
		
		exceptionMessages.add(exceptionMessage);
		exceptionMessages.add(exceptionMessageDataNotFound);
		
		messagesConfigFile = new MessagesConfigFile();
		messagesConfigFile.setExceptionMessages(exceptionMessages);
		
		Mockito.when(exceptionMessage.getException()).thenReturn(Exception.class);
		Mockito.when(exceptionMessageDataNotFound.getException()).thenReturn(DataNotFoundException.class);
	}
	
	@Test
	public void shouldBeReturnSpecificExceptionMessage(){
		ExceptionMessage exceptionMessage = messagesConfigFile.getExceptionMessage(Exception.class);
		Assert.assertEquals(Exception.class, exceptionMessage.getException());
	}
	
	@Test(expected = MessageNotFoundException.class)
	public void shoudBeThrowMessageNotFoundExceptionWhenListIsEmpty(){
		messagesConfigFile.setExceptionMessages(new ArrayList<ExceptionMessage>());
		messagesConfigFile.getExceptionMessage(Exception.class);
	}
	
	@Test(expected = MessageNotFoundException.class)
	public void shoudBeThrowMessageNotFoundExceptionWhenExceptionNotRegistered(){
		messagesConfigFile.getExceptionMessage(Runtime.class);
	}
}
