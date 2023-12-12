package Java.DataBase.Action;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TableExample {
	public static void main(String[] args) {
		String[] columnNames = { "","일자", "노선명", "역명" };
		Object[][] data = { { "1", 20300103, "7호선", "장암" }
				// 다른 데이터도 추가 가능
		};

		// DefaultTableModel 생성
		DefaultTableModel model = new DefaultTableModel(data, columnNames);

		// JTable 생성 및 DefaultTableModel 설정
		JTable table = new JTable(model);

		// JFrame에 JTable 추가
		JFrame frame = new JFrame();
		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}