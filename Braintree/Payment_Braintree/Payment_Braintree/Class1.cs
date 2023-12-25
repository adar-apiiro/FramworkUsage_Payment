namespace Payment_Braintree;
using System;
using Braintree;

namespace BraintreeExample
{
    class Program
    {
        static void Main(string[] args)
        {
            // Create an instance of BraintreeGateway
            var gateway = new BraintreeGateway
            {
                Environment = Braintree.Environment.SANDBOX,
                MerchantId = "the_merchant_id",
                PublicKey = "a_public_key",
                PrivateKey = "a_private_key"
            };

            // Create a TransactionRequest to specify the transaction details
            TransactionRequest request = new TransactionRequest
            {
                Amount = 1000.00M,
                PaymentMethodNonce = nonceFromTheClient, // Replace with an actual nonce
                Options = new TransactionOptionsRequest
                {
                    SubmitForSettlement = true
                }
            };

            // Perform a sale transaction using the Sale method
             Result<Transaction> result = gateway.Transaction.Sale(request);

            // Check the result and handle success or errors
            if (result.IsSuccess())
            {
                Transaction transaction = result.Target;
                Console.WriteLine("Success!: " + transaction.Id);
            }
            else if (result.Transaction != null)
            {
                Transaction transaction = result.Transaction;
                Console.WriteLine("Error processing transaction:");
                Console.WriteLine("  Status: " + transaction.Status);
                Console.WriteLine("  Code: " + transaction.ProcessorResponseCode);
                Console.WriteLine("  Text: " + transaction.ProcessorResponseText);
            }
            else
            {
                // Handle validation errors
                foreach (ValidationError error in result.Errors.DeepAll())
                {
                    Console.WriteLine("Attribute: " + error.Attribute);
                    Console.WriteLine("  Code: " + error.Code);
                    Console.WriteLine("  Message: " + error.Message);
                }
            }
        }
    }
}