package life.board.app.configuration;

public class settings {
    public static String SIMULATION_FOLDER;

    public static String getSimulationFolder(){
        SIMULATION_FOLDER = System.getenv("SIMULATION_FOLDER");
        if(SIMULATION_FOLDER == null){
            SIMULATION_FOLDER = System.getProperty("user.dir");
        }
        return SIMULATION_FOLDER;
    }
}
