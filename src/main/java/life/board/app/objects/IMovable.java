package life.board.app.objects;


/**
 * We can imagine that we would like to create immobile object in the future, such as obstacle
 * That's why IMovable and and IObject are distinct interface.
 */
public interface IMovable extends IObject {
    /**
     *
     * @return x coordinate
     */
    int getXPosition();

    /**
     *
     * @return y coordinate
     */
    int getYPosition();

    /**
     * Place the object at this coordinate
     * @param x coordonate of x
     * @param y coordonate of y
     */
    void setPosition(int x, int y);

    /**
     * The object move forward of m unit in the looking direction
     * A positive integer means move forward
     * A negative integer means move backward
     */
    void moveForward(int m);

    /**
     *
     * @return a value from 0 to 359 (degree). 
     * This program using a grid, only 4 value can be returned :
     * 90 means that the object is looking on the right side,
     * 180 looking down
     * 270 looking left 
     * 0 looking up 
     */
    int getOrientation();

    /**
     * Set the orientation of the object
     * @param d d value to set to orientation
     */
    void setOrientation(int d);

    /**
     * Add d degree to the object orientation
     * @param d value to add to orientation
     */
    void addOrientation(int d);


}
