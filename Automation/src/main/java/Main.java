import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;

public class Main {

	public static void main(String[] args) throws IOException{
	
		System.out.println("Program started");
		
		GUI gui = new GUI();
		
		
		ArrayList<String> IDBolt = new ArrayList<String>();
		ArrayList<Double> payoutsBolt = new ArrayList<Double>();
		ArrayList<String> namesBolt = new ArrayList<String>();  
		
		
		ArrayList<String> IDUber = new ArrayList<String>();
		ArrayList<Double> payoutsUber = new ArrayList<Double>();
		ArrayList<String> namesUber = new ArrayList<String>();
		
		String uberType = "uber";
		String boltType = "bolt";
		
		Double totalPayout=0.0;
		
		//Getting files that i need 
		
		while(gui.close==false) {
			   try {
				   
				TimeUnit.SECONDS.sleep(1);
			   } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			   }
		}
		
		String formula = gui.formula;
		String bolt = gui.bolt;
		String uber = gui.uber;
		
		//Here i am creating workbook and sheets to get values from excel files
	    FileInputStream excelFile = new FileInputStream(formula);
		XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		getData(boltType,bolt,IDBolt,payoutsBolt,namesBolt);
		getData(uberType,uber,IDUber,payoutsUber,namesUber);
		
		System.out.println("Total Drivers For Bolt:"+IDBolt.size());
		System.out.println("Total Drivers For Uber:"+IDUber.size());
		
		for(int i =0; i<payoutsBolt.size();i++) {
			totalPayout+=payoutsBolt.get(i);
		}
		System.out.println("Total Payout For Bolt:"+totalPayout);
		//-------------------------------------------------------
				totalPayout=0.0;
		//-------------------------------------------------------
		for(int i =0; i<payoutsUber.size();i++) {
			totalPayout+=payoutsUber.get(i);
		}
		System.out.println("Total Payout For Uber:"+totalPayout);		
				
				
		fillData(boltType,sheet,IDBolt,payoutsBolt,namesBolt);
		fillData(uberType,sheet,IDUber,payoutsUber,namesUber);

		
		excelFile.close();
		workbook.setForceFormulaRecalculation(true);
		FileOutputStream fos = new FileOutputStream(formula);
		workbook.write(fos);
		fos.close();
		workbook.close();
		
		
	}
	
	public static String clean(String s) {
		return s.replace("\u00A0", " ")
				.trim()
				.replaceAll("\\s+", " ")
				.toLowerCase();
	}
	
	public static void getData(String type,String file,ArrayList<String> ID,ArrayList<Double> payouts,ArrayList<String> names) {
		
		String line;
		
       try (BufferedReader br = new BufferedReader(new FileReader(file))){
			
	
			//Here i am skipping header line
			br.readLine();
			
			//Here i am checking if there is next line
			while((line = br.readLine()) != null) {
				
				//Here i am adding values separated by comma to the String array
				String[] values = line.split(",");
				
				if(type.equals("bolt")) {
				
				String driverID = values[24].replace("\"","").trim();		
				ID.add(driverID);
				
				String name = values[0].replace("\"","").trim();
				names.add(name);
				
				String makeClear = values[18].replace("\"","");
				Double makeDouble = Double.parseDouble(makeClear);
				payouts.add(makeDouble);
			   }
			
				else if(type.equals("uber")) {
					
					String driverID = values[0].replace("\"","").trim();
					
					String name = values[1].replace("\"","").trim()+" "+values[2].replace("\"","").trim();
					names.add(name);
					
					String makeClear1 = values[3].replace("\"","");
					Double makeDouble1 = Double.parseDouble(makeClear1);
					
					if(!driverID.equals("1c27fe4c-27da-4d44-b04b-202f87c4e759")) {
					ID.add(driverID);	
					payouts.add(makeDouble1);
					}
					
				}
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
	}
	
	public static void fillData(String type,XSSFSheet sheet,ArrayList<String> ID,ArrayList<Double> payouts,ArrayList<String> names) {
		
		int rows = sheet.getLastRowNum();
		int cells = sheet.getRow(0).getLastCellNum();
		
		
		//Here is the nested loops for going through every cell
		for(int r = 0; r<=rows;r++) {
			XSSFRow row = sheet.getRow(r);
			XSSFCell nameCell= row.getCell(1);
			XSSFCell surnameCell= row.getCell(2);
			XSSFCell payoutCellBolt = row.getCell(3);
			XSSFCell payoutCellUber = row.getCell(4);
			XSSFCell IDBolt = row.getCell(12);
			XSSFCell IDUber = row.getCell(13); 
			
			
			for(int i=0;i<ID.size();i++) {	
			
				    	if(type.equals("bolt")) {
				    		
				    		if(IDBolt!=null && IDBolt.getStringCellValue().equals(ID.get(i))) {	
				    		payoutCellBolt.setCellValue(payouts.get(i));
				    		payouts.remove(i);
				    		ID.remove(i);
				    		break;
				    		}
				    		
				    	}else if(type.equals("uber")) {
				    		
				    		if(IDUber!=null && IDUber.getStringCellValue().equals(ID.get(i))) {
				    		payoutCellUber.setCellValue(payouts.get(i));
				    		payouts.remove(i);
				    		ID.remove(i);
				    		break;
				    		}
				   	  }   	
			}
			
		}
		if(type.equals("bolt") && ID.size()>0) {
			System.out.println("Matching ID's in Bolt wasnt found for ID's: "+ID);
			
		}else if(type.equals("uber") && ID.size()>0) {
			System.out.println("Matching ID's in Uber wasnt found for ID's: "+ID);
			
		}

	}
}
