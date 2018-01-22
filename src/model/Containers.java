/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.Observable;

/**
 * @author mashichao
 *
 */
public class Containers extends Observable{
	/**
	 * there are three positions can contain items
	 */
	private ArrayList<Items> rightGrassContainer;
	private ArrayList<Items> boatContainer;
	private ArrayList<Items> leftGrassContainer;
	private ArrayList<ArrayList<Items>> riverContainer;
	/**
	 * initial some items
	 */
	private Fox aFox;
	private Goose aGoose;
	private Beans aBean;
	private Farmer aFarmer;
	private Status aStatus;
	
	//The constructor
	public Containers(Fox aFox, Goose aGoose, Beans aBean, Farmer aFarmer, Status aStatus){
		
		rightGrassContainer = new ArrayList<Items>();
		boatContainer = new ArrayList<Items>();
		leftGrassContainer = new ArrayList<Items>();
		riverContainer = new ArrayList<ArrayList<Items>>();
		
		this.aFox = aFox;
		this.aGoose = aGoose;
		this.aBean = aBean;
		this.aFarmer = aFarmer;
		this.aStatus = aStatus;
		/**
		 * initial the position of items
		 */
		riverContainer.add(boatContainer);
		rightGrassContainer.add(aFox);
		rightGrassContainer.add(aGoose);
		rightGrassContainer.add(aBean);
		rightGrassContainer.add(aFarmer);
	}
	
	/**
	 * Some get methods used in "View"
	 */
	//for RGC
	public boolean foxInRGC(){
		return rightGrassContainer.contains(aFox);
	} 
	public boolean gooseInRGC(){
		return rightGrassContainer.contains(aGoose);
	} 
	public boolean beanInRGC(){
		return rightGrassContainer.contains(aBean);
	} 
	public boolean farmerInRGC(){
		return rightGrassContainer.contains(aFarmer);
	} 
	//for BC
	public boolean foxInBC(){
		return boatContainer.contains(aFox);
	} 
	public boolean gooseInBC(){
		return boatContainer.contains(aGoose);
	} 
	public boolean beanInBC(){
		return boatContainer.contains(aBean);
	} 
	public boolean farmerInBC(){
		return boatContainer.contains(aFarmer);
	} 
	//for LGC
	public boolean foxInLGC(){
		return leftGrassContainer.contains(aFox);
	} 
	public boolean gooseInLGC(){
		return leftGrassContainer.contains(aGoose);
	} 
	public boolean beanInLGC(){
		return leftGrassContainer.contains(aBean);
	} 
	public boolean farmerInLGC(){
		return leftGrassContainer.contains(aFarmer);
	} 
	//for river
	public boolean boatInRightRiver(){
		return riverContainer.contains(boatContainer);
	}
	
	/**
	 * Some action methods used in 'Controller'
	 * @param anItem
	 */
	//for RGC
	public void moveInRGC(Items anItem){
			rightGrassContainer.add(anItem);
			setChanged();
			notifyObservers();
	}
	public Items moveOutRGC(Items anItem){
		if(boatContainer.size()>=2){
			return null;
		}
		for(Items aItem: rightGrassContainer){
			if(aItem.equals(anItem))return rightGrassContainer.remove(rightGrassContainer.indexOf(aItem));
		}				
		return null;
	}
	//for BC
	public void moveInBC(Items anItem){
		if(anItem!=null){
			if(boatContainer.size() < 2){
				boatContainer.add(anItem);
				setChanged();
				notifyObservers();
				aStatus.setStatus(anItem.getName()+" gets on the boat");
			}
		}
	}
	public Items moveOutBC(Items anItem){
		for(Items aItem: boatContainer){
			if(aItem.equals(anItem)){
				aStatus.setStatus(aItem.getName()+" get off the boat");
				return boatContainer.remove(boatContainer.indexOf(aItem));
			}	
		}
		return null;
	}
	//for LGC
	public void moveInLGC(Items anItem){
			leftGrassContainer.add(anItem);
			setChanged();
			notifyObservers();
	}
	public Items moveOutLGC(Items anItem){
		if(boatContainer.size()>=2){
			return null;
		}
		for(Items aItem: leftGrassContainer){
			if(aItem.equals(anItem))return leftGrassContainer.remove(leftGrassContainer.indexOf(aItem));
		}
		return null;
	}
	//for Water
	public void moveInRightWater(){
		if(riverContainer.isEmpty()){
			if(this.farmerInBC()){
				riverContainer.add(boatContainer);
				setChanged();
				notifyObservers();
				aStatus.setStatus("The boat move backward");
				aStatus.setScore();
			}else{
				aStatus.setStatus("The boat can not move without the farmer");
			}
		}
	}
	public void moveOutRightWater(){
		if(!riverContainer.isEmpty()){
			if(this.farmerInBC()){
				riverContainer.remove(0);
				setChanged();
				notifyObservers();
				aStatus.setStatus("The boat move forward");
				aStatus.setScore();
			}else{
				aStatus.setStatus("The boat can not move without the farmer");
			}
		}
	}
	
	/**
	 * Some methods used to check the the success and failure of the game
	 */
	public void checkRightGrass(){
		if(this.foxInRGC() && this.gooseInRGC() && !this.boatInRightRiver()){
			aStatus.setStatus("The fox ate the goose, GAME OVER! Reopen the window to restart the Game!");
			aStatus.setEnd();
		}else if(this.gooseInRGC() && this.beanInRGC() && !this.boatInRightRiver()){
			aStatus.setStatus("The goose ate the bean, GAME OVER! Reopen the window to restart the Game!");
			aStatus.setEnd();
		}
	}
	public void checkLeftGrass(){
		if(this.foxInLGC() && this.gooseInLGC() && this.boatInRightRiver()){
			aStatus.setStatus("The fox ate the goose, GAME OVER! Reopen the window to restart the Game!");
			aStatus.setEnd();
		}else if(this.gooseInLGC() && this.beanInLGC() && this.boatInRightRiver()){
			aStatus.setStatus("The goose ate the bean, GAME OVER! Reopen the window to restart the Game!");
			aStatus.setEnd();
		}
	}
	public void checkSuccess(){
		if(this.foxInLGC() && this.gooseInLGC() && this.beanInLGC() && this.farmerInLGC()){
			aStatus.setStatus("Victory!!! Reopen the window to restart the Game!");
			aStatus.setEnd();
		}
	}
	public void checkAll(){
		this.checkRightGrass();
		this.checkLeftGrass();
		this.checkSuccess();
	}
}
