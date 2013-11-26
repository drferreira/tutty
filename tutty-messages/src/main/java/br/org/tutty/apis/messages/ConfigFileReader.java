package br.org.tutty.apis.messages;

import java.io.InputStream;
import java.util.Arrays;

import javax.print.attribute.standard.Severity;

import br.org.tutty.apis.messages.exceptions.ConfigFileNotFoundException;
import br.org.tutty.apis.messages.exceptions.InterfaceMessageException;
import br.org.tutty.apis.messages.models.ExceptionMessage;
import br.org.tutty.apis.messages.models.Message;
import br.org.tutty.apis.messages.models.MessagesConfigFile;
import br.org.tutty.util.exceptions.ResourceNotFoundException;
import br.org.tutty.util.jee.web.ResourceUtil;
import br.org.tutty.util.xstream.GenericXStreamEnumConverter;
import br.org.tutty.util.xstream.XStreamProcessorBuilder;

import com.thoughtworks.xstream.XStream;


/**
 * Classe responsavel por realizar a leitura do arquivo de configuração <b>
 * "MESSAGE-CONF.xml" </b>. Necessita que o arquivo esteja disponivel dentro da
 * raiz da pasta de resources maven <b> (/src/main/resources) </b>. <br> Extende
 * {@link XStreamProcessorBuilder} para inicializar Converters e anotações
 * utilizadas pelo XStream.
 * 
 * @author drferreira
 * 
 */
@SuppressWarnings("rawtypes")
public class ConfigFileReader extends XStreamProcessorBuilder {

	public static final String MESSAGE_CONF_XML_NAME = "message-conf.xml";

	private MessagesConfigFile messagesConf;
	private InputStream inputStream;

	@SuppressWarnings("unchecked")
	public ConfigFileReader() {
		super(Arrays.asList(Message.class, ExceptionMessage.class), Arrays.asList(new GenericXStreamEnumConverter(Severity.class)), new XStream());
	}
	
	public void init(ResourceUtil resourceUtil) throws ConfigFileNotFoundException {
		process();

		try {
			inputStream = resourceUtil.getResourceStream(MESSAGE_CONF_XML_NAME);
			messagesConf = (MessagesConfigFile) xStream.fromXML(inputStream);

		} catch (ResourceNotFoundException e) {
			throw new ConfigFileNotFoundException();
		} 
	}

	public Message getMessage(InterfaceMessageException interfaceMessageException) {
		ExceptionMessage exceptionMessage = messagesConf.getExceptionMessage(interfaceMessageException.getException());
		String idMessage = interfaceMessageException.getIdMessage();

		if (idMessage == null) {
			return exceptionMessage.getDefaultMessage();
		} else {
			return exceptionMessage.getMessage(idMessage);
		}
	}

	public MessagesConfigFile getMessagesConf() {
		return messagesConf;
	}
}
