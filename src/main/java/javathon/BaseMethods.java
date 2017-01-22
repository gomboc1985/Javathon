package javathon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BaseMethods {
	
	public static int round(double d) {
		int tmp = (int) d;
		if(d - tmp >= 0.5)
			return tmp + 1;
		else
			return tmp;
	}
	
	public static int round(String d) {
		Scanner s = new Scanner(d);
		s.useDelimiter("[^\\p{Alnum},\\.-]");
		if(s.hasNextInt())
			return s.nextInt();
		else if(s.hasNextDouble())
			return round(s.nextDouble());
		
		return -1;
	}
	
	public static int bitNeeded(int i) {
		return 32 - Integer.numberOfLeadingZeros(i);
	}
	
	public static boolean palindromic(int num){
		String in = Integer.toString(num);
		return in.equals(reverse(in,1));
	}
	
	public static int hex(String i){
		return Integer.parseInt(i, 16);
	}
	
	public static String swapXY(String i){
		StringBuilder out = new StringBuilder();
		for(int j=0; j< i.length(); j++){
			char c = i.charAt(j);
			if(c == 'x' || c == 'X')
				out.append('y');
			else if(c =='y' || c == 'Y')
				out.append('x');
			else
				out.append(c);
		}
		return out.toString().toLowerCase();
	}
	
	public static Number[] findNumbers(String i){
		Scanner s = new Scanner(i);
		List<Number> pre_out = new ArrayList<Number>();
		s.useDelimiter("[^\\p{Digit},\\.[-]*]");
		while (s.hasNext()) {
	        if (s.hasNextInt())
	            pre_out.add(s.nextInt());
	        else if (s.hasNextDouble())
	        	pre_out.add(s.nextDouble());
	        else
	            s.next();
		}
		s.close();
		
		Number[] out = new Number[pre_out.size()];
		for(int j=0; j<pre_out.size(); j++)
			out[j] = pre_out.get(j);
		
		return out;
	}
	
	public static String reverse(String i, int positions){
		int size = i.length();
		int res = size / positions;
		int rem = size % positions;

		StringBuilder out = new StringBuilder();

		for(int j=0; j<rem; j++)
			out.append(i.charAt(size-rem+j));

		for(int j=res; j>0; j--){
			for(int k=0; k<positions; k++){
				int index = (j-1)*positions + k;
				out.append(i.charAt(index));
			}
		}
		
		return out.toString();
	}

}
