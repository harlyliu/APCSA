public class Identifier{
	private String name;
	private double value;
	
	public Identifier(String s, double n){
		name = s;
		value = n;
	}
	
	public void setName(String s){ name = s; }
	
	public void setValue(double n){ value = n; }
		
	public String getName(){ return name; }
			
	public double getValue(){ return value; }
} 
