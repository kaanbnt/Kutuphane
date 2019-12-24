package com.vaadin.menu.domain;

import com.vaadin.menu.common.BaseDomain;

import javax.persistence.*;

@Entity
@Table(name = "KITAP")
public class Kitap extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 255)
    private String kitapAdi;

    @Enumerated(EnumType.STRING)
    @Column(length = 25)
    private EnumKitapKategori enumKitapKategori;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_YAZAR", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "KITAP_YAZAR_ID"))
    private Yazar yazar;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKitapAdi() {
        return kitapAdi;
    }

    public void setKitapAdi(String kitapAdi) {
        this.kitapAdi = kitapAdi;
    }

    public EnumKitapKategori getEnumKitapKategori() {
        return enumKitapKategori;
    }

    public void setEnumKitapKategori(EnumKitapKategori enumKitapKategori) {
        this.enumKitapKategori = enumKitapKategori;
    }

    public Yazar getYazar() {
        return yazar;
    }

    public void setYazar(Yazar yazar) {
        this.yazar = yazar;
    }

    @Override
    public String toString() {
        return kitapAdi;
    }
}
