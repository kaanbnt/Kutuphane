package com.vaadin.menu.ui.views;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.menu.dao.YazarDao;
import com.vaadin.menu.domain.Yazar;
import com.vaadin.menu.ui.components.SaveButton;
import com.vaadin.ui.*;

public class AddYazarView extends BaseAddView {

    @PropertyId("id")
    private TextField idField;

    @PropertyId("yazarAdi")
    private TextField yazarAdiField;

    private FormLayout mainLayout;
    private BeanItem<Yazar> item;
    private FieldGroup binder;

    public AddYazarView() {
        fillViewByYazar(new Yazar());
    }

    public void fillViewByYazar(Yazar yazar) {
        item = new BeanItem<Yazar>(yazar);
        binder = new FieldGroup(item);
        binder.bindMemberFields(this);
    }

    public void buildMainLayout() {
        mainLayout = new FormLayout();
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
        try {
            binder.commit();
            Yazar yazar=item.getBean();

            YazarDao yazarDao = new YazarDao();
            yazar = yazarDao.saveYazar(yazar);
            idField.setValue(yazar.getId().toString());
            Notification.show("Yazar Eklendi");
        }
        catch (FieldGroup.CommitException e){
            System.out.println(e.getMessage());
        }
    }
}
