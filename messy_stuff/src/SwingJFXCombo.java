import java.awt.Color;
import java.awt.Container;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.*;
import javafx.scene.text.*;
import javax.swing.*;

public class SwingJFXCombo {

    public static Container initialize(){

        final JFXPanel fxPanel = new JFXPanel();
        JPanel jp = new JPanel();

        jp.add(fxPanel);
        jp.setVisible(true);
        // Really shouldn't do this, so commented it out
        //jp.setPreferredSize(new Dimension(500, 300));
        jp.setBackground(Color.CYAN);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(fxPanel);
            }
        });

        return jp;
    }

    private static void initFX(JFXPanel fxPanel) {
        Scene scene = initScene();
        fxPanel.setScene(scene);
    }

    private static Scene initScene(){
        Group  root  =  new  Group();
        Scene  scene  =  new  Scene(root, javafx.scene.paint.Color.ALICEBLUE );
        Text  text  =  new  Text();
        text.setX(40);
        text.setY(100);
        text.setFont(new Font(25));
        text.setText("Welcome JavaFX!");
        root.getChildren().add(text);
        return (scene);
    }

    public static void main(String[] args){
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(SwingJFXCombo.initialize());
        frame.pack();
    }
}