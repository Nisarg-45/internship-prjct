package com.onerivet.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.dto.AddOnDto;
import com.onerivet.service.AddOnService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/add-ons")
@RequiredArgsConstructor
public class AddOnController {

    private final AddOnService addOnService;

    @GetMapping
    public ResponseEntity<List<AddOnDto>> getAddOns(@RequestParam Integer vehicleTypeId) {
        List<AddOnDto> addOns = addOnService.getAddOns(vehicleTypeId);

        if (addOns.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(addOns);
    }
}
