package life.board.app.http;

public class SimulationResponse {
    private  String details;
    private  String msg;
    private  SimulationRequest simulationRequest;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public SimulationResponse(String msg, String details){
        this.msg = msg;
        this.details = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public SimulationRequest getSimulationRequest() {
        return simulationRequest;
    }

    public void setSimulationRequest(SimulationRequest simulationRequest) {
        this.simulationRequest = simulationRequest;
    }

    @Override
    public String toString() {
        return "SimulationResponse{" +
                "details='" + details + '\'' +
                ", msg='" + msg + '\'' +
                ", simulationRequest=" + simulationRequest +
                '}';
    }
}
