/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets.Backend;

import java.util.Scanner;
import sockets.Backend.Cliente;
import sockets.Backend.Servidor;

/**
 *
 * @author jpmunoz
 */
public class Core {
    public Core(){
        System.out.println("1-Servidor 2.Cliente");
        Scanner s = new Scanner(System.in);
        Integer i = (Integer)s.nextInt();
        if(i!=null){
            if(i.equals(1)){
                Servidor s2 = new Servidor(null);
            }else if(i.equals(2)){
                Cliente c = new Cliente(null);
            }
        }
    }
}
