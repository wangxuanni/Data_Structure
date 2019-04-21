package MyRecursion;

public class WriteVertical {
    public static void writeVertical(int number) {
        if (number < 10) {
            System.out.println(number);
        } else {
            writeVertical(number / 10);
            System.out.println(number % 10);
        }

    }

    public static void main(String[] args) {
        writeVertical(931);
    }
}
