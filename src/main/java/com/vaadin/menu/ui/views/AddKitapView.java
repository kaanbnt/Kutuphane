package com.vaadin.menu.ui.views;

import com.vaadin.menu.dao.KitapDao;
import com.vaadin.menu.dao.YazarDao;
import com.vaadin.menu.domain.EnumKitapKategori;
import com.vaadin.menu.domain.Kitap;
import com.vaadin.menu.domain.Yazar;
import com.vaadin.menu.ui.components.MenuTextField;
import com.vaadin.menu.ui.components.SaveButton;
import com.vaadin.ui.*;

import java.util.List;

public class AddKitapView extends BaseAddView {

    private MenuTextField idField;
    private TextField kitapAdiField;
    private ComboBox enumCombo;
    private ComboBox yazarCombo;
    private SaveButton saveButton;
    private FormLayout formLayout;

    public AddKitapView() {
    }

    public void fillViewByKitap(Kitap kitap) {
        idField.setValue(kitap.getId().toString());
        kitapAdiField.setValue(kitap.getKitapAdi());
        enumCombo.setValue(kitap.getEnumKitapKategori());
        yazarCombo.setValue(kitap.getYazar());
    }

    public void buildMainLayout() {
        formLayout = new FormLayout();
        addComponent(formLayout);

        idField = new MenuTextField("ID");
        idField.setEnabled(false);
        formLayout.addComponent(idField);

        kitapAdiField = new TextField("Kitap Adı");
        formLayout.addComponents(kitapAdiField);

        enumCombo = new ComboBox("Kitap Türü");
        for (EnumKitapKategori string : EnumKitapKategori.values()) {
            enumCombo.addItem(string);
        }
        formLayout.addComponent(enumCombo);

        YazarDao yazarDao = new YazarDao();
        List<Yazar> yazarList = yazarDao.findAllYazar();
        yazarCombo = new ComboBox("Yazar Adı");
        for (Yazar yazar : yazarList) {
            yazarCombo.addItem(yazar);
        }
        formLayout.addComponent(yazarCombo);

        saveButton = new SaveButton();
        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                saveView();
            }
        });
        formLayout.addComponent(saveButton);
    }

    public void saveView() {
        Long idFieldValue = null;
        if (idField.getValue() != "") {
            idFieldValue = Long.parseLong(idField.getValue());
        }
        String nameKitapValue = kitapAdiField.getValue();
        EnumKitapKategori enumKitapKategori = (EnumKitapKategori) enumCombo.getValue();
        Yazar yazar = (Yazar) yazarCombo.getValue();

        Kitap kitap = new Kitap();
        kitap.setId(idFieldValue);
        kitap.setKitapAdi(nameKitapValue);
        kitap.setEnumKitapKategori(enumKitapKategori);
        kitap.setYazar(yazar);

        KitapDao kitapDao = new KitapDao();
        kitap = kitapDao.saveKitap(kitap);
        idField.setValue(kitap.getId().toString());
        Notification.show("Kitap Eklendi");
    }
}