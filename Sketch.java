import processing.core.PApplet;

public class Sketch extends PApplet {
	
  float [] floatSnowY = {50,75,100,125,180,400,250,325,200,300};
	float [] floatSnowX = {50,100,150,250,300,350,400,450,500,550};
  boolean [] hideSnowFlakeStatus = {false,false,false,false,false,false,false,false,false,false};

  boolean blnHit = false;
  boolean MoveRight = false;
  boolean MoveLeft = false;
  
  double doubleRadius = 12.5;

  int intLocation = 0;
  int intCircleX = 300;
  int intCircleY = 535;
  int intHealth = 3;

  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	  // put your size call here
    size(600, 550);
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
      

      if (intHealth > 0) {

        background(0);

        for (int i = 0; i < 10; i++) {  
     
          if (dist(intCircleX, intCircleY,floatSnowX[i],floatSnowY[i]) < doubleRadius) {

            intHealth -= 1;
            floatSnowY[i] = 0;

          }

          if (hideSnowFlakeStatus[i] == false) {
        
            fill(255);
          
            ellipse(floatSnowX[i],floatSnowY[i],25,25);
            floatSnowY[i] += 1;
          
          if (floatSnowY[i] > height) {

            floatSnowY[i] = 0;
      
          }
        }

      if (blnHit == true) {

        hideSnowFlakeStatus[intLocation] = true;

      }

        fill(0,0,255);
        ellipse(intCircleX,intCircleY,25, 25);

        if (MoveRight == true) {

          intCircleX += 1;

        } if (MoveLeft == true) {

          intCircleX -= 1;

        }

        if (intCircleX < 12.5) {

          intCircleX = (int) 12.5;

        } else if (intCircleX > width - 12.5) {
          
          intCircleX = (int) (width - 12.5);

        } 
      }
    } else {

      background(255);

    }
  }
  
  public void mousePressed() {

    for (int i = 0; i < 10; i++) {
      
      if (dist((float) mouseX, (float) mouseY,floatSnowX[i],floatSnowY[i]) < doubleRadius) {

        blnHit = true;
        intLocation = i;

      }
    }
  }

  public void keyPressed() {
    if (keyPressed) {

      if (key == 'd' || key =='D') {

        MoveRight = true;

      } if (key == 'a' || key == 'A') {

        MoveLeft = true;

      } if (keyCode == LEFT) {

        MoveLeft = true;

      } if (keyCode == RIGHT) {

        MoveRight = true;

      }
    }
  }

  public void keyReleased() {
 
    if (key == 'd' || key =='D') {

      MoveRight = false;

    } if (key == 'a' || key == 'A') {

      MoveLeft = false;

    } if (keyCode == LEFT) {

      MoveLeft = false;

    } if (keyCode == RIGHT) {

      MoveRight = false;
      
    }
  }
}
