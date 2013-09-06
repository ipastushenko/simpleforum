package it.sevenbits.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Utils for controllers
 * @author Ivan Pastushenko @ sevenbits
 * @version 1.0.0 02.09.2013
 */
final class ControllerUtils {
    /**default count rows of table*/
    public static final int DEFAULT_COUNT_ROWS_TABLE = 10;

    public static int getCurrentPage(Long page, int countPages) {
        int currentPage = page.intValue();
        if (currentPage > countPages)
            currentPage = countPages;

        return currentPage;
    }

    public static int getCountPages(int listSize, int countRows) {
        int countPages = listSize / countRows;
        if (listSize % countRows != 0 || listSize == 0)
            countPages += 1;

        return countPages;
    }

    public static Long getCurrentPage(Long page) {
        if (page == null)
            return (long) 1;
        return page;
    }
    /**
     * Get index of prev page
     * @param currentPage index of current page
     * @return index of prev page
     */
    public static int getPagePrev(int currentPage) {
        int pagePrev = currentPage - 1;
        if (pagePrev < 0)
            pagePrev = 0;
        return pagePrev;
    }

    /**
     * Get index of next page
     * @param currentPage index of current page
     * @param countRow count rows
     * @param listSize size of list objects
     * @return index of next page
     */
    public static int getPageNext(int currentPage, int countRow, int listSize) {
        int pageNext = currentPage + 1;
        if (currentPage * countRow >= listSize)
            pageNext = 1;
        return pageNext;
    }

    /**
     * Get list objects of current page
     * @param list list objects
     * @param countRow count rows of page
     * @param currentPage index of current page
     * @param <T> type o object in list
     * @return list objects of current page
     */
    public static <T> List<T> getListEntity(List<T> list, int countRow, int currentPage) {
        List<T> listEntity = new ArrayList<>();
        for (int i = (currentPage - 1) * countRow; i < list.size() && i < currentPage * countRow; ++i) {
            listEntity.add(list.get(i));
        }
        return listEntity;
    }

    /**
     * Get count rows
     * @param resourceClass resourse class
     * @return count rows
     */
    public static int getCountRow(Class resourceClass) {
        Properties prop = new Properties();
        try {
            InputStream inStream = resourceClass.getClassLoader().getResourceAsStream("common.properties");
            prop.load(inStream);
            inStream.close();
        } catch (IOException e) {
            return DEFAULT_COUNT_ROWS_TABLE;
        }
        return Integer.parseInt(prop.getProperty("countrows"));
    }
}
