import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.param.ChargeCreateParams;
import com.stripe.param.CustomerCreateParams;

import java.util.HashMap;
import java.util.Map;

public class StripePaymentExample {

    private static final String SECRET_KEY = "sk_test_4eC39HqLyjWDarjtT1zdp7dc"; // Replace with your actual secret key

    public static void main(String[] args) {
        Stripe.apiKey = SECRET_KEY;

        try {
            // Create a Customer
            Customer customer = createCustomer("test@example.com", "John Doe");

            // Create a Charge
            Charge charge = createCharge(customer.getId(), 2000, "usd");

            // Print Charge information
            System.out.println("Charge ID: " + charge.getId());
            System.out.println("Status: " + charge.getStatus());

        } catch (StripeException e) {
            e.printStackTrace();
        }
    }

    private static Customer createCustomer(String email, String name) throws StripeException {
        CustomerCreateParams customerCreateParams = CustomerCreateParams.builder()
                .setEmail(email)
                .setName(name)
                .build();

        return Customer.create(customerCreateParams);
    }

    private static Charge createCharge(String customerId, int amount, String currency) throws StripeException {
        ChargeCreateParams chargeCreateParams = ChargeCreateParams.builder()
                .setAmount(amount)
                .setCurrency(currency)
                .setCustomer(customerId)
                .build();

        return Charge.create(chargeCreateParams);
    }
}
