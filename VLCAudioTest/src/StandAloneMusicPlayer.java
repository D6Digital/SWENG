import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.ListModel;
import javax.swing.Painter;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;
import uk.co.caprica.vlcj.player.list.MediaListPlayer;
import uk.co.caprica.vlcj.player.list.MediaListPlayerEventAdapter;
import uk.co.caprica.vlcj.player.list.MediaListPlayerMode;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.test.basic.PlayerControlsPanel;
import uk.co.caprica.vlcj.test.list.TestMediaListEmbeddedPlayer;

//import uk.co.caprica.vlcj.player.list.MediaList;
import uk.co.caprica.vlcj.medialist.MediaList;

/**
 * @author Josh Lant
 *
 */
public class StandAloneMusicPlayer {
	static String vlcLibraryPath = "H:\\Users\\Dr. Gabber\\Desktop\\Work Programs\\eclipse-jee-juno-SR2-win32-x86_64\\eclipse\\SWENG\\VLC\\vlc-2.1.3";
	//static String vlcLibraryPath = "C:\\xtemp\\SWENG\\VLC\\vlc-2.1.3";
	//static String vlcLibraryPath = "C:\\xtemp\\SWENG\\VLC\\vlc-2.0.1";
	static DefaultListModel listModel = new DefaultListModel<String>();
    static JFrame mainFrame = new JFrame("mainFrame");
    static JFrame playlistFrame = new JFrame("playlistFrame");
    static JPanel playPanel = new JPanel();
    static JList playContents = new JList(listModel);
    static Container contentPane;
    static EmbeddedMediaPlayer mediaPlayer;
    static MediaList mediaList;
    static MediaListPlayer mediaListPlayer;
    private static final long serialVersionUID = 1L;
    static String currentPlayIndex;
    static String currentFilePath = "H:\\Users\\Dr. Gabber\\Desktop\\Work Programs\\eclipse-jee-juno-SR2-win32-x86_64\\eclipse\\SWENG\\VLCAudioTest\\Playlist";
   // static String currentFilePath = "C:\\xtemp\\SWENG\\VLCAudioTest\\Playlist";
    //static String currentFilePath = "M:\\Year 2\\Engineering for Hearing and Voice\\Lab 1- Week 3\\Audio Samples";
    static String newFilePath = currentFilePath;
    static FileChooser fileChooser = new FileChooser(newFilePath);
    static Boolean isPaused = false;

    /**
     * Constructor for 
     */
    public StandAloneMusicPlayer() {
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),vlcLibraryPath);
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        ArrayList<String> files = getFilenames(currentFilePath);
        setupGUI(files);
        mainFrame.repaint();
        musicThread.start();
    }
    
    Thread musicThread = new Thread("Socket") {
        public void run() {
                while (true) {
                    musicPlayerLoop();                      
            }  
        }
    };
   
    private void musicPlayerLoop() {
        String newPlayIndex = (String) playContents.getSelectedValue();
        chooseFromPlaylist(newFilePath, newPlayIndex);
        chooseNewPlaylist();
    }
    
    
    /**
     * 
     */
    private void chooseNewPlaylist() {
    	newFilePath = fileChooser.getNewFilePath();
    	if((currentFilePath != newFilePath)  && (newFilePath != null)) {
    		ArrayList<String> files = getFilenames(newFilePath);
    		createList(files);
    		currentFilePath = newFilePath;
    		System.out.println("size is " + mediaList.size());
    		//for(int i = 0; i < mediaList.size(); i++) {
    		mediaList.clear();
    		
    		//}
    		String[] options = {};
    		for(String filename : files) {
    		mediaList.addMedia(newFilePath + "\\" + filename, options);	
    		}
    		mediaListPlayer.setMediaList(mediaList);
    		
    	}
	}

    
	/**
	 * @param newFilePath
	 * @param newPlayIndex
	 */
	private void chooseFromPlaylist(String newFilePath, String newPlayIndex) {
//    	if(newPlayIndex != currentPlayIndex) {
//    		String media = newFilePath + "\\" + newPlayIndex;
//    		System.out.println(media);
//    		mediaPlayer.playMedia(media);
//    		currentPlayIndex = newPlayIndex;
//    	}
	}
    
    private void stopMedia() {
    	mediaPlayer.stop();
    }
    
    private JButton setupListenerAndAction(JButton buttonName, final String playerMethodCaseName) {
        
        buttonName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                switch (playerMethodCaseName) {
                case "stop":        stopMedia();        break;
                case "pause":       pauseMedia();       break;
                case "play":        playMedia();        break;
                case "next":        nextMedia();        break;
                case "previous":    previousMedia();    break;
                default: break;
                }
            }
        });    
            
        return buttonName; 
        
    }
    
    public JButton getStopButton() {
        JButton button = new JButton("Stop");
        setupListenerAndAction(button, "stop");      
        return button;     
    }
    
    public JButton getPauseButton() {
        JButton button = new JButton("Pause");
        setupListenerAndAction(button, "pause");      
        return button;     
    }
    
    public JButton getPlayButton() {
        JButton button = new JButton("Play");
        setupListenerAndAction(button, "play");      
        return button;     
    }
    
    public JButton getNextButton() {
        JButton button = new JButton("Next");
        setupListenerAndAction(button, "next");      
        return button;     
    }
    
    public JButton getPreviousButton() {
        JButton button = new JButton("Previous");
        setupListenerAndAction(button, "previous");      
        return button;     
    }
    
    private void pauseMedia() {
        if(isPaused == false) {
    	mediaPlayer.pause();
    	isPaused = true;
        }
        else if(isPaused == true) {
        mediaPlayer.play();
        isPaused = false;
        }
    }
    
    private void playMedia() {
	   mediaPlayer.play();
    }
    
    private void nextMedia() {
        //mediaPlayer.pl
    	mediaListPlayer.playNext();
    }
    
    private void previousMedia() {
        mediaListPlayer.playPrevious();
    }

    private void adjustVolume(int volumePercent) {
    	
    }
    
    private void shufflePlaylist(Boolean onOff) {
    	
    }
    
    private void loopPlaylist(Boolean onOff) {
    	
    }
    
    private void openPlaylist() {
    	
    }
    
    private void closePlaylist() {
    	
    }
   
    
    private void lockPlaylistWithPresentation() {
    	
    }
    
    private void unlockPlaylistFromPresentation() {
    	
    }
    
    private void browseForNewPlaylistFolder() {
    	
    }
    
	private void setupGUI(ArrayList<String> files) {
    	JPanel playlistChooserPanel = new JPanel();
    	playlistChooserPanel.add(fileChooser.openDialog());

    	contentPane = playlistFrame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        createList(files);
        mediaPlayer = openMediaPlayer();
        playlistFrame.add(playlistChooserPanel, BorderLayout.NORTH);
        playlistFrame.add(playPanel, BorderLayout.CENTER);
        playlistFrame.setSize(200, 300);
        playlistFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playlistFrame.pack();
        playlistFrame.setVisible(true);
        playlistFrame.validate(); 

    }

    private static void createList(ArrayList<String> nameList) {
    	listModel.clear();
        for(String listItem : nameList){
        	listModel.addElement(listItem);
        }
        playPanel.add(playContents);
        playlistFrame.pack();
        
        
    }

    private static ArrayList<String> getFilenames(String newFilePath) {
       File folder = new File(newFilePath);
       File[] shit = folder.listFiles();
       ArrayList<String> returnList = new ArrayList();
       
       for(int i = 0; i < shit.length; i ++) {
           returnList.add(shit[i].getName());
       }
   
       return returnList;
    }


//	private static EmbeddedMediaPlayer openMediaPlayer() {
//	    final EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
//	    final EmbeddedMediaPlayer mediaPlayer = mediaPlayerComponent.getMediaPlayer();
//	    MediaListPlayer mediaListPlayer;
//	    MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
//
//	    
//	    mediaList = mediaPlayerFactory.newMediaList();
//	    mediaListPlayer = mediaPlayerFactory.newMediaListPlayer();
//	    mediaListPlayer.setMediaPlayer(mediaPlayer);
//	    
//	    String[] options = {};
//	    ArrayList<String> thing = getFilenames(newFilePath);
//	    
//	    for(String filename: thing) {
//	    mediaList.addMedia(filename, options);
//	    }
//	    
//	  
//	    mediaListPlayer.setMediaList(mediaList);
//        mediaListPlayer.setMode(MediaListPlayerMode.LOOP);
//	    
//	    
//	    mainFrame.setContentPane(mediaPlayerComponent);
//	    contentPane.add(mediaPlayerComponent, BorderLayout.EAST);
//	    //contentPane.add(mediaListPlayer, BorderLayout.EAST);
//	    return mediaPlayer;
//	}
	
	   private static EmbeddedMediaPlayer openMediaPlayer() {
	       MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
	       final EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
	        Canvas canvas = new Canvas();
	        canvas.setBackground(Color.black);
	        CanvasVideoSurface videoSurface = mediaPlayerFactory.newVideoSurface(canvas);

	        EmbeddedMediaPlayer mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer();
	        mediaPlayer.setVideoSurface(videoSurface);

	        mediaListPlayer = mediaPlayerFactory.newMediaListPlayer();

//	        mediaListPlayer.addMediaListPlayerEventListener(new MediaListPlayerEventAdapter() {
//	            @Override
//	            public void nextItem(MediaListPlayer mediaListPlayer, libvlc_media_t item, String itemMrl) {
//	                System.out.println("nextItem()");
//	            }
//	        });

	        mediaListPlayer.setMediaPlayer(mediaPlayer); // <--- Important, associate the media player with the media list player

	        JPanel cp = new JPanel();
	        cp.setBackground(Color.black);
	        cp.setLayout(new BorderLayout());
	        cp.add(canvas, BorderLayout.CENTER);

	        JFrame f = new JFrame("vlcj embedded media list player test");
	        f.setIconImage(new ImageIcon(TestMediaListEmbeddedPlayer.class.getResource("/icons/vlcj-logo.png")).getImage());
	        f.setContentPane(cp);
	        f.setSize(800, 600);
	        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        f.setVisible(true);

	      mediaList = mediaPlayerFactory.newMediaList();
	      String[] options = {};
	      ArrayList<String> thing = getFilenames(newFilePath);
	      String beg = "M:\\Year 2\\Engineering for Hearing and Voice\\Lab 1- Week 3\\Audio Samples\\";
	      for(String filename: thing) {
	      mediaList.addMedia(newFilePath + "\\" + filename, options);
	      }
	      int x = mediaList.size();
	      System.out.println("x = " + x);
//	      for(int y = 1; y < x-1; y++) {
//	          mediaList.removeMedia(0);
//	      }
	        mediaListPlayer.setMediaList(mediaList);
	        mediaListPlayer.setMode(MediaListPlayerMode.LOOP);

	        
		    mainFrame.setContentPane(mediaPlayerComponent);
		    contentPane.add(mediaPlayerComponent, BorderLayout.EAST);
		    //contentPane.add(mediaListPlayer, BorderLayout.EAST);
		    return mediaPlayer;
		    
		    
//	        mediaListPlayer.play();
//
//	        // This looping is just for purposes of demonstration, ordinarily you would
//	        // not do this of course
//	        for(;;) {
//	            try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//	           mediaPlayer.setChapter(3);
//
//	            try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//	            mediaListPlayer.playNext();
//	        }

	    }
   
}
