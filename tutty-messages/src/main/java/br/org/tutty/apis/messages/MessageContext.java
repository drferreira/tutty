package br.org.tutty.apis.messages;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage.Severity;
import javax.inject.Inject;

import br.org.tutty.apis.messages.exceptions.ConfigFileNotFoundException;
import br.org.tutty.apis.messages.exceptions.ConfigFileReadException;
import br.org.tutty.apis.messages.exceptions.DefaultMessagesRepeatedException;
import br.org.tutty.apis.messages.exceptions.IDMessageRepeatedException;
import br.org.tutty.apis.messages.exceptions.InterfaceMessageException;
import br.org.tutty.apis.messages.exceptions.MessageNotFoundException;
import br.org.tutty.apis.messages.exceptions.NotFoundDefaultMessageException;
import br.org.tutty.apis.messages.models.Message;
import br.org.tutty.apis.messages.validators.ConfigFileValidator;
import br.org.tutty.util.jee.web.MessageUtil;
import br.org.tutty.util.jee.web.ResourceUtil;
import br.org.tutty.util.reflections.ReflectionUtil;

@SessionScoped
public class MessageContext {
	
	@Inject
	private ConfigFileReader configFileReader;
	@Inject
	private MessageUtil facesUtil;
	@Inject
	private ReflectionUtil reflectionUtil;
	private ResourceUtil resourceUtil;
	private ConfigFileValidator configFileValidator;

	@PostConstruct
	public void setUp() throws IDMessageRepeatedException, DefaultMessagesRepeatedException, NotFoundDefaultMessageException, ConfigFileNotFoundException{
		ResourceUtil resourceUtil = new ResourceUtil(reflectionUtil.whoMadeTheLastCall().getClassLoader());
		configFileReader.init(resourceUtil);
		configFileValidator = new ConfigFileValidator(configFileReader.getMessagesConf());
		configFileValidator.validate();
	}
	
	public void showDefaultMessage(Exception exception, String target) throws IOException{
		Message exceptionMessage = configFileReader.getMessage(new InterfaceMessageException(exception.getClass()));
		showGenericMessage(exceptionMessage, target);
	}
	
	public void showMessage(InterfaceMessageException interfaceMessageException, String target) throws IOException{
		Message exceptionMessage = configFileReader.getMessage(interfaceMessageException);
		showGenericMessage(exceptionMessage, target);
	}
	
	public void showMessage(Exception exception, String idMessage, String target) throws IOException{
		Message exceptionMessage = configFileReader.getMessage(new InterfaceMessageException(exception.getClass(), idMessage));
		showGenericMessage(exceptionMessage, target);
	}
	
	public void showGenericMessage(Message exceptionMessage, String target){
		if (exceptionMessage != null) {

			try {
				Properties properties = new Properties();
				properties.load(resourceUtil.getResourceStream(exceptionMessage.getResource()));
				String message = properties.getProperty(exceptionMessage.getKeyMessage());
				
				Severity severity = exceptionMessage.getSeverity();
				facesUtil.addMessage(severity, severity.toString(), message, target);
			
			} catch (IOException e) {
				throw new ConfigFileReadException();
			}
		
		}else {
			throw new MessageNotFoundException();
		}
	}
}