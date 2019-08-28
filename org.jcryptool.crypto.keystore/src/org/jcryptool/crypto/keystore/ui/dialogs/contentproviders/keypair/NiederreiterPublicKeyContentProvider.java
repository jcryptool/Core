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
import org.jcryptool.crypto.keystore.ui.dialogs.contentproviders.CertificateContentProvider;
import org.jcryptool.crypto.keystore.ui.dialogs.contentproviders.Messages;

import de.flexiprovider.api.exceptions.InvalidKeySpecException;
import de.flexiprovider.api.keys.Key;
import de.flexiprovider.pqc.ecc.niederreiter.NiederreiterKeyFactory;
import de.flexiprovider.pqc.ecc.niederreiter.NiederreiterPublicKey;
import de.flexiprovider.pqc.ecc.niederreiter.NiederreiterPublicKeySpec;

/**
 * @author Anatoli Barski
 * 
 */
public class NiederreiterPublicKeyContentProvider extends CertificateContentProvider {

    @Override
    protected List<TableEntry> getAlgorithmElements(Object inputElement) {

        List<TableEntry> paramElements = new ArrayList<TableEntry>();

        try {
            NiederreiterPublicKey key = (NiederreiterPublicKey) inputElement;
            if (key == null)
                return null;

            paramElements.add(new TableEntry(Messages.ContentProvider_k, "" + key.getK())); //$NON-NLS-2$
            paramElements.add(new TableEntry(Messages.ContentProvider_n, "" + key.getN())); //$NON-NLS-2$
            paramElements.add(new TableEntry(Messages.ContentProvider_t, "" + key.getT())); //$NON-NLS-2$
            paramElements.add(new TableEntry(Messages.ContentProvider_h, "" + key.getH())); //$NON-NLS-2$

        } catch (ClassCastException e) {
            return null;
        }
        return paramElements;
    }

    @Override
    protected List<TableEntry> getKeySpecElements(Key key) {

        List<TableEntry> paramElements = new ArrayList<TableEntry>();

        try {
            NiederreiterKeyFactory keyFactory = new NiederreiterKeyFactory();
            NiederreiterPublicKeySpec keySpec = (NiederreiterPublicKeySpec) keyFactory.getKeySpec(key,
                    NiederreiterPublicKeySpec.class);
            if (keySpec == null)
                return null;

        } catch (ClassCastException e) {
            return null;
        } catch (InvalidKeySpecException e) {
            return null;
        }
        return paramElements;
    }
}
