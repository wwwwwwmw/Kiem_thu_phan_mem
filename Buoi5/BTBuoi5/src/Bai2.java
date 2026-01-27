public class Bai2 {
    private String message;

    public Bai2(String message) {
        this.message = message;
    }

    public void printMessage() {
        System.out.println(message);
    }

    public String printHiMessage() {
        return "Hi!" + message;
    }
}