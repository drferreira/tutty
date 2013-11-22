package br.org.tutty.util.xstream;

import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.SingleValueConverter;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class XStreamProcessorBuilder {

	private List<SingleValueConverter> converters;
	private List<Class> annotations;
	protected XStream xStream;

	public XStreamProcessorBuilder(List annotations, List converters, XStream xStream) {
		this.xStream = xStream;

		this.annotations = annotations;
		this.converters = converters;
	}

	public void process() {
		processAnnotations();
		registerConverters();
	}

	private void processAnnotations() {
		for (Class processed : annotations) {
			xStream.processAnnotations(processed);
		}
	}

	private void registerConverters() {
		for (SingleValueConverter converter : converters) {
			xStream.registerConverter(converter);
		}
	}
}
