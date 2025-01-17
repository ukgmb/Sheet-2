package uebung;

public class Main {
    public static void main(String[] args) {
        A x = new A();
        B y = new B();
        A z = new B();

        doPrint(x);
        doPrint(y);
        doPrint(z);
        System.out.println();
        System.out.println(test());

        D d = new D();
        String word = " donds";
        int i = word.length();

    }

    public static void doPrint(A a) {
        System.out.println("A");
        a.print();
    }

    public static void doPrint(B b) {
        System.out.println("B");
        b.print();
    }

    public static String test() {
        return "" + 1;
    }
}
