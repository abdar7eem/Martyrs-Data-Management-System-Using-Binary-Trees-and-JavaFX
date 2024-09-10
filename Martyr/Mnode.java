package Martyr;

public class Mnode {
	
	public Mnode next;
	public Object data;
	
	public Mnode(Object data) {
		super();
		this.data = data;
	}

	public Mnode getNext() {
		return next;
	}

	public void setNext(Mnode next) {
		this.next = next;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
	
	
	
}

