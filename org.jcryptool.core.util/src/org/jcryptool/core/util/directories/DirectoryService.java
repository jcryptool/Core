// -----BEGIN DISCLAIMER-----
/*******************************************************************************
 * Copyright (c) 2019 JCrypTool Team and Contributors
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
// -----END DISCLAIMER-----
package org.jcryptool.core.util.directories;

import org.eclipse.core.resources.ResourcesPlugin;

/**
 * Utility class for all directory services used in JCrypTool. Plug-ins should use this class for all directory purposes
 * rather than determining a path themselves.
 * 
 * @author Dominik Schadow
 * @version 0.9.5
 */
public class DirectoryService {
    private static final String TEMP_DIR = System.getProperty("java.io.tmpdir"); //$NON-NLS-1$
    private static String userHome = System.getProperty("user.home"); //$NON-NLS-1$
    private static final String WORKSPACE_DIR = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString();

    /**
     * Returns the system temp directory.
     * 
     * @return The complete path to the temp directory
     */
    public static String getTempDir() {
        return TEMP_DIR;
    }

    /**
     * Returns the users home directory.
     * 
     * @return The complete path to the user home directory
     */
    public static String getUserHomeDir() {
        return userHome;
    }

    public static void setUserHomeDir(String userHome) {
        DirectoryService.userHome = userHome;
    }

    /**
     * Returns the configured workspace directory (normally <i>user.home/Documents/.jcryptool</i>).
     * 
     * @return The complete path to the configured workspace
     */
    public static String getWorkspaceDir() {
        return WORKSPACE_DIR;
    }
}
