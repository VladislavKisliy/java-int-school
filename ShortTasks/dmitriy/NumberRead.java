import java.io.*;
import java.util.*; 

public class NumberRead {
	
	private static String readFile(String filename) throws IOException {
	    BufferedReader reader = new BufferedReader(new FileReader (filename));
	    String line = null;
	    StringBuilder sb = new StringBuilder();
	    while((line=reader.readLine()) != null ) {
	        sb.append(line+" ");	        
	    }	    
	    reader.close();	    
	    return sb.toString();
	}
	
	public static void main(String[] args) throws IOException {
		String filename = "C:/Work Docs/somefile.txt";
		String filestring = readFile(filename).trim();
		List<String> strNum = Arrays.asList(filestring.split("\\s+"));
		NavigableSet<Double> numbers = new TreeSet<Double>();
		for (String s : strNum)
			numbers.add(Double.parseDouble(s));
		
		Scanner user_input = new Scanner( System.in );
		System.out.print("Enter integer value, do it:");
	    int i = 0;
		try {
			i = user_input.nextInt();
	    } catch (InputMismatchException ex) {
	       System.out.println("Not a number!");
	       return;
	    }   		
	
		System.out.println("Nearest smaller value is "+numbers.lower(Double.valueOf(i)));
		System.out.println("Nearest larger value is "+numbers.higher(Double.valueOf(i)));

	}
}
