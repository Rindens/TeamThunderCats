package ee.ut.math.tvt.teamthundercats.salessystem.ui.model;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.teamthundercats.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.teamthundercats.salessystem.domain.data.StockItem;

/**
 * Main model. Holds all the other models.
 */
public class SalesSystemModel {
    
    private static final Logger log = Logger.getLogger(SalesSystemModel.class);

    // Warehouse model
    private StockTableModel warehouseTableModel;
    
    // Current shopping cart model
    private PurchaseInfoTableModel currentPurchaseTableModel;
    
    //History table model
    private HistoryTableModel historyTableModel;
    //History purchase items table model
    private PurchaseInfoTableModel purchaseHistoryTableModel;

    public PurchaseInfoTableModel getPurchaseHistoryTableModel() {
		return purchaseHistoryTableModel;
	}

	public void setPurchaseHistoryTableModel(
			PurchaseInfoTableModel purchaseHistoryTableModel) {
		this.purchaseHistoryTableModel = purchaseHistoryTableModel;
	}

	private final SalesDomainController domainController;

    /**
     * Construct application model.
     * @param domainController Sales domain controller.
     */
    public SalesSystemModel(SalesDomainController domainController) {
        this.domainController = domainController;
        
        warehouseTableModel = new StockTableModel();
        currentPurchaseTableModel = new PurchaseInfoTableModel();
        historyTableModel =  new HistoryTableModel();
        purchaseHistoryTableModel = new PurchaseInfoTableModel();

        // populate stock model with data from the warehouse
        warehouseTableModel.populateWithData(domainController.loadWarehouseState());
        historyTableModel.populateWithData(domainController.loadHistoryState());


    }

    public StockTableModel getWarehouseTableModel() {
        return warehouseTableModel;
    }

    public PurchaseInfoTableModel getCurrentPurchaseTableModel() {
        return currentPurchaseTableModel;
    }
    
    public HistoryTableModel getHistoryTableModel(){
    	return historyTableModel;
    }
    
    public void addItemToWarehouse(StockItem item){
    	domainController.addItemToWarehouse(item);
    }
}
