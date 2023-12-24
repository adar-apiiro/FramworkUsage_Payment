import com.razorpay.RazorpayClient;
import com.razorpay.Order;
import com.razorpay.Customer;
import com.razorpay.RazorpayException;
import com.razorpay.Payment;
import org.json.JSONObject;

import java.util.List;

public class RazorpayExample {

    public static void main(String[] args) {
        try {
            // Initialize Razorpay
            RazorpayClient razorpay = new RazorpayClient("[YOUR_KEY_ID]", "[YOUR_KEY_SECRET]");

            // Create a payment order
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", 50000); // amount in the smallest currency unit
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "order_rcptid_11");

            Order order = razorpay.Orders.create(orderRequest);
            System.out.println("Order ID: " + order.get("id"));

            // Handle payment order exception
        } catch (RazorpayException e) {
            System.out.println("Payment Order Exception: " + e.getMessage());
        }

        // Handle 'payment.submit' event
        var rzp = new Razorpay(options);
        rzp.on('payment.submit', function (data) {
            if (data.method === 'bank_transfer') {
                // User has selected Bank Transfer
            }
        });

        // Fetch payments with 'card' expansion
        try {
            RazorpayClient instance = new RazorpayClient("[YOUR_KEY_ID]", "[YOUR_KEY_SECRET]");
            JSONObject cardParams = new JSONObject();
            cardParams.put("expand[]", "card");
            List<Payment> cardPayments = instance.payments.fetchAll(cardParams);
        } catch (RazorpayException e) {
            System.out.println("Fetch Card Payments Exception: " + e.getMessage());
        }

        // Fetch payments with 'emi' expansion
        try {
            RazorpayClient instance = new RazorpayClient("[YOUR_KEY_ID]", "[YOUR_KEY_SECRET]");
            JSONObject emiParams = new JSONObject();
            emiParams.put("expand[]", "emi");
            List<Payment> emiPayments = instance.payments.fetchAll(emiParams);
        } catch (RazorpayException e) {
            System.out.println("Fetch EMI Payments Exception: " + e.getMessage());
        }

        // Fetch a specific payment downtime by ID
        try {
            RazorpayClient instance = new RazorpayClient("[YOUR_KEY_ID]", "[YOUR_KEY_SECRET]");
            String DowntimeId = "down_F7LroRQAAFuswd";
            Payment specificPayment = instance.payments.fetchPaymentDowntimeById(DowntimeId);
        } catch (RazorpayException e) {
            System.out.println("Fetch Specific Payment Downtime Exception: " + e.getMessage());
        }

        // Fetch all payment downtimes
        try {
            RazorpayClient instance = new RazorpayClient("[YOUR_KEY_ID]", "[YOUR_KEY_SECRET]");
            List<Payment> allDowntimes = instance.payments.fetchPaymentDowntime();
        } catch (RazorpayException e) {
            System.out.println("Fetch All Payment Downtimes Exception: " + e.getMessage());
        }
    }
}
