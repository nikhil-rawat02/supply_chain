package com.example.supply_managment;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.net.RequestOptions;

import java.util.HashMap;
import java.util.Map;
// payment gateway functionality under development, not add in project yet
public class PaymentGateway {
    public void createCustomer() throws StripeException {

    }

    public static void main(String[] args) throws StripeException {
//        PaymentGateway paymentGateway = new PaymentGateway();
        Stripe.apiKey = "sk_test_51MJxa2SIb68XWXR0uZNNP8z8QCTsoLjKkwyEWJcoCXCPHZJqsNIhsTcax7tCsMa9NuSV797jfcwK7BBnlakPtilp0057T99y7R";
        Map<String,Object> customerParameter = new HashMap<String, Object>();
        customerParameter.put("email", "new@email");

        Customer newCustomer = Customer.create(customerParameter);
        System.out.println(newCustomer.getId());
//        paymentGateway.createCustomer();

    }
}
