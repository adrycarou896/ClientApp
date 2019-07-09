package tfg.SocketsClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	
	private JLabel labelNumInClase;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);	
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = getPanel();
		panel.add(getLblClase(), BorderLayout.WEST);
		panel.add(getLabelNumInClase(), BorderLayout.CENTER);

	}
	
	private JPanel getPanel(){
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		return panel;
	}
	
	private JLabel getLblClase(){
		return new JLabel("Clase 1: ");
	}
	
	private JLabel getLabelNumInClase(){
		this.labelNumInClase=new JLabel("0");
		return this.labelNumInClase;
	}
	
	public JLabel getLabel(){
		return this.labelNumInClase;
	}

}
