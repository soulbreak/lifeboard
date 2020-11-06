package life.board.app.objects;

import life.board.app.models.IEnvironment;

/**
 * Common interface for all existing objects in the environment
 */
public interface IObject {
    /**
     *
     * @param environment : An object cannot be created without the environment context
     *                    The environment also inform the Object about its
     */
    void setEnvironment(IEnvironment environment);

    /**
     *
     * @return the environment of the object
     */
    IEnvironment getEnvironment();

    /**
     * This method is called by the environment at each step
     */
    void act();
}
