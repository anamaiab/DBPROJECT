/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhobd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Marina Kako
 */
public class TrabalhoBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
         
        Connection conexao; 
        
        try{
 
            String url = "jdbc:oracle:thin:@grad.icmc.usp.br:15215:orcl";
 
            String usuario = "M9763151";
 
            String senha = "mk21011998*";
           
            conexao = DriverManager.getConnection(url, usuario, senha);
            
            System.out.println("Foi!!!");
            
            Statement stmt = conexao.createStatement(); //cria um Statement
 
            ResultSet resultado = stmt.executeQuery("select * from convidado"); //executa uma consulta
 
  
 
            while(resultado.next()){ //o método next() retorna true caso haja mais linhas
 
                System.out.print(resultado.getString("cpf")+"\t");
 
                System.out.println(resultado.getString("nome"));
 
                //System.out.println(resultado.getString("cnpj"));
 
            }
 
            resultado.close(); //fecha o ResultSet
 
            stmt.close(); //fecha o Statement
 
            conexao.close(); //encerra a conexão
 
        }catch(SQLException sqle){
 
            sqle.printStackTrace();
 
        }
        
    
        Menu menu = new Menu(); 
        
        menu();
        
    }
    
}
