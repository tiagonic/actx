package br.com.actx.arquitetura.util;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;

public class MessagesUtil {
	public static void addInfoMessage(String message) {
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		ExternalContext externalContext = facesContext.getExternalContext(); 
		externalContext.getFlash().setKeepMessages(true); 
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
	}

	public static void addErrorMessage(String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
	}

	public static void addWarnMessage(String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail));
	}
}
