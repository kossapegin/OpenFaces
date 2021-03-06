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

package org.openfaces.component.chart.impl.configuration.charts;

import org.jfree.chart.plot.Plot;
import org.openfaces.component.chart.Chart;
import org.openfaces.component.chart.ChartModel;
import org.openfaces.component.chart.impl.ModelInfo;

/**
 * @author Eugene Goncharov
 */
public abstract class AbstractChartConfigurator implements ChartConfigurator {
    private ChartModel model;

    public AbstractChartConfigurator(ChartModel model) {
        this.model = model;
    }

    public ChartModel getModel() {
        return model;
    }

    public abstract Plot configurePlot(Chart chart, ModelInfo info);
}
