package br.org.tutty.util.jee.web;

import java.io.InputStream;

import br.org.tutty.util.exceptions.ResourceNotFoundException;

public class ResourceUtil {
	
	private ClassLoader classLoader;
	
	public ResourceUtil(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}
	
	public InputStream getResourceStream(String address) throws ResourceNotFoundException{
		InputStream resourceAsStream = classLoader.getResourceAsStream(address);
		
		if (resourceAsStream != null) {
			return resourceAsStream;
		}else {
			throw new ResourceNotFoundException();
		}
	}
}
