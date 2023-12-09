package com.example.ecommers.controller;

import com.example.ecommers.dto.BidDTO;
import com.example.ecommers.dto.PaymentMPDTO;
import com.example.ecommers.model.*;
import com.example.ecommers.repository.I_UserRepository;
import com.example.ecommers.serviceInterface.I_BidService;
import com.example.ecommers.serviceInterface.I_ProductService;
import com.google.gson.Gson;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mercadopago.MercadoPagoConfig;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1/product/mercadoPago")
@RequiredArgsConstructor
public class MpController {

    private final I_BidService bidService;
    private final I_ProductService productService;

    @PostMapping("/compra")
    @CrossOrigin(origins = "https://www.mercadopago.com/")
    public ResponseEntity<String> mercadoPago(@Valid @RequestBody PaymentMPDTO paymentMPDTO) throws MPException, MPApiException {
        try {
            MercadoPagoConfig.setAccessToken("TEST-5642789275818402-120519-ce2348b6a2e786844849c5e1f8aa2e42-498645273");
            List<PreferenceItemRequest> items = new ArrayList<>();
            for (ItemEntity item : paymentMPDTO.getLstItem()) {
                PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                        .id(item.getProduct().getIdProduct().toString())
                        .title(item.getProduct().getProductName())
                        .pictureUrl(item.getProduct().getProductImg())
                        .description(item.getProduct().getDescription())
                        .categoryId(item.getProduct().getTypeCategory().getIdCategory().toString())
                        .quantity(item.getQuantitySelected())
                        .unitPrice(item.getProduct().getProductPrice())
                        .build();

                items.add(itemRequest);
            }
            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .backUrls(PreferenceBackUrlsRequest.builder()
                            .success("localhost:5173/Success")
                            .pending("localhost:5173")
                            .failure("localhost:5173/Failure")
                            .build())
                    .autoReturn("approved")
                    .build();
            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);

            // Aquí podrías devolver el ID de la preferencia o cualquier otra información que necesites.
            return ResponseEntity.ok(preference.getId());
        } catch (MPException | MPApiException e) {
            // Manejo de excepciones
            if (e instanceof MPApiException apiException) {
                System.out.println("Error de la API de Mercado Pago:");
                System.out.println("HTTP Status Code: " + apiException.getStatusCode());
                System.out.println("Response: " + apiException.getMessage());
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud");
        }
    }

    @PostMapping("/newBid")
    public ResponseEntity<?> createBid(@Valid @RequestBody BidDTO bidDTO) {
        try {
            System.out.println("BID  dto:"+bidDTO.toString());
            bidService.saveBid(bidDTO);
            List<ProductEntity>products=productService.findByStatusTrue();
            Gson gson = new Gson();
            String json = gson.toJson(products);
            return new  ResponseEntity<>(json,HttpStatus.CREATED);
        }catch (RuntimeException re){
            return new ResponseEntity<>(re.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}