import com.razorpay.*;

import java.util.HashMap;
import java.util.Map;

public class SimpleRazorpayExample {
    public static void main(String[] args) {
        // Set your API key and secret
        String apiKey = "your_api_key";
        String apiSecret = "your_api_secret";

        // Create a Razorpay client
        try {
            RazorpayClient razorpayClient = new RazorpayClient(apiKey, apiSecret);

            // Example: Create an order
            Map<String, Object> orderRequest = new HashMap<>();
            orderRequest.put("amount", 50000); // Amount in paise (Indian currency), so 50000 paise = ₹500
            orderRequest.put("currency", "INR");
            orderRequest.put("payment_capture", 1); // Auto-capture payment

            Order order = razorpayClient.Orders.create(orderRequest);
            System.out.println("Order created: " + order);

            // Now, you would redirect the user to the order's payment page, and then handle the payment success or failure.

        } catch (RazorpayException e) {
            System.err.println("Razorpay API error: " + e.getMessage());
        }
    }
}
