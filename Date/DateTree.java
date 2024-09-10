package Date;


import java.util.Date;

import District.Dnode;
import Location.Lnode;
import Martyr.Martyr;
import Martyr.Mls;

public class DateTree{
	DateNode root;
	DateStack dateStack=new DateStack();
	DateStack temps=new DateStack();
	Mls mls=new Mls();
	int Marcount=0;

	public DateNode getRoot() {
		return root;
	}

	public DateNode find(String data) {
		return find(data,root);
	}

	public DateNode find(String data, DateNode node) {
		if (node == null) {
			return null;
		}

		Date curr = convertDate(node.data);
		Date ptr = convertDate(data);

		if (curr.compareTo(ptr) == 0) {
			return node;
		}
		else if (curr.compareTo(ptr) < 0) {
			return find(data, node.right);
		}
		else {
			return find(data, node.left);
		}
	}

	public Date convertDate(String d) {
		String[] tokens = d.split("/");
		int day = Integer.parseInt(tokens[1]);
		int month = Integer.parseInt(tokens[0]);
		int year = Integer.parseInt(tokens[2]);
		Date date = new Date(year - 1900, month - 1, day);
		return date;
	}


	public void insert(String data) {
		root= insert( data,getRoot());
	}

	public void updateDate(String DateName,String data) {
		DateNode node=find(DateName);
		Mls temp=node.mls;
		delete(DateName);
		insert(data);
		find(data).setMls(temp);
	}


	public DateNode insert(String data,DateNode node) {
		if (node == null) { 
			return new DateNode(data); 
		}
		if ((data).equalsIgnoreCase((node.data))){
			return node;
		}
		if (convertDate(data).compareTo(convertDate(node.data)) < 0) 
			node.left = insert(data, node.left); 
		else if (convertDate(data).compareTo(convertDate(node.data)) > 0) 
			node.right = insert(data, node.right); 
		else 
			return node;

		return node; 

	}

	public void delete(String data) {
		root = delete(data, root);
	}

	public DateNode delete(String data,DateNode node) {
		if(node==null) {
			return null;
		}
		if(node.data==null) {
			return null;
		}

		if(convertDate(node.data).compareTo(convertDate(data))<0) {
			node.right= delete( data,node.right);

		}else if(convertDate(node.data).compareTo(convertDate(data))>0) {
			node.left= delete( data,node.left);

		}else {
			if(node.right==null && node.left==null) {
				node = null;
			}
			else if(node.right==null) {
				node=node.left;
			}
			else if(node.left==null) {
				node=node.right;
			}else {
				DateNode suc =  minValueNode( node.right);
				node.data =suc.data;
				node.right= delete( node.data,node.right);
			}

		}

		return node;
	}

	public DateNode minValueNode() {
		return minValueNode(minValueNode(root));
	}

	public DateNode minValueNode(DateNode node) { 
		DateNode current = node; 
		if(current!=null) {
			while (current.left != null) 
				current = current.left; 
		} 
		return current; 
	}

	DateNode maxValueNode(DateNode node) { 
		DateNode current = node; 
		if(current!=null) {
			while (current.right != null) 
				current = current.right; 
		}
		return current; 
	}


	public int DatesTotalMartyr(){
		Marcount=0;
		return DatesTotalMartyr(root);
	}
	private int DatesTotalMartyr(DateNode r){
		if (r != null)
		{
			DatesTotalMartyr(r.getLeft());
			Marcount+=r.mls.getCount();
			DatesTotalMartyr(r.getRight());
		}
		return Marcount;
	}

	public void inOrder(){
		inOrder(root);
	}
	private void inOrder(DateNode r){
		if (r != null){
			inOrder(r.getLeft());
			dateStack.push(r);
			inOrder(r.getRight());
		}

	}

	public void preorder(){
		preorder(root);
	}
	private void preorder(DateNode r){
		if (r != null){
			System.out.print(r.getData() +" ");
			preorder(r.getLeft());
			preorder(r.getRight());
		}	

	}

	public String earliestDate() {
		if(minValueNode(root)!=null) {
			if(minValueNode(root).data!=null) {
				if(minValueNode(root).getMls().getCount()!=0) {
					return (minValueNode(root).data);
				}
				else {
					return root.data;
				}
			}
		}
		return null;


	}
	public String latestDate() {
		if(maxValueNode(root)!=null) {
			if(maxValueNode(root).getMls().getCount()!=0) {
				return(maxValueNode(root).data);
			}
		}
		return null;

	}

	public DateNode maxMartyrNum() {
		return maxMartyrNum(root);
	}

	private DateNode maxMartyrNum(DateNode node) {
		if (node == null) {
			return null; 
		}
		DateNode maxNode = node;

		DateNode leftMax = maxMartyrNum(node.left);
		if (leftMax != null && leftMax.mls.getCount() > maxNode.mls.getCount()) {
			maxNode = leftMax;
		}

		DateNode rightMax = maxMartyrNum(node.right);
		if (rightMax != null && rightMax.mls.getCount() > maxNode.mls.getCount()) {
			maxNode = rightMax;
		}

		return maxNode;
	}

	public boolean isEmpty() {
		if(root ==null) {
			return true;
		}
		else {
			return false;
		}
	}



	public DateStack getDateStack() {
		return dateStack;
	}


	public void setDateStack(DateStack dateStack) {
		this.dateStack = dateStack;
	}

	public Martyr averageAge() {
		return null;
	}

	

	public DateStack getTemps() {
		return temps;
	}


	public void setTemps(DateStack temps) {
		this.temps = temps;
	}


	public Mls getMls() {
		return mls;
	}


	public void setMls(Mls mls) {
		this.mls = mls;
	}


}
