package br.org.tutty.util.jee.web;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = MessageUtil.class)
public class MessageUtilTest {

	private MessageUtil messageUtil;

	@Mock
	private FacesContext context;
	@Mock
	private FacesMessage facesMessage;

	private String summary = "sumary";
	private String message = "message";
	private String target = "target";


	@Before
	public void setUp() {
		messageUtil = new MessageUtil(context);
	}

	@Test
	public void shouldBeFatalMessage() throws Exception {
		PowerMockito.whenNew(FacesMessage.class).withArguments(FacesMessage.SEVERITY_FATAL, summary, message).thenReturn(facesMessage);

		messageUtil.addMessage(FacesMessage.SEVERITY_FATAL, summary, message, target);
		Mockito.verify(context).addMessage(target, facesMessage);
	}
	
	@Test
	public void shouldBeErrorMessage() throws Exception {
		PowerMockito.whenNew(FacesMessage.class).withArguments(FacesMessage.SEVERITY_ERROR, summary, message).thenReturn(facesMessage);

		messageUtil.addMessage(FacesMessage.SEVERITY_ERROR, summary, message, target);
		Mockito.verify(context).addMessage(target, facesMessage);
	}
	
	@Test
	public void shouldBeWarningMessage() throws Exception {
		PowerMockito.whenNew(FacesMessage.class).withArguments(FacesMessage.SEVERITY_WARN, summary, message).thenReturn(facesMessage);

		messageUtil.addMessage(FacesMessage.SEVERITY_WARN, summary, message, target);
		Mockito.verify(context).addMessage(target, facesMessage);
	}
	
	@Test
	public void shouldBeInfoMessage() throws Exception {
		PowerMockito.whenNew(FacesMessage.class).withArguments(FacesMessage.SEVERITY_INFO, summary, message).thenReturn(facesMessage);

		messageUtil.addMessage(FacesMessage.SEVERITY_INFO, summary, message, target);
		Mockito.verify(context).addMessage(target, facesMessage);
	}

}
