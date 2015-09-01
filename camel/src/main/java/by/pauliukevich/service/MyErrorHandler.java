package by.pauliukevich.service;

import java.util.Map;

import org.apache.camel.Body;
import org.apache.camel.Headers;
import org.apache.camel.OutHeaders;

public class MyErrorHandler {

	public Object messageFailed(@Headers Map<?, ?> in, @Body String payload, @OutHeaders Map<String, Object> out) {
		out.put("customerid", in.get("customerid"));
		out.put("orderid", "failed");
		return "Order ERROR";
	}

}
