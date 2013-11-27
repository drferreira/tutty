package br.org.tutty.apis.messages;

import java.io.InputStream;

import javax.faces.application.FacesMessage;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.org.tutty.apis.messages.exceptions.ConfigFileNotFoundException;
import br.org.tutty.apis.messages.exceptions.InterfaceMessageException;
import br.org.tutty.apis.messages.models.Message;
import br.org.tutty.apis.messages.models.MessagesConfigFile;
import br.org.tutty.util.exceptions.ResourceNotFoundException;
import br.org.tutty.util.jee.web.ResourceUtil;

import com.thoughtworks.xstream.XStream;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = ConfigFileReader.class)
public class ConfigFileReaderTest {
	
	private static final String INCORRECT_XML = "incorrect_conf.xml";
	private static String CORRECT_XML = "correct_conf.xml";
	
	@Mock
	private ResourceUtil resourceUtil;
	@Mock
	private InputStream inputStream;
	@Mock
	private XStream xStream;
	@Mock
	private MessagesConfigFile messagesConfigFile;
	@Mock
	private InterfaceMessageException interfaceMessageException;
	
	public void setUp() throws Exception{
		PowerMockito.whenNew(XStream.class).withNoArguments().thenReturn(xStream);
		Mockito.when(resourceUtil.getResourceStream(CORRECT_XML)).thenReturn(inputStream);
		Mockito.when(xStream.fromXML(inputStream)).thenReturn(messagesConfigFile);
	}

	@Test
	public void shouldBeCallGetResourceStream() throws Exception{
		setUp();
		
		ConfigFileReader configFileReader = new ConfigFileReader(CORRECT_XML);
		
		configFileReader.init(resourceUtil);
		
		Mockito.verify(resourceUtil).getResourceStream(CORRECT_XML);
	}
	
	@Test
	public void shouldBeConvertInputStremToMessageConf() throws Exception{
		setUp();
		
		ConfigFileReader configFileReader = new ConfigFileReader(CORRECT_XML);
		
		configFileReader.init(resourceUtil);
		
		Mockito.verify(xStream).fromXML(inputStream);
	}
	
	@Test(expected = ConfigFileNotFoundException.class)
	public void shouldBeThrowConfigFileNotFoundExceptionWhenReceiveIncorrectConfFileName(){
		Mockito.when(resourceUtil.getResourceStream(INCORRECT_XML)).thenReturn(inputStream);
		Mockito.when(resourceUtil.getResourceStream(INCORRECT_XML)).thenThrow(new ResourceNotFoundException());
		
		ConfigFileReader configFileReader = new ConfigFileReader(INCORRECT_XML);
		configFileReader.init(resourceUtil);
	}
	
	@Test
	public void shouldBeGetDefaultMessage(){
		ConfigFileReader configFileReader = new ConfigFileReader(CORRECT_XML);
		ResourceUtil resourceUtil = new ResourceUtil(getClass().getClassLoader());
		
		InterfaceMessageException interfaceMessageException = new InterfaceMessageException(Exception.class);
		
		configFileReader.init(resourceUtil);
		Message message = configFileReader.getMessage(interfaceMessageException);
		
		Assert.assertTrue(message.isDefault());
	}
	
	@Test
	public void shouldBeGetNotDefaulMessage(){
		ConfigFileReader configFileReader = new ConfigFileReader(CORRECT_XML);
		ResourceUtil resourceUtil = new ResourceUtil(getClass().getClassLoader());
		
		InterfaceMessageException interfaceMessageException = new InterfaceMessageException(Exception.class, "warning");
		
		configFileReader.init(resourceUtil);
		Message message = configFileReader.getMessage(interfaceMessageException);
		
		Assert.assertFalse(message.isDefault());
		Assert.assertEquals("warning", message.getIdMessage());
	}
	
	@Test
	public void shouldBeGeCorrectKeyMessage(){
		ConfigFileReader configFileReader = new ConfigFileReader(CORRECT_XML);
		ResourceUtil resourceUtil = new ResourceUtil(getClass().getClassLoader());
		
		InterfaceMessageException interfaceMessageException = new InterfaceMessageException(Exception.class, "warning");
		
		configFileReader.init(resourceUtil);
		Message message = configFileReader.getMessage(interfaceMessageException);
		
		Assert.assertEquals("test2", message.getKeyMessage());
	}
	
	@Test
	public void shouldBeGeCorrectSeverity(){
		ConfigFileReader configFileReader = new ConfigFileReader(CORRECT_XML);
		ResourceUtil resourceUtil = new ResourceUtil(getClass().getClassLoader());
		
		InterfaceMessageException interfaceMessageException = new InterfaceMessageException(Exception.class, "warning");
		
		configFileReader.init(resourceUtil);
		Message message = configFileReader.getMessage(interfaceMessageException);
		
		Assert.assertEquals("warning", message.getIdMessage());
	}
	
	
	@Test
	public void shouldBeCallGetResourceStreamWithCorrectName(){
		ConfigFileReader configFileReader = new ConfigFileReader(CORRECT_XML);
		ResourceUtil resourceUtil = new ResourceUtil(getClass().getClassLoader());
		
		configFileReader.init(resourceUtil);
		
		InterfaceMessageException interfaceMessageException = new InterfaceMessageException(Exception.class);
		Message message = configFileReader.getMessage(interfaceMessageException);
		
		Assert.assertEquals(FacesMessage.SEVERITY_INFO, message.getSeverity());
		Assert.assertTrue(message.isDefault());
		Assert.assertEquals("messages", message.getResource());
		Assert.assertEquals("test1", message.getKeyMessage());
	}
}
