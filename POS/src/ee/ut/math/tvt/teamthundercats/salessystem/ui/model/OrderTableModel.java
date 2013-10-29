package ee.ut.math.tvt.teamthundercats.salessystem.ui.model;

import java.util.NoSuchElementException;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.teamthundercats.salessystem.domain.data.Order;
import ee.ut.math.tvt.teamthundercats.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.teamthundercats.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.teamthundercats.salessystem.ui.SalesSystemUI;
import ee.ut.math.tvt.teamthundercats.salessystem.ui.panels.PurchaseItemPanel;


/**
 * Purchase history details model. 
 */
public class OrderTableModel extends SalesSystemTableModel<Order> {
	private static final long serialVersionUID = 1L;

    private PurchaseItemPanel.ComboListener comboListener;
	
	private static final Logger log = Logger.getLogger(OrderTableModel.class);
	
	
	public OrderTableModel() {
		super(new String[] { "Id", "Date", "Price"});
	}

	@Override
	protected Object getColumnValue(Order item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getOrderId();
		case 1:
			return item.getDateTime();
		case 2:
			return item.getTotalPrice();

		}
		throw new IllegalArgumentException("Column index out of range");
	}

	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < headers.length; i++)
			buffer.append(headers[i] + "\t");
		buffer.append("\n");

		for (final Order item : rows) {
			buffer.append(item.getOrderId() + "\t");
			buffer.append(item.getDateTime() + "\t");
			buffer.append(item.getTotalPrice() + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
	}
	


	
	
    /**
     * Add new StockItem to table.
     */
    public void addItem(final Order item) {
        
        
        rows.add(item);
       
        fireTableDataChanged();
    }

	public void setComboListener(PurchaseItemPanel.ComboListener comboListener) {
		// TODO Auto-generated method stub
		
	}
}
