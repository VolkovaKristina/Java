package exchange;

import java.util.concurrent.Exchanger;

public class Thread1  implements  Runnable{
    Exchanger<String> exch;
    String mes;

    Thread1(Exchanger<String> ex){
        this.exch = ex;
        mes = "hello java";
    }
    @Override
    public void run() {
        try{
            mes = exch.exchange(mes);
            System.out.println("Thread1" + mes);
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }

    }
}
