package Location;

public class Queue{

	int front,rear;
	static int SIZE =1000;
	Lnode []queue;
	int count=0;


	public Queue() {
		this(SIZE);
	}

	public Queue(int size) {
		front=0;
		rear=size-1;
		this.queue = new Lnode[size];
	}

	public boolean isEmpty() {
		return count==0;
	}

	public boolean isFull() {
		return  count==getSize() ;
	}

	public void enQueue(Lnode data) {
		if(isFull()) {
			return;
		}
		else {
			rear=(rear+1)%getSize();
			queue[rear]=data;
			count++;
		}
	}

	public Lnode deQueue( ) {
		if(isEmpty()) {
			return null;
		}
		Lnode temp = queue[front];
		front=(front+1)%getSize();
		count--;
		return temp;
	}

	public  int getSize() {
		return queue.length;
	}
	public Object getRear() {

		if(isEmpty()) {
			return null;
		}
		return  queue[rear];
	}
	public Lnode getFront() {

		if(isEmpty()) {
			return null;
		}

		return  queue[front];
	}
	public Lnode peek() {
		if(isEmpty()) {
			return null;
		}
		return queue[rear];
	}

}