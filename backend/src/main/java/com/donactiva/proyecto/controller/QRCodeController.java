package com.donactiva.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.donactiva.proyecto.service.QRCodeService;

@RestController
public class QRCodeController {

    @Autowired
    private QRCodeService qrCodeService;

    @GetMapping("/generate-qr")
    public ResponseEntity<byte[]> generateQRCode(@RequestParam String data) {
        try {
            byte[] qrCode = qrCodeService.generateQRCode(data, 250, 250); // Tamaño del código QR
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "image/png");
            return new ResponseEntity<>(qrCode, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

