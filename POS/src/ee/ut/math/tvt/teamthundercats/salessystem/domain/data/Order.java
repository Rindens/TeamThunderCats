package ee.ut.math.tvt.teamthundercats.salessystem.domain.data;

import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Order implements Cloneable, DisplayableItem {
	
	private List<SoldItem> goods;
	private Double sum;
	private Date date;
	private final Long id;
	
	public static Long counter = 0L;

	public Order(List<SoldItem> goods){
		this.goods = goods;
		this.date = new Date();
		this.id=counter++;
		
	}

	public void refreshStock() {
		
		for (SoldItem soldItem : goods) {
			int amount = soldItem.getQuantity();
			int stockAmount = soldItem.getStockItem().getQuantity();
			if(stockAmount-amount>-1){
				soldItem.getStockItem().setQuantity(stockAmount-amount);
			} else {
				//TODO: Throw new exeption?
			}
			
		}
		
	}

	public Double calculateSum() {
		Double sum = 0.0;
		for(SoldItem item : goods){
			sum+=item.getSum();
		}
		this.sum=sum;
		return sum;
	}

	public List<SoldItem> getGoods() {
		return goods;
	}

	public void setGoods(List<SoldItem> goods) {
		this.goods = goods;
		//refreshStock(goods);
	}

	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public Long getId() {
		return id;
	}
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(" Purchase id: "+id);
		sb.append(" Products: ");
		for(SoldItem item : goods){
			sb.append(item.getName()+"; ");
		}
		return sb.toString();
	}
}
