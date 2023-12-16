import processing.core.PApplet;

/**
 * To run a snowflake game where the player must dodge all the snows flakes, the player can click a snowflake to make it dissapear 
 * @author Preston Wong
 * 
 */

public class Sketch extends PApplet {
	
  // arrays for the location for each snow flake and their visibility 
  float [] floatSnowY = {-400, -325, -300, -275, -220, -50, -200, -125, -250, -150, -250, -150, -350, -50, -350, -315, -255, -375, -175, 0};
	float [] floatSnowX = {50, 100, 150, 250, 275, 350, 50, 220, 300, 350, 10, 250, 109, 215, 267, 320, 256, 200, 241, 375};
  boolean [] blnHideSnowFlakeStatus = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};

  // boolean values to see if a snow flack has been clicked, if the blue circle is being moved, or for the speed at which the snowflakes fall at  
  boolean blnHit = false;
  boolean blnMoveRight = false;
  boolean blnMoveLeft = false;
  boolean blnFallSlower = false;
  boolean blnFallFaster = false;
  
  // sets the max distance away that you can be hit, or that you can click a snowflake 
  double dblClickingRadius = 20;
  double dblRadius = 24;

  // variable that contorls how fast the snowflakes fall 
  int intSpeed = 3;

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
    size(400, 550);

  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {

    // sets the background to black before starting the game 
    background(0);
    
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
      
    // checks if player health if over 1
    if (intHealth > 0) {

      background(0);

      // chekcs the hp of the player left and prints out the needed squares
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
        if (blnHideSnowFlakeStatus[i] == false) {

          // sets the background black
          fill(255);

          // draws the snowflakes 
          ellipse(floatSnowX[i],floatSnowY[i],intCircleSize,intCircleSize);
          floatSnowY[i] += intSpeed;

          // draws the player blue circle 
          fill(0,0,255);
          ellipse(intCircleX, intCircleY, intCircleSize, intCircleSize);

          // adjusts how fast the snowflakes fall 
          if (blnFallFaster == true) {

            intSpeed += 1;

          } else if (blnFallSlower == true) {
            
            intSpeed -= 1;

          }

          // sets limits to how slow or how fast the snowflakes can move 
          if (intSpeed < 1) {

            intSpeed = 1;

          } else if (intSpeed > 10) {

            intSpeed = 10;

          }
        
          // sends the snowflake back to the top once it reaches the bottom of the canvas
          if (floatSnowY[i] > height) {

            floatSnowY[i] = 0;
    
          }
        }

        // uses this to set the visibility of the snowflake to invisible if the user has clicked it with their moues 
        if (blnHit == true) {

          blnHideSnowFlakeStatus[intLocation] = true;

        }

        // adjusts the location of the player circle 
        if (blnMoveRight == true) {

          intCircleX = intCircleX + 1;

        } if (blnMoveLeft == true) {

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
      text("Good Game", width / 2, height / 2);

    }
  }

  /**
   * sees if the mouse button has been pressed down and it then checks to see if the location of the cursor is over a snowflake 
   */
  public void mousePressed() {
    
    // makes this method is looking at the same set of information in the array as the draw method 
    for (int i = 0; i < 20; i++) {
      
      // determines if the location of the moues is touching any snowflake 
      if (dist((float) mouseX, (float) mouseY, floatSnowX[i], floatSnowY[i]) < dblClickingRadius) {

        // if the moues is touching a snow flake, then it will change the visiblity of that snowflake 
        blnHit = true;
        intLocation = i;

      }
    }
  }

  /**
   * checks to see if keyboard keys have been pressed, and there have been, it checks to see if certain ones have been pressed and changes the corresponding boolean value to ture 
   */
  public void keyPressed() {

    // first checks if any keys are pressed 
    if (keyPressed) {

      // determines if one of the following keys are pressed and will turn that boolean value to true 
      if (key == 'd' || key =='D') {

        blnMoveRight = true;

      } if (key == 'a' || key == 'A') {

        blnMoveLeft = true;

      } if (keyCode == LEFT) {

        blnMoveLeft = true;

      } if (keyCode == RIGHT) {

        blnMoveRight = true;
 
      } if (keyCode == UP) {
        
        blnFallSlower = true;

      } if (keyCode == DOWN) {

        blnFallFaster = true;

      }
    }
  }

  /**
   * sees if a keyboard key has been released, it then sees which one has been released and then changes that corresponding boolean value to false 
   */
  public void keyReleased() {
 
    if (key == 'd' || key =='D') {

      blnMoveRight = false;

    } if (key == 'a' || key == 'A') {

      blnMoveLeft = false;

    } if (keyCode == LEFT) {

      blnMoveLeft = false;

    } if (keyCode == RIGHT) {

      blnMoveRight = false;
      
    } if (keyCode == UP) {
        
      blnFallSlower = false;

    } if (keyCode == DOWN) {

      blnFallFaster = false;

    }
  }
}
