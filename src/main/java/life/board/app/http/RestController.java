package life.board.app.http;


import com.google.gson.Gson;
import life.board.app.simulation.FlipSimulation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/lifeboard/api")
public class RestController {
    Logger logger = LoggerFactory.getLogger(RestController.class);
    @GetMapping("/help")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("Please, contact the developper");
    }

    @RequestMapping(value = "/simulation/step",
            method = RequestMethod.PUT,
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<SimulationResponse> SimulationRequest(HttpEntity<String> httpEntity) {
        String json = httpEntity.getBody();
        Gson gson = new Gson();
        SimulationRequest simulationRequest = gson.fromJson(json, SimulationRequest.class);
        logger.info("New request : " + simulationRequest.toString());
        FlipSimulation flipSimulation = new FlipSimulation(simulationRequest);
        boolean simulationSuccess = flipSimulation.run();
        SimulationResponse simulationResponse = new SimulationResponse("Running step "+ simulationRequest.getStep(), "NA");
        simulationResponse.setSimulationRequest(simulationRequest);
        if(simulationSuccess){
            logger.info("Simulation OK : " + simulationResponse.toString());
            simulationResponse.setMsg("Simulation OK");
            simulationResponse.setDetails("Simulation performed in "
                    + (flipSimulation.getEnvironment().getLastGoToStepTime() / 1000000)
                    + " milliseconds is stored in " +  flipSimulation.getResultPath());
            return ResponseEntity.status(HttpStatus.OK).body(simulationResponse);
        } else {
            logger.error(flipSimulation.getDetails());
            simulationResponse.setMsg("Simulation Error");
            // Stay evasive, technical error  should be secret
            simulationResponse.setDetails("Something bad happens during the simulation. " +
                    "Please contact your technical support : " + flipSimulation.getDetails());
            return ResponseEntity.status(HttpStatus.OK).body(simulationResponse);
        }

    }
}