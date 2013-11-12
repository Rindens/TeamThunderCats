package ee.ut.math.tvt.teamthundercats.salessystem.domain.controller.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ee.ut.math.tvt.teamthundercats.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.teamthundercats.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.teamthundercats.salessystem.domain.data.DisplayableItem;
import ee.ut.math.tvt.teamthundercats.salessystem.domain.data.Order;
import ee.ut.math.tvt.teamthundercats.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.teamthundercats.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.teamthundercats.salessystem.ui.model.StockTableModel;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

/**
 * Implementation of the sales domain controller.
 */
public class SalesDomainControllerImpl implements SalesDomainController {
	
	private static final Logger log = Logger.getLogger(SalesDomainControllerImpl.class);
	
	private List<StockItem> dataset = new ArrayList<StockItem>();
	private List<Order> Orders =  new ArrayList<Order>();
	private Order currentOrder;
	private Session session = HibernateUtil.currentSession();
	
	public Order getCurrentOrder() {
		return currentOrder;
	}

	public void submitCurrentOrder(List<SoldItem> items) throws VerificationFailedException {
		
		if(Boolean.FALSE){
			throw new VerificationFailedException("Underaged!");
		}
		
		// If current Order doesn't exist, we'll create one
		if(currentOrder==null){
			currentOrder = new Order();
			currentOrder.setItems(items);
			currentOrder.setDate(new Date());
		}
	}

	public void cancelCurrentOrder() throws VerificationFailedException {
		this.currentOrder =  null;
	}
	

	public void startNewOrder() throws VerificationFailedException {
		//XXX: we do not need it?
	}

	public List<StockItem> loadWarehouseState() {
		
		if(!dataset.isEmpty()){
			return dataset;
		}
		
		/**
		 * Getting list of stockItems from DB.
		 */
		List<StockItem> stock = session.createCriteria(StockItem.class).list();
		dataset.addAll(stock);
		
		
//		StockItem chips = new StockItem(1l, "Lays chips", "Potato chips", 11.0, 5);
//		StockItem chupaChups = new StockItem(2l, "Chupa-chups", "Sweets", 8.0, 8);
//	    StockItem frankfurters = new StockItem(3l, "Frankfurters", "Beer sauseges", 15.0, 12);
//	    StockItem beer = new StockItem(4l, "Free Beer", "Student's delight", 0.0, 100);
//
//		dataset.add(chips);
//		dataset.add(chupaChups);
//		dataset.add(frankfurters);
//		dataset.add(beer);
		
		return dataset;
	}
	
	@Override
	public List<Order> loadHistoryState() {
		if(!Orders.isEmpty()){
			return Orders;
		}
		List<Order> listOfOrders =  session.createCriteria(Order.class).list();
		Orders.addAll(listOfOrders);
		return Orders;
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
	
	public void addItemToWarehouse(StockItem item) {
		saveOrUpdate(item);
		this.dataset.add(item);
		
	}
	
	public void endSession() {
	    HibernateUtil.closeSession();
	}

	@Override
	public List<SoldItem> getItemsForOrder(Long id) {
		if(Orders!=null){
			Order Order = (Order) session.get(Order.class, id);
			return Order.getItems();
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

	
}
