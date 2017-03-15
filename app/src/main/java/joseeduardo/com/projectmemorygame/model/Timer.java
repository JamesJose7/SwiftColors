package joseeduardo.com.projectmemorygame.model;

/**
 * Created by jeeps on 3/14/2017.
 */
public class Timer {

    public interface TimerListener {
        void onFinish();
        void onTick();
    }

    private int seconds;
    private TimerListener listener;
    private boolean interrupt;

    public Timer(int seconds, TimerListener listener) {
        this.seconds = seconds;
        this.listener = listener;
        interrupt = false;
    }

    public void start() {
        Thread timerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    try {
                        if (!interrupt) {
                            listener.onTick();
                            Thread.sleep(1000);
                            seconds--;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (seconds >= 1 && !interrupt);
                seconds = 0;
                listener.onTick();
                if (!interrupt)
                    listener.onFinish();
            }
        });

        timerThread.start();
    }

    public int getSeconds() {
        return seconds;
    }

    public void cancelTimer() {
        interrupt = true;
    }
}
