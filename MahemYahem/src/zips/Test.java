package zips;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Test {
	public static void main(String[] args) { 
		ZipCalculator z = new ZipCalculator(); 
		ZipCalculator.print(z.nearbyZips("20878", 5));
	}


}
