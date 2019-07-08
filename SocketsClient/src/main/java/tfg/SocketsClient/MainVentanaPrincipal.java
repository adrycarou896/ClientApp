package tfg.SocketsClient;

public class MainVentanaPrincipal extends Thread{
	
	private VentanaPrincipal frame;
	
	public void main(){
		try {
			frame = new VentanaPrincipal();
			frame.setVisible(true);	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public VentanaPrincipal getVentanaPrincipal(){
		return this.frame;
	}
}
