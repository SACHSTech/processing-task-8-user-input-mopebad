import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.MouseEvent;

public class Sketch extends PApplet {

  // Images for background, flowers, and dog
  PImage imgBackground;
  PImage imgFlower;
  PImage imgBackground2;
  PImage imgDog;

  // ArrayLists to store positions of flowers and raindrops
  ArrayList<PVector> flowers = new ArrayList<PVector>();
  ArrayList<Raindrop> raindrops = new ArrayList<Raindrop>();

  // boolean for various actions
  boolean isDragging = false;
  boolean isFlowerClicked = false;

  // Sun size and location variables
  int intSunSizeX = 30;
  int intSunSizeY = 30;
  int intSunX = 20;
  int intSunY = 20;

  // Variables to manage sun movement
  boolean isUpPressed = false;
  boolean isDownPressed = false;
  boolean isRightPressed = false;
  boolean isLeftPressed = false;
  boolean isWPressed = false;
  boolean isDPressed = false;
  boolean isSPressed = false;
  boolean isAPressed = false;

  // Variable for the size of the dog image
  int imgSize = 50;

  /**
   * Sets the size of the sketch window.
   */
  public void settings() {
    size(1000, 850);
  }

  /**
   * Initializes the sketch with necessary setup.
   */
  public void setup() {
    background(255);
    imgBackground = loadImage("white.png");
    imgFlower = loadImage("flower.png");
    imgFlower.resize(50, 0);
    imgBackground2 = loadImage("green.png");
    imgDog = loadImage("pepsi.jpg");
    imgDog.resize(imgSize, imgSize);
  }

  /**
   * Draws elements on the screen continuously.
   */
  public void draw() {
    // Display background images
    image(imgBackground2, 0, height / 2, width, height / 2);
    image(imgBackground, 0, 0, width, height / 2);
    image(imgDog, mouseX - imgSize / 2, mouseY - imgSize / 2);

    // Draw the sun
    noStroke();
    fill(255, 223, 34);
    ellipse(intSunX, intSunY, intSunSizeX, intSunSizeY);

    // Handle adding flowers on click
    if (isFlowerClicked && mouseY > height / 2) {
      flowers.add(new PVector(mouseX, mouseY));
      isFlowerClicked = false;
    }

    // Display flowers
    for (PVector pos : flowers) {
      image(imgFlower, pos.x - imgFlower.width / 8, pos.y - imgFlower.height / 8);
    }

    // Display raindrops
    for (int i = raindrops.size() - 1; i >= 0; i--) {
      Raindrop r = raindrops.get(i);
      r.update();
      r.display();
    }

    // Sets boundaries for where the sun can be
    if (intSunX > width) {
      intSunX = width;
    } else if (intSunX < 0) {
      intSunX = 0;
    } else if (intSunY > 220) {
      intSunY = 220;
    } else if (intSunY < 0) {
      intSunY = 0;
    }

    // Handle multiple key presses for sun movement
    if (isUpPressed) {
      intSunSizeY += 2;
    }
    if (isDownPressed) {
      intSunSizeY -= 2;
    }
    if (isLeftPressed) {
      intSunSizeX -= 2;
    }
    if (isRightPressed) {
      intSunSizeX += 2;
    }

    // Limit the size of the sun
    if (intSunSizeX > 100) {
      intSunSizeX = 100;
    } else if (intSunSizeY > 100) {
      intSunSizeY = 100;
    } else if (intSunSizeY < -100) {
      intSunSizeY = -100;
    } else if (intSunSizeX < -100) {
      intSunSizeX = -100;
    }

    // Use keys to change the location of the sun
    if (isDPressed) {
      intSunX += 10;
    }
    if (isSPressed) {
      intSunY += 10;
    }
    if (isAPressed) {
      intSunX -= 10;
    }
    if (isWPressed) {
      intSunY -= 10;
    }

    // Draw a flower image at the mouse click position if isFlowerClicked is true
    if (isFlowerClicked) {
      image(imgFlower, mouseX - imgFlower.width / 8, mouseY - imgFlower.height / 8);
    }

  }

  /**
   * Handles key press events to set corresponding movement boolean.
   */
  public void keyPressed() {
    if (keyPressed) {
      if (keyCode == UP) {
        isUpPressed = true;
      } else if (keyCode == DOWN) {
        isDownPressed = true;
      } else if (keyCode == RIGHT) {
        isRightPressed = true;
      } else if (keyCode == LEFT) {
        isLeftPressed = true;
      } else if (key == 'w' || key == 'W') {
        isWPressed = true;
      } else if (key == 'd' || key == 'D') {
        isDPressed = true;
      } else if (key == 's' || key == 'S') {
        isSPressed = true;
      } else if (key == 'a' || key == 'A') {
        isAPressed = true;
      }
    }
  }

  /**
   * Handles key release events to reset corresponding movement boolean.
   */
  public void keyReleased() {
    if (keyCode == UP) {
      isUpPressed = false;
    } else if (keyCode == DOWN) {
      isDownPressed = false;
    } else if (keyCode == RIGHT) {
      isRightPressed = false;
    } else if (keyCode == LEFT) {
      isLeftPressed = false;
    } else if (key == 'w' || key == 'W') {
      isWPressed = false;
    } else if (key == 'd' || key == 'D') {
      isDPressed = false;
    } else if (key == 's' || key == 'S') {
      isSPressed = false;
    } else if (key == 'a' || key == 'A') {
      isAPressed = false;
    }
  }

  /**
   * Function to handle mouse click events to set the isFlowerClicked flag to
   * true.
   */
  public void mousePressed() {
    isFlowerClicked = true;
  }

  /**
   * Function to handle mouse release events to set the isFlowerClicked flag to
   * false.
   */
  public void mouseReleased() {
    isFlowerClicked = false;
  }

  /**
   * Function to handle mouse drag events (rain simulation).
   */
  public void mouseDragged() {
    // Create raindrops for the entire canvas
    isDragging = true;
    for (int i = 0; i < 1; i++) { // Create multiple raindrops per frame
      raindrops.add(new Raindrop(mouseX, mouseY));
    }
  }

  /**
   * Function to adjust the size of the image based on mouse movement.
   */
  public void mouseMoved() {
    imgSize = (int) map(mouseX + mouseY, 0, width + height, 20, 100);
  }

  /**
   * Function to handle mouse wheel events.
   * 
   * @param event MouseEvent object containing scroll information.
   */
  public void mouseWheel(MouseEvent event) {
    int scrollDirection = event.getCount();
    int numRaindrops = 1; // Number of raindrops to add/remove

    if (scrollDirection > 0) {
      for (int i = 0; i < numRaindrops; i++) {
        raindrops.add(new Raindrop(random(width), random(height)));
      }
    } else {
      for (int i = 0; i < numRaindrops; i++) {
        if (!raindrops.isEmpty()) {
          raindrops.remove(0);
        }
      }
    }
  }

  /**
   * Raindrop class for raindrop properties and behavior.
   */
  class Raindrop {
    PVector position;
    float speed;

    /**
     * Constructor to initialize a raindrop.
     * 
     * @param x X-coordinate of raindrop.
     * @param y Y-coordinate of raindrop.
     */
    Raindrop(float x, float y) {
      position = new PVector(x, y);
      speed = random(6, 10);
    }

    /**
     * Updates raindrop's position.
     */
    void update() {
      position.y += speed;
      // Remove raindrops that have moved off-screen
      if (position.y > height) {
        raindrops.remove(this);
      }
    }

    /**
     * Displays the raindrop on the screen.
     */
    void display() {
      stroke(0, 128, 255); // Raindrop color
      strokeWeight(2);
      line(position.x, position.y, position.x, position.y + 10); // Adjust length for raindrop appearance
    }
  }
}
