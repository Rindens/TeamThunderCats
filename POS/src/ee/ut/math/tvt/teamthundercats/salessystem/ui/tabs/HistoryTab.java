package ee.ut.math.tvt.teamthundercats.salessystem.ui.tabs;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ee.ut.math.tvt.teamthundercats.salessystem.ui.model.SalesSystemModel;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "History" in the menu).
 */
public class HistoryTab {
	
	private SalesSystemModel model;
    public int currentOrderIndex = 0;
    public List<List<String>> confirmedSales = new ArrayList<List<String>>();
    // TODO - implement!

    public HistoryTab() {} 
    
    public Component draw() {
        JPanel panel = new JPanel();
        String[] columns ={"Order Id", "Total Price","Date","Time"};
        JTable contents = new JTable();
    	JScrollPane scrollPane = new JScrollPane(contents);

        contents.setFillsViewportHeight(true);

        // TODO - Sales history tabel
        return panel;
    }
}