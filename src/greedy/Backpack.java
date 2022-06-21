package greedy;

import java.util.*;

class Item {
    double cost;
    int volume;

    public Item(double cost, int amount) {
        this.cost = cost;
        this.volume = amount;
    }
}

public class Backpack {
    public static void putInBackpack() {
        Scanner scanner = new Scanner(System.in);
        List<Item> items = new ArrayList<>();
        int n = scanner.nextInt();
        int fullVolume = scanner.nextInt();
        for (int i = 0; i < n; ++i) {
            double cost = scanner.nextDouble();
            int itemVolume = scanner.nextInt();
            items.add(new Item(cost / itemVolume, itemVolume));
        }
        items.sort(Comparator.comparingDouble(i -> -i.cost));
        double tempVolume, totalSum = 0;
        for (int i = 0; i < items.size() && fullVolume > 0; ++i) {
            tempVolume = Math.min(fullVolume, items.get(i).volume);
            totalSum += tempVolume * items.get(i).cost;
            fullVolume -= tempVolume;
        }
        System.out.printf("%.3f", totalSum);
    }

    public static void main(String[] args) {
        putInBackpack();
    }
}
