package exchange;

import java.util.concurrent.Exchanger;

public class Main {
    public static void main(String[] args) {
        Exchanger<String> ex = new Exchanger<>();
        new Thread(new Thread1(ex)).start();
        new Thread(new GetThread(ex)).start();

    }
}