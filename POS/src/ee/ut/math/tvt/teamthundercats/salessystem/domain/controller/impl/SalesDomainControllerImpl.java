package ee.ut.math.tvt.teamthundercats.salessystem.domain.controller.impl;

import java.util.ArrayList;
import java.util.List;

import ee.ut.math.tvt.teamthundercats.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.teamthundercats.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.teamthundercats.salessystem.domain.data.Order;
import ee.ut.math.tvt.teamthundercats.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.teamthundercats.salessystem.domain.data.StockItem;

/**
 * Implementation of the sales domain controller.
 */
public class SalesDomainControllerImpl implements SalesDomainController {
	
	private List<StockItem> dataset = new ArrayList<StockItem>();
	private Order currentPurchase;
	
	public Order getCurrentPurchase() {
		return currentPurchase;
	}

	public void submitCurrentPurchase(List<SoldItem> goods) throws VerificationFailedException {
		// Let's assume we have checked and found out that the buyer is underaged and
		// cannot buy chupa-chups
		// FIXME : need to be changed by proper implementation
		if(Boolean.FALSE){
			throw new VerificationFailedException("Underaged!");
		}
		
		// Save purchase
		if(currentPurchase==null){
			currentPurchase =  new Order(goods);
		}
	}

	public void cancelCurrentPurchase() throws VerificationFailedException {				
		this.currentPurchase =  null;
	}
	

	public void startNewPurchase() throws VerificationFailedException {
		// XXX - Start new purchase
	}

	public List<StockItem> loadWarehouseState() {
		// XXX mock implementation
		if(!dataset.isEmpty()){
			return dataset;
		}

		StockItem chips = new StockItem(1l, "Lays chips", "Potato chips", 11.0, 5);
		StockItem chupaChups = new StockItem(2l, "Chupa-chups", "Sweets", 8.0, 8);
	    StockItem frankfurters = new StockItem(3l, "Frankfurters", "Beer sauseges", 15.0, 12);
	    StockItem beer = new StockItem(4l, "Free Beer", "Student's delight", 0.0, 100);

		dataset.add(chips);
		dataset.add(chupaChups);
		dataset.add(frankfurters);
		dataset.add(beer);
		
		return dataset;
	}

	@Override
	public void addItemToWarehouse(StockItem item) {
		this.dataset.add(item);
		
	}
}
