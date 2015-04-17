package pro2_lab6;
/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                           *
 *                 Laboratory work #6. Java. Monitors                        *
 *                                                                           *
 * Task: MA = (B*C)*MO + α*(MT*MR)                                           *
 *                                                                           *
 * @file GeneralResourseMonitor.java 							     	     *
 * @author Kuzmenko Volodymyr					                             *
 * @group IO-21								                                 *
 * @date 17.04.2015                                                          *
 *                                                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
public class GeneralResourseMonitor {
	private Matrix MR;
	private int v = 0;
	private int alfa;
	
	public synchronized void setAlfa(int value){
		this.alfa = value;
	}
	
	public synchronized void addV(int value){
		this.v += value;
	}
	
	public synchronized void setMR(Matrix value){
		this.MR = value;
	}
	
	public synchronized int copyAlfa(){
		return this.alfa;
	}
	
	public synchronized int copyV(){
		return this.v;
	}
	
	public synchronized Matrix copyMR(){
		return this.MR;
	}
}
