// -----BEGIN DISCLAIMER-----
/*******************************************************************************
 * Copyright (c) 2008, 2019 JCrypTool Team and Contributors
 *
 * All rights reserved. This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
// -----END DISCLAIMER-----
package org.jcryptool.crypto.flexiprovider.operations.ui.actions.menu;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.jcryptool.core.logging.utils.LogUtil;
import org.jcryptool.core.util.constants.IConstants;
import org.jcryptool.core.util.directories.DirectoryService;
import org.jcryptool.crypto.flexiprovider.descriptors.IFlexiProviderOperation;
import org.jcryptool.crypto.flexiprovider.operations.OperationsManager;
import org.jcryptool.crypto.flexiprovider.operations.ui.listeners.ISelectedOperationListener;

public class ExportOperationHandler extends AbstractHandler {
    private ISelectedOperationListener listener;

    public ExportOperationHandler(ISelectedOperationListener listener) {
        this.listener = listener;
    }

    public Object execute(ExecutionEvent event) {
        IFlexiProviderOperation descriptor = listener.getFlexiProviderOperation();

        if (descriptor != null) {
            LogUtil.logInfo("exporting... (" + descriptor.getTimestamp() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
            FileDialog dialog = new FileDialog(Display.getCurrent().getActiveShell(), SWT.SAVE);
            dialog.setFilterPath(DirectoryService.getUserHomeDir());
            dialog.setFilterExtensions(new String[] { IConstants.ALL_FILTER_EXTENSION });
            dialog.setFilterNames(new String[] { IConstants.ALL_FILTER_NAME });
            dialog.setOverwrite(true);

            String filename = dialog.open();
            if (filename != null) {
                OperationsManager.getInstance().export(descriptor.getTimestamp(), filename);
            }
        }
        return (null);
    }
}
