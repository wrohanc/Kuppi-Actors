import com.ro.actors.Actor;

/**
 * Created by rohan on 2017-07-20.
 */
public class AverageAnalyzer extends Actor<Order> {
    private double sum;
    private int n;

    @Override
    public void onMessage(Order message) {
        sum = sum + message.quantity;
        n = n + 1;
        System.out.println("Average : " + (sum/n));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
