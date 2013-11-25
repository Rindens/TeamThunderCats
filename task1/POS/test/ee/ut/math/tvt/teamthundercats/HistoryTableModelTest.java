package ee.ut.math.tvt.teamthundercats;
//My added test class for model class HistoryTableModel

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.teamthundercats.salessystem.domain.data.Order;
import ee.ut.math.tvt.teamthundercats.salessystem.ui.model.HistoryTableModel;



public class HistoryTableModelTest {
    HistoryTableModel model1;
    Order item1;

   
    @Before
    public void setUp() {
            model1 = new HistoryTableModel();          
            item1 = new Order();                      
    }

    /*
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
	*/


    @Test(expected = IllegalArgumentException.class)
    public void testAddWhenColumnIndexOutOfRange() {            
            model1.getColumnValue(item1, 10);
    }

    /*
	public void addPurchase(Order order) {
		logger.info("Creating new history row for "+order.toString());
		rows.add(order);
		order.refreshStock();
		fireTableDataChanged();
	}
    */
    
    @Test(expected = NullPointerException.class)
    public void testAddPurchaseWithEmptyOrder(){
    	model1.addPurchase(item1);

    }     
} 

