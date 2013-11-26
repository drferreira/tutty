package br.org.tutty.apis.messages.validators;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.org.tutty.apis.messages.exceptions.EmptyRegisterException;
import br.org.tutty.apis.messages.models.ExceptionMessage;
import br.org.tutty.apis.messages.models.Message;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = MessageExceptionValidator.class)
@SuppressWarnings("rawtypes")
public class MessageExceptionValidatorTest {

	private MessageExceptionValidator messageExceptionValidator;
	private List<ExceptionMessage> exceptionMessages;
	private List<Message> messages;
	
	@Mock
	private ExceptionMessage<Exception> exceptionMessage;
	@Mock
	private Message message;
	@Mock
	private MessageValidator messageValidator;

	@Before
	public void setUp() throws Exception{
		exceptionMessages = new ArrayList<ExceptionMessage>();
		messages = new ArrayList<Message>();
		
		exceptionMessages.add(exceptionMessage);
		messages.add(message);
		
		messageExceptionValidator = new MessageExceptionValidator(exceptionMessages);
		
		Mockito.when(exceptionMessage.getException()).thenReturn(Exception.class);
		Mockito.when(exceptionMessage.getMessages()).thenReturn(messages);
		PowerMockito.whenNew(MessageValidator.class).withArguments(messages).thenReturn(messageValidator);
	}
	
	@Test
	public void validateMessagesShouldBeCallValidateMessageValidator() throws Exception{
		messageExceptionValidator.validate();
		
		Mockito.verify(messageValidator).validate();
	}
	
	@Test(expected = EmptyRegisterException.class)
	public void shoudlBeThrowEmptyRegisterExceptionWhenExceptionValueIsNull(){
		Mockito.when(exceptionMessage.getException()).thenReturn(null);
		
		messageExceptionValidator.validate();
	}
	
}
