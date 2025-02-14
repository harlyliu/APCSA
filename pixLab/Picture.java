import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu and Harly Liu
 * @since 2/14/2025
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
	/**
	* Constructor that takes no arguments 
	*/
	public Picture ()
	{
	/* not needed but use it to show students the implicit call to super()
	 * child constructors always call a parent constructor 
	 */
	super();  
	}

	/**
	* Constructor that takes a file name and creates the picture 
	* @param fileName the name of the file to create the picture from
	*/
	public Picture(String fileName)
	{
	// let the parent class handle this fileName
	super(fileName);
	}

	/**
	* Constructor that takes the width and height
	* @param height the height of the desired picture
	* @param width the width of the desired picture
	*/
	public Picture(int height, int width)
	{
	// let the parent class handle this width and height
	super(width,height);
	}

	/**
	* Constructor that takes a picture and creates a 
	* copy of that picture
	* @param copyPicture the picture to copy
	*/
	public Picture(Picture copyPicture)
	{
	// let the parent class do the copy
	super(copyPicture);
	}

	/**
	* Constructor that takes a buffered image
	* @param image the buffered image to use
	*/
	public Picture(BufferedImage image)
	{
	super(image);
	}

	////////////////////// methods ///////////////////////////////////////

	/**
	* Method to return a string with information about this picture.
	* @return a string with information about the picture such as fileName,
	* height and width.
	*/
	public String toString()
	{
	String output = "Picture, filename " + getFileName() + 
	  " height " + getHeight() 
	  + " width " + getWidth();
	return output;

	}

	/** Method to set the blue to 0 */
	public void zeroBlue()
	{
	Pixel[][] pixels = this.getPixels2D();
	for (Pixel[] rowArray : pixels)
	{
	  for (Pixel pixelObj : rowArray)
	  {
		pixelObj.setBlue(0);
	  }
	}
	}

	/** Method that mirrors the picture around a 
	* vertical mirror in the center of the picture
	* from left to right */
	public void mirrorVertical()
	{
	Pixel[][] pixels = this.getPixels2D();
	Pixel leftPixel = null;
	Pixel rightPixel = null;
	int width = pixels[0].length;
	for (int row = 0; row < pixels.length; row++)
	{
	  for (int col = 0; col < width / 2; col++)
	  {
		leftPixel = pixels[row][col];
		rightPixel = pixels[row][width - 1 - col];
		rightPixel.setColor(leftPixel.getColor());
	  }
	} 
	}

	/** Mirror just part of a picture of a temple */
	public void mirrorTemple()
	{
	int mirrorPoint = 276;
	Pixel leftPixel = null;
	Pixel rightPixel = null;
	int count = 0;
	Pixel[][] pixels = this.getPixels2D();

	// loop through the rows
	for (int row = 27; row < 97; row++)
	{
	  // loop from 13 to just before the mirror point
	  for (int col = 13; col < mirrorPoint; col++)
	  {
		
		leftPixel = pixels[row][col];      
		rightPixel = pixels[row]                       
						 [mirrorPoint - col + mirrorPoint];
		rightPixel.setColor(leftPixel.getColor());
	  }
	}
	}

	/** copy from the passed fromPic to the
	* specified startRow and startCol in the
	* current picture
	* @param fromPic the picture to copy from
	* @param startRow the start row to copy to
	* @param startCol the start col to copy to
	*/
	public void copy(Picture fromPic, 
				 int startRow, int startCol)
	{
		Pixel fromPixel = null;
		Pixel toPixel = null;
		Pixel[][] toPixels = this.getPixels2D();
		Pixel[][] fromPixels = fromPic.getPixels2D();
		for (int fromRow = 0, toRow = startRow; 
			 fromRow < fromPixels.length &&
			 toRow < toPixels.length; 
			 fromRow++, toRow++)
		{
		  for (int fromCol = 0, toCol = startCol; 
			   fromCol < fromPixels[0].length &&
			   toCol < toPixels[0].length;  
			   fromCol++, toCol++)
		  {
			fromPixel = fromPixels[fromRow][fromCol];
			toPixel = toPixels[toRow][toCol];
			toPixel.setColor(fromPixel.getColor());
		  }
		}   
	}

	/** Method to create a collage of several pictures */
	public void createCollage()
	{
		Picture flower1 = new Picture("flower1.jpg");
		Picture flower2 = new Picture("flower2.jpg");
		this.copy(flower1,0,0);
		this.copy(flower2,100,0);
		this.copy(flower1,200,0);
		Picture flowerNoBlue = new Picture(flower2);
		flowerNoBlue.zeroBlue();
		this.copy(flowerNoBlue,300,0);
		this.copy(flower1,400,0);
		this.copy(flower2,500,0);
		this.mirrorVertical();
		this.write("collage.jpg");
	}


	/** Method to show large changes in color 
	* @param edgeDist the distance for finding edges
	*/
	public void edgeDetection(int edgeDist)
	{
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		Pixel[][] pixels = this.getPixels2D();
		Color rightColor = null;
		for (int row = 0; row < pixels.length; row++)
		{
		  for (int col = 0; 
			   col < pixels[0].length-1; col++)
		  {
			leftPixel = pixels[row][col];
			rightPixel = pixels[row][col+1];
			rightColor = rightPixel.getColor();
			if (leftPixel.colorDistance(rightColor) > 
				edgeDist)
			  leftPixel.setColor(Color.BLACK);
			else
			  leftPixel.setColor(Color.WHITE);
		  }
		}
	}

	public void keepOnlyBlue(){
		Pixel[][] pixels = this.getPixels2D();
		for (int row = 0; row < pixels.length; row++){
			for (int col = 0; col < pixels[0].length; col++){
				pixels[row][col].setGreen(0);
				pixels[row][col].setRed(0);
			}
		}
	}

	public void negate(){
		Pixel[][] pixels = this.getPixels2D();
		for (int row = 0; row < pixels.length; row++){
			for (int col = 0; col < pixels[0].length; col++){
				pixels[row][col].setGreen(255-pixels[row][col].getGreen());
				pixels[row][col].setRed(255-pixels[row][col].getRed());
				pixels[row][col].setBlue(255-pixels[row][col].getBlue());
			}
		}
	}

	public void grayScale(){
		Pixel[][] pixels = this.getPixels2D();
		for (int row = 0; row < pixels.length; row++){
			for (int col = 0; col < pixels[0].length; col++){
				Pixel currPixel = pixels[row][col];
				int ave = (currPixel.getRed() + currPixel.getGreen() + currPixel.getRed())/3;
				pixels[row][col].setGreen(ave);
				pixels[row][col].setRed(ave);
				pixels[row][col].setBlue(ave);
			}
		}
	  }
	   /** To pixelate by dividing area into size x size.
	  * @param size Side length of square area to pixelate.
	  */
	public void pixelate(int size) {
		Pixel[][] pixels = this.getPixels2D();
		int sumRed,sumGreen,sumBlue,sumSquares, aveRed, aveGreen, aveBlue, amtSquares;
		for (int row = 0; row < pixels.length; row += size){
			for (int col = 0; col < pixels[0].length; col+=size){
				sumRed = 0;
				sumGreen = 0;
				sumBlue = 0;
				amtSquares = 0;
				for (int i = row; i < Math.min(row + size, pixels.length); i++){
					for (int j= col; j < Math.min(col + size, pixels[row].length);j++){
						sumRed += pixels[i][j].getRed();
						sumGreen += pixels[i][j].getGreen();
						sumBlue += pixels[i][j].getBlue();
						amtSquares++;
					}
				}
				aveRed = sumRed/(amtSquares);
				aveGreen = sumGreen/(amtSquares);
				aveBlue = sumBlue/(amtSquares);
				for (int i = row; i < Math.min(row + size, pixels.length); i++){
					for (int j= col; j < Math.min(col + size, pixels[row].length);j++){
						pixels[i][j].setRed(aveRed);
						pixels[i][j].setGreen(aveGreen);
						pixels[i][j].setBlue(aveBlue);
					}
				}
			}
		}
	}  

	 /** Method that blurs the picture
	 * @param size Blur size, greater is more blur
	 * @return Blurred picture
	 */
	public Picture blur(int size)
	{
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(this);
		Pixel[][] resultPixels = result.getPixels2D();
		int sumRed,sumGreen,sumBlue,sumSquares, aveRed, aveGreen, aveBlue, amtSquares;
		for (int row = 0; row < pixels.length; row ++){
			for (int col = 0; col < pixels[0].length; col++){
				sumRed = 0;
				sumGreen = 0;
				sumBlue = 0;
				amtSquares = 0;
				for (int i = Math.max(0,row-size/2); 
					i < Math.min(row + size/2, pixels.length); i++){
					for (int j= Math.max(0,col-size/2); 
						j < Math.min(col + size/2, pixels[row].length);j++){
						sumRed += pixels[i][j].getRed();
						sumGreen += pixels[i][j].getGreen();
						sumBlue += pixels[i][j].getBlue();
						amtSquares++;
					}
				}
				aveRed = sumRed/(amtSquares);
				aveGreen = sumGreen/(amtSquares);
				aveBlue = sumBlue/(amtSquares);
				resultPixels[row][col].setRed(aveRed);
				resultPixels[row][col].setGreen(aveGreen);
				resultPixels[row][col].setBlue(aveBlue);
			}
		}
		return result;
	 }
	 
	  /** Method that enhances a picture by getting average Color around
	 * a pixel then applies the following formula:
	 *
	 * pixelColor <- 2 * currentValue - averageValue
	 *
	 * size is the area to sample for blur.
	 *
	 * @param size Larger means more area to average around pixel
	 * and longer compute time.
	 * @return enhanced picture
	 */
	 public Picture enhance(int size)
	 {
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(this);
		Pixel[][] resultPixels = result.getPixels2D();
		int sumRed,sumGreen,sumBlue,sumSquares, aveRed, aveGreen, aveBlue, amtSquares;
		for (int row = 0; row < pixels.length; row ++){
			for (int col = 0; col < pixels[0].length; col++){
				sumRed = 0;
				sumGreen = 0;
				sumBlue = 0;
				amtSquares = 0;
				for (int i = Math.max(0,row-size/2); 
					i < Math.min(row + size/2, pixels.length); i++){
					for (int j= Math.max(0,col-size/2); 
						j < Math.min(col + size/2, pixels[row].length);j++){
						sumRed += pixels[i][j].getRed();
						sumGreen += pixels[i][j].getGreen();
						sumBlue += pixels[i][j].getBlue();
						amtSquares++;
					}
				}
				aveRed = 2*pixels[row][col].getRed()-sumRed/(amtSquares);
				aveGreen = 2*pixels[row][col].getGreen()-sumGreen/(amtSquares);
				aveBlue = 2*pixels[row][col].getBlue()-sumBlue/(amtSquares);
				resultPixels[row][col].setRed(aveRed);
				resultPixels[row][col].setGreen(aveGreen);
				resultPixels[row][col].setBlue(aveBlue);
			}
		}
		return result;
	}

	/* swaps the left and right side of the picture
	* 
	*/
	public Picture swapLeftRight(){
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(this);
		Pixel[][] resultPixels = result.getPixels2D();
		for (int row = 0; row < pixels.length; row ++){
			for (int col = 0; col < pixels[0].length; col++){
				resultPixels[row][(col + pixels[0].length/2)%pixels[0].length]
				.setColor(pixels[row][col].getColor());
			}
		}
		return result;
	}
	 
	/* shifts each row of the picture to create a stair like patter
	 * @param shiftCount The number of pixels to shift to the right
	 * @param steps The number of steps
	 * @return The picture with pixels shifted in stair steps
	 */
	 public Picture stairStep(int shiftCount, int steps) {
			Pixel[][] pixels = this.getPixels2D();
			Picture result = new Picture(this);
			Pixel[][] resultPixels = result.getPixels2D();
			for (int row = 0; row < pixels.length; row ++){
				for (int col = 0; col < pixels[0].length; col++){
					resultPixels[row][(col+(row/(pixels.length/steps))*shiftCount)%pixels[0].length]
					.setColor(pixels[row][col].getColor());
				}
			}
			return result;
	 }

	 /*distort the picture into a gaussian curve
	 * @param maxFactor Max height (shift) of curve in pixels
	 * @return Liquified picture
	 */
	public Picture liquify(int maxHeight){
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(this);
		
		Pixel[][] resultPixels = result.getPixels2D();
		for (int row = 0; row < pixels.length; row ++){
			for (int col = 0; col < pixels[0].length; col++){
				double exponent = Math.pow(row - getHeight() / 2.0, 2) / (2.0 * Math.pow(getHeight()/5, 2));
				int rightShift = (int)(maxHeight * Math.exp(- exponent)); 
				resultPixels[row][(col+rightShift)%pixels[0].length]
				.setColor(pixels[row][col].getColor());
			}
		}
		return result;

	}
	/*  distort the picture into a sine function
	* @param amplitude  The maximum shift of pixels    
	* @return Wavy picture    */   
	public Picture wavy(int amplitude) {
		Pixel[][] pixels = this.getPixels2D();  
		Picture result = new Picture(this);
		Pixel[][] resultPixels = result.getPixels2D(); 
		for (int row = 0; row < pixels.length; row++){
			for (int col = 0; col < pixels[row].length; col++){
				resultPixels[row][(int)(col+pixels[row].length + 
						amplitude * Math.sin(2*Math.PI *row/200))%
						pixels[row].length].setColor(pixels[row][col].getColor());
			}
		}
		return result;
	}
	
	/** Method that creates an edge detected black/white picture
	* @param threshold threshold as determined by Pixel’s colorDistance method
	* @return edge detected picture
	*/
	public Picture edgeDetectionBelow(int threshold)
	{ 
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] resultPixels = result.getPixels2D(); 
		Pixel abovePixel = null;
		Pixel belowPixel = null;
		Color belowColor = null;
		for (int row = 0; row < pixels.length-1; row++)
		{
		  for (int col = 0; 
			   col < pixels[0].length; col++)
		  {
			abovePixel = pixels[row][col];
			belowPixel = pixels[row+1][col];
			belowColor = belowPixel.getColor();
			if (abovePixel.colorDistance(belowColor) > 
				threshold)
			  abovePixel.setColor(Color.BLACK);
			else
			  abovePixel.setColor(Color.WHITE);
		  }
		}
		return result;
	}
	 
	 
	public boolean isGreen(Color color){
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();
		if(green > red*1.2 && green > blue*1.2) return true;
		return false; 
	}
	/** Method that creates a green screen picture
	 * @return green screen picture
	 */
	 public Picture greenScreen()
	 {
		 // Get background picture
		 Picture bkgnd = new Picture("images/greenScreenImages/IndoorHouseLibraryBackground.jpg");
		 Pixel[][] bkgndPixels = bkgnd.getPixels2D();
		 // Get cat picture
		 Picture cat = new Picture("images/greenScreenImages/kitten1GreenScreen.jpg");
		 Pixel[][] catPixels = cat.getPixels2D();
		 // Get mouse picture
		 Picture mouse = new Picture("images/greenScreenImages/mouse1GreenScreen.jpg");
		 Pixel[][] mousePixels = mouse.getPixels2D();
		 for (int i = 0; i < mousePixels.length; i+=3){
			for (int j = 0; j < mousePixels[0].length; j+=3){
				if(!isGreen(mousePixels[i][j].getColor())){
					bkgndPixels[i/3+350][j/3+300].setColor(mousePixels[i][j].getColor());
				}
			}
		 }
		 for (int i = 0; i < catPixels.length; i+=2){
			for (int j = 0; j < catPixels[0].length; j+=2){
				if(!isGreen(catPixels[i][j].getColor())){
					bkgndPixels[i/2+430][j/2+450].setColor(catPixels[i][j].getColor());
				}
			}
		 }
		 return bkgnd;
		 
	}
	/* Main method for testing - each class in Java can have a main 
	* method 
	*/
	public static void main(String[] args) 
	{
		Picture beach = new Picture("images/beach.jpg");
		beach.explore();
		beach.zeroBlue();
		beach.explore();
	}
	


} // this } is the end of class Picture, put all new methods before this
	
	
  
