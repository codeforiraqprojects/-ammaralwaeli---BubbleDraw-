package BubbleDraw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Panal extends JPanel {

    private ArrayList<Bubble> bubbleList;
    private int size = 50, fr, lr;
    private Timer timer;
    private final int DELAY = 25;
    private BufferedImage imageA;
    String imagePath = "D:\\123.jpg";

    public Panal() {
        try {
            load1(imagePath);
        } catch (IOException x) {
        }

        bubbleList = new ArrayList<>();
        addMouseListener(new BubbleListener());
        addMouseMotionListener(new BubbleListener());
        addMouseWheelListener(new BubbleListener());
        timer = new Timer(DELAY, new BubbleListener());
        timer.start();
        setBackground(Color.BLACK);
        setPreferredSize(getMaximumSize());

    }

    public void load1(String s) throws IOException {
        File file = new File(imagePath); // I have bear.jpg in my working directory
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Panal.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            imageA = ImageIO.read(fis); //reading the image file
        } catch (IOException ex) {
            Logger.getLogger(Panal.class.getName()).log(Level.SEVERE, null, ex);
        }
        // return imageA;
    }

    @Override
    public void paintComponent(Graphics page) {

        super.paintComponent(page);
        for (Bubble b : bubbleList) {
            page.setColor(b.color);

            //setBackground(b.color);
            //b.size = (int) (Math.random() * 100);
            //fr=(int) (Math.random() * 100);
            //lr=(int) (Math.random() * 100);
            page.fillOval(b.x - b.size / 2, b.y - b.size / 2, b.size, b.size);
            //page.drawImage(imageA, b.x - b.size / 2, b.y - b.size / 2, this);
            //page.setColor(Color.WHITE);
            //page.drawString(Integer.toString(b.c + 1), b.x, b.y);


            //page.drawString("Ammar", b.x, b.y);
            //page.drawLine(0, b.y - b.size/2, getWidth(), b.y - b.size/2);
            //page.drawLine(0, b.y - b.size/2+b.size, getWidth(), b.y - b.size/2+b.size);
        }
        page.setColor(Color.green);
        page.drawString("Count of Bubbles : " + bubbleList.size(), 5, 15);

    }

    private class BubbleListener implements MouseListener, MouseMotionListener, MouseWheelListener, ActionListener {

        /*MouseListener*/
        @Override
        public void mouseClicked(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
            int x = e.getX(), y = e.getY();
             for (Bubble b : bubbleList) {
             if ((x >= b.x- b.size / 2 && x <= b.x- b.size / 2 + b.size) && (y >= b.y- b.size / 2 && y <= b.y- b.size / 2 + b.size)) {
             bubbleList.remove(b);
             System.out.print(b.color+"\n");
             break;
             }
             }
            /*for (int i = 0; i < 256; i++) {
             size = (int) (Math.random() * 100);
             bubbleList.add(new Bubble(e.getX(), e.getY(), size, bubbleList.size()));
             }*/
            //bubbleList.add(new Bubble(e.getX(), e.getY(), size, bubbleList.size()));
            repaint();

        }

        @Override
        public void mousePressed(MouseEvent e) {
            /*bubbleList.add(new Bubble(e.getX(), e.getY(), size));
             repaint();*/
            //timer.stop();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //timer.start();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
            //bubbleList.clear();
        }

        /*MouseMotionListener*/
        @Override
        public void mouseDragged(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
            //size=(int)(Math.random()*50);
            bubbleList.add(new Bubble(e.getX(), e.getY(), size, bubbleList.size()));
            /*int x = e.getX(), y = e.getY();
             for (Bubble b : bubbleList) {
             if ((x >= b.x - b.size / 2 && x <= b.x - b.size / 2 + b.size) && (y >= b.y - b.size / 2 && y <= b.y - b.size / 2 + b.size)) {
             bubbleList.remove(b);
             System.out.print(b.color + "\n");
             break;
             }
             }*/
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
            /*bubbleList.add(new Bubble(e.getX(), e.getY(), size, bubbleList.size()));
             repaint();*/
            int x = e.getX(), y = e.getY();
            /*for (Bubble b : bubbleList) {
                if ((x >= b.x - b.size / 2 && x <= b.x - b.size / 2 + b.size) && (y >= b.y - b.size / 2 && y <= b.y - b.size / 2 + b.size)) {
                    //setBackground(b.color);
                    bubbleList.remove(b);
                    //System.out.print(b.color + "\n");
                    break;
                }
            }*/
            repaint();
        }

        /*MouseWheelListener*/
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
            size -= e.getWheelRotation();
            repaint();
        }

        /*ActionListener*/
        @Override
        public void actionPerformed(ActionEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
            for (Bubble b : bubbleList) {
                b.update();
            }
            repaint();
        }
    }

    private class Bubble {

        int x, y, size, xSpeed, ySpeed, c;
        private final int maxSpeed = 5;
        Color color;

        Bubble(int newX, int newY, int newSize, int count) {
            x = newX;
            y = newY;
            size = newSize;
            c = count;
            color = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
            do {
                xSpeed = (int) (Math.random() * maxSpeed * 2 - maxSpeed);
                ySpeed += (int) (Math.random() * maxSpeed * 2 - maxSpeed);
            } while (xSpeed == 0 && ySpeed == 0);
        }

        private void update() {
            /**
             * int rndx=(int)(Math.random()*3),rndy=(int)(Math.random()*3);
             * if(rndx==1){ x+=50; } else if(rndx==0){ x-=50; } else
             * if(rndx==2){ x+=0; } if(rndy==1){ y+=50; } else if(rndy==0){
             * y-=50; } else if(rndy==2){ y+=0;
            }
             */
            x += xSpeed;
            y += ySpeed;
            if (x < 0 || x > getWidth()) {
                xSpeed *= -1;
            }
            if (y < 0 || y > getHeight()) {
                ySpeed *= -1;
            }
        }
    }
}
