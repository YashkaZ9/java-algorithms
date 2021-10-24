package GreedyAlgorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Huffman {
    public static void generateString() throws FileNotFoundException {
        PrintWriter pr = new PrintWriter("input.txt");
        Random random = new Random();
        int n = random.nextInt(10000);
        for (int i = 0; i < n; ++i) {
            pr.print((char) ('a' + random.nextInt(26)));
        }
        pr.close();
    }

    public static void encode() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("input.txt"));
        String input = scanner.next();
        Map<Character, Integer> charsFreqs = new HashMap<>();
        input.chars().forEach(i -> charsFreqs.put((char) i, charsFreqs.getOrDefault((char) i, 0) + 1));
        Map<Character, Node> charsNodes = new HashMap<>();
        java.util.PriorityQueue<Node> queueNodes = new java.util.PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : charsFreqs.entrySet()) {
            LeafNode node = new LeafNode(entry.getKey(), entry.getValue());
            queueNodes.add(node);
            charsNodes.put(entry.getKey(), node);
        }
        while (queueNodes.size() > 1) {
            Node left = queueNodes.remove();
            Node right = queueNodes.remove();
            InnerNode inner = new InnerNode(left, right);
            queueNodes.add(inner);
        }
        Node root = queueNodes.remove();
        if (charsFreqs.size() == 1)
            root.buildCode("0");
        else
            root.buildCode("");
        StringBuilder encodedString = new StringBuilder();
        input.chars().forEach(i -> encodedString.append(charsNodes.get((char) i).code));
        System.out.println(charsNodes.size() + " " + encodedString.length());
        charsNodes.forEach((k, v) -> System.out.println(k + ": " + v.code));
        System.out.println(encodedString.toString());
    }

    public static void decode() {
        HashMap<String, String> codeChar = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        int letterCount = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < letterCount; ++i) {
            String[] temp = scanner.nextLine().split(": ");
            codeChar.put(temp[1], temp[0]);
        }
        String encoded = scanner.nextLine();
        StringBuilder decoded = new StringBuilder();
        String temp = "";
        for (int i = 0; i < encoded.length(); ++i) {
            temp += encoded.charAt(i);
            if (codeChar.containsKey(temp)) {
                decoded.append(codeChar.get(temp));
                temp = "";
            }
        }
        System.out.println(decoded.toString());
    }

    public static void main(String[] args) throws FileNotFoundException {
//        generateString();
        long start = System.currentTimeMillis();
//        Huffman.encode();
        Huffman.decode();
        long finish = System.currentTimeMillis();
        System.out.println(finish - start + " ms");
    }
}

class Node implements Comparable<Node> {
    final int freq;
    String code;

    public Node(int freq) {
        this.freq = freq;
    }

    public void buildCode(String code) {
        this.code = code;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(freq, o.freq);
    }
}

class LeafNode extends Node {
    char letter;

    public LeafNode(char letter, int freq) {
        super(freq);
        this.letter = letter;
    }

    @Override
    public void buildCode(String code) {
        super.buildCode(code);
    }
}

class InnerNode extends Node {
    Node left;
    Node right;

    public InnerNode(Node left, Node right) {
        super(left.freq + right.freq);
        this.left = left;
        this.right = right;
    }

    @Override
    public void buildCode(String code) {
        super.buildCode(code);
        left.buildCode(code + "0");
        right.buildCode(code + "1");
    }
}