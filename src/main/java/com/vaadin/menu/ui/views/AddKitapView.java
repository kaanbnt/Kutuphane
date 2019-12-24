package com.vaadin.menu.ui.views;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
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

    @PropertyId("id")
    private MenuTextField idField;

    @PropertyId("kitapAdi")
    private TextField kitapAdiField;

    @PropertyId("enumKitapKategori")
    private ComboBox enumCombo;

    @PropertyId("yazar")
    private ComboBox yazarCombo;

    private SaveButton saveButton;
    private FormLayout formLayout;
    private BeanItem<Kitap> item;
    private FieldGroup binder;

    public AddKitapView() {
        fillViewByKitap(new Kitap());
    }

    public void fillViewByKitap(Kitap kitap) {
        item = new BeanItem<Kitap>(kitap);
        binder = new FieldGroup(item);
        binder.bindMemberFields(this);
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
        try {
            binder.commit();
            Kitap kitap = item.getBean();

            KitapDao kitapDao = new KitapDao();
            kitap = kitapDao.saveKitap(kitap);
            idField.setValue(kitap.getId().toString());
            Notification.show("Kitap Eklendi");

        } catch (FieldGroup.CommitException e) {
            System.out.println(e.getMessage());
        }
    }
}