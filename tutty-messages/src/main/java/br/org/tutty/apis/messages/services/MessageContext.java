package br.org.tutty.apis.messages.services;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage.Severity;
import javax.inject.Inject;

import br.org.tutty.apis.messages.ConfigFileReader;
import br.org.tutty.apis.messages.exceptions.ConfigFileNotFoundException;
import br.org.tutty.apis.messages.exceptions.ConfigFileReadException;
import br.org.tutty.apis.messages.exceptions.DefaultMessagesRepeatedException;
import br.org.tutty.apis.messages.exceptions.IDMessageRepeatedException;
import br.org.tutty.apis.messages.exceptions.InterfaceMessageException;
import br.org.tutty.apis.messages.exceptions.MessageNotFoundException;
import br.org.tutty.apis.messages.exceptions.NotFoundDefaultMessageException;
import br.org.tutty.apis.messages.models.Message;
import br.org.tutty.apis.messages.validators.ConfigFileValidator;
import br.org.tutty.util.web.faces.MessageUtil;
import br.org.tutty.util.web.faces.ResourceUtil;

@SessionScoped
public class MessageContext {
	
	@Inject
	private ConfigFileReader configFileReader;
	@Inject
	private ResourceUtil resourceUtil;
	@Inject
	private MessageUtil facesUtil;
	private ConfigFileValidator configFileValidator;

	@PostConstruct
	public void setUp() throws IDMessageRepeatedException, DefaultMessagesRepeatedException, NotFoundDefaultMessageException, ConfigFileNotFoundException{
		configFileReader.init();
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
				String message = resourceUtil.getPropertyValue(exceptionMessage.getResource(), exceptionMessage.getKeyMessage());
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
