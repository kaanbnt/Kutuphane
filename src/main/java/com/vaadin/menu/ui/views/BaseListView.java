package com.vaadin.menu.ui.views;

import com.vaadin.ui.VerticalLayout;

public abstract class BaseListView extends VerticalLayout {

    public abstract void fillTable();

    public abstract void buildTableContainer();

    public abstract void buildTable();

}
