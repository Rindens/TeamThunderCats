package ee.ut.math.tvt.teamthundercats.salessystem.ui.model;

import java.util.ArrayList;

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
	public ArrayList<ArrayList<SoldItem>> listOfOrderContents = new ArrayList<ArrayList<SoldItem>>();
	public ArrayList<Order> listOfOrderData = new ArrayList<Order>();

    
    private static final Logger log = Logger.getLogger(SalesSystemModel.class);

    // Warehouse model
    private StockTableModel warehouseTableModel;
    
    // Current shopping cart model
    private PurchaseInfoTableModel currentPurchaseTableModel;


    private final SalesDomainController domainController;
    
    

    /**
     * Construct application model.
     * @param domainController Sales domain controller.
     */
    public SalesSystemModel(SalesDomainController domainController) {
        this.domainController = domainController;
        
        warehouseTableModel = new StockTableModel();
        currentPurchaseTableModel = new PurchaseInfoTableModel();

        // populate stock model with data from the warehouse
        warehouseTableModel.populateWithData(domainController.loadWarehouseState());

    }

    public StockTableModel getWarehouseTableModel() {
        return warehouseTableModel;
    }

    public PurchaseInfoTableModel getCurrentPurchaseTableModel() {
        return currentPurchaseTableModel;
    }

    
}
