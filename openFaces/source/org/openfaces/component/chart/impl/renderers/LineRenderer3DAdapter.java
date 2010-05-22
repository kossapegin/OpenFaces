/*
 * OpenFaces - JSF Component Library 2.0
 * Copyright (C) 2007-2010, TeamDev Ltd.
 * licensing@openfaces.org
 * Unless agreed in writing the contents of this file are subject to
 * the GNU Lesser General Public License Version 2.1 (the "LGPL" License).
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * Please visit http://openfaces.org/licensing/ for more details.
 */

package org.openfaces.component.chart.impl.renderers;

import org.jfree.chart.renderer.category.LineRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.openfaces.component.chart.LineChartView;

import java.awt.*;

/**
 * @author Dmitry Pikhulya
 */
public class LineRenderer3DAdapter extends LineRenderer3D implements Chart3DRendererAdapter, CustomizedRenderer  {
    private ItemsRenderer delegate;

    public LineRenderer3DAdapter(LineChartView chartView, CategoryDataset dataSet) {
        delegate = new ItemsRenderer();
        ChartRendering.setupSeriesColors(chartView, this);

        ChartRendering.processLineAndShapeRendererProperties(this, dataSet, chartView);
    }


    @Override
    public Paint getItemOutlinePaint(int row, int column) {
        final Paint itemOutlinePaint = delegate.getItemOutlinePaint(row, column);
        if (itemOutlinePaint != null) {
            return itemOutlinePaint;
        }

        return super.getItemOutlinePaint(row, column);
    }

    @Override
    public Stroke getItemOutlineStroke(int row, int column) {
        final Stroke outlineStroke = delegate.getItemOutlineStroke(row, column);
        if (outlineStroke != null) {
            return outlineStroke;
        }

        return super.getItemOutlineStroke(row, column);
    }

    @Override
    public Paint getItemPaint(int row, int column) {
        final Paint itemPaint = delegate.getItemPaint(row, column);
        if (itemPaint != null) {
            return itemPaint;
        }

        return super.getItemPaint(row, column);
    }

    public void setItemOutlinePaint(int row, int column, Paint paint) {
        delegate.setItemOutlinePaint(row, column, paint);
    }

    public void setItemOutlineStroke(int row, int column, Stroke stroke) {
        delegate.setItemOutlineStroke(row, column, stroke);
    }

    public void setItemPaint(int row, int column, Paint paint) {
        delegate.setItemPaint(row, column, paint);
    }
}