package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println("Запускается главный поток....");
        CommonResurce commonResurce= new CommonResurce();
        for (int i=1; i<5; i++){
            Thread t=new Thread (new CountThread(commonResurce), name:" Thread"+i);
            t.start();
        }
        System.out.println("Заканчиваем");
    }
}
