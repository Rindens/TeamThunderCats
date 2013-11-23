package ee.ut.math.tvt.teamthundercats.salessystem.ui.tabs;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.teamthundercats.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.teamthundercats.salessystem.ui.model.SalesSystemModel;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "History" in the menu).
 */
public class HistoryTab {
	private static final Logger log = Logger.getLogger(SalesSystemModel.class);
	
	private SalesSystemModel model;

    public HistoryTab(SalesSystemModel model) {
    	this.model =  model;
    } 
    
    public Component draw() {
    	JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gc = new GridBagConstraints();
        panel.setLayout(gb);

        gc.fill = GridBagConstraints.BOTH;
        gc.anchor = GridBagConstraints.NORTH;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.weightx = 1.0;
        gc.weighty = 0.4;
        panel.add(drawHistorykMainPane(), gc);
        
        gc.weighty = 0.5;
        gc.fill = GridBagConstraints.BOTH;
        panel.add(drawProductsPane(), gc);
        return panel;
        
    }

	private Component drawProductsPane() {
		JPanel panel = new JPanel();

	    JTable table = new JTable(model.getPurchaseHistoryTableModel());
	    
	    JTableHeader header = table.getTableHeader();
	    header.setReorderingAllowed(false);

	    JScrollPane scrollPane = new JScrollPane(table);

	    GridBagConstraints gc = new GridBagConstraints();
	    GridBagLayout gb = new GridBagLayout();
	    gc.fill = GridBagConstraints.BOTH;
	    gc.weightx = 1.0;
	    gc.weighty = 1.0;

	    panel.setLayout(gb);
	    panel.add(scrollPane, gc);

	    panel.setBorder(BorderFactory.createTitledBorder("Bought products"));
	    return panel;
	}

	private Component drawHistorykMainPane() {
		JPanel panel = new JPanel();

		final JTable table = new JTable(model.getHistoryTableModel());

		JTableHeader header = table.getTableHeader();
		header.setReorderingAllowed(false);

		JScrollPane scrollPane = new JScrollPane(table);

		GridBagConstraints gc = new GridBagConstraints();
		GridBagLayout gb = new GridBagLayout();
		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 1.0;
		gc.weighty = 1.0;

		panel.setLayout(gb);
		panel.add(scrollPane, gc);

		panel.setBorder(BorderFactory.createTitledBorder("Purchase history"));
		table.setCellSelectionEnabled(true);

		ListSelectionModel cellSelectionModel = table.getSelectionModel();
		cellSelectionModel
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		rowClickListener(table, cellSelectionModel);
		return panel;
	}

	private void rowClickListener(final JTable table, ListSelectionModel cellSelectionModel) {
		cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
		  public void valueChanged(ListSelectionEvent e) {
		    int[] selectedRow = table.getSelectedRows();
		    if(selectedRow.length>=1){
		        List<SoldItem> list = model.getHistoryTableModel().getGoodsForSelection(selectedRow);
		        model.getPurchaseHistoryTableModel().populateWithData(list);
		        model.getPurchaseHistoryTableModel().fireTableDataChanged();
			    log.info("selected rows"+Arrays.toString(selectedRow));
		    }
		  }

		});
	}
}
