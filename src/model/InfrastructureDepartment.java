package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class InfrastructureDepartment {
	
	//public static final String BILLBOARD_FILE_NAME = "data/billboard.bbd";
	private String FILE_EXPORT_DANGEORUS_TXT_PATH = "data/report.txt";
	private String FILE_IMPORT_BILLBOARDS_CSV_PATH = "data/BillboardDataExported.csv";
	private String FILE_SAVE_PATH = "data/serializedBillboards";
	private List<Billboard> billboards;

	
	//Builder
	public InfrastructureDepartment() {
		
		billboards = new ArrayList <Billboard>();
		
		
	}
	
	//Methods
	
	public void addBillboard( double w, double h, boolean iu, String b)
	{
		billboards.add(new Billboard(w,h,iu,b));
	}
	
	public void saveBillboards() throws FileNotFoundException, IOException
	{
		ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream(FILE_SAVE_PATH));
		oos.writeObject(billboards);
		oos.close();
	}

	@SuppressWarnings("unchecked")
	public void loadBillboard() throws ClassNotFoundException, IOException
	{
		File f = new File(FILE_SAVE_PATH);
		if( f.exists())
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			billboards = (List<Billboard>) ois.readObject();
			ois.close();
		}
	}
	
	public void exportDangerousBillboardReport() throws IOException
	{
		FileWriter fw = new FileWriter( FILE_EXPORT_DANGEORUS_TXT_PATH, false );

		
		for( int i = 0; i < billboards.size(); i++ )
		{
			Double area = billboards.get(i).calculateArea(billboards.get(i).getWidth(),billboards.get(i).getHeight());
		
			if( area >= 160 )
			{
				Billboard dangerousBillboards = billboards.get(i);
				fw.write(dangerousBillboards.toString() +"\n");
			}
		}
		fw.close();
	}
	
	public void importData() throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(FILE_IMPORT_BILLBOARDS_CSV_PATH) );
		String line = br.readLine();
		
		while( line != null )
		{
			String [] info = line.split("|");
			addBillboard(Double.parseDouble(info[0]), Double.parseDouble(info[1]), Boolean.parseBoolean(info[2]), info[3]);
			
			line = br.readLine();
		}	
		br.close();
	}
		
	
}
