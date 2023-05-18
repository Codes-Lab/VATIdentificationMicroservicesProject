package com.codeslab.vatidentification.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface IVATCountryRepository extends JpaRepository<VATCountry, Long> {

    VATCountry findByCode(String code);

    void deleteByName(String name);
}
