import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Othelo {

    private JFrame frame;

    public Othelo() {
        frame = new JFrame("Othelo");
        SetProperties();

    }

    public void SetProperties() {
        frame.setSize(800, 800);
        Draw();
        frame.setLayout(new GridLayout(8, 8));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void Draw() {
        for (int i = 0; i < 64; i++) {

            JButton b = new JButton();
            b.setVisible(true);
            b.setBackground(Color.GRAY);
            b.setForeground(b.getBackground());
            b.setActionCommand(Integer.toString(i+1));
            
            b.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(e);

                }

            });
            frame.add(b);
        }
    }


    
}