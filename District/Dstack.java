package District;

public class Dstack {
	Dnode [] stack;
	static int SIZE=100;
	int top;

	public Dstack( ) {
		this(SIZE);
	}

	public Dstack( int size) {
		super();
		stack = new Dnode [size];
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
		}else {
			return false;
		}
	}
	public boolean push(Dnode data) {
		if(isFull()) {
			return false;
		}
		else {
			top++;
			stack[top]=data;
			return true;
		}
	}


	public Dnode pop() {
		if(isEmpty()) {
			return null;
		}
		else {
			return stack[top--];
		}
	}

	public Dnode peek() {
		if(isEmpty()) {
			return null;
		}
		else {
			return stack[top];
		}
	}
	
	public void clear(Dstack s) {
		while(!s.isEmpty()) {
			s.pop();
		}
	}

	public void printStack(Dstack s1) {
		Dstack s2  = new Dstack(s1.getSize());
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