package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scrape {
	
	String zip;
	
	public static void main(String[] args) throws IOException {
		Scrape test = new Scrape("20878");
		//System.out.println(test.getUtilities());
		System.out.println(test.getContaminants("https://www.ewg.org/tapwater/system.php?pws=MD0150002"));
	}
	
	public Scrape(String zip) {
		this.zip = zip;
	}
	
	public ArrayList<String[]> getUtilities() throws IOException {
		String url = "https://www.ewg.org/tapwater/search-results.php?zip5=" + zip + "&searchtype=zip";
		Document doc = Jsoup.connect(url).get();
		Element body = doc.body();
		Element wrapper = body.getElementsByClass("flexbox-column-wrapper").first();
		Element figure = wrapper.getElementsByClass("search-results-figure").first();
		Element table = figure.getElementsByClass("search-results-table").first();
		Elements entries = table.getElementsByTag("tbody").first().children();
		ArrayList<String[]> utilities = new ArrayList<>();
		boolean done = false;
		int i = 0;
		while(!done) {
			try {
				Element info = entries.eq(i).first();
				Elements children = info.children();
				String[] arr = new String[4];
				String element1 = children.eq(0).first().toString();
				String pattern1 = "\"(.*)\">(.*)</a>";
				Pattern r1 = Pattern.compile(pattern1);
				Matcher m1 = r1.matcher(element1);
				if(m1.find()) {
					arr[3] = "https://www.ewg.org/tapwater/" + m1.group(1);
					arr[0] = m1.group(2);
				}
				String element2 = children.eq(1).first().toString();
				String pattern2 = ">(.*)<";
				Pattern r2 = Pattern.compile(pattern2);
				Matcher m2 = r2.matcher(element2);
				if(m2.find()) {
					arr[1] = m2.group(1);
				}
				String element3 = children.eq(2).first().toString();
				String pattern3 = ">\\s(.*)<\\/td>";
				Pattern r3 = Pattern.compile(pattern3);
				Matcher m3 = r3.matcher(element3);
				if(m3.find()) {
					arr[2] = m3.group(1);
				}
				utilities.add(arr);
				//System.out.println(Arrays.toString(arr));
				i++;
			} catch(NullPointerException e) {
				e.printStackTrace();
				done = true;
			}
		}
		return utilities;
	}
	
	public ArrayList<String[]> getContaminants(String url) throws IOException {
		Document doc = Jsoup.connect(url).get();
		Element body = doc.body();
		Element wrapper = body.getElementsByClass("flexbox-column-wrapper").first();
		Element circle = wrapper.getElementsByClass("contaminant-tile-wrapper").first();
		Element flex = circle.getElementsByClass("contaminant-tile flex").first();
		ArrayList<String[]> contaminants = new ArrayList<>();
		Element string1 = flex.children().first();
		String exceedingContaminants = null;
		String pattern1 = ">(.*)<";
		Pattern r1 = Pattern.compile(pattern1);
		Matcher m1 = r1.matcher(string1.toString());
		if(m1.find()) {
			exceedingContaminants = m1.group(1);
		}
		Element string2 = wrapper.getElementsByClass("total-contaminants").first();
		String totalContaminants = null;
		String pattern2 = ">(\\d*) ";
		Pattern r2 = Pattern.compile(pattern2);
		Matcher m2 = r2.matcher(string2.toString());
		if(m2.find()) {
			totalContaminants = m2.group(1);
		}
		String[] contaminantsArr = new String[2];
		contaminantsArr[0] = exceedingContaminants;
		contaminantsArr[1] = totalContaminants;
		contaminants.add(contaminantsArr);
		Element list = wrapper.getElementsByClass("contaminants-list").first();
		Elements grid = list.getElementsByClass("contaminants-grid").first().children();
		boolean done = false;
		int i = 0;
		while(!done) {
			try {
				Element info = grid.eq(i).first();
				Element section;
				try {
					section = info.children().first();
				} catch(NullPointerException e) { 
					break;
				}
				Element contaminant = section.getElementsByClass("contaminant-name").first();
				String[] arr = new String[2];
				Element name = contaminant.getElementsByTag("h3").first();
				String pattern3 = ">(.*)<";
				Pattern r3 = Pattern.compile(pattern3);
				Matcher m3 = r3.matcher(name.toString());
				if(m3.find()) {
					arr[0] = m3.group(1);
				}
				Element multiplier = section.getElementsByClass("detect-times-greater-than").first();
				String pattern4 = ">(.*)x<";
				Pattern r4 = Pattern.compile(pattern4);
				Matcher m4 = r4.matcher(multiplier.toString());
				if(m4.find()) {
					arr[1] = m4.group(1);
				}
				contaminants.add(arr);
				i++;
			} catch(NullPointerException e) {
				e.printStackTrace();
				done = true;
			}
		}
		return contaminants;
		
	}
	
	

}
