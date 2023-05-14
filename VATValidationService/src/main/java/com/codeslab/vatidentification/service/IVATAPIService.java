////////////////////////////////////////////////////////////////////////////////
//
// Created by AMundewal on 14.05.2023.
//
// Copyright (c) 2006 - 2023 FORCAM GmbH. All rights reserved.
////////////////////////////////////////////////////////////////////////////////

package com.codeslab.vatidentification.service;

import java.util.Set;

/**
 *
 */
public interface IVATAPIService {

    boolean isVATIdentificationValid(String country, String vatId);

    boolean addVATCountry(String country, String regex);

    Set<String> getAllVATCountries();

    boolean deleteVATCountry(String country);
}
