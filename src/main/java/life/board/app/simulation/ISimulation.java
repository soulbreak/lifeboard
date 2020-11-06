package life.board.app.simulation;

import life.board.app.models.IEnvironment;


public interface ISimulation {

    /**
     * Run the simulation
     * @return True if simulation is successful otherwise False
     */
    boolean run();

    /**
     * String representation of the result location
     * @return the location of the result
     */
    String getOutputFolder();

    void setOutputFolder(String path);

    /**
     * Set the folder in which the result will be saved
     * @param pathname destination folder
     */
    void setResultPath(String pathname);

    String getResultPath();

    IEnvironment getEnvironment();

    String getDetails();

    void setDetails(String details);

}
