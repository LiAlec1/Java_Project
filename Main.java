import javax.swing.*;

public class Main {
    public static void main(String args[]) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Game Name");

        GamePanel panel = new GamePanel();
        window.add(panel);

        window.pack();

        window.setLocationRelativeTo(null); // Set window location to center
        window.setVisible(true);

        panel.startGameThread();
    }
}