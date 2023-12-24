import com.wepay.WePay;                     // WePay object needed for API initialization
import com.wepay.model.*;                   // contains call classes and all API call functions
import com.wepay.model.data.*;              // contains all data objects needed for making calls
import com.wepay.model.data.AccountData;
import com.wepay.model.data.CheckoutData
import com.wepay.net.WePayResource;         // network resource used to execute calls
import com.wepay.exception.WePayException;  // handles WePay exceptions
import org.json.*;                          // SDK uses JSON when handling API call parameters
import com.google.gson.*;                   // SDK uses GSON for building objects from API responses

public class WePayExample {

  WePay wepay = new WePay();
  wepay.initialize(myClientId, myClientSecret, useStageEnv);
  
  // create a new account
  AccountData aData = new AccountData();
  aData.name = "Test Person";                          // required parameter for this API call
  aData.description = "This is an example account";    // required parameter for this API call
  aData.type = "personal";                             // optional parameter for this API call
  Account newAccount = Account.create(aData, myAccessToken);
  
  // get an accessToken for the new account
  OAuth2Data data = new OAuth2Data();
  data.redirectUri = "http://www.mywebsite.com/oauth2/";
  data.scope = "manage_accounts,collect_payments,view_user,preapprove_payments,send_money";
  String sendUserToThisURL = OAuth2.authorize(data, null);
  //send user to the returned URL to complete OAuth2 authorization form
  
  //on completion of authorization, user is redirected to redirectUri with a code parameter
  //once you have the code you can exchange it for an access token
  OAuth2Data data = new OAuth2Data();
  data.redirectUri = "http://www.mywebsite.com/oauth2/";
  data.code = code;
  String userNewAccessToken = OAuth2.token(data, null);
  
  // create a new checkout
  CheckoutData cData = new CheckoutData();
  cData.accountId = newAccount.getAccountId();       // use a returned object to access information
  cData.shortDescription = "Soccer Ball Purchase";
  cData.type = "GOODS";
  cData.amount = 29.95;
  cData.currency = "USD";
  Checkout newCheckout = Checkout.create(cData, userNewAccessToken);
  
}
