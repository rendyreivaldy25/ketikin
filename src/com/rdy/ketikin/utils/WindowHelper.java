package com.rdy.ketikin.utils;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WindowHelper implements NativeMouseInputListener {

	ClipboardHelper clip;
	RobotHelper robs;

	public void createWindows() {
		setUpJNativeHook();
		clip = new ClipboardHelper();
		FileHelper file = new FileHelper();
		JSONArray configObject = file.getConfigObject();
		robs = new RobotHelper();

		JFrame frame = new JFrame(Configs.APPS_NAME);
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setSize(300, 300);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		for (int i = 0; i < configObject.length(); i++) {
			try {
				JSONObject singleConfig = configObject.getJSONObject(i);
				String buttonLabel = singleConfig.getString("buttonLabel");
				String textToCopy = singleConfig.getString("text");
				addButtons(panel, buttonLabel, textToCopy);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		frame.setLayout(null);// using no layout managers
		frame.add(panel);
		frame.pack();
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
		setUpFrameStartUpLocation(frame);
	}

	public void setUpFrameStartUpLocation(Frame frame) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
		Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();

		// frame size
		Dimension size = frame.getBounds().getSize();

		int x = (int) rect.getMaxX() - frame.getWidth();
        int y = (int) rect.getMaxY() - frame.getHeight();
		frame.setLocation(x, y);
	}

	public void setUpJNativeHook() {
		try {
			GlobalScreen.registerNativeHook();

			Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
			logger.setLevel(Level.OFF);
		} catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}
		// Add the appropriate listeners.
		GlobalScreen.addNativeMouseListener(this);
		GlobalScreen.addNativeMouseMotionListener(this);
	}

	public void addButtons(JPanel panel, String buttonLabel, String textToCopy) {
		JToggleButton button = new JToggleButton(buttonLabel);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				robs.buttonClicked(textToCopy, button, true);
			}
		});
		panel.add(button);
	}

	@Override
	public void nativeMouseClicked(NativeMouseEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("Mouse Clicked: " + e.getClickCount());
		robs.isToggleClicked = false;
		robs.typeTextV2();
	}

	@Override
	public void nativeMousePressed(NativeMouseEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("Mouse Pressed: " + e.getButton());
	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("Mouse Released: " + e.getButton());
	}

	@Override
	public void nativeMouseDragged(NativeMouseEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("Mouse Moved: " + e.getX() + ", " + e.getY());
	}

	@Override
	public void nativeMouseMoved(NativeMouseEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("Mouse Dragged: " + e.getX() + ", " + e.getY());
	}

}
