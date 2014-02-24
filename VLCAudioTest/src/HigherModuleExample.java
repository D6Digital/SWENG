import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class HigherModuleExample {
    static JButton stopButton = new JButton();
    static JButton pauseButton = new JButton();
    static JButton playButton = new JButton();
    static JFrame mainFrame = new JFrame();
    static JPanel mainPanel = new JPanel();
    
    public static void main(String[] args) throws IOException {
        StandAloneMusicPlayer  musicPlayer = new StandAloneMusicPlayer();
        stopButton = musicPlayer.getStopButton();
        pauseButton = musicPlayer.getPauseButton();
        playButton = musicPlayer.getPlayButton();
        
        mainPanel.add(stopButton);
        mainFrame.add(mainPanel);
        mainPanel.add(pauseButton);
        mainFrame.add(mainPanel);
        mainPanel.add(playButton);
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
        mainFrame.validate();
       }
}
