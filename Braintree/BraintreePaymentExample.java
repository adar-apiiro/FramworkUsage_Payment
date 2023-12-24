import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;
import com.braintreegateway.Result;
import com.braintreegateway.ValidationError;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;
import com.braintreegateway.Customer;
import com.braintreegateway.CreditCard;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.CreditCardRequest;
import com.braintreegateway.CreditCardOptions;
import com.braintreegateway.Address;

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

        // For simplicity, you can use a hard-coded nonce (replace with an actual nonce from the client)
        String paymentMethodNonce = "fake-valid-nonce";

        // Sale Transaction
        TransactionRequest saleRequest = new TransactionRequest()
                .amount(new BigDecimal("10.00"))
                .paymentMethodNonce(paymentMethodNonce)
                .options()
                .submitForSettlement(true)
                .done();

        // Submit the sale transaction
        Result<Transaction> saleResult = gateway.transaction().sale(saleRequest);

        if (saleResult.isSuccess()) {
            Transaction saleTransaction = saleResult.getTarget();
            System.out.println("Sale Transaction ID: " + saleTransaction.getId());
            System.out.println("Status: " + saleTransaction.getStatus());
        } else {
            for (ValidationError error : saleResult.getErrors().getAllDeepValidationErrors()) {
                System.err.println("Error: " + error.getMessage());
            }
        }

        // Create Customer
        CustomerRequest customerRequest = new CustomerRequest()
                .firstName("John")
                .lastName("Doe");

        Result<Customer> customerResult = gateway.customer().create(customerRequest);

        if (customerResult.isSuccess()) {
            Customer customer = customerResult.getTarget();
            System.out.println("Customer ID: " + customer.getId());
        } else {
            for (ValidationError error : customerResult.getErrors().getAllDeepValidationErrors()) {
                System.err.println("Error: " + error.getMessage());
            }
        }

        // Add Credit Card to Customer
        CreditCardRequest creditCardRequest = new CreditCardRequest()
                .paymentMethodNonce(paymentMethodNonce)
                .customerId("customer_id");

        Result<CreditCard> creditCardResult = gateway.creditCard().create(creditCardRequest);

        if (creditCardResult.isSuccess()) {
            CreditCard creditCard = creditCardResult.getTarget();
            System.out.println("Credit Card Token: " + creditCard.getToken());
        } else {
            for (ValidationError error : creditCardResult.getErrors().getAllDeepValidationErrors()) {
                System.err.println("Error: " + error.getMessage());
            }
        }

        // Update Credit Card Options
        CreditCardOptions creditCardOptions = new CreditCardOptions()
                .makeDefault(true);

        CreditCardRequest updateCreditCardRequest = new CreditCardRequest()
                .options()
                .updateExistingToken("credit_card_token")
                .done()
                .creditCard()
                .options()
                .makeDefault(true)
                .done();

        Result<CreditCard> updateCreditCardResult = gateway.creditCard().update("credit_card_token", updateCreditCardRequest);

        if (updateCreditCardResult.isSuccess()) {
            CreditCard updatedCreditCard = updateCreditCardResult.getTarget();
            System.out.println("Updated Credit Card Token: " + updatedCreditCard.getToken());
        } else {
            for (ValidationError error : updateCreditCardResult.getErrors().getAllDeepValidationErrors()) {
                System.err.println("Error: " + error.getMessage());
            }
        }
    }
}
