package ee.ut.math.tvt.teamthundercats;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;
import ee.ut.math.tvt.teamthundercats.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.teamthundercats.salessystem.ui.model.StockTableModel;
 

/*
StockTableModelTest
◦testValidateNameUniqueness() 
◦testHasEnoughInStock() 
◦testGetItemByIdWhenItemExists() 
◦testGetItemByIdWhenThrowsException() 
*/


public class StockTableModelTest {
        StockTableModel model1;
        StockItem item1;

 
 
        @Before
        public void setUp() {
                model1 = new StockTableModel();
                item1 = new StockItem((long) 1,"Cupcakes","Sweets", 2.2,50);
        }


        /*
        public void addItem(final StockItem stockItem) {
    		try {
    			StockItem item = getItemById(stockItem.getId());
    			item.setQuantity(item.getQuantity() + stockItem.getQuantity());
    			log.debug("Found existing item " + stockItem.getName()
    					+ " increased quantity by " + stockItem.getQuantity());
    		}
    		catch (NoSuchElementException e) {
    			rows.add(stockItem);
    			log.debug("Added " + stockItem.getName()
    					+ " quantity of " + stockItem.getQuantity());
    		}
    		fireTableDataChanged();
    	}
    	*/
 
 
        @Test
        public void testHasEnoughInStock() {
                model1.addItem(item1);
                assertEquals(50, model1.getColumnValue(item1, 3));
                item1.setQuantity(item1.getQuantity() - 10);
                assertEquals(40, model1.getColumnValue(item1, 3));
        }
 
 
        @Test
        public void testGetItemByIdWhenItemExists() {
                model1.addItem(item1);
                assertEquals(item1, model1.getItemById(1));
        }

        /*
    	@Override
    	public Object getColumnValue(StockItem item, int columnIndex) {
    		switch (columnIndex) {
    		case 0:
    			return item.getId();
    		case 1:
    			return item.getName();
    		case 2:
    			return item.getPrice();
    		case 3:
    			return item.getQuantity();
    		}
    		throw new IllegalArgumentException("Column index out of range");
    	}
    	*/
 
        @Test(expected = NoSuchElementException.class)
        public void testGetItemByIdWhenThrowsException() {
        		model1.addItem(item1);
                model1.getItemById(555);
        }
        
        // My added test
        @Test(expected = IllegalArgumentException.class)
        public void testGetColumnValueWhenThrowsException() {
                model1.addItem(item1);
                model1.getColumnValue(item1, 5);
        } 

} 
