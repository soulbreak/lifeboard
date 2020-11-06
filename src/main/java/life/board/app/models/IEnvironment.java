package life.board.app.models;

import life.board.app.objects.IMovable;
import life.board.app.maps.IBooleanGrid;

import java.util.List;

public interface IEnvironment {
    /**
     * Pre-actions, init the environment
     */
    void initialize();

    /**
     * Call nextStep until we reach the stepNumber
     * @param stepNumber number of step to run
     */
    void goToStep(long stepNumber);

    /**
     * Run a single step
     */
    void runStep();

    /**
     * Increment step by 1
     */
    void incrementStep();

    /**
     * Get current step
     * @return current step
     */
    long getStep();

    /**
     *
     * @param movable : add object in the environment
     */
    void addMovables(List<IMovable> movable);

    /**
     *
     * @return Movables objects
     */
    List<IMovable> getMovables();

    /**
     *
     * @param map : set the map of the environment
     */
    void setMap(IBooleanGrid map);

    /**
     *
     * @return the map of the environment
     */
    IBooleanGrid getMap();

    /**
     * Get last goToStep function time in nanoseconds
     * @return number of nanosecond(s) taken by the last run of GoToStep function
     */
    long getLastGoToStepTime();

}
