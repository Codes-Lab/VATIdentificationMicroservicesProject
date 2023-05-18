package com.codeslab.vatidentification.restcontroller;

import com.codeslab.vatidentification.service.IVATAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 *
 */
@RestController
//Allows the Cross Origin requests to the controller.
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/customers")
public class VATAPIRestController {

    @Autowired
    private IVATAPIService mVATAPIService;

    @RequestMapping(value = "/verify", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean getVATIdValidationStatus(String country, String vatId) {
        return mVATAPIService.isVATIdentificationValid(country, vatId);
    }

    @RequestMapping(value = "/getAllCountries", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Set<String> getAllVATCountries() {
        return mVATAPIService.getAllVATCountries();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean deleteVATCountry(@RequestParam("country") String country) {
        return mVATAPIService.deleteVATCountry(country);
    }

    @GetMapping(value = "/addcountry")
    @ResponseBody
    public boolean addNewVATCountryWithRegex(@RequestParam("country") String country,
                                             @RequestParam("countryCode") String countryCode,
                                             @RequestParam("regex") String regex) {
        return mVATAPIService.addVATCountry(country, countryCode, regex);
    }

}
