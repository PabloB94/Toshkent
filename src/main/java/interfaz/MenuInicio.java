package interfaz;

import metro.AEstrella;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuInicio {
	
	public JFrame frameSeleccion;
	public JFrame frameSeleccion2;
	private AEstrella servicios;
	protected CalcularViaje seleccion;
	private Mapa plano;
	protected String origen;
	protected String destino;
	Color color;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					MenuInicio window = new MenuInicio();
					window.frameSeleccion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		});
		
	}

	public MenuInicio() {
		frameSeleccion = new JFrame();
		plano = new Mapa(this,servicios);
		seleccion = new CalcularViaje(this);
		initialize();
	}
	
	private void initialize() {
		frameSeleccion.pack();
		frameSeleccion.setSize(700, 467);//Tamano de la ventana
		frameSeleccion.setLocationRelativeTo(null);// Centrar en pantalla
		frameSeleccion.setResizable(false);//Para no poder cambiar tamano ventana
		frameSeleccion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Para cerrar correctamente		
		frameSeleccion.setTitle("Metro Tashkent");//Titulo Ventana		
		frameSeleccion.setIconImage(new ImageIcon(getClass().getResource("/images/Logo.png")).getImage());//Icono app
		
		// boton ver mapa
		JButton buttonMapa = new JButton("PLANO DE \nMETRO");
		buttonMapa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				plano.framePlano.setVisible(true);
			}
		});
		
		buttonMapa.setFont(new Font("Arial", Font.BOLD, 18));
		buttonMapa.setBounds(400, 250, 200, 39);			
		buttonMapa.setSize(200, 100);
		frameSeleccion.getContentPane().add(buttonMapa);
		
		JButton buttonTray = new JButton("VIAJE");
		buttonTray.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				seleccion.frameSeleccion.setVisible(true);
			}
		});
		
		buttonTray.setFont(new Font("Arial", Font.BOLD, 18));	
		buttonTray.setBounds(100, 250, 200, 39);			
		buttonTray.setSize(200, 100);

		frameSeleccion.getContentPane().add(buttonTray);
		
		JPanel panelFondoBlanco = new JPanel();
		frameSeleccion.getContentPane().add(panelFondoBlanco);
		
		JLabel logo = new JLabel("TOSHKENT METRO");
		logo.setForeground(Color.WHITE);
		logo.setFont(new Font("Arial", Font.BOLD,40));
		logo.setBounds(50,-200,900,700);							
		frameSeleccion.getContentPane().add(logo);
		
		JPanel panelFondoBlancoTitulo = new JPanel();
		frameSeleccion.getContentPane().add(panelFondoBlancoTitulo);
		
		// Imagen de Fondo
		JLabel ImagenFondo = new JLabel("");
		ImagenFondo.setIcon(new ImageIcon(MenuInicio.class.getResource("/images/metro.jpg")));
		ImagenFondo.setBounds(0, 0, 900, 600);								
		frameSeleccion.getContentPane().add(ImagenFondo);		
	}
}

