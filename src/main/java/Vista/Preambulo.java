package Vista;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Preambulo extends JFrame {
    private JMenuBar menuBar;
    private JMenu menuOptions;
    private JMenuItem menuItemUser;
    private JMenuItem menuItemDoctor;
    private JMenuItem menuItemAdmin;

    private JPanel panelPrincipal;
    private JLabel lbTitulo;
    private JLabel lbSubtitulo;

    public Preambulo() {
        setTitle("Fleming");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        menuBar = new JMenuBar();
        menuOptions = new JMenu("Opciones");

        menuItemUser = new JMenuItem("Usuario");
        menuItemDoctor = new JMenuItem("Doctor");
        menuItemAdmin = new JMenuItem("Administrador");

        menuItemUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login_User loginUser = new Login_User();
                loginUser.setExtendedState(JFrame.MAXIMIZED_BOTH);
                loginUser.setDefaultCloseOperation(EXIT_ON_CLOSE);
                loginUser.setVisible(true);
                setVisible(false);
            }
        });

        menuItemDoctor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login_Doctor loginDoc = new Login_Doctor();
                loginDoc.setExtendedState(JFrame.MAXIMIZED_BOTH);
                loginDoc.setDefaultCloseOperation(EXIT_ON_CLOSE);
                loginDoc.setVisible(true);
                setVisible(false);
            }
        });

        menuItemAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login_Admin loginAdmin = new Login_Admin();
                loginAdmin.setExtendedState(JFrame.MAXIMIZED_BOTH);
                loginAdmin.setDefaultCloseOperation(EXIT_ON_CLOSE);
                loginAdmin.setVisible(true);
                setVisible(false);
            }
        });

        menuOptions.add(menuItemUser);
        menuOptions.add(menuItemDoctor);
        menuOptions.add(menuItemAdmin);

        menuBar.add(menuOptions);

        setJMenuBar(menuBar);

        panelPrincipal = new JPanel();
        lbTitulo = new JLabel("F L E M I N G");
        lbSubtitulo = new JLabel("Conectándote con los mejores profesionales");

        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        lbTitulo.setAlignmentX(CENTER_ALIGNMENT);
        lbSubtitulo.setAlignmentX(CENTER_ALIGNMENT);

        panelPrincipal.add(Box.createVerticalStrut(50));
        panelPrincipal.add(lbTitulo);
        panelPrincipal.add(Box.createVerticalStrut(20));
        panelPrincipal.add(lbSubtitulo);

        add(panelPrincipal);
    }
}