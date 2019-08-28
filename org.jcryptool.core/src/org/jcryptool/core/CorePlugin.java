// -----BEGIN DISCLAIMER-----
/*******************************************************************************
 * Copyright (c) 2019 JCrypTool Team and Contributors
 *
 * All rights reserved. This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
// -----END DISCLAIMER-----
package org.jcryptool.core;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The Activator class controls the plug-in life cycle.
 *
 * @author amro
 * @version 0.2.1
 */
public class CorePlugin extends AbstractUIPlugin {
    /** The plug-in ID. */
    public static final String PLUGIN_ID = "org.jcryptool.core"; //$NON-NLS-1$
    /** The shared instance. */
    private static CorePlugin plugin;

    /**
     * The method uses the superclass method which refreshes the plug-in actions.
     */
    @Override
	public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    /**
     * The method uses the superclass method which saves this plug-in's preference and dialog stores
     * and shuts down its image registry (if they are in use).
     */
    @Override
	public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     *
     * @return the shared instance
     */
    public static CorePlugin getDefault() {
        return plugin;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in relative path.
     *
     * @param path the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(String path) {
        return imageDescriptorFromPlugin(PLUGIN_ID, path);
    }
}
