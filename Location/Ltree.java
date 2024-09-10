package Location;

import Date.DateNode;
import Date.DateTree;
import District.Dnode;
import District.Dstack;
import Martyr.Martyr;

public class Ltree  {

	Lnode root;
	int Lcounter=0;
	Lstack lstack=new Lstack();
	Lstack temps=new Lstack();
	private int martCount;
	static Queue queue=new Queue();
	int martCounter=0;

	public Lnode getRoot() {
		return root;
	}

	public Lstack getLstack() {
		return lstack;
	}

	public void setLstack(Lstack lstack) {
		this.lstack = lstack;
	}

	public Lstack getTemps() {
		return temps;
	}

	public void setTemps(Lstack temps) {
		this.temps = temps;
	}

	public Lnode find(String data) {
		return find( data,getRoot());
	}

	public Lnode find(String data,Lnode node) {
		if(node==null) {
			return null;
		}

		if(node.getData().equalsIgnoreCase(data)) {
			return node;
		}
		else if(node.getData().compareToIgnoreCase(data)<0) {
			return find(data, node.right);

		}else  {
			return find(data, node.left);

		}


	}

	public void insert(String data) {
		root= insert( data,getRoot());

	}

	public Lnode insert(String data,Lnode node) {
		if(node==null) {
			node = new Lnode(data);
		}
		if ((data).equalsIgnoreCase((node.data))){
			return node;
		}

		if(node.getData().compareToIgnoreCase(data)<0 ) {
			node.right= insert(data, node.right);

		}else if(node.getData().compareToIgnoreCase(data)>0) {
			node.left= insert(data, node.left);

		}

		return node;
	}

	public void delete(String data) {
		root = delete(data, root);
	}

	public Lnode delete(String data, Lnode node) {
		if (node == null) {
			return null;
		}

		if (data.compareToIgnoreCase(node.data) < 0) {
			node.left = delete(data, node.left);
		} else if (data.compareToIgnoreCase(node.data) > 0) {
			node.right = delete(data, node.right);
		} else {
			if (node.left == null) {
				return node.right;
			} else if (node.right == null) {
				return node.left;
			} else {
				Lnode successor = minValueNode(node.right);
				node.data = successor.data;
				node.right = delete(successor.data, node.right);
			}
		}
		return node;
	}

	Lnode minValueNode(Lnode node) { 
		Lnode current = node; 
		while (current.left != null) 
			current = current.left; 

		return current; 
	} 

	public int LocationstotalMartyr(){
		martCount=0;
		return LocationstotalMartyr(root);
	}

	private int LocationstotalMartyr(Lnode r){
		if (r != null){
			LocationstotalMartyr(r.getLeft());
			martCount +=r.dateTree.DatesTotalMartyr();
			LocationstotalMartyr(r.getRight());
		}
		return martCount;
	}

	public int totalLocation(){
		Lcounter=0;
		return totalLocation(root);
	}

	private int totalLocation(Lnode r){
		if (r != null){
			totalLocation(r.getLeft());
			Lcounter+=1;
			totalLocation(r.getRight());
		}
		return Lcounter;
	}

	public void updateLocation(String locName,String data) {
		Lnode node=find(locName);
		DateTree temp=node.dateTree;
		delete(locName);
		insert(data);
		find(data).setDateTree(temp);
	}

	public void inOrderNavigation(){
		inOrderNavigation(root);
	}
	private void inOrderNavigation(Lnode root){
		if (root != null){
			inOrderNavigation(root.getRight());
			lstack.push(root);
			inOrderNavigation(root.getLeft());
		}
	}

	public void inOrder(){
		inOrder(root);
	}

	private void inOrder(Lnode r){
		if (r != null)
		{
			inOrder(r.getLeft());
			System.out.print(r.getData() +" ");
			inOrder(r.getRight());
		}
	}

	public void preorder(){
		preorder(root);
	}

	private void preorder(Lnode r){
		if (r != null){
			System.out.print(r.getData() +" ");
			preorder(r.getLeft());
			preorder(r.getRight());
		}
	}

	public void NavigateLevelOreder(){
		NavigateLevelOreder(root);
		while(!queue.isEmpty()) {
			lstack.push(queue.deQueue());
		}
	}

	static void NavigateLevelOreder(Lnode root) {
		if (root == null) {
			return;
		}
		Queue q1=new Queue();
		q1.enQueue(root); 
		while (!q1.isEmpty()) {
			Lnode curr = q1.deQueue();
			if (curr != null) {
				if (curr.left != null) {
					q1.enQueue(curr.left);
				}
				if (curr.right != null) {
					q1.enQueue(curr.right);
				}
				queue.enQueue(curr);    
			}
		}

	}

}