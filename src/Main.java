import javax.swing.*;
import java.awt.*;

public class Main {
    public static void openFrame(JFrame frame) {
        frame.setVisible(true);
    }

    public static void closeFrame(JFrame frame) {
        frame.setVisible(false);
    }

    public static void main(String[] args) {
        JFrame mainMenuFrame = new JFrame();
        mainMenuFrame.setBounds(10,10,710,600);
        GridLayout mainMenuLayout = new GridLayout(2,1);
        mainMenuFrame.setLayout(mainMenuLayout);
        JTextField mainMenuText = new JTextField("Witaj Użytkowniku!" +
                "\nWybierz grę:");
        mainMenuFrame.add(mainMenuText);

        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(mainMenuLayout);

        mainMenuFrame.add(mainMenuPanel);

        JButton brickBreakerButton = new JButton("BRICK BREAKER");

        mainMenuPanel.add(brickBreakerButton);
        openFrame(mainMenuFrame);

        brickBreakerButton.addActionListener(e -> {
            closeFrame(mainMenuFrame);

            JFrame obj = new JFrame();
            GamePlay gamePlay = new GamePlay(2);
            obj.setBounds(10,10,710,600);
            obj.setTitle("Brick Breaker");
            obj.setResizable(false);
            openFrame(obj);
            obj.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            obj.add(gamePlay);
        });




    }
}