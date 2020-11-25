// -----BEGIN DISCLAIMER-----
/*******************************************************************************
 * Copyright (c) 2011, 2020 JCrypTool Team and Contributors
 *
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
// -----END DISCLAIMER-----
package org.jcryptool.core;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.jcryptool.core.logging.utils.LogUtil;

/**
 * The ApplicationWorkbenchWindowAdvisor class configures the workbench window.
 *
 * @author Dominik Schadow
 * @version 0.9.5
 */
public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    /**
     * Creates a new workbench window advisor for configuring a workbench window via the given workbench window
     * configurer.
     *
     * @param configurer an object for configuring the workbench window
     */
    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    /**
     * Creates a new action bar advisor to configure the action bars of the window via the given action bar configurer.
     *
     * @param configurer the action bar configurer for the window
     * @return the action bar advisor for the window
     */
    @Override
	public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }

    @Override
    public void preWindowOpen() {
    	
    	IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
    	
    	// This Code tries to get the optimal initial size of the 
    	// JCT based on the screen resolution and the sdisplay zoom
    	int monitorWidth = 0;
    	int zoom = 0;
    	try {
    		// try-catch-block just as a precaution
    		monitorWidth = Display.getCurrent().getPrimaryMonitor().getBounds().width;
    		zoom = Display.getCurrent().getPrimaryMonitor().getZoom();
    	} catch (Exception e) {
    		LogUtil.logError(CorePlugin.PLUGIN_ID, e);
    	}
    	
    	if (monitorWidth < 1200) {
    		// For monitors like 1024 x 768
    		configurer.getWindow().getShell().setMaximized(true);
    	} else if (monitorWidth < 1400) {
    		// For monitors like 1280x720 or 1366x768
    		configurer.setInitialSize(new Point(1100, 600));
    	} else if (monitorWidth < 1800) {
    		// For monitors like 1440x900 or 1600x900
    		if (zoom <= 100) {
    			configurer.setInitialSize(new Point(1050, 600));
    		} else if (zoom <= 125) {
    			configurer.setInitialSize(new Point(1350, 700));
    		} else {
    			configurer.getWindow().getShell().setMaximized(true);
    		}
    	} else if (monitorWidth < 2600) {
    		// For monitors like 1920x1080 or 2560x1440
    		if (zoom <= 100) {
    			configurer.setInitialSize(new Point(1200, 600));
    		} else if (zoom <= 125 ) {
    			configurer.setInitialSize(new Point(1450, 750));
    		} else if (zoom <= 150 ) {
    			configurer.setInitialSize(new Point(1700, 900));
    		} else {
    			configurer.getWindow().getShell().setMaximized(true);
    		}
    	} else {
    		// Monitors like 4k or above
    		if (zoom <= 100) {
    			configurer.setInitialSize(new Point(1500, 800));
    		} else if (zoom <= 125 ) {
    			configurer.setInitialSize(new Point(1750, 1000));
    		} else if (zoom <= 150 ) {
    			configurer.setInitialSize(new Point(2000, 1200));
    		} else {
    			configurer.getWindow().getShell().setMaximized(true);
    		}
    	}
    	

        configurer.setShowCoolBar(true);
        configurer.setShowMenuBar(true);
        configurer.setShowPerspectiveBar(true);
        configurer.setShowProgressIndicator(true);
        configurer.setShowStatusLine(true);

    	configurer.addEditorAreaTransfer(FileTransfer.getInstance());
    	configurer.configureEditorAreaDropListener(new EditorAreaDropTargetListener());
    	
    	//Add to the title you enter in the jcryptool.product file the Version of the JCT (that is also
    	//entered in the jcryptool.product file) and the maven build timestamp.
    	configurer.setTitle(Platform.getProduct().getName()
    			+ " "
    			+ Platform.getProduct().getDefiningBundle().getVersion()
    			+ " "
    			+ Platform.getProduct().getProperty("mavenBuildTimestamp"));
        
        PlatformUI.getPreferenceStore().setValue(IWorkbenchPreferenceConstants.PERSPECTIVE_BAR_EXTRAS, Perspective.PERSPECTIVE_ID
                + ", org.jcryptool.crypto.flexiprovider.ui.perspective.FlexiProviderPerspective"); //$NON-NLS-1$
    }

    @Override
    public void postWindowOpen() {
        TrayDialog.setDialogHelpAvailable(true);

        super.postWindowOpen();
    }
}
