package br.org.tutty.util.xstream;

import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

public class GenericXStreamEnumConverter extends AbstractSingleValueConverter {

	@SuppressWarnings("rawtypes")
	private final Class enumType;
	
	@SuppressWarnings("rawtypes")
	public GenericXStreamEnumConverter(Class enumType) {
		this.enumType = enumType;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean canConvert(Class c) {
		return c.equals(enumType);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object fromString(String c) {
		return Enum.valueOf(enumType, c);
	}
}
