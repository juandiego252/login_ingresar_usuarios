import javax.swing.*;
import  usuarios.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.ArrayList;

public class loginScrenn {
    private JPanel LoginScreen;
    private JLabel texto1;
    private JPasswordField usernameTextField;
    private JButton cleanButton;
    private JButton loginButton;
    private JLabel text2;
    private JLabel text3;
    private JPasswordField passwordField;
    private JTextField textField1;
    public List<personas> listapersonas;

    public loginScrenn() {
        listapersonas = cargarUsuariosDesdeArchivo();
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre_usuario = textField1.getText();
                String contrasena = new String(passwordField.getPassword());
                boolean credencialesOk = verificarUsuarios(nombre_usuario, contrasena);
                if (credencialesOk) {
                    showWelcomeScreen(nombre_usuario);
                } else {
                    JOptionPane.showMessageDialog(LoginScreen, "Credenciales incorrectas", "Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                passwordField.setText("");
            }
        });
    }

    public List<personas> cargarUsuariosDesdeArchivo() {
        List<personas> usuarios = new ArrayList<>();

        try (FileInputStream fileIn = new FileInputStream("usuarios.dat");
             ObjectInputStream obIn = new ObjectInputStream(fileIn)) {

            usuarios = (List<personas>) obIn.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(LoginScreen, "Error al cargar los usuarios", "Cargar Usuarios", JOptionPane.ERROR_MESSAGE);
        }
        return usuarios;
    }
    private boolean verificarUsuarios (String nombreUsuario, String contrasena){
        for (personas usuarios: listapersonas){
            if (usuarios.getNombre().equals(nombreUsuario) && usuarios.getContrasena().equals(contrasena)){
                return true;
            }
        }
        return false;
    }

    private void showWelcomeScreen(String nombreUsuario) {
        new WelcomeScreen(nombreUsuario);
    }

    public void setListapersonas(List<personas>usuarios){
        this.listapersonas = usuarios;
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("loginScrenn");
        frame.setContentPane(new loginScrenn().LoginScreen);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

