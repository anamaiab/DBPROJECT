/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhobd;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


/**
 *
 * @author Marina Kako
 * @author Marcelo de Moraes
 * @author Ana Boba
 */
public class TrabalhoBD {
	/**
     * @param args the command line arguments
	 * @throws SQLException 
     */
    public static int main(String[] args) throws SQLException {
    	
    	String user = "M9763151";
    	String pass = "mk21011998*";
    	String url = "jdbc:oracle:thin:@grad.icmc.usp.br:15215:orcl";
    	Statement stmt; 
        
    	//objeto que reliza conecao
    	Connect conexao = new Connect(user, pass, url);
        stmt = conexao.makeConnection();
        
        //verifica se conexao foi estabelecida
        if(stmt != null) {
        	System.out.println("Usuario " + user + "conectado em " + url);
        }
        else {
        	System.out.println("Conexao nao pode ser estabelecida");
        	return -1;
        }
         
        //MENU
        Scanner ler = new Scanner(System.in);  
        System.out.println("Bem vindo ao seu gerenciador de eventos");
        while(true) {
             System.out.println("Escolha um dos eventos: \n"
             		+ "1-Festa\n"
             		+ "2-Confraternizacao");
             
             int evento = ler.nextInt();
         
             if(evento == 1){
            	 int a = 1;
            	 //loop na secao Festa
            	 while(a == 1) {
            		 System.out.println("Menu Festas\n");
            		 System.out.println("Escolha uma das opcoes abaixo:\n"
                      		+ "1-Listar tabelas\n"
                      		+ "2-Consultar\n"
                      	    + "3-Voltar\n"
                      		+ "4-Sair");
                      
                      int op = ler.nextInt();
                      
                      if(op == 1){
                          //listar tabelas
                      }else if(op == 2){
                          //consultar
                      }else if(op == 3){
                    	  a = 0;
                      }else{
                          //sair
                          System.out.println("Saindo...");
                          conexao.close();
                          return 0;
                      }
            	 }
                 
             }
             else if(evento == 2){
            	 int a = 2;
            	 //Loop na secao Confraternizacao
            	 while(a == 2) {
            		 System.out.println("Menu Confraternizacao\n");
            		 System.out.println("Escolha uma das opcoes abaixo:\n"
		                 		+ "1-Listar tabelas\n"
		                 		+ "2-Consultar\n"
		                 		+ "3-Inserir tupla\n"
		                 		+ "4-Alterar tupla\n"
		                 		+ "5-Remover tupla\n"
		                 		+ "6-Voltar\n"
		                 		+ "7-Sair");
			          
			          int op = ler.nextInt();
			          
			          switch(op){
			              case 1: //apenas um teste, por isso numa classe dps
			            	  ResultSet rs = stmt.executeQuery("SELECT owner, table_name FROM dba_tables");
			            	  ResultSetMetaData rsmd = rs.getMetaData();
			            	  int columnsNumber = rsmd.getColumnCount();
			            	  while (rs.next()) {
			            		    for(int i = 1; i < columnsNumber; i++)
			            		        System.out.print(rs.getString(i) + " ");
			            		    System.out.println();
			            		}
			            	  rs.close();
			                  break;
			              case 2:
			                  //consultar
			                  break;
			              case 3:
			                  //inserir tupla
			                  break;
			              case 4:
			                  //alterar tupla
			                  break;
			              case 5:
			                  //remover tupla
			                  break;
			              case 6:
			            	  a = 0;
			              case 7:
			                  //sair
			                  System.out.println("Saindo...");
			                  conexao.close();
			                  return 0;
			          }
            	 }
                 
             }
        }
    }
}
