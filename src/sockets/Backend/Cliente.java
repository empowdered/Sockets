/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets.Backend;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import sockets.Frontend.VentanaPrincipal;

/**
 *
 * @author jpmunoz
 */
public class Cliente implements Runnable {

    private Socket s;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    
    private VentanaPrincipal vp;

    public Cliente(VentanaPrincipal vp) {
        this.vp = vp;
    }

    @Override
    public void run() {
        try {
            s = new Socket("127.0.0.1", 9999);
            ois = new ObjectInputStream(s.getInputStream());
            oos = new ObjectOutputStream(s.getOutputStream());
            System.out.println((String) ois.readObject());
            vp.getAreaChat().append("Conexion Exitosa");
            this.leerLinea();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            this.cerrarCliente();
        }
    }

    public void escribirLinea(String line) {
        try {
            oos.writeObject(line);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void leerLinea() {
        try {
            while (true) {
                Object aux = ois.readObject();
                if (aux != null && aux instanceof String) {
                    vp.getAreaChat().append("\nServidor dice: " + (String) aux);
                }
                Thread.sleep(15);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void cerrarCliente() {
        try {
            oos.close();
            ois.close();
            s.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
