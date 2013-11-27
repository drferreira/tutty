package br.org.tutty.apis.messages;

import java.io.InputStream;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.org.tutty.apis.messages.exceptions.ConfigFileReadException;
import br.org.tutty.apis.messages.exceptions.MessageNotFoundException;
import br.org.tutty.apis.messages.models.Message;
import br.org.tutty.apis.messages.models.MessagesConfigFile;
import br.org.tutty.apis.messages.validators.ConfigFileValidator;
import br.org.tutty.util.jee.web.MessageUtil;
import br.org.tutty.util.jee.web.ResourceUtil;
import br.org.tutty.util.reflections.ReflectionUtil;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = MessageContext.class)
public class MessageContextTest {

	private static final String TARGET_ID = "TARGET_ID";
	private static final Severity SEVERITY_INFO = FacesMessage.SEVERITY_INFO;
	private static final String MESSAGE_TEXT = "MESSAGE";
	private static final String RESOURCE_NAME = "RESOURCE_NAME";
	private static final String RESOURCE_KEY = "RESOURCE_KEY";
	
	@InjectMocks
	private MessageContext messageContext;
	@Mock
	private ConfigFileReader configFileReader;
	@Mock
	private ReflectionUtil reflectionUtil;
	@Mock
	private ResourceUtil resourceUtil;
	@Mock
	private MessagesConfigFile messagesConfigFile;
	@Mock
	private ConfigFileValidator configFileValidator;
	@Mock
	private Message message;
	@Mock
	private Properties properties;
	@Mock
	private InputStream inputStream;
	@Mock
	private MessageUtil messageUtil;
	
	@Before
	public void setUp() throws Exception{
		PowerMockito.whenNew(ConfigFileValidator.class).withArguments(messagesConfigFile).thenReturn(configFileValidator);
		PowerMockito.whenNew(ResourceUtil.class).withAnyArguments().thenReturn(resourceUtil);
		PowerMockito.whenNew(ConfigFileReader.class).withArguments(MessageContext.MESSAGE_CONF_XML_NAME).thenReturn(configFileReader);;
		
		Mockito.when(configFileReader.getMessagesConf()).thenReturn(messagesConfigFile);
		Mockito.when(reflectionUtil.whoMadeTheLastCall()).thenReturn(MessageContextTest.class);
		
	}
	
	@Test
	public void shouldBeExecuteInitConfigFileReader(){
		messageContext.setUp();
		
		Mockito.verify(configFileReader).init(resourceUtil);
	}
	
	@Test
	public void shouldBeExecuteValidateXmlWhenRunPostConstruct(){
		messageContext.setUp();
		
		Mockito.verify(configFileValidator).validate();
	}
	
	@Test
	public void shouldBeClassloaderClassReceivingInjection(){
		messageContext.setUp();
		
		Mockito.verify(reflectionUtil.whoMadeTheLastCall());
	}
	
	@Test(expected = MessageNotFoundException.class)
	public void shouldBeThrowMessageNotFoundExceptionWhenExceptionMessageIsNull(){
		messageContext.showGenericMessage(null, TARGET_ID);
	}
	
	@Test(expected = ConfigFileReadException.class)
	public void shouldBeThrowConfigFileReaderExceptionWhenGeResourceStreamThrowIOException() throws Exception{
		PowerMockito.whenNew(Properties.class).withNoArguments().thenReturn(properties);
		
		Mockito.when(message.getResource()).thenReturn(RESOURCE_NAME);
		Mockito.when(resourceUtil.getResourceStream(RESOURCE_NAME)).thenThrow(new ConfigFileReadException());
		
		messageContext.showGenericMessage(message, TARGET_ID);
	}
	
	@Test
	public void shouldBeLoadPropertieFile() throws Exception{
		PowerMockito.whenNew(Properties.class).withNoArguments().thenReturn(properties);
		
		Mockito.when(message.getResource()).thenReturn(RESOURCE_NAME);
		Mockito.when(message.getKeyMessage()).thenReturn(RESOURCE_KEY);
		Mockito.when(message.getSeverity()).thenReturn(SEVERITY_INFO);
		Mockito.when(resourceUtil.getResourceStream(RESOURCE_NAME)).thenReturn(inputStream);
		Mockito.when(properties.getProperty(RESOURCE_KEY)).thenReturn(MESSAGE_TEXT);
		
		messageContext.showGenericMessage(message, TARGET_ID);
		
		Mockito.verify(message).getResource();
		Mockito.verify(resourceUtil).getResourceStream(RESOURCE_NAME);
		Mockito.verify(properties).load(inputStream);
		Mockito.verify(messageUtil).addMessage(SEVERITY_INFO, SEVERITY_INFO.toString(), MESSAGE_TEXT, TARGET_ID);
	}
}
