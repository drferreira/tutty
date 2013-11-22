package br.org.tutty.util.reflections;

import br.org.tutty.util.exceptions.ReflectionException;

public class ReflectionUtil {
	private static final int LAST_CALL_POSITION = 1;
	
	@SuppressWarnings("rawtypes" )
	public Class whoMadeTheLastCall(){
		String className = Thread.currentThread().getStackTrace()[LAST_CALL_POSITION].getClassName();
		try {
			return Class.forName(className);
		
		} catch (ClassNotFoundException e) {
			throw new ReflectionException();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public ClassLoader getClassloaderOfLastCall(){
		Class whoMadeTheLastCall = whoMadeTheLastCall();
		return whoMadeTheLastCall.getClassLoader();
	}
}
