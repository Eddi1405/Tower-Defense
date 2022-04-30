import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//Test anstadt Thread.sleep
public class loop {

    public loop(){
    }
    //Die Methode myTask wird nach 1 Sekunde startzeit gestartet und wird dann jede 2 Sekunden ausgeführt
    public static void main(String[] args) {
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(loop::myTask, 1, 2, TimeUnit.SECONDS);
    }

    private static void myTask() {
        System.out.println("Running");
    }
}
