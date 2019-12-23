package com.vaadin.menu.ui.views;

import com.vaadin.ui.VerticalLayout;

public abstract class BaseAddView extends VerticalLayout {

    public BaseAddView() {
        buildMainLayout();
    }

    public abstract void buildMainLayout();

    public abstract void saveView();
}
