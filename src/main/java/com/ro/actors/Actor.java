package com.ro.actors;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by rohan on 2017-07-20.
 */
public abstract class Actor<T> implements Runnable {
    private final BlockingQueue<T> queue = new LinkedBlockingDeque();//todo
    private Sender<T> sender = new Sender<T>(queue, this);
    private String name;
    private boolean started = true;

    public abstract void onMessage(T message);

    public final void run() {
        while (started){
            try {
                onMessage(queue.take());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Sender getSender(){
        return sender;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    void stop(){
        started = false;
    }
}

