import processing.core.PApplet;

/**
 * Processing Task 8
 * Author: Joshua Chan
 */
public class Sketch extends PApplet {

  /**
   * Sets the size of the window.
   */
  public void settings() {
    size(800, 800);
  }

  /**
   * Sets up the initial background color when the sketch starts.
   */
  public void setup() {
    background(210, 255, 173);
  }

  /**
   * Draws continuous elements or animations.
   */
  public void draw() {
    // Sample continuous element (e.g., moving clouds)
    drawClouds(); // Moving clouds

    // Other elements based on user input (mouse and keyboard)
    // You might not need code for all input events within the draw function,
    // only those elements that need continuous rendering or updates.
  }

  /**
   * Creates flowers at the mouse position upon mouse click.
   * @param mouseX The x-coordinate of the mouse.
   * @param mouseY The y-coordinate of the mouse.
   */
  public void mouseClicked() {
    drawFlower(mouseX, mouseY); // Flower shape
  }

  /**
   * Draws grass by drawing lines while dragging the mouse.
   * @param pmouseX The previous x-coordinate of the mouse.
   * @param pmouseY The previous y-coordinate of the mouse.
   * @param mouseX The current x-coordinate of the mouse.
   * @param mouseY The current y-coordinate of the mouse.
   */
  public void mouseDragged() {
    drawGrass(pmouseX, pmouseY, mouseX, mouseY); // Draw grass while dragging
  }

  /**
   * Adds butterflies at the mouse position when the mouse wheel is used.
   * @param mouseX The x-coordinate of the mouse.
   * @param mouseY The y-coordinate of the mouse.
   */
  public void mouseWheel() {
    drawButterfly(mouseX, mouseY); // Butterfly shape
  }

  /**
   * Changes the background/sky color based on keyboard input.
   * Pressing 'b' or 'B' changes to a blue sky, 'g' or 'G' changes to green grass.
   */
  public void keyPressed() {
    if (key == 'b' || key == 'B') {
      background(0, 0, 255); // Blue sky
    } else if (key == 'g' || key == 'G') {
      background(0, 255, 0); // Green grass
    }
  }

  /**
   * Draws clouds with a smoother shape.
   */
  private void drawClouds() {
    noStroke();
    fill(255); // White clouds
    ellipse(frameCount % width, 50, 50, 20); // Moving ellipse representing clouds
  }

  /**
   * Draws a flower at a given position.
   * @param x The x-coordinate of the flower.
   * @param y The y-coordinate of the flower.
   */
  private void drawFlower(float x, float y) {
    int[] colors = { color(200, 51, 73), color(0, 102, 204), color(255, 204, 0) }; // Red, blue, and yellow
    int randomColorIndex = (int) random(0, colors.length);
    fill(colors[randomColorIndex]); // Random flower color
    float petalSize = 20;
    for (int i = 0; i < 8; i++) {
        float angle = i * TWO_PI / 8;
        float petalX = x + cos(angle) * petalSize;
        float petalY = y + sin(angle) * petalSize;
        ellipse(petalX, petalY, petalSize, petalSize);
    }
    fill(255, 255, 0); // Center of the flower
    ellipse(x, y, 10, 10);
}

  /**
   * Draws grass at the current and previous mouse positions.
   * @param prevX The previous x-coordinate of the mouse.
   * @param prevY The previous y-coordinate of the mouse.
   * @param currentX The current x-coordinate of the mouse.
   * @param currentY The current y-coordinate of the mouse.
   */
  private void drawGrass(float prevX, float prevY, float currentX, float currentY) {
    stroke(0, 150, 0); // Green color for grass
    line(prevX, prevY, currentX, currentY); // Draw grass while dragging
  }

  /**
   * Draws a butterfly at a given position.
   * @param x The x-coordinate of the butterfly.
   * @param y The y-coordinate of the butterfly.
   */
  private void drawButterfly(float x, float y) {
    fill(random(100, 220)); // Yellow color for butterflies
    ellipse(x, y, 15, 10); // Butterfly body
    fill(random(0,256)); // Blue color for wings
    float wingSize = 30;
    float wingOffset = 15;
    ellipse(x - wingOffset, y, wingSize, (float) (wingSize * 1.2)); // Left wing
    ellipse(x + wingOffset, y, wingSize, (float) (wingSize * 1.2)); // Right wing
  }
}
