import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Block extends Rectangle {
    Image pic; // Image associated with the block
    private int addX = 3; // Horizontal speed of the block
    private int addY = -3; // Vertical speed of the block
    Rectangle left, right; // Rectangles representing the left and right sides for collision detection
    boolean destroyed = false; // Flag indicating if the block is destroyed

    public static int speed = 12; // Speed of the block
    public static int red = 100; // Red color component for block
    public static int green = 140; // Green color component for block
    public static int blue = 200; // Blue color component for block

    // Constructor for any block in the game
    public Block(int PlaceX, int PlaceY, int width, int height, String picName) {
        this.x = PlaceX; // Set the x-coordinate
        this.y = PlaceY; // Set the y-coordinate
        this.width = width; // Set the width
        this.height = height; // Set the height
        this.left = new Rectangle(PlaceX - 1, PlaceY, 1, height); // Initialize the left side for collision detection
        this.right = new Rectangle(PlaceX + width + 1, PlaceY, 1, height); // Initialize the right side for collision detection
        this.pic = Toolkit.getDefaultToolkit().getImage(picName); // Load the image for the block
    }

    // Set the x-coordinate of the block
    public void setPlaceX(int x) {
        this.x = x;
    }

    // Set the y-coordinate of the block
    public void setPlaceY(int y) {
        this.y = y;
    }

    // Set the vertical speed of the block
    public void setAddY(int addY) {
        this.addY = addY;
    }

    // Get the vertical speed of the block
    public int getAddY() {
        return this.addY;
    }

    // Set the horizontal speed of the block
    public void setAddX(int addX) {
        this.addX = addX;
    }

    // Get the horizontal speed of the block
    public int getAddX() {
        return this.addX;
    }

    // Set the width of the block
    public void setWidth(int width) {
        this.width = width;
    }

    // Set the image for the block
    public void setImage(String name) {
        this.pic = Toolkit.getDefaultToolkit().getImage(name);
    }

    // Draw the block on the screen
    public void draw(Graphics g, Component comp) {
        if (!this.destroyed) {
            g.setColor(new Color(red, green, blue)); // Set the color for the block
            g.fillRect(0, 0, 1000, 1000); // Fill the background with the specified color
            g.drawImage(this.pic, this.x, this.y, this.width, this.height, comp); // Draw the block image
        }
    }

    // Draw the player brick on the screen
    public void drawPlayerBrick(Graphics g, Component comp) {
        if (!this.destroyed) {
            g.drawImage(this.pic, this.x, this.y, this.width, this.height, comp); // Draw the player brick image
        }
    }

    // Draw the brick board on the screen
    public void drawBrickBoard(Graphics g, Component comp) {
        if (!this.destroyed) {
            g.drawImage(this.pic, this.x, this.y, this.width, this.height, comp); // Draw the brick board image
        }
    }
}
