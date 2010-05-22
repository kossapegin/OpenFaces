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

package org.openfaces.component.chart.impl.renderers.states;

import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.renderer.xy.XYItemRendererState;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Eugene Goncharov
 */
public class XYLineAreaFillItemRendererState extends XYItemRendererState {
    private Polygon areaPolygon;
    private Collection<Line2D.Double> lines;

    public XYLineAreaFillItemRendererState(PlotRenderingInfo info) {
        super(info);

        areaPolygon = new Polygon();
        lines = new ArrayList<Line2D.Double>();
    }

    public Polygon getAreaPolygon() {
        return areaPolygon;
    }

    public void setAreaPolygon(Polygon areaPolygon) {
        this.areaPolygon = areaPolygon;
    }

    public Collection<Line2D.Double> getLines() {
        return lines;
    }

    public void setLines(Collection<Line2D.Double> lines) {
        this.lines = lines;
    }
}
