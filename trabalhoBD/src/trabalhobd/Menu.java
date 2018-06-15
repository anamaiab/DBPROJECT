/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhobd;

import java.util.Scanner;

/**
 *
 * @author Marina Kako
 */
public class Menu {

    public static void main(String[] args) {
    
        Scanner ler = new Scanner(System.in);
 
        System.out.println("Bem vindo ao seu gerenciador de eventos");
        System.out.println("Escolha um dos eventos: \n1-Festa\n2-Confraternizacao");
        
        int evento = ler.nextInt();
    
        if(evento == 1){
            System.out.println("Escolha uma das opções abaixo:\n1-Listar tabelas\n2-Consultar\n3-Sair");
            
            int op = ler.nextInt();
            
            if(op == 1){
                //listar tabelas
            }else if(op == 2){
                //consultar
            }else{
                //sair
                System.out.println("Saindo...");
                return;
            }
        } else if(evento == 2){
            System.out.println("Escolha uma das opções abaixo:\n1-Listar tabelas\n2-Consultar\n3-Inserir tupla\n4-Alterar tupla\n5-Remover tupla\n6-Sair");
            
            int op = ler.nextInt();
            
            switch(op){
                case 1:
                    //listar tabelas
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
                    //sair
                    System.out.println("Saindo...");
                    return;
            }
        }
    }
    
}
