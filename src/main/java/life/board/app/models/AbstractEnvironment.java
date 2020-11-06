package life.board.app.models;

import life.board.app.objects.IMovable;
import life.board.app.maps.IBooleanGrid;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEnvironment implements IEnvironment {
    private long step;
    private IBooleanGrid map;
    final private ArrayList<IMovable> movables = new ArrayList<>();
    private long duration = 0;

    public AbstractEnvironment(IBooleanGrid map){
        this.setMap(map);
        this.initialize();
    }

    @Override
    abstract public void runStep();

    @Override
    public void initialize() {
        this.step = 0;
    }

    @Override
    public void goToStep(long stepNumber) {
        long startTime = System.nanoTime();
        while (this.step < stepNumber) {
            this.runStep();
        }
        long stopTime = System.nanoTime();
        this.duration = (stopTime - startTime);
    }

    @Override
    public long getStep() {
        return this.step;
    }

    @Override
    public void addMovables(List<IMovable> movable) {
        this.movables.addAll(movable);
    }

    @Override
    public List<IMovable> getMovables() {
        return this.movables;
    }

    @Override
    public void setMap(IBooleanGrid map) {
        this.map = map;
    }

    @Override
    public IBooleanGrid getMap() {
        return this.map;
    }

    @Override
    public void incrementStep() {
        this.step++;
    }

    @Override
    public long getLastGoToStepTime() { return this.duration; }

}
