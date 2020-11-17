package com.company;

public class NewThread extends Thread{
    NewTread(string name){
        super(name);
    }
    public void run(){
        System.out.printf("$s started/n", Thread.currentTread().getName());
        int counter=1;
        while(!isInterrupted()){
            System.out.println("Loop"+counter++);
            try{
                Thread.sleep(millis 100);
        }catch (InterruptedException e){
            System.out.println("Thread is interrupted")
            interrupt();
        }
        System.out.printf("Thread $s is finished/n,");
    }

}
