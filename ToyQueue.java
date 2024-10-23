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
        toyQueue.offer(toy);
    }

    public Toy get() {
        List<Toy> toys = new ArrayList<>(toyQueue);
        int totalFrequency = 0;
        for (Toy toy : toys) {
            totalFrequency += toy.getFrequency();
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

    public void writeToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            while (!toyQueue.isEmpty()) {
                Toy toy = toyQueue.poll();
                writer.write(toy.getId() + "," + toy.getName() + "," + toy.getFrequency());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
