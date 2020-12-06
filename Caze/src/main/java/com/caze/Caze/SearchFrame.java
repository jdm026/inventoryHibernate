package com.caze.Caze;

import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SearchFrame {

	
	protected static JFrame gridLayout(String inum, String part, String alt,
								Plano plano, String seq, String qoh)
	{
		JFrame frame = new JFrame("ItemSearch");
		JPanel panel = new JPanel();
		
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(250, 250);
		
		panel.setLayout(new GridLayout(6, 0, 5, 10));
		
		JLabel labelINum = new JLabel("Item:\t" + inum);
		JLabel labelPart = new JLabel("Part:\t" + part);
		JLabel labelAlt = new JLabel("Alt:\t\t" + alt);
		JLabel labelPlano = new JLabel("Plan:\t" + plano.getPlanoid());
		JLabel labelSeq = new JLabel("Seq:\t\t" + seq);
		JLabel labelQoh = new JLabel("Qoh:\t" + qoh);
		
		
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		panel.add(labelINum);
		panel.add(labelPart);
		panel.add(labelAlt);
		panel.add(labelPlano);
		panel.add(labelSeq);
		panel.add(labelQoh);
		
		frame.add(panel);
		
		
		return frame;
	}
	
	protected static JFrame gridLayout(String inum, String part, String alt,
			String plano, String seq, String qoh)
	{
		JFrame frame = new JFrame("ItemSearch");
		JPanel panel = new JPanel();
		
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(250, 250);
		
		panel.setLayout(new GridLayout(6, 0, 5, 10));
		
		JLabel labelINum = new JLabel("Item:\t" + inum);
		JLabel labelPart = new JLabel("Part:\t" + part);
		JLabel labelAlt = new JLabel("Alt:\t\t" + alt);
		
		JLabel labelPlano = new JLabel("Plan:\tN/A");
		
		
		JLabel labelSeq = new JLabel("Seq:\t\t" + seq);
		JLabel labelQoh = new JLabel("Qoh:\t" + qoh);
		
		
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		panel.add(labelINum);
		panel.add(labelPart);
		panel.add(labelAlt);
		panel.add(labelPlano);
		panel.add(labelSeq);
		panel.add(labelQoh);
		
		frame.add(panel);
		
		
		return frame;
		
	}
			
}



























































































