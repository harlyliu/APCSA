/**
 *	FirstAssignment.java
 *	Display a brief description of your summer vacation on the screen.
 *
 *	To compile Linux:	javac -cp .:mvAcm.jar FirstAssignment.java
 *	To execute Linux:	java -cp .:mvAcm.jar FirstAssignment
 *
 *	To compile MS Powershell:	javac -cp ".;mvAcm.jar" FirstAssignment.java
 *	To execute MS Powershell:	java -cp ".;mvAcm.jar" FirstAssignment
 *
 *	@author	Your name
 *	@since	Today's date
 */
import java.awt.Font;

import acm.program.GraphicsProgram;
import acm.graphics.GLabel;

public class FirstAssignment extends GraphicsProgram {
    
    public void run() {
    	//	The font to be used
    	Font f = new Font("Serif", Font.BOLD, 18);
    	
    	//	Line 1
    	GLabel s1 = new GLabel("What I did on my summer vacation ...", 10, 20);
    	s1.setFont(f);
    	add(s1);
    	//	Continue adding lines until you have 12 to 15 lines
    	
    	GLabel s2 = new GLabel("In the first week of summer, I stayed home and enjoyed life.", 10, 40);
    	s2.setFont(f);
    	add(s2);
    	GLabel s3 = new GLabel("I did a lot of coding, practiced for SAT, practiced coding, and studied calc", 10, 60);
    	s3.setFont(f);
    	add(s3);
    	GLabel s4 = new GLabel("The next week I went to Bamf in canada", 10, 80);
    	s4.setFont(f);
    	add(s4);
    	GLabel s5 = new GLabel("We stayed in Banf village itself and we visited nearby sights", 10, 100);
    	s5.setFont(f);
    	add(s5);
    	GLabel s6 = new GLabel("Lake Louise was really beautiful. There was also a really blue lake called Lake Peyto", 10, 120);
    	s6.setFont(f);
    	add(s6);
    	GLabel s7 = new GLabel("We traveled North to Jasper where we say a lot of bears and other animals.", 10, 140);
    	s7.setFont(f);
    	add(s7);
    	GLabel s8 = new GLabel("The next few weeks, my aunt, uncle and cousins came to visit", 10, 160);
    	s8.setFont(f);
    	add(s8);
    	GLabel s9 = new GLabel("We went to visit Santa Cruz beach.", 10, 180);
    	s9.setFont(f);
    	add(s9);
    	GLabel s10 = new GLabel("They went to camp, while I stayed home and did my work.", 10, 200);
    	s10.setFont(f);
    	add(s10);
    	GLabel s11 = new GLabel("I also started a podcast on a bet with a friend", 10, 220);
    	s11.setFont(f);
    	add(s11);
    	GLabel s12 = new GLabel("Then I went to coding camp for a week.", 10, 240);
    	s12.setFont(f);
    	add(s12);
    	GLabel s13 = new GLabel("I prepared for USACO. I hope I pass silver this year.", 10, 260);
    	s13.setFont(f);
    	add(s13);
    	GLabel s14 = new GLabel("For the last two weeks I went to debate camp in UCLA", 10, 280);
    	s14.setFont(f);
    	add(s14);
    	GLabel s15 = new GLabel("Overall, I had a great summer. ", 10, 300);
    	s15.setFont(f);
    	add(s15);
    }
    
}
