import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//Test anstadt Thread.sleep
public class loop {
    public static int i = 1;
    public loop(){
    }
    //Die Methode myTask wird nach 1 Sekunde startzeit gestartet und wird dann jede 2 Sekunden ausgeführt
    public static void start() {
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(Gegner::move,0,100,TimeUnit.MILLISECONDS);
    }

    private static int myTask() {
        if(i <11){
            System.out.println("Running"+i);
            i++;

        }
        else{

            System.out.println("stop");
        }

        return i;

    }

}
