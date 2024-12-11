package br.com.act.validators;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("customRegexValidator")
public class CustomRegexValidator implements Validator<Object> {

	private String pattern;

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (pattern != null && value != null && !value.toString().matches(pattern)) {
			String label = (String) component.getAttributes().get("label");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, label + ": Insira um valor válido.", null);
			throw new ValidatorException(msg);
		}
	}
}
