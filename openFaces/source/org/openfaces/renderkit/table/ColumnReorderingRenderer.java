/*
 * OpenFaces - JSF Component Library 2.0
 * Copyright (C) 2007-2009, TeamDev Ltd.
 * licensing@openfaces.org
 * Unless agreed in writing the contents of this file are subject to
 * the GNU Lesser General Public License Version 2.1 (the "LGPL" License).
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * Please visit http://openfaces.org/licensing/ for more details.
 */
package org.openfaces.renderkit.table;

import org.openfaces.component.table.AbstractTable;
import org.openfaces.component.table.BaseColumn;
import org.openfaces.component.table.ColumnReordering;
import org.openfaces.component.table.ColumnResizing;
import org.openfaces.component.table.ColumnResizingState;
import org.openfaces.renderkit.RendererBase;
import org.openfaces.util.RenderingUtil;
import org.openfaces.util.ResourceUtil;
import org.openfaces.util.ScriptBuilder;
import org.openfaces.util.StyleParam;
import org.openfaces.util.StyleUtil;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Dmitry Pikhulya
 */
public class ColumnReorderingRenderer extends RendererBase {
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        ColumnReordering cr = (ColumnReordering) component;
        AbstractTable table = (AbstractTable) component.getParent();
        StyleParam draggedCellStyle = new StyleParam(cr, "draggedCell", "o_table_draggedColumn");
        StyleParam autoScrollAreaStyle = new StyleParam(cr, "autoScrollArea", "o_headerAutoScrollArea");
        StyleParam dropTargetStyle = new StyleParam(cr, "dropTarget", "o_table_columnDropTarget");
        StyleUtil.renderStyleClasses(context, cr);
        // important: style rendering is made earlier than init function deliberately to allow script to
        // inspect styles dynamically
        RenderingUtil.renderInitScript(context,
                new ScriptBuilder().initScript(context, table,
                        "O$.Table._initColumnReordering",
                        draggedCellStyle,
                        cr.getDraggedCellTransparency(),
                        autoScrollAreaStyle,
                        cr.getAutoScrollAreaTransparency(),
                        ResourceUtil.getResourceURL(context, cr.getAutoScrollLeftImageUrl(), ColumnReorderingRenderer.class, "autoScrollLeft.gif"),
                        ResourceUtil.getResourceURL(context, cr.getAutoScrollRightImageUrl(), ColumnReorderingRenderer.class, "autoScrollRight.gif"),
                        dropTargetStyle,
                        ResourceUtil.getResourceURL(context, cr.getDropTargetTopImageUrl(), ColumnReorderingRenderer.class, "dropTargetTop.gif"),
                        ResourceUtil.getResourceURL(context, cr.getDropTargetBottomImageUrl(), ColumnReorderingRenderer.class, "dropTargetBottom.gif")
                ),
                AbstractTableRenderer.getTableJsURL(context)
        );
    }

    @Override
    public void decode(FacesContext context, UIComponent component) {
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        AbstractTable table = ((AbstractTable) component.getParent());
        String reorderingStr = params.get(table.getClientId(context) + "::reorderColumns");
        if (reorderingStr == null) return;
        String separator = "->";
        int separatorIdx = reorderingStr.indexOf(separator);
        int srcColIndex = Integer.parseInt(reorderingStr.substring(0, separatorIdx));
        int dstColIndex = Integer.parseInt(reorderingStr.substring(separatorIdx + separator.length()));
        if (srcColIndex == dstColIndex)
            return;
        List<BaseColumn> columns = new ArrayList<BaseColumn>(table.getColumnsForRendering());
        ColumnResizing columnResizing = table.getColumnResizing();
        ColumnResizingState resizingState = columnResizing != null ? columnResizing.getResizingState() : null;
        List<String> columnWidths = null;
        if (resizingState != null) {
            columnWidths = new ArrayList<String>();
            for (int i = 0, count = resizingState.getColumnCount(); i < count; i++)
                columnWidths.add(resizingState.getColumnWidth(i));
        }

        BaseColumn column = columns.remove(srcColIndex);
        columns.add(dstColIndex < srcColIndex ? dstColIndex : dstColIndex - 1, column);
        if (columnWidths != null) {
            String columnWidth = columnWidths.remove(srcColIndex);
            columnWidths.add(dstColIndex < srcColIndex ? dstColIndex : dstColIndex - 1, columnWidth);
            columnResizing.setResizingState(new ColumnResizingState(
                    columnWidths.toArray(new String[columnWidths.size()]),
                    resizingState.getTableWidth()));
        }

        List<String> columnsOrder = new ArrayList<String>(columns.size());
        for (BaseColumn col : columns) {
            columnsOrder.add(col.getId());
        }
        table.getAttributes().put("submittedColumnsOrder", columnsOrder);
    }
}