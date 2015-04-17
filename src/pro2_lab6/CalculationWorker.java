package pro2_lab6;

public class CalculationWorker implements Runnable {
	private int tid;
	private GeneralResourseMonitor resMonitor;
	private SynchronizationMonitor syncMonitor;
	
	public CalculationWorker(GeneralResourseMonitor resourseMonitor,
			SynchronizationMonitor syncMonitor, int id) {
		this.resMonitor = resourseMonitor;
		this.syncMonitor = syncMonitor;
		this.tid = id;
	}

	@Override
	public void run() {
		System.out.println("Thread "+tid + " started.");
		
		switch (this.tid) {
		case 1:
			Executor.MT = CalculateUtils.inputMatrix(1);
			resMonitor.setAlfa(1);
			syncMonitor.inputSignal();
			break;
		case 3:
			Executor.C = CalculateUtils.inputVector(1);
			Executor.MO = CalculateUtils.inputMatrix(1);
			syncMonitor.inputSignal();
			break;
		case 6:
			Executor.B = CalculateUtils.inputVector(1);
			Matrix MR = CalculateUtils.inputMatrix(1);
			resMonitor.setMR(MR);
			syncMonitor.inputSignal();
			break;
		}
		
		syncMonitor.waitForInput();

		int vi = CalculateUtils.operation1(tid);
		
		resMonitor.addV(vi);
		
		syncMonitor.calcVSignal();
		
		syncMonitor.waitForCalcV();
		
		int alfai = resMonitor.copyAlfa();
		vi = resMonitor.copyV();
		Matrix MRi = resMonitor.copyMR();
		
		CalculateUtils.operation3(alfai, vi, MRi, tid);
		
		if (tid == 6){
			syncMonitor.waitForCalcMA();
			CalculateUtils.outputMatrix(Executor.MA);
		}else
			syncMonitor.calcMASignal();
		
		System.out.println("Thread "+tid + " finished.");
	}
}
