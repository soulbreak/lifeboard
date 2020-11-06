package life.board.app.models;

import life.board.app.objects.IMovable;
import life.board.app.maps.IBooleanGrid;

/**
 * FlipEnvironment role is to launch act on all movables objects and flip the color of their origin positions
 */
public class FlipEnvironment extends AbstractEnvironment {
    /**
     * Constructor in which we inject the map in the Environment
     * @param map to inject in the environment
     */
    public FlipEnvironment(IBooleanGrid map){
        super(map);
    }

    @Override
    public void runStep() {
        for(IMovable m: this.getMovables()){
            // Get the initial coordinate of the object
            int x = m.getXPosition();
            int y = m.getYPosition();
            boolean value = this.getMap().getValue(x, y); // Get the value (White=0, Black=1)
            m.act(); // The act based on its implementation (with unique Machine Implementation:rotate and move)
            // If White set Black
            this.getMap().setValue(x, y, !value); // If Black set White, if White set Black
        }
        this.incrementStep();
    }
}
