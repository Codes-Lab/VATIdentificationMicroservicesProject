package com.codeslab.vatidentification.service;

import com.codeslab.vatidentification.VATHandlers.GenericVATHandler;
import com.codeslab.vatidentification.VATHandlers.IVATHandler;
import com.codeslab.vatidentification.exception.BadRequestException;
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

    private void createVATHandler(VATCountry vatCountry) {
        mCountriesVATHandlersMap.put(vatCountry.getCode(), new GenericVATHandler(vatCountry.getVATFormat()));
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
    public Set<String> getAllVATCountries() throws BadRequestException {
        if (mCountriesVATHandlersMap.isEmpty()) {
            this.initialize();
        }
        return mCountriesVATHandlersMap.keySet();
    }

    @Override
    public boolean deleteVATCountry(String country) throws BadRequestException {
        if (mCountriesVATHandlersMap.isEmpty()) {
            this.initialize();
        }
        try {
            if (mCountriesVATHandlersMap.get(country) != null) {
                ivatCountryRepository.deleteByName(country);
                mCountriesVATHandlersMap.remove(country);
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            throw new BadRequestException("Something went wrong.", ex);
        }
    }

    @Override
    public boolean addVATCountry(String country, String countryCode, String regex) throws BadRequestException {
        if (mCountriesVATHandlersMap.isEmpty()) {
            this.initialize();
        }

        try {
            final VATCountry byCode = ivatCountryRepository.findByCode(countryCode);
            if (byCode != null) {
                throw new BadRequestException("Cannot create duplicate records.");
            }
            final VATCountry saved = ivatCountryRepository.saveAndFlush(new VATCountry(country, countryCode, regex));
            if (saved.getId() > 0L) {
                this.createVATHandler(saved);
                return true;
            }
            return false;
        } catch (Exception ex) {
            throw new BadRequestException("Something went wrong.", ex);
        }
    }
}
