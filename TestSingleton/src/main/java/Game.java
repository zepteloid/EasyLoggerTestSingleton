import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private static final int MAX_NUMBER = 50;
    private final GameLogger logger;
    private final Scanner scanner;

    public Game() {
        this.logger = GameLogger.getLogger();
        this.scanner = new Scanner(System.in);
    }

    public void startGame() {
        logger.logEvent("Игра начата");
        int targetNumber = (int) (Math.random() * MAX_NUMBER) + 1;
        int attempts = 0;

        logger.logEvent("Загадано число: " + targetNumber);
        System.out.println("Я загадал число от 1 до " + MAX_NUMBER + ". Попробуйте угадать!");

        while (true) {
            System.out.print("Ваша попытка: ");
            String input = scanner.nextLine();
            logger.logEvent("Пользователь ввел: " + input);

            try {
                int guess = Integer.parseInt(input);
                attempts++;

                if (guess < 1 || guess > MAX_NUMBER) {
                    System.out.println("Число должно быть от 1 до " + MAX_NUMBER);
                    logger.logEvent("Некорректный диапазон: " + guess);
                    continue;
                }

                if (guess == targetNumber) {
                    logger.logEvent("Число угадано за " + attempts + " попыток");
                    System.out.println("Поздравляю! Вы угадали за " + attempts + " попыток!");
                    break;
                } else if (guess < targetNumber) {
                    logger.logEvent("Подсказка: введенное число меньше");
                    System.out.println("Мое число больше!");
                } else {
                    logger.logEvent("Подсказка: введенное число больше");
                    System.out.println("Мое число меньше!");
                }
            } catch (NumberFormatException e) {
                logger.logEvent("Ошибка: введено не число");
                System.out.println("Это не число! Попробуйте еще раз.");
            }
        }

        logger.logEvent("Игра завершена");
        scanner.close();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();

        System.out.println("\n--- Логи игры ---");
        GameLogger.getLogger().printLogs();
    }
}

