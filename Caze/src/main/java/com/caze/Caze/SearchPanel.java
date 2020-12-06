package com.caze.Caze;

import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SearchPanel {

	protected static JPanel gridLayout(String inum, String part, String alt,
			String plano, String seq, String qoh) throws NullPointerException
	{
		
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(6, 0, 5, 10));
		
		JLabel labelINum = new JLabel("Item:\t" + inum);
		JLabel labelPart = new JLabel("Part:\t" + part);
		JLabel labelAlt = new JLabel("Alt:\t" + alt);
		JLabel labelPlano = new JLabel("Plano:\t" + plano);
		JLabel labelSeq = new JLabel("Seq:\t" + seq);
		JLabel labelQoh = new JLabel("Qoh:\t" + qoh);
		
		
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		panel.add(labelINum);
		panel.add(labelPart);
		panel.add(labelAlt);
		panel.add(labelPlano);
		panel.add(labelSeq);
		panel.add(labelQoh);
		 
		return panel;

	}
	
}
