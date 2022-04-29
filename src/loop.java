import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//TODO Ok -Seb
public class loop {

    public loop(){
    }

    public static void main(String[] args) {
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(loop::myTask, 0, 2, TimeUnit.SECONDS);
    }

    private static void myTask() {
        System.out.println("Running");
    }
}
