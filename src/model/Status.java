/**
 * 
 */
package model;

import java.util.Observable;

/**
 * @author mashichao
 *used in the title of the frame
 */
public class Status extends Observable{
	
	private String status;
	private int score;
	private boolean end;
	
	public Status(){
		status = "Fox, Goose and Bag of Beans";
		score = 0;
		end = false;
	}

	public void setStatus(String aStatus){
		this.status = aStatus;
		setChanged();
		notifyObservers();
	}
	public void setScore(){
		this.score--;
		setChanged();
		notifyObservers();
	}
	public void setEnd(){
		end = true;
		setChanged();
		notifyObservers();
	}
	
	public String getStatus(){
		return status+" >>>Score: "+score;
	}
	public boolean getEnd(){
		return end;
	}
}
