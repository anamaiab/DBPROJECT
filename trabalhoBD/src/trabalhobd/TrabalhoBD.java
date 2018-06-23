/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhobd;

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
	
    public static void printTable(ResultSet rs) throws SQLException {;
     	ResultSetMetaData meta = rs.getMetaData();
        int columnCount = meta.getColumnCount();
        
        for(int i = 1; i <= columnCount; i++) {
        	System.out.print(String.format("%-25s", "| " + meta.getColumnName(i)));
        }
        System.out.println("\n");
        
        while(rs.next()){
            for(int i = 1; i <= columnCount; i++)
                System.out.print(String.format("%-25s", "| " + rs.getString(i)));
            System.out.println("");
        }
        System.out.println("");      
	}
	
    public static void main(String[] args) throws SQLException {
    	
    	String user = "M9763151";
    	String pass = "123456";
    	String url = "jdbc:oracle:thin:@grad.icmc.usp.br:15215:orcl";
    	Statement stmt; 
        
    	//objeto que reliza conecao
    	Connect conexao = new Connect(user, pass, url);
        stmt = conexao.makeConnection();
        
        //verifica se conexao foi estabelecida
        if(stmt != null) {
        	System.out.println("Usuario " + user + " conectado em " + url + "\n");
        }
        else {
        	System.out.println("Conexao nao pode ser estabelecida.");
        	return;
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
                          System.out.println("FESTA\nREPUBLICA\nPROPRIETARIOREP\nUNIVERSITARIO\nMOTORISTA\nTRANSPORTE\nCONDUCAO\nTRAJETO\n"
                                  + "SEGURANCA\nSEGURATRABALHA\nDJ\nDJPERFORMA\nFOODTRUCK\nCOMIDASFT\nPROPRIETARIOFT\nBEBIDA\nBEBIDASERVIDA\nPULSEIRA\n");
                          
                      }else if(op == 2){
                          //consultar
                          System.out.println("Digite o nome da tabela que deseja consultar:");
                          String tabelaC = ler.next();
                          tabelaC = tabelaC.toUpperCase();
                          String comandoC = "SELECT * FROM " + tabelaC;
                          System.out.println("Caso deseje restringir a consulta por alguma condicao, "
                                  + "digite-a no formato ''coluna = condicao'' (sem aspas duplas, com aspas simples na condicao caso nao seja um numero)."
                                  + "\nCaso contrario, digite '*'");
                          
                          String enter = ler.nextLine();
                          String condicaoC = ler.nextLine();
                          
                          if(!condicaoC.equals("*")) comandoC += " WHERE " + condicaoC;
                          comandoC = comandoC.toUpperCase();
                          
                          try{
                              ResultSet res = stmt.executeQuery(comandoC);
                              printTable(res);
                          }catch(SQLException sqle){
                              System.out.println("\nErro ao consultar\n");
                          }
                                                  
                          
                      }else if(op == 3){
                    	  a = 0;
                      }else{
                          //sair
                          conexao.close();
                          ler.close();
                          System.out.println("Sessao finalizada");
                          return;
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
			     case 1: //listar todas as tabelas
			  	  //TODO Listar apenas tabelas pertinentes ao escopo de confraternizacao
			     	  ResultSet rs = stmt.executeQuery("SELECT table_name, owner FROM all_tables WHERE owner= '" + user + "' ORDER BY owner, table_name");			            	      	  
                                  while (rs.next()) {
                                        System.out.print(rs.getString(1) + " ");
			                System.out.println();
			          }
			          System.out.println();

			          rs.close();
			          break;
                                          
			     case 2:
			         //consultar
                                 System.out.println("1-Consultas gerais\n"
                                           + "2-Consultas avançacadas");
                                 int c = ler.nextInt();
                                          
                                 switch(c){
                                     case 1:
                                        System.out.println("Digite o nome da tabela que deseja consultar:");
                                        String tabelaC = ler.next();
                                        tabelaC = tabelaC.toUpperCase();
                                        String comandoC = "SELECT * FROM " + tabelaC; 
                                                  
                                        System.out.println("Caso deseje restringir a consulta por alguma condicao, "
                                                 + "digite-a no formato 'coluna = condicao' (sem aspas). Caso contrario, digite '*'"); 
                                        String enter = ler.nextLine();
                                        String condicaoC = ler.nextLine();
                                                  
                                        if(!condicaoC.equals("*")) comandoC += " WHERE " + condicaoC;
                                                  
                                        comandoC = comandoC.toUpperCase();
                                                  
                                        try{
                                            ResultSet res = stmt.executeQuery(comandoC);
                                            printTable(res);
                                                    
                                        }catch(SQLException sqle){
                                           System.out.println("\nErro ao consultar\n");
                                        }
                                                  
                                         break;
                                                  
                                     case 2: 
                                         System.out.println("Escoha qual consulta deseja realizar:\n"
                                                 + "1-Quais sao os jogadores de determinada modalidade\n"
                                                 + "2-Quem sao os motoristas que prestaram servico para determinada evento\n"
                                                 + "3-Quais comidas foram servidas em determinada confraternização\n");
                                         int ca = ler.nextInt(); 
                                         ler.nextLine();
                                         
                                         switch(ca){
                                             case 1: 
                                                 System.out.println("Digite o nome da modalidade desejada:");
                                                 String modalidade = ler.nextLine(); 
                                                 System.out.println("Se desejar filtrar por time, digite seu nome. Caso contrario, digite *: ");
                                                 String time = ler.nextLine(); 
                                                 
                                                 String comando;
                                                 if(time.equals("*")) comando = "SELECT J.CPF, C.NOME FROM JOGADOR J, CONVIDADO C WHERE MODALIDADE = '"+ modalidade + "' AND J.CPF = C.CPF";
                                                 else comando = "SELECT J.CPF, C.NOME FROM JOGADOR J, CONVIDADO C WHERE MODALIDADE = '"+ modalidade + "' AND TIME = '" +time+"' AND J.CPF = C.CPF";
                                                 comando = comando.toUpperCase(); 
                                                 
                                                 try{
                                                    ResultSet res = stmt.executeQuery(comando);
                                                    printTable(res);
                                                    
                                                }catch(SQLException sqle){
                                                    System.out.println("\nErro ao consultar\n");
                                                }
                                                 
                                                 break; 
                                             case 2:
                                                 System.out.println("Digite o nome do evento desejado:"); 
                                                 String conf = ler.nextLine();
                                                 String comando2 = "SELECT M.CNH, M.NOME FROM MOTORISTA M JOIN CONDUCAO C ON  M.CNH = C.MOTORISTA " +
                                                        "JOIN TRANSPORTE T ON C.TRANSPORTE = T.PLACA JOIN VIAGEM V ON V.TRANSPORTE = T.PLACA " +
                                                        "JOIN CONFRATERNIZACAO CO ON CO.NOME = V.CONF AND CO.DATA = V.DATACONF " +
                                                        "WHERE CO.NOME = '" + conf + "'";
                                                 comando2 = comando2.toUpperCase(); 
                                                 
                                                 try{
                                                    ResultSet res = stmt.executeQuery(comando2);
                                                    printTable(res);
                                                    
                                                }catch(SQLException sqle){
                                                    System.out.println("\nErro ao consultar\n");
                                                }
                                                 break;
                                                 
                                             case 3:
                                                 System.out.println("Digite o nome da confraternizacao desejada:");
                                                 String confr = ler.nextLine(); 
                                                 String comando3 = "SELECT S.SALADA, A.ACOMP, PQ.PRATO " +
                                                    "FROM SALADAS S FULL OUTER JOIN BUFFET B ON S.BUFFET = B.CNPJ " +
                                                    "FULL OUTER JOIN PRATOSQUENTES PQ ON PQ.BUFFET = B.CNPJ " +
                                                    "FULL OUTER JOIN ACOMPANHAMENTOS A ON A.BUFFET = B.CNPJ " +
                                                    "JOIN COMIDACONF CC ON CC.BUFFET = B.CNPJ " +
                                                    "JOIN CONFRATERNIZACAO C ON CC.CONF = C.NOME " +
                                                    "WHERE C.NOME = '" + confr + "'";
                                                 
                                                 comando3 = comando3.toUpperCase(); 
                                                 
                                                 try{
                                                    ResultSet res = stmt.executeQuery(comando3);
                                                    printTable(res);
                                                    
                                                }catch(SQLException sqle){
                                                    System.out.println("\nErro ao consultar\n");
                                                }
                                                 break;
                                         }
                                         break;
                                 }
                                          
			         break;
			     case 3:
			        //inserir tupla
                                 System.out.println("Digite o nome da tabela onde deseja inserir um dado:");
                                 String tabela = ler.next();
                                 String limparbuffer = ler.nextLine();
                                 tabela = tabela.toUpperCase();
                                          
                                 ResultSet colunas = stmt.executeQuery("SELECT table_name, column_name, data_type, data_length FROM USER_TAB_COLUMNS WHERE table_name = '" + tabela + "'");
                                 String comando = new String(); 
                                 boolean tipoU = true;
                                 boolean tipoJ = true;
                                         
                                 while(colunas.next()){
                                 //Para tratar a insercao da cor de medalha que é um atributo derivado de colocaocao
                                 if(tabela.equals("MEDALHA") && colunas.getString(2).equals("COR")) continue; 
                                              
                                 System.out.println("Digite o seguinte dado no formato pedido:");
                                 System.out.println(colunas.getString(2) + " " + colunas.getString(3) + " de tamanho " + colunas.getString(4));
                                              
                                 //Para tratar a insercao da cor de medalha que é um atributo derivado de colocaocao
                                 if(tabela.equals("MEDALHA") && colunas.getString(2).equals("COLOCACAO")){
                                    String atributo = ler.next();
                                    String ignorarenter = ler.nextLine();
                                    comando += atributo + ", ";
                                    if(atributo.equals("1")) comando += "'OURO', ";
                                    else if(atributo.equals("2")) comando += "'PRATA', ";
                                    else comando+= "'BRONZE', ";
                                    continue;
                                 }
                                              
                                 if(colunas.getString(3).equals("DATE")){
                                     System.out.println("Informe a data no formato DD/MM/YYYY");
                                     String atributo = ler.next();
                                     String ignorarenter = ler.nextLine();
                                     comando += "to_date('" + atributo + "', 'DD/MM/YYYY'), ";
                                 } else if(colunas.getString(3).equals("FLOAT")){
                                     System.out.println("Numeros decimais devem ser inseridos com ponto");
                                     String atributo = ler.next();
                                     String ignorarenter = ler.nextLine();
                                     comando += atributo + ", ";
                                 } else if(tabela.equals("CONVIDADO") && colunas.getString(2).equals("TIPOU")){
                                     System.out.println("Digite o numero 1 caso o convidado seja um universitario ou 0 caso nao seja");
                                     String atributo = ler.next(); 
                                     String ignorarenter = ler.nextLine();
                                     if(atributo.equals("1")) tipoU = true;
                                     else tipoU = false;
                                     comando += atributo + ", ";
                                 }else if(tabela.equals("CONVIDADO") && colunas.getString(2).equals("TIPOJ")){
                                     System.out.println("Digite o numero 1 caso o convidado seja um jogador ou 0 caso nao seja");
                                     String atributo = ler.next();
                                     String ignorarenter = ler.nextLine();
                                     if(atributo.equals("1")) tipoJ = true;
                                     else tipoJ = false;
                                     comando += atributo + ", ";
                                 }else if(colunas.getString(3).equals("NUMBER")){
                                     String atributo = ler.next();
                                     String ignorarenter = ler.nextLine();
                                     comando += atributo + ", ";
                                 } else{
                                     String atributo = ler.nextLine();
                                     comando += "'"+ atributo + "', "; 
                                 }
                            }
                                          
                                          //Para tratar o problema de participacao total do convidado
                                          if(tabela.equals("CONVIDADO") && !(tipoU || tipoJ)){
                                              System.out.println("Erro ao inserir tupla.\n"
                                                      + "O convidado precisa ser Jogador ou Universitario\n"); 
                                              break;
                                          }
                                          
                                          int tamanho = comando.length();
                                          comando = comando.substring(0, tamanho-2);
                                          comando = comando.toUpperCase();
                                          System.out.println(comando);
                                          
                                        try{
                                            stmt.executeUpdate("insert into " + tabela + " values (" + comando + ")");
                                            System.out.println("\nTupla inserida na tabela.\n");
                                        }catch(SQLException sqle){
                                            System.out.println("\nErro ao inserir tupla\n");
                                        }
			                  break;
                                          
                                          
			              case 4:
			                  //alterar tupla
                                          
                                          System.out.println("Digite o nome da tabela onde deseja alterar um dado:");
                                          String tabelaUp = ler.next();
                                          tabelaUp = tabelaUp.toUpperCase();
                                          ResultSet colunasUp = stmt.executeQuery("SELECT table_name, column_name, data_type, data_length FROM USER_TAB_COLUMNS WHERE table_name = '" + tabelaUp + "'");
                                          String comandoUp = new String(); 
                                          comandoUp = "UPDATE " + tabelaUp + " SET ";
                                          
                                          System.out.println("A tabela " + tabelaUp + " possui as seguintes colunas nos designados formatos:");
                                          while(colunasUp.next()){
                                              System.out.println(colunasUp.getString(2) + " " + colunasUp.getString(3) + " de tamanho " + colunasUp.getString(4));
                                          }
                                          
                                          System.out.println("Digite a coluna que será feita a alteração:");
                                          String colunaUp = ler.next();
                                          comandoUp += colunaUp + " = ";  
                                          
                                          System.out.println("Digite o valor que deseja setar essa coluna:");
                                          String atributoUp = ler.next();
                                          comandoUp += atributoUp;

                                          System.out.println("Se houver condicao para a mudanca, insira no formato 'coluna = condicao'. Se nao houver, digite '*':");
                                          String ignorarEnter = ler.nextLine();
                                          String condicaoUp = ler.nextLine();
                                          if(!condicaoUp.equals("*")){
                                              comandoUp += " WHERE " + condicaoUp; 
                                          }
                                          
                                          comandoUp = comandoUp.toUpperCase();
                                          System.out.println(comandoUp);
                                          
                                          try{
                                            stmt.executeUpdate(comandoUp);
                                            System.out.println("\nTupla alterada.\n");
                                          }catch(SQLException sqle){
                                            System.out.println("\nErro ao alterar tupla\n");
                                          }
			                  break;
			              case 5:
			                  //remover tupla
                                          
                                          System.out.println("Digite o nome da tabela onde deseja deletar um dado:");
                                          String tabelaDel = ler.next();
                                          tabelaDel = tabelaDel.toUpperCase();
                                          String comandoDel = "DELETE FROM " + tabelaDel; 
                                          
                                          System.out.println("Digite a condicao para a remocao no formato 'coluna = condicao' (sem aspas)"); 
                                          String ignoraEnter = ler.nextLine();
                                          String condicaoDel = ler.nextLine(); 
                                          comandoDel += " WHERE " + condicaoDel; 
                                          comandoDel = comandoDel.toUpperCase();
                                          
                                          try{
                                            stmt.executeUpdate(comandoDel);
                                            System.out.println("\nTupla removida.\n");
                                          }catch(SQLException sqle){
                                            System.out.println("\nErro ao remover tupla\n");
                                          }
                                          
			                  break;
			              case 6:
			            	  a = 0;
                                          break;
			              case 7:
			                  //sair
			                  conexao.close();
			                  ler.close();
			                  System.out.println("Sessao finalizada.");
			                  return;
			          }
            	 }
                 
             }
        }
    }
}