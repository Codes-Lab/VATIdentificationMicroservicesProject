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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String code;
    @Column(name = "VAT_FORMAT")
    private String VATFormat;

    public VATCountry() {
    }

    public VATCountry(String name, String code, String VATFormat) {
        this.name = name;
        this.code = code;
        this.VATFormat = VATFormat;
    }

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
