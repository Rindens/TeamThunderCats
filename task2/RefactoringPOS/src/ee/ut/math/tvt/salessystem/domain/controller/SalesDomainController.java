package ee.ut.math.tvt.salessystem.domain.controller;

import ee.ut.math.tvt.salessystem.domain.data.Client;
import ee.ut.math.tvt.salessystem.domain.data.Sale;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

import java.util.List;

/**
 * Sales domain controller is responsible for the domain specific business
 * processes.
 */
public interface SalesDomainController {

    /**
     * Load the current state of the warehouse.
     *
     * @return List of ${link
     *         ee.ut.math.tvt.salessystem.domain.data.StockItem}s.
     */
    public List<StockItem> getAllStockItems();


    public List<Client> getAllClients();

    public List<Sale> getAllSales();

    public Client getClient(long id);

    public void createStockItem(StockItem stockItem);

    

    /**
     * Register new sale.
     *
     * @param sale
     *            Sale that needs to be registered.
     * @throws VerificationFailedException
     */
    public void registerSale(Sale sale)
            throws VerificationFailedException;


    public void setModel(SalesSystemModel model);

    /**
     * Close all resources
     */
    public void endSession();


	public void refresh(String string);
    
}
