package com.vaadin.menu.ui.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

public class SaveButton extends Button {
    public SaveButton(){
        setIcon(FontAwesome.CHECK);
        setCaption("KAYDET");
        addStyleName(ValoTheme.BUTTON_FRIENDLY);
    }
}
