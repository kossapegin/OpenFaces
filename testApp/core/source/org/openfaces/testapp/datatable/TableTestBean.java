/*
 * OpenFaces - JSF Component Library 2.0
 * Copyright (C) 2007-2012, TeamDev Ltd.
 * licensing@openfaces.org
 * Unless agreed in writing the contents of this file are subject to
 * the GNU Lesser General Public License Version 2.1 (the "LGPL" License).
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * Please visit http://openfaces.org/licensing/ for more details.
 */
package org.openfaces.testapp.datatable;

import org.openfaces.component.filter.FilterCriterion;
import org.openfaces.component.filter.FilterCondition;
import org.openfaces.component.filter.CompositeFilterCriterion;
import org.openfaces.component.filter.ExpressionFilterCriterion;
import org.openfaces.component.table.FilterKind;
import org.openfaces.util.Faces;

import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ActionEvent;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Dmitry Pikhulya
 */

public class TableTestBean {

    private Logger logger = Logger.getLogger(TableTestBean.class.getName());

    private List<TestBean> collection1 = new ArrayList<TestBean>();
    private List<TestBean> collection2 = new ArrayList<TestBean>();

    private int[] testRowIndexes;
    private List testRowIds;
    private TestBean[] testRowDatas;
    private int testPageIndex;
    private int testPageIndex2;

    private int[] checkedRowIndexes1;
    private List checkedRowIds1;
    private TestBean[] checkedRowDatas1;
    private List checkedRowIndexes2;
    private List checkedRowIds2;
    private List checkedRowDatas2;

    private int counter;

    public TableTestBean() {
        logger.info("TableTestBean created");
        collection1.add(new TestBean("a_escapingTest:<test>&nbsp;&amp;'\n\"", 1));
        collection1.add(new TestBean("d", 11));
        collection1.add(new TestBean("b", 1));
        collection1.add(new TestBean("e", 4));
        collection1.add(new TestBean("c", 33));
        collection1.add(new TestBean("Four", 4));
        collection1.add(new TestBean("d", 3));
        collection1.add(new TestBean("e", 2));
        collection1.add(new TestBean("f", 2));
        collection1.add(new TestBean("d", 11));
        collection1.add(new TestBean("d", 33));
        collection1.add(new TestBean("f", 3));
        collection1.add(new TestBean("a", 4));
        collection1.add(new TestBean("Four", 4));
        collection1.add(new TestBean("Chetyre", 4));
        collection1.add(new TestBean(null, 0));

        collection2.addAll(collection1);
    }

    public List<TestBean> getCollection1() {
        logger.info("getCollection1() invoked");
        return collection1;
    }

    public List<TestBean> getCollection2() {
        logger.info("getCollection2() invoked");
        return collection2;
    }

    public int[] getTestRowIndexes() {
        return testRowIndexes;
    }

    public void setTestRowIndexes(int[] testRowIndexes) {
        this.testRowIndexes = testRowIndexes;
    }

    public List getTestRowIds() {
        return testRowIds;
    }

    public void setTestRowIds(List testRowIds) {
        this.testRowIds = testRowIds;
    }

    public TestBean[] getTestRowDatas() {
        return testRowDatas;
    }

    public void setTestRowDatas(TestBean[] testRowDatas) {
        this.testRowDatas = testRowDatas;
    }

    public int getTestPageIndex() {
        return testPageIndex;
    }

    public void setTestPageIndex(int testPageIndex) {
        this.testPageIndex = testPageIndex;
    }

    public int getTestPageIndex2() {
        return testPageIndex;
    }

    public void setTestPageIndex2(int testPageIndex2) {
        this.testPageIndex2 = testPageIndex2;
    }

    public String switchPage() {
        testPageIndex = testPageIndex2;
        return "";
    }

    public int[] getCheckedRowIndexes1() {
        return checkedRowIndexes1;
    }

    public void setCheckedRowIndexes1(int[] checkedRowIndexes1) {
        this.checkedRowIndexes1 = checkedRowIndexes1;
    }

    public List getCheckedRowIds1() {
        return checkedRowIds1;
    }

    public void setCheckedRowIds1(List checkedRowIds1) {
        this.checkedRowIds1 = checkedRowIds1;
    }

    public TestBean[] getCheckedRowDatas1() {
        return checkedRowDatas1;
    }

    public void setCheckedRowDatas1(TestBean[] checkedRowDatas1) {
        this.checkedRowDatas1 = checkedRowDatas1;
    }

    public List getCheckedRowIndexes2() {
        return checkedRowIndexes2;
    }

    public void setCheckedRowIndexes2(List checkedRowIndexes2) {
        this.checkedRowIndexes2 = checkedRowIndexes2;
    }

    public List getCheckedRowIds2() {
        return checkedRowIds2;
    }

    public void setCheckedRowIds2(List checkedRowIds2) {
        this.checkedRowIds2 = checkedRowIds2;
    }

    public List getCheckedRowDatas2() {
        return checkedRowDatas2;
    }

    public void setCheckedRowDatas2(List checkedRowDatas2) {
        this.checkedRowDatas2 = checkedRowDatas2;
    }

    public int getCollection1Size() {
        return requestSortedAndFilteredCollectionSize();
    }

    private int requestSortedAndFilteredCollectionSize() {
        return requestSortedAndFilteredCollection().size();
    }

    public Collection getCollection1Paged() {
        Integer pageStart = Faces.var("pageStart", Integer.class);
        Integer pageSize = Faces.var("pageSize", Integer.class);
        List filteredList = requestSortedAndFilteredCollection();
        boolean paginationUsed = pageStart != null && pageSize != null;
        if (!paginationUsed)
            return filteredList;
        return filteredList.subList(pageStart, pageStart + pageSize);
    }

    private List requestSortedAndFilteredCollection() {
        CompositeFilterCriterion filterCriteria = Faces.var("filterCriteria", CompositeFilterCriterion.class);
        String sortColumnId = Faces.var("sortColumnId", String.class);
        final boolean sortAscending = Faces.var("sortAscending", Boolean.class);

        List<TestBean> sortedList = new ArrayList<TestBean>(collection1);
        if ("col1".equals(sortColumnId))
            Collections.sort(sortedList, new Comparator<TestBean>() {
                public int compare(TestBean b1, TestBean b2) {
                    int result;
                    String f1 = b1.getField1();
                    String f2 = b2.getField1();
                    if (f1 == null || f2 == null) {
                        if (f1 == null && f2 == null) {
                            result = 0;
                        } else {
                            result = (f1 == null) ? -1 : +1;
                        }
                    } else {
                        result = f1.compareTo(f2);
                    }
                    if (!sortAscending) {
                        result = -result;
                    }
                    return result;
                }
            });
        else if ("col2".equals(sortColumnId))
            Collections.sort(sortedList, new Comparator<TestBean>() {
                public int compare(TestBean b1, TestBean b2) {
                    int result = Integer.valueOf(b1.getField2()).compareTo(b2.getField2());
                    if (!sortAscending) {
                        result = -result;
                    }
                    return result;
                }
            });

        return (filterCriteria != null) ? filterCollection(sortedList, filterCriteria) : sortedList;
    }

    private List<TestBean> filterCollection(List<TestBean> sortedList, CompositeFilterCriterion filterCriteria) {
        List<TestBean> result = new ArrayList<TestBean>();
        for (TestBean record : sortedList) {
            boolean recordAccepted = true;
            for (FilterCriterion c : filterCriteria.getCriteria()) {
                ExpressionFilterCriterion criterion = (ExpressionFilterCriterion) c;
                String fieldName = criterion.getExpressionStr();

                if (!filterAcceptsRecord(fieldName, criterion, record)) {
                    recordAccepted = false;
                    break;
                }
            }
            if (recordAccepted) {
                result.add(record);
            }
        }
        return result;
    }

    private boolean filterAcceptsRecord(String fieldName, ExpressionFilterCriterion criterion, TestBean record) {
        String fieldValue = fieldName.equals("col1") ? record.getField1() : String.valueOf(record.getField2());
        if (criterion != null && criterion.getCondition().equals(FilterCondition.EMPTY) && criterion.isInverse()) {
            return fieldValue != null && fieldValue.length() > 0;
        }
        if (criterion != null && criterion.getCondition().equals(FilterCondition.EMPTY)) {
            return fieldValue == null || fieldValue.length() == 0;
        }
        if (criterion instanceof ExpressionFilterCriterion) {
            String filterText = criterion.getArg1().toString();
            if (filterText == null) {
                return fieldValue == null;
            }
            if (fieldValue == null) {
                return true;
            }
            return fieldValue.toUpperCase().indexOf(filterText.toUpperCase()) != -1;
        }
        throw new IllegalStateException("Unknown filter criterion type: " + criterion.getClass());
    }

    public Object getObjectByKey() {
        Object key = Faces.var("rowKey");
        return TestBean.findById((String) key);
    }

    public Collection<Object> getCol1FilterValues() {
        // in real application this would be queried from a datables
        return Arrays.asList(new Object[]{null, "a", "b", "c", "d", "e", "f", "Chetyre", "Four"});
    }

    public Collection<String> getCol2FilterValues() {
        // in real application this would be queried from a datables
        return Arrays.asList("0", "1", "2", "3", "4", "11", "33");
    }

    public String getField2Range() {
        TestBean row = Faces.var("row", TestBean.class);
        int field2 = row.getField2();
        if (field2 < 10) {
            return "< 10";
        } else {
            return ">= 10";
        }
    }

    private int pageSize = 3;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    private Object testObj;

    public Object getTestObj() {
        return testObj;
    }

    public void setTestObj(Object testObj) {
        this.testObj = testObj;
    }

    public void insertRow() {
        TestBean newItem = new TestBean("", 0);
        collection1.add(newItem);
    }

    public void deleteRow() {
        List<TestBean> objectsToRemove = new ArrayList<TestBean>(Arrays.asList(testRowDatas));
        for (TestBean anObjectsToRemove : objectsToRemove) {
            collection1.remove(anObjectsToRemove);
        }
    }


    private Point scrollPos = new Point(0, 250);

    public Point getScrollPos() {
        return scrollPos;
    }

    public void setScrollPos(Point scrollPos) {
        this.scrollPos = scrollPos;
    }

    public void test() {
        logger.info("test");
    }

    public void test1(ValueChangeEvent event) {
        logger.info("test1");
        logger.info("  Old Value: " + event.getOldValue());
        logger.info("  New Value: " + event.getNewValue());
    }

    public void test2(ValueChangeEvent event) {
        logger.info("test2");
        logger.info("  Old Value: " + event.getOldValue());
        logger.info("  New Value: " + event.getNewValue());
    }

    private static final List<String> COLUMN_LIST_1 = new ArrayList<String>(Arrays.asList("1", "2", "3", "4", "5"));
    private static final List<String> COLUMN_LIST_2 = new ArrayList<String>(Arrays.asList("5", "9"));

    private List<String> columns = COLUMN_LIST_1;

    public List<String> getTestColumns() {
        return columns;
    }


    public void changeColumns() {
        if (columns == COLUMN_LIST_1)
            columns = COLUMN_LIST_2;
        else
            columns = COLUMN_LIST_1;
    }

    private int columnsCreated = 10;

    public void addColumn() {
        columns.add(String.valueOf(columnsCreated++));
    }

    private Object filterValue1;
    private Object filterValue2;

    public Object getFilterValue1() {
        return filterValue1;
    }

    public void setFilterValue1(Object filterValue1) {
        this.filterValue1 = filterValue1;
    }

    public Object getFilterValue2() {
        return filterValue2;
    }

    public void setFilterValue2(Object filterValue2) {
        this.filterValue2 = filterValue2;
    }

    public boolean isColumnRendered() {
        String colData = Faces.var("col", String.class);
        int colIndex = Integer.parseInt(colData);
        return colIndex != 3;
    }

    public boolean isSortingEnabled() {
        String colData = Faces.var("col", String.class);
        int colIndex = Integer.parseInt(colData);
        return colIndex % 2 == 0;
    }

    public FilterKind getFilterKind() {
        String colData = Faces.var("col", String.class);
        int colIndex = Integer.parseInt(colData);
        if (colIndex == 4) {
            return null;
        }
        switch (colIndex % 3) {
            case 0:
                return FilterKind.COMBO_BOX;
            case 1:
                return FilterKind.DROP_DOWN_FIELD;
            case 2:
                return FilterKind.SEARCH_FIELD;
            default:
                throw new UnsupportedOperationException();
        }
    }

    private Map filterValue = new HashMap();

    public Map getFilterValue() {
        return filterValue;
    }

    public List<String> getFilterValues() {
        String colData = Faces.var("col", String.class);
        if (colData.equals("5")) {
            return Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0");
        } else {
            return null;
        }
    }

    public List getColumnsOrder() {
        return null;
    }

    public boolean getSpecificRowsCondition() {
        TestBean row = Faces.var("row", TestBean.class);
        if (row == null) {
            return false;
        }
        String field1 = row.getField1();
        if (field1 == null) {
            return false;
        }
        return field1.indexOf("d") != -1;
    }

    public boolean getHighlightedColCondition1() {
        String colId = Faces.var("colId", String.class);
        return colId.startsWith("customCol");
    }

    public boolean getOddColumnsCondition() {
        Integer columnIndex = Faces.var("colIndex", Integer.class);
        return columnIndex % 2 == 0;
    }

    private static int _requestCounter = 0;

    public String getRequestId() {
        return "requestNo_" + _requestCounter++;
    }

    public void resetCounter(ActionEvent actionEvent) {
        counter = 0;
    }

    public void incrementCounter(ActionEvent actionEvent) {
        counter++;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void dynamicColumnActionTest() {
        TestBean row = Faces.var("row", TestBean.class);
        String col = Faces.var("col", String.class);

        System.out.println("dynamicColumnActionTest: row[field1='" + row.getField1() + "']; col['" + col + "']");

    }
}