package zips;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ZipCalculator {
	ArrayList<ZipCode> zips; 
	
	public ZipCalculator() { 
		File file= new File("zip.csv");
		Scanner inputStream;
		zips = new ArrayList<ZipCode>();
		try{
			inputStream = new Scanner(file);

			while(inputStream.hasNext()){
				String line= inputStream.next();
				String[] values = line.split(",");
				zips.add(new ZipCode(values[0], Double.valueOf(values[1]), Double.valueOf(values[2])));
			}

			inputStream.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	public ArrayList<String> nearbyZips(String zip, double d){ 
		double x = 0; 
		double y = 0;
		ArrayList<String> nearZips = new ArrayList<String>();
		for (int i = 0; i < zips.size(); i++) { 
			if (zips.get(i).getZip().equals(zip)) { 
				x = zips.get(i).getLat();
				y = zips.get(i).getLongi();
			}
		}
		for (int i = 0; i < zips.size(); i++) {
			if (distance(zips.get(i).getLat(), x ,zips.get(i).getLongi(), y) < d && zips.get(i).getZip() != zip) { 
				nearZips.add(zips.get(i).getZip());
			}
		}
		return nearZips;
	}
	private double distance(double lat1, double lat2, double lon1, double lon2) {

	    final int R = 6371; // Radius of the earth

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c / 1.609; // convert to miles

	    return distance;
	}
	public static void print(ArrayList<String> a) { 
		for (int i = 0; i < a.size(); i++) { 
			System.out.println(a.get(i));
		}
	}

}
