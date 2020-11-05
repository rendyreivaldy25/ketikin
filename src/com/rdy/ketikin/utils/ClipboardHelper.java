package com.rdy.ketikin.utils;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class ClipboardHelper {

	public void pasteThings() {
		Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable t = c.getContents(this);
		if (t == null)
			return;
		try {
			String copiedText = (String) t.getTransferData(DataFlavor.stringFlavor);
			System.out.println(copiedText);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void copyThings(String textToCpy) {
		StringSelection stringSelection = new StringSelection(textToCpy);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}

}
