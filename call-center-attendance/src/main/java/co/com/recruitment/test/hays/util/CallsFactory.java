package co.com.recruitment.test.hays.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import co.com.recruitment.test.hays.domain.Call;

public class CallsFactory {
	
	public static final int START_INCLUSIVE = 1;

	public static final String STANDARD_NUMBER = "123456789";
	
	
	public List<Call> startCalls(int limit) {
		 return IntStream.range(START_INCLUSIVE, limit)
				.mapToObj(this::createCall)
				.collect(Collectors.toList());				        
	}
	
	private Call createCall(int c) {		
		Call call = new Call();		
		call.setNumber(STANDARD_NUMBER.concat(String.valueOf(c)));		
		return call;
	}

}
