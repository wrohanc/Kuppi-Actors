import com.ro.actors.Actor;

/**
 * Created by rohan on 2017-07-20.
 */
public class MinAnalyzer extends Actor<Order> {
    private double min;
    @Override
    public void onMessage(Order message) {
        if(min > message.price){
            min = message.price;
            System.out.println("Min " + min);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
