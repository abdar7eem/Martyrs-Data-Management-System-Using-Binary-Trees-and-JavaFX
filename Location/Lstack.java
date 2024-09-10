package Location;

public class Lstack {
	Lnode [] stack;
	static int SIZE=100;
	int top;

	public Lstack( ) {
		this(SIZE);
	}

	public Lstack( int size) {
		super();
		stack = new Lnode [size];
		this.top = -1;
	}
	public int getSize() {
		return stack.length;
	}

	public boolean isFull() {
		if(top>=getSize()-1) {
			return true;
		}else {
			return false;
		}
	}

	public boolean isEmpty() {
		if(top<0) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean push(Lnode data) {
		if(isFull()) {
			return false;
		}
		else {
			top++;
			stack[top]=data;
			return true;
		}
	}


	public Lnode pop() {
		if(isEmpty()) {
			return null;
		}
		else {
			return stack[top--];
		}
	}

	public Lnode peek() {
		if(isEmpty()) {
			return null;
		}
		else {
			return stack[top];
		}
	}

	public void clear(Lstack s) {
		while(!s.isEmpty()) {
			s.pop();
		}
	}

	public void printStack(Lstack s1) {
		Lstack s2  = new Lstack(s1.getSize());
		while(!s1.isEmpty()) {
			s2.push(s1.pop());
			System.out.println(s2.peek());
		}
		while(!s2.isEmpty()) {
			s1.push(s2.pop());
		}

	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}


}