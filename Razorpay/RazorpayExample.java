import com.razorpay.RazorpayClient;
import com.razorpay.Order;
import com.razorpay.RazorpayException;
import org.json.JSONObject;

public class RazorpayExample {

    public static void main(String[] args) {
        // Step 1: Initialize Razorpay
        try {
            RazorpayClient razorpay = new RazorpayClient("your_api_key", "your_api_secret");

            // Step 2: Create a Payment
            JSONObject options = new JSONObject();
            options.put("amount", 50000);  // amount in paise (50000 paise = Rs 500)
            options.put("currency", "INR");
            options.put("receipt", "order_rcptid_11");
            options.put("payment_capture", 1); // 1 for automatic capture, 0 for manual capture

            Order order = razorpay.Orders.create(options);
            System.out.println("Order ID: " + order.get("id"));
        } catch (RazorpayException e) {
            e.printStackTrace();
        }
    }
}
