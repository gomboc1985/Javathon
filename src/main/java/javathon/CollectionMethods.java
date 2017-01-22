package javathon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class CollectionMethods {
	
	public static int extreme(int[] in, boolean max){
		int out = in[0];

		for(int i = 1; i<in.length; i++)
			if(max && in[i] > out)
					out=in[i];
			else if(!max && in[i] < out)
				out = in[i];

		return out;
	}
	
	public static int max(int[] in){
		return extreme(in, true);
	}
	
	public static int min(int[] in){
		return extreme(in, false);
	}
	
	public static int[] sortIt(int[] in){
		int[] out = in.clone();
		Arrays.sort(out);
		return out;
	}
	
	public static boolean[] sortIt(boolean[] in){
		List<Boolean> pre_out = new ArrayList<Boolean>(in.length);
		for(int i = 0; i<in.length; i++)
			pre_out.add(new Boolean(in[i]));
		
		Collections.sort(pre_out);
		boolean[] out = new boolean[in.length];
		for(int i = in.length - 1; i>-1; i--)
			out[in.length - i -1] = pre_out.get(i);
		
		return out;
	}
	
	public static int[] removeNegative(int[] in){
		List<Integer> pre_out = new ArrayList<Integer>();
		for(int i=0; i<in.length; i++)
			if(in[i]>=0)
				pre_out.add(in[i]);
			
		int[] out = new int[pre_out.size()];
		for(int i=0; i<pre_out.size(); i++)
			out[i] = pre_out.get(i);
		return sortIt(out);
	}
	
	public static int count(String[] in, String e){
		int out = 0;
		for(String s : in)
			if(s==e)
				out++;
		return out;
	}
	
	public static int maxRepetitions(String[] in){
		int out = 0;
		int[] tmp = new int[in.length];
		for(String s : in){
			tmp[out] = count(in, s);
			out++;
		}
		out = max(tmp);
		return out;
	}
	
	public static int[] mergeAndSort(int[]... in){
		List<Integer> pre_out = new ArrayList<Integer>();
		for(int[] i : in)
			for(int j : i)
				if(!pre_out.contains(j))
					pre_out.add(j);

		int[] out = new int[pre_out.size()];
		
		for(int i=0; i<pre_out.size(); i++)
			out[i] = pre_out.get(i);
		
		return sortIt(out);
	}

}
