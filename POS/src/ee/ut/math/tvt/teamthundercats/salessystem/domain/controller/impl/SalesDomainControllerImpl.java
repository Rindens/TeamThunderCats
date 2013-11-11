package ee.ut.math.tvt.teamthundercats.salessystem.domain.controller.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;

import ee.ut.math.tvt.salessystem.util.HibernateUtil;
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
	private List<Order> orders =  new ArrayList<Order>();
	private Order currentOrder;

	private Session session = HibernateUtil.currentSession();

	public Order getcurrentOrder() {
		return currentOrder;
	}

	public void submitCurrentOrder(List<SoldItem> goods) throws VerificationFailedException {
		// Let's assume we have checked and found out that the buyer is underaged and
		// cannot buy chupa-chups
		// FIXME : need to be changed by proper implementation
		if(Boolean.FALSE){
			throw new VerificationFailedException("Underaged!");
		}

		// Save purchase
		if(currentOrder==null){
			currentOrder =  new Order(goods);
		}
	}

	public void cancelCurrentOrder() throws VerificationFailedException {				
		this.currentOrder =  null;
	}


	public void startNewPurchase() throws VerificationFailedException {
		// XXX - Start new purchase
	}

	public List<StockItem> loadWarehouseState() {
		// XXX mock implementation
		if(!dataset.isEmpty()){
			return dataset;
		}

		List<StockItem> stock = session.createCriteria(StockItem.class).list();
		dataset.addAll(stock);

		/*StockItem chips = new StockItem(1l, "Lays chips", "Potato chips", 11.0, 5);
		StockItem chupaChups = new StockItem(2l, "Chupa-chups", "Sweets", 8.0, 8);
	    StockItem frankfurters = new StockItem(3l, "Frankfurters", "Beer sauseges", 15.0, 12);
	    StockItem beer = new StockItem(4l, "Free Beer", "Student's delight", 0.0, 100);

		dataset.add(chips);
		dataset.add(chupaChups);
		dataset.add(frankfurters);
		dataset.add(beer);*/

		return dataset;
	}

	@Override
	public void addItemToWarehouse(StockItem item) {
		this.dataset.add(item);

	}

	@Override
	public void endSession() {
		HibernateUtil.closeSession();

	}

	@Override
	public Order getCurrentOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> loadHistoryState() {
		if(!orders.isEmpty()){
			return orders;
		}
		List<Order> listOfPurchases =  session.createCriteria(Order.class).list();
		orders.addAll(listOfPurchases);
		return orders;
	}

	@Override
	public List<SoldItem> getItemsForOrder(Long id) {
		if(orders!=null){
			Order order = (Order) session.get(Order.class, id);
			return order.getItems();
		}
		return new ArrayList<SoldItem>();
	}

	@Override
	public void commitCurrentOrder() {
		saveOrUpdate(currentOrder);
		for (SoldItem item : currentOrder.getItems()) {
			item.setCurrentOrder(currentOrder);
			saveOrUpdate(item);
		}		
	}

	private void saveOrUpdate(Object data){
		try{
			session.saveOrUpdate(data);
		} catch(NonUniqueObjectException e){
			if(data instanceof StockItem){
				StockItem stockItem = (StockItem) data;
				StockItem actualStockItem = (StockItem) session.get(StockItem.class, stockItem.getId());
				session.saveOrUpdate(actualStockItem);
			}
			e.printStackTrace();
		}
		session.beginTransaction().commit();
	}
}
