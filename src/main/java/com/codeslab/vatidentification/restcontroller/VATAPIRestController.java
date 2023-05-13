package com.codeslab.vatidentification.restcontroller;

import com.codeslab.vatidentification.service.VATAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/customers")
public class VATAPIRestController {

    @Autowired
    private VATAPIService mVATAPIService;

    @RequestMapping(value = "/verify", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean getVATIdValidationStatus(String country, String vatId) {
        return mVATAPIService.isVATIdentificationValid(country, vatId);
    }

}
