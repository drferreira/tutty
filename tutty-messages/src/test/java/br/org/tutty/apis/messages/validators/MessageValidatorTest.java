package br.org.tutty.apis.messages.validators;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import br.org.tutty.apis.messages.models.Message;

@RunWith(PowerMockRunner.class)
public class MessageValidatorTest {

	private MessageValidator messageValidator;
	
		
	@Test
	public void haveCorrectValidators(){
		messageValidator = new MessageValidator(new ArrayList<Message>());
		
		List<Validator> validators = messageValidator.getValidators();
		Assert.assertSame(DefaultMessageEmptyValidator.class ,validators.get(0).getClass());
		Assert.assertSame(MultipleDefaultMessageValidator.class ,validators.get(1).getClass());
		Assert.assertSame(IdMessageRepeatedValidator.class ,validators.get(2).getClass());
	}
}
