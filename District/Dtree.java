package District;

import java.util.ArrayList;

import Date.DateNode;
import Location.Lnode;
import Location.Ltree;
import Martyr.Martyr;


public class Dtree  {

	Dnode root;
	int count=0;
	Dstack dstack=new Dstack();

	public Dstack getStack() {
		return getDstack();
	}

	public void setStack(Dstack dstack) {
		this.setDstack(dstack);
	}

	public Dnode getRoot() {
		return root;
	}

	public Dnode find(String data) {
		return find( data,getRoot());
	}

	public Dnode find(String data,Dnode node) {
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
		root= insert(data,getRoot());
	}

	public Dnode insert(String data, Dnode node) {
		if (node == null) {
			return new Dnode(data); 
		}
		if(data.compareToIgnoreCase(node.data) == 0) {
			return node;
		}
		if (data.compareToIgnoreCase(node.data) < 0) {
			node.left = insert(data, node.left); 
		}
		else if (data.compareToIgnoreCase(node.data) > 0) { 
			node.right = insert(data, node.right); 
		}
		else {
			return node;
		}
		return node; 
	}

	public void delete(String data) {
		root = delete(data, root);
	}

	public Dnode delete(String data, Dnode node) {
		if (node == null) {
			return null;
		}

		if (data.compareToIgnoreCase(node.data) < 0) {
			node.left = delete(data, node.left);
		} 
		else if (data.compareToIgnoreCase(node.data) > 0) {
			node.right = delete(data, node.right);
		} 
		else {
			if (node.left == null) {
				return node.right;
			} else if (node.right == null) {
				return node.left;
			} else {
				Dnode successor = findminValueNode(node.right);
				node.data = successor.data;
				node.right = delete(successor.data, node.right);
			}
		}
		return node;
	}

	Dnode findminValueNode(Dnode node) { 
		Dnode current = node; 
		while (current.left != null) { 
			current = current.left; 
		}
		return current; 
	} 


	public void inOrder(){
		inOrder(root);
	}

	public void inOrder(Dnode root){
		if (root != null){
			inOrder(root.getLeft());
			inOrder(root.getRight());
		}
	}

	
	public void updateDistrict(String dName,String data) {
		Dnode node=find(dName);
		Ltree temp=node.lTree;
		delete(dName);
		insert(data);
		find(data).setlTree(temp);
	}

	public void navigateInOrder(){
		navigateInOrder(root);
	}
	private void navigateInOrder(Dnode root){

		if (root != null)
		{
			navigateInOrder(root.getRight());
			getDstack().push(root);
			navigateInOrder(root.getLeft());
		}
	}


	public Dstack getDstack() {
		return dstack;
	}


	public void setDstack(Dstack dstack) {
		this.dstack = dstack;
	}
}
