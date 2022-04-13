package ee.Karu.webshop.api;


import ee.Karu.webshop.model.input.EveryPayResponse;
import ee.Karu.webshop.model.output.EveryPayData;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.util.Date;

@RestController
public class PaymentController {

    @PostMapping("payment")
    public String getPaymentLink(@RequestBody String amount) {

        /*
        {
            "api_username": "92ddcfab96e34a5f",
                "account_name": "EUR3D1",
                "amount": 10.00,
                "order_reference": "2387",
                "nonce": "a9b7f7e71a01b9901",
                "timestamp": "2022-04-13T09:32:15+03:00",
                "customer_url": "https://shop.example.com/cart"
        }
        */

        EveryPayData everyPayData = new EveryPayData();
        everyPayData.setApi_username("92ddcfab96e34a5f");
        everyPayData.setAccount_name("EUR3D1");
        everyPayData.setAmount(Integer.parseInt(amount));
        everyPayData.setOrder_reference("abs" + Math.random());
        everyPayData.setNonce("ad" + Math.random() + ZonedDateTime.now());
        everyPayData.setTimestamp(ZonedDateTime.now().toString());
        everyPayData.setCustomer_url("https://www.delfi.ee"); // serverisse yles heroku -- java ja front-end (Angular/React)

        // urli t6stame application propeties sisse
        String url = "https://igw-demo.every-pay.com/lp/9hyazm/JEtaz2xz";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic OTJkZGNmYWI5NmUzNGE1Zjo4Y2QxOWU5OWU5YzJjMjA4ZWU1NjNhYmY3ZDBlNGRhZA==");
        HttpEntity<EveryPayData> httpEntity = new HttpEntity<>(everyPayData, headers);

        // yks ja sama new kogu aeg
        RestTemplate restTemplate = new RestTemplate(); // v6imaldab teha HTTP paringuid
         ResponseEntity<EveryPayResponse> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, EveryPayResponse.class);
        if( response.getStatusCodeValue() == 201 && response.getBody() != null) {
            return response.getBody().getPayment_link();
        }

        return "";
    }

}
