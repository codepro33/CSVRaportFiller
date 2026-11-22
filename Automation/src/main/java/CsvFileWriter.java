import java.io.FileWriter;
import java.io.IOException;

public class CsvFileWriter {
	
String fileNameCSVmBank = "C:\\Users\\abdul\\OneDrive\\Documents\\FilesForProject\\data//payoutsmBank.csv";
	

public void writeToCSVmBank(
String executionDate,
Double amount,
int senderBankNum,
int senderIban,
int receiverIban,
String senderData,
String receiverData,
int receiverBankNum,
String title           ){
	
	String amountFilter = amount.toString();
	amountFilter.replace(",", "");
	int amountmBank = Integer.parseInt(amountFilter);
	
	try {
		FileWriter writer = new FileWriter(fileNameCSVmBank);
		writer.append(
				110+","+
		        executionDate+","+
				amountmBank+","+
		        senderBankNum+","+
				0+","+
		        senderIban+","+
				receiverIban+","+
		        senderData+","+
				receiverData+","+
		        0+","+
				receiverBankNum+","+
		        title+","+
				""+","+
		        ""+","+
				51+","+
		        ""    );
		
		writer.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
}

}
