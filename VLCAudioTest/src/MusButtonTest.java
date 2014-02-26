import static org.junit.Assert.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * T001- A test to check that the music player interface is returned correctly.
 * @author Joshua Lant
 *
 */
public class MusButtonTest {
    static JButton 
    stopButton, playButton, pauseButton, nextButton, previousButton, openPlaylistButton 
    = new JButton();
    static JSlider volumeSlider = new JSlider();
    static JFrame mainFrame = new JFrame();
    static JPanel mainPanel = new JPanel();
    StandAloneMusicPlayer  musicPlayer;
    
    
    @Before
    public void setUp() throws Exception {
        musicPlayer = new StandAloneMusicPlayer();
        stopButton = musicPlayer.getStopButton();
        pauseButton = musicPlayer.getPauseButton();
        playButton = musicPlayer.getPlayButton();
        nextButton = musicPlayer.getNextButton();
        previousButton = musicPlayer.getPreviousButton();
        openPlaylistButton = musicPlayer.getOpenPlaylistButton();
        volumeSlider = musicPlayer.getVolumeSlider();
        mainPanel.add(stopButton);
        mainPanel.add(pauseButton);
        mainPanel.add(playButton);
        mainPanel.add(nextButton);
        mainPanel.add(previousButton);
        mainPanel.add(openPlaylistButton);
        mainPanel.add(volumeSlider);
        mainFrame.setSize(200, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
        mainFrame.validate(); 
    }

    @SuppressWarnings("deprecation")
    @Test
    public void steps1to7() {

        // Step 1. A correctly labelled play button is retrieved from  class StandAloneMusicPlayer.        
        assertEquals("The play button was not retrieved correctly",
                true, playButton.isDisplayable());
        assertEquals("The play button was not retrieved correctly",
                true, playButton.isEnabled());
        assertEquals("The play button was not retrieved correctly, it was: " + playButton.getLabel(),
                "Play", playButton.getLabel());
        
        // Step 2. A correctly labelled stop button is retrieved from  class StandAloneMusicPlayer.
        assertEquals("The stop button was not retrieved correctly",
                true, stopButton.isDisplayable());
        assertEquals("The stop button was not retrieved correctly",
                true, stopButton.isEnabled());
        assertEquals("The stop button was not retrieved correctly, it was: " + stopButton.getLabel(),
                "Stop", stopButton.getLabel());
        
        // Step 3. A correctly labelled pause button is retrieved from  class StandAloneMusicPlayer.
        assertEquals("The pause button was not retrieved correctly",
                true, pauseButton.isDisplayable());
        assertEquals("The pause button was not retrieved correctly",
                true, pauseButton.isEnabled());
        assertEquals("The pause button was not retrieved correctly, it was: " + pauseButton.getLabel(),
                "Pause", pauseButton.getLabel());
        
        // Step 4. A correctly labelled next button is retrieved from  class StandAloneMusicPlayer.
        assertEquals("The next button was not retrieved correctly",
                true, nextButton.isDisplayable());
        assertEquals("The next button was not retrieved correctly",
                true, nextButton.isEnabled());
        assertEquals("The next button was not retrieved correctly, it was: " + nextButton.getLabel(),
                "Next", nextButton.getLabel());
        
        // Step 5. A correctly labelled previous button is retrieved from  class StandAloneMusicPlayer.
        assertEquals("The previous button was not retrieved correctly",
                true, previousButton.isDisplayable());
        assertEquals("The previous button was not retrieved correctly",
                true, previousButton.isEnabled());
        assertEquals("The previous button was not retrieved correctly, it was: " + previousButton.getLabel(),
                "Previous", previousButton.getLabel());
        
        // Step 6. A correctly labelled play button is retrieved from  class StandAloneMusicPlayer.
        assertEquals("The volumeSlider was not retrieved correctly",
                true, volumeSlider.isDisplayable());
        assertEquals("The volumeSlider was not retrieved correctly",
                true, volumeSlider.isEnabled());
        assertEquals("The max volume for volumeSlider was not correct, it was: " + volumeSlider.getMaximum(),
                100, volumeSlider.getMaximum());
        assertEquals("The min volume for volumeSlider was not correct, it was: " + volumeSlider.getMinimum(),
                0, volumeSlider.getMinimum());
        
        // Step 7. A correctly labelled open playlist button is retrieved from  class StandAloneMusicPlayer.
        assertEquals("The openPlaylist button was not retrieved correctly",
                true, openPlaylistButton.isDisplayable());
        assertEquals("The openPlaylist button was not retrieved correctly",
                true, openPlaylistButton.isEnabled());
        assertEquals("The openPlaylist button was not retrieved correctly, it was: " + openPlaylistButton.getLabel(),
                "Open Playlist", openPlaylistButton.getLabel());
        
    }

    @After
    public void tearDown() throws Exception {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.dispose();
    }
}
