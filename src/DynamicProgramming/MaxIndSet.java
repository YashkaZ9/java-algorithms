package DynamicProgramming;

import java.util.*;

class Node {
    int weight;
    List<Node> children;

    public Node(int weight) {
        this.weight = weight;
        this.children = new ArrayList<>();
    }

    public void add(Node node) {
        children.add(node);
    }

    public void addChildren(Node... nodes) {
        Collections.addAll(children, nodes);
    }
}

public class MaxIndSet {
    private Map<Node, Integer> nodeWeights;

    public MaxIndSet() {
        nodeWeights = new HashMap<>();
    }

    public void printOptimalSet(Node node, int maxCount) {
        int curCount = 0;
        for (Node child : node.children) {
            curCount += nodeWeights.get(child);
        }
        if (curCount == maxCount) {
            for (Node child : node.children) {
                printOptimalSet(child, nodeWeights.get(child));
            }
        } else {
            System.out.print(node.weight + " ");
            for (Node child : node.children) {
                for (Node grandChild : child.children) {
                    printOptimalSet(grandChild, nodeWeights.get(grandChild));
                }
            }
        }
    }

    public int findMaxIndSet(Node node) {
        if (!nodeWeights.containsKey(node)) {
            if (node.children.isEmpty()) {
                nodeWeights.put(node, node.weight);
            } else {
                int max1 = node.weight;
                for (Node child : node.children) {
                    for (Node grandChild : child.children) {
                        max1 += findMaxIndSet(grandChild);
                    }
                }
                int max2 = 0;
                for (Node child : node.children) {
                    max2 += findMaxIndSet(child);
                }
                nodeWeights.put(node, Math.max(max1, max2));
            }
        }
        return nodeWeights.get(node);
    }

    public static void main(String[] args) {
        Node one = new Node(3);
        Node two = new Node(5);
        Node three = new Node(1);
        Node four = new Node(6);
        one.addChildren(two, three, four);
        Node five = new Node(2);
        Node six = new Node(3);
        Node seven = new Node(7);
        Node eight = new Node(2);
        two.add(five);
        three.add(six);
        four.addChildren(seven, eight);
        Node nine = new Node(1);
        Node ten = new Node(2);
        Node eleven = new Node(1);
        seven.addChildren(nine, ten, eleven);
        MaxIndSet maxIndSet = new MaxIndSet();
        int maxIndSetCount = maxIndSet.findMaxIndSet(one);
        System.out.println(maxIndSetCount);
        maxIndSet.printOptimalSet(one, maxIndSetCount);
    }
}
