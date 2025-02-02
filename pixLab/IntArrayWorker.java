public class IntArrayWorker
{
  /** two dimensional matrix */
  private int[][] matrix = null;
  
  /** set the matrix to the passed one
    * @param theMatrix the one to use
    */
  public void setMatrix(int[][] theMatrix)
  {
    matrix = theMatrix;
  }
  
  /**
   * Method to return the total 
   * @return the total of the values in the array
   */
  public int getTotal()
  {
    int total = 0;
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < matrix[0].length; col++)
      {
        total = total + matrix[row][col];
      }
    }
    return total;
  }
  
  /**
   * Method to return the total using a nested for-each loop
   * @return the total of the values in the array
   */
  public int getTotalNested()
  {
    int total = 0;
    for (int[] rowArray : matrix)
    {
      for (int item : rowArray)
      {
        total = total + item;
      }
    }
    return total;
  }
  
  /**
   * Method to fill with an increasing count
   */
  public void fillCount()
  {
    int numCols = matrix[0].length;
    int count = 1;
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < numCols; col++)
      {
        matrix[row][col] = count;
        count++;
      }
    }
  }
  
  /**
   * print the values in the array in rows and columns
   */
  public void print()
  {
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < matrix[0].length; col++)
      {
        System.out.print( matrix[row][col] + " " );
      }
      System.out.println();
    }
    System.out.println();
  }
  
  
  /** 
   * fill the array with a pattern
   */
  public void fillPattern1()
  {
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < matrix[0].length; 
           col++)
      {
        if (row < col)
          matrix[row][col] = 1;
        else if (row == col)
          matrix[row][col] = 2;
        else
          matrix[row][col] = 3;
      }
    }
  }
  
  public int getCount(int num){
	  int ans = 0;
	  for (int i = 0; i < matrix.length; i++){
		for (int j = 0; j < matrix[i].length; j++){
			if (matrix[i][j] == num) ans++;
		}
	  }
	  return ans;
  }
  
  public int getLargest(){
	int ans = 0;
	for (int i = 0; i < matrix.length; i++){
		for (int j = 0; j < matrix[i].length; j++){
			if (matrix[i][j] > ans)ans = matrix[i][j];
		}
	}
	return ans;
  }
  
  public int getColTotal(int col){
	int ans = 0;
	for (int i = 0; i < matrix.length;i++) ans += matrix[i][col];
	return ans;
  }
  
  public void reverseRows(){
	print();
	for (int i = 0; i <matrix.length; i++){
		for (int j= 0; j < matrix[i].length/2; j++){
			int temp = matrix[i][matrix[i].length-1-j];
			matrix[i][matrix[i].length-1-j] = matrix[i][j];
			matrix[i][j] = temp;
		}
	}
	print();
  }
    /** Method to test getCount*/
  public static void testGetCount()
  {
    IntArrayWorker worker = new IntArrayWorker();
    int[][] nums = new int[3][4];
    worker.setMatrix(nums);
    worker.fillPattern1();
    int count = worker.getCount(1);
    System.out.println("Count should be 6 and count is " + count);
  }
  
    /** Method to test getLargest */
  public static void testGetLargest()
  { // test when largest is last
    IntArrayWorker worker = new IntArrayWorker();
    int [][] nums2 = {{1, 2, 3}, {4, 5, 6}};
    worker.setMatrix(nums2);
    int largest = worker.getLargest();
    System.out.println("Largest should be 6 and is " + largest); 
    // test when largest is first
    int[][] nums3 = {{6, 2, 3}, {4, 5, 1}};
    worker.setMatrix(nums3);
    largest = worker.getLargest();
    System.out.println("Largest should be 6 and is " + largest); 
    // test when largest is in the middle
    int[][] nums4 = {{1, 2, 3}, {6, 5, 1}};
    worker.setMatrix(nums4);
    largest = worker.getLargest();
    System.out.println("Largest should be 6 and is " + largest);
    // test when duplicate largest
    int[][] nums5 = {{6, 2, 6}, {4, 5, 1}};//    worker.setMatrix(nums5);
    largest = worker.getLargest();
    System.out.println("Largest should be 6 and is " + largest);
  }
  
    /** Method to test getColTotal */
  public static void testGetColTotal()
  {
    IntArrayWorker worker = new IntArrayWorker();
    int [][] nums2 = {{1, 2, 3}, {4, 5, 6}};
    worker.setMatrix(nums2);
    int total = worker.getColTotal(0);
    System.out.println("Total for column 0 should be 5 and is " + total);
    total = worker.getColTotal(1);
    System.out.println("Total for column 1 should be 7 and is " + total);
    total = worker.getColTotal(2);
    System.out.println("Total for column 2 should be 9 and is " + total);
  }
 
}
