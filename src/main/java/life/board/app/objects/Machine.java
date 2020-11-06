package life.board.app.objects;


import life.board.app.models.IEnvironment;

public class Machine extends AbstractObject {

    public Machine(IEnvironment env, int x, int y, int o){
        super(env, x, y, o);
    }

    public void act(){
        boolean initialValue = this.getEnvironment().getMap().getValue(this.getXPosition(), this.getYPosition());
        // If the intial value is white turn 90° clockwise
        if(!initialValue){
            this.addOrientation(90);
            // If the intial value is white turn 90° counter-clockwise
        } else {
            this.addOrientation(-90);
        }
        this.moveForward(1);
    }
}
