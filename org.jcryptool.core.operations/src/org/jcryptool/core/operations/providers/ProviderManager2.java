// -----BEGIN DISCLAIMER-----
/*******************************************************************************
 * Copyright (c) 2019 JCrypTool Team and Contributors
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
// -----END DISCLAIMER-----
package org.jcryptool.core.operations.providers;

import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.jcryptool.core.logging.utils.LogUtil;
import org.jcryptool.core.operations.IOperationsConstants;
import org.jcryptool.core.operations.OperationsPlugin;

public class ProviderManager2 {
    private static final String factoryDefaultProvider = "FlexiCore"; //$NON-NLS-1$

    private static ProviderManager2 instance;

    private String defaultProviderName;

    /*  */
    private List<String> availableProviders = new ArrayList<String>();

    private ProviderManager2() {
        loadProviders();
        // establish order
    }

    public synchronized static ProviderManager2 getInstance() {
        if (instance == null)
            instance = new ProviderManager2();
        return instance;
    }

    private void loadProviders() {
        IExtensionPoint extensionPoint = Platform.getExtensionRegistry().getExtensionPoint(OperationsPlugin.PLUGIN_ID,
                IOperationsConstants.PL_PROVIDERS2);

        if (extensionPoint == null) {
            LogUtil.logError(
                    OperationsPlugin.PLUGIN_ID,
                    "extension point " + IOperationsConstants.PL_PROVIDERS2 + " not available", new NullPointerException(), true); //$NON-NLS-1$ //$NON-NLS-2$
            return;
        }

        IExtension[] extensions = extensionPoint.getExtensions();
        for (int i = 0; i < extensions.length; i++) {
            IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
            for (int j = 0; j < configElements.length; j++) {
                try {
                    String[] attribs = configElements[j].getAttributeNames();
                    for (int k = 0; k < attribs.length; k++) {
                        LogUtil.logInfo("attribName: " + attribs[k]); //$NON-NLS-1$
                    }
                    AbstractProviderController controller = (AbstractProviderController) configElements[j]
                            .createExecutableExtension("providerController"); //$NON-NLS-1$
                    addProviders(controller.addProviders());
                } catch (CoreException e) {
                    LogUtil.logError(OperationsPlugin.PLUGIN_ID, "CoreException while accessing a provider controller", //$NON-NLS-1$
                            e, false);
                }
            }
        }
        LogUtil.logInfo("activated providers:"); //$NON-NLS-1$
        Iterator<String> it = availableProviders.iterator();
        while (it.hasNext()) {
            LogUtil.logInfo(it.next());
        }
    }

    private void addProviders(List<String> providers) {
        availableProviders.addAll(providers);
    }

    public Iterator<String> getOrderedProviderNames() {
        return availableProviders.iterator();
    }

    public Provider getProvider(String name) {
        return Security.getProvider(name);
    }

    public Provider getDefaultProvider() {
        return Security.getProvider(defaultProviderName);
    }

    public Provider getFactoryDefaultProvider() {
        return Security.getProvider(factoryDefaultProvider);
    }

}
