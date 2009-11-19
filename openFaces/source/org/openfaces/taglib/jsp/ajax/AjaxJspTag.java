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
package org.openfaces.taglib.jsp.ajax;

import org.openfaces.taglib.internal.ajax.AjaxTag;
import org.openfaces.taglib.jsp.AbstractComponentJspTag;

import javax.el.MethodExpression;
import javax.el.ValueExpression;

/**
 * @author Ilya Musihin
 */
public class AjaxJspTag extends AbstractComponentJspTag {
    public AjaxJspTag() {
        super(new AjaxTag());
    }

    public void setEvent(ValueExpression event) {
        getDelegate().setPropertyValue("event", event);
    }

    public void setFor(ValueExpression aFor) {
        getDelegate().setPropertyValue("for", aFor);
    }

    public void setStandalone(ValueExpression standalone) {
        getDelegate().setPropertyValue("standalone", standalone);
    }

    public void setRender(ValueExpression render) {
        getDelegate().setPropertyValue("render", render);
    }

    public void setExecute(ValueExpression execute) {
        getDelegate().setPropertyValue("execute", execute);
    }

    public void setSubmitInvoker(ValueExpression submitInvoker) {
        getDelegate().setPropertyValue("submitInvoker", submitInvoker);
    }


    public void setDelay(ValueExpression delay) {
        getDelegate().setPropertyValue("delay", delay);
    }

    public void setDisableDefault(ValueExpression disableDefault) {
        getDelegate().setPropertyValue("disableDefault", disableDefault);
    }

    public void setListener(MethodExpression listener) {
        getDelegate().setPropertyValue("listener", listener);
    }

    public void setImmediate(ValueExpression immediate) {
        getDelegate().setPropertyValue("immediate", immediate);
    }


    public void setOnerror(ValueExpression onError) {
        getDelegate().setPropertyValue("onerror", onError);
    }

    public void setOnajaxstart(ValueExpression onAjaxStart) {
        getDelegate().setPropertyValue("onajaxstart", onAjaxStart);
    }

    public void setOnajaxend(ValueExpression onAjaxEnd) {
        getDelegate().setPropertyValue("onajaxend", onAjaxEnd);
    }
}