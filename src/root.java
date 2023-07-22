import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import usuarios.*;


public class root {
    private JPanel root;
    private JTextField nombreText;
    private JTextField contraText;
    private JButton GUARDARButton;
    private JPasswordField passwordField;

    private List <personas> listadopersonas;

    public root() {
        listadopersonas = new ArrayList<>();
        GUARDARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreText.getText();
                String contrasena = new String(passwordField.getPassword());

                personas datos_usuarios = new personas(nombre,contrasena);
                listadopersonas.add(datos_usuarios);

                try {
                    FileOutputStream fileObj = new FileOutputStream("usuarios.dat");
                    ObjectOutputStream obOut = new ObjectOutputStream(fileObj);

                    obOut.writeObject(listadopersonas);

                    obOut.close();
                    fileObj.close();

                    JOptionPane.showMessageDialog(root,"Datos Guardados correctamente","Guardar Datos",JOptionPane.INFORMATION_MESSAGE);
                    nombreText.setText("");
                    passwordField.setText("");

                }  catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(root,"Error al guardar los datos","cargar datos", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("root");
        frame.setContentPane(new root().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        root rootApp = new root();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                loginScrenn loginScrenn = new loginScrenn();
                loginScrenn.setListapersonas(rootApp.listadopersonas);
            }
        });
    }
}
