// -----BEGIN DISCLAIMER-----
/*******************************************************************************
 * Copyright (c) 2019 JCrypTool Team and Contributors
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
// -----END DISCLAIMER-----
package org.jcryptool.crypto.keystore.ui.dialogs.contentproviders.keypair;

import java.util.ArrayList;
import java.util.List;

import org.jcryptool.crypto.keystore.ui.dialogs.TableEntry;
import org.jcryptool.crypto.keystore.ui.dialogs.contentproviders.AbstractKeyNodeContentProvider;
import org.jcryptool.crypto.keystore.ui.dialogs.contentproviders.Messages;

import de.flexiprovider.api.exceptions.InvalidKeySpecException;
import de.flexiprovider.api.keys.Key;
import de.flexiprovider.nf.iq.iqgq.IQGQKeyFactory;
import de.flexiprovider.nf.iq.iqgq.IQGQParameterSpec;
import de.flexiprovider.nf.iq.iqgq.IQGQPrivateKey;
import de.flexiprovider.nf.iq.iqgq.IQGQPrivateKeySpec;

/**
 * @author Anatoli Barski
 * 
 */
public class IQGQPrivateKeyContentProvider extends AbstractKeyNodeContentProvider {

    @Override
    protected List<TableEntry> getAlgorithmElements(Object inputElement) {

        List<TableEntry> paramElements = new ArrayList<TableEntry>();

        try {
            IQGQPrivateKey key = (IQGQPrivateKey) inputElement;
            if (key == null)
                return null;

        } catch (ClassCastException e) {
            return null;
        }
        return paramElements;
    }

    @Override
    protected List<TableEntry> getKeySpecElements(Key key) {

        List<TableEntry> paramElements = new ArrayList<TableEntry>();

        try {
            IQGQKeyFactory keyFactory = new IQGQKeyFactory();
            IQGQPrivateKeySpec keySpec = (IQGQPrivateKeySpec) keyFactory.getKeySpec(key, IQGQPrivateKeySpec.class);
            if (keySpec == null)
                return null;

            paramElements.add(new TableEntry(Messages.ContentProvider_exponent, "" + keySpec.getExponent())); //$NON-NLS-2$
            paramElements.add(new TableEntry(Messages.ContentProvider_theta, "" + keySpec.getTheta())); //$NON-NLS-2$

            paramElements.addAll(getParameters(keySpec));
        } catch (ClassCastException e) {
            return null;
        } catch (InvalidKeySpecException e) {
            return null;
        }
        return paramElements;
    }

    private List<TableEntry> getParameters(IQGQPrivateKeySpec keySpec) {
        IQGQParameterSpec params = (IQGQParameterSpec) keySpec.getParams();
        List<TableEntry> paramElements = new ArrayList<TableEntry>();
        paramElements.add(new TableEntry(Messages.ContentProvider_discriminant, "" + params.getDiscriminant())); //$NON-NLS-2$
        return paramElements;
    }
}
