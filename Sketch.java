import processing.core.PApplet;

public class Sketch extends PApplet {
	
  // arrays for the lcation for each snow flake and their visibility 
  float [] floatSnowY = {-400, -325, -300, -275, -220, -50, -200, -125, -250, -150, -250, -150, -350, -50, -350, -315, -255, -375, -175, 0};
	float [] floatSnowX = {50, 100, 150, 250, 300, 350, 400, 450, 500, 550, 10, 350, 109, 215, 267, 390, 256, 500, 541, 600};
  boolean [] hideSnowFlakeStatus = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};

  // boolean values to see if a snow flack has been clicked or if the blue circle is being moved 
  boolean blnHit = false;
  boolean MoveRight = false;
  boolean MoveLeft = false;
  
  // radius checks for both circle to circle collision and clicking 
  double dblClickingRadius = 20;
  double dblRadius = 24;

  // locations of the circle 
  int intCircleSize = 30;
  int intLocation = 0;
  int intCircleX = 300;
  int intCircleY = 535;

  // amount of health 
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
      
    // checks if player health if over 1
    if (intHealth > 0) {

      background(0);

      // chekcs the hp of the player left and prints out the needed hearts
      if (intHealth == 3) {

        fill(255,0,0);
        rect(5,5,8,8);
        rect(16,5,8,8);
        rect(26,5,8,8);

      } else if (intHealth == 2) {

        fill(255,0,0);
        rect(5,5,8,8);
        rect(16,5,8,8);
        
      } else if (intHealth == 1) {

        fill(255,0,0);
        rect(5,5,8,8);

      }
    
      // uses a for loop to locate the starting positiosn of each snowflake 
      for (int i = 0; i < 20; i++) {  
    
        // sees if the player cirlce is touching a snowflake 
        if (dist(intCircleX, intCircleY,floatSnowX[i],floatSnowY[i]) <= dblRadius) {

          // decresaes the health by 1 and sends the snowflake back up to the top to prevent the player from instnatly dieing 
          intHealth -= 1;
          floatSnowY[i] = 0;

        }

        // checks the visibility of the snow flake before loading it 
        if (hideSnowFlakeStatus[i] == false) {
      
          fill(255);
        
          ellipse(floatSnowX[i],floatSnowY[i],intCircleSize,intCircleSize);
          floatSnowY[i] += 3;
        
          // sends the snowflake back to the top once it reaches the bottom of the canvas
          if (floatSnowY[i] > height) {

            floatSnowY[i] = 0;
    
          }
        }

        // uses this to set the visibility of the snowflake to invisible if the user has clicked it with their moues 
        if (blnHit == true) {

          hideSnowFlakeStatus[intLocation] = true;

        }

        fill(0,0,255);
        ellipse(intCircleX,intCircleY,intCircleSize, intCircleSize);

        // adjusts the location of the player circle 
        if (MoveRight == true) {

          intCircleX = intCircleX + 1;

        } if (MoveLeft == true) {

          intCircleX = intCircleX - 1;

        }

        // preventes the player circle from going beyond the left and right bounds of the canvas 
        if (intCircleX < 12.5) {

          intCircleX = (int) 12.5;

        } else if (intCircleX > width - 12.5) {
          
          intCircleX = (int) (width - 12.5);

        } 
      }

    } else {

      // sets the background white and prints ggs once the player has no more lives left 
      background(255);
      fill(0);
      text("Good Game", height / 2, width / 2);

    }
  }
  

  public void mousePressed() {
    
    // makes this method is looking at the same set of information in the array as the draw method 
    for (int i = 0; i < 20; i++) {
      
      // determines if the location of the moues is touching any snowflake 
      if (dist((float) mouseX, (float) mouseY,floatSnowX[i],floatSnowY[i]) < dblClickingRadius) {

        // if the moues is touching a snow flake, then it will change the visiblity of that snowflake 
        blnHit = true;
        intLocation = i;

      }
    }
  }

  public void keyPressed() {

    // first checks if any keys are pressed 
    if (keyPressed) {

      // determines if the key is one of the following and will move the circle accordingly 
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

  // makes sure that once the key is released, that the player circle does not continue to move left or right 
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
