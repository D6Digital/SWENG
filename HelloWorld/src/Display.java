import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;


public class Display implements ActionListener, MouseListener{

	
	
	//Create  Canvas
    //static Painter MyCanvas = new Painter();

	//protected static int iteration;
    
	protected static JButton PlayButton;
	protected static EmbeddedMediaPlayer mediaPlayer;
	protected static Painter randompanel;
	protected static JPanel vidControlPanel;
	protected static JPanel vidpanel;
	protected static JFrame frame;
    
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
                RuntimeUtil.getLibVlcLibraryName(), "resources/lib/vlc-2.0.1"
            );
	
    	
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);	
		
	frame = new JFrame("Display");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    vidpanel = new JPanel();
    vidControlPanel = new JPanel();
    randompanel = new Painter();
    PlayButton = new JButton("PAUSE");
    
    vidControlPanel.setForeground(Color.BLUE);
    vidControlPanel.setBackground(Color.BLACK);
    vidControlPanel.setOpaque(false);
    
    JLayeredPane layerPane = new JLayeredPane();
    
    
    frame.setLayout(null);
    layerPane.setLayout(null);
    Dimension minimumSize = new Dimension(1600, 1000);
    frame.setMinimumSize(minimumSize);
    layerPane.setBounds(0,0,1600,1000);
    
    Canvas canvas = new Canvas();
    MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
    CanvasVideoSurface videoSurface = mediaPlayerFactory.newVideoSurface(canvas);
    mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer();
    mediaPlayer.setVideoSurface(videoSurface);
    canvas.setBounds(0, 0, 720, 276);
    vidpanel.setBounds(0, 0, 720, 276);
    vidControlPanel.setBounds(200, 260, 500, 100);
    randompanel.setBounds(0, 220, 720, 200);
    vidpanel.add(canvas);
    vidControlPanel.add(PlayButton);
    Display exampleD = new Display();
    
    PlayButton.setActionCommand("Play/Pause");
    PlayButton.addActionListener(exampleD);
    
    //vidpanel.addMouseListener(exampleD);
    frame.addMouseListener(exampleD);
    
    mediaPlayer.setEnableMouseInputHandling(false);
    mediaPlayer.setEnableKeyInputHandling(false);
    canvas.addMouseListener(exampleD);
    layerPane.add(randompanel,3);
    layerPane.add(vidControlPanel,0);
    layerPane.add(vidpanel,4);
    
    
    
    frame.add(layerPane);
    
    //Display the window.
    frame.pack();
    frame.setVisible(true);
    
    randompanel.repaint();
    
    mediaPlayer.playMedia("resources/video/avengers.mp4");
    
    
    
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

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if("Play/Pause".equals(e.getActionCommand()))
				{
				if("PLAY".equals(PlayButton.getText())){
					PlayButton.setText("PAUSE");
					mediaPlayer.play();
					for (int i = 0; i > -30; i--) {
						randompanel.setXcoord((randompanel.getXcoord()+i));
						randompanel.repaint();
						vidControlPanel.repaint();
					}
				}
					
				else {
					if("PAUSE".equals(PlayButton.getText())){
						PlayButton.setText("PLAY");
						mediaPlayer.pause();
						for (int i = 0; i < 30; i++) {
							randompanel.setXcoord((randompanel.getXcoord()+i));
							randompanel.repaint();
							vidControlPanel.repaint();
						}
					}
					
				}
				}
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getComponent().getParent()!=null)
		{
			//vidpanel.setBounds(e.getComponent().getParent().getX()+e.getX(), e.getComponent().getParent().getY()+e.getY(), 720, 276);
			//frame.pack();
			//vidpanel.repaint();
			
			
			
			if("PLAY".equals(PlayButton.getText())){
				PlayButton.setText("PAUSE");
				mediaPlayer.play();
				for (int i = 0; i > -20; i--) {
					randompanel.setXcoord((randompanel.getXcoord()+i));
					randompanel.repaint();
					vidControlPanel.repaint();
				}
			}
				
			else {
				if("PAUSE".equals(PlayButton.getText())){
					PlayButton.setText("PLAY");
					mediaPlayer.pause();
					for (int i = 0; i < 20; i++) {
						randompanel.setXcoord((randompanel.getXcoord()+i));
						randompanel.repaint();
						vidControlPanel.repaint();
					}
				}
				
			}
		}
		else
		{
			vidpanel.setBounds(e.getX(),e.getY(), 720, 276);
			frame.pack();
			vidpanel.repaint();
			
			
		}
		
	}
	
	
	
	
	
}
