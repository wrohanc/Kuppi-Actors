package com.ro.actors;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by rohan on 2017-07-20.
 */
public class ActorSystem {
    private static ActorSystem actorSystem = new ActorSystem();
    private static ExecutorService executor;
    private static HashMap<String, Sender> references = new HashMap<>(10, 5);
    private static boolean started;

    public static synchronized void start(ExecutorService exec) {
        if (!started) {
            System.out.println("Start actor system..");
            executor = exec;
            started = true;//do other initializations
        }
    }

    public static synchronized Sender startActor(Actor actor, String name) {
        if (references.containsKey(name)) {
            throw new ActorNameExistException();
        }

        actor.setName(name);

        references.put(name, actor.getSender());

        executor.submit(actor);

        return actor.getSender();
    }
    
    public static synchronized void stopActor(String name) {
        if (!references.containsKey(name)) {
            throw new ActorNotFoundException();
        }
        references.get(name).stop();
    }


    public static Sender getSender(String name) {
        if (!references.containsKey(name)) {
            throw new ActorNotFoundException();
        }

        return references.get(name);
    }

    public static synchronized void stop() {
        if (started) {
            try {
                System.out.println("Stop actor system.");
                executor.shutdown();
                executor.awaitTermination(1, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (!executor.isTerminated()) {
                    System.err.println("Canceling non-finished tasks.");
                }
                executor.shutdownNow();
                System.out.println("Shutdown completed.");
            }
            started = false;
        }
        System.exit(1);
    }

    private ActorSystem() {
    }
}
