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

package org.openfaces.event;

import org.openfaces.component.input.fileattachments.FileAttachment;

import javax.faces.component.UIComponent;
import javax.faces.event.FacesEvent;
import javax.faces.event.FacesListener;


public class FileRemovedEvent extends FacesEvent {
    private final FileAttachment fileAttachment;

    public FileRemovedEvent(UIComponent component, FileAttachment fileAttachment) {
        super(component);
        if (component == null) throw new IllegalArgumentException("Component " + component + " is null");
        this.fileAttachment = fileAttachment;

    }


    public FileAttachment getFileAttachment() {
        return fileAttachment;
    }

    @Override
    public boolean isAppropriateListener(FacesListener listener) {
        return listener instanceof FileRemovedListener;
    }

    @Override
    public void processListener(FacesListener listener) {
        ((FileRemovedListener) listener).process(this);
    }

}