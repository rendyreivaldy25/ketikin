package com.rdy.ketikin;

import com.rdy.ketikin.utils.WindowHelper;

public class MainActivity {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainActivity();
	}

	public MainActivity() {
		System.out.println("Hello World !");

		WindowHelper window = new WindowHelper();
		window.createWindows();
	}

}
