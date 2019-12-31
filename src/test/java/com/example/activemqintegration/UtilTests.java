package com.example.activemqintegration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class UtilTests {
	@Test
	void shouldParseJsonString() throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		String jsonString = "{\"name\":\"value\"}";

		Map<String, Object> map = mapper.readValue(jsonString, Map.class);
	}
}
