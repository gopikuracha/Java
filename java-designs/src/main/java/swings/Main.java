package swings;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;

public class Main {

	public static void main(String[] args) {
		setFonts();
		JTable table = new JTable(new AbstractTableModel() {
			
			public Object getValueAt(int rowIndex, int columnIndex) {
				return null;
			}
			
			public int getRowCount() {
				return 2;
			}
			
			public int getColumnCount() {
				return 3;
			}
		});
		table.setRowHeight(36);
		JLabel label = new JLabel("Hi Gopi");
		JScrollPane pane = new JScrollPane(table);
		pane.setPreferredSize(new Dimension(300, 100));
		
		JPanel panel = new JPanel();	
		panel.add(label);
		panel.add(pane);
		
		JFrame frame = new JFrame("Hi Kriss");
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	private static void setFonts() {
		Font font = new Font("Dialog", Font.PLAIN, 18);
		UIManager.put("Table.font", font);
		UIManager.put("TableHeader.font", font);
	}
}
