package interfaz;

import metro.ListaParadas;
import metro.Estacion;
import metro.AEstrella;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Viaje {

    private JFrame frameTrayecto;
    private JPanel contentPaneRuta;
    private AEstrella servicios;
    private CalcularViaje seleccion;
    private List<String> trasbordos;
    private JLabel labelIndicaciones;
    private JTextArea textArea;
    private JTextArea textArea2;
    private ListaParadas construccion;

    public Viaje(CalcularViaje seleccion, AEstrella servicios) {
        frameTrayecto = new JFrame();
        this.seleccion = seleccion;
        this.servicios = servicios;
        this.trasbordos = new ArrayList<String>();
        this.trasbordos.add("");
        this.trasbordos.add("");
        this.trasbordos.add("");
        construccion = new ListaParadas();
        inicialize();
    }

    public List<Estacion> verificarDestino(Estacion origen, String destino) {
        int linea = 0;
        Estacion trasbordoB = ListaParadas.getEstacion(destino);
        List<Estacion> comparar = servicios.recorrido(origen, trasbordoB), resultado = null;
        if (trasbordoB.getNumEnlaces()) {

            linea = trasbordoB.getConexionA();
            resultado = servicios.recorrido(origen, ListaParadas.getLineas()[linea - 1].get(destino));
            comparar = servicios.getUltimoTiempo() > comparar.get(0).getPesoRecorrido() ? comparar : resultado;
        }
        return comparar;
    }

    public void hacerRecorrido() {
        double tiempo = 0;
        List<Estacion> recorrido = new ArrayList<Estacion>();
        if (!this.trasbordos.contains(seleccion.getOrigen()) && !this.trasbordos.contains(seleccion.getDestino())) {
            recorrido = servicios.recorrido(ListaParadas.getEstacion(seleccion.getOrigen()), ListaParadas.getEstacion(seleccion.getDestino()));
            tiempo = servicios.getUltimoTiempo();
        } else if (this.trasbordos.contains(seleccion.getOrigen()) && !this.trasbordos.contains(seleccion.getDestino())) {
            int linea = 0;
            Estacion trasbordoA = ListaParadas.getEstacion(seleccion.getOrigen());
            recorrido = servicios.recorrido(trasbordoA, ListaParadas.getEstacion(seleccion.getDestino()));
            tiempo = servicios.getUltimoTiempo();
            List<Estacion> comparar = null;
            if (trasbordoA.getNumEnlaces()) {

                linea = trasbordoA.getConexionA();
                comparar = servicios.recorrido(ListaParadas.getLineas()[linea - 1].get(seleccion.getOrigen()), ListaParadas.getEstacion(seleccion.getDestino()));
                recorrido = tiempo > servicios.getUltimoTiempo() ? comparar : recorrido;
                tiempo = tiempo > servicios.getUltimoTiempo() ? servicios.getUltimoTiempo() : tiempo;
            }
        } else if (!this.trasbordos.contains(seleccion.getOrigen()) && this.trasbordos.contains(seleccion.getDestino())) {
            recorrido = verificarDestino(ListaParadas.getEstacion(seleccion.getOrigen()), seleccion.getDestino());
            tiempo = recorrido.get(0).getPesoRecorrido();
        } else {
            List<Estacion> comparar = null;
            Estacion origen = ListaParadas.getEstacion(seleccion.getOrigen());
            recorrido = verificarDestino(origen, seleccion.getDestino());
            tiempo = recorrido.get(0).getPesoRecorrido();
            int[] conexiones = {origen.getConexionA()/*, origen.getConexionB()*/};
            for (int i = 0; i < 1 /*2*/; i++)
                if (conexiones[i] > 0) {
                    origen = ListaParadas.getLineas()[conexiones[i] - 1].get(seleccion.getOrigen());
                    comparar = verificarDestino(origen, seleccion.getDestino());
                    recorrido = tiempo > comparar.get(0).getPesoRecorrido() ? comparar : recorrido;
                    tiempo = tiempo > comparar.get(0).getPesoRecorrido() ? comparar.get(0).getPesoRecorrido() : tiempo;
                }
        }

        seleccion.setOrigen("");
        seleccion.setDestino("");
        String ruta = "";
        String nodo = "";
        String nombre = "";
        int linea = 0;
        int size = recorrido.size();
        for (int i = size - 1; i > -1; i--) {
            if (i == size - 1) {
                nombre = recorrido.get(i).getNombre();
                linea = recorrido.get(i).getLinea();
                if (linea == 1) {
                    ruta = ruta + "Se encuentra en ";
                    ruta = ruta + nombre + "\n";
                    ruta = ruta + "Debe tomar la linea Uzbekistan" + "\n hasta la estacion ";
                }
                if (linea == 2) {
                    ruta = ruta + "Se encuentra en ";
                    ruta = ruta + nombre + "\n";
                    ruta = ruta + "Debe tomar la linea Chilonzor" + "\n hasta la estacion ";
                }
                if (linea == 3) {
                    ruta = ruta + "Se encuentra en ";
                    ruta = ruta + nombre + "\n";
                    ruta = ruta + "Debe tomar la linea Yunusabad" + "\n hasta la estacion ";
                }

            } else if (i == 0) {
                ruta = ruta + recorrido.get(i).getNombre();
            } else if (recorrido.get(i).getLinea() != linea) {
                ruta = ruta + nombre + "\nHaga transbordo \n";
                linea = recorrido.get(i).getLinea();
                if (linea == 1) {
                    ruta = ruta + "Debe tomar la linea Uzbekistan" + " hasta la estacion ";
                }
                if (linea == 2) {
                    ruta = ruta + "Debe tomar la linea Chilonzor" + " hasta la estacion ";
                }
                if (linea == 3) {
                    ruta = ruta + "Debe tomar la linea Yunusabad" + " hasta la estacion ";
                }
            } else
                nombre = recorrido.get(i).getNombre();
            linea = recorrido.get(i).getLinea();
            if (linea == 1) {
                nodo = nodo + recorrido.get(i).getNombre() + " (linea Uzbekistan)\n";
            }
            if (linea == 2) {
                nodo = nodo + recorrido.get(i).getNombre() + " (linea Chilonzor)\n";
            }
            if (linea == 3) {
                nodo = nodo + recorrido.get(i).getNombre() + " (llinea Yunusabad)\n";
            }

        }
        ruta = ruta + "\n\nTiempo estimado: " + (int) tiempo + " minutos";
        textArea.setText(nodo);
        textArea2.setText(ruta);
    }

    public void inicialize() {
        frameTrayecto.pack();
        frameTrayecto.setSize(700, 500);//Tamano de la ventana
        frameTrayecto.setLocationRelativeTo(null);// Centrar en pantalla
        frameTrayecto.setResizable(false);//Para no poder cambiar tamano ventana
        frameTrayecto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Para cerrar correctamente
        frameTrayecto.setTitle("Toshkent Metro — Tashkent");//Titulo Ventana
        frameTrayecto.setIconImage(new ImageIcon(getClass().getResource("/images/Logo.png")).getImage());//Icono app

        frameTrayecto.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                frameTrayecto.setVisible(false);
                seleccion.getCalctripframe().setVisible(true);
            }
        });

        contentPaneRuta = new JPanel();
        contentPaneRuta.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPaneRuta.setLayout(null);
        contentPaneRuta.setBackground(Color.WHITE);

        frameTrayecto.setContentPane(contentPaneRuta);

        JButton btnAtrasMapa = new JButton("MENU PRINCIPAL");

        btnAtrasMapa.setBounds(250, 420, 150, 30);
        contentPaneRuta.add(btnAtrasMapa);
        btnAtrasMapa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                frameTrayecto.setVisible(false);
                if (frameTrayecto.isVisible())
                    frameTrayecto.setVisible(true);
            }
        });


        JLabel lblSeleccioneUnOrigen = new JLabel("Mejor Opcion");
        lblSeleccioneUnOrigen.setForeground(Color.BLUE);
        lblSeleccioneUnOrigen.setFont(new Font("ARIAL", Font.BOLD, 25));
        lblSeleccioneUnOrigen.setHorizontalAlignment(SwingConstants.CENTER);
        lblSeleccioneUnOrigen.setBounds(10, 10, 300, 20);
        frameTrayecto.getContentPane().add(lblSeleccioneUnOrigen);

        textArea = new JTextArea();
        textArea.setBackground(Color.white);
        textArea.setEditable(false);
        textArea.setBounds(350, 100, 250, 279);
        contentPaneRuta.add(textArea);
        textArea.setFont(new Font("ARIAL", Font.CENTER_BASELINE, 12));
        textArea.setAlignmentX(SwingConstants.CENTER);
        textArea.setAlignmentX(SwingConstants.CENTER);
        textArea.setAlignmentY(SwingConstants.CENTER);

        textArea2 = new JTextArea();
        textArea2.setEditable(false);
        textArea2.setBounds(10, 100, 330, 300);
        contentPaneRuta.add(textArea2);
        textArea2.setFont(new Font("ARIAL", Font.CENTER_BASELINE, 12));
        textArea2.setAlignmentX(SwingConstants.CENTER);
        textArea2.setAlignmentY(SwingConstants.CENTER);

        labelIndicaciones = new JLabel("");
        labelIndicaciones.setIcon(new ImageIcon(Viaje.class.getResource("/images/Metro.jpg")));
        labelIndicaciones.setBounds(0, 0, 798, 556);
        contentPaneRuta.add(labelIndicaciones);
    }

    public JFrame getFrameTrayecto() {
        return frameTrayecto;
    }
}