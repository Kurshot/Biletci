package Biletci.controller;

import Biletci.enums.City;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/city")
public class CityController {
    @GetMapping
    public ResponseEntity<List<String>> getAllCities() {
        List<String> cities = Arrays.stream(City.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return ResponseEntity.ok(cities);
    }
}
