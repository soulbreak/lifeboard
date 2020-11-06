package life.board.app;

import life.board.app.maps.IGridProperties;
import life.board.app.maps.MatrixBooleanGrid;
import life.board.app.objects.IMovable;
import life.board.app.objects.Machine;
import life.board.app.models.FlipEnvironment;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class example {

    public static void main(String[] args) {
        long stepToRun;
        Scanner input = new Scanner(System.in);
        //MatrixBooleanGrid map = new MatrixBooleanGrid(65536);
        MatrixBooleanGrid map = new MatrixBooleanGrid(65536);
        FlipEnvironment env = new FlipEnvironment(map);
        // Initial position is x = 0, y = 0, 90° (look at the right side)
        Random random = new Random();
        String j = System.getenv("SIMU");

        ArrayList<IMovable> machines = new ArrayList<>();
        //int x, y;
        int numberOfMachines = 0;
        ///* Generate some random machines */
        //for(int i= 0; i < numberOfMachines;i++){
        //    x = random.nextInt(20);
        //    y = random.nextInt(20);
        //    machines.add(new Machine(env,x,y, 90));
        //}
        machines.add(new Machine(env,0,0, 90));
        env.addMovables(machines);
        //Generate 10 first image
        //for(int a=0; a<10; a++){
        //    env.runStep();
        //    Path p = java.nio.file.Paths.get("C:\\Users\\Documents\\DEV\\buildOut\\artifacts\\app_jar",
        //            String.valueOf(env.getStep()) + ".png");
        //    map.saveToDisk(p.toString(), 1, true);
        //    IGridProperties properties = map.saveToDisk(p.toString(), 1,false);
        //    System.out.println(properties.toString());
        //    System.out.println("Image generated in " + p.toString());
        //}

        System.out.println("Number of step to run ?");
        stepToRun = input.nextLong();
        env.goToStep(stepToRun);
        System.out.println(stepToRun + " step(s) in " + (env.getLastGoToStepTime() / 1000000) + " milliseconds");
        Path p = java.nio.file.Paths.get("C:\\Users\\Documents\\DEV\\buildOut\\artifacts\\app_jar",
                String.valueOf(env.getStep()) + ".png");
        IGridProperties properties = map.saveToDisk(p.toString(), 5,"javaawt", false);
        System.out.println("Image properties : " + properties.toString());
        if(properties.error()){
            System.out.println("Error : " + properties.getDetails());
        }

        // CmdLine to generate a video :
        // ffmpeg.exe  -pattern_type sequence -i '%d.png' -c:v libx264 -vf fps=25 -pix_fmt yuv420p out.mp4
        // Dynamic draw with for video
        stepToRun = input.nextInt();
        System.out.println("Number of step to run ?");
        for(long a=0; a<stepToRun; a++){
            env.runStep();

            map.saveToDisk("C:\\Users\\Pictures\\board\\"
                    + String.valueOf(env.getStep())  +".png", 5,"javaawt",true);
        }


    }
}
