package test.portone.controller;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PaymentController {

    @Value("${iamport.key}")
    private String restApiKey;

    @Value("${iamport.secret}")
    private String restApiSecret;

    private IamportClient iamportClient;

    @PostConstruct
    public void init(){
        this.iamportClient = new IamportClient(restApiKey, restApiSecret);
    }

    @PostMapping("/api/v1/payments")
    public IamportResponse<Payment> paymentByImpUid(@RequestBody PaymentRequest request) throws
            IamportResponseException,
            IOException {
        log.info("Payment Request : {}", request.toString());
        return iamportClient.paymentByImpUid(request.impUid());
    }
}
