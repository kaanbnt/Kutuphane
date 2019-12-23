package com.vaadin.menu.ui.components;

import com.vaadin.menu.ui.views.*;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class SideBar extends VerticalLayout {

    private Content content;

    private MenuButton btnMenuButton;

    public SideBar(Content content) {
        this.content = content;

        setSpacing(true);//elementler arası boşuk
        setMargin(true);//her tarafında boşluk

        btnMenuButton = new MenuButton(FontAwesome.PLUS);
        btnMenuButton.setCaption("Yazar Ekle");
        btnMenuButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                AddYazarView addYazarView=new AddYazarView();
                content.setContent(addYazarView);
            }
        });
        addComponent(btnMenuButton);

        btnMenuButton = new MenuButton(FontAwesome.PENCIL_SQUARE);
        btnMenuButton.setCaption("Yazar Listele");
        btnMenuButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                ListYazarView listYazarView=new ListYazarView();
                content.setContent(listYazarView);
            }
        });
        addComponent(btnMenuButton);


        btnMenuButton = new MenuButton(FontAwesome.PLUS);
        btnMenuButton.setCaption("Kitap Ekle");
        btnMenuButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                AddKitapView addKitapView=new AddKitapView();
                content.setContent(addKitapView);
            }
        });
        addComponent(btnMenuButton);

        btnMenuButton = new MenuButton(FontAwesome.BOOK);
        btnMenuButton.setCaption("Kitap Listele");
        btnMenuButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                ListKitapView listKitapView=new ListKitapView();
                content.setContent(listKitapView);
            }
        });
        addComponent(btnMenuButton);

        btnMenuButton = new MenuButton(FontAwesome.USER_PLUS);
        btnMenuButton.setCaption("Üye Ekle");
        btnMenuButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                AddUyeView addUyeView=new AddUyeView();
                content.setContent(addUyeView);
            }
        });
        addComponent(btnMenuButton);

        btnMenuButton = new MenuButton(FontAwesome.USER);
        btnMenuButton.setCaption("Üye Listele");
        btnMenuButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                ListUyeList listUyeList=new ListUyeList();
                content.setContent(listUyeList);
            }
        });
        addComponent(btnMenuButton);

        btnMenuButton = new MenuButton(FontAwesome.PLUS);
        btnMenuButton.setCaption("Emanet Ekle");
        btnMenuButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                AddEmanetView addEmanetView=new AddEmanetView();
                content.setContent(addEmanetView);
            }
        });
        addComponent(btnMenuButton);

        btnMenuButton = new MenuButton(FontAwesome.ARROW_CIRCLE_O_RIGHT);
        btnMenuButton.setCaption("Emanet Listele");
        btnMenuButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                ListEmanetView listEmanetView=new ListEmanetView();
                content.setContent(listEmanetView);
            }
        });
        addComponent(btnMenuButton);
    }
}
