package br.org.tutty.util.xstream;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;

import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

@SuppressWarnings("rawtypes")
public class SeverityMessageConverter extends AbstractSingleValueConverter {

	@Override
	public boolean canConvert(Class type) {
		return type.equals(FacesMessage.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Object fromString(String str) {
		List<Severity> facesMessages = new ArrayList<Severity>(FacesMessage.VALUES);
		
		for (Severity severity : facesMessages) {
			if (severity.toString().equals(str+" "+severity.getOrdinal())) {
				return severity;
			}
		}
		return null;
	}

}
