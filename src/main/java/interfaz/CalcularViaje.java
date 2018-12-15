package interfaz;

import metro.AEstrella;
import metro.ListaParadas;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CalcularViaje {

    private MenuInicio initmenuui;
    private JFrame calctripframe;
    private AEstrella astar;
    private Viaje tripui;
    private String origen;
    private String destino;
    private JList<String> originlist;
    private JList<String> destinationlist;

    private final String WINDOW_TITTLE_TXT = "Toshkent Metro — Tashkent";
    private final String _H = "<html>";
    private final String H_ = "</html>";

    public CalcularViaje(MenuInicio initmenuui) {
        this.initmenuui = initmenuui;
        this.calctripframe = new JFrame();
        this.astar = new AEstrella();
        this.tripui = new Viaje(initmenuui, this, astar);
        this.origen = "";
        this.destino = "";
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
        calctripframe.pack();
        calctripframe.setFocusable(true);
        calctripframe.setPreferredSize(new Dimension(780, 580));
        calctripframe.setSize(calctripframe.getPreferredSize()); // Tamano de la ventana
        calctripframe.setLocationRelativeTo(null); // Centrar en pantalla
        calctripframe.setResizable(false); // Para no poder cambiar tamano ventana
        calctripframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Para cerrar correctamente
        calctripframe.setTitle(WINDOW_TITTLE_TXT); // Titulo Ventana
        calctripframe.setIconImage(new ImageIcon(getClass().getResource("/images/Logo.png")).getImage()); // Icono app

        calctripframe.getContentPane().setPreferredSize(calctripframe.getPreferredSize());
        calctripframe.getContentPane().setSize(calctripframe.getContentPane().getPreferredSize());
        calctripframe.getContentPane().setLayout(null);

        //
        // Components
        //

        //
        // Body Panel
        //
        JSplitPane bodysplitpane = new JSplitPane();
        calctripframe.getContentPane().add(bodysplitpane);
        bodysplitpane.setLocation(0, 0);
        bodysplitpane.setOpaque(false);
        bodysplitpane.setPreferredSize(bodysplitpane.getParent().getPreferredSize());
        bodysplitpane.setSize(bodysplitpane.getPreferredSize());
        bodysplitpane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        bodysplitpane.setDividerLocation(0.08);
        bodysplitpane.setOneTouchExpandable(false);
        bodysplitpane.setContinuousLayout(true);

        BasicSplitPaneUI ui1 = (BasicSplitPaneUI) bodysplitpane.getUI();
        BasicSplitPaneDivider divider1 = ui1.getDivider();
        divider1.setVisible(false);

        //
        // Top panel
        //
        JPanel toppanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bodysplitpane.setTopComponent(toppanel);
        toppanel.setPreferredSize(new Dimension(toppanel.getParent().getWidth(), (int) (toppanel.getParent().getHeight() * 0.08)));
        toppanel.setSize(toppanel.getPreferredSize());
        toppanel.setOpaque(false);

        //
        // Home button
        //
        JButton homebtn = new JButton("");
        toppanel.add(homebtn);
        homebtn.setHorizontalTextPosition(JButton.CENTER);
        homebtn.setOpaque(false);
        homebtn.setBorderPainted(false);
        homebtn.setFocusPainted(false);
        homebtn.setContentAreaFilled(false);
        homebtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                calctripframe.setVisible(false);
                initmenuui.initmenuframe.setVisible(true);
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
        JSplitPane bottonsplitpane = new JSplitPane();
        bodysplitpane.setBottomComponent(bottonsplitpane);
        bottonsplitpane.setLocation(0, 0);
        bottonsplitpane.setOpaque(false);
        bottonsplitpane.setSize(bodysplitpane.getWidth(), (int) (bodysplitpane.getHeight() * 0.92));
        bottonsplitpane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        bottonsplitpane.setDividerLocation(0.75);
        bottonsplitpane.setOneTouchExpandable(false);
        bottonsplitpane.setContinuousLayout(true);

        BasicSplitPaneUI ui2 = (BasicSplitPaneUI) bottonsplitpane.getUI();
        BasicSplitPaneDivider divider2 = ui2.getDivider();
        divider2.setVisible(false);

        //
        // Groups Container
        //
        JPanel groupscontainer = new JPanel();
        bottonsplitpane.setTopComponent(groupscontainer);
        groupscontainer.setPreferredSize(new Dimension(bottonsplitpane.getWidth(), (int) (bottonsplitpane.getHeight() * 0.75)));
        groupscontainer.setSize(groupscontainer.getPreferredSize());
        groupscontainer.setOpaque(false);

        GroupLayout gl = new GroupLayout(groupscontainer);
        groupscontainer.setLayout(gl);

        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        //
        // Scroll Panes JLabels
        //
        JLabel originjlabel = new JLabel("Origen");
        originjlabel.setOpaque(false);
        originjlabel.setForeground(new Color(0, 0, 0));
        originjlabel.setFont(new Font("ARIAL", Font.CENTER_BASELINE, 20));
        originjlabel.setHorizontalTextPosition(SwingConstants.CENTER);
        originjlabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel destinationjlabel = new JLabel("Destino");
        destinationjlabel.setOpaque(false);
        destinationjlabel.setForeground(new Color(0, 0, 0));
        destinationjlabel.setFont(new Font("ARIAL", Font.CENTER_BASELINE, 20));
        destinationjlabel.setHorizontalTextPosition(SwingConstants.CENTER);
        destinationjlabel.setHorizontalAlignment(SwingConstants.CENTER);

        //
        // Scroll Panes
        //
        ArrayList<String> stations = ListaParadas.estacionesOrdenadas();
        final int longitud = stations.size();

        // SP1
        JScrollPane originscrollpane = new JScrollPane();
        originscrollpane.setOpaque(false);

        DefaultListModel<String> origindlm = new DefaultListModel<String>();
        originlist = new JList<String>(origindlm);
        originscrollpane.setViewportView(originlist);

        for (int i = 0; i < longitud; ++i) {
            origindlm.addElement(stations.get(i));
        }

        originlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        originlist.setSelectedIndex(0);

        // SP2
        JScrollPane destinationscrollpane = new JScrollPane();
        destinationscrollpane.setOpaque(false);

        DefaultListModel<String> destiantiondlm = new DefaultListModel<String>();
        destinationlist = new JList<String>(destiantiondlm);
        destinationscrollpane.setViewportView(destinationlist);

        for (int i = 0; i < longitud; ++i) {
            destiantiondlm.addElement(stations.get(i));
        }

        destinationlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        destinationlist.setSelectedIndex(0);

        // Groups Layout
        // H1  H2
        // jl1 jl2 V1
        // sp1 sp2 V2
        gl.setHorizontalGroup(
                gl.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                (int) (bottonsplitpane.getWidth() * 0.165),
                                (int) (bottonsplitpane.getWidth() * 0.165))
                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(originjlabel)
                                .addComponent(originscrollpane)
                        )
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE + 30)
                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(destinationjlabel)
                                .addComponent(destinationscrollpane)
                        )
        );
        gl.setVerticalGroup(
                gl.createSequentialGroup()
                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(originjlabel)
                                .addComponent(destinationjlabel)
                        )
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(originscrollpane)
                                .addComponent(destinationscrollpane)
                        ).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        );

        gl.linkSize(SwingConstants.HORIZONTAL, originjlabel, destinationjlabel);
        gl.linkSize(SwingConstants.HORIZONTAL, originscrollpane, destinationscrollpane);
        gl.linkSize(SwingConstants.HORIZONTAL, originjlabel, originscrollpane);
        gl.linkSize(SwingConstants.HORIZONTAL, destinationjlabel, destinationscrollpane);

        //
        // Next Button Panel
        //
        JPanel nextbtnpanel = new JPanel();
        bottonsplitpane.setBottomComponent(nextbtnpanel);
        nextbtnpanel.setSize(bottonsplitpane.getWidth(), (int) (bottonsplitpane.getHeight() * 0.25));
        nextbtnpanel.setPreferredSize(nextbtnpanel.getSize());
        nextbtnpanel.setOpaque(false);

        //
        // Next Button
        //
        JButton nextbtn = new JButton("Siguiente");
        nextbtnpanel.add(nextbtn);
        nextbtn.setOpaque(false);
        nextbtn.setEnabled(false);
        nextbtn.setSize(300, 200);
        nextbtn.setMargin(new Insets(10, 15, 10, 15));
        nextbtn.setHorizontalTextPosition(JButton.CENTER);
        nextbtn.setFont(new Font("Arial", Font.BOLD, 15));
        nextbtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                calctripframe.setVisible(false);
                tripui.getTripFrame().setVisible(true);
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

        //
        // Trip Metro Image JLabel
        //
        JLabel tripimgjl = new JLabel("");
        calctripframe.getContentPane().add(tripimgjl);
        tripimgjl.setLocation(0, 0);
        tripimgjl.setSize(calctripframe.getContentPane().getWidth(), calctripframe.getContentPane().getHeight());
        tripimgjl.setHorizontalAlignment(SwingConstants.CENTER);

        ImageIcon imgicon = new ImageIcon(Mapa.class.getResource("/images/Viaje.jpg"));
        tripimgjl.setIcon(imgicon);

        //
        // Events
        //
        originlist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                origen = originlist.getSelectedValue();
                nextbtn.setEnabled(!origen.equals("") && !destino.equals("")
                        && !origen.equals(destino));
            }
        });

        originlist.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (originlist.hasFocus()
                        && e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    origen = originlist.getSelectedValue();
                    nextbtn.setEnabled(!origen.equals("") && !destino.equals("") && !origen.equals(destino));
                }
            }
        });

        destinationlist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                destino = destinationlist.getSelectedValue();
                nextbtn.setEnabled(!origen.equals("") && !destino.equals("")
                        && !origen.equals(destino));
            }
        });

        destinationlist.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (destinationlist.hasFocus()
                        && e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    destino = destinationlist.getSelectedValue();
                    nextbtn.setEnabled(!origen.equals("") && !destino.equals("") && !origen.equals(destino));
                }
            }
        });


        calctripframe.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                origen = originlist.getSelectedValue();
                destino = destinationlist.getSelectedValue();
            }
        });
    }

    public MenuInicio getInitmenuui() {
        return initmenuui;
    }

    public JFrame getCalctripframe() {
        return calctripframe;
    }

    public AEstrella getAstar() {
        return astar;
    }

    public Viaje getTripui() {
        return tripui;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setInitmenuui(MenuInicio initmenuui) {
        this.initmenuui = initmenuui;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public JList<String> getOriginlist() {
        return originlist;
    }

    public JList<String> getDestinationlist() {
        return destinationlist;
    }
}