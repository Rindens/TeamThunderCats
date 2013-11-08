package ee.ut.math.tvt.teamthundercats.salessystem.ui.tabs;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;


import ee.ut.math.tvt.teamthundercats.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.teamthundercats.salessystem.ui.model.OrderTableModel;
import ee.ut.math.tvt.teamthundercats.salessystem.ui.model.PurchaseInfoTableModel;
import ee.ut.math.tvt.teamthundercats.salessystem.ui.model.SalesSystemModel;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "History" in the menu).
 */
public class HistoryTab {
    private ListSelectionModel listSelectionModel;
    
    private SalesSystemModel model;

	public int currentOrderIndex = 0;
	public static List<PurchaseInfoTableModel> confirmedSales = new ArrayList<PurchaseInfoTableModel>();

	// TODO - implement!

	public HistoryTab() {} 

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

		gc.weighty = 1.0;
		gc.fill = GridBagConstraints.BOTH;
		panel.add(drawHistoryMainPane(), gc);
		return panel;
	}



	private Component drawHistoryMainPane() {

		JPanel tablePanel = new OrderTableModel();
		return tablePanel;
	}
	protected void outputPurchaseWindow(int index){
		
		JFrame purchaseWindow= new JFrame("Review purchase nr. "+index);
		purchaseWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel smallTablePanel = new JPanel();
        
		JTable tableContents=  new JTable(confirmedSales.get(index));

		JTableHeader header = tableContents.getTableHeader();
		header.setReorderingAllowed(false);

		JScrollPane scrollPane = new JScrollPane(tableContents);

		GridBagConstraints gc = new GridBagConstraints();
		GridBagLayout gb = new GridBagLayout();
		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 1.0;
		gc.weighty = 1.0;

		smallTablePanel.setLayout(gb);
		smallTablePanel.add(scrollPane, gc);

		smallTablePanel.setBorder(BorderFactory.createTitledBorder("Sales history: "+index));
		
		purchaseWindow.add(smallTablePanel);

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		purchaseWindow.setLocation((screen.width - 300) / 2, (screen.height - 300) / 2);

		//Display the window.
		purchaseWindow.pack();
		purchaseWindow.setVisible(true);
		purchaseWindow.setAlwaysOnTop(true);
	}
	
	class SharedListSelectionHandler implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) { 
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            outputPurchaseWindow(lsm.getMinSelectionIndex());
        }
    }
}
