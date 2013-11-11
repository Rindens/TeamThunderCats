package ee.ut.math.tvt.teamthundercats.salessystem.domain.controller;

import java.util.List;

import ee.ut.math.tvt.teamthundercats.salessystem.domain.data.Order;
import ee.ut.math.tvt.teamthundercats.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.teamthundercats.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.teamthundercats.salessystem.domain.exception.VerificationFailedException;


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
	public List<StockItem> loadWarehouseState();

	// business processes
	/**
	 * Initiate new business transaction - purchase of the goods.
	 * 
	 * @throws VerificationFailedException
	 */
	public void startNewPurchase() throws VerificationFailedException;

	/**
	 * Rollback business transaction - purchase of goods.
	 * 
	 * @throws VerificationFailedException
	 */
	public void cancelCurrentOrder() throws VerificationFailedException;

	/**
	 * Commit business transaction - purchsae of goods.
	 * 
	 * @param goods
	 *            Goods that the buyer has chosen to buy.
	 * @return 
	 * @throws VerificationFailedException
	 */
	public void submitCurrentOrder(List<SoldItem> goods)
			throws VerificationFailedException;

	/**
	 * Method for adding items to the stock.
	 * 
	 */
	public void endSession();

	public void addItemToWarehouse(StockItem item);

	public Order getCurrentOrder();

	public List<Order> loadHistoryState();

	public List<SoldItem> getItemsForOrder(Long selectedRow);

	public void commitCurrentOrder();
}	

