/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets.Backend;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import sockets.Frontend.VentanaPrincipal;

/**
 *
 * @author jpmunoz
 */
public class Servidor implements Runnable {

    private ServerSocket ss;
    private Socket s;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    private VentanaPrincipal vp;

    public Servidor(VentanaPrincipal vp) {
        this.vp = vp;
    }

    public void cerrarServidor() {
        try {
            ois.close();
            oos.close();
            s.close();
            ss.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void escribirLinea(String linea) {
        try {
            oos.writeObject(linea);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void leerLinea() {
        try {
            while (true) {
                Object aux = ois.readObject();
                if (aux != null && aux instanceof String) {
                    vp.getAreaChat().append((String) aux);
                }
                Thread.sleep(15);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {

            ss = new ServerSocket(9999);
            s = ss.accept();
            oos = new ObjectOutputStream(s.getOutputStream());
            ois = new ObjectInputStream(s.getInputStream());
            vp.getAreaChat().append("Conexion Exitosa\n");
            this.leerLinea();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.cerrarServidor();
        }
    }
}
