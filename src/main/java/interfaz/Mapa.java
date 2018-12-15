package interfaz;

import metro.AEstrella;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Mapa {

    protected JFrame mapuiframe;
    private final MenuInicio initmenuui;

    private final String WINDOW_TITTLE_TXT = "Toshkent Metro — Tashkent";
    private final String _H = "<html>";
    private final String H_ = "</html>";

    public Mapa(MenuInicio initmenuui, AEstrella servicios) {
        this.mapuiframe = new JFrame();
        this.initmenuui = initmenuui;
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
        mapuiframe.pack();
        mapuiframe.setFocusable(true);
        mapuiframe.setPreferredSize(new Dimension(775, 550));
        mapuiframe.setSize(mapuiframe.getPreferredSize()); // Tamano de la ventana
        mapuiframe.setLocationRelativeTo(null); // Centrar en pantalla
        mapuiframe.setResizable(false); // Para no poder cambiar tamano ventana
        mapuiframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Para cerrar correctamente
        mapuiframe.setTitle(WINDOW_TITTLE_TXT); // Titulo Ventana
        mapuiframe.setIconImage(new ImageIcon(getClass().getResource("/images/Logo.png")).getImage()); // Icono app

        mapuiframe.getContentPane().setPreferredSize(mapuiframe.getPreferredSize());
        mapuiframe.getContentPane().setSize(mapuiframe.getContentPane().getPreferredSize());
        mapuiframe.getContentPane().setLayout(new GridLayout());
        mapuiframe.getContentPane().setBackground(Color.WHITE);

        //
        // Components
        //

        //
        // Main panel
        //
        JSplitPane mapuimainpanel = new JSplitPane();
        mapuiframe.getContentPane().add(mapuimainpanel);
        mapuimainpanel.setOpaque(false);
        mapuimainpanel.setPreferredSize(mapuiframe.getContentPane().getSize());
        mapuimainpanel.setSize(mapuimainpanel.getPreferredSize());
        mapuimainpanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
        mapuimainpanel.setDividerLocation(0.1);
        mapuimainpanel.setOneTouchExpandable(false);
        mapuimainpanel.setContinuousLayout(true);

        BasicSplitPaneUI ui = (BasicSplitPaneUI) mapuimainpanel.getUI();
        BasicSplitPaneDivider divider = ui.getDivider();
        divider.setVisible(false);

        //
        // Header panel
        //
        JPanel headerpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        headerpanel.setPreferredSize(new Dimension(mapuimainpanel.getWidth(), (int) (mapuiframe.getContentPane().getHeight() * 0.08)));
        headerpanel.setSize(headerpanel.getPreferredSize());
        headerpanel.setOpaque(false);

        //
        // Home button
        //
        JButton homebtn = new JButton("");
        headerpanel.add(homebtn);
        homebtn.setHorizontalTextPosition(JButton.CENTER);
        homebtn.setOpaque(false);
        homebtn.setBorderPainted(false);
        homebtn.setFocusPainted(false);
        homebtn.setContentAreaFilled(false);
        homebtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mapuiframe.setVisible(false);
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
        // Image Botton Panel
        //
        JPanel bottonpanel = new JPanel(new GridLayout());
        bottonpanel.setPreferredSize(new Dimension(mapuimainpanel.getWidth(), (int) (mapuimainpanel.getHeight() * 0.92)));
        bottonpanel.setSize(bottonpanel.getPreferredSize());
        bottonpanel.setOpaque(false);

        mapuimainpanel.setBottomComponent(bottonpanel);
        mapuimainpanel.setTopComponent(headerpanel);

        //
        // Map Metro Image JLabel
        //
        JLabel mapimgjl = new JLabel("");
        bottonpanel.add(mapimgjl);
        mapimgjl.setHorizontalAlignment(SwingConstants.CENTER);

        ImageIcon imgicon = new ImageIcon(Mapa.class.getResource("/images/MapaMetro.png"));
        mapimgjl.setIcon(imgicon);

        //
        // Events
        //
        mapuiframe.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mapuiframe.setVisible(false);
                initmenuui.getInitmenuframe().setVisible(true);
            }
        });
    }

    public JFrame getMapuiframe() {
        return mapuiframe;
    }

    public MenuInicio getInitmenuui() {
        return initmenuui;
    }
}