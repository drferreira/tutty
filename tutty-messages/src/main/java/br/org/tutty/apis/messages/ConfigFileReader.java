package br.org.tutty.apis.messages;

import java.io.InputStream;
import java.util.Arrays;

import br.org.tutty.apis.messages.exceptions.ConfigFileNotFoundException;
import br.org.tutty.apis.messages.exceptions.InterfaceMessageException;
import br.org.tutty.apis.messages.models.ExceptionMessage;
import br.org.tutty.apis.messages.models.Message;
import br.org.tutty.apis.messages.models.MessagesConfigFile;
import br.org.tutty.util.exceptions.ResourceNotFoundException;
import br.org.tutty.util.jee.web.ResourceUtil;
import br.org.tutty.util.xstream.SeverityMessageConverter;
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

	private String messageConfXmlName;
	private MessagesConfigFile messagesConf;
	private InputStream inputStream;

	@SuppressWarnings("unchecked")
	public ConfigFileReader(String messageConfXmlName) {
		super(Arrays.asList(MessagesConfigFile.class, Message.class, ExceptionMessage.class), Arrays.asList(new SeverityMessageConverter()), new XStream());
		this.messageConfXmlName = messageConfXmlName;
	}
	
	public void init(ResourceUtil resourceUtil) throws ConfigFileNotFoundException {
		process();

		try {
			inputStream = resourceUtil.getResourceStream(messageConfXmlName);
			messagesConf = (MessagesConfigFile) xStream.fromXML(inputStream);

		} catch (ResourceNotFoundException e) {
			throw new ConfigFileNotFoundException();
		} 
	}

	public Message getMessage(InterfaceMessageException interfaceMessageException) {
		Class exception = interfaceMessageException.getException();
		ExceptionMessage exceptionMessage = messagesConf.getExceptionMessage(exception);
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
