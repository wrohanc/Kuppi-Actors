package com.ro.actors;

import java.util.concurrent.BlockingQueue;

/**
 * Created by rohan on 2017-07-20.
 */
public class Sender<T> {
    private final Actor<T> actor;
    private final BlockingQueue<T> queue;
    private boolean started = true;

    Sender(BlockingQueue<T> queue, Actor<T> actor) {
        this.actor = actor;
        this.queue = queue;
    }

    public boolean send(T message) {
        if(started) {
            try {
                this.queue.put(message);
                return true;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    void stop() {
        started = false;
        actor.stop();
    }
}
