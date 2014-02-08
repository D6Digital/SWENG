import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class HelloWorld extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	 public HelloWorld(String text) {
		super(text);
		this.setBounds(0, 0, 40, 20);
	}

	private static void createAndShowGUI() {
	        //Create and set up the window.
	        JFrame frame = new JFrame("Hello Label");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setLayout(null);
	        
	        // Not using a layout manager so a size for the frame
	        // should be decided otherwise the minimum is nothing
	        Dimension minimumSize = new Dimension(600, 400);
	        frame.setMinimumSize(minimumSize);
	        
	        String text = "Hello World!";
	        
	        // JButton placed at specific co-ordinates
	        JButton button1 = new JButton("OK");
	        button1.setBounds(40, 200, 75, 75);
	        
	        //Add contents to the window.
	        frame.add(new HelloWorld(text));
	        frame.add(button1);
	        
	        //Display the window.
	        frame.pack();
	        frame.setVisible(true);
	    }

	public static void main(String[] args) {
		
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
		
		
		System.out.println("Person:   Hey SWENG Group 5!!!");
		System.out.println("Us: 	  Hi :)");
		for (int e = 0; e < args.length; e++) {
			 
		} 
		
	}

}
