import processing.core.PApplet;

public class Sketch extends PApplet {
	
  float [] floatSnowY = {50,75,100,125,180,400,250,325,200,300};
	float [] floatSnowX = {50,100,150,250,300,350,400,450,500,550};
  double doubleRadius = 12.5;
  boolean hit = false;
  boolean LeftPressed = false;
  boolean RightPressed = false;
  boolean MoveRight = false;
  boolean MoveLeft = false;
  int rectintX = 250;
  int rectintY = 550;

  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(550, 550);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(0);
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
      background(0);
      double speed = 0.5; 

      
    
      fill(255);
      rect(rectintX,rectintY,100, -100);

      if (MoveRight == true || LeftPressed == true) {
        rectintX += 10;
      } if (MoveLeft == true || RightPressed == true) {
        rectintX -= 10;
      }

      if (rectintX < 0) {
        rectintX = 0;
      } else if (rectintX > width - 100) {
        rectintX = width -100;
      } 
   

      for (int i = 0; i < 10; i++) {
        
        if (hit == true) {
          fill(0);
        }
        
        ellipse(floatSnowX[i],floatSnowY[i],25,25);
        floatSnowY[i] += speed;
        //speed = speed + 0.5;

        if (speed > 5) {
          speed = 5;
        }
        if (floatSnowY[i] > height) {
          floatSnowY[i] = 0;
          hit = false;
        }
        
    }

  }
  
  public void mousePressed() {

    for (int i = 0; i < 10; i++) {
      if (dist((float) mouseX, (float) mouseY,floatSnowX[i],floatSnowY[i]) < doubleRadius) {
        hit = true;
      }
  }

  }

  public void keyPressed() {
    if (keyPressed) {
      if (key == 'd' || key =='D') {
        MoveRight = true;
      } if (key == 'a' || key == 'A') {
        MoveLeft = true;
      }
    }
  }

  public void keyCode() {
      if (keyCode == LEFT) {
        LeftPressed = true;
      } if (keyCode == RIGHT) {
        RightPressed = true;
      }
    
  }

  public void keyReleased() {
    if (!keyPressed) {
      MoveRight = false;
      MoveLeft = false;
    }
  }
}
