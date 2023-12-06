package com.example.ecommers.controller;


import com.example.ecommers.dto.PaymentMPDTO;
import com.example.ecommers.model.ItemEntity;
import com.mercadopago.client.preference.PreferenceItemRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.mercadopago.MercadoPagoConfig;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1/product/mercadoPago")
@AllArgsConstructor
@NoArgsConstructor
public class MpController {
    @GetMapping
    public void mercadoPago(@Valid @RequestBody PaymentMPDTO paymentMPDTO){
        MercadoPagoConfig.setAccessToken("TEST-5642789275818402-120519-ce2348b6a2e786844849c5e1f8aa2e42-498645273");
        List<PreferenceItemRequest> items = new ArrayList<>();
        for(ItemEntity item:paymentMPDTO.getLstItem()){
            PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                    .id(item.getProduct().getIdProduct().toString())
                    .title(item.getProduct().getProductName())
                    .pictureUrl(item.getProduct().getProductImg())
                    .description(item.getProduct().getDescription())
                    .categoryId(item.getProduct().getTypeCategory().getIdCategory().toString())
                    .quantity(1)
                    .unitPrice(item.getProduct().getProductPrice())
                    .build();

            items.add(itemRequest);
        }
        /*
        List<Excluded> excludedPaymentMethods = new ArrayList<>();
        ExcludedPaymentMethod excludedPaymentMethod = new ExcludedPaymentMethod();
        excludedPaymentMethod.setId("master");
        excludedPaymentMethods.add(excludedPaymentMethod);

        List<ExcludedPaymentType> excludedPaymentTypes = new ArrayList<>();
        ExcludedPaymentType excludedPaymentType = new ExcludedPaymentType();
        excludedPaymentType.setId("ticket");
        excludedPaymentTypes.add(excludedPaymentType);

        PaymentMethods paymentMethods = {
                excluded_payment_methods: [],
        excluded_payment_types: [
        {
            id: "ticket"
        },
        {
            id: "credit_card"
        }
          ],
        installments: 1
}


        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items)
                .payer(payer)
                .backUrls(backUrls)
                .autoReturn("approved")
                .paymentMethods(paymentMethods)
                .notificationUrl("https://www.your-site.com/ipn")
                .statementDescriptor("MEUNEGOCIO")
                .externalReference("Reference_1234")
                .expires(true)
                .expirationDateFrom("2016-02-01T12:00:00.000-04:00")
                .expirationDateTo("2016-02-28T12:00:00.000-04:00")
                .build();

        */
    }
}
