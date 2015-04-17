package pro2_lab6;

public class SynchronizationMonitor {
	private int inputFlag = 0;
	private int vFlag = 0;
	private int MaFlag = 0;

	public synchronized void inputSignal() {
		inputFlag++;
		if (inputFlag == 3)
			notifyAll();
	}

	public synchronized void calcVSignal() {
		vFlag++;
		if (vFlag == Executor.P)
			notifyAll();
	}

	public synchronized void calcMASignal() {
		MaFlag++;
		if (MaFlag == Executor.P-1)
			notifyAll();
	}

	public synchronized void waitForInput() {
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void waitForCalcV() {
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void waitForCalcMA() {
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
