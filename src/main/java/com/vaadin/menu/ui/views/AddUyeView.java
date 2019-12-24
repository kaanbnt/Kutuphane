package com.vaadin.menu.ui.views;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.menu.dao.UyeDao;
import com.vaadin.menu.domain.Uye;
import com.vaadin.menu.ui.components.SaveButton;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

public class AddUyeView extends BaseAddView {

    @PropertyId("id")
    private TextField idField;

    @PropertyId("adi")
    private TextField uyeAdiField;

    @PropertyId("soyadi")
    private TextField uyeSoyadiField;

    private FormLayout mainLayout;
    private BeanItem<Uye> item;
    private FieldGroup binder;

    public AddUyeView(){
        fillViewByUye(new Uye());
    }

    public void fillViewByUye(Uye uye) {
        item = new BeanItem<Uye>(uye);
        binder = new FieldGroup(item);
        binder.bindMemberFields(this);
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
                fillViewByUye(new Uye());

            }
        });
        mainLayout.addComponent(saveButton);
    }

    public void saveView() {
        try {
            binder.commit();
            Uye uye = item.getBean();

            UyeDao uyeDao = new UyeDao();
            uye = uyeDao.saveUye(uye);
            idField.setValue(uye.getId().toString());
            Notification.show("Üye Eklendi");
        }
        catch (FieldGroup.CommitException e){
            System.out.println(e.getMessage());
        }
    }
}
