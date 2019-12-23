package com.vaadin.menu.domain;

import com.vaadin.menu.common.BaseDomain;

import javax.persistence.*;

@Entity
@Table(name = "YAZAR")
public class Yazar extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50)
    private String yazarAdi;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYazarAdi() {
        return yazarAdi;
    }

    public void setYazarAdi(String yazarAdi) {
        this.yazarAdi = yazarAdi;
    }

    @Override
    public String toString() {
        return yazarAdi;
    }
}
