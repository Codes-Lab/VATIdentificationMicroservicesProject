package com.codeslab.vatidentification.service;

import com.codeslab.vatidentification.VATHandlers.GenericVATHandler;
import com.codeslab.vatidentification.VATHandlers.IVATHandler;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Service
public class VATAPIService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VATAPIService.class);

    @Value("#{${VATCountriesWithFormat}}")
    private Map<String, String> mCountriesWithVATIdRegex;

    private final Map<String, IVATHandler> mCountriesVATHandlersMap = new HashMap<>();


    @PostConstruct
    public void initialize() {
        for (Map.Entry<String, String> entry : mCountriesWithVATIdRegex.entrySet()) {
            if (this.isRegexValid(entry.getValue())) {
                mCountriesVATHandlersMap.put(entry.getKey(), new GenericVATHandler(entry.getValue()));
            } else {
                LOGGER.error("Invalid regex {} found for the CountryCode {}", entry.getValue(), entry.getKey());
            }
        }
    }

    private boolean isRegexValid(String regex) {
        return regex.matches("^[A-Z].*");
    }


    public boolean isVATIdentificationValid(String country, String vatId) {
        final IVATHandler ivatHandler = mCountriesVATHandlersMap.get(country);
        if (ivatHandler == null) {
            LOGGER.error("Invalid country code {} provided.", country);
            return false;
        }
        return ivatHandler.isVATIDValid(vatId);
    }
}
