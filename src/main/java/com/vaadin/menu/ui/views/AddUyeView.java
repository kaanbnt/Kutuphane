package com.vaadin.menu.ui.views;

import com.vaadin.menu.dao.UyeDao;
import com.vaadin.menu.domain.Uye;
import com.vaadin.menu.ui.components.SaveButton;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

public class AddUyeView extends BaseAddView {
    private TextField idField;
    private TextField uyeAdiField;
    private TextField uyeSoyadiField;
    private FormLayout mainLayout;

    public void AddUyeView(){

    }

    public void fillViewByUye(Uye uye) {
        idField.setValue(uye.getId().toString());
        uyeAdiField.setValue(uye.getAdi());
        uyeSoyadiField.setValue(uye.getSoyadi());
    }

    public void  buildMainLayout(){
        mainLayout=new FormLayout();
        addComponent(mainLayout);

        idField = new TextField("ID");
        idField.setEnabled(false);
        mainLayout.addComponent(idField);

        uyeAdiField = new TextField("Üye Adı");
        mainLayout.addComponent(uyeAdiField);

        uyeSoyadiField=new TextField("Üye Soyadı");
        mainLayout.addComponents(uyeSoyadiField);

        SaveButton saveButton = new SaveButton();
        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                saveView();
            }
        });
        mainLayout.addComponent(saveButton);
    }

    public void saveView() {
        Long idFieldValue = null;
        if (idField.getValue() != "") {
            idFieldValue = Long.parseLong(idField.getValue());
        }
        String uyeAdiFieldValue = uyeAdiField.getValue();
        String uyeSoyadiFieldValue=uyeSoyadiField.getValue();

        Uye uye = new Uye();
        uye.setId(idFieldValue);
        uye.setAdi(uyeAdiFieldValue);
        uye.setSoyadi(uyeSoyadiFieldValue);

        UyeDao uyeDao = new UyeDao();
        uye = uyeDao.saveUye(uye);
        idField.setValue(uye.getId().toString());
        Notification.show("Üye Eklendi");
    }
}
