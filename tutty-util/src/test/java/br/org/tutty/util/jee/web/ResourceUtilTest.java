package br.org.tutty.util.jee.web;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import br.org.tutty.util.exceptions.ResourceNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class ResourceUtilTest {

	private static String PROPERTY_NAME = "file_test.properties";
	private static String INCORRECT_PROPERTY_NAME = "incorrect_file_test.properties";
	private ResourceUtil resourceUtil;
	
	@Test
	public void shouldBeReturnStreamPropertyFileTest(){
		resourceUtil = new ResourceUtil(this.getClass().getClassLoader());
		InputStream inputStream = resourceUtil.getResourceStream(PROPERTY_NAME);
		Assert.assertNotNull(inputStream);
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void shouldBeThrowResourceNotFoundWhenIncorrectNameFile(){
		resourceUtil = new ResourceUtil(this.getClass().getClassLoader());
		resourceUtil.getResourceStream(INCORRECT_PROPERTY_NAME);
	}
}
