package com.vaadin.menu.ui.views;

import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.menu.dao.UyeDao;
import com.vaadin.menu.domain.Uye;
import com.vaadin.menu.ui.components.DeleteButton;
import com.vaadin.shared.ui.MultiSelectMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import java.util.List;

public class ListUyeList extends BaseListView {
    private Table table;
    private IndexedContainer indexedContainer;
    private AddUyeView addUyeView;
    private DeleteButton deleteButton;
    Uye uye;

    public ListUyeList() {
        setSpacing(true);
        buildTableContainer();

        buildTableContainer();
        buildTable();
        addComponent(table);

        deleteButton = new DeleteButton();
        deleteButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                UyeDao uyeDao = new UyeDao();
                uyeDao.deleteUye(uye);
                Notification.show("Üye Silindi");
            }
        });
        addComponent(deleteButton);

        addUyeView = new AddUyeView();
        addComponent(addUyeView);

        fillTable();
    }

    public void fillTable() {

        UyeDao uyeDao = new UyeDao();
        List<Uye> uyeList = uyeDao.findAllUye();
        for (Uye uye : uyeList) {
            Item item = indexedContainer.addItem(uye);
            item.getItemProperty("id").setValue(uye.getId());
            item.getItemProperty("adi").setValue(uye.getAdi());
            item.getItemProperty("soyadi").setValue(uye.getSoyadi());
        }
    }

    public void buildTableContainer() {

        indexedContainer = new IndexedContainer();
        indexedContainer.addContainerProperty("id", Long.class, null);
        indexedContainer.addContainerProperty("adi", String.class, null);
        indexedContainer.addContainerProperty("soyadi", String.class, null);
    }

    public void buildTable() {
        table = new Table();
        table.setHeight("250px");
        table.setContainerDataSource(indexedContainer);
        table.setColumnHeaders("NO", "ÜYE ADI", "ÜYE SOYADI");
        table.setSelectable(true);
        table.setMultiSelectMode(MultiSelectMode.SIMPLE);
        table.setMultiSelect(false);
        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                uye = (Uye) itemClickEvent.getItemId();
                addUyeView.fillViewByUye(uye);
            }
        });
    }
}
