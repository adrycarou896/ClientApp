package tfg.SocketsClient;

import javax.swing.JLabel;

public class Case1 extends Client{
	
	private VentanaPrincipal ventanaPrincipal;
	
	public Case1(){
		MainVentanaPrincipal mainVentanaPrincipal = new MainVentanaPrincipal();
		mainVentanaPrincipal.main();
		this.ventanaPrincipal = mainVentanaPrincipal.getVentanaPrincipal();
	}

	@Override
	public void enter() {
		JLabel label = this.ventanaPrincipal.getLabel();
		label.setText(String.valueOf(Integer.parseInt(label.getText())+1));
	}

	@Override
	public void out() {
		JLabel label = this.ventanaPrincipal.getLabel();
		label.setText(String.valueOf(Integer.parseInt(label.getText())-1));
	}
	
	
}
