package pro2_lab6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                           *
 *                 Laboratory work #6. Java. Monitors                        *
 *                                                                           *
 * Task: MA = (B*C)*MO + α*(MT*MR)                                           *
 *                                                                           *
 * @file Executor.java 							     *
 * @author Kuzmenko Volodymyr					             *
 * @group IO-21								     *
 * @date 17.04.2015                                                          *
 *                                                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
public class Executor {
	public static int N = 12;
	public static int P = 6;
	public static int H = N / P;

	public static Matrix MA = new Matrix(N);
	public static Matrix MO = new Matrix(N);
	public static Matrix MT = new Matrix(N);
	public static Vector B = new Vector(N);
	public static Vector C = new Vector(N);
	
	public static void main(String[] args) {
		System.out.println("Lab 6 started");
		ExecutorService service = Executors.newFixedThreadPool(P);
		GeneralResourseMonitor resourseMonitor = new GeneralResourseMonitor();
		SynchronizationMonitor syncMonitor = new SynchronizationMonitor();
		
		
		
		
		for (int id = 1; id <= P; id++) {
			service.execute(new CalculationWorker(resourseMonitor, syncMonitor, id));
		}
		
		service.shutdown();
		System.out.println("Lab 6 finished");
	}

}
