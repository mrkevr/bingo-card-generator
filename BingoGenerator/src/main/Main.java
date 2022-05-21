package main;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Main {

	public static void main(String[] args) {

		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		new BingoGenerator(dim.width/2-325/2, dim.height/2-385/2);

	}

}
