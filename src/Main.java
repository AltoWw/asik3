import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();
        Random r = new Random();
        for (int i = 0; i < 10000; i++) {
            MyTestingClass key = new MyTestingClass(i, "Name" + i);
            Student value = new Student("Student" + i, r.nextInt(100));
            table.put(key, value);
        }
        System.out.println(table);
        for (int i = 0; i < table.getM(); i++) {
            System.out.println("Bucket " + i + ": " + table.getBucketSize(i));
        }
    }
}