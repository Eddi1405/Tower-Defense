package old;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//Test anstadt Thread.sleep
public class loop {
    final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    public loop(){
    }
    //Die Methode myTask wird nach 1 Sekunde startzeit gestartet und wird dann jede 2 Sekunden ausgeführt
    public void start(Runnable ballon,int initialDelay,int delay) {
        executorService.scheduleWithFixedDelay(ballon,initialDelay,delay,TimeUnit.MILLISECONDS);
    }
    public void stop(){
        executorService.shutdown();
    }
}
