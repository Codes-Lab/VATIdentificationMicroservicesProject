////////////////////////////////////////////////////////////////////////////////
//
// Created by AMundewal on 14.05.2023.
//
// Copyright (c) 2006 - 2023 FORCAM GmbH. All rights reserved.
////////////////////////////////////////////////////////////////////////////////

package com.codeslab.vatidentification.persistence;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

/**
 *
 */
@Entity
@Table(name = "VAT_COUNTRY")
@Cacheable(false)
public class VATCountry implements Serializable {

    @Serial
    private static final long serialVersionUID = -7994401085596069960L;

    @Id
    @GeneratedValue
    private Long Id;
    private String name;
    private String code;
    @Column(name = "VAT_FORMAT")
    private String VATFormat;

    @Column(name = "ID")
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "VAT_FORMAT")
    public String getVATFormat() {
        return VATFormat;
    }

    public void setVATFormat(String VATFormat) {
        this.VATFormat = VATFormat;
    }
}
