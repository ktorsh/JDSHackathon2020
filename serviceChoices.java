import java.util.ArrayList;

public class serviceChoices {
	
	static ArrayList<String[]> masterArrayList = new ArrayList<String[]>();
	
	serviceChoices(ArrayList<String[]> a){
		masterArrayList = a;
	}
	
	public static ArrayList<String[]> getArray(){
		return masterArrayList;
	}
	
	public static void setArray(ArrayList<String[]> a){
		masterArrayList = new ArrayList<String[]>(a);
	}
	
	
}
