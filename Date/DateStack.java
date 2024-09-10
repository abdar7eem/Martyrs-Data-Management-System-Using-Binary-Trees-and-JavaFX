package Date;



public class DateStack {
	DateNode [] stack;
	static int SIZE=100;
	int top;

	public DateStack( ) {
		this(SIZE);
	}

	public DateStack( int size) {
		super();
		stack = new DateNode [size];
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
	public boolean push(DateNode data) {
		if(isFull()) {
			return false;
		}
		else {
			top++;
			stack[top]=data;
			return true;
		}
	}


	public DateNode pop() {
		if(isEmpty()) {
			return null;
		}
		else {
			return stack[top--];
		}
	}

	public DateNode peek() {
		if(isEmpty()) {
			return null;
		}
		else {
			return stack[top];
		}
	}

	public void clear(DateStack s) {
		while(!s.isEmpty()) {
			s.pop();
		}
	}

	public void printStack(DateStack s1) {
		DateStack s2  = new DateStack(s1.getSize());
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
