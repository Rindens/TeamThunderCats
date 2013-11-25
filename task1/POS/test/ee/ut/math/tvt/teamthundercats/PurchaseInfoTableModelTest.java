package ee.ut.math.tvt.teamthundercats;
// My added test class for model class PurchaseInfoTableModel

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.teamthundercats.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.teamthundercats.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.teamthundercats.salessystem.ui.model.PurchaseInfoTableModel;



public class PurchaseInfoTableModelTest {
       PurchaseInfoTableModel model1;
       SoldItem item1;

      
       @Before
       public void setUp() {
               model1 = new PurchaseInfoTableModel();
               item1 = new SoldItem(new StockItem((long) 1,"Cupcakes","Sweets", 2.2,50), 10);
       }


       /*
 	   	@Override
 	   	protected Object getColumnValue(SoldItem item, int columnIndex) {
 	   		switch (columnIndex) {
 	   		case 0:
 	   			return item.getId();
 	   		case 1:
 	   			return item.getName();
 	   		case 2:
 	   			return item.getPrice();
 	   		case 3:
 	   			return item.getQuantity();
 	   		case 4:
 	   			return item.getSum();
 	   		}
 	   		throw new IllegalArgumentException("Column index out of range");
 	   	}
 	   	*/
       
       @Test
       public void testAddWithZeroQuantity() {
               item1 = new SoldItem(new StockItem((long) 1,"Cupcakes","Sweets", 2.2,50), 0);             
               model1.addItem(item1);             
               assertEquals(0, model1.getValueAt(0, 3));
       }
       
       @Test(expected = IllegalArgumentException.class)
       public void testAddWhenColumnIndexOutOfRange() {
               item1 = new SoldItem(new StockItem((long) 1,"Cupcakes","Sweets", 2.2,50), 0);             
               model1.addItem(item1);             
               model1.getValueAt(0, 10);
       }
       
       @Test
       public void testGetTotalPrice() {
               item1 = new SoldItem(new StockItem((long) 1,"Cupcakes","Sweets", 2.2,50), 5);             
               model1.addItem(item1);             
               assertEquals(11.0, model1.getValueAt(0, 4));
       }


} 
