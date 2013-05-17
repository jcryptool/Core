package org.jcryptool.visual.jctca.listeners;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.jcryptool.core.logging.utils.LogUtil;
import org.jcryptool.crypto.keystore.backend.KeyStoreAlias;
import org.jcryptool.crypto.keystore.backend.KeyStoreManager;
import org.jcryptool.visual.jctca.Util;

public class SigVisPluginOpenListener implements SelectionListener {
	Button btn_check;
	Label lbl_file;
	Text txt_sign;
	Combo cmb_keys;
	
	public SigVisPluginOpenListener(Button btn_check, Label lbl_file, Text txt_sign, Combo cmb_keys) {
		this.btn_check = btn_check;
		this.lbl_file = lbl_file;
		this.txt_sign = txt_sign;
		this.cmb_keys = cmb_keys;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		if (lbl_file.getText() != "") { //$NON-NLS-1$
			System.out.println(lbl_file.getText());
		}else{
			System.out.println(txt_sign.getText());
		}
		String selected = cmb_keys.getText();
		String key_hash = selected.split("Hash: ")[1]; //$NON-NLS-1$
		key_hash = key_hash.split(" ")[0]; //$NON-NLS-1$
		KeyStoreAlias pubAlias = Util.getAliasForHash(key_hash);
		KeyStoreAlias privAlias = KeyStoreManager.getInstance().getPrivateForPublic(pubAlias);
		org.jcryptool.visual.sig.algorithm.Input.privateKey = privAlias;
		org.jcryptool.visual.sig.algorithm.Input.data = (lbl_file.getText()!="" ? lbl_file.getText() : txt_sign.getText()).getBytes();
		org.jcryptool.visual.sig.algorithm.Input.path = lbl_file.getText();
		byte[] hash, sig;
		if (btn_check.getSelection() == true) {
			try {
				PlatformUI.getWorkbench().getActiveWorkbenchWindow()
						.getActivePage()
						.showView("org.jcryptool.visual.sig.view"); //$NON-NLS-1$
			} catch (PartInitException e1) {
				LogUtil.logError(e1);
			}
		} else {
			hash = org.jcryptool.visual.sig.algorithm.Input.data;
			hash = org.jcryptool.visual.sig.algorithm.Hash.hashInput("SHA-256", hash);
			sig = org.jcryptool.visual.sig.algorithm.SigGeneration.SignInput("SHA256withRSA", hash);
		}

	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
