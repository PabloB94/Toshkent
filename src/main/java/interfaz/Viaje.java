package interfaz;

import metro.ListaParadas;
import metro.Estacion;
import metro.AEstrella;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Viaje {

    private JFrame tripframe;
    private AEstrella astar;
    private MenuInicio initmenuui;
    private CalcularViaje calctrip;
    private List<String> trasbordos;
    private JTextArea nodestextarea;
    private JTextArea routetextarea;
    private ListaParadas construccion;

    private final String WINDOW_TITTLE_TXT = "Toshkent Metro — Tashkent";

    public Viaje(MenuInicio initmenuui, CalcularViaje calctrip, AEstrella astar) {
        this.initmenuui = initmenuui;
        this.tripframe = new JFrame();
        this.calctrip = calctrip;
        this.astar = astar;
        this.trasbordos = new ArrayList<String>();
        construccion = new ListaParadas();
        inicialize();
    }

    public void inicialize() {
        LookAndFeel currlaf = UIManager.getLookAndFeel();
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e1) {
            // If Nimbus is not available, sets the GUI to another look and feel.
            try {
                UIManager.setLookAndFeel(currlaf.getName());
            } catch (Exception e2) {
                e1.printStackTrace();
                e2.printStackTrace();
            }
        }

        //
        // Window
        //
        tripframe.pack();
        tripframe.setFocusable(true);
        tripframe.setPreferredSize(new Dimension(780, 580));
        tripframe.setSize(tripframe.getPreferredSize()); // Tamano de la ventana
        tripframe.setLocationRelativeTo(null); // Centrar en pantalla
        tripframe.setResizable(false); // Para no poder cambiar tamano ventana
        tripframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Para cerrar correctamente
        tripframe.setTitle(WINDOW_TITTLE_TXT); // Titulo Ventana
        tripframe.setIconImage(new ImageIcon(getClass().getResource("/images/Logo.png")).getImage()); // Icono app

        tripframe.getContentPane().setPreferredSize(tripframe.getPreferredSize());
        tripframe.getContentPane().setSize(tripframe.getContentPane().getPreferredSize());
        tripframe.getContentPane().setLayout(null);

        //
        // Components
        //

        //
        // Body Panel
        //
        JSplitPane bodysplitpane = new JSplitPane();
        tripframe.getContentPane().add(bodysplitpane);
        bodysplitpane.setLocation(0, 0);
        bodysplitpane.setOpaque(false);
        bodysplitpane.setPreferredSize(bodysplitpane.getParent().getPreferredSize());
        bodysplitpane.setSize(bodysplitpane.getPreferredSize());
        bodysplitpane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        bodysplitpane.setDividerLocation(0.08);
        bodysplitpane.setOneTouchExpandable(false);
        bodysplitpane.setContinuousLayout(false);

        BasicSplitPaneUI ui = (BasicSplitPaneUI) bodysplitpane.getUI();
        BasicSplitPaneDivider divider = ui.getDivider();
        divider.setVisible(false);

        //
        // Top panel
        //
        JPanel toppanel = new JPanel(new BorderLayout());
        bodysplitpane.setTopComponent(toppanel);
        toppanel.setPreferredSize(new Dimension(toppanel.getParent().getWidth(), (int) (toppanel.getParent().getHeight() * 0.08)));
        toppanel.setSize(toppanel.getPreferredSize());
        toppanel.setOpaque(false);

        //
        // Back Arrow Panel
        //
        JPanel backarrowpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toppanel.add(backarrowpanel, BorderLayout.WEST);
        backarrowpanel.setPreferredSize(new Dimension((int) (backarrowpanel.getParent().getWidth() * 0.5), backarrowpanel.getParent().getHeight()));
        backarrowpanel.setSize(backarrowpanel.getPreferredSize());
        backarrowpanel.setOpaque(false);

        //
        // Home Button Panel
        //
        JPanel homebuttonpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        toppanel.add(homebuttonpanel, BorderLayout.EAST);
        homebuttonpanel.setPreferredSize(new Dimension((int) (homebuttonpanel.getParent().getWidth() * 0.5), homebuttonpanel.getParent().getHeight()));
        homebuttonpanel.setSize(homebuttonpanel.getPreferredSize());
        homebuttonpanel.setOpaque(false);

        //
        // Back Arrow Button
        //
        JButton backarrowbtn = new JButton("");
        backarrowpanel.add(backarrowbtn);
        backarrowbtn.setHorizontalTextPosition(JButton.CENTER);
        backarrowbtn.setOpaque(false);
        backarrowbtn.setBorderPainted(false);
        backarrowbtn.setFocusPainted(false);
        backarrowbtn.setContentAreaFilled(false);
        backarrowbtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tripframe.setVisible(false);
                calctrip.getCalctripframe().setVisible(true);
            }

            Cursor c = null;

            @Override
            public void mouseEntered(MouseEvent e) {
                JButton btn = (JButton) e.getSource();
                c = btn.getCursor();
                btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton btn = (JButton) e.getSource();
                btn.setCursor(c);
            }
        });

        try {
            Image img = ImageIO.read(getClass().getResource("/images/back-arrow.png"));
            backarrowbtn.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //
        // Home Button
        //
        JButton homebtn = new JButton("");
        homebuttonpanel.add(homebtn);
        homebtn.setHorizontalTextPosition(JButton.CENTER);
        homebtn.setOpaque(false);
        homebtn.setBorderPainted(false);
        homebtn.setFocusPainted(false);
        homebtn.setContentAreaFilled(false);
        homebtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tripframe.setVisible(false);
                initmenuui.getInitmenuframe().setVisible(true);
            }

            Cursor c = null;

            @Override
            public void mouseEntered(MouseEvent e) {
                JButton btn = (JButton) e.getSource();
                c = btn.getCursor();
                btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton btn = (JButton) e.getSource();
                btn.setCursor(c);
            }
        });

        try {
            Image img = ImageIO.read(getClass().getResource("/images/web-page-home.png"));
            homebtn.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //
        // Botton panel
        //
        JPanel bottonpanel = new JPanel();
        bodysplitpane.setBottomComponent(bottonpanel);
        bottonpanel.setPreferredSize(new Dimension(bodysplitpane.getWidth(), (int) (bodysplitpane.getHeight() * 0.92)));
        bottonpanel.setSize(bottonpanel.getPreferredSize());
        bottonpanel.setOpaque(false);
        bottonpanel.setAlignmentY(SwingConstants.CENTER);
        bottonpanel.setAlignmentX(SwingConstants.CENTER);
        bottonpanel.setLayout(null);
        bottonpanel.setBackground(Color.CYAN);

        //
        // Route JLabel
        //
        JLabel routejlabel = new JLabel("Ruta");
        bottonpanel.add(routejlabel);
        routejlabel.setPreferredSize(new Dimension((int)(bottonpanel.getWidth()/2), 100));
        routejlabel.setSize(routejlabel.getPreferredSize());
        routejlabel.setOpaque(false);
        routejlabel.setForeground(new Color(0, 0, 0));
        routejlabel.setFont(new Font("ARIAL", Font.BOLD, 20));
        routejlabel.setHorizontalTextPosition(SwingConstants.CENTER);
        routejlabel.setHorizontalAlignment(SwingConstants.CENTER);
        routejlabel.setBounds(0, 0, (int)(bottonpanel.getPreferredSize().getWidth()/2), 100);

        //
        // Nodes JLabel
        //
        JLabel nodesjlabel = new JLabel("Estaciones");
        bottonpanel.add(nodesjlabel);
        nodesjlabel.setPreferredSize(new Dimension((int)(bottonpanel.getWidth()/2), 100));
        nodesjlabel.setSize(routejlabel.getPreferredSize());
        nodesjlabel.setOpaque(false);
        nodesjlabel.setForeground(new Color(0, 0, 0));
        nodesjlabel.setFont(new Font("ARIAL", Font.BOLD, 20));
        nodesjlabel.setHorizontalTextPosition(SwingConstants.CENTER);
        nodesjlabel.setHorizontalAlignment(SwingConstants.CENTER);
        nodesjlabel.setBounds((int)(bottonpanel.getPreferredSize().getWidth()/2), 0, (int)(bottonpanel.getPreferredSize().getWidth()/2), 100);

        //
        // Route Text Area
        //
        routetextarea = new JTextArea();
        bottonpanel.add(routetextarea);
        routetextarea.setEditable(false);
        routetextarea.setFocusable(false);
        routetextarea.setWrapStyleWord(true);
        routetextarea.setLineWrap(true);
        routetextarea.setAutoscrolls(true);
        routetextarea.setPreferredSize(new Dimension(350, 400));
        routetextarea.setSize(routetextarea.getPreferredSize());
        routetextarea.setFont(new Font("ARIAL", Font.BOLD, 12));
        routetextarea.setAlignmentX(SwingConstants.CENTER);
        routetextarea.setAlignmentY(SwingConstants.CENTER);
        routetextarea.setLocation(25, 75);

        //
        // Nodes Text Area
        //
        nodestextarea = new JTextArea();
        bottonpanel.add(nodestextarea);
        nodestextarea.setEditable(false);
        nodestextarea.setFocusable(false);
        nodestextarea.setWrapStyleWord(true);
        nodestextarea.setLineWrap(true);
        nodestextarea.setAutoscrolls(true);
        nodestextarea.setPreferredSize(new Dimension(350, 400));
        nodestextarea.setSize(nodestextarea.getPreferredSize());
        nodestextarea.setFont(new Font("ARIAL", Font.BOLD, 12));
        nodestextarea.setAlignmentX(SwingConstants.CENTER);
        nodestextarea.setAlignmentY(SwingConstants.CENTER);
        nodestextarea.setLocation(400, 75);

        //
        // Metro Image JLabel
        //
        JLabel metroimgjl = new JLabel("");
        tripframe.getContentPane().add(metroimgjl);
        metroimgjl.setLocation(0, 0);
        metroimgjl.setSize(tripframe.getContentPane().getWidth(), tripframe.getContentPane().getHeight());
        metroimgjl.setHorizontalAlignment(SwingConstants.CENTER);

        ImageIcon imgicon = new ImageIcon(Mapa.class.getResource("/images/Ruta.jpg"));
        metroimgjl.setIcon(imgicon);

        tripframe.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                bodysplitpane.resetToPreferredSizes();
                hacerRecorrido();
            }
        });
    }

    public List<Estacion> verificarDestino(Estacion origen, String destino) {
        int linea = 0;
        Estacion trasbordoB = ListaParadas.getEstacion(destino);
        List<Estacion> comparar = astar.recorrido(origen, trasbordoB), resultado = null;
        if (trasbordoB.getNumEnlaces()) {

            linea = trasbordoB.getConexionA();
            resultado = astar.recorrido(origen, ListaParadas.getLineas()[linea - 1].get(destino));
            comparar = astar.getUltimoTiempo() > comparar.get(0).getPesoRecorrido() ? comparar : resultado;
        }
        return comparar;
    }

    public void hacerRecorrido() {
        double tiempo = 0;
        List<Estacion> recorrido = new ArrayList<Estacion>();
        if (!this.trasbordos.contains(calctrip.getOrigen()) && !this.trasbordos.contains(calctrip.getDestino())) {
            recorrido = astar.recorrido(ListaParadas.getEstacion(calctrip.getOrigen()), ListaParadas.getEstacion(calctrip.getDestino()));
            tiempo = astar.getUltimoTiempo();
        } else if (this.trasbordos.contains(calctrip.getOrigen()) && !this.trasbordos.contains(calctrip.getDestino())) {
            int linea = 0;
            Estacion trasbordoA = ListaParadas.getEstacion(calctrip.getOrigen());
            recorrido = astar.recorrido(trasbordoA, ListaParadas.getEstacion(calctrip.getDestino()));
            tiempo = astar.getUltimoTiempo();
            List<Estacion> comparar = null;
            if (trasbordoA.getNumEnlaces()) {

                linea = trasbordoA.getConexionA();
                comparar = astar.recorrido(ListaParadas.getLineas()[linea - 1].get(calctrip.getOrigen()), ListaParadas.getEstacion(calctrip.getDestino()));
                recorrido = tiempo > astar.getUltimoTiempo() ? comparar : recorrido;
                tiempo = tiempo > astar.getUltimoTiempo() ? astar.getUltimoTiempo() : tiempo;
            }
        } else if (!this.trasbordos.contains(calctrip.getOrigen()) && this.trasbordos.contains(calctrip.getDestino())) {
            recorrido = verificarDestino(ListaParadas.getEstacion(calctrip.getOrigen()), calctrip.getDestino());
            tiempo = recorrido.get(0).getPesoRecorrido();
        } else {
            List<Estacion> comparar = null;
            Estacion origen = ListaParadas.getEstacion(calctrip.getOrigen());
            recorrido = verificarDestino(origen, calctrip.getDestino());
            tiempo = recorrido.get(0).getPesoRecorrido();
            int[] conexiones = {origen.getConexionA()/*, origen.getConexionB()*/};
            for (int i = 0; i < 1 /*2*/; i++)
                if (conexiones[i] > 0) {
                    origen = ListaParadas.getLineas()[conexiones[i] - 1].get(calctrip.getOrigen());
                    comparar = verificarDestino(origen, calctrip.getDestino());
                    recorrido = tiempo > comparar.get(0).getPesoRecorrido() ? comparar : recorrido;
                    tiempo = tiempo > comparar.get(0).getPesoRecorrido() ? comparar.get(0).getPesoRecorrido() : tiempo;
                }
        }

        calctrip.setOrigen("");
        calctrip.setDestino("");
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
                    ruta = ruta + "Debe tomar la linea Uzbekistan hasta la estacion ";
                }
                if (linea == 2) {
                    ruta = ruta + "Se encuentra en ";
                    ruta = ruta + nombre + "\n";
                    ruta = ruta + "Debe tomar la linea Chilonzor hasta la estacion ";
                }
                if (linea == 3) {
                    ruta = ruta + "Se encuentra en ";
                    ruta = ruta + nombre + "\n";
                    ruta = ruta + "Debe tomar la linea Yunusabad hasta la estacion ";
                }

            } else if (i == 0) {
                ruta = ruta + recorrido.get(i).getNombre()+"\n";
            } else if (recorrido.get(i).getLinea() != linea) {
                ruta = ruta + nombre + "\nHaga transbordo\n";
                linea = recorrido.get(i).getLinea();
                if (linea == 1) {
                    ruta = ruta + "Debe tomar la linea Uzbekistan hasta la estacion ";
                }
                if (linea == 2) {
                    ruta = ruta + "Debe tomar la linea Chilonzor hasta la estacion ";
                }
                if (linea == 3) {
                    ruta = ruta + "Debe tomar la linea Yunusabad hasta la estacion ";
                }
            } else
                nombre = recorrido.get(i).getNombre();

            linea = recorrido.get(i).getLinea();
            if (linea == 1) {
                nodo = nodo + recorrido.get(i).getNombre() + " (Linea Uzbekistan)\n";
            }
            if (linea == 2) {
                nodo = nodo + recorrido.get(i).getNombre() + " (Linea Chilonzor)\n";
            }
            if (linea == 3) {
                nodo = nodo + recorrido.get(i).getNombre() + " (Linea Yunusabad)\n";
            }
        }

        ruta = ruta + "\n\nTiempo estimado: " + (int) tiempo + " minutos";
        nodestextarea.setText(nodo );
        routetextarea.setText(ruta );
    }


    public JFrame getTripFrame() {
        return tripframe;
    }
}