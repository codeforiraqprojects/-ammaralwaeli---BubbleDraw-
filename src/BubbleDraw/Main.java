package BubbleDraw;

import javax.swing.JFrame;
public class Main extends JFrame{

    public static void main(String[] args) {
        JFrame f=new JFrame("Ammar");
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.getContentPane().add(new Panal());
        f.pack();
        f.setVisible(true);
    }
}
