import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

//Comment by Chris S

//Josh D mixing things up!!!!!
public class Display extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Create  Canvas
    //static Painter MyCanvas = new Painter();

	//protected static int iteration;
    
	
	
    
	public Display() {
		super();
	}

//	private static void topLevelLoop(int i)
//	{
//	
//		
//			
//		MyCanvas.setOval(0, i+30, i+30, 2);
//		MyCanvas.setOval(1, i+80, i+80, 1);
//			
//		MyCanvas.paint(MyCanvas.getGraphics());
//		MyCanvas.update(MyCanvas.getGraphics());
//		
//		
//		
//	}
	
	private static void createAndShowGUI()
	{
		NativeLibrary.addSearchPath(
                RuntimeUtil.getLibVlcLibraryName(), "C:/Program Files/VideoLAN/VLC"
            );
	
    	
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);	
		
	JFrame frame = new JFrame("Display");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel vidpanel = new JPanel();
    Painter randompanel = new Painter();
    JButton PlayButton = new JButton("PLAY!");
    
    
    frame.setLayout(null);
    Dimension minimumSize = new Dimension(800, 600);
    frame.setMinimumSize(minimumSize);
    
    Canvas canvas = new Canvas();
    MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
    CanvasVideoSurface videoSurface = mediaPlayerFactory.newVideoSurface(canvas);
    EmbeddedMediaPlayer mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer();
    mediaPlayer.setVideoSurface(videoSurface);
    canvas.setBounds(0, 0, 720, 276);
    vidpanel.setBounds(0, 0, 720, 276);
    randompanel.setBounds(0, 280, 720, 200);
    vidpanel.add(canvas);
    randompanel.add(PlayButton);
    
    
    
    frame.add(randompanel);
    frame.add(vidpanel);
    
    
    
    //Display the window.
    frame.pack();
    frame.setVisible(true);
    
    randompanel.repaint();
    
    mediaPlayer.playMedia("bin/Captain America.mp4");
    
	}
	


	public static void main(String[] args) {
		
		
		
		
		
//		iteration = 0;
//		final int delay = 40; //milliseconds
//	    final ActionListener taskPerformer = new ActionListener() {
//	        public void actionPerformed(ActionEvent evt) {
//	        	topLevelLoop(iteration);
//	        	iteration ++;
//	        }
//	    };
	    
		
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                createAndShowGUI();
	                //new Timer(delay, taskPerformer).start();
	            }
	      });
		
		
	}
	
	
	
	
	
}
