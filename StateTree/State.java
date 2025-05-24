/**
 *	The object to store US state information.
 *
 *	@author	
 *	@since	
 */
public class State implements Comparable<State>
{
	private String name;			// state name
	private String abbreviation;	// state abbreviation
	private int population;			// state population
	private int area;				// state area in sq miles
	private int reps;				// number of House Reps
	private String capital;			// state capital city
	private int month;				// month joined Union
	private int day;				// day joined Union
	private int year;				// year joined Union
	
	public State(String nameIn, String abrIn, int popIn, int arIn, int repsIn,
		String capIn, int monIn, int dayIn, int yrIn) {
		name = nameIn;
		abbreviation = abrIn;
		population = popIn;
		area = arIn;
		reps = repsIn;
		capital = capIn;
		month = monIn;
		day = dayIn;
		year = yrIn;
	}
	
	@Override
	public int compareTo(State other) {
		return name.compareTo(other.name); 
	}
	
	public String getName ( ){	
		return name; 
	}
	public String getAbbreviation ( ){	
		return abbreviation; 
	}
	public int getPopulation ( ){	
		return population; 
	}
	public int getArea ( ){	
		return area; 
	}
	public int getReps ( ){	
		return reps; 
	}
	public String getCapital ( ){	
		return capital; 
	}
	public int getMonth ( ){	
		return month; 
	}
	public int getDay ( ){	
		return day; 
	}
	public int getYear ( ){	
		return year; 
	}
	
	
	
	@Override
	public String toString() {	
		return String.format("%-20s %-5s %-10d %-10d %-5d %-20s %-5d %-5d %d\n",
			name, abbreviation, population, area, reps, capital, month, day, year);
    }
}
