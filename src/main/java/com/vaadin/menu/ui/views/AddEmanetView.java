package com.vaadin.menu.ui.views;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.menu.dao.EmanetDao;
import com.vaadin.menu.dao.KitapDao;
import com.vaadin.menu.dao.UyeDao;
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

    @PropertyId("id")
    private MenuTextField idField;

    @PropertyId("kitap")
    private ComboBox kitapCombo;

    @PropertyId("uye")
    private ComboBox uyeCombo;

    private SaveButton saveButton;
    private FormLayout formLayout;
    private BeanItem<Emanet> item;
    private FieldGroup binder;

    public AddEmanetView() {
        fillViewByEmanet(new Emanet());
    }

    public void fillViewByEmanet(Emanet emanet) {
        item = new BeanItem<Emanet>(emanet);
        binder = new FieldGroup(item);
        binder.bindMemberFields(this);
    }

    public void buildMainLayout() {
        formLayout = new FormLayout();
        addComponent(formLayout);

        idField = new MenuTextField("ID");
        idField.setEnabled(false);
        formLayout.addComponent(idField);

        kitapCombo = new ComboBox("Kitap Adı");
        formLayout.addComponent(kitapCombo);
        fillKitapCombo();

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

    private void fillKitapCombo() {
        KitapDao kitapDao = new KitapDao();
        List<Kitap> kitapList = kitapDao.findAllKitap();
        for (Kitap kitap : kitapList) {
            kitapCombo.addItem(kitap);
        }
    }

    public void saveView() {
        try {
            binder.commit();
            Emanet emanet = item.getBean();

            EmanetDao emanetDao = new EmanetDao();
            emanet = emanetDao.saveEmanet(emanet);
            idField.setValue(emanet.getId().toString());
            Notification.show("Emanet Eklendi");
        } catch (FieldGroup.CommitException e) {
            System.out.println(e.getMessage());
        }
    }
}

