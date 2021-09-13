package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import model.InfrastructureDepartment;

public class Main {

	private Scanner reader;
	private InfrastructureDepartment inDep;
	
	
	public Main() throws IOException
	{
		reader = new Scanner(System.in);
		inDep = new InfrastructureDepartment();
		inDep.importData();
	}
	
	
	public static void main(String args[]) throws IOException, ClassNotFoundException
	{
		System.out.println("The App is initializing...");
		
		Main main = new Main();
		
		int menuOp = 0;

		do
		{
			menuOp = main.showMenu();
			main.executeOptions(menuOp);

		}while( menuOp != 4 );
				
	}//Main method ends.
	
	public int showMenu() throws IOException 
	{
		int menuOp = 0;

		String menu = 
			"WELCOME TO THE BILLBOARD CENSUS MANAGER\n" +
			"Pick an option \n" +
			"(1) Add Billboard\n" +
			"(2) Show Billboards\n"+
			"(3) Export and show dangerous billboard report\n" +								
			"(4) Exit";
		
		System.out.println(menu);
		menuOp = reader.nextInt();
		
		return menuOp;

	}//Method ends
	
	public void executeOptions( int Option ) throws IOException, ClassNotFoundException
	{
		switch( Option ) 
		{

			case 1:
				System.out.println (addNewBillboard() );
				break;
			case 2:
				showBillboards();
				break;
	
			case 3:
				exportDangerousReport();
				break;
		
			case 4:
				System.out.println("Bye");
				break;
			default:
				System.out.println("Error, invalid option");
				
		}//Switch ends
		
	}//Method ends
	
	public String addNewBillboard() throws IOException, ClassNotFoundException
	{
		String addConfirm = "";
		String billboardInfo = ""; 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		System.out.println("Type the billboard's info separating each camp by \"++\" " +
		"Example: 300 <width> ++ 400 <height> ++ true <is in Use> ++ Caracol <brand>" );
			
		billboardInfo = br.readLine();
		//br.close();		
		if(billboardInfo != null )
		{
			String [] info = billboardInfo.split("\\+\\+");
			
			
			//System.out.println(Arrays.toString(info));
				
			inDep.addBillboard(Double.parseDouble(info[0]), Double.parseDouble(info[1]), Boolean.parseBoolean(info[2]), info[3]);
			inDep.exportData();			
			addConfirm = "THE BILLBOARD WAS SUCCESFULLY ADDED";
			inDep.loadBillboard();
			inDep.saveBillboards();
		}	
			
		return addConfirm;
	}
	
	public void showBillboards() throws IOException
	{
		//inDep.importData();
		System.out.println("\t\t\t_________________");
		System.out.println("\t\t\tBILLBOARDS");
		System.out.println("\t\t\t_________________");
		
		
		System.out.println("\tW"+ "\tH" +"\tinUse" +"\tBrand");
        for (int i = 0; i <= inDep.getBillboards().size()-1; i++) 
        {
        	System.out.print( "\t"+ inDep.getBillboards().get(i).getWidth());
        	System.out.print("\t"+ inDep.getBillboards().get(i).getHeight());
        	System.out.print("\t"+inDep.getBillboards().get(i).isInUse());
        	System.out.println("\t"+inDep.getBillboards().get(i).getBrand());
        	
        }
		
        System.out.println("\n\tTOTAL " + inDep.getBillboards().size() + " billboards");
	}
	
	public void exportDangerousReport() throws IOException
	{
		inDep.exportDangerousBillboardReport();
		int count = 0;
		System.out.println("\t\t\t===========================");
		System.out.println("\t\t\tDANGEROUS BILLBOARD REPORT");
		System.out.println("\t\t\t===========================");
		
		for(int i = 0; i < inDep.getDangerousBillboards().size(); i ++)
		{ 
			count++;
	    	System.out.println( "\t"+count+ " Billboard " +inDep.getDangerousBillboards().get(i).getBrand()+" with area " + 
	    	inDep.getBillboards().get(i).calculateArea(inDep.getBillboards().get(i).getWidth(), inDep.getBillboards().get(i).getHeight()));

		}

		
	}

}
