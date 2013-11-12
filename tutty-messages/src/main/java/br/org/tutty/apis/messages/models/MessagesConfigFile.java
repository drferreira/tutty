package br.org.tutty.apis.messages.models;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@SuppressWarnings("rawtypes")
@XStreamAlias("messages-conf")
public class MessagesConfigFile {
	
	@XStreamAlias("exception-message")
	@XStreamImplicit
	private List<ExceptionMessage> exceptionMessages;

	public ExceptionMessage getExceptionMessage(Class exception){
		String exceptionName = exception.getName();
		
		for (ExceptionMessage exceptionMessage : exceptionMessages) {
			if (exceptionMessage.getException().getName().equals(exceptionName)) {
					return exceptionMessage;
			}
		}
		
		return null;
	}

	public List<ExceptionMessage> getExceptionMessages() {
		return exceptionMessages;
	}
}
