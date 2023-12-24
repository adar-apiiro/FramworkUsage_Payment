import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;
import com.braintreegateway.Result;
import com.braintreegateway.ValidationError;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;

import java.math.BigDecimal;

public class BraintreePaymentExample {

    // Replace with your own values
    private static final String MERCHANT_ID = "your_merchant_id";
    private static final String PUBLIC_KEY = "your_public_key";
    private static final String PRIVATE_KEY = "your_private_key";

    public static void main(String[] args) {
        // Create a BraintreeGateway instance
        BraintreeGateway gateway = new BraintreeGateway(
                Environment.SANDBOX, // Use Environment.PRODUCTION for production environment
                MERCHANT_ID,
                PUBLIC_KEY,
                PRIVATE_KEY
        );

        // For simplicity, you can use a hard-coded nonce (replace with actual nonce from client)
        String paymentMethodNonce = "fake-valid-nonce";

        // Set up a Transaction request
        TransactionRequest request = new TransactionRequest()
                .amount(new BigDecimal("10.00"))
                .paymentMethodNonce(paymentMethodNonce)
                .options()
                .submitForSettlement(true)
                .done();

        // Submit the transaction
        Result<Transaction> result = gateway.transaction().sale(request);

        if (result.isSuccess()) {
            Transaction transaction = result.getTarget();
            System.out.println("Transaction ID: " + transaction.getId());
            System.out.println("Status: " + transaction.getStatus());
        } else {
            for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
                System.err.println("Error: " + error.getMessage());
            }
        }
    }
}
