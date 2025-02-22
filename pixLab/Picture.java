/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture caterpillar = new Picture("images/caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVertical();
    caterpillar.explore();
  }
  
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("images/temple.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
  }
  
  /** Method to test the collage method */
  public static void testCollage()
  {
    Picture canvas = new Picture("images/640x480.jpg");
    canvas.createCollage();
    canvas.explore();
  }
  
  /** Method to test edgeDetection */
  public static void testEdgeDetection()
  {
    Picture swan = new Picture("images/swan.jpg");
    swan.edgeDetection(10);
    swan.explore();
  }
  
   public static void testNegate(){
	Picture temple = new Picture("images/temple.jpg");
	temple.explore();
	temple.negate();
	temple.explore();
  }
  public static void testKeepOnlyBlue(){
	Picture temple = new Picture("images/temple.jpg");
	temple.explore();
	temple.keepOnlyBlue();
	temple.explore();
  }
  public static void testGrayscale(){
	Picture temple = new Picture("images/temple.jpg");
	temple.explore();
	temple.grayScale();
	temple.explore();
  }
  
   public static void testPixelate(){
	Picture beach = new Picture("images/beach.jpg");
	beach.explore();
	beach.pixelate(10);
	beach.explore();
	beach = new Picture("images/beach.jpg");
	beach.pixelate(20);
	beach.explore();
	beach = new Picture("images/beach.jpg");
	beach.pixelate(30);
	beach.explore();
  }
  
  public static void testBlur(){
	Picture beach = new Picture("images/beach.jpg");
	beach.explore();
	Picture newBeach = beach.blur(11);
	newBeach.explore();
  }
  
   public static void testEnhance(){
	Picture beach = new Picture("images/beach.jpg");
	beach.explore();
	Picture newBeach = beach.enhance(11);
	newBeach.explore();
  }
  
   public static void testSwapLeftRight(){
	Picture beach = new Picture("images/beach.jpg");
	beach.explore();
	Picture newBeach = beach.swapLeftRight();
	newBeach.explore();
  }
  
   public static void testStairStep(){
	Picture beach = new Picture("images/beach.jpg");
	beach.explore();
	Picture newBeach = beach.stairStep(10,10);
	newBeach.explore();
	newBeach = beach.stairStep(1,400);
	newBeach.explore();
  }
  
   public static void testLiquify(){
	Picture beach = new Picture("images/beach.jpg");
	beach.explore();
	Picture newBeach = beach.liquify(400);
	newBeach.explore();
  }
  
  public static void testWavy(){
	Picture beach = new Picture("images/beach.jpg");
	beach.explore();
	Picture newBeach = beach.wavy(50);
	newBeach.explore();
  }
  
  public static void testGreenScreen(){
	Picture pic = new Picture("images/beach.jpg");
	pic = pic.greenScreen();
	pic.explore();
  }
  
  public static void testEdgeDetectionBelow(){
	Picture swan = new Picture("images/swan.jpg");
	swan.explore();
	swan = swan.edgeDetectionBelow(10);
	swan.explore();
  }
  
  public static void testRotate(){
	Picture beach = new Picture("images/beach.jpg");
	beach.explore();
	Picture rotatedBeach = beach.rotate(Math.PI/4);
	rotatedBeach.explore();
  }
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
    testRotate();
    //testEdgeDetectionBelow();
    //testGreenScreen();
    //testPixelate();
    //testBlur();
    //testEnhance();
    //testSwapLeftRight();
    //testLiquify();
    //testWavy();
    //testStairStep();
    //testZeroBlue();
    //testKeepOnlyBlue();
    //testKeepOnlyRed();
    //testKeepOnlyGreen();
    //testNegate();
    //testGrayscale();
    //testFixUnderwater();
    //testMirrorVertical();
    //testMirrorTemple();
    //testMirrorArms();
    //testMirrorGull();
    //testMirrorDiagonal();
    //testCollage();
    //testCopy();
    //testEdgeDetection();
    //testEdgeDetection2();
    //testChromakey();
    //testEncodeAndDecode();
    //testGetCountRedOverValue(250);
    //testSetRedToHalfValueInTopHalf();
    //testClearBlueOverValue(200);
    //testGetAverageForColumn(0);
  }
}
