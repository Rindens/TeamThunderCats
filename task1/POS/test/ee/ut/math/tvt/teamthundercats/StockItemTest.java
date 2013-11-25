package ee.ut.math.tvt.teamthundercats;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.teamthundercats.salessystem.domain.data.StockItem;

/*
StockItemTest 
◦testClone() 
◦testGetColumn() 
*/

public class StockItemTest {
        StockItem item1;

        @Before
        public void setUp(){
                item1 = new StockItem((long) 1,"Cupcakes","Sweets", 2.2,50);
        }

        
        /*
        public Object clone() {
            StockItem item =
                new StockItem(getId(), getName(), getDescription(), getPrice(), getQuantity());
            return item;
        }
        */

        @Test
        public void testClone(){
        		assertEquals(((StockItem)item1.clone()).getId(), item1.getId());
                assertEquals(((StockItem)item1.clone()).getName(), item1.getName());
                assertEquals(((StockItem)item1.clone()).getDescription(), item1.getDescription());
                assertEquals(((StockItem)item1.clone()).getPrice(), item1.getPrice(), 0.0001);
                assertEquals(((StockItem)item1.clone()).getQuantity(), item1.getQuantity());
        }
        
        
        /*
        public Object getColumn(int columnIndex) {
            switch(columnIndex) {
                case 0: return id;
                case 1: return name;
                case 2: return new Double(price);
                case 3: return new Integer(quantity);
                default: throw new RuntimeException("invalid column!");
            }
        }
        */
        
        @Test
        public void testGetColumn() {
                assertEquals(item1.getId(), item1.getColumn(0));
                assertEquals(item1.getName(), item1.getColumn(1));
                assertEquals(item1.getPrice(), item1.getColumn(2));
                assertEquals(item1.getQuantity(), item1.getColumn(3));
        }
 
        
        // My added test
        @Test(expected = AssertionError.class)
        public void testMixedColumns() {
                assertEquals(item1.getName(), item1.getColumn(0));
                assertEquals(item1.getId(), item1.getColumn(1));
                assertEquals(item1.getQuantity(), item1.getColumn(2));
                assertEquals(item1.getPrice(), item1.getColumn(3));
        }
}

