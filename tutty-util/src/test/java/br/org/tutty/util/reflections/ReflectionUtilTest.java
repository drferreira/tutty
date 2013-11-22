package br.org.tutty.util.reflections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.org.tutty.util.exceptions.ReflectionException;
import br.org.tutty.util.reflections.ReflectionUtil;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = ReflectionUtil.class)
public class ReflectionUtilTest {

	private ReflectionUtil reflectionUtil;

	@Before
	public void setUp(){
		reflectionUtil = new ReflectionUtil();
	}
	
	@Test
	public void shouldBeReturnThisClass(){
		Assert.assertEquals(reflectionUtil.whoMadeTheLastCall().getName(), ReflectionUtil.class.getName());
	}
	
	@Test
	public void shouldBeReturnClassloaderThisClass() throws ClassNotFoundException{
		ClassLoader classLoader = reflectionUtil.getClassloaderOfLastCall();
		Assert.assertNotNull(classLoader);
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = ReflectionException.class)
	public void shouldBeThrowReflectionProblem() throws ClassNotFoundException{
		PowerMockito.mockStatic(Class.class);
		
		Mockito.when(Class.forName("br.org.tutty.util.reflections.ReflectionUtil")).thenThrow(ReflectionException.class);
		
		reflectionUtil.whoMadeTheLastCall();
	}
}
