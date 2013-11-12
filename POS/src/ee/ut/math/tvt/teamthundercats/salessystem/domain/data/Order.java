package ee.ut.math.tvt.teamthundercats.salessystem.domain.data;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import ee.ut.math.tvt.teamthundercats.salessystem.domain.data.SoldItem;


@Entity
@Table(name = "order")
public class Order implements Cloneable, DisplayableItem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
    private Long id;
	
	@OneToMany(mappedBy = "solditem")
	@Transient
    private List<SoldItem> items;
    
    @Column(name = "TOTAL_PRICE")
    private Double sum;
    
    @Column(name = "date")
    private Date date;
    
    
    
	
	public static Long counter = 0L;


	public void refreshStock() {
		
		for (SoldItem soldItem : items) {
			int amount = soldItem.getQuantity();
			int stockAmount = soldItem.getStockItem().getQuantity();
			if(stockAmount-amount>-1){
				soldItem.getStockItem().setQuantity(stockAmount-amount);
			} else {
			}
			
		}
	}

	public Double calculateSum() {
		Double sum = 0.0;
		for(SoldItem item : items){
			sum+=item.getSum();
		}
		this.sum=sum;
		return sum;
	}

	public List<SoldItem> getItems() {
		return items;
	}
	

	public void setItems(List<SoldItem> items) {
		this.items = items;
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
		sb.append(" Order id: "+id);
		sb.append(" Items of an order: ");
		for(SoldItem item : items){
			sb.append(item.getName()+"; ");
		}
		return sb.toString();
	}
}
