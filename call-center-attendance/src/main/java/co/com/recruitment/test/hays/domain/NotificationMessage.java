package co.com.recruitment.test.hays.domain;

import co.com.recruitment.test.hays.concurrency.interfaces.INotificationService;

public class NotificationMessage implements INotificationService {

	private final static String messageStructure = 
			String.valueOf("================================================\n"
					+ "Employee: [EMPLOYEE_NAME] - Total calls answered : [TOTAL] \n"
					+ "Client: [CLIENT_NAME] \n"
					+ "Call Duration: [DURATION_NAME] \n"
					+ "================================================ \n");

	@Override
	public void print(CallSummary callSummary) {

		//String message = String.valueOf(messageStructure);
		
		messageStructure.replace("[EMPLOYEE_NAME]", callSummary.getEmployeeName());
		messageStructure.replace("[TOTAL]", callSummary.getTotalCall());
		messageStructure.replace("[CLIENT_NAME]", callSummary.getClientName());
		messageStructure.replace("[DURATION_NAME]", callSummary.getTimeConsume());		
		
		System.out.println(messageStructure);


	}

}
