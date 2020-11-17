package com.company;

public class NewThread2 implement Runnable{
    private boolean isActive;
    public void disable(){
        isActive=false;
    }
    NewThred2(){
        isActive=true;
    }
}
