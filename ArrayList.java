package edu.cofc.compsci.csci230;


/**
 * 
 * @author Computer Science Department, College of Charleston, CSCI 230 Spring 2014
 *
 */
public class ArrayList implements List {
	
	private Node[] nodeArray = new Node[10];
	private int emptyIndex = 0;
	


	/**
	 * 
	 * Add new Node object to first index position 
	 * that does not have a node object
	 * 
	 */
	public void add(int data) {
		
		if ( emptyIndex >= nodeArray.length ) {
			
			Node[] temp = new Node[nodeArray.length*2];
			
			System.arraycopy( nodeArray, 0, temp, 0, nodeArray.length );
			
			nodeArray = temp;
			
		} 
		
		nodeArray[emptyIndex++] = new Node(null,data);
		
	} // end add() method


	/**
	 * 
	 * Get the integer value stored in the node 
	 * object at the specified index position
	 * 
	 */
	public int getData(int index) throws OutOfBoundsException {
		
		return retrieve( index ).getData();
		
	} // end getData() method
	


	/**
	 * 
	 */
	public void printList() {
		
		try {
			
			if ( size() == 0 ) {
				
				System.out.println("Array List is empty!" );
				
			} else {


				for (int i=0; i<size(); i++ ) {
					
					System.out.printf("Node(%d)=%d\n", i+1, getData( i ) );
					
				} 
			
			}
			
		} catch( OutOfBoundsException error ) {
			
			System.out.println( error.getMessage() );
			
		}
		
	} // end printList() method


	/**
	 * 
	 * Remove the last node object in the array and
	 * resize array if less than 50% of capacity is
	 * being used
	 * 
	 */
	public void remove() {
		
		nodeArray[--emptyIndex]=null;
		
		emptyIndex = ( emptyIndex < 0 ) ? 0 : emptyIndex;
		
		if ( emptyIndex < ((int)(nodeArray.length*0.5)) ) {
			
			Node[] temp = new Node[((int)(nodeArray.length*0.5))];
			
			System.arraycopy( nodeArray, 0, temp, 0, ((int)(nodeArray.length*0.5)) );
			
			nodeArray = temp;
			
		}
		
	} // end remove() method
	
	
	/**
	 * 
	 */
	public Node retrieve(int index) throws OutOfBoundsException {


		if ( index >= 0 && index < size() ) {
			
			return nodeArray[index];
			
		} else {
			
			String message = String.format( "index position %d is not valid!\n", index );
			throw new OutOfBoundsException( message );
			
		}
		
	} // end retrieve() method


	/**
	 * 
	 * Return the number of node objects stored 
	 * in the array list
	 * 
	 */
	public int size() {
	
		return emptyIndex;
		
	} // end size() method
	
	/**
	 * 
	 * Implement as discussed in Lecture 5
	 * 
	 */
	public void add(int index, int data) throws OutOfBoundsException {
		if (index == 0){
			add(data);
		}
		
		if ( index <= size()  && index >= 0 && emptyIndex >= 0) {
			for (int i = emptyIndex; index <= i; i--){
				//Node[] tmp = new Node[nodeArray.length];
				nodeArray[i] = nodeArray[i-1];
				}
			nodeArray[index] = new Node(null, data);
			Node[] tmp = new Node[nodeArray.length*2];
			System.arraycopy( nodeArray, 0, tmp, 0, nodeArray.length );
			nodeArray = tmp;
			emptyIndex++;
			if ( emptyIndex >= nodeArray.length ) {
				Node[] temp = new Node[nodeArray.length*2];
				System.arraycopy( nodeArray, 0, temp, 0, nodeArray.length );
				nodeArray = temp;
				} 
			
			
		}
		else {
			String message = String.format( "index position %d is not valid!\n", index );
			throw new OutOfBoundsException( message );
			
		}
		
		
	} // end add() method


	/**
	 * 
	 * Implement as discussed in Lecture 5
	 * 
	 */
	public void clear() {
		//for (int i = size()+1; i >= 0; i--){
		//	remove();
			//}
		
		emptyIndex = 0;
		
		}
		


	/**
	 * 
	 * Implement as discussed in Lecture 5
	 * 
	 */
	public void remove(int index) throws OutOfBoundsException {
		emptyIndex--;
		if ( index < size() && index >= 0) {
			nodeArray[index]=null;	
			
			for (int i = index; i<size(); i++) {
				
				nodeArray[i] = nodeArray[i+1];
				
				
			}
			
			
			
		}	
			
		if ( emptyIndex < ((int)(nodeArray.length*0.5)) ) {
			
			Node[] temp = new Node[((int)(nodeArray.length*0.5))];
	
			System.arraycopy( nodeArray, index, temp, index, ((int)(nodeArray.length*0.5)) );
			nodeArray = temp;
			
		}	
			
		else if ( index == size()){
			emptyIndex--;
			remove();
			
		}
		else {
			String message = String.format( "index position %d is not valid!\n", index );
			throw new OutOfBoundsException( message );
		}




	}
	
	/**
	 * 
	 * Testing array list functionality
	 * 
	 */
		
			public static void main(String[] args) {
				
				
				ArrayList test = new ArrayList();
				
				
				System.out.println("----------------------------------");
				//1
				try {
				test.add( -1, 0 );
				System.out.println( "[1] Fail" );
				} catch( Exception error ) { System.out.println( "[1] Pass" ); }
				System.out.println("----------------------------------");
				//2
				try {
				test.add( 1, 0 );
				test.printList();
				System.out.println( "[2] Fail" );
				} catch( Exception error ) { System.out.println( "[2] Pass" ); }
				System.out.println("----------------------------------");
				//3
				try {
				test.remove( -1 );
				System.out.println( "[3] Fail" );
				} catch( Exception error ) { System.out.println( "[3] Pass" ); }
				System.out.println("----------------------------------");
				//4
				try {
				test.remove( 1 );
				System.out.println( "[4] Fail" );
				} catch( Exception error ) { System.out.println( "[4] Pass" ); }
				System.out.println("----------------------------------");
				//5
				try {
					test.printList();
				for ( int i=0; i<10; i++) test.add( i, i );
				
				if ( test.size() == 10 && test.getData( 0 ) == 0 && test.getData( 9 ) == 9 ) System.out.println( "[5] Pass" );
				test.printList();
				} catch( Exception error ) { System.out.println( "[5] Fail" ); }
				System.out.println("----------------------------------");
				//6
				try {
				for ( int i=2; i<6; i++ ) test.remove( i );
				if ( test.size() == 6 && test.getData( 2 ) == 3 && test.getData( 5 ) == 9 ) System.out.println( "[6] Pass" );
				//test.printList();
				} catch( Exception error ) { System.out.println( "[6] Fail" ); }
				System.out.println("----------------------------------");
				//7
				try {
				test.remove( 6 );
				System.out.println( "[7] Fail" );
				} catch( Exception error ) { System.out.println( "[7] Pass" ); }
				System.out.println("----------------------------------");
				//8
				try {
				test.add( 7, 11 );
				System.out.println( "[8] Fail" );
				//test.printList();
				} catch( Exception error ) { System.out.println( "[8] Pass" ); }
				System.out.println("----------------------------------");
				//9
				try {
				test.add( 6, 11 );
				System.out.println( "[9] Pass" );
				//test.printList();
				} catch( Exception error ) { System.out.println( "[9] Fail" ); }
				System.out.println("----------------------------------");
				//10
				test.clear();
				if ( test.size() == 0 ) System.out.println( "[10] Pass" );
				else System.out.println("[10] Fail" );
				System.out.println("----------------------------------");
				//11
				try {
				test.add( 0, 1 );
				if ( test.size()==1 && test.getData( 0 )==1 ) System.out.println( "[11] Pass" );
				else System.out.println( "[11] Fail" );
				} catch( Exception error ) { System.out.println( "[11] Fail" ); }
				System.out.println("----------------------------------");
				//12
				try {
				test.add( 0, 3 );
				if ( test.size()==2 && test.getData( 0 )==3 ) System.out.println( "[12] Pass" );
				else System.out.println( "[12] Fail" );
				} catch( Exception error ) { System.out.println( "[12] Fail" ); }
				System.out.println("----------------------------------");
				//13
				try {
				test.add( 0, 5 );
				if ( test.size()==3 && test.getData( 0 )==5 ) System.out.println( "[13] Pass" );
				else System.out.println( "[13] Fail" );
				} catch( Exception error ) { System.out.println( "[13] Fail" ); }
				System.out.println("----------------------------------");
				//14
				try {
				test.remove( 0 );
				if ( test.size()==2 && test.getData( 0 )==3 ) System.out.println( "[14] Pass" );
				else System.out.println( "[14] Fail" );
				} catch( Exception error ) { System.out.println( "[14] Fail" ); }
				System.out.println("----------------------------------");
				//15
				try {
				test.remove( 1 );
				if ( test.size()==1 && test.getData( 0 )==3 ) System.out.println( "[15] Pass" );
				else System.out.println( "[15] Fail" );
				} catch( Exception error ) { System.out.println( "[15] Fail" ); }
				System.out.println("----------------------------------");
				//16
				try {
				test.add( 1, 8 );
				if ( test.size()==2 && test.getData( 1 )==8 ) System.out.println( "[16] Pass" );
				else System.out.println( "[16] Fail" );
				//test.printList();
				} catch( Exception error ) { System.out.println( "[16] Fail" ); }
				System.out.println("----------------------------------");
				//17
				try {
				for ( int i=1; i<4; i++ ) {
				test.add( i, i*2 );
				}
				if ( test.size()==5 && test.getData( 1 )==2 && test.getData( 2 )==4 && test.getData( 3 ) == 6 ) System.out.println( "[17] Pass" );
				else System.out.println( "[17] Fail" );
				//test.printList();
				} catch( Exception error ) { System.out.println( "[17] Fail" ); }
				System.out.println("----------------------------------");
				//18
				try {
				for ( int i=3; i>0; i-- ) {
				test.remove( i );
				}
				if ( test.size()==2 && test.getData( 0 )==3 && test.getData( 1 )==8 ) System.out.println( "[18] Pass" );
				else System.out.println( "[18] Fail" );
				//test.printList();
				} catch( Exception error ) { System.out.println( "[18] Fail" ); }
				System.out.println("----------------------------------");
				//19
				try {
				for ( int i=2; i<10; i++ ) {
				test.add( i, 1 );
				}
				int sum = 0;
				for ( int i=2; i<10; i++ ) {
				sum = sum + test.getData( i );
				}
				if ( test.size()==10 && test.getData( 0 )==3 && test.getData( 1 )==8 & sum==8) System.out.println( "[19] Pass" );
				else System.out.println( "[19] Fail" );
				//test.printList();
				} catch( Exception error ) { System.out.println( "[19] Fail" ); }
				System.out.println("----------------------------------");
				//20
				try {
				test.add( 0, -1);
				for ( int i=7; i>4; i-- ) {
				test.remove( i );
				}
				int sum = 0;
				for ( int i=3; i<8; i++ ) {
				sum = sum + test.getData( i );
				}
				if ( test.size()==8 && test.getData( 0 )==-1 && test.getData( 1 )==3 & sum==5) System.out.println( "[20] Pass" );
				else System.out.println( "[20] Fail" );
				// test.printList();
				} catch( Exception error ) { System.out.println( "[20] Fail" ); 
				
				
				}


