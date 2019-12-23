package com.vaadin.menu.ui.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

import java.awt.*;

public class DeleteButton extends Button {
    public DeleteButton() {
        setIcon(FontAwesome.TRASH);
        setCaption("Sil");
        addStyleName(ValoTheme.BUTTON_DANGER);
    }
}
