package pro2_lab6;

/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                           *
 *                 Laboratory work #6. Java. Monitors                        *
 *                                                                           *
 * Task: MA = (B*C)*MO + α*(MT*MR)                                           *
 *                                                                           *
 * @file CalculationWorker.java 					     *
 * @author Kuzmenko Volodymyr					             *
 * @group IO-21								     *
 * @date 17.04.2015                                                          *
 *                                                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

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
			//1. Якщо tid = 1, Введення MT, α
			Executor.MT = CalculateUtils.inputMatrix(1);
			resMonitor.setAlfa(1);
			//2. Якщо tid = 1, Сигнал про завершення вводу.
			syncMonitor.inputSignal();
			break;
		case 3:
			//3. Якщо tid = 3, Введення С, MO
			Executor.C = CalculateUtils.inputVector(1);
			Executor.MO = CalculateUtils.inputMatrix(1);
			//4. Якщо tid = 2, Сигнал про завершення вводу.
			syncMonitor.inputSignal();
			break;
		case 6:
			//5. Якщо tid = 6, Введення B, MR
			Executor.B = CalculateUtils.inputVector(1);
			Matrix MR = CalculateUtils.inputMatrix(1);
			resMonitor.setMR(MR);
			//6. Якщо tid = 6, Сигнал про завершення вводу.
			syncMonitor.inputSignal();
			break;
		}
		//7. Чекати завершення вводу в інших задачах
		syncMonitor.waitForInput();
		//8. Обчислення 1: vi = BH∙CH
		int vi = CalculateUtils.operation1(tid);
		//9. Обчислення 2: v = v + vi
		resMonitor.addV(vi);
		//10. Сигнал про завершення обчислення 2.
		syncMonitor.calcVSignal();
		//11. Чекати завершення обчислення 2 в інших задачах
		syncMonitor.waitForCalcV();
		//12. Копіювати αi := α, vi = v, MRi = MR
		int alfai = resMonitor.copyAlfa();
		vi = resMonitor.copyV();
		Matrix MRi = resMonitor.copyMR();
		//13. Обчислення 3: MAH = vi∙MOH + αi∙(MTH∙MRi) 
		CalculateUtils.operation3(alfai, vi, MRi, tid);
		
		if (tid == 6){
			//15. Якщо tid = 6, Чекати на завершення обчислення 3 в інших задачах
			//16. Якщо tid = 6, Вивести MA
			syncMonitor.waitForCalcMA();
			CalculateUtils.outputMatrix(Executor.MA);
		}else{
			//14. Якщо tid !=6, Сигнал про завершення обчислення 3
			syncMonitor.calcMASignal();			
		}
		System.out.println("Thread "+tid + " finished.");
	}
}
