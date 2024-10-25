import java.util.Scanner;
import java.time.LocalDateTime;

public class ToyShop {

    public static void main(String[] args) {
        ToyQueue toyQueue = new ToyQueue();
        start(toyQueue);
    }

    static void start(ToyQueue toyQueue) {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        System.out.println("\nДобро пожаловать в магазин игрушек!");

        while (true) {
            System.out.println("\n 1 - добавить игрушку \n 2 - разыграть игрушку \n 3 - выход");
            System.out.print("Введите число: ");
            userInput = scanner.nextLine();

            if (userInput.equals("1")) {
                System.out.println("\nВы выбрали добавить игрушку");
                addToy(toyQueue);
            } else if (userInput.equals("2")) {
                System.out.println("\nВы выбрали разыграть игрушку");
                for (int i = 0; i < 10; i++) {
                    raffleToy(toyQueue);
                }
            } else if (userInput.equals("3")) {
                System.out.println("Выход из программы. Спасибо за визит!");
                break; // Завершить цикл и выйти из программы
            } else {
                System.out.println("\nНекорректный ввод! Пожалуйста, попробуйте еще раз.");
            }
        }
        scanner.close(); // Закрыть сканер после выхода из цикла
    }

    static void addToy(ToyQueue toyQueue) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название игрушки: ");
        String toyName = scanner.next();
        System.out.print("Введите частоту выигрыша игрушки от 0 до 10: ");
        LocalDateTime dateTime = LocalDateTime.now();
        String id = dateTime.toString();
        int toyFrequency = 0;

        // Обработка частоты выигрыша
        while (true) {
            String toyFrequencyString = scanner.next();
            try {
                toyFrequency = Integer.parseInt(toyFrequencyString);
                if (toyFrequency < 0 || toyFrequency > 10) {
                    System.out.println("Не смогли превратить в int");
                    throw new NumberFormatException(); // Исключение для диапазона
                }
                break; // Если все хорошо, выход из цикла
            } catch (NumberFormatException exception) {
                System.out.println("\nНекорректный ввод! Пожалуйста, введите число от 0 до 10.");
            }
        }

        Toy toy = new Toy(id, toyName, toyFrequency);
        toyQueue.put(toy);
        // scanner.close(); // Не закрываем здесь, чтобы избежать ошибок в дальнейшем
    }

    static void raffleToy(ToyQueue toyQueue) {
        Toy result = toyQueue.get();
        System.out.println("Полученный результат: " + result.getName());
        toyQueue.writeToFile("toys.txt", result);
    }
}
