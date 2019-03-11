import com.ro.actors.Actor;

/**
 * Created by rohan on 2017-07-20.
 */
public class MaxAnalyzer extends Actor<Order> {
    private double max;
    @Override
    public void onMessage(Order message) {
        if(max < message.price){
            max = message.price;
            System.out.println("Max " + max);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
