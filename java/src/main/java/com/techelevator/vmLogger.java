package com.techelevator;

import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class vmLogger implements Closeable {

	private File vendingMachineLog;
	private PrintWriter vmWriter;
	
	public vmLogger (String purchaseLog) 
			throws IOException {
		this.vendingMachineLog = new File(purchaseLog);
		
//		if (this.vendingMachineLog.exists()) {
//			vmWriter = new PrintWriter(new FileWriter
//					(this.vendingMachineLog, true));
//		} else {
			vmWriter = new PrintWriter
					(this.vendingMachineLog);
		}
	//}
	
	public void write(String vmLog) {
		vmWriter.println(vmLog);
		vmWriter.flush();
	}
	
	@Override
	public void close() throws IOException {
		vmWriter.close();
	}
	
	
}
