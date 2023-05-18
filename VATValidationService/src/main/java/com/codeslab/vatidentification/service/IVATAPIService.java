
package com.codeslab.vatidentification.service;

import com.codeslab.vatidentification.exception.BadRequestException;

import java.util.Set;

/**
 *
 */
public interface IVATAPIService {

    boolean isVATIdentificationValid(String country, String vatId);

    boolean addVATCountry(String country, String countryCode, String regex) throws BadRequestException;

    Set<String> getAllVATCountries();

    boolean deleteVATCountry(String country);
}
