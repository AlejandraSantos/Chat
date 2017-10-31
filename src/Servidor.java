import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoServidor mimarco=new MarcoServidor();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	}	
}

class MarcoServidor extends JFrame implements Runnable{
	
	public MarcoServidor(){
		
		setBounds(1200,300,280,350);				
			
		JPanel milamina= new JPanel();
		
		milamina.setLayout(new BorderLayout());
		
		areatexto=new JTextArea();
		
		milamina.add(areatexto,BorderLayout.CENTER);
		
		add(milamina);
		
		setVisible(true);
                Thread hilo = new Thread(this);
                hilo.start();
		
		}
	
	private	JTextArea areatexto;

    @Override
    public void run() {
        System.out.println("meme");
            try {
                ServerSocket servidor = new ServerSocket(9999);
                String nick, ip, mensaje;
                PaqueteEnvio paqueteR=new PaqueteEnvio();
                while(true){
                Socket misocket = servidor.accept();
                ObjectInputStream paquetedatos = new ObjectInputStream(misocket.getInputStream());
                paqueteR=(PaqueteEnvio) paquetedatos.readObject();
                
                nick = paqueteR.getNick();
                ip = paqueteR.getIp();
                mensaje = paqueteR.getMensaje();
                areatexto.append("\n"+nick+": "+mensaje+" para: "+ ip);
//                DataInputStream flujoentrada = new DataInputStream(misocket.getInputStream());
//                String mensajetexto = flujoentrada.readUTF();
//                areatexto.append("\n" + mensajetexto);
                misocket.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(MarcoServidor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MarcoServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
