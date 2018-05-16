package co.com.recruitment.test.hays.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import co.com.recruitment.test.hays.domain.Call;

public class CallsFactory {
	
	public static final int START_INCLUSIVE = 1;
	public static final String STANDARD_NUMBER = "123456789";
	
	
	public List<Call> startCalls(int limitExclusive) {
		//When it defines a range the superior limit is exclusive  
		return IntStream.range(START_INCLUSIVE, limitExclusive)
				.mapToObj(this::createCall)
				.collect(Collectors.toList());				        
	}
	
	private Call createCall(int index) {		
		return new Call(STANDARD_NUMBER.concat(String.valueOf(index)));		
	}

}
