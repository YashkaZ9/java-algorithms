package greedy;

import java.util.*;

public class PriorityQueue implements Iterable<Integer> {
    List<Integer> data;

    private class PriorityQueueIterator implements Iterator<Integer> {
        private Integer curIndex;

        public PriorityQueueIterator() {
            if (data.size() > 0)
                curIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return curIndex < data.size();
        }

        @Override
        public Integer next() {
            if (!this.hasNext())
                throw new NoSuchElementException();
            Integer value = data.get(curIndex);
            curIndex++;
            return value;
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new PriorityQueueIterator();
    }

    public PriorityQueue() {
        data = new ArrayList<>();
    }

    public int size() {
        return data.size();
    }

    public void insert(Integer e) {
        data.add(e);
        if (data.size() == 1) return;
        Integer curIndex = data.size() - 1;
        Integer curParentIndex = curIndex / 2;
        while (curIndex > 0 && data.get(curIndex) > data.get(curParentIndex)) {
            Integer t = data.get(curParentIndex);
            data.set(curParentIndex, data.get(curIndex));
            data.set(curIndex, t);
            curIndex = curParentIndex;
            curParentIndex = curIndex / 2;
        }
    }

    public Integer delete(int i) {
        if (i < 0 || i >= data.size()) throw new IllegalStateException();
        if (data.size() == 1)
            return data.remove(0);
        Integer res = data.get(i);
        data.set(i, data.remove(data.size() - 1));
        int curIndex = i;
        while (2 * i < data.size()) {
            curIndex = i;
            if (data.get(2 * i) > data.get(curIndex))
                curIndex = 2 * i;
            if (2 * i + 1 < data.size() && data.get(2 * i + 1) > data.get(curIndex))
                curIndex = 2 * i + 1;
            if (curIndex == i) break;
            Integer t = data.get(i);
            data.set(i, data.get(curIndex));
            data.set(curIndex, t);
            i = curIndex;
        }
        return res;
    }

    public Integer extractMax() {
        if (data.size() == 0) throw new IllegalStateException("Priority queue is empty");
        return delete(0);
    }
}

class Main {
    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String[] command = scanner.nextLine().split(" ");
            if (command.length > 1) queue.insert(Integer.valueOf(command[1]));
            else System.out.println(queue.extractMax());
        }
    }
}