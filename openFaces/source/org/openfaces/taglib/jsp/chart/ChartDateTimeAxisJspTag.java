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
package org.openfaces.taglib.jsp.chart;

import org.openfaces.taglib.internal.chart.ChartDateTimeAxisTag;

import javax.el.ValueExpression;

/**
 * @author Ekaterina Shliakhovetskaya
 */
public class ChartDateTimeAxisJspTag extends ChartAxisJspTag {


    public ChartDateTimeAxisJspTag() {
        super(new ChartDateTimeAxisTag());
    }

    public void setLowerBound(ValueExpression lowerBound) {
        getDelegate().setPropertyValue("lowerBound", lowerBound);
    }

    public void setUpperBound(ValueExpression upperBound) {
        getDelegate().setPropertyValue("upperBound", upperBound);
    }

    public void setDateFormat(ValueExpression dateFormat) {
        getDelegate().setPropertyValue("dateFormat", dateFormat);
    }

}
