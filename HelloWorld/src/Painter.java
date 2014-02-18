   
    import java.awt.Color;  
      import java.awt.Graphics;  
      import java.awt.BasicStroke;    
      import java.awt.Graphics2D;             
      import java.awt.geom.Ellipse2D;         
      import java.awt.geom.Rectangle2D;       
      import java.awt.geom.Line2D;            
      import javax.swing.JPanel;  
        
      public class Painter extends JPanel   
      {  
         /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		// draw shapes with Java 2D API  
         public void paintComponent( Graphics g )  
         {  
            super.paintComponent( g ); // call superclass's paintComponent  
        
            Graphics2D g2d = ( Graphics2D ) g; // cast g to Graphics2D  
        
            // draw 2D ellipse filled with a blue-yellow gradient         
            g2d.setPaint( Color.BLACK);                                    
            g2d.fill( new Ellipse2D.Double( 5, 30, 65, 100 ) ); 
            g2d.setPaint( Color.RED);  
            g2d.fill( new Ellipse2D.Double( 350, 0, 65, 100 ) ); 
        
            // draw 2D rectangle in red                             
            g2d.setPaint( Color.BLACK );                              
            g2d.setStroke( new BasicStroke( 2.0f ) );              
            g2d.draw( new Rectangle2D.Double( 80, 30, 65, 100 ) );  
        
           // draw 2D lines in green and yellow                 
            g2d.setPaint( Color.BLACK );                         
            g2d.draw( new Line2D.Double( 395, 30, 320, 150 ) );  
         } // end method paintComponent  
      } // end class ShapesJPanel  
