package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Component;

public abstract class AnyOneTab {

	private String name;
	
	public AnyOneTab(String name){
		this.name = name;
	}
	
	public abstract Component draw();
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name =  name;
	}
}
