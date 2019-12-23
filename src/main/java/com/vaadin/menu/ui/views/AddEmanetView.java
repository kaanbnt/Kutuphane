package com.vaadin.menu.ui.views;

import com.vaadin.menu.Dao.EmanetDao;
import com.vaadin.menu.Dao.KitapDao;
import com.vaadin.menu.Dao.UyeDao;
import com.vaadin.menu.domain.Emanet;
import com.vaadin.menu.domain.Kitap;
import com.vaadin.menu.domain.Uye;
import com.vaadin.menu.ui.components.MenuTextField;
import com.vaadin.menu.ui.components.SaveButton;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;

import java.util.List;

public class AddEmanetView extends BaseAddView {

    private MenuTextField idField;
    private ComboBox kitapCombo;
    private ComboBox uyeCombo;
    private SaveButton saveButton;
    private FormLayout formLayout;

    public AddEmanetView() {
    }

    public void fillViewByEmanet(Emanet emanet) {
        idField.setValue(emanet.getId().toString());
        kitapCombo.setValue(emanet.getKitap());
        uyeCombo.setValue(emanet.getUye());
    }

    public void buildMainLayout(){
        formLayout = new FormLayout();
        addComponent(formLayout);

        idField = new MenuTextField("ID");
        idField.setEnabled(false);
        formLayout.addComponent(idField);

        KitapDao kitapDao = new KitapDao();
        List<Kitap> kitapList = kitapDao.findAllKitap();
        kitapCombo = new ComboBox("Kitap Adı");
        for (Kitap kitap : kitapList) {
            kitapCombo.addItem(kitap);
        }
        formLayout.addComponent(kitapCombo);

        UyeDao uyeDao = new UyeDao();
        List<Uye> uyeList = uyeDao.findAllUye();
        uyeCombo = new ComboBox("Üye Adı");
        for (Uye uye : uyeList) {
            uyeCombo.addItem(uye);
        }
        formLayout.addComponent(uyeCombo);

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
        Kitap kitap = (Kitap) kitapCombo.getValue();
        Uye uye = (Uye) uyeCombo.getValue();

        Emanet emanet=new Emanet();
        emanet.setId(idFieldValue);
        emanet.setKitap(kitap);
        emanet.setUye(uye);

        EmanetDao emanetDao = new EmanetDao();
        emanet = emanetDao.saveEmanet(emanet);
        idField.setValue(emanet.getId().toString());
        Notification.show("Emanet Eklendi");
    }
}
