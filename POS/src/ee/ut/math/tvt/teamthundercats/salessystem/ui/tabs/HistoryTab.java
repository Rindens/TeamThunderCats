package ee.ut.math.tvt.teamthundercats.salessystem.ui.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.TableModel;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.teamthundercats.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.teamthundercats.salessystem.domain.data.Order;
import ee.ut.math.tvt.teamthundercats.salessystem.domain.data.SoldItem;

/**
 * Main model. Holds all the other models.
 */
public class SalesSystemModel {
	public int orderId;
	
    public static List<List<String>> confirmedOrders = new ArrayList<List<String>>();

    
    private static final Logger log = Logger.getLogger(SalesSystemModel.class);

    // Warehouse model
    private StockTableModel warehouseTableModel;
    
    // Current shopping cart model
    private PurchaseInfoTableModel currentPurchaseTableModel;

    public OrderTableModel orderTableModel;

    private final SalesDomainController domainController;
    
    

    /**
     * Construct application model.
     * @param domainController Sales domain controller.
     */
    public SalesSystemModel(SalesDomainController domainController) {
        this.domainController = domainController;
        
        warehouseTableModel = new StockTableModel();
        currentPurchaseTableModel = new PurchaseInfoTableModel();
        orderTableModel = new OrderTableModel();

        // populate stock model with data from the warehouse
        warehouseTableModel.populateWithData(domainController.loadWarehouseState());
        

    }

    public StockTableModel getWarehouseTableModel() {
        return warehouseTableModel;
    }

    public PurchaseInfoTableModel getCurrentPurchaseTableModel() {
        return currentPurchaseTableModel;
    }
    
    public OrderTableModel getOrderTableModel() {
        return orderTableModel;
    }

    
}
