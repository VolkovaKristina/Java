package exchange;

import java.util.concurrent.Exchanger;

public class GetThread  implements Runnable{
    Exchanger<String> exch;
    String mes;

    GetThread(Exchanger<String> ex){
        this.exch = ex;
        mes = "hello world";
    }
    @Override
    public void run() {
        try{
            mes = exch.exchange(mes);
            System.out.println("GetThread" + mes);
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }

    }
}