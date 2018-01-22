/**
 * 
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.MyFrame;
import model.Containers;
import model.Farmer;
import model.Fox;
import model.Goose;
import model.Status;

/**
 * @author mashichao
 *
 */
public class Controller implements ActionListener{

	private Fox aFox;
	private Goose aGoose;
	private model.Beans aBean;
	private Farmer aFarmer;
	private Containers aContainer;
	private Status aStatus;
	
	public Controller(Fox aFox, Goose aGoose, model.Beans aBean, Farmer aFarmer, Containers aContainer, Status aStatus) {
		this.aFox = aFox;
		this.aGoose = aGoose;
		this.aBean = aBean;
		this.aFarmer = aFarmer;
		this.aContainer = aContainer;
		this.aStatus = aStatus;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()){
		case "JBboatLeft":
			aContainer.moveOutRightWater();
			aContainer.checkAll();
			break;
		case "JBboatRight":
			aContainer.moveInRightWater();
			aContainer.checkAll();
			break;
		case "JBfoxLeft":
			if(aContainer.boatInRightRiver()){
				aContainer.moveInBC(aContainer.moveOutRGC(aFox));
			}else if(aContainer.foxInBC()){
				aContainer.moveInLGC(aContainer.moveOutBC(aFox));
			}
			aContainer.checkAll();
			break;
		case "JBfoxRight":
			if(!aContainer.boatInRightRiver()){
				aContainer.moveInBC(aContainer.moveOutLGC(aFox));
			}else if(aContainer.foxInBC()){
				aContainer.moveInRGC(aContainer.moveOutBC(aFox));
			}
			aContainer.checkAll();
			break;
		case "JBgooseLeft":
			if(aContainer.boatInRightRiver()){
				aContainer.moveInBC(aContainer.moveOutRGC(aGoose));
			}else if(aContainer.gooseInBC()){
				aContainer.moveInLGC(aContainer.moveOutBC(aGoose));
			}
			aContainer.checkAll();
			break;
		case "JBgooseRight":
			if(!aContainer.boatInRightRiver()){
				aContainer.moveInBC(aContainer.moveOutLGC(aGoose));
			}else if(aContainer.gooseInBC()){
				aContainer.moveInRGC(aContainer.moveOutBC(aGoose));
			}
			aContainer.checkAll();
			break;
		case "JBbeansLeft":
			if(aContainer.boatInRightRiver()){
				aContainer.moveInBC(aContainer.moveOutRGC(aBean));
			}else if(aContainer.beanInBC()){
				aContainer.moveInLGC(aContainer.moveOutBC(aBean));
			}
			aContainer.checkAll();
			break;
		case "JBbeansRight":
			if(!aContainer.boatInRightRiver()){
				aContainer.moveInBC(aContainer.moveOutLGC(aBean));
			}else if(aContainer.beanInBC()){
				aContainer.moveInRGC(aContainer.moveOutBC(aBean));
			}
			aContainer.checkAll();
			break;
		case "JBfarmerLeft":
			if(aContainer.boatInRightRiver()){
				aContainer.moveInBC(aContainer.moveOutRGC(aFarmer));
			}else if(aContainer.farmerInBC()){
				aContainer.moveInLGC(aContainer.moveOutBC(aFarmer));
			}
			aContainer.checkAll();
			break;
		case "JBfarmerRight":
			if(!aContainer.boatInRightRiver()){
				aContainer.moveInBC(aContainer.moveOutLGC(aFarmer));
			}else if(aContainer.farmerInBC()){
				aContainer.moveInRGC(aContainer.moveOutBC(aFarmer));
			}
			aContainer.checkAll();
			break;
		default:
			aStatus.setStatus("This is an useless status XD");
			break;
		}	
	}
	
	public static void main(String[] args){
		
		Fox theFox = new Fox();
		Goose theGoose = new Goose();
		model.Beans theBean = new model.Beans();
		Farmer theFarmer = new Farmer();
		Status theStatus = new Status();
		
		Containers theContainer = new Containers(theFox, theGoose, theBean, theFarmer, theStatus);
		
		Controller theController = new Controller(theFox, theGoose, theBean, theFarmer, theContainer, theStatus);
		
		MyFrame aMyFrame = new MyFrame(theFox, theGoose, theBean, theFarmer, theContainer, theController, theStatus);
		
		theContainer.addObserver(aMyFrame);
		theStatus.addObserver(aMyFrame);
	}
}
