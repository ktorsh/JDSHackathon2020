package zips;

public class ZipCode {
	private String zip; 
	private double lat; 
	private double longi;
	
	public ZipCode(String z, double la, double lo) { 
		zip = z; 
		lat = la; 
		longi = lo; 
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLongi() {
		return longi;
	}

	public void setLongi(double longi) {
		this.longi = longi;
	}
	

}
