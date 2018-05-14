package co.com.recruitment.test.hays.util;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {
	
	
	
	private static final int ORIGIN = 5;
	private static final int BOUND = 11;	
	
	public static int getCallDuration() {
		return ThreadLocalRandom.current().nextInt(ORIGIN, BOUND);		
	}

}
