package com.company;

public class Comsumer  implements Runnable{
    Store store;
    Comsumer(Store store){
        this.store = store;
    }

    @Override
    public void run() {
        for(int i=1; i<10; i++){
            store.get();
        }

    }
}
