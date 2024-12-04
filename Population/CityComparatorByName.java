public class CityComparatorByName implements Comparator<City> {
	public int compare(City city1, City city2){
		int val = city1.getName().compareTo(city2.getName());
		if (val == 0){
			return city1.getPopulation()-city2.getPopulation();
		}
		return val;
	}
}
