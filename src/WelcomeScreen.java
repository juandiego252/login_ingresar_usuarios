import javax.swing.*;
import java.awt.*;

public class WelcomeScreen {
    private JPanel WelcomeScreen;
    private JLabel welcomelabel;

    public WelcomeScreen(String nombreUsuario) {
        JFrame frame = new JFrame("Bienvenid@");
        WelcomeScreen = new JPanel(new BorderLayout());
        welcomelabel = new JLabel("Bienvenid@: " + nombreUsuario);

        welcomelabel.setFont(new Font("Arial", Font.PLAIN, 20));
        WelcomeScreen.add(welcomelabel, BorderLayout.CENTER);

        frame.setContentPane(WelcomeScreen);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
