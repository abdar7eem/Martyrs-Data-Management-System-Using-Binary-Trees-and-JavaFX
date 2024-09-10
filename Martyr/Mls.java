package Martyr;

import java.util.ArrayList;

public class Mls  {

	public Mnode first;
	public Mnode last;
	private int count=0;

	public void addFirst(Object data) {
		Mnode node=new Mnode(data);
		if(first==null) {
			last =first=node;
			setCount(getCount() + 1);
		}
		else {
			node.next=first;
			first=node;	
			setCount(getCount() + 1);
		}
	}

	public Object getFirst() {
		if(first==null) {
			return null;
		}
		else {
			return first.data;
		}
	}

	public void addLast(Object data) {
		Mnode node=new Mnode(data);
		if(last==null) {
			last=first=node;
		}
		else {
			last.next=node;
			last=node;		

		}
		setCount(getCount() + 1);
	}

	public Object getLast() {
		if(last==null) {
			return null;
		}
		else {
			return last.data;
		}
	}

	public void add(Object data,int index) {

		if(index<=0) {
			addFirst(data);
		}
		else if(index>=getCount()) {
			addLast(data);
		}
		else {
			Mnode node =new Mnode(data);
			Mnode temp=first;
			for(int i=0;i<index;i++) {
				temp=temp.next;
			}
			node.next=temp.next;
			temp.next=node;
			setCount(getCount() + 1);
		}
	}



	public int size() {
		return getCount();
	}


	public ArrayList<Martyr> p() {
		Mnode node=first;
		ArrayList<Martyr> arr=new ArrayList<>();
		for(int i=0;i<getCount();i++) {
			if(node!=null) {
				arr.add((Martyr)node.data);
				node=node.next;}
		}
		return arr;
	}


	public boolean removeFirst() {
		if (first == null) {
			return false;
		} else {
			Mnode temp = first;
			first = first.next;
			temp.next = null;
		}
		setCount(getCount() - 1);
		return true;
	}

	public boolean removeLast() {
		if (getCount() < 0) {
			return false;
		} else if (getCount() == 0) {
			first = last = null;
		} else {
			Mnode temp = first;
			for (int i = 0; i < getCount() - 1; i++) {
				if(temp.next!=null) {
					temp = temp.next;}
			}
			temp.next = null;
			last = temp;
		}
		setCount(getCount() - 1);
		return true;

	}

	public boolean remove(int index) {
		if (getCount() < 0 || index > getCount()) {
			return false;

		} else if (getCount() == 1) {
			return removeFirst();
		} else if (getCount() == index) {
			return removeLast();
		}
		else if(index==0) {
			return removeFirst();
		}
		else {
			Mnode ptr=first;
			for(int i=0;i<index-1;i++) {
				ptr=ptr.next;
			}
			Mnode temp=ptr.next;
			ptr.next=temp.next;
			temp.next=null;
			setCount(getCount() - 1);
		}
		return true;
	}

	
	public void addSort55(Object data) {
		Mnode newNode = new Mnode(data);
		if (first == null) {
			first = last = newNode;
			last.setNext(first);
			count++;
			return;
		}
		if (((Martyr) first.getData()).getAge()>(((Martyr) data).getAge())) {

			newNode.setNext(first);
			first = newNode;
			last.setNext(first);
			count++;
			return;

		}





		Mnode temp = first.getNext();
		do {
			if (((Martyr) first.getData()).getAge()>(((Martyr) data).getAge())) {

				newNode.next=first;
				first=newNode;
				last.next=first;
				count++;
				return;

			}else if(((Martyr) first.getData()).getAge()==(((Martyr) data).getAge())) {

				if(((Martyr) newNode.getData()).getGender().equalsIgnoreCase("F")) {

				}



			}
			if (((Martyr) temp.getData()).getAge()>(((Martyr) data).getAge()))  {

				break;
			}
			if(temp.next==first){

				temp.next=newNode;
				last=newNode;
				last.next=first;
				count++;
				return;
			}
			temp = temp.getNext();

		} while (temp != first);


		newNode.setNext(first.getNext());
		first.setNext(newNode);
		count++;

	}

	public Martyr martyrOldest() {
		Mnode temp = first;
		Mnode    max = temp;
		for(int i=0;i<count;i++) {
			if(temp!=null) {
				if(((Martyr)temp.getData()).getAge()>((Martyr)max.getData()).getAge()) {
					max=temp;
				}
				temp=temp.next;
			}
		}
		return ((Martyr)max.getData());
	}


	public Martyr martyrYoungest() {
		Mnode temp = first;
		Mnode min = temp;

		for(int i=0;i<count;i++) {
			if(temp!=null) {
				if(((Martyr)temp.getData()).getAge()<((Martyr)min.getData()).getAge()) {
					min=temp;
				}
				temp=temp.next;
			}
		}
		if(min==null) {
			return null;
		}
		return ((Martyr)min.getData());

	}

	public boolean removeByData(String data) {
		if(getCount()<0) {
			return false;
		}
		else if(((Martyr)first.getData()).getMname().compareTo(data)==0) {
			return removeFirst();
		}
		else if(((Martyr)last.getData()).getMname().compareTo(data)==0) {
			return removeLast();
		}
		else {
			Mnode ptr = first;
			for(int i=0; i<getCount();i++) {
				if(ptr!=null) {
					if(((Martyr)ptr.getData()).getMname().compareTo(data)==0){
						return remove(i);
					}

					ptr=ptr.next;
				}
			}
			return false;
		}
	}
	
	public double getMartyrAverage(){
		Mnode mNode =first;

		if(mNode==null) {
			return -1;
		}
		double sum=0;
		double res=0;
		for(int i=0;i<count;i++){
			sum+=((Martyr)mNode.getData()).getAge();
			if(mNode.getNext()==null) {
				return sum/(count);
			}
			mNode=mNode.getNext();
		}

		res=sum/count;
		return res;
	}

	public String getMartyerList( ) {
		String res="";

		Mnode temp = first;

		for (int i = 0; i < count+1; i++) {
			if(temp==null) {
				return res;
			}

			res+=((Martyr) temp.getData()).getMname()+ ",";
			temp = temp.getNext();

		}

		return res;

	}

	public ArrayList<Martyr> searchMartyr(String name){

		ArrayList<Martyr> arr= new ArrayList<>();

		Mnode temp = first;

		for (int i = 0; i <count; i++) {
			if(((Martyr)temp.getData()).getMname().contains(name)) {
				arr.add((Martyr)temp.getData());
				//b9textAtea.appendText(((Martyr)temp.getData()).getName() + "\n");;
			}
			temp = temp.getNext();
		}

		return arr;

	}



	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}








