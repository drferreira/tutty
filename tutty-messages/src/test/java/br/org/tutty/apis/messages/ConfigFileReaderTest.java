package br.org.tutty.apis.messages;

import java.io.InputStream;

import javax.faces.application.FacesMessage.Severity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.org.tutty.apis.messages.models.MessagesConfigFile;
import br.org.tutty.util.jee.web.ResourceUtil;
import br.org.tutty.util.reflections.ReflectionUtil;
import br.org.tutty.util.xstream.GenericXStreamEnumConverter;

import com.thoughtworks.xstream.XStream;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = ConfigFileReader.class)
public class ConfigFileReaderTest {

	@InjectMocks
	private ConfigFileReader configFileReader;

	@Mock
	private MessagesConfigFile messagesConf;
	@Mock
	private ReflectionUtil reflectionUtil;
	@Mock
	private XStream xstream;
	@Mock
	private GenericXStreamEnumConverter genericXStreamEnumConverter;
	@Mock
	private ResourceUtil resourceUtil;
	@Mock
	private InputStream inputStream;

	@Before
	public void setUp() throws Exception {
		configFileReader = new ConfigFileReader();
		
		PowerMockito.whenNew(XStream.class).withNoArguments().thenReturn(xstream);
		PowerMockito.whenNew(GenericXStreamEnumConverter.class).withArguments(Severity.class).thenReturn(genericXStreamEnumConverter);
		PowerMockito.whenNew(ReflectionUtil.class).withNoArguments().thenReturn(reflectionUtil);
		
		Mockito.when(reflectionUtil.whoMadeTheLastCall()).thenReturn(ConfigFileReaderTest.class);
	}

	@Test
	public void initShouldBeGetInputStreamWithCorrectFileName() throws Exception {
		configFileReader.init(resourceUtil);

		Mockito.verify(xstream).processAnnotations(Any.class);
		Mockito.verify(reflectionUtil).whoMadeTheLastCall();
	}

}
