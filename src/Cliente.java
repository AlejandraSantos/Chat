import java.net.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class Cliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoCliente mimarco=new MarcoCliente();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}


class MarcoCliente extends JFrame{
	
	public MarcoCliente(){
		
		setBounds(600,300,280,350);
				
		LaminaMarcoCliente milamina=new LaminaMarcoCliente();
		
		add(milamina);
		
		setVisible(true);
		}	
	
}

class LaminaMarcoCliente extends JPanel{
	
	public LaminaMarcoCliente(){
                nick = new JTextField(5);
                add(nick);
		JLabel texto=new JLabel("Chat");
		add(texto);
                
                ip= new JTextField(8);
                add(ip);
                
                JTextArea campochat=new JTextArea(12,20);
                add(campochat);
		campo1=new JTextField(20);
                
	
		add(campo1);		
	
		miboton=new JButton("Enviar");
                EnviaTexto mievento = new EnviaTexto();
                miboton.addActionListener(mievento);
		
		add(miboton);	
		
	}
        
    private class EnviaTexto implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("prueba");
            try {
                Socket misocket= new Socket("192.168.0.5",9999);
                PaqueteEnvio datos = new PaqueteEnvio();
                datos.setNick(nick.getText());
                datos.setIp(ip.getText());
                datos.setMensaje(campo1.getText());
                ObjectOutputStream paquetaxo = new ObjectOutputStream(misocket.getOutputStream());
                paquetaxo.writeObject(datos);
                misocket.close();
                
                        } catch (IOException ex) {
                Logger.getLogger(LaminaMarcoCliente.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
            }
        }

    }
		
	private JTextField campo1, nick, ip;	
	private JButton miboton;
        private JTextArea campochat;
	
}

class PaqueteEnvio implements Serializable{
    private String nick, ip, mensaje;
    
    public String getNick(){
    return nick;
}
    public void setNick(String nick){
        this.nick = nick;
    }
    
    public String getIp(){
    return ip;
}
    public void setIp(String ip){
        this.ip = ip;
    }
    
    public String getMensaje(){
        return mensaje;
    }
    
    public void setMensaje(String mensaje){
        this.mensaje= mensaje;
    }
}