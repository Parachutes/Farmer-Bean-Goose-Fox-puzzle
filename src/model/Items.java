/**
 * 
 */
package model;


/**
 * @author mashichao
 *
 */
public class Items{
	//attributes of an item
	private String name;
	private String picPath;
	//constructor
	public Items(String filePath, String name){
		this.picPath = filePath;
		this.name = name;
	}
	//get Method
	public String getThePicPath(){
		return picPath;
	}
	public String getName(){
		return name;
	}
	
	
	//<<<<<<<<<<<Debug
	@Override
	public String toString(){
		return name;
	}
}
