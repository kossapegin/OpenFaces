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
package org.openfaces.component.table;

import javax.faces.context.FacesContext;

public class Sorting extends AbstractTableConfigurator {
    public static final String COMPONENT_TYPE = "org.openfaces.Sorting";
    public static final String COMPONENT_FAMILY = "org.openfaces.Sorting";

    private boolean explicitMode;

    public Sorting() {
        this(true);
    }
    public Sorting(boolean explicitMode) {
        this.explicitMode = explicitMode;
        setRendererType("org.openfaces.SortingRenderer");
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    @Override
    public Object saveState(FacesContext context) {
        return new Object[]{
                super.saveState(context),

        };
    }

    @Override
    public void restoreState(FacesContext context, Object stateObj) {
        Object[] state = (Object[]) stateObj;
        int i = 0;
        super.restoreState(context, state[i++]);

    }

    public boolean isExplicitMode() {
        return explicitMode;
    }
}
