package com.stripe.sample;

import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

public class Server {

    public static void main(String[] args) {
        port(4242);

        // This is a public sample test API key.
        // Don’t submit any personally identifiable information in requests made with this key.
        // Sign in to see your own test API key embedded in code samples.
        Stripe.apiKey = "sk_test_4eC39HqLyjWDarjtT1zdp7dc";

        staticFiles.externalLocation(
                Paths.get("public").toAbsolutePath().toString());

        post("/create-checkout-session", (request, response) -> {
            String YOUR_DOMAIN = "http://localhost:4242";
            SessionCreateParams params =
                    SessionCreateParams.builder()
                            .setMode(SessionCreateParams.Mode.PAYMENT)
                            .setSuccessUrl(YOUR_DOMAIN + "/success.html")
                            .setCancelUrl(YOUR_DOMAIN + "/cancel.html")
                            .addLineItem(
                                    SessionCreateParams.LineItem.builder()
                                            .setQuantity(1L)
                                            // Provide the exact Price ID (for example, pr_1234) of the product you want to sell
                                            .setPrice("{{PRICE_ID}}")
                                            .build())
                            .build();
            Session session = Session.create(params);

            response.redirect(session.getUrl(), 303);
            return "";
        });
    }
}
