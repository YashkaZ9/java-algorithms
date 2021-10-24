package Test;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.*;

public class IO {
    public static void main(String[] args) throws IOException {
//        IO and files
//
//        String hello = "Hello, world!";
//        try (FileOutputStream fos = new FileOutputStream("test.txt");
//            FileInputStream fis = new FileInputStream("test.txt")) {
//            byte[] arr = hello.getBytes();
//            fos.write(arr, 0, arr.length);
//            while (fis.available() > 0) {
//                System.out.print((char)fis.read());
//            }
//        }
//        catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        byte[] buf = hello.getBytes();
//        BufferedOutputStream baos = new BufferedOutputStream(new ByteArrayOutputStream());
//        baos.write(buf, 0, 5);
//        System.out.println(baos);
//        BufferedInputStream bais = new BufferedInputStream(new ByteArrayInputStream(buf));
//        int i = -1;
//        while ((i = bais.read()) != -1)
//            System.out.print((char)i);
//
//        try (PrintStream ps = new PrintStream("test.txt")) {
//            ps.println();
//            ps.print("New Line");
//        }
//        catch (IOException ex) {
//            PrintWriter pw = new PrintWriter(System.out);
//            pw.println(ex.getMessage());
//        }
//
//        Person p = new Person("Yura", 21, 187.5, false);
//        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("test.txt"));
//        DataInputStream dis = new DataInputStream(new FileInputStream("test.txt"))) {
//            dos.writeUTF(p.name);
//            dos.writeInt(p.age);
//            dos.writeDouble(p.height);
//            dos.writeBoolean(p.married);
//            System.out.println("Dos worked successfully");
//            Person copy = new Person(dis.readUTF(), dis.readInt(), dis.readDouble(), dis.readBoolean());
//            System.out.println("Dis worked successfully");
//            System.out.println(p);
//            System.out.println(copy);
//        }
//        catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        try (FileWriter fw = new FileWriter("test.txt", true); FileReader fr = new FileReader("test.txt")) {
//            String text = "Love Java";
//            fw.write(text);
//            fw.append("\nvery match");
//            char[] buf = new char[256];
//            int bc = fr.read(buf);
//            buf = Arrays.copyOf(buf, bc);
//            for (char c : buf)
//                System.out.print(c);
//        }
//        catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//             BufferedWriter writer = new BufferedWriter(new FileWriter("test.txt"))) {
//            String s = null;
//            while (!(s = reader.readLine()).isEmpty()) {
//                writer.write(s);
//                writer.newLine();
//                writer.flush();
//            }
//        }
//        catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        List<Person> people = Arrays.asList(new Person("Yura", 21, 187.5, false),
//                                            new Person("Liza", 22, 167.25, true));
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("serialized.dat"));
//            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("serialized.dat"))) {
//            oos.writeObject(people);
//            System.out.println("OOS worked");
//            List<Person> people2 = (List<Person>)ois.readObject();
//            for (Person person : people2) {
//                System.out.println(person);
//            }
//        }
//        catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        printFiles(".");
//    }
//
//    static void printFiles(String path) {
//        for (File file : new File(path).listFiles()) {
//            if (file.isDirectory())
//                printFiles(file.getAbsolutePath());
//            System.out.println(file.getName() + " " + file.lastModified());
//        }
//    }
//
//    static class Person implements Comparable<Person>, Serializable {
//        String name;
//        int age;
//        transient double height;
//        transient boolean married;
//
//        public Person(String name, int age, double height, boolean married) {
//            this.name = name;
//            this.height = height;
//            this.age = age;
//            this.married = married;
//        }
//
//        @Override
//        public String toString() {
//            return String.format("Name: %s. Age: %d. Height: %.2f. Is married: %b", name, age, height, married);
//        }
//
//        @Override
//        public int compareTo(Person o) {
//            return age - o.age;
//        }
    }
}
