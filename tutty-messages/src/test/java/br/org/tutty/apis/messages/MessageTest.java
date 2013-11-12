package br.org.tutty.apis.messages;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.org.tutty.apis.messages.models.Message;

public class MessageTest {
	
	@Test
	public void messageIsDefaultShouldBeReturnTruWhenIDIsEqualNull(){
		Message message = new Message();
		message.setIdMessage(null);
		
		assertTrue(message.isDefault());
	}

	@Test
	public void messageIsDefaultShouldBeReturnTrueWhenIDIsEmpty(){
		Message message = new Message();
		message.setIdMessage("");
		
		assertEquals(true, message.isDefault());
	}

	@Test
	public void messageIsDefaultShouldBeReturnFalseWhenIDNotNullOrEmpty(){
		Message message = new Message();
		message.setIdMessage("ID");
		
		assertEquals(false, message.isDefault());
	}
}
