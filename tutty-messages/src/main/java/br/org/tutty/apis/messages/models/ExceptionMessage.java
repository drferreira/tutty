package br.org.tutty.apis.messages.models;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * Classe criada para representar uma mensagem definida atraves de uma exceção.
 * 
 * @author drferreira
 * @param <T>
 * 
 */
@XStreamAlias("exception-message")
public class ExceptionMessage<T extends Exception> {

	@XStreamAlias("exception-register")
	@XStreamAsAttribute
	private Class<T> exception;
	@XStreamImplicit(itemFieldName = "message")
	private List<Message> messages;

	/**
	 * Utilizado para buscar a mensagem setada como default. Caso não seja
	 * encontrada uma mensagem valida sera retornado NULL.
	 * 
	 * @return
	 */
	public Message getDefaultMessage() {
		for (Message message : messages) {
			if (message.isDefault()) {
				return message;
			}
		}

		return null;
	}

	/**
	 * Busca uma mensagem a partir do seu respectivo ID
	 * 
	 * @param idMessage
	 * @return
	 */
	public Message getMessage(String idMessage) {
		for (Message message : messages) {
			if (idMessage.equals(message.getIdMessage())) {
				return message;
			}
		}

		return null;
	}

	public Class<T> getException() {
		return exception;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setException(Class<T> exception) {
		this.exception = exception;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}
