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
package org.openfaces.component.filter.criterion;

import org.openfaces.component.filter.FilterCriterion;
import org.openfaces.component.filter.FilterCriterionProcessor;

import java.util.List;

/**
 * @author Dmitry Pikhulya
 */
public class AndFilterCriterion extends CompositeFilterCriterion {

    public AndFilterCriterion() {
    }

    public AndFilterCriterion(List<FilterCriterion> criteria) {
        super(criteria);
    }

    public boolean acceptsAll() {
        List<FilterCriterion> criteria = getCriteria();
        for (FilterCriterion criterion : criteria) {
            if (!criterion.acceptsAll())
                return false;
        }
        return true;
    }

    public boolean acceptsValue(Object value) {
        List<FilterCriterion> criteria = getCriteria();
        for (FilterCriterion criterion : criteria) {
            if (!criterion.acceptsValue(value))
                return false;
        }
        return true;
    }

    public Object process(FilterCriterionProcessor processor) {
        return processor.process(this);
    }
}
