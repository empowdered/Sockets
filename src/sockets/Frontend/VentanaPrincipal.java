/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets.Frontend;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import sockets.Backend.Cliente;
import sockets.Backend.Servidor;

/**
 *
 * @author jpmunoz
 */
public class VentanaPrincipal extends JFrame implements ActionListener, WindowListener {

    private JMenuBar menuBar;
    private JMenu accion;
    private JMenuItem conect, create, exit;

    private JTextArea areaChat;
    private JTextField text;
    private JButton send;
    private JScrollPane scroll;
    
    private Servidor s;
    private Cliente c;

    public VentanaPrincipal() {
        super("Chat tutorial");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 600);
        this.addWindowListener(this);
        this.ubicarComponentes();
        this.setVisible(true);
    }

    public void ubicarComponentes() {
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        accion = new JMenu("Acci�n");
        menuBar.add(accion);
        conect = new JMenuItem("Conectar");
        conect.addActionListener(this);
        accion.add(conect);
        create = new JMenuItem("Crear Servidor");
        create.addActionListener(this);
        accion.add(create);
        exit = new JMenuItem("Salir");
        exit.addActionListener(this);
        accion.add(exit);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        areaChat = new JTextArea();
        scroll = new JScrollPane(areaChat);
        this.add(scroll, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        text = new JTextField(20);
        this.add(text, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        send = new JButton("Enviar");
        send.addActionListener(this);
        this.add(send, gbc);

    }

    public JTextArea getAreaChat() {
        return areaChat;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create) {
            s = new Servidor(this);
            Thread t = new Thread(s);
            t.start();
        } else if (e.getSource() == conect) {
            c = new Cliente(this);
            Thread t = new Thread(c);
            t.start();
        } else if (e.getSource() == exit) {
            this.dispose();
        } else if (e.getSource() == send) {
            if (s != null) {
                s.escribirLinea(text.getText());
                areaChat.append("\nYo:" + text.getText());
                text.setText("");
            }
            if (c != null) {
                c.escribirLinea(text.getText());
                areaChat.append("\nYo:" + text.getText());
                text.setText("");
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        JOptionPane.showMessageDialog(null, "Ohhh me has cerrado");
        System.exit(0); 
    }

    @Override
    public void windowClosed(WindowEvent e) {
        if (s != null) {
            s.cerrarServidor();
        }
        if (c != null) {
            c.cerrarCliente();
        }
    }

    @Override
    public void windowIconified(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
       //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates. 
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
      //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
