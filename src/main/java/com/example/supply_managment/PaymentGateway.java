package com.example.supply_managment;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.net.RequestOptions;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
// payment gateway functionality under development, not add in project yet
public class PaymentGateway {
    public void createCustomer() throws StripeException {

    }

    public static void main(String[] args) throws StripeException {
//        PaymentGateway paymentGateway = new PaymentGateway();
        Stripe.apiKey = "sk_test_51MJxa2SIb68XWXR0uZNNP8z8QCTsoLjKkwyEWJcoCXCPHZJqsNIhsTcax7tCsMa9NuSV797jfcwK7BBnlakPtilp0057T99y7R";
        RequestOptions requestOptions = RequestOptions.builder()
                .setApiKey("sk_test_51MJxa2SIb68XWXR0uZNNP8z8QCTsoLjKkwyEWJcoCXCPHZJqsNIhsTcax7tCsMa9NuSV797jfcwK7BBnlakPtilp0057T99y7R")
                .build();
        Charge charge = Charge.retrieve(
                "ch_3MKnU32eZvKYlo2C1o67roLR",
                requestOptions);
    }
}
