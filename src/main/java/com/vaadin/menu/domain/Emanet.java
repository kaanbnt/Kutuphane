package com.vaadin.menu.domain;

import com.vaadin.menu.common.BaseDomain;
import javax.persistence.*;

@Entity
@Table(name = "EMANET")
public class Emanet extends BaseDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_UYE", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "EMANET_UYE_ID"))
    private Uye uye;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_KITAP", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "EMANET_KITAP_ID"))
    private Kitap kitap;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Uye getUye() {
        return uye;
    }

    public void setUye(Uye uye) {
        this.uye = uye;
    }

    public Kitap getKitap() {
        return kitap;
    }

    public void setKitap(Kitap kitap) {
        this.kitap = kitap;
    }
}
