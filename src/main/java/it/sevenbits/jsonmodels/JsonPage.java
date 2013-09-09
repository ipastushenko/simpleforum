package it.sevenbits.jsonmodels;

import it.sevenbits.entity.hibernate.TitleEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Json page messages model
 * @author Ivan Pastushenko @ sevenbits
 * @version 1.0.0 06.09.2013
 */
public class JsonPage extends JsonBase {
    private static final long serialVersionUID = -3741716781143989506L;
    private int currentPage;
    private int countPages;
    private List<String> elements;
    private List<Long> elementIds;
    private List<TitleEntity> arrayList = new ArrayList<>();

    public JsonPage(
            final int currentPage, final int countPages,
            final List<String> elements,final List<Long> elementIds
    ) {
        super(true);
        this.countPages = countPages;
        this.currentPage = currentPage;
        this.elements = elements;
        this.elementIds = elementIds;
        TitleEntity titleEntity = new TitleEntity();
        titleEntity.setId((long)1);
        titleEntity.setName("Hello");
        arrayList.add(titleEntity);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getCountPages() {
        return countPages;
    }

    public List<String> getElements() {
        return elements;
    }

    public List<Long> getElementIds() {
        return elementIds;
    }

    public List<TitleEntity> getArrayList() {
        return arrayList;
    }

    public void setCurrentPage(final int currentPage) {
        this.currentPage = currentPage;
    }

    public void setCountPages(final int countPages) {
        this.countPages = countPages;
    }

    public void setElements(final List<String> elements) {
        this.elements = elements;
    }

    public void setElementIds(final List<Long> elementIds) {
        this.elementIds = elementIds;
    }

    public void setArrayList(final List<TitleEntity> arrayList) {
        this.arrayList = arrayList;
    }
}
