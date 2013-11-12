package br.org.tutty.apis.messages.models;

import javax.faces.application.FacesMessage.Severity;

import br.org.tutty.util.xstream.converters.GenericXStreamEnumConverter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias("message")
public class Message {

	@XStreamAsAttribute
	private String id;
	@XStreamAsAttribute
	private String resource;
	@XStreamAsAttribute
	private String key;
	@XStreamAsAttribute
	@XStreamConverter(GenericXStreamEnumConverter.class)
	private Severity severity;

	public Boolean isDefault(){
		return id == null || id.isEmpty() ? true : false;
	}
	
	public String getIdMessage() {
		return id;
	}
	public void setIdMessage(String idMessage) {
		this.id = idMessage;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public String getKeyMessage() {
		return key;
	}
	public void setKeyMessage(String keyMessage) {
		this.key = keyMessage;
	}

	public Severity getSeverity() {
		return severity;
	}
}
