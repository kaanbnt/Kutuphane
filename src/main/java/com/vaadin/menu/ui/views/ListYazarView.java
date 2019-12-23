package com.vaadin.menu.ui.views;

import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.menu.Dao.UyeDao;
import com.vaadin.menu.Dao.YazarDao;
import com.vaadin.menu.domain.Yazar;
import com.vaadin.menu.ui.components.DeleteButton;
import com.vaadin.shared.ui.MultiSelectMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;

import java.util.List;

public class ListYazarView extends BaseListView {
    private Table table;
    private IndexedContainer indexedContainer;
    private AddYazarView addYazarView;
    private DeleteButton deleteButton;
    Yazar yazar;

    public ListYazarView() {
        setSpacing(true);
        buildTableContainer();

        buildTableContainer();
        buildTable();
        addComponent(table);

        deleteButton = new DeleteButton();
        deleteButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                YazarDao yazarDao = new YazarDao();
                yazarDao.deleteYazar(yazar);
                Notification.show("Yazar Silindi");
            }
        });
        addComponent(deleteButton);

        addYazarView= new AddYazarView();
        addComponent(addYazarView);

        fillTable();
    }

    public void fillTable() {

        YazarDao yazarDao = new YazarDao();
        List<Yazar> yazarList = yazarDao.findAllYazar();
        for (Yazar yazar : yazarList) {
            Item item = indexedContainer.addItem(yazar);
            item.getItemProperty("id").setValue(yazar.getId());
            item.getItemProperty("yazarAdi").setValue(yazar.getYazarAdi());
        }
    }

    public void buildTableContainer() {

        indexedContainer = new IndexedContainer();
        indexedContainer.addContainerProperty("id", Long.class, null);
        indexedContainer.addContainerProperty("yazarAdi", String.class, null);
    }

    public void buildTable() {
        table = new Table();
        table.setHeight("250px");
        table.setContainerDataSource(indexedContainer);
        table.setColumnHeaders("NO", "YAZAR ADI");
        table.setSelectable(true);
        table.setMultiSelectMode(MultiSelectMode.SIMPLE);
        table.setMultiSelect(false);
        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                yazar = (Yazar) itemClickEvent.getItemId();
                addYazarView.fillViewByYazar(yazar);
            }
        });
    }
}
