package com.tokyo.beach.restaurants.pricerange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@CrossOrigin
public class PriceRangeController {
    private PriceRangeRepository priceRangeRepository;

    @Autowired
    public PriceRangeController(PriceRangeRepository priceRangeRepository) {
        this.priceRangeRepository = priceRangeRepository;
    }

    @RequestMapping(value = "/priceranges", method = GET)
    @ResponseStatus(HttpStatus.OK)
    public List<PriceRange> getAll() {
        return priceRangeRepository.getAll();
    }
}
