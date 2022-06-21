package greedy;

import java.util.*;

public class CoverSegments {
    public static class Point {
        int x;

        public Point(int x) {
            this.x = x;
        }

        @Override
        public String toString() {
            return String.valueOf(x);
        }
    }

    public static class Segment {
        Point from;
        Point to;

        public Segment(Point from, Point to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "(" + from + ", " + to + ")";
        }
    }

    public static void coverSegment() {
        Scanner scanner = new Scanner(System.in);
        List<Segment> segments = new ArrayList<>();
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            int[] points = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
            segments.add(new Segment(new Point(points[0]), new Point(points[1])));
        }
        segments.sort(Comparator.comparingInt(s -> s.from.x));
        List<Point> points = new ArrayList<>();
        Segment curSegment;
        for (int i = 0; i < segments.size(); ) {
            curSegment = segments.get(i++);
            points.add(new Point(curSegment.to.x));
            while (i < segments.size() && segments.get(i).from.x <= curSegment.to.x) {
                if (segments.get(i).to.x < curSegment.to.x) {
                    if (!points.isEmpty())
                        points.remove(points.size() - 1);
                    curSegment = segments.get(i);
                    points.add(new Point(curSegment.to.x));
                }
                ++i;
            }
        }
        System.out.println(points.size());
        points.forEach(point -> System.out.print(point.x + " "));
    }

    public static void main(String ...args) {
        coverSegment();
    }
}