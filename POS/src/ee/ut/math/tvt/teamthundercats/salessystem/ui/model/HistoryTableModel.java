package ee.ut.math.tvt.teamthundercats.salessystem.ui.model;

import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.teamthundercats.salessystem.domain.data.Order;
import ee.ut.math.tvt.teamthundercats.salessystem.domain.data.SoldItem;

public class HistoryTableModel extends SalesSystemTableModel<Order>{

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(HistoryTableModel.class);
	
	
	public HistoryTableModel() {
		super(new String[] {"Id","Date","Sum"});
	}
	
	@Override
	protected Object getColumnValue(Order item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getId();
		case 1:
			return new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss").format(item.getDate());
		case 2:
			return item.getSum();
		}
		throw new IllegalArgumentException("Column index out of range");
	}

	public void addPurchase(Order purchase) {
		logger.info("Creating new history row for "+purchase.toString());
		rows.add(purchase);
		fireTableDataChanged();
	}

	public  java.util.List<SoldItem> getGoodsForSelection(int[] selectedRow) {
		for(int i : selectedRow){
			if(getItemById(i)!= null){
				return getItemById(i).getGoods();
			}
		}
		return null;
		
	}
}
