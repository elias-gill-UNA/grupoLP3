package ejercicio3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
// @Autor Elias Gill

public class App extends JFrame {

    TrayIcon trayIcon;
    SystemTray tray;

    App() {
        System.out.println(System.getProperty("user.dir"));
        VentanaCampoTexto textFieldFrame = new VentanaCampoTexto();
        textFieldFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textFieldFrame.setSize(450, 200);
        textFieldFrame.setVisible(true);
        textFieldFrame.setLocation(450, 200);

        System.out.println("Creando la instancia");

        // En esta sección se agrega el código que podría lanzar error
        // en tiempo de ejecución
        try {
            System.out.println("Configurando vista y funcionamiento");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // se captura el error
            System.out.println("No se pudo configurar");
        }

        // crear el nuevo systray si es que el SO lo permite
        if (SystemTray.isSupported()) {
            System.out.println("Bandeja de sistema soportada");

            // get the system tray and add an image to it
            tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit()
                    .getImage(System.getProperty("user.dir") + "/aguja.png");

            // crear el menu del tray
            PopupMenu popup = new PopupMenu();
            // asignar el boton de salir
            MenuItem defaultItem = new MenuItem("Salir");
            // nueva funcion para salir
            ActionListener exitListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Cerrando....");
                    System.exit(0);
                }
            };
            defaultItem.addActionListener(exitListener);
            popup.add(defaultItem);

            // asignar el boton de abrir
            defaultItem = new MenuItem("Abrir");
            // funcion de abrir ventana
            defaultItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    textFieldFrame.setVisible(true);
                    textFieldFrame.setExtendedState(JFrame.NORMAL);
                }
            });
            popup.add(defaultItem);

            // crear el icono del tray con autoSize
            trayIcon = new TrayIcon(image, "Servicio", popup);
            trayIcon.setImageAutoSize(true);

        } else {
            System.out.println("Bandeja de sistema no soportada");
        }

        // inicio del método para gestionar la bandeja de sistema
        textFieldFrame.addWindowStateListener(new WindowStateListener() {
            public void windowStateChanged(WindowEvent e) {
                if (e.getNewState() == ICONIFIED) {
                    try {
                        tray.add(trayIcon);
                        textFieldFrame.setVisible(false);
                        System.out.println("Agregando a bandeja de sistema");
                    } catch (AWTException ex) {
                        System.out.println("No se pudo agregar a la bandeja de sistema");
                    }
                }

                if (e.getNewState() == 7) {
                    try {
                        tray.add(trayIcon);
                        textFieldFrame.setVisible(false);
                        System.out.println("Agregado a la bandeja de sistema");
                    } catch (AWTException ex) {
                        System.out.println("No se puede agregar");
                    }
                }

                if (e.getNewState() == MAXIMIZED_BOTH) {
                    tray.remove(trayIcon);
                    textFieldFrame.setVisible(true);
                    System.out.println("Icono removido de la bandeja de sistema");
                }
                System.out.println(System.getProperty("user.dir"));
                if (e.getNewState() == NORMAL) {
                    tray.remove(trayIcon);
                    textFieldFrame.setVisible(true);
                    System.out.println("Icono removido");
                }
            }
        });

    }

    public static void main(String[] args) {
        new App();
    }
}
