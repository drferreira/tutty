package br.org.tutty.apis.messages.models;

import java.util.List;

import br.org.tutty.apis.messages.exceptions.MessageNotFoundException;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@SuppressWarnings("rawtypes")
@XStreamAlias("messages-conf")
public class MessagesConfigFile {
	
	@XStreamImplicit(itemFieldName = "exception-message")
	private List<ExceptionMessage> exceptionMessages;
	
	public ExceptionMessage getExceptionMessage(Class exception){
		String exceptionName = exception.getName();
		
		for (ExceptionMessage exceptionMessage : exceptionMessages) {
			if (exceptionMessage.getException().getName().equals(exceptionName)) {
					return exceptionMessage;
			}
		}
		
		throw new MessageNotFoundException();
	}
	

	public List<ExceptionMessage> getExceptionMessages() {
		return exceptionMessages;
	}

	public void setExceptionMessages(List<ExceptionMessage> exceptionMessages) {
		this.exceptionMessages = exceptionMessages;
	}
}
