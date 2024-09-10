package District;

import Location.Ltree;

public class Dnode {

	Dnode left,right;
	String data;
	Ltree lTree=new Ltree();
	
	public Dnode(String key){
		this.data=key;
	}

	@Override
	public String toString() {
		return data;
	}

	public Dnode getLeft() {
		return left;
	}

	public void setLeft(Dnode left) {
		this.left = left;
	}

	public Dnode getRight() {
		return right;
	}

	public void setRight(Dnode right) {
		this.right = right;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Ltree getlTree() {
		return lTree;
	}

	public void setlTree(Ltree lTree) {
		this.lTree = lTree;
	}


}
