package interfaz;

import metro.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Mapa {

	protected JFrame framePlano;
	private JPanel contentPaneMapa;
	private final MenuInicio inicio;
	private JButton btnAtrasMapa;

	public Mapa(MenuInicio seleccion2, AEstrella servicios) {
		framePlano = new JFrame();
		this.inicio = seleccion2;
		inicialize();	
	}

	public void inicialize(){
		
		framePlano.pack();
		framePlano.setSize(883, 600);//Tamano de la ventana
		framePlano.setLocationRelativeTo(null);// Centrar en pantalla
		framePlano.setResizable(false);//Para no poder cambiar tamano ventana
		framePlano.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Para cerrar correctamente		
		framePlano.setTitle("Metro Taskent");//Titulo Ventana		
		framePlano.setIconImage(new ImageIcon(getClass().getResource("/images/Logo.png")).getImage());//Icono app

		contentPaneMapa = new JPanel();
		contentPaneMapa.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPaneMapa.setLayout(null);
		contentPaneMapa.setBackground(Color.WHITE);

		framePlano.setContentPane(contentPaneMapa);
		framePlano.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				framePlano.setVisible(false);
				inicio.frameSeleccion.setVisible(true);
			}
		});

		btnAtrasMapa = new JButton("Atrás");
		btnAtrasMapa.setBounds(598, 35, 100, 39);						
		contentPaneMapa.add(btnAtrasMapa);
		btnAtrasMapa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				framePlano.setVisible(false);
				if (inicio.frameSeleccion.isVisible())
					inicio.frameSeleccion.setVisible(true);

			}
		});

		JLabel ImagenMapa = new JLabel("");
		ImagenMapa.setIcon(new ImageIcon(Mapa.class.getResource("/images/MapaMetro.png")));
		ImagenMapa.setBounds(0, -50, 883, 700);						
		contentPaneMapa.add(ImagenMapa);
	}

}