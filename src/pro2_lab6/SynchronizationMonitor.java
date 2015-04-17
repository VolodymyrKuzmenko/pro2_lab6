package pro2_lab6;
/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                           *
 *                 Laboratory work #6. Java. Monitors                        *
 *                                                                           *
 * Task: MA = (B*C)*MO + α*(MT*MR)                                           *
 *                                                                           *
 * @file SynchronizationMonitor.java 				     				     *
 * @author Kuzmenko Volodymyr					                             *
 * @group IO-21								                                 *
 * @date 17.04.2014                                                          *
 *                                                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
public class SynchronizationMonitor {
	private int inputFlag = 0;
	private int vFlag = 0;
	private int MaFlag = 0;

	public synchronized void inputSignal() {
		inputFlag++;
	
		if (inputFlag >= 3) {
			notifyAll();
			
		}
	}

	public synchronized void calcVSignal() {
		vFlag++;
		if (vFlag >= Executor.P)
			notifyAll();
	}

	public synchronized void calcMASignal() {
		MaFlag++;
		if (MaFlag == Executor.P-1)
			notifyAll();
	}

	public synchronized void waitForInput() {
		try {
			if (inputFlag<3) wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void waitForCalcV() {
		try {
			if (vFlag<Executor.P)
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void waitForCalcMA() {
		try {
			if (MaFlag<Executor.P-1)
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
