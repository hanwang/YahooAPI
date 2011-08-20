import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;


public class YahooAPI {
	int startMonth;
	int startDay;
	int startYear;
	
	int endMonth;
	int endDay;
	int endYear;
	
	String freq;
	
	public ArrayList<Double> getClose(String symbol){
		String baseUrl = "http://ichart.finance.yahoo.com/table.csv?ignore=.csv";
		baseUrl += "&s=" + symbol;
		baseUrl += "&a=" + startMonth;
		baseUrl += "&b=" + startDay;
		baseUrl += "&c=" + startYear;
		baseUrl += "&d=" + endMonth;
		baseUrl += "&e=" + endDay;
		baseUrl += "&f=" + endYear;
		baseUrl += "&g=" + freq;
		
		URL url;
		
		ArrayList<Double> close = new ArrayList<Double>(); 
		
		System.out.print("Opening URL: ");
		System.out.print(baseUrl);
		System.out.println(" ");
		
		try {
			url = new URL(baseUrl);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			
			in.readLine(); //Forward Header
			
			while (true){
				String thisLine = in.readLine();
				if (thisLine == null){
					break;
				}
				String[] separatedLine = thisLine.split("[,X]"); // split by commas
				close.add(Double.parseDouble(separatedLine[4]));
			}
			
			return close;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String args[]){
		YahooAPI y = new YahooAPI();
		y.startDay = 3;
		y.startMonth = 2; //0 is jan, so 2 is march
		y.startYear = 2011;
		
		y.endDay = 20;
		y.endMonth = 2; //0 is jan, so 2 is march
		y.endYear = 2011;
		
		y.freq = "d"; // daily frequency, w for weekly, m for monthly
		
		ArrayList<Double> close = y.getClose("GE");
		
		Iterator<Double> iter = close.iterator();
		
		System.out.println("Returned Close Values:");
		while (iter.hasNext()){
			System.out.println(iter.next());
		}
		
	}
}
