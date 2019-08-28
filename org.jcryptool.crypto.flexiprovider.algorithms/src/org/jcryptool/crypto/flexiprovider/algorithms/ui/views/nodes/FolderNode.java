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
package org.jcryptool.crypto.flexiprovider.algorithms.ui.views.nodes;

import org.eclipse.jface.resource.ImageDescriptor;
import org.jcryptool.crypto.flexiprovider.algorithms.FlexiProviderAlgorithmsPlugin;
import org.jcryptool.crypto.flexiprovider.ui.nodes.TreeNode;

public class FolderNode extends TreeNode {

	public FolderNode(String name) {
		super(name);
	}
	
	public ImageDescriptor getImageDescriptor() {
		return FlexiProviderAlgorithmsPlugin.getImageDescriptor("icons/16x16/folder.png"); //$NON-NLS-1$
	}
	
}
