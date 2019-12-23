package com.vaadin.menu.domain;

import com.vaadin.menu.common.BaseDomain;
import javax.persistence.*;

@Entity
@Table(name = "UYE")
public class Uye extends BaseDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50)
    private String adi;

    @Column(length = 50)
    private String soyadi;


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getSoyadi() {
        return soyadi;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }

    @Override
    public String toString() {
        return adi;
    }
}
