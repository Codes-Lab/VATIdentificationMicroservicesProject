package com.codeslab.vatidentification.service;

import com.codeslab.vatidentification.VATHandlers.GenericVATHandler;
import com.codeslab.vatidentification.VATHandlers.IVATHandler;
import com.codeslab.vatidentification.persistence.IVATCountryRepository;
import com.codeslab.vatidentification.persistence.VATCountry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 */
@Service
public class VATAPIService implements IVATAPIService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VATAPIService.class);

    private final Map<String, IVATHandler> mCountriesVATHandlersMap = new HashMap<>();

    private final IVATCountryRepository ivatCountryRepository;

    @Autowired
    public VATAPIService(IVATCountryRepository ivatCountryRepository) {
        this.ivatCountryRepository = ivatCountryRepository;
    }

    public void initialize() {
        final List<VATCountry> vatCountries = ivatCountryRepository.findAll();
        for (VATCountry country : vatCountries) {
            if (this.isRegexValid(country.getVATFormat())) {
                mCountriesVATHandlersMap.put(country.getCode(), new GenericVATHandler(country.getVATFormat()));
            } else {
                LOGGER.error("Invalid regex {} found for the CountryCode {}", country.getVATFormat(), country.getCode());
            }
        }
    }

    private boolean isRegexValid(String regex) {
        return regex.matches("^[A-Z].*");
    }


    public boolean isVATIdentificationValid(String country, String vatId) {
        if (mCountriesVATHandlersMap.isEmpty()) {
            this.initialize();
        }
        final IVATHandler ivatHandler = mCountriesVATHandlersMap.get(country);
        if (ivatHandler == null) {
            LOGGER.error("Invalid country code {} provided.", country);
            return false;
        }
        return ivatHandler.isVATIDValid(vatId);
    }

    @Override
    public Set<String> getAllVATCountries() {
        if (mCountriesVATHandlersMap.isEmpty()) {
            this.initialize();
        }
        return mCountriesVATHandlersMap.keySet();
    }

    @Override
    public boolean deleteVATCountry(String country) {
        if (mCountriesVATHandlersMap.isEmpty()) {
            this.initialize();
        }
        if (mCountriesVATHandlersMap.get(country) != null) {
            mCountriesVATHandlersMap.remove(country);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addVATCountry(String country, String regex) {
        if (mCountriesVATHandlersMap.isEmpty()) {
            this.initialize();
        }
        return false;
    }
}
