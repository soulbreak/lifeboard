package life.board.app.simulation;

import life.board.app.configuration.settings;
import life.board.app.http.SimulationRequest;
import life.board.app.maps.IBooleanGrid;
import life.board.app.maps.IGridProperties;
import life.board.app.maps.MatrixBooleanGrid;
import life.board.app.models.FlipEnvironment;
import life.board.app.models.IEnvironment;
import life.board.app.objects.IMovable;
import life.board.app.objects.Machine;


import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Class used by http api /simulation/step
 * Many thing hardcoded here, exposing parameters to the api is easy
 */
public class FlipSimulation implements ISimulation {
    final private IBooleanGrid map;
    final private IEnvironment env;
    final private SimulationRequest simulationRequest;
    private String details;
    private String outputFolder;
    private String resultOutput;



    public FlipSimulation(SimulationRequest simulationRequest) {
        // Some parameters are hardcoded but can be easily exposed
        this.simulationRequest = simulationRequest;
        this.setOutputFolder(settings.getSimulationFolder());
        this.map = new MatrixBooleanGrid(this.simulationRequest.getMatrixSize());
        this.env = new FlipEnvironment(map);
        ArrayList<IMovable> machines = new ArrayList<>();
        machines.add(new Machine(env,0,0, 90));
        env.addMovables(machines);
    }

    @Override
    public boolean run() {

        Path p = java.nio.file.Paths.get(this.getOutputFolder(), this.simulationRequest.getStep() + ".png");
        this.setResultPath(p.toString());
        this.env.goToStep(this.simulationRequest.getStep());
        IGridProperties properties = map.saveToDisk(this.getResultPath(),
                this.simulationRequest.getCellResolution(),
                this.simulationRequest.getDrawingLibrary(),
                this.simulationRequest.isWithGrid());
        this.setDetails(properties.getDetails());
        return !properties.error();
    }

    @Override
    public String getOutputFolder() {
        return this.outputFolder;
    }

    @Override
    public void setOutputFolder(String path){
        this.outputFolder = path;
    }

    @Override
    public void setResultPath(String pathname) {
        this.resultOutput = pathname;
    }

    @Override
    public String getResultPath() {
        return this.resultOutput;
    }

    @Override
    public IEnvironment getEnvironment() {
        return this.env;
    }

    @Override
    public String getDetails() {
        return this.details;
    }

    @Override
    public void setDetails(String details) {
        this.details = details;
    }
}
