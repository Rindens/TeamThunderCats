package ee.ut.math.tvt.teamthundercats;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import ee.ut.math.tvt.teamthundercats.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.teamthundercats.salessystem.domain.data.StockItem;
 
/*
SoldItemTest
◦testGetSum() 
◦testGetSumWithZeroQuantity() 
*/
 
public class SoldItemTest {
        StockItem item1;
        
 
        @Before
        public void setUp() throws Exception {
        	 item1 = new StockItem((long) 1,"Cupcakes","Sweets", 2.2,50);
        }
        
        /*
        public double getSum() {
            return price * ((double) quantity);
        }
        */
        
        @Test
        public void testGetSum() {
                SoldItem item = new SoldItem(item1, 10);
                assertEquals(2.2*10, item.getSum(), 0.0001);
        }
 
        
 
        @Test
        public void testGetSumWithZeroQuantity() {
                SoldItem SumWithZeroQuantity = new SoldItem(item1, 0);
                assertEquals(0, SumWithZeroQuantity.getSum(), 0.0001);
        }
 
        
        // My added test
        @Test(expected = AssertionError.class)
        public void testGetSumWithNegativeQuantity() {
                SoldItem SumWithNegativeQuantity = new SoldItem(item1, -1);
                assertEquals(0, SumWithNegativeQuantity.getSum(), 0.0001); 
        }

        
 
} 

