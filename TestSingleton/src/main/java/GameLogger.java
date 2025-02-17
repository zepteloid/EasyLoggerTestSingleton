import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GameLogger {
    private static GameLogger gameLogger;
    private List<String> listLogger = new ArrayList<>();

    private GameLogger(){}

    public static synchronized GameLogger getLogger(){
        if (gameLogger == null){
            gameLogger = new GameLogger();
        }
        return gameLogger;
    }

    public void logEvent(String message){
        listLogger.add(LocalDateTime.now() + " : " + message);
    }

    public void printLogs(){
        for(String log : listLogger){
            System.out.println(log);
        }
    }
}
