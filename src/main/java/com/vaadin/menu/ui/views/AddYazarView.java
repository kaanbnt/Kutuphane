package com.vaadin.menu.ui.views;

import com.vaadin.menu.dao.YazarDao;
import com.vaadin.menu.domain.Yazar;
import com.vaadin.menu.ui.components.SaveButton;
import com.vaadin.ui.*;

public class AddYazarView extends BaseAddView {
    private TextField idField;
    private TextField yazarAdiField;
    private FormLayout mainLayout;

    public AddYazarView(){
    }

    public void fillViewByYazar(Yazar yazar) {
        idField.setValue(yazar.getId().toString());
        yazarAdiField.setValue(yazar.getYazarAdi());
    }

    public void  buildMainLayout(){
        mainLayout=new FormLayout();
        addComponent(mainLayout);

        idField = new TextField("ID");
        idField.setEnabled(false);
        mainLayout.addComponent(idField);

        yazarAdiField = new TextField("Yazar Adı");
        mainLayout.addComponent(yazarAdiField);

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
        String nameFieldValue = yazarAdiField.getValue();

        Yazar yazar = new Yazar();
        yazar.setId(idFieldValue);
        yazar.setYazarAdi(nameFieldValue);

        YazarDao yazarDao = new YazarDao();
        yazar = yazarDao.saveYazar(yazar);
        idField.setValue(yazar.getId().toString());
        Notification.show("Yazar Eklendi");
    }

}
