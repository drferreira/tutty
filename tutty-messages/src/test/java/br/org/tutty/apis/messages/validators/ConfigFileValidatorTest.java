package br.org.tutty.apis.messages.validators;


import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import br.org.tutty.apis.messages.models.MessagesConfigFile;

@RunWith(PowerMockRunner.class)
public class ConfigFileValidatorTest {

	private ConfigFileValidator configFileValidator;
	private List<Validator> validators;
	
	@Mock
	private MessageExceptionValidator exceptionValidator;
	
	@Test
	public void shouldBeAddSpecificValidators(){
		configFileValidator = new ConfigFileValidator(new MessagesConfigFile());
		List<Validator> validators = configFileValidator.getValidators();
		
		Assert.assertFalse(configFileValidator.getValidators().isEmpty());
		Assert.assertEquals(1, validators.size());
		Assert.assertSame(validators.get(0).getClass(), MessageExceptionValidator.class);
	}
	
	@Test
	public void shouldBeCallValidateMethods(){
		validators = new ArrayList<Validator>();
		validators.add(exceptionValidator);
		
		configFileValidator = new ConfigFileValidator(new MessagesConfigFile());
		configFileValidator.setValidators(validators);
		
		configFileValidator.validate();
		
		Mockito.verify(exceptionValidator).validate();
	}
}
