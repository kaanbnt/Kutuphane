package com.vaadin.menu.ui.components;

import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class Header extends VerticalLayout {

    private Label baslik;

    public Header() {
        setWidth(100, Unit.PERCENTAGE);
        setHeight(100, Unit.PIXELS);
        baslik=new Label();
        baslik.setValue("HOŞGELDİNİZ");
        baslik.addStyleName(ValoTheme.LABEL_H2);
        baslik.addStyleName(ValoTheme.TEXTAREA_ALIGN_CENTER);
        addComponent(baslik);
    }

    public void setHeaderTitle(String title) {
        baslik.setValue(title);
    }
}
