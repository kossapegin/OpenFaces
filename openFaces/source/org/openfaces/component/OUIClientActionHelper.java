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
package org.openfaces.component;

import org.openfaces.util.AjaxUtil;
import org.openfaces.util.ComponentUtil;
import org.openfaces.util.StyleUtil;
import org.openfaces.util.SelfScheduledAction;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.context.FacesContext;

/**
 * @author Dmitry Pikhulya
 */
public abstract class OUIClientActionHelper {
    private static int componentIdCounter = 0;

    public static void ensureComponentIdSpecified(UIComponent component) {
        if (component == null)
            return;
        if (!ComponentUtil.isComponentIdSpecified(component))
            component.setId(OUIClientActionHelper.generateComponentId());
    }

    private static String generateComponentId() {
        return "_dynamicId_" + componentIdCounter++;
    }

    public static String getClientActionInvoker(FacesContext context, OUIClientAction clientAction) {
        return getClientActionInvoker(context, clientAction, false);
    }

    public static String getClientActionInvoker(
            FacesContext context,
            OUIClientAction clientAction,
            boolean considerOnlyCommandLinkAndButton) {
        String aFor = clientAction.getFor();
        UIComponent actionComponent = (UIComponent) clientAction;
        if (aFor != null) {
            return ComponentUtil.referenceIdToClientId(context, actionComponent, aFor);
        } else if (!clientAction.isStandalone()) {
            UIComponent associatedComponent = actionComponent.getParent();
            boolean allowedComponent = !considerOnlyCommandLinkAndButton ||
                    (associatedComponent instanceof HtmlCommandButton || associatedComponent instanceof HtmlCommandLink);
            String result = allowedComponent ? associatedComponent.getClientId(context) : null;
            if (associatedComponent != null) {
                // reset client-id cache for client-id to be calculated as usual after setParent is called
                associatedComponent.setId(associatedComponent.getId());
            }
            return result;
        } else
            return null;
    }


    public void onParentChange(final OUIClientAction action, final UIComponent parent) {
        ensureComponentIdSpecified(parent);

        FacesContext context = FacesContext.getCurrentInstance();
        if (action.isStandalone() || action.getFor() != null ||
                parent instanceof HtmlCommandButton || parent instanceof HtmlCommandLink) {
            if (context.getResponseWriter() != null) {
                AjaxUtil.addJSLinks(context, parent);
                StyleUtil.requestDefaultCss(context);
            }
            return;
        }

        ComponentUtil.runWhenReady(new SelfScheduledAction() {
            public boolean executeIfReady() {
                FacesContext context = FacesContext.getCurrentInstance();
                String script = getClientActionScript(context, action);
                if (script == null)
                    return false;
                if (context.getResponseWriter() != null) {
                    AjaxUtil.addJSLinks(context, parent);
                    StyleUtil.requestDefaultCss(context);
                }

                String event = action.getEvent();
                parent.getAttributes().put(event, script);
                return true;
            }
        });
    }

    protected abstract String getClientActionScript(FacesContext context, OUIClientAction action);

}
