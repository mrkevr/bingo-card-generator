package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

//gets frame position after moving
class MoveAdapter extends ComponentAdapter {

	int x;
	int y;

}

public class BingoGenerator extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JMenuBar menubar = new JMenuBar();
	JMenu gameMenu = new JMenu("Game");
	JMenu editMenu = new JMenu("Edit");
	JMenu helpMenu = new JMenu("Help");
	JMenu setMarkerColor = new JMenu("Marker Color");

	JMenuItem resetCard = new JMenuItem("Reset Card");
	JMenuItem addCard = new JMenuItem("Add New Card");
	JMenuItem exit = new JMenuItem("Exit");
	JMenuItem markerRed = new JMenuItem("Red");
	JMenuItem markerGreen = new JMenuItem("Green");
	JMenuItem resetMarker = new JMenuItem("Clear Marker");

	BingoColumn bCol;
	BingoColumn iCol;
	BingoColumn nCol;
	BingoColumn gCol;
	BingoColumn oCol;

	JMenuItem aboutGame = new JMenuItem("About Bingo Generator");
	// dimensions for creating new card
	int newX;
	int newY;

	BingoGenerator(int x, int y) {

		newX = x;
		newY = y;

		this.setLocation(x, y);

		this.setTitle("Bingo Generator");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(325, 385);
		this.setResizable(false);
		this.setLayout(null);

		// random bingo card color
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		Color randomColor = new Color(r, g, b);
		this.getContentPane().setBackground(randomColor);

		// menu
		setMarkerColor.add(markerRed);
		setMarkerColor.add(markerGreen);
		gameMenu.add(addCard);
		gameMenu.add(resetCard);
		gameMenu.add(exit);
		editMenu.add(setMarkerColor);
		editMenu.add(resetMarker);
		helpMenu.add(aboutGame);
		menubar.add(gameMenu);
		menubar.add(editMenu);
		menubar.add(helpMenu);
		this.setJMenuBar(menubar);

		addComponentListener(new MoveAdapter() {

			@Override
			public void componentMoved(ComponentEvent e) {
				newX = e.getComponent().getX();
				newY = e.getComponent().getY();
			}

		});

		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
				// System.exit(ABORT);
			}
		});

		addCard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new BingoGenerator(newX + 20, newY + 20);

			}
		});

		resetCard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
				new BingoGenerator(newX, newY);

			}
		});

		markerGreen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				bCol.setMarkerColor(Color.GREEN);
				iCol.setMarkerColor(Color.GREEN);
				nCol.setMarkerColor(Color.GREEN);
				gCol.setMarkerColor(Color.GREEN);
				oCol.setMarkerColor(Color.GREEN);

			}
		});

		markerRed.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				bCol.setMarkerColor(Color.RED);
				iCol.setMarkerColor(Color.RED);
				nCol.setMarkerColor(Color.RED);
				gCol.setMarkerColor(Color.RED);
				oCol.setMarkerColor(Color.RED);

			}
		});

		resetMarker.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				bCol.removeMarker();
				iCol.removeMarker();
				nCol.removeMarker();
				gCol.removeMarker();
				oCol.removeMarker();

			}
		});

		aboutGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null,
						"Bingo Generator v1.0 \n Created by MRKEVR, 2022 \n markevercantillano@gmail.com");

			}
		});

		// creates bingo column objects
		bCol = new BingoColumn('B', 20, 10, randomColor);
		iCol = new BingoColumn('I', 75, 10, randomColor);
		nCol = new BingoColumn('N', 130, 10, randomColor);
		gCol = new BingoColumn('G', 185, 10, randomColor);
		oCol = new BingoColumn('O', 240, 10, randomColor);
		ArrayList<BingoColumn> columns = new ArrayList<>();

		this.add(bCol);
		this.add(iCol);
		this.add(nCol);
		this.add(gCol);
		this.add(oCol);
		this.setVisible(true);

	}

}
