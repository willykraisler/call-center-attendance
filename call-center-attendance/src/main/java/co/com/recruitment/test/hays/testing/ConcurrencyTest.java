package co.com.recruitment.test.hays.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.IntStream;

public class ConcurrencyTest {



	int  count = 0;

	void incrementSync() {
	    synchronized (this) {
	        count = count + 1;
	    }
	}
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
//		private int thread;
//		private LocalDateTime start;
//		private LocalDateTime finish;
//		
//		public DispatcherTelCalls(int thread) {
//			this.start = LocalDateTime.now(ZoneId.of("America/Bogota"));
//			this.thread = thread;
//		}
		
//		@Override
//		public void run() {
//			try {
//				int minut = ThreadLocalRandom.current().nextInt(5, 11);
//				System.out.println("it start: " + thread + " - With :" + minut + " Min" );
//				TimeUnit.MINUTES.sleep(minut);
//				finish = LocalDateTime.now(ZoneId.of("America/Bogota"));
//				System.out.println("it finish: " + thread  + " - Real Time to process : " + Duration.between(start, finish).toMillis());
//			} catch (InterruptedException e) {		
//				e.printStackTrace();
//			}
//		}


		//		Runnable task = () -> {
		//			String threadName = Thread.currentThread().getName();
		//			System.out.println("Hello " + threadName);
		//		};





		//		
		//		task.run();
		//
		//		Thread thread = new Thread(task);
		//		thread.start();
		//
		//		System.out.println("Done!");

		//		Runnable runnable = () -> {
		//		    try {
		//		        String name = Thread.currentThread().getName();
		//		        System.out.println("Foo " + name);
		//		        TimeUnit.SECONDS.sleep(1);
		//		        System.out.println("Bar " + name);
		//		    }
		//		    catch (InterruptedException e) {
		//		        e.printStackTrace();
		//		    }
		//		};
		//
		//		Thread thread = new Thread(runnable);
		//		thread.start();


		//		ExecutorService executor = Executors.newSingleThreadExecutor();
		//		executor.submit(() -> {
		//		    String threadName = Thread.currentThread().getName();
		//		    System.out.println("Hello " + threadName);
		//		});
		//		
		//		
		//		try {
		//		    System.out.println("attempt to shutdown executor");
		//		    executor.shutdown();
		//		    executor.awaitTermination(5, TimeUnit.SECONDS);
		//		}
		//		catch (InterruptedException e) {
		//		    System.err.println("tasks interrupted");
		//		}
		//		finally {
		//		    if (!executor.isTerminated()) {
		//		        System.err.println("cancel non-finished tasks");
		//		    }
		//		    executor.shutdownNow();
		//		    System.out.println("shutdown finished");
		//		}

		//		Callable<Integer> task = () -> {
		//		    try {
		//		        TimeUnit.SECONDS.sleep(1);
		//		        return 123;
		//		    }
		//		    catch (InterruptedException e) {
		//		        throw new IllegalStateException("task interrupted", e);
		//		    }
		//		};
		//		
		//		ExecutorService executor = Executors.newFixedThreadPool(1);
		//		Future<Integer> future = executor.submit(task);
		//
		//		System.out.println("future done? " + future.isDone());
		//
		//		Integer result = future.get();
		//
		//		System.out.println("future done? " + future.isDone());
		//		System.out.print("result: " + result);


		//		ExecutorService executor = Executors.newWorkStealingPool();
		//
		//		List<Callable<String>> callables = Arrays.asList(
		//		        () -> "task1",
		//		        () -> "task2",
		//		        () -> "task3");
		//
		//		executor.invokeAll(callables)
		//		    .stream()
		//		    .map(future -> {
		//		        try {
		//		            return future.get();
		//		        }
		//		        catch (Exception e) {
		//		            throw new IllegalStateException(e);
		//		        }
		//		    })
		//		    .forEach(System.out::println);
		
//		ExecutorService executor = Executors.newFixedThreadPool(2);
//
//		IntStream.range(0, 10000).forEach(i -> executor.submit(this::incrementSync));
//
//		stop(executor);
//
//		System.out.println(count);  // 10000
		
		
//		ExecutorService executor = Executors.newFixedThreadPool(5);
//		ReentrantLock lock = new ReentrantLock();
//
//		executor.submit(() -> {
//		    lock.lock();
//		    try {
//		        sleep(1);
//		    } finally {
//		        lock.unlock();
//		    }
//		});
//
//		executor.submit(() -> {
//		    System.out.println("Locked: " + lock.isLocked());
//		    System.out.println("Held by me: " + lock.isHeldByCurrentThread());
//		    boolean locked = lock.tryLock();
//		    System.out.println("Lock acquired: " + locked);
//		});
//
//		stop(executor);
		
		
		
//		ExecutorService executor = Executors.newFixedThreadPool(2);
//		Map<String, String> map = new HashMap<>();
//		ReadWriteLock lock = new ReentrantReadWriteLock();
//
//		executor.submit(() -> {
//		    lock.writeLock().lock();
//		    try {
//		        sleep(10);
//		        map.put("foo", "bar");
//		    } finally {
//		        lock.writeLock().unlock();
//		    }
//		});
//		
//		Runnable readTask = () -> {
//		    lock.readLock().lock();
//		    try {
//		        System.out.println(map.get("foo"));
//		        sleep(100);
//		    } finally {
//		        lock.readLock().unlock();
//		    }
//		};
//
//		executor.submit(readTask);
//		executor.submit(readTask);
//
//		stop(executor);
		
		
		ExecutorService executor = Executors.newFixedThreadPool(2);
		StampedLock lock = new StampedLock();

		executor.submit(() -> {
		    long stamp = lock.tryOptimisticRead();
		    try {
		        System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
		        sleep(1);
		        System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
		        sleep(2);
		        System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
		    } finally {
		        lock.unlock(stamp);
		    }
		});

		executor.submit(() -> {
		    long stamp = lock.writeLock();
		    try {
		        System.out.println("Write Lock acquired");
		        sleep(2);
		    } finally {
		        lock.unlock(stamp);
		        System.out.println("Write done");
		    }
		});

		stop(executor);





	}


	public static void stop(ExecutorService executor) {
		try {
			executor.shutdown();
			executor.awaitTermination(60, TimeUnit.SECONDS);
		}
		catch (InterruptedException e) {
			System.err.println("termination interrupted");
		}
		finally {
			if (!executor.isTerminated()) {
				System.err.println("killing non-finished tasks");
			}
			executor.shutdownNow();
		}
	}

	public static void sleep(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}

	
	
//	enum Type{
//	OPERATOR(1),
//	SUPERVISOR(2),
//	DIRECTOR(3);
//	
//	private long priority;
//
//	private Type(int priority) {
//		this.priority = priority;
//	}
//
//	public long getPriority() {
//		return priority;
//	}		
//}
}
