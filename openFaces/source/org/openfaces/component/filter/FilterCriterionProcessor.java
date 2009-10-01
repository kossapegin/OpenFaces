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
package org.openfaces.component.filter;

import org.openfaces.component.filter.criterion.PropertyFilterCriterion;
import org.openfaces.component.filter.criterion.AndFilterCriterion;
import org.openfaces.component.filter.criterion.OrFilterCriterion;

/**
 * @author Dmitry Pikhulya
 */
public abstract class FilterCriterionProcessor {
    public abstract Object process(PropertyFilterCriterion criterion);
    public abstract Object process(AndFilterCriterion criterion);
    public abstract Object process(OrFilterCriterion criterion);
}
