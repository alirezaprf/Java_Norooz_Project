import java.awt.*;

import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.JFrame;

public class AWTGraphicsDemo extends JFrame {
       
   public AWTGraphicsDemo(){
      super("Java AWT Examples");
      prepareGUI();
      
   }

   private void prepareGUI(){
      setSize(900,900);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }    

   @Override
   public void paint(Graphics g) {
      Rectangle2D shape = new Rectangle2D.Float();
      shape.setFrame(200, 350, 200,100);
            
      Graphics2D g2 = (Graphics2D) g; 
      Font font = new Font("Serif", Font.PLAIN, 40);
      g2.setColor(Color.red);
      g2.setFont(font);
      g2.setPaint(Color.red);

        AffineTransform old = g2.getTransform();
        g2.rotate(Math.toRadians(65));
        //draw shape/image (will be rotated)
        g2.fill (shape);
        g2.setTransform(old);
      
      
       
      g.drawString("Welcome to TutorialsPoint", 50, 70);
      g2.drawString("Rectangle2D.Rectangle", 100, 120);
   }
}
