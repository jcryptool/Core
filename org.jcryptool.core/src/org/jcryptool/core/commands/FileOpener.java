// -----BEGIN DISCLAIMER-----
/*******************************************************************************
 * Copyright (c) 2010, 2019 JCrypTool Team and Contributors
 *
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
// -----END DISCLAIMER-----
package org.jcryptool.core.commands;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.jcryptool.core.logging.utils.LogUtil;
import org.jcryptool.core.operations.util.PathEditorInput;

/**
 * @author Holger Friedrich (code extracted from a class with no recorded author)
 *
 */
public class FileOpener {

    private static final String TEXT_EDITOR = "org.jcryptool.editor.text.editor.JCTTextEditor"; //$NON-NLS-1$
    private static final String HEX_EDITOR = "net.sourceforge.ehep.editors.HexEditor"; //$NON-NLS-1$

    private static String getEditorId(final String osString) {
        final IEditorDescriptor descriptor = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(osString);

        if (descriptor != null) {
            return descriptor.getId();
        } else {
            // no file association; opening the file with the hex editor
            return HEX_EDITOR;
        }
    }
    
    public static void open(String filename) {
        final IPath path = new Path(filename);
        final String editorId = getEditorId(path.toOSString());

        if (editorId != null) {
            try {
                if (editorId.equals(TEXT_EDITOR)) {
                    PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new PathEditorInput(path.toOSString()), editorId, true,
                            IWorkbenchPage.MATCH_NONE);
                } else if (editorId.equals(HEX_EDITOR)) {
                	PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new PathEditorInput(path.toOSString()), editorId, true,
                            IWorkbenchPage.MATCH_NONE);
                }
            } catch (final PartInitException ex) {
                MessageDialog.openError(PlatformUI.getWorkbench().getDisplay().getActiveShell(), Messages.OpenFileAction_title_could_not_open,
                        NLS.bind(Messages.OpenFileAction_message_could_not_open, editorId));
                LogUtil.logError(ex);
            }
        } else { // no editor is associated
            MessageDialog.openInformation(PlatformUI.getWorkbench().getDisplay().getActiveShell(),
                    Messages.OpenFileAction_title_could_not_open,
                    NLS.bind(Messages.OpenFileAction_message_assign_editor, path.getFileExtension()));
        }
    }

}
