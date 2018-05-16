# call-center-attendance
    This the programming test in almundo.com

# System requirements

- Java  1.8
- Maven 3

# Compilation
It shows the tests with the prints to show how it works

```shell
$ cd call-center-attendance/
$ mvn clean && mvn -U install

```


## How did I resolve the problem (Optional)

I used a thread execution service to send calls to execute on demand
those callable are Employee class because they will answer the calls and They should
wait to solve the client's problem.

Dispatcher class has the ability to make someone who is not busy to attend the call
if someone is enabled to answer the call the dispatcher submit the employee with that call, 
otherwise the class Dispatcher has a queue with the calls on hold until someone can attend them.

how it shows the next test receives the calls and traverses sequentially calling until someone responds
after that, it must verify the amounts of calls between the roles to verify that the operator was the
most productive call center agent.

also, it must verify that the amount of calls are congruent withe the calls factory amount.

```java
// This a test with support more than 10 calls

    @Test
	public void greater_than_ten_calls() throws InterruptedException {
		//Greater than ten calls
		callsFactory.startCalls(TWENTY_CALLS_SIMULTANEOUSLY + EXCLUSIVE).stream().sequential()
		.filter(Objects::nonNull)
		.forEach(callUntilDontHaveQueue());

		//This a minimal delay to wait that it finish to process any call
		TimeUnit.SECONDS.sleep(DELAY_PROCESS);
		
		calculateTotalCalls();
		//this assert guarantee that the most efficient call center agent is the operator with major 
		//amount of calls processed 
		assertTrue( callsByDirector < callsBySupervisor);
		assertTrue( callsBySupervisor < callsByOperator);
		assertTrue( callsByDirector < callsByOperator);
		assertEquals(TWENTY_CALLS_SIMULTANEOUSLY, totalCalls);

	}
```
