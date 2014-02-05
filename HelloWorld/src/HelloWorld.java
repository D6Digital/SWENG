import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class HelloWorld extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	 public HelloWorld(String text) {
		super(text);
	}

	private static void createAndShowGUI() {
	        //Create and set up the window.
	        JFrame frame = new JFrame("Hello Label");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        
	        String text = "Hello World!";
	        //Add contents to the window.
	        frame.add(new HelloWorld(text));

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
