import java.util.Scanner;

public class ExampleVolatile {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();

        // реализация прерывания потока нажатием на клавишу "enter"
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        myThread.shutdown();
    }
}
class MyThread extends Thread {
    // volatile позволяет хранить переменную running в Main Memory
    // тем самым исключить ситуацию не когерентности кэшей на ядрах процессора
    private volatile boolean running = true;

    public void run() {
        while (running) {
            System.out.println("Hello");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    // метод прерывания потока
    public void shutdown() {
        this.running = false;
    }
}
