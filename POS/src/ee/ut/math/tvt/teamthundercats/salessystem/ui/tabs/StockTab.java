package ee.ut.math.tvt.teamthundercats.salessystem.ui.tabs;

import ee.ut.math.tvt.teamthundercats.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.teamthundercats.salessystem.ui.model.PurchaseInfoTableModel;
import ee.ut.math.tvt.teamthundercats.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.teamthundercats.salessystem.ui.model.StockTableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;

import org.apache.log4j.Logger;


public class StockTab {

	private JButton addItem;

	private static final Logger log = Logger.getLogger(StockTab.class);

	private SalesSystemModel model;
	
	private StockTableModel currentStock;
	
	
	private JPanel tablePanel;
	
	private JTable tableContents;

	public StockTab(SalesSystemModel model) {
		this.model = model;
	}

	// warehouse stock tab - consists of a menu and a table
	public Component draw() {
		JPanel panel = new JPanel();

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gc = new GridBagConstraints();
		panel.setLayout(gb);

		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.NORTH;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.weightx = 1.0d;
		gc.weighty = 0d;

		panel.add(drawStockMenuPane(), gc);

		gc.weighty = 1.0;
		gc.fill = GridBagConstraints.BOTH;
		panel.add(drawStockMainPane(), gc);
		return panel;
	}

	// warehouse menu
	private Component drawStockMenuPane() {
		JPanel panel = new JPanel();

		GridBagConstraints gc = new GridBagConstraints();
		GridBagLayout gb = new GridBagLayout();

		panel.setLayout(gb);

		gc.anchor = GridBagConstraints.NORTHWEST;
		gc.weightx = 0;

		addItem = new JButton("Add item to stock");
		gc.gridwidth = GridBagConstraints.RELATIVE;
		gc.weightx = 1.0;

		addItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFrame addItemFrame = new JFrame("Add Items");
				//addItemFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				addItemFrame.setLayout(new GridLayout(2,1));

				Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
				addItemFrame.setLocation((screen.width - 300) / 2, (screen.height - 300) / 2);

				JPanel panel = new JPanel(new GridLayout(5,2));

				final JTextField itemIdField = new JTextField();
				final JTextField itemNameField = new JTextField();
				final JTextField itemTypeField = new JTextField();
				final JTextField itemPriceField = new JTextField();    		   
				final JTextField itemQuantityField = new JTextField();

				panel.add(new JLabel("Id:"));
				panel.add(itemIdField);

				panel.add(new JLabel("Name:"));  
				panel.add(itemNameField);

				panel.add(new JLabel("Type:"));  
				panel.add(itemTypeField);

				panel.add(new JLabel("Price:"));
				panel.add(itemPriceField);

				panel.add(new JLabel("Quantity:"));
				panel.add(itemQuantityField);

				JButton saveButton =  new JButton("Save");

				saveButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent i) {

						StockItem currentItem = new StockItem(Long.valueOf(itemIdField.getText()), itemNameField.getText(), itemTypeField.getText(), Double.parseDouble(itemPriceField.getText()), Integer.parseInt(itemQuantityField.getText()));
						model.getWarehouseTableModel().addItem(currentItem);
						log.debug("Added " +itemNameField.getText() + " to warehouse");

						model.getWarehouseTableModel().fireTableDataChanged();
						draw();
						addItemFrame.dispose();
					}

				});

				addItemFrame.add(panel);
				addItemFrame.add(saveButton);

				//4. Size the frame.
				addItemFrame.pack();

				//5. Show it.
				addItemFrame.setVisible(true);
			}
		});

		panel.add(addItem, gc);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		return panel;
	} 



	// table of the wareshouse stock
	private Component drawStockMainPane() {
		tablePanel = new JPanel();

		tableContents = new JTable(model.getWarehouseTableModel());

		JTableHeader header = tableContents.getTableHeader();
		header.setReorderingAllowed(false);

		JScrollPane scrollPane = new JScrollPane(tableContents);

		GridBagConstraints gc = new GridBagConstraints();
		GridBagLayout gb = new GridBagLayout();
		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 1.0;
		gc.weighty = 1.0;

		tablePanel.setLayout(gb);
		tablePanel.add(scrollPane, gc);

		tablePanel.setBorder(BorderFactory.createTitledBorder("Warehouse status"));
		return tablePanel;
	}

}
