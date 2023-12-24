import com.wepay.WePay;
import com.wepay.model.Account;
import com.wepay.model.Checkout;
import com.wepay.model.data.AccountData;
import com.wepay.model.data.CheckoutData;
import com.wepay.model.data.OAuth2Data;
import com.wepay.net.OAuth2;
import com.wepay.exception.WePayException;

public class WePayExample {

    public static void main(String[] args) {
        WePay wepay = new WePay();
        wepay.initialize(myClientId, myClientSecret, useStageEnv);

        // create a new account
        AccountData aData = new AccountData();
        aData.name = "Test Person";                          // required parameter for this API call
        aData.description = "This is an example account";    // required parameter for this API call
        aData.type = "personal";                             // optional parameter for this API call
        try {
            Account newAccount = Account.create(aData, myAccessToken);

            // get an accessToken for the new account
            OAuth2Data oauth2Data = new OAuth2Data();
            oauth2Data.redirectUri = "http://www.mywebsite.com/oauth2/";
            oauth2Data.scope = "manage_accounts,collect_payments,view_user,preapprove_payments,send_money";
            String sendUserToThisURL = OAuth2.authorize(oauth2Data, null);
            // send the user to the returned URL to complete OAuth2 authorization form

            // on completion of authorization, the user is redirected to redirectUri with a code parameter
            // once you have the code, you can exchange it for an access token
            OAuth2Data tokenData = new OAuth2Data();
            tokenData.redirectUri = "http://www.mywebsite.com/oauth2/";
            tokenData.code = code;
            String userNewAccessToken = OAuth2.token(tokenData, null);

            // create a new checkout
            CheckoutData cData = new CheckoutData();
            cData.accountId = newAccount.getAccountId();       // use a returned object to access information
            cData.shortDescription = "Soccer Ball Purchase";
            cData.type = "GOODS";
            cData.amount = 29.95;
            cData.currency = "USD";
            Checkout newCheckout = Checkout.create(cData, userNewAccessToken);

        } catch (WePayException e) {
            e.printStackTrace();
        }
    }
}
