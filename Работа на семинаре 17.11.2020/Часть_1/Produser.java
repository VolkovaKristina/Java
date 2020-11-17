package com.company;

public class Produser  implements  Runnable{
    Store store;
    Produser(Store store){
        this.store =store;
    }

    @Override
    public void run() {
        for(int i=1; i<10; i++){
            store.put();
        }
    }
}