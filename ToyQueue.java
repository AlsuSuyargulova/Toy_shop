import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class ToyQueue {
    private PriorityQueue<Toy> toyQueue;
    private Random random;

    public ToyQueue() {
        toyQueue = new PriorityQueue<>((a, b) -> Integer.compare(b.getFrequency(), a.getFrequency()));
        random = new Random();
    }

    public void put(Toy toy) {
        toyQueue.add(toy);
    }

    public Toy get() {
        List<Toy> toys = new ArrayList<>(toyQueue);
        int totalFrequency = 0;
        for (Toy toy : toys) {
            totalFrequency += toy.getFrequency();
        }
        if (totalFrequency <= 0) {
            return null; // Или выбросьте исключение, если это необходимо
        }
        int randomValue = random.nextInt(totalFrequency);
        for (Toy toy : toys) {
            randomValue -= toy.getFrequency();
            if (randomValue < 0) {
                return toy;
            }
        }
        return null;
    }

    public void writeToFile(String filename, Toy toy) {
        // Проверка на null
        if (toy == null) {
            System.err.println("Error: Toy object is null. Unable to write to file.");
            return;
        }

        // Использование метода try-with-resources для автоматического закрытия ресурса
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) { // добавлен режим append
            // Форматирование строки для записи
            String record = String.format("Id: %s, Название: %s, Частоту: %d \n", toy.getId(), toy.getName(),
                    toy.getFrequency());

            // Запись записи в файл
            writer.write(record);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            e.printStackTrace(); // Логирование ошибки (можно заменить на логирование с помощью логгера)
        }
    }
}
