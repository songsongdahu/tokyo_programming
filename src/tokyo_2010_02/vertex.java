package tokyo_2010_02;

import java.util.ArrayList;

public class vertex {
	private int num;
	private ArrayList<vertex> nexts;
	
	public vertex(int num){
		this.num = num;
		nexts = new ArrayList<vertex>();
	}
	
	public int getNum(){
		return num;
	}
	
	public void setNum(int num){
		this.num = num;
	}
	
	public ArrayList<vertex> getNexts(){
		return nexts;
	}
	
	public void addNext(vertex v){
		nexts.add(v);
	}
	
	public boolean hasV(vertex v){
		for(int i=0;i<nexts.size();i++){
			if(v.getNum()==nexts.get(i).getNum()){
				return true;
			}
		}
		return false;
	}
	
	public String toString(){
		return num+"";
	}
}
