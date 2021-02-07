package homework; // please do not change the package 
                           //     or restore before turning in

import algs13.Queue;
import stdlib.GraphvizBuilder;
import stdlib.StdDraw;
import stdlib.StdOut;

/*  Benjamin M. Chavez
*   Section 810
*   
*   Delete one of the following:
*  A) The work submitted here is solely mine. 
*  
*   
*/ 


/**
 * Version 1.0
 * 
 * This homework is worth 24 points; it has two credit level options.
 * Option 1: complete ToDos 1-4    20/24 points 
 * Option 2: complete ToDos 1-5    24/24 points
 * 
 * effectively, option 1 has a maximum grade of B.  
 * To get a 100% score on the assignment you must complete option 2
 * 
 * You must not change the declaration of any method.
 * You must not change the Node class declaration
 * You may not add instance variables
 * You may not use any other java classes/algorithms without permission
 * 
 * You may create helper functions for Todo's 3, 4, and 5
 * 
 */


/**
 *  The simpleBST class represents a symbol table of
 *  generic key-value pairs using a binary search tree.  
 *  recursive versions of get and put have been renamed 
 *  rGet and rPut to facilitate testing of your non-recursive versions
 *  you may not use rPut or rGet as part of your 'toDo' solutions
 *  a size method is also provided which you may use 
 *  
 */
public class simpleBST<Key extends Comparable<Key>, Value> {
	private Node root;             // root of BST

	static boolean verbose = true;   // set to false to suppress positive test results
	private class Node {
		private Key key;           // key
		private Value val;         // associated data
		private Node left, right;  // left and right subtrees

		public Node(Key key, Value val) {
			this.key = key;
			this.val = val;
		}

		/**
		 * this method is provided to facilitate testing
		 */
		public void buildString(StringBuilder s) {
			s.append(left == null ? '[' : '(');
			s.append(key + "," + val);
			s.append(right == null ? ']' : ')');
			if (left != null) left.buildString(s);
			if (right != null) right.buildString(s);
		}
	}
	/**
	 * This method is provided to facilitate testing
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		root.buildString(s);
		return s.toString();
	}

	/**
	 * Initializes an empty symbol table.
	 */
	public simpleBST() {
		root = null;
	}

	/** size
	 * 
	 * return the size of the tree
	 */
	public int size() {
		return size( root);
	}
	private int size(Node x) {
		if ( x == null) return 0;
		return 1 + size(x.left)+size(x.right);
	}
	
	/** iokeys
	 * 
	 * Returns an Iterable containing the keys of the tree
	 * corresponding to an InOrder traversal
	 * 
	 * Hint: follow the approach outlined in class.
	 * use a helper function to carry out the traversal  
	 *   
	 *   
	 *   ToDo 1
	 *   ***************************************************************************************************************************STARTHERE
	 */
	public Iterable<Key> ioKeys() {
		Queue<Key> qok = new Queue();  // queue of keys
		ioKeys(root, qok);
		return qok;  //To do 1 complete this method
	}
	
	private Iterable<Key> ioKeys(Node x, Queue<Key> qok) {
		if ( x == null ) return qok;	
		ioKeys(x.left, qok);			// Traverse the left subtree in order
		qok.enqueue(x.key); 			// Process the node's root
		ioKeys(x.right, qok);			// Traverse the right subtree in order
		return qok;
	}

	
	/** put
	 * 
	 * Inserts the specified key-value pair into the binary search tree, overwriting the old 
	 * value with the new value if the tree already contains the specified key.
	 * 
	 * ToDo 2   write a non-recursive implementation of put
         * 
         *   you must fully document this method to receive credit
	 */
	public void put(Key key, Value val) {
		// return;    // To Do 2  complete this method
		// declare x as root to be able to update value during iteration
		Node x = root;
		
		while ( x != null ) {
			// create a comparison vaiable to determine if key is less than, equal to, or greater than the current node.
			int comp = key.compareTo(x.key);
			// if key is equal to the current node, update the node's value 
			if (comp == 0) {
				x.val = val;
				return;
			}
			// if key is less than the current node, iterate over the left subtree
			else if (comp < 0) {
				if (x.left == null) {					// if at the end of left subtree create new node
					x.left = new Node(key, val);
					return;
				}
				else
					x = x.left;							// update iterable and continue while loop
			}
			// if key is greater than the current node, iterate over the right subtree
			else
				if (x.right == null) {					// if at the end of right subtree create new node
					x.right = new Node(key, val);
					return;
				}
					
				else
					x = x.right;						// update iterable and continue while loop
		}
	}


	/** numNodesWithExactlyOneChild
	 * 
	 * Returns the number of nodes in the tree
	 * that have exactly one child
	 * 
	 * ToDo 3  
	 */
	public int numNodesWithExactlyOneChild() {
		int count = 0;
		return numNodesWithExactlyOneChild(root, count);
		
//		return -1; // ToDo 3 complete this method
	}
	
	private int numNodesWithExactlyOneChild(Node x, int count) {
		if (x == null) return count;
		if ( x.left == null && x.right == null) return count;
		
		if ( x.left == null && x.right != null) {
			count = numNodesWithExactlyOneChild(x.right, count + 1);
		}
		else if ( x.left != null && x.right == null ) {
			count = numNodesWithExactlyOneChild(x.left, count + 1);
			return count;
		}
		else {
			count = numNodesWithExactlyOneChild(x.left, count);
			count = numNodesWithExactlyOneChild(x.right, count);
		}
		return count;
	}
	
	public int numNodesWithTwoChildren() {
		int count = 0;
		return numNodesWithTwoChildren(root, count);
	}
	
	private int numNodesWithTwoChildren(Node x, int count) {
		if (x == null) return count;
		if (x.left == null && x.right == null) return count;
		
		if (x.left != null && x.right != null)
			count += 1;
		
		count = numNodesWithTwoChildren(x.left, count);
		count = numNodesWithTwoChildren(x.right, count);
		
		return count;
	}
	
	
	
	
	
	/** numberOfNodesAtDepth
	 * 
	 * Returns the number of nodes with depth == d
	 * Precondition: none 
	 * 
	 * param:  d  the depth to search for
	 * 
	 * hint:  use a recursive helper function
	 *      
	 * ToDo 4
	 */
	public int numNodesAtDepth(int d) {
		StdOut.println(d);
		int count = 0;
		return numNodesAtDepth(root, d, count);
		//		return -1; // ToDo 4  complete this method	
	}
	
	private int numNodesAtDepth(Node x, int depth, int count) {
		
		if (x == null ) return count;
		if (depth == 0) {
			return count += 1;
		}
		count = numNodesAtDepth(x.left, depth -1, count);
		count = numNodesAtDepth(x.right, depth -1, count);
		
		return count;
	}
	/** numNodesDeeperThan */
	public int numNodesDeeperThan(int d) {
//		StdOut.println(d);
		int count = 0;
		int currentd = 0;
		return numNodesDeeperThan(root, d, currentd, count);
		//		return -1; // ToDo 4  complete this method	
	}
	
	private int numNodesDeeperThan(Node x, int depth, int currentd, int count) {
		if (x == null ) return count;
		
		if (currentd > depth) {
			count += 1;
		}
		
		count = numNodesDeeperThan(x.left, depth, currentd + 1, count);
		count = numNodesDeeperThan(x.right, depth, currentd + 1, count);
		
	
		
		
		return count;
	}
	
	/**
	 * 
	 * delete
	 * 
	 * deletes the key (&value) from the table if the key is present
	 * using the the *dual* of the Hibbard approach from the text. That is, 
	 * for the two-child scenario, delete the node by replacing it 
	 * with it's predecessor (instead of its successor)
	 * 
	 * The functions:  max, deleteMax have been provided for you
	 * 
	 * ToDo 5:  implement a version of delete meeting the above spec
	 * 
	 * if you complete this toDo, comment out or delete the print statement, otherwise leave it in.
	 * 
	 */
	public void delete(Key key) {
//		StdOut.println(" delete not implemented ");
		root = delete(root, key);
		return;  // ToDo 5  complete this method
	}

	private Node delete(Node x, Key key) {
		
		if ( x == null) 
			return null;
		
		int comp = key.compareTo(x.key);
		if (comp < 0 )
			x.left = delete(x.left, key);
		else if (comp > 0)
			x.right = delete(x.right, key);
		else {									// comp == 0
			if (x.right == null)
				return x.left;
			if (x.left == null)
				return x.right;
			Node t = x;
			x = max(t.left);
			x.left = deleteMax(t.left);
			x.right = t.right;
			
		}
		return x;
		
	}
	
	/** max
	 * 
	 * return the node in the subtree x with the maximum key  ( right most node)
	 * 
	 * this is a method to help implement the delete method
	 */
	private Node max(Node x) {
		if ( x.right == null) return x;
		return max(x.right);
	}

	/** deleteMax
	 * 
	 * return the subtree x with the node with maximum key deleted
	 * 
	 * 	this is a method to help implement the delete method
	 */
	private Node deleteMax(Node x) {
		if ( x.right == null) return x.left;
		x.right = deleteMax(x.right);
		return x;
	}


	/*****************************************************
	 * 
	 * Utility functions 
	 */

	public Value rGet(Key key) {
		return rGet(root, key);
	}
	private Value rGet(Node x, Key key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) return rGet(x.left, key);
		else if (cmp > 0) return rGet(x.right, key);
		else              return x.val;
	}
	public void rPut(Key key, Value val) {
		if (key == null) throw new NullPointerException("first argument to put() is null");
		root = rPut(root, key, val);
	}

	private Node rPut(Node x, Key key, Value val) {
		if (x == null) return new Node(key, val);
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) x.left  = rPut(x.left,  key, val);
		else if (cmp > 0) x.right = rPut(x.right, key, val);
		else              x.val   = val;
		return x;
	}

	/* main calls all the testing functions */

	public static void main(String[] args)
	{
		// the 3 lines below are just
		// to illustrate building a tree and visualizing it
		// comment out or delete before submitting 
		// from (keys, values)
//		simpleBST<String,String> sampleTree = from("bcade","56981");
//		sampleTree.drawTree();
//		sampleTree.toGraphviz("myTree");
		
		
		// comment in/out calls to focus on a given ToDo method  				
//		iokeysTests();
//		putTests();
//		numNodesWithExactlyOneChildTests();
//		numNodesWithTwoChildrenTests();
//		numNodesAtDepthDTests();
		numNodesDeeperThanTests();
//		deleteTests();
	}

	/* ioKeysTests
	 * 
	 * parameters to testIoKeys
	 * 1:  String of keys to add to symbol table in order left-to-right
	 * 2:  expected result
	 */
	public static void iokeysTests() {
		testIoKeys("","");
		testIoKeys("HCADMLZ","ACDHLMZ");   
		testIoKeys("ABCDEFG","ABCDEFG");   
		testIoKeys("GFEDCBA","ABCDEFG");  
		testIoKeys("MABC","ABCM");      
		testIoKeys("MGLJK","GJKLM"); 
		testIoKeys("CBADE","ABCDE");   
		StdOut.println("----------- ioKeys tests completed");
	}

	/* putTests
	 * 
	 *  parameters to testPut
	 * 1:  String of keys to add to a symbol table in order left-to-right
	 * 2:  String of vals corresponding to above keys
	 * 3,4:  key,value to 'put'
	 * 5,6:  String of keys,values expected in resulting table in order left-to-right
	 */
	public static void putTests() {
		testPut("CA","31","C","3","CA","31");  // put existing key-val pair, no change
		testPut("HCADMJZGL","123456789","L","9","HCADMJZGL","123456789");    // put existing key-val pair, no change
		testPut("HCADMJZGL","123456789","L","0","HCADMJZGL","123456780");    // update value  in Leaf
		testPut("HCADMJZGL","123456789","C","0","HCADMJZGL","103456789");    // update value  in middle	
		testPut("HCADMJZGL","123456789","B","0","HCABDMJZGL","1230456789");  // Add new kv in middle
		testPut("ABCEFG","123567","D","4","ABCEDFG","1235467");  // straight line right, add new kv in middle
		StdOut.println("----------- put tests  completed");
	}


	/* 	numNodesWithExactlyOneChildTests
	 * 
	 *  parameters to 	testNumNodesWithExactlyOneChild
	 * 1:  String of keys & values to add to a symbol table in order left-to-right
	 * 2:  expected number of nodes with exactly one child
	 */
	public static void numNodesWithExactlyOneChildTests() {
		testNumNodesWithExactlyOneChild("C", 0);  
		testNumNodesWithExactlyOneChild("CA", 1);
		testNumNodesWithExactlyOneChild("BAC", 0);
		testNumNodesWithExactlyOneChild("ABC", 2);
		
//		testNumNodesWithExactlyOneChild("CBA", 2);
	
		testNumNodesWithExactlyOneChild("BACD", 1);
		testNumNodesWithExactlyOneChild("CBADE", 2);
		testNumNodesWithExactlyOneChild("DBACFEG", 0);
		testNumNodesWithExactlyOneChild("EAIDFBHCG", 6);
		StdOut.println("----------- numNodesWithExactlyOneChild tests  completed");
	}
	/* 	numNodesWithTwoChildren
	 */
	public static void numNodesWithTwoChildrenTests() {
//		testNumNodesWithTwoChildren("C", 0);  
//		testNumNodesWithTwoChildren("CA", 1);
		testNumNodesWithTwoChildren("BAC", 1);  
		testNumNodesWithTwoChildren("ABC", 0);

////		testNumNodesWithExactlyOneChild("CBA", 2);
//		
		testNumNodesWithTwoChildren("BACD", 1);
		testNumNodesWithTwoChildren("CBADE", 1);
		testNumNodesWithTwoChildren("DBACFEG", 3);
//		testNumNodesWithExactlyOneChild("EAIDFBHCG", 6);
		StdOut.println("----------- numNodesWithExactlyOneChild tests  completed");
	}

	/* 	numNodesAtDepth Tests
	 * 
	 *  parameters to testnumNodesAtDepth
	 * 1:  String of keys & values to add to a symbol table in order left-to-right
	 * 2:  depth to check for
	 * 2:  expected number of nodes at the specified depth
	 */
	public static void numNodesAtDepthDTests() {
		testnumNodesAtDepth("",0, 0);  		// empty tree has no nodes at any depth
		testnumNodesAtDepth("C",0, 1);  
		testnumNodesAtDepth("BAC",0, 1); 
		testnumNodesAtDepth("ABCDEFG",3, 1);
		testnumNodesAtDepth("DBACFEG",1, 2);
		testnumNodesAtDepth("DBACFEG",2, 4);
		testnumNodesAtDepth("DBACFEG",3, 0);
		testnumNodesAtDepth("EAIDFBHCG",4, 2);

		StdOut.println("----------- numNodesAtDepthD tests  completed");
	}
	/* 	numNodesDeeperThan Tests
	 * 
	 */
	public static void numNodesDeeperThanTests() {
		testnumNodesDeeperThan("",0, 0);  		// empty tree has no nodes at any depth
		testnumNodesDeeperThan("C",0, 0);  
		testnumNodesDeeperThan("BAC",0, 2); 
		testnumNodesDeeperThan("ABCDEFG",3, 3);
//		testnumNodesAtDepth("DBACFEG",1, 2);
//		testnumNodesAtDepth("DBACFEG",2, 4);
//		testnumNodesAtDepth("DBACFEG",3, 0);
//		testnumNodesAtDepth("EAIDFBHCG",4, 2);
		
		StdOut.println("----------- numNodesAtDepthD tests  completed");
	}
	/** deleteTests
	 * 
	 * parameters to testDelete
	 * 1:  String of keys to add to a symbol table in order left-to-right
	 * 2:  String of vals corresponding to above keys
	 * 3:  key to be deleted
	 * 4,5:  String of keys,values expected in resulting table in order left-to-right
	 */
	public static void deleteTests() {

		testDelete("CA","31","C","A","1");
		testDelete("CAD","314","D","CA","31");
		testDelete("ABCDEFG","1234567","G","ABCDEF","123456");  // straight line right
		testDelete("ABCDEFG","1234567","D","ABCEFG","123567");  // straight line right
		testDelete("GFEDCBA","7654321","A","GFEDCB","765432");  // straight line left
		testDelete("GFEDCBA","7654321","D","GFECBA","765321");  // straight line left
		testDelete("HCADMJZ","1234567", "H", "DCAMJZ","423567"); // delete root
		testDelete("HCADMJZGL","123456789", "M", "HCADLJZG","12349678"); // delete M
		testDelete("MJALBCD","1234567","J", "MDALBC","173456"); // delete J
		StdOut.println("----------- delete tests  completed");
	}

	/** from
	 * builds a BST using the author's version of put
	 */
	public static simpleBST<String, String> from(String keys, String vals) {
		if ( keys.length() != vals.length()) 
			throw new IllegalArgumentException("array sizes do not match");

		simpleBST<String,String> abst = new simpleBST<String, String>();
		for (int i=0; i < keys.length(); i++) {
			abst.rPut(keys.substring(i, i+1),vals.substring(i,i+1));
		}
		return abst;
	}
	/*************************************** testing functions ******************************/

	/**
	 *  testIokeys 
	 * param keys: all substrings of keys of length 1 are added to the ST
	 * param expected:  a string containing the correct inOrder ordering
	 */
	public static void testIoKeys( String keys,  String expected) {

		// create and populate the table from the input string keys
		simpleBST<String,String> aTree = from(keys,keys);
	
//		*********************************** To draw tree
//		aTree.drawTree();
		//  do the test
		Iterable<String> actual = aTree.ioKeys();
		//report result
		String actualString = new String();
		for (String s : actual) actualString += s;
		if ( actualString.equals(expected)) { // test passes
			if (verbose)
				StdOut.format("ioKeys: Correct  Tree Keys [ %s ] Answer: %s\n", keys, expected);
		}
		else
			StdOut.format("ioKeys: *Error*  Tree Keys [ %s ] expected: %s  actual: %s\n", 
					keys, expected, actualString);
	}

	/**
	 *  testNumNodesWithExactlyOneChild 
	 * param keys: all substrings of keys of length 1 are added to the ST
	 * param expected:  the correct result for this input string
	 */
	public static void testNumNodesWithExactlyOneChild( String keys, int expected ) {

		// create and populate the table from the input string 
		simpleBST<String,String> aTree = from(keys,keys);
		//  do the test
		int actual = aTree.numNodesWithExactlyOneChild();

		if ( actual == expected)  {// test passes
			if (verbose)
				StdOut.format("testNumNodesWithExactlyOneChild: Correct   Keys: [ %s ]   actual: %d\n", keys, actual);
		}
		else
			StdOut.format("testNumNodesWithExactlyOneChild: *Error*   Keys: [ %s ]   expected: %d  actual: %d\n", keys, expected, actual);
	}
	/**
	 *  testnumNodesWithTwoChildren 
	 */
	public static void testNumNodesWithTwoChildren( String keys, int expected ) {
		
		// create and populate the table from the input string 
		simpleBST<String,String> aTree = from(keys,keys);
		//  do the test
		int actual = aTree.numNodesWithTwoChildren();
		
		if ( actual == expected)  {// test passes
			if (verbose)
				StdOut.format("testNumNodesWithTwoChildren: Correct   Keys: [ %s ]   actual: %d\n", keys, actual);
		}
		else
			StdOut.format("testNumNodesWithTwoChildren: *Error*   Keys: [ %s ]   expected: %d  actual: %d\n", keys, expected, actual);
	}
	/**
	 *  testnumNodesAtDepth 
	 * param keys: all substrings of keys of length 1 are added to the ST
	 * param theDepth:  the depth to check for
	 * param expected:  the correct result for this input string and depth
	 */
	public static void testnumNodesAtDepth( String keys, int theDepth, int expected ) {

		// create and populate the table from the input string 
		simpleBST<String,String> aTree = from(keys,keys);
		//  do the test
		int actual = aTree.numNodesAtDepth(theDepth);

		if ( actual == expected)  {// test passes
			if (verbose)
				StdOut.format("testnumNodesAtDepthD: Correct   Keys: [ %s ]   actual: %d\n", keys, actual);
		}
		else
			StdOut.format("testnumNodesAtDepthD: *Error*   Keys: [ %s ]   expected: %d  actual: %d\n", keys, expected, actual);
	}
	/**
	 *  testnumNodesDeeperThan
	 */
	public static void testnumNodesDeeperThan( String keys, int theDepth, int expected ) {
		
		// create and populate the table from the input string 
		simpleBST<String,String> aTree = from(keys,keys);
		//  do the test
		int actual = aTree.numNodesDeeperThan(theDepth);
		
		if ( actual == expected)  {// test passes
			if (verbose)
				StdOut.format("testnumNodesDeeperThanD: Correct   Keys: [ %s ]   actual: %d\n", keys, actual);
		}
		else
			StdOut.format("testnumNodesDeeperThanD: *Error*   Keys: [ %s ]   expected: %d  actual: %d\n", keys, expected, actual);
	}



	/**
	 *  testPut 
	 * param keys: all substrings of keys of length 1 are added to the ST
	 * param vals: corresponding values for keys
	 * param pKey, pVal:   the key-value pair to be inserted into the ST
	 * param exKeys, exVals:  strings contained the expected ST contents after inserting pKey,pVal
	 * 
	 * this test not check for inserting a null value
	 */

	public static void testPut( String keys, String vals, String pKey, 
			String pVal,String exKeys, String exVals ) {

		// create and populate the table from the input string 
		simpleBST<String,String> testTree = from(keys,vals);
		simpleBST<String,String> expectedTree = from(exKeys,exVals);

		//  do the test
		testTree.put(pKey,pVal);

		String actual = testTree.toString();
		String expected = expectedTree.toString();
		if ( actual.equals(expected) ) {  // test passes 
			if (verbose) 
				StdOut.format("testPut: Correct Keys:[%s] Vals:[%s] \n                 Put:(%s,%s) Result: %s\n", 
						keys, vals, pKey, pVal, actual);
		} else
			StdOut.format("testPut: *Error*  Keys:[%s] Vals:[%s] \n                Put:(%s,%s) Result: %s\n",  
					keys, vals, pKey, pVal, actual);
	}

	/**
	 *  testDelete 
	 * param keys: all substrings of keys of length 1 are added to the ST
	 * param vals: corresponding values for keys
	 * param key:   the key to be deleted
	 * param exKeys, exVals:  strings contained the expected ST contents after deleting key
	 * 
	 */
	public static void testDelete( String keys, String vals, String key, String exKeys, String exVals ) {

		// create and populate the table from the input string 
		simpleBST<String,String> testTree = from(keys,vals);
		simpleBST<String,String> expectedTree = from(exKeys,exVals);
		//  do the test
		testTree.delete(key);
		String actual = testTree.toString();
		String expected = expectedTree.toString();
		if ( actual.equals(expected) ) { // test passes
			if (verbose)
				StdOut.format("testDelete: Correct  Keys:[ %s ]  Vals:[%s] \n           Delete:%s Result: %s\n", 
						keys, vals, key, actual);
		}else
			StdOut.format("testDelete: *Error*  Keys:[ %s ]  Vals:[%s] \n           Delete:%s Result: %s\n",  
					keys, vals, key, actual);
	}


	public void toGraphviz(String filename) {
		GraphvizBuilder gb = new GraphvizBuilder ();
		toGraphviz (gb, null, root);
		gb.toFileUndirected (filename, "ordering=\"out\"");
	}
	private void toGraphviz (GraphvizBuilder gb, Node parent, Node n) {
		if (n == null) { gb.addNullEdge (parent); return; }
		gb.addLabeledNode (n, n.key.toString ());
		if (parent != null) gb.addEdge (parent, n);
		toGraphviz (gb, n, n.left);
		toGraphviz (gb, n, n.right);
	}
	
	// You may modify "drawTree" if you wish
	public void drawTree() {
		if (root != null) {
			StdDraw.setPenColor (StdDraw.BLACK);
			StdDraw.setCanvasSize(1200,700);
			drawTree(root, .5, 1, .25, 0);
		}
	}
	private void drawTree (Node n, double x, double y, double range, int depth) {
		int CUTOFF = 10;
		StdDraw.text (x, y, n.key.toString ());
		StdDraw.setPenRadius (.007);
		if (n.left != null && depth != CUTOFF) {
			StdDraw.line (x-range, y-.08, x-.01, y-.01);
			drawTree (n.left, x-range, y-.1, range*.5, depth+1);
		}
		if (n.right != null && depth != CUTOFF) {
			StdDraw.line (x+range, y-.08, x+.01, y-.01);
			drawTree (n.right, x+range, y-.1, range*.5, depth+1);
		}
	}
	
}