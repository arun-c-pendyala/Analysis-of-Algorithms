package algosproject2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LCS_ArunChandra_Pendyala_apendyal {

	
	
	
	public static void main(String[] args) throws IOException{
		
		String outputfile = "output.txt"; 
		FileWriter filew = new FileWriter(outputfile,false);
		
		
		BufferedReader br = new BufferedReader(new FileReader("test10"));
		String line;
		String[] lines = new String[2];
		int k =0;
		int m=0,n=0;
	    
		while( (line = br.readLine() ) != null && k < 2){
			lines[k] = line.trim();
			k++;
			
		}
		if(lines[0].length()> lines[1].length()){ // assign the larger string length as m 
			m = lines[0].length();
			n = lines[1].length();
		}
		else{
			n = lines[0].length();
			m = lines[1].length();
		}
		
		int[][] pi = new int[n+1][m+1];
		
		int[][] opt = new int[n+1][m+1];
		
		for(int j =0; j <= m ; j++){
			opt[0][j] = 0;
			
		}
		//O(n^2) algorithm to obtain length of LCS
	    for(int i=1; i<=n ; i++){
				opt[i][0] = 0;
				for(int j=1 ; j<=m ; j++){
					if(lines[0].charAt(i-1) == lines[1].charAt(j-1)){
						opt[i][j] = opt[i-1][j-1]+1;
						pi[i][j] = 0;   // 0 corresponds to \
						
					}
					else if(opt[i][j-1] >= opt[i-1][j]){
						opt[i][j] = opt[i][j-1];
						pi[i][j] = 1;  // 1 corresponds to <-
						
					}
					else{
						opt[i][j] = opt[i-1][j];
						pi[i][j] = 2;  // 2 corresponds to |
					}
				}
				
				
		}
		//print the longest common subsequence	
	    String res = "";
	    int i = n;
	    int j = m;
	    
	    while(i>0 && j>0){
	    	if(pi[i][j]==0){
	    		res = lines[0].charAt(i-1) + res;
	    		i -= 1;
	    		j -= 1;
	    		
	    	}
	    	else if(pi[i][j]==2){
	    		i -= 1;
	    	}
	    	else{
	    		j -= 1;
	    	}
	    	
	    }
	    System.out.println(res.length());
	    System.out.println(res);
			
	    filew.write(res.length() + "\n");
	    filew.write(res);
	    
	    
	    filew.close();
			
			
			
		
	//Implementation based on the slides of Prof. Shi Li	
		
	}
	
	
}
