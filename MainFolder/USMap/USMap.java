/*Draws USMap cities
 * Harly Liu
 * 9/4/2024
 */

import java.util.Scanner;
import java.awt.Color;

public class USMap{
	private Scanner infile;
	private Scanner inFileBigCity;
	private String[] bigcityNames = new String[276];
	private int[] bigcityPop = new int[276];
	private int amt = 0;
	
	 /**
     * main
     * @param  args
     * @return none
     */
	public static void main(String args[]){
		USMap usmap = new USMap();
		usmap.run();
		
	}
	
	/**
     * reads in the big cities and creates all scanners.
     *  calls create window
     * @param  args
     * @return none
     */
	public void run(){
		infile = FileUtils.openToRead("bigcities.txt");
		while(infile.hasNext()){
			String row = infile.nextLine();
			row = row.substring(row.indexOf(" ")+1);
			int population = Integer.parseInt(row.substring(row.lastIndexOf(" ")+1));
			String name = row.substring(0, row.lastIndexOf(" "));
			bigcityNames[amt] = name;
			bigcityPop[amt] = population;
			amt++;
		}	
		infile = FileUtils.openToRead("cities.txt");
		setupCanvas();
	}
	
	/**
     * sets up window and draws map. draws all small cities first and 
     * then draws large cities
     * @param  args
     * @return none
     */
	public void setupCanvas() {
		StdDraw.setTitle("USMap");
		StdDraw.setCanvasSize(900, 512);
		StdDraw.setXscale(128.0, 65.0);
		StdDraw.setYscale(22.0, 52.0);
		String[] citynames = new String[100000];
		double[] cityx = new double[100000];
		double[] cityy = new double[100000];
		int city = 0;
		while(infile.hasNext()){
			StdDraw.setPenColor(Color.GRAY);
			StdDraw.setPenRadius(0.005);
			double x = infile.nextDouble();
			double y = infile.nextDouble();
			String citName = infile.nextLine();
			cityx[city] = x;
			cityy[city] = y;
			citynames[city] = citName;
			StdDraw.point(y,x);
			city++;
		}
		
		String prev = "";
		for (int j = 0; j < city; j++){
			double y = cityy[j];
			double x = cityx[j];
			String citName = citynames[j];
			boolean isBigCity = false;
			for (int i = 0; i < amt; i++){
				if (citName.trim().equals(prev.trim())){
					break;
				}
				if (citName.trim().equals(bigcityNames[i].trim())){
					isBigCity = true;
					//System.out.println(bigcityNames[i]);
					//System.out.println(bigcityPop[i]);
					if (i <= 9){
						StdDraw.setPenColor(StdDraw.RED);
					}
					else{
						StdDraw.setPenColor(StdDraw.BLUE);
					}
					//System.out.println(0.6 * (Math.sqrt(bigcityPop[i])/18500));
					StdDraw.setPenRadius(0.6 * (Math.sqrt(bigcityPop[i])/18500));
					StdDraw.point(y,x);
					//StdDraw.filledCircle(y,x, (Math.sqrt(bigcityPop[i])/18500));
					break;
				}
			}
			if(!isBigCity){
				StdDraw.setPenColor(Color.GRAY);
				StdDraw.setPenRadius(0.005);
				StdDraw.point(y,x);
			}
			prev = citName;
		}
	}
}
