package trabalhobd;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TableFormater {
	private String table;
	private int rowNumber;
	private int columnNumber;
	private Statement stmt;
	private String SQL;
	private String tableName;
	private ArrayList<ArrayList<String>> data;
	private ArrayList<String> columns;
	
	TableFormater(){
		table = "";
		rowNumber = 0;
		columnNumber = 0;
		columns = new ArrayList<String>();
		data = new ArrayList<ArrayList<String>>();
	}
	
	private void getColumnsNames() throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT table_name, column_name FROM USER_TAB_COLUMNS WHERE table_name = '" + tableName + "'");
		
		while(rs.next()) {
			columns.add(rs.getString(2));
			columnNumber++;
		}
            
		
		rs.close();	
	}
	
	private void getTableData() throws SQLException{
		ResultSet rs = stmt.executeQuery(SQL);
		ResultSetMetaData metadata = rs.getMetaData();
		int columnNumber = metadata.getColumnCount();
		
		for(int i = 0; i < columnNumber; i++)
			data.add(new ArrayList<String>());
		
		while(rs.next()) {
			for(int i = 1; i <= columnNumber; i++)
                data.get(i-1).add(rs.getString(i));
			rowNumber++; 
		}	
	}
	
	private int getLongestStringSize(ArrayList<String> s) {
		int max = 0;
		for(int i = 0; i < s.size(); i++) {
			if(s.get(i).length() > max) max = s.get(i).length();
		}
		return max;
	}
	
	
	private void mergeDataColumn(){
		for(int i = 0; i < columnNumber; i++) {
			data.get(i).add(0, columns.get(i));
		}
		rowNumber++;
	}
	
	public void printRawTable(ArrayList<ArrayList<String>> table) {
		for(int i = 0; i < rowNumber; i++) {
			for(int j = 0; j < columnNumber; j++) {
				System.out.print(data.get(j).get(i) + " ");				
			}
			System.out.print("\n");
		}
	}
	
	public String getFormatedTable(Statement stmt, String SQL, String tableName) throws SQLException{
		this.stmt = stmt;
		this.SQL = SQL;
		this.tableName = tableName;
		
		getTableData(); //array de array de string (matriz)
		getColumnsNames(); //array de colunas
	
		mergeDataColumn(); //bota as colunas na matriz
		
		
			
		return table;
	}
}
