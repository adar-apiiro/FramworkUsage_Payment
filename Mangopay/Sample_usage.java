import com.mangopay.MangoPayApi;
import com.mangopay.core.CurrencyIso;
import com.mangopay.core.Money;
import com.mangopay.entities.PayOut;
import com.mangopay.entities.Wallet;
import com.mangopay.entities.BankAccount;

import java.util.Arrays;
import java.util.List;

public class MangoPayPaymentExample {

    public static void main(String[] args) {
        // Initialize MangoPay API
        MangoPayApi api = new MangoPayApi();
        api.getConfig().setClientId("your_client_id");
        api.getConfig().setClientPassword("your_client_password");
        api.getConfig().setBaseURL("https://api.sandbox.mangopay.com"); // Use the sandbox environment for testing

        try {
            // Create a Wallet
            Wallet wallet = new Wallet();
            wallet.setOwners(Arrays.asList("user_id")); // User ID associated with the wallet
            wallet.setDescription("My Wallet");
            wallet.setCurrency(CurrencyIso.EUR);

            api.getWalletApi().create(wallet);

            // Make a Payment
            PayOut payOut = new PayOut();
            payOut.setAuthorId("user_id"); // ID of the user initiating the payment
            payOut.setDebitedWalletId("source_wallet_id"); // ID of the source wallet
            payOut.setCreditedUserId("recipient_user_id"); // ID of the user receiving the payment
            payOut.setCreditedWalletId("destination_wallet_id"); // ID of the destination wallet
            payOut.setDebitedFunds(new Money());
            payOut.getDebitedFunds().setCurrency(CurrencyIso.EUR);
            payOut.getDebitedFunds().setAmount(1000); // Amount in cents (1000 cents = 10 EUR)
            payOut.setFees(new Money());
            payOut.getFees().setCurrency(CurrencyIso.EUR);
            payOut.getFees().setAmount(0); // No fees

            api.getPayOutApi().create(payOut);

            // Retrieve User Bank Accounts
            List<BankAccount> bankAccounts = getUserBankAccounts(api, "user_id", 1, 10);
            // Process the list of bank accounts as needed

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<BankAccount> getUserBankAccounts(MangoPayApi api, String userId, int page, int itemsPerPage) {
        try {
            // Fetch user's bank accounts
            return api.getUserApi().getBankAccounts(userId, page, itemsPerPage);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
