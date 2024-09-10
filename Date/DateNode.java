package Date;

import Martyr.Mls;

public class DateNode {
	DateNode left,right;
	String data;
	Mls mls=new Mls();

	public DateNode(String key){
		this.data=key;
	}

	@Override
	public String toString() {
		return data;
	}

	public DateNode getLeft() {
		return left;
	}

	public void setLeft(DateNode left) {
		this.left = left;
	}

	public DateNode getRight() {
		return right;
	}

	public void setRight(DateNode right) {
		this.right = right;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}


	public Mls getMls() {
		return mls;
	}

	public void setMls(Mls mls) {
		this.mls = mls;
	}

	
}

