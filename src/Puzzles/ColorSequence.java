import javax.swing.JButton;
import javax.swing.Timer;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.geom.Point2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ColorSequence extends JButton {
    
    private boolean mouseOver = false;
	private boolean mousePressed = false;
    private boolean lock = true;

    private boolean paintRed = false;
    private boolean paintYellow = false;
    private boolean paintGreen = false;
    private boolean paintBlue = false;
    private boolean paintGray = false;

    private int size;
    private Color color;
    
	public ColorSequence(int size){
		super("");
        this.color = Color.black;
        this.size = size;

		setOpaque(false);
		setFocusPainted(false);
		setBorderPainted(false);

		MouseAdapter mouseListener = new MouseAdapter(){

			@Override
			public void mousePressed(MouseEvent me){
				if(contains(me.getX(), me.getY()) && lock){
					mousePressed = true;
					repaint();
				}
			}

			@Override
			public void mouseReleased(MouseEvent me){
                if(lock) {
                    mousePressed = false;
                    repaint();
                    displayColorSequence();
                }
			}

			@Override
			public void mouseExited(MouseEvent me){
				mouseOver = false;
				mousePressed = false;
				repaint();
			}

			@Override
			public void mouseMoved(MouseEvent me){
				mouseOver = contains(me.getX(), me.getY());
				repaint();
			}
		};

		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);		
	}

    public void clickLock(boolean val) {
		lock = val;
	}
	
    private int getDiameter(){
		int diameter = Math.min(getWidth(), getHeight());
		return diameter;
	}

	@Override
	public Dimension getPreferredSize(){
		int minDiameter = size;
		return new Dimension(minDiameter, minDiameter);
	}

	@Override
	public boolean contains(int x, int y){
		int radius = getDiameter()/2;
		return Point2D.distance(x, y, getWidth()/2, getHeight()/2) < radius;
	}

	@Override
	public void paintComponent(Graphics g){

		int diameter = getDiameter();
		int radius = diameter/2;

		if(mousePressed){
			g.setColor(Color.LIGHT_GRAY);
		}
		else{
			g.setColor(color);
		}
		g.fillOval(getWidth()/2 - radius, getHeight()/2 - radius, diameter, diameter);

		if(mouseOver){
			g.setColor(Color.black);
		}
		else{
			g.setColor(color);
		}
		g.drawOval(getWidth()/2 - radius, getHeight()/2 - radius, diameter, diameter);

        if(!lock) {
            if (paintRed) {
                g.setColor(Color.red);
            } else if (paintYellow) {
                g.setColor(Color.yellow);
            } else if (paintGreen) {
                g.setColor(Color.green);
            } else if (paintBlue) {
                g.setColor(Color.cyan);
            } else if (paintGray) {
                g.setColor(Color.lightGray);
            }
        }

        g.fillOval(getWidth()/2 - radius, getHeight()/2 - radius, diameter, diameter);
	}

	public void displayColorSequence() {
		// engage clicklock
		clickLock(false);

		// set fill color to red initially
        paintRed = true;
		repaint();
		
		Timer clickLock = new Timer(3500, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e ){
				clickLock(true);
			}
		} );
		clickLock.setRepeats( false );
		clickLock.start();

		// use timers to display each new color for 500 ms, then light gray for 100 ms in between
		Timer timer0 = new Timer(500, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e ){
                paintRed = false;
				paintGray = true;
                repaint();
			}
		} );
		timer0.setRepeats( false );
		timer0.start();

		Timer timer1 = new Timer(600, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e ){
				paintRed = true;
				paintGray = false;
                repaint();
			}
		} );
		timer1.setRepeats( false );
		timer1.start();

		Timer timer2 = new Timer(1100, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e ){
				paintRed = false;
				paintGray = true;
                repaint();
			}
		} );
		timer2.setRepeats( false );
		timer2.start();

		Timer timer3 = new Timer(1200, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e ){
				paintGreen = true;
				paintGray = false;
                repaint();
			}
		} );
		timer3.setRepeats( false );
		timer3.start();

		Timer timer4 = new Timer(1700, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e ){
				paintGreen = false;
				paintGray = true;
                repaint();
			}
		} );
		timer4.setRepeats( false );
		timer4.start();

		Timer timer5 = new Timer(1800, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e ){
				paintYellow = true;
				paintGray = false;
                repaint();
			}
		} );
		timer5.setRepeats( false );
		timer5.start();

		Timer timer6 = new Timer(2300, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e ){
				paintYellow = false;
				paintGray = true;
                repaint();
			}
		} );
		timer6.setRepeats( false );
		timer6.start();

		Timer timer7 = new Timer(2400, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e ){
				paintBlue = true;
				paintGray = false;
                repaint();
			}
		} );
		timer7.setRepeats( false );
		timer7.start();

		Timer timer8 = new Timer(2900, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e ){
				paintBlue = false;
				paintGray = true;
                repaint();
			}
		} );
		timer8.setRepeats( false );
		timer8.start();

		Timer timer9 = new Timer(3000, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e ){
				paintGreen = true;
				paintGray = false;
                repaint();
			}
		} );
		timer9.setRepeats( false );
		timer9.start();

		Timer timerLast = new Timer(3500, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e ){
				paintGreen = false;
                repaint();
			}
		} );
		timerLast.setRepeats( false );
		timerLast.start();
	}
}
