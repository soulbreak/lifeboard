package life.board.app.objects;

import life.board.app.models.IEnvironment;

public abstract class AbstractObject implements IMovable {
    private int xPosition;
    private int yPosition;
    private int orientation;
    private IEnvironment environment;

    public AbstractObject(IEnvironment env, int x, int y, int o){
        this.setEnvironment(env);
        this.setPosition(x,y);
        this.setOrientation(o);
    }

    /**
     * What an object do at each is the responsibility of the concret class
     * It defines its behaviour
     */
    @Override
    abstract public void act();

    @Override
    public void moveForward(int m) {
        if(this.getOrientation() == 0){
            this.setPosition(this.getXPosition(),this.getYPosition() + m);
        } else if(this.getOrientation() == 90) {
            this.setPosition(this.getXPosition() + m,this.getYPosition());
        } else if(this.getOrientation() == 180) {
            this.setPosition(this.getXPosition(),this.getYPosition() - m);
        } else if(this.getOrientation() == 270) {
            this.setPosition(this.getXPosition() - m,this.getYPosition());
        } else {
            // Grid system is limited to these orientation, do nothing...or raise an Exception ?
        }
    }

    @Override
    public int getXPosition() {
        return this.xPosition;
    }
    @Override
    public int getYPosition() {
        return this.yPosition;
    }

    @Override
    public void setPosition(int x, int y) {
        this.xPosition = x;
        this.yPosition = y;
    }

    @Override
    public int getOrientation() {
        return this.orientation;
    }

    @Override
    public void setOrientation(int o) {
        this.orientation = o % 360;
    }

    @Override
    public void addOrientation(int o) {
        this.setOrientation(this.getOrientation() + o);
    }

    @Override
    public void setEnvironment(IEnvironment environment) {
        this.environment = environment;
    }

    @Override
    public IEnvironment getEnvironment() {
        return this.environment;
    }



}
