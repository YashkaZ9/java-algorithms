package Test;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AdvancedTopics {
    public static void main(String[] args) throws CloneNotSupportedException {
//        Advanced topics

//        Colors[] colors = Colors.values();
//        Arrays.stream(colors).forEach(color -> System.out.println(color.getColor()));

//        Book book = new Book(new Author("Yuri Baykov", 22), "World by programmer's eyes");
//        System.out.println(book);
//        Book copy = book.clone();
//        copy.setAuthor(new Author("Lzeauthor", 22));
//        System.out.println("Book: " + book);
//        System.out.println("Copy: " + copy);

//        String tel = "12345678999";
//        String hell = "Ye hell Hell Hhelloo h e l l hell!";
//        System.out.println(Pattern.matches("\\+?\\d{11}", tel));
//        Pattern pattern = Pattern.compile("hell(\\w*)");
//        Matcher matcher = pattern.matcher(hell);
//        while (matcher.find())
//            System.out.println(matcher.group());
//        String well = matcher.replaceAll("well");
//        System.out.println(well);

//        Comparator<Person> comp = new PersonNameComparer().thenComparing(new PersonAgeComparer());
//        Set<Person> people = new TreeSet<>(comp);
//        people.addAll(Arrays.asList(new Person("Yura", 22),
//                new Person("Jesse", 21),
//                new Person("Liza", 23)));
//        people.forEach(System.out::println);

//        try {
//            Person p = new Person("Yura", 21);
//            Method method = Person.class.getMethod("setAge", int.class);
//            method.invoke(p, 15);
//            System.out.println(p.getAge());
//        } catch (Exception ex) {
//            ex.getMessage();
//        }

//        Stream<Phone> phoneStream = Stream.of(new Phone("iPhone 6 S", 54000),
//                new Phone("Lumia 950", 45000),
//                new Phone("Samsung Galaxy S 6", 40000),
//                new Phone("LG G 4", 32000));
//        long sum = phoneStream.reduce(0, (x, y) -> x += y.getPrice() > 50000 ? 0 : y.getPrice(), (x, y) -> x + y);
//        System.out.println(sum);

//        List<Integer> list = Arrays.asList();
//        Optional<Integer> m = list.stream().max(Integer::compare);
//        System.out.println(m.orElse(-1));

//        Stream<CompanyPhone> phones = Stream.of(new CompanyPhone("iPhone X", "Apple", 600),
//                new CompanyPhone("Pixel 2", "Google", 500),
//                new CompanyPhone("iPhone 8", "Apple", 450),
//                new CompanyPhone("Galaxy S9", "Samsung", 440),
//                new CompanyPhone("Galaxy S8", "Samsung", 340));
//        Map<String, List<CompanyPhone>> group = phones.collect(Collectors.groupingBy(CompanyPhone::getCompany));
//        for (Map.Entry<String, List<CompanyPhone>> companies : group.entrySet()) {
//            System.out.println(companies.getKey());
//            companies.getValue().forEach(System.out::println);
//            System.out.println();
//        }
//        Map<String, Long> countByCompany = phones.collect(Collectors.groupingBy(CompanyPhone::getCompany, Collectors.counting()));
//        countByCompany.forEach((k, v) -> System.out.println(k + ": " + v));
//        Map<Boolean, List<CompanyPhone>> group = phones.collect(Collectors.partitioningBy(phone -> phone.getPrice() < 500));
//        for (Map.Entry<Boolean, List<CompanyPhone>> companies : group.entrySet()) {
//            System.out.println(companies.getKey());
//            companies.getValue().forEach(System.out::println);
//            System.out.println();
//        }
//        Map<String, Integer> countByCompany = phones.collect(Collectors.groupingBy(CompanyPhone::getCompany, Collectors.summingInt(CompanyPhone::getPrice)));
//        countByCompany.forEach((k, v) -> System.out.println(k + ": " + v));
//        Map<String, Optional<CompanyPhone>> countByCompany = phones.collect(Collectors.groupingBy(CompanyPhone::getCompany,
//                Collectors.minBy(Comparator.comparingInt(CompanyPhone::getPrice))));
//        countByCompany.forEach((k, v) -> System.out.println(k + ": " + v.get().getName()));
//        Map<String, List<String>> listPhones = phones.collect(Collectors.groupingBy(CompanyPhone::getCompany,
//                Collectors.mapping(CompanyPhone::getName, Collectors.toList())));
//        listPhones.forEach((k, v) -> {
//            System.out.println(k);
//            v.forEach(p -> System.out.println(p));
//            System.out.println();
//        });

//        List<String> list = Arrays.asList("Мама", ";lkjlker", "мыла", "бла-бла", "ыреше", "раму");
//        list.parallelStream().filter(s -> s.length() == 4).sequential().forEach(s -> System.out.print(s + " "));

//        long start = System.nanoTime();
//        Optional<BigInteger> par = IntStream.rangeClosed(1, 100000).parallel().mapToObj(BigInteger::valueOf).reduce((x, y) -> x.multiply(y));
//        System.out.println(par.orElse(BigInteger.ZERO));
//        long finish = System.nanoTime();
//        System.out.println(finish - start);
//        start = System.nanoTime();
//        Optional<BigInteger> seq = IntStream.rangeClosed(1, 100000).mapToObj(BigInteger::valueOf).reduce((x, y) -> x.multiply(y));
//        System.out.println(seq.orElse(BigInteger.ZERO));
//        finish = System.nanoTime();
//        System.out.println(finish - start);

//        Phone[] phones = new Phone[]{new Phone("iPhone 8", 54000),
//                new Phone("Pixel 2", 45000),
//                new Phone("Samsung Galaxy S9", 40000),
//                new Phone("Nokia 9", 32000)};
//        Arrays.parallelSetAll(phones, i -> {
//            phones[i].setPrice(phones[i].getPrice() + 5000);
//            return phones[i];
//        });
//        Arrays.parallelSort(phones, Comparator.comparingInt(Phone::getPrice));
//        Arrays.stream(phones).forEach(System.out::println);

//        int[] arr = {1, 2, 3, 4, 5};
//        Arrays.parallelPrefix(arr, (x, y) -> x * y);
//        for (int i : arr) {
//            System.out.print(i + " ");
//        }

//        try {
//            Class cls = Class.forName(TestReflection.class.getName());
//            TestReflection tr = (TestReflection) cls.newInstance();
//            System.out.println(tr);
//            for (Constructor constr : TestReflection.class.getConstructors()) {
//                Class[] paramsTypes = constr.getParameterTypes();
//                Arrays.stream(paramsTypes).forEach(pt -> System.out.print(pt.getName() + " "));
//                System.out.println();
//            }
//            TestReflection n = (TestReflection)cls.getConstructor(int.class, String.class).newInstance(1, "Hello");
//            Method m = n.getClass().getDeclaredMethod("getNum");
//            m.setAccessible(true);
//            System.out.println(m.invoke(n));
//            Field f = n.getClass().getDeclaredField("s");
//            f.setAccessible(true);
//            f.set(n, (String)"Java");
//            System.out.println(f.get(n));
//        } catch (ClassNotFoundException | NoSuchMethodException | NoSuchFieldException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
//            e.printStackTrace();
//        }

//        for (Annotation annotation : Reflector.class.getAnnotations()) {
//            if (annotation instanceof Reflectable) {
//                Reflectable ref = (Reflectable)annotation;
//                System.out.println("Name: " + ref.name());
//                System.out.println("Value: " + ref.value());
//            }
//        }

//        int[] arr = (int[])Array.newInstance(int.class, 2);
//        for (int i = 0; i < arr.length; ++i) {
//            Array.set(arr, i, new Random().nextInt(100));
//        }
//        Arrays.stream(arr).forEach(System.out::println);
    }
}

@Reflectable (name = "reflection", value = "valuable")
class Reflector {
    int x;
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Reflectable {
    public String name();
    public String value();
}

class TestReflection {
    private int num;
    private String s = "default";

        public TestReflection(int num, String s) {
        this.num = num;
        this.s = s;
    }

    private int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return String.format("%s: %d", s, num);
    }
}

class CompanyPhone extends Phone {
    private String company;

    public CompanyPhone(String name, String company, int price) {
        super(name, price);
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    @Override
    public String toString() {
        return String.format("%s: %d", getName(), getPrice());
    }
}

class Phone {

    private String name;
    private int price;

    public Phone(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s: %d", name, price);
    }
}

class PersonNameComparer implements Comparator<Person> {
    public int compare(Person a, Person b) {
        return a.getName().compareTo(b.getName());
    }
}

class PersonAgeComparer implements Comparator<Person> {
    public int compare(Person a, Person b) {
        return a.getAge() > b.getAge() ? 1 : a.getAge() < b.getAge() ? -1 : 0;
    }
}

class Person implements Comparable<Person> {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(Person p) {
        return name.compareTo(p.name) + (age < p.age ? -1 : age > p.age ? 1 : 0);
    }

    @Override
    public String toString() {
        return String.format("%s: %d", name, age);
    }
}

class Book implements Cloneable {
    private Author author;
    private String name;

    public Book(Author author, String name) {
        this.author = author;
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Book clone() throws CloneNotSupportedException {
        Book book = (Book) super.clone();
        book.author = (Author) author.clone();
        return book;
    }

    @Override
    public String toString() {
        return String.format("\"%s\" is written by %s", name, author);
    }
}

class Author implements Cloneable {
    private String name;
    private int careerAge;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCareerAge() {
        return careerAge;
    }

    public void setCareerAge(int careerAge) {
        this.careerAge = careerAge;
    }

    public Author(String name, int careerAge) {
        this.name = name;
        this.careerAge = careerAge;
    }

    public Author clone() throws CloneNotSupportedException {
        return (Author) super.clone();
    }

    @Override
    public String toString() {
        return name;
    }
}

enum Colors {
    RED("#ff0000"), GREEN("#00ff00"), BLUE("#0000ff");

    private String color;

    Colors(String color) {
        this.color = color;
    }

    int FASHIONRATE = 100;

    public String getColor() {
        return color;
    }
}
