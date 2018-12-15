package interfaz;

import metro.AEstrella;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Collections;

public class MenuInicio {

    public JFrame initmenuframe;
    private CalcularViaje calcviaje;
    private AEstrella astar;
    private Mapa mapui;
    private String origen;
    private String destino;

    private final String WINDOW_TITTLE_TXT = "Toshkent Metro — Tashkent";
    private final String LABEL_TITTLE_TXT = "Toshkent Metro";
    private final String MAP_BTN_TXT = "<p style=\"text-align:center;\">Mapa de<br>Metro</p>";
    private final String TRIP_BTN_TXT = "<p style=\"text-align:center;\">Nuevo<br>Viaje</p>";
    private final String _H = "<html>";
    private final String H_ = "</html>";

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MenuInicio initmenuui = new MenuInicio();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }

    public MenuInicio() {
        initmenuframe = new JFrame();
        mapui = new Mapa(this, astar);
        calcviaje = new CalcularViaje(this);
        initialize();
    }

    private void initialize() {
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
        initmenuframe.pack();
        initmenuframe.setFocusable(true);
        initmenuframe.setPreferredSize(new Dimension(700, 450));
        initmenuframe.setSize(initmenuframe.getPreferredSize()); // Tamano de la ventana
        initmenuframe.setLocationRelativeTo(null); // Centrar en pantalla
        initmenuframe.setResizable(false); // Para no poder cambiar tamano ventana
        initmenuframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Para cerrar correctamente
        initmenuframe.setTitle(WINDOW_TITTLE_TXT); // Titulo Ventana
        initmenuframe.setIconImage(new ImageIcon(getClass().getResource("/images/Logo.png")).getImage()); // Icono app

        initmenuframe.getContentPane().setPreferredSize(initmenuframe.getPreferredSize());
        initmenuframe.getContentPane().setSize(initmenuframe.getContentPane().getPreferredSize());
        initmenuframe.getContentPane().setLayout(null);

        //
        // Components
        //

        //
        // Body JPanel
        //
        JPanel bodypanel = new JPanel();
        initmenuframe.getContentPane().add(bodypanel);
        bodypanel.setLayout(new GridLayout(2, 1));
        bodypanel.setLocation(0, 0);
        bodypanel.setPreferredSize(new Dimension(initmenuframe.getWidth(), initmenuframe.getHeight()));
        bodypanel.setSize(bodypanel.getPreferredSize());
        bodypanel.setOpaque(false);

        //
        // Header JPanel
        //
        JPanel headpanel = new JPanel();
        bodypanel.add(headpanel);
        headpanel.setLayout(new GridLayout());
        headpanel.setPreferredSize(new Dimension(bodypanel.getWidth(), bodypanel.getHeight() / 2));
        headpanel.setSize(headpanel.getPreferredSize());
        headpanel.setOpaque(false);

        GridBagConstraints headergbc = new GridBagConstraints();
        headergbc.fill = GridBagConstraints.HORIZONTAL;
        headergbc.ipady = 10;
        headergbc.weightx = 0.0;
        headergbc.gridwidth = 3;
        headergbc.gridx = 0;
        headergbc.gridy = 1;

        //
        // Header Tittle
        //
        JLabel tittle = new JLabel(LABEL_TITTLE_TXT);
        headpanel.add(tittle, headergbc);
        tittle.setForeground(new Color(0, 42, 255)); // Tashkent Metro Logo Blue
        tittle.setFont(new Font("Arial", Font.BOLD, 50));
        tittle.setHorizontalAlignment(JLabel.CENTER);
        tittle.setHorizontalTextPosition(JLabel.CENTER);

        //
        // Footer JPanel
        //
        JPanel footpanel = new JPanel();
        bodypanel.add(footpanel);
        footpanel.setPreferredSize(new Dimension(bodypanel.getWidth(), bodypanel.getHeight() / 2));
        footpanel.setSize(footpanel.getPreferredSize());
        footpanel.setOpaque(false);

        //
        // Buttons Wrapper JPanel
        //
        JPanel btnswrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        footpanel.add(btnswrapper);
        btnswrapper.setOpaque(false);

        //
        // Metro Map Button
        //
        JButton mapbtn = new JButton(_H + MAP_BTN_TXT + H_);
        btnswrapper.add(mapbtn);
        mapbtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mapui.getMapuiframe().setVisible(true);
                initmenuframe.setVisible(false);
            }

            Color c = null;
            Font f = null;
            Cursor r = null;

            @Override
            public void mouseEntered(MouseEvent e) {
                JButton btn = (JButton) e.getSource();
                c = btn.getForeground();
                f = btn.getFont();
                btn.setForeground(new Color(0, 0, 0));
                btn.setFont(f.deriveFont(Collections.singletonMap(
                        TextAttribute.WEIGHT, TextAttribute.WEIGHT_SEMIBOLD)));
                btn.setFont(f.deriveFont(Collections.singletonMap(
                        TextAttribute.SIZE, f.getSize() + 1)));
                r = btn.getCursor();
                btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btn.requestFocus();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton btn = (JButton) e.getSource();
                btn.setForeground(c);
                btn.setFont(f);
                btn.setCursor(r);
                initmenuframe.requestFocus();
            }
        });

        mapbtn.setForeground(new Color(28, 28, 28));
        mapbtn.setFont(new Font("Arial", Font.TRUETYPE_FONT, 19));
        mapbtn.setPreferredSize(new Dimension((int) (footpanel.getWidth() * 0.40), 100));
        mapbtn.setOpaque(false);
        mapbtn.setHorizontalTextPosition(SwingConstants.CENTER);
        mapbtn.setVerticalTextPosition(SwingConstants.CENTER);

        //
        // Trip Button
        //
        JButton tripbtn = new JButton(_H + TRIP_BTN_TXT + H_);
        btnswrapper.add(tripbtn);
        tripbtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                calcviaje.getOriginlist().setSelectedIndex(0);
                calcviaje.getDestinationlist().setSelectedIndex(0);
                calcviaje.getCalctripframe().setVisible(true);
                initmenuframe.setVisible(false);
            }

            Color c = null;
            Font f = null;
            Cursor r = null;

            @Override
            public void mouseEntered(MouseEvent e) {
                JButton btn = (JButton) e.getSource();
                c = btn.getForeground();
                f = btn.getFont();
                btn.setForeground(new Color(0, 0, 0));
                btn.setFont(f.deriveFont(Collections.singletonMap(
                        TextAttribute.WEIGHT, TextAttribute.WEIGHT_SEMIBOLD)));
                btn.setFont(f.deriveFont(Collections.singletonMap(
                        TextAttribute.SIZE, f.getSize() + 1)));
                r = btn.getCursor();
                btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btn.requestFocus();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton btn = (JButton) e.getSource();
                btn.setForeground(c);
                btn.setFont(f);
                btn.setCursor(r);
                initmenuframe.requestFocus();
            }
        });

        tripbtn.setForeground(new Color(28, 28, 28));
        tripbtn.setFont(new Font("Arial", Font.TRUETYPE_FONT, 19));
        tripbtn.setPreferredSize(new Dimension((int) (footpanel.getWidth() * 0.40), 100));
        tripbtn.setOpaque(false);
        tripbtn.setHorizontalTextPosition(SwingConstants.CENTER);
        tripbtn.setVerticalTextPosition(SwingConstants.CENTER);

        //
        // Background Image
        //
        JLabel backgroundimg = new JLabel("");
        initmenuframe.getContentPane().add(backgroundimg);
        backgroundimg.setIcon(new ImageIcon(MenuInicio.class.getResource("/images/Metro.jpg")));
        backgroundimg.setLocation(0, 0);
        backgroundimg.setPreferredSize(initmenuframe.getSize());
        backgroundimg.setSize(backgroundimg.getPreferredSize());
        backgroundimg.setOpaque(false);

        //
        // Events
        //
        initmenuframe.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                initmenuframe.requestFocus();
            }
        });

        initmenuframe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                initmenuframe.requestFocus();
            }
        });

        initmenuframe.setVisible(true);
    }

    public JFrame getInitmenuframe() {
        return initmenuframe;
    }

    public CalcularViaje getCalcviaje() {
        return calcviaje;
    }

    public AEstrella getAstar() {
        return astar;
    }

    public Mapa getMapui() {
        return mapui;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }
}

