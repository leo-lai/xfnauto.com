package main.com.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class MoneySerializerOut extends JsonSerializer<Double> {

	@SuppressWarnings("null")
	@Override
	public void serialize(Double money, JsonGenerator jsonGenerator,
			SerializerProvider arg2) throws IOException,
			JsonProcessingException {
		jsonGenerator.writeNumber(money!=null?null:money);
		
	}

}
