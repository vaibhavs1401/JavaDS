package book;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BSTBook {
	class BookNode {
	    int bookId;
	    String title;
	    String author;
	    String genre;
	    int yearPublished;
	    int copiesAvailable;
	    BookNode left, right;

	    public BookNode(int bookId, String title, String author, String genre, int year, int copies) {
	        this.bookId = bookId;
	        this.title = title;
	        this.author = author;
	        this.genre = genre;
	        this.yearPublished = year;
	        this.copiesAvailable = copies;
	        this.left = this.right = null;
	    }

		@Override
		public String toString() {
			return "BookNode [bookId=" + bookId + ", title=" + title + "]";
		}
	    
	   
	}
	
	private BookNode root;
	public BSTBook() {
		root = null;
	}
	
	public void addNode(int bookId, String title, String author, String genre, int year, int copies) {
		BookNode newnode = new BookNode(bookId, title, author, genre, year, copies);
		if(root == null) {
			root = newnode;
		}
		else {
			BookNode trav = root;
			while(true) {
				if(newnode.bookId < root.bookId) {
					if(trav.left == null) {
						trav.left = newnode;
						break;
					}
					else {
						trav = trav.left;
					}
				}else {
					if(trav.right == null) {
						trav.right = newnode;
						break;
					}
					else {
						trav = trav.right;
					}
				}
			}
		}
		
	}
	
	public void deleteNode(int id) {
		BookNode trav = root;
		BookNode parent = null;
		while(trav!=null) {
			if(id == trav.bookId)
				break;
			parent = trav;
			if(id < trav.bookId)	
				trav = trav.left;
			else
				trav = trav.right;
		}
		if(trav == null)
			return;
		
		if(trav.left!=null && trav.right != null) {
			BookNode pred = trav.left;
			parent = trav;
			while(pred.right!=null) {
				parent = pred;
				pred = pred.right;
			}
			trav.bookId = pred.bookId;
			trav.author = pred.author;
			trav.copiesAvailable = pred.copiesAvailable;
			trav.genre = pred.genre;
			trav.title = pred.title;
			trav.yearPublished = pred.yearPublished;
			
			trav = pred;
		}
		
		if(trav.right == null) {
			if(trav == root)
				root = trav.left;
			else if(trav == parent.left)
				parent.left = trav.left;
			else if(trav == parent.right)
				parent.right = trav.left;
		}
		
		else {
			if(trav == root)
				root = trav.right;
			else if(trav == parent.left)
				parent.left = trav.right;
			else if(trav == parent.right)
				parent.right = trav.right;
		}

		
	}
	
	
	public void DFSTraversal() {
		Stack<BookNode> st = new Stack<BSTBook.BookNode>();
		
		System.out.println("DFS Traversal");
		st.push(root);
		while(!st.isEmpty()) {
			BookNode trav = st.pop();
			System.out.println(" " + trav.toString());
			
			if(trav.right != null)
				st.push(trav.right);
			if(trav.left != null)
				st.push(trav.left);
		}
		System.out.println("");
	}
	
	
	public void BFSTraversal() {
		Queue<BookNode> q = new LinkedList<BSTBook.BookNode>();
		System.out.println("BFS Traversal: ");
		q.offer(root);
		while(!q.isEmpty()) {
			BookNode trav = q.poll();			
			System.out.println(" " + trav.toString());
			if(trav.left != null)
				q.offer(trav.left);
			if(trav.right != null)
				q.offer(trav.right);
			
		}
		System.out.println("");
	}
	
	public void searchBook(int id) {
		BookNode trav = root;
		while(trav!=null) {
			if(id == trav.bookId) {
				break;
			}
			else if(id < trav.bookId) {
				trav = trav.left;
			}
			else if(id> trav.bookId) {
				trav = trav.right;
			}			
		}
		if(trav == null) {
			return;
		}
		else {
			System.out.println(trav.toString());
		}
		
	}
	
	public void searchBookByTitle(String title) {
		Queue<BookNode> bk = new LinkedList<BSTBook.BookNode>();
		System.out.println("Searching book by title using bfs traversal");
		bk.offer(root);
		while(!bk.isEmpty()) {
			BookNode trav = bk.poll();
			if(trav.equals(title)) {
				System.out.println("Book found");
				System.out.println(trav.toString());
				return;
			}
			if(trav.left!= null)
				bk.offer(trav.left);
			if(trav.right!= null)
				bk.offer(trav.right);
		}
		System.out.println("No book found!");
		
	}
	
	public void displayInorder() {	
		displayInorderTraversal(root);
	}
	
	
	private void displayInorderTraversal(BookNode trav){
		if(trav == null)
			return;
		displayInorderTraversal(trav.left);
		System.out.println(trav.toString());
		displayInorderTraversal(trav.right);
	}
	
	
	public void diplayPreOrderGenre(String genre) {
		displayPreOrderGenre(root, genre);
	}
	
	private void displayPreOrderGenre(BookNode trav, String genre) {
		if(trav == null)
			return;
		if(trav.genre.equals(genre)) {
			System.out.println(trav.toString());
		}
		displayPreOrderGenre(trav.left, genre);
		displayPreOrderGenre(trav.right , genre);
		
	}
	
	
	public void displayBeforeYear(int year) {
		System.out.println("Displaying books before given year by DFS");
		Stack <BookNode> st = new Stack<BSTBook.BookNode>();
		st.push(root);
		int count = 0;
		while(!st.isEmpty()) {
			BookNode trav = st.pop();
			if(trav.yearPublished < year) {
				count++;
			}
			else {
				if(trav.left!=null)
					st.push(trav.left);
				if(trav.right!=null)
					st.push(trav.right);
			}
		}
		System.out.println("Count is " + count);
		
	}
	
	public void upDatecopies(int id, int copies) {
		BookNode trav = root;
		while(trav!=null) {
			if(id == trav.bookId) {
				break;
			}
			else if(id < trav.bookId) {
				trav = trav.left;
			}
			else if(id > trav.bookId) {
				trav = trav.right;
			}
		}
		
		if(trav == null) {
			System.out.println("No book found! ");
		}else {
			trav.bookId = id;
			System.out.println(trav.toString());
		}
		
				
	}
	
	
	private void findMax(BookNode trav) {
		if(trav == null) {
			System.out.println("Tree is empty!");
			return;
		}

		while(trav.right!=null) {
			trav = trav.right;
		}
		System.out.println(trav.toString());
	}
	
	private void findMin(BookNode trav) {
		if(trav == null)
			return;
		while(trav.left != null) {
			trav = trav.left;
		}
		System.out.println(trav.toString());
	}
	
	
	public void minMax() {
		findMax(root);
		findMin(root);
	}
	
	
	public void Leafnodes() {
		Leafnodes(root);
	}
	
	private void Leafnodes(BookNode trav) {
		if(trav == null)
			return;
		if(trav.left == null && trav.right == null) {
			System.out.println(trav.toString());
		}
		Leafnodes(trav.left);
		Leafnodes(trav.right);
		
	}
	
	
	public void nonLeafNodes() {
		nonLeafNodes(root);
	}
	
	private void nonLeafNodes(BookNode trav) {
		if(trav == null)
			return;
		if(trav.left!=null || trav.right!= null) {
			System.out.println(trav.toString());
		}
		nonLeafNodes(trav.left);
		nonLeafNodes(trav.right);
	}
	
	
	
	public void BookatDepth(int level) {
		BookatDepth(root, level);
	}
	
	private void BookatDepth(BookNode trav, int level) {
		if(root == null) {
			return;
		}
		if(level == 0) {
			System.out.println(trav.toString());
		} else {
			BookatDepth(trav.left , level -1);
			BookatDepth(trav.right, level -1);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
