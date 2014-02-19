   
    import java.awt.AlphaComposite;
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
         public Painter() {
			super();
			this.setOpaque(false);
			// TODO Auto-generated constructor stub
		}



		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		int xcoord = 5;
		int ycoord = 30;
		public int getXcoord() {
			return xcoord;
		}
		public void setXcoord(int xc) {
			this.xcoord = xc;
		}
//		public int getY() {
//			return ycoord;
//		}
//		public void setY(int ycoord) {
//			this.ycoord = ycoord;
//		}
		// draw shapes with Java 2D API  
		
		
		
		private AlphaComposite makeComposite(float alpha) {
      	  int type = AlphaComposite.SRC_OVER;
      	  return (AlphaComposite.getInstance(type, alpha));
      	 }

		
		
         public void paintComponent( Graphics g )  
         {  
            super.paintComponent( g ); // call superclass's paintComponent  
        
            Graphics2D g2d = ( Graphics2D ) g; // cast g to Graphics2D  
            //Color invisible = new Color(255, 0,0, 200);
            
            //g2d.setBackground(invisible);
            g2d.setComposite(makeComposite((float) 0.2));
            
            // draw 2D ellipse filled with a blue-yellow gradient         
            g2d.setPaint( Color.RED);     
            g2d.fill( new Ellipse2D.Double( xcoord, ycoord, 65, 100 ) ); 
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
