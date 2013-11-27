package br.org.tutty.util.jee.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
	
	@Test
	public void printInputStream() throws IOException{
		resourceUtil = new ResourceUtil(this.getClass().getClassLoader());
		InputStream inputStream = resourceUtil.getResourceStream("4test.xml");
	
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		
		String readLine;
		
		while (((readLine = bufferedReader.readLine()) != null)) {
			System.out.println(readLine);
		}
	}
}
