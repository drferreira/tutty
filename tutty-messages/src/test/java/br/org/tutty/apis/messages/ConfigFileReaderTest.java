package br.org.tutty.apis.messages;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.org.tutty.apis.messages.models.MessagesConfigFile;
import br.org.tutty.util.ReflectionUtil;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = ConfigFileReader.class)
public class ConfigFileReaderTest {

	@InjectMocks
	private ConfigFileReader configFileReader;
	
	@Mock
	private MessagesConfigFile messagesConfigFile;
	@Mock
	private ReflectionUtil reflectionUtil;
	
	@Before
	public void setUp(){
		configFileReader = new ConfigFileReader();
	}
	
	@Test
	public void shouldBeCallInitWhenCreateObject() throws Exception{
		PowerMockito.whenNew(ReflectionUtil.class).withNoArguments().thenReturn(reflectionUtil);
		Mockito.when(reflectionUtil.whoMadeTheLastCall()).thenReturn(ConfigFileReaderTest.class);
		
		configFileReader.init();
		
		Mockito.verify(reflectionUtil).whoMadeTheLastCall();
	}
	
	
}
