////////////////////////////////////////////////////////////////////////////////
//
// Created by AMundewal on 14.05.2023.
//
// Copyright (c) 2006 - 2023 FORCAM GmbH. All rights reserved.
////////////////////////////////////////////////////////////////////////////////

package com.codeslab.vatidentification.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface IVATCountryRepository extends JpaRepository<VATCountry, Long> {
}
