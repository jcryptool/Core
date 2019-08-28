//-----BEGIN DISCLAIMER-----
/*******************************************************************************
* Copyright (c) 2019 JCrypTool Team and Contributors
*
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
//-----END DISCLAIMER-----
package org.jcryptool.actions.ui.views;

import org.eclipse.osgi.util.NLS;

/**
 * Externalized strings for the <code>org.jcryptool.actions.ui.views</code> package.
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class Messages extends NLS {
    private static final String BUNDLE_NAME = "org.jcryptool.actions.ui.views.messages"; //$NON-NLS-1$
    public static String ActionView_0;
    public static String ActionView_1;
    public static String ActionView_2;
    public static String ActionView_3;
	public static String ActionView_5;
	public static String ActionView_6;
    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }
}
