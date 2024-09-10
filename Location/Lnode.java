package Location;

import Date.DateTree;

public class Lnode {

	Lnode left,right;
	String data;
	DateTree dateTree=new DateTree();

	public Lnode(String key){
		this.data=key;
	}

	@Override
	public String toString() {
		return data;
	}

	public Lnode getLeft() {
		return left;
	}

	public void setLeft(Lnode left) {
		this.left = left;
	}

	public Lnode getRight() {
		return right;
	}

	public void setRight(Lnode right) {
		this.right = right;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public DateTree getDateTree() {
		return dateTree;
	}


	public void setDateTree(DateTree dateTree) {
		this.dateTree = dateTree;
	}

	

}

