package angrybirds;

//==============================================================================
//Assignment 2 - Angry Birds
//Danica Xiong
//December 1st
//Java, Eclipse Standard/SDK Version: Kepler Service Release 2
//==============================================================================
//Problem Definition 	–Use projectile motion to launch a bird and see if it hits a target.
//==============================================================================
//==============================================================================
//List of Identifiers 
//       - let TextField1 be an input field
//       - let TextField1 be an input field
//       - let TextField1 be an input field
//       - let TextField1 be an input field
//       - let doubleVer represent the vertical input (Type double)
//       - let doubleHor represent the horizontal input (Type double)
//       - let doubleVel represent the velocity input (Type double)
//       - let doubleAng represent the angle input (Type double)
//       - let doubleTime represent the time (Type double)
//       - let doubleA represent the first term in a parabola (Type double)
//        - let doubleB represent the second term in a parabola (Type double)
//       - let doubleC represent the second term in a parabola (Type double)
//       - let intTarX represent the X value of the 1st target (Type integer)
//       - let intTar2X represent the X value of the 2nd target (Type integer)
//       - let globalCount represent how many times target1's X value was called for (Type integer)
//       - let globalCount2 represent how many times target2's X value was called for (Type integer)
//       - let globalCount3 represent if target 1 got hit(Type integer)
//       - let globalCount4 represent if target 2 got hit(Type integer)
//       - let lastCount represent how many times the player missed
//==============================================================================




import java.applet.*;
import java.util.Random;
import java.awt.*;




public class birds extends Applet {




	TextField text1, text2, text3, text4;// Declares text fields
	double doubleVer, doubleHor, doubleVel, doubleAng; // Declare double primitive data types named doubleVer, doubleHor, doubleVel, doubleAng
	double doubleA, doubleB, doubleC; // Declare double primitive data types named doubleA, doubleB, doubleC
	int intTarX, intTar2X; // Declare integer primitive data types named intTarX, and intTar2x;
	int globalCount = 4, globalCount2 = 4, globalCount3 = 0, globalCount4 = 0, lastCount = 0; // Declare integer primitive data types global counts




	/**init method
	*This procedural method takes in inputs by user using text fields
	*Labels are used to make input fields more clear
	*Buttons added to clear, reset, draw, parabola
	*@param none
	*@return void
	*/
	public void init() { 
		TextField numberField = new TextField(15);
		Label label_1 = new Label("(Horizontal)");
		Label label_2 = new Label("(Vertical)");
		Label label_3 = new Label("(Velocity)");
		Label label_4 = new Label("(Angle)");
		text1 = new TextField(8);
		text2 = new TextField(8);
		text3 = new TextField(8);
		text4 = new TextField(8);
		add(label_1);
		add(text1);
		add(label_2);
		add(text2);
		add(label_3);
		add(text3);
		add(label_4);
		add(text4);
		text1.setText(null);
		text2.setText(null);
		text3.setText(null);
		text4.setText(null);
		setSize(900, 800);
		add(new Button("Draw"));
		add(new Button("Clear"));
		add(new Button("Restart"));
	}
	
	/**getNewInput method
	*This procedural method converts inputs from strings into usable primative data types (Doubles)
	*Try/Catch in case Mr. Cho tries to break my program
	*If doubleHor goes over limit, set it to limit
	*@param none
	*@return void
	*/
	private void getNewInput() {
		try {
			doubleHor = Double.parseDouble(text1.getText());
if (doubleHor>500){
doubleHor = 500;
}
			doubleVer = Double.parseDouble(text2.getText());
			doubleVel = Double.parseDouble(text3.getText());
			doubleAng = Double.parseDouble(text4.getText());
		} catch (NumberFormatException e) {
		}
	}
	
	/**radians method
	*This functional method converts the angle into radians, and returns that number
	*		double rad = the angle in radians
	*@param none
	*return (double) the angle in radians to be used in calculation
	*/
	public double radians() {
		double rad;
		rad = (Math.toRadians(doubleAng));
		return rad;
	}
	
	/**abc method
	*This procedural method converts the inputs into the abc terms of a parabola 
	*For more clear, and easier calculation
	*		doubleA = 1st term in parabola
	*		doubleB = 2nd term in parabola
	*		doubleC = 3rd term in parabola
	*@param none
	*@return void
	*/
	public void abc() {
		doubleA = -4.9;
		doubleB = ((doubleVel * (Math.sin(radians()))));
		doubleC = doubleVer;
	}
	
	/**root1 method
	*This functional method finds one of the times of which the parabola is at 0, and returns that number
	*Calls abc method for easier calculations
	*		doubleTime = time of which the parabola is at 0
	*@param none
	*@return (double)the root
	*/
	public double root1() {
		abc();
		double doubleTime = Math
				.round(((-doubleB) - (Math.sqrt((doubleB * doubleB) - 4 * (doubleA) * doubleC))) / (2 * doubleA));
		return (doubleTime);
	}
	/**root2 method
	*This functional method finds one of the times of which the parabola is at 0, and returns that number
	*		doubleTime = time of which the parabola is at 0
	*@param none
	*@return (double)the 2nd root
	*/
	public double root2() {
		double doubleTime = Math
				.round(((-doubleB) + (Math.sqrt((doubleB * doubleB) - 4 * (doubleA) * doubleC))) / (2 * doubleA));
		return (doubleTime);
	}
	/**xRoot1 method
	*This functional method finds the X value of where the parabola starts at, and returns that number
	*		(int)root  = the X value of where the parabola starts at
	*@param none
	*@return (double) the X value of where it starts at
	*/
	public double xRoot1() {
		double root;
		root = (doubleB/doubleA);
		return 0;
	}
	/**xRootDis method
	*This functional method finds the X coordinate when the parabola lands, and returns that number
	*		(double)root  = time of which the parabola is at it's peak
	*@param none
	*@return (double) the parabola's end X value
	*/
	public double xRootDis() {
		int x;
		x = (int) (doubleHor + (doubleVel * root1() * Math.cos(radians())));
		return x;
	}




	/**middleTime method
	*This functional method finds one of the time parabola is at it's peak, and returns that number
	*		(double)mid  = time of which the parabola is at it's peak
	
	*@param none
	*@return (double) the peak's time
	*/
	public double middleTime() {
		double root1 = root1();
		double root2 = root2();
		double mid;
		mid = (root2 + root1) / 2;
		return mid;
	}




	/**xRootDis method
	*This functional method find the Y coordinate when the parabola is at it's peak, and returns that number
	*It is found by adding the 2 roots and then dividing the sum by 2
	*		(int)y  = Y value of which the parabola is at it's peak
	*@param none
	*@return (String) the peak's Y value
	*/
	public String highHeight() {
		String y;
		int mid = (int)middleTime();
		y = String.valueOf(Math.round((doubleC + (doubleB * mid) + doubleA * mid * mid)));
		return y;




	}
	
	/**target method
	*This functional method gives the random X coordinate of where the 1st target is
	*Function only refreshes after 4 inputs by user
	*		Rand object allows for random numbers
	*		(int) x  = x value of where the target is
	*@param none
	*@return (int) the target's x value
	*/
	public int target() {
		int x;
		Random rand = new Random();
		globalCount++;
		if (globalCount < 4) {
		} else {
			intTarX = rand.nextInt(700) + 100;
			globalCount = 0;
		}
		return intTarX;
	}
	
	/**target2 method
	*This functional method gives the random X coordinate of where the 2nd target is
	*Function only refreshes after 4 inputs by user
	*		Rand object allows for random numbers
	*		(int) x  = x value of where the target is
	*@param none
	*@return (int) the 2nd target's x value
	*/
	public int target2() {
		int x;
		Random rand = new Random();
		globalCount2++;
		if (globalCount2 < 4) {
		} else {
			intTar2X = rand.nextInt(700) + 100;
			globalCount2 = 0;
		}
		return intTar2X;
	}
	
	/**distance method
	*This functional method finds the distance between the parabola's landing spot, and the 1st target
	*and returns that number
	*Subtracts parabola's landing value, and target's coordinates
	*		(String) distance = String value of the distance between the target and parabola
	*		(int) d = Distance between the target and parabola for calculation
	*@param none
	*@return (String) String value of the distance between the 1st target and parabola
	*/
	public String distance() {
		String distance;
		int d;
		d = (int) (intTarX - xRootDis());
		if (d < 0) {
			d = (-d);
		}
		distance = String.valueOf(d);
		return distance;
	}
	
	/**distance2 method
	*This functional method finds the distance between the parabola's landing spot, and the 2nd target
	*and returns that number
	*Subtracts parabola's landing value, and target's coordinates
	*		(String) distance = String value of the distance between the 2nd target and parabola
	*		(int) d = Distance between the target and parabola for calculation
	*@param none
	*@return (String) String value of the distance between the 2nd target and parabola
	*/
	public String distance2() {
		String distance;
		int d;
		d = (int) (intTar2X - xRootDis());
		if (d < 0) {
			d = (-d);
		}
		distance = String.valueOf(d);
		return distance;
	}
	
	/**xAd method
	*This functional method finds out whether the parabola's x value moves
	*and returns the distance of which it moved
	*Subtracts parabola's starting value and horizontal input
	*		(int) add = Where the parabolas supposed to start
	*		(int) x = Distance between the x value and horizontal input
	*		doubleTime = Where the parabolas supposed to begin
	*@param none
	*@return (int) Distance between the x value and horizontal input
	*/
	public int xAd() {
		double doubleTime = xRoot1();
		int add = 0;
		int x;
		x = (int) (doubleHor + (doubleVel * doubleTime * Math.cos(radians())));
		if (x != doubleHor) {
			add = (int) doubleHor - x;
		}
		return add;
	}
	
	/**check method
	*This procedural method determines whether the player has won or lost
	*Function outputs message depending on if they win or lose
	*@param g - graphics object used to draw output
	*@return none
	*/
	public void check(Graphics g) {
		if (lastCount <= 4) {
			if (globalCount3 >= 1 && globalCount4 >= 1) {
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, 900, 900);
				g.setColor(Color.BLUE);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ie) {
				}
				g.drawString("Good job, you win! Hit (Restart) to play again!",
						400, 350);
			}
		} else {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 900, 900);
			g.setColor(Color.BLUE);
			g.drawString("You lost! Hit (Restart) to retry!", 400, 350);
		}
	}
	
	/**test method
	*This procedural method tests if the player hit the 1st target or not and outputs a message
	*in response to that. It also draws the target
	*		(int) xp = y value of target
	*@param g - graphics object used to draw output
	*@return none
	*/
	public void test(Graphics g) {
		g.setColor(Color.red);
		int xp = (int) Math.round(100 + xRootDis());
		if (xp >= intTarX - 20 && xp <= intTarX + 20) {
			g.drawString("HIT!", 120, 130);
			globalCount4++;
			if (globalCount3 ==0) {
				g.drawString("Try to get the 2nd target!", 200, 130);
			}
		}




		else if (globalCount4 == 0) {
			g.drawString("MISS!", 120, 130);
			g.drawString(distance(), 180, 130);
			g.drawString("meters away from 1st target", 220, 130);




			g.setColor(Color.RED);
			g.fillOval(intTarX + xAd(), 685, 30, 30);




		} else {
			g.setColor(Color.white);
			g.fillRect(115, 120, 300, 19);
		}
	}
	
	/**test2 method
	*This procedural method tests if the player hit the 2nd target or not and outputs a message
	*in response to that. It also draws the target
	*		(int) xp = y value of target
	*@param g - graphics object used to draw output
	*@return none
	*/
	public void test2(Graphics g) {
		g.setColor(Color.PINK);
		int xp = (int) Math.round(100 + xRootDis());
		if (xp >= intTar2X - 20 && xp <= intTar2X + 20) {
			globalCount3++;
			g.drawString("HIT! ", 120, 150);
			if (globalCount4==0) {
				g.drawString("Try to get the 1st target!", 200, 150);
			}
		} else if (globalCount3==0) {
			g.drawString("MISS! ", 120, 150);
			g.drawString(distance2(), 180, 150);
			g.drawString("meters away from 2nd target", 220, 150);




			g.setColor(Color.PINK);
			g.fillOval(intTar2X + xAd(), 685, 30, 30);
		} else {
			g.setColor(Color.white);
			g.fillRect(115, 135, 300, 19);
		}
	}
	
	/**paint method
	*This procedural method outputs the instructions to using the program
	*it is also the main method, responsible for outputs
	*in response to that. It also draws the target
	*		(String) timeSt = String value of the time of which the projectile is in the air
	*@param g - graphics object used to draw output
	*@return none
	*/
	public void paint(Graphics g) {
		abc();
		String timSt;
		timSt = String.valueOf(root1());
		g.drawString(
				"Input Horizontal and Vertical starting positions, Velocity, and Angle in the respective boxes  ",
				60, 50);
		g.drawString(
				"Try to hit the red balls using projectile motion!(input in boxes above)  ",
				60, 70);
		g.drawString("Time in air is about: ", 60, 90);
		g.drawString(timSt, 180, 90);
		g.drawString("seconds  ", 200, 90);
		g.drawString("Highest point is at:  ", 60, 110);
		g.drawString(highHeight(), 170, 110);
		g.drawString("Meters ", 200, 110);
		g.setColor(Color.blue);
		g.drawString("Enter any Coordinates to start!", 300, 100);
		abc();
		test(g);
		test2(g);
		grid(g);
		curve(g);
		check(g);
	}
	
	/**grid method
	*This functional draws the grid for the parabola
	*		(String) xSt = value of horizontal lines
	*		(String) ySt = value of verticle lines
	*@param g - graphics object used to draw output
	*@return none
	*/
	public void grid(Graphics g) {




		g.setColor(Color.black);
		g.drawLine(0, 700, 800, 700);
		g.drawLine(100, 150, 100, 800);
		for (int xLine = 100; xLine <= 800; xLine += 100) {
			g.drawLine(xLine, 690, xLine, 710);
			String xSt = String.valueOf(xLine - 100);
			g.drawString(xSt, xLine, 730);
		}
		for (int yLine = 200; yLine <= 700; yLine += 100) {
			g.drawLine(90, yLine, 110, yLine);
			String ySt = String.valueOf(-yLine + 700);
			g.drawString(ySt, 60, yLine + 5);
		}
		g.setColor(Color.GREEN);
		g.drawString("height(m)", 60, 150);
		g.drawString("distance(m)", 750, 750);
	}
	
	/**grid method
	*This functional draws the parabola
	*		(int) xp = integer gets rid of unused x values
	*		(int) yp = integer value of y
	*@param g - graphics object used to draw output
	*@return none
	*/
	public void curve(Graphics g) {
		int xp, yp;
		g.setColor(Color.blue);
		for (double t = xRoot1(); t <= root1(); t = t + 0.1) {
			double y = doubleC + (t * doubleB) + (doubleA * t * t);
			double x = doubleHor + (doubleVel * t * Math.cos(radians()));
			while (x < doubleHor) {
				xp = -600;
			}
			xp = (int) Math.round(100 + x);
			yp = (int) Math.round(y);
			g.fillOval(xp + xAd(), -yp + 700, 7, 7);
			try {
				Thread.sleep(20);
			} catch (InterruptedException ie) {
			}
		}
	}
	
	/**resetCoordinates method
	*This functional method clears all inputs from the program
	*@param g
	*@return none
	*/
	private void resetCoordinates() {
		doubleVer = 0;
		doubleHor = 0;
		doubleVel = 0;
		doubleAng = 0;
		text1.setText(null);
		text2.setText(null);
		text3.setText(null);
		text4.setText(null);
	}

	/**action method
	*This functional method clears all inputs from the program, and also resets the global variables
	*It also triggers the drawing of the parabola
	*@param g
	*@return none
	*/
	public boolean action(Event ev, Object arg) {
		if (arg.equals("Draw")) {
			getNewInput();
			repaint();
			target();
			target2();
			lastCount++;
		} else if (arg.equals("Clear")) {
			resetCoordinates();
			repaint();
		} else if (arg.equals("Restart")) {
			resetCoordinates();
			repaint();
			globalCount = 4;
			globalCount2 = 4;
			globalCount3 = 0;
			globalCount4 = 0;
			lastCount = 0;
		}
		return true;
	}
}
