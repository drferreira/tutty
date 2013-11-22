package br.org.tutty.util.jee.web;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class MessageUtil {
	
	private FacesContext facesContext;
	
	public MessageUtil(FacesContext facesContext) {
		this.facesContext = facesContext;
	}
	
	public void addMessage(Severity severity, String summary, String message, String target){
		facesContext.addMessage(target, getFacesMessage(severity, summary, message));
	}
	
	public void addFatalMessage(String summary, String message, String target){
		addMessage(FacesMessage.SEVERITY_FATAL, summary, message, target);
	}
	
	public void addErrorMessage(String summary, String message, String target){
		addMessage(FacesMessage.SEVERITY_ERROR, summary, message, target);
	}
	
	public void addWarningMessage(String summary, String message, String target){
		addMessage(FacesMessage.SEVERITY_WARN, summary, message, target);
	}
	
	public void addInfoMessage(String summary, String message, String target){
		addMessage(FacesMessage.SEVERITY_INFO, summary, message, target);
	}
	
	public FacesMessage getFacesMessage(Severity severity, String summary, String message){
		return new FacesMessage(severity, summary, message);
	}
}
