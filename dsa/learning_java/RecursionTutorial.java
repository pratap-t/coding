public class RecursionTutorial {
    public static void main(String[] args) {
        sayhi(5);
    }

    private static void sayhi(int count) {
        // System.out.println(count);

        if (count <= 1) {
            return;
        }
        sayhi(count - 1);
        System.out.println(count);
    }
}
