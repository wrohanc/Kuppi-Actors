import com.ro.actors.ActorSystem;
import com.ro.actors.Sender;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by rohan on 2017-07-20.
 */
public class TestActor {
    @BeforeClass
    public static void setup(){
        ActorSystem.start(new ForkJoinPool(10));
        ActorSystem.startActor(new AverageAnalyzer(), "average");
        ActorSystem.startActor(new MinAnalyzer(), "min");
        ActorSystem.startActor(new MaxAnalyzer(), "max");
    }

    @Test
    public void sendMessages() {
        Sender avg = ActorSystem.getSender("average");
        Sender min = ActorSystem.getSender("min");
        Sender max = ActorSystem.getSender("max");

        Random rand = new Random();

        for(int i = 0; i < 10; i++){
            int  n = rand.nextInt(10) + 1;
            Order order = new Order();
            order.id = i;
            order.price = n;
            order.quantity = n;
            avg.send(order);
            min.send(order);
            max.send(order);

        }

    }

    @AfterClass
    public static void cleanup(){
        ActorSystem.stop();
    }
}
