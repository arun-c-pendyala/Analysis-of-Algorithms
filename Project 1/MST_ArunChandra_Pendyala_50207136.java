




import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class MST_ArunChandra_Pendyala_50207136{
	
	
	public static int[][] w = null; //weight matrix
	public static int m,n=0;  // n- no. of vertices ,  m - no. of edges
	 
	
	
	public static class Out{
		
		int u=0;
		int pi_u=0;
		int count =0;
		int weight =0 ;
		
		
		int[] pi = new int[n+1];
		
		public void  Out_store(int u_min , int ver){ //this stores {u, pi(u)} combinations
			u = u_min;
			pi[ver] = u_min;
			
		}
		
	
	
	} 
	
    
	
	public static class Heap{
		
	
	int size = 0;
	
	int[] v_heap = new int[n+1];
	HashMap<Integer, Integer> p = new HashMap<Integer,Integer>();
	int[] key = new int[n+1];
	
    public  void Init(int[] v, int[] k ){
		                                      // initialise arrays v and k
		Arrays.fill(v, 0);
		Arrays.fill(k, 9999999);
        

	}
	
	
	
	public  void Insert(int v, int key_value){   //insert operation
		v_heap[0]= 777777777;
		size = size +1;
		v_heap[size] = v;
		p.put(v,size);
		key[v] = key_value;
		
		
		
		Heapify_up(size); 
		
		
		
	}
	
	public  void Heapify_up(int i){     // Heapify_up operation
		
		while(i>1){
			
			int temp ;
		    double jd;
			jd=Math.floor(i/2);
			int j = (int) jd;
                        
			if (key[v_heap[i]] < key[v_heap[j]] ){
				
				temp = v_heap[i]; //swap v_heap[i] and v_heap[j]
				v_heap[i] = v_heap[j];
				v_heap[j] = temp;
				
				p.put(v_heap[i],i) ;
				p.put(v_heap[j],j);
				i = j;
				
			}
			else
			{
			   break;
			
			}
		
		}
	}
	
	
	public  void Heapify_down(int i){   // Heapify_down operation
	   int j;
	   int temp;
	   
		while (2*i <= size){
			
			if((2*i==size)||key[v_heap[2*i]] <= key[v_heap[2*i+1]]){
				j=2*i;
			}
			else{
				j=2*i+1;
				
				
			}
			
			
			if(key[v_heap[j]] < key[v_heap[i]]){
				temp = v_heap[i]; //swap v_arr[i] and v_arr[j]
				v_heap[i] = v_heap[j];
				v_heap[j] = temp;
				
				p.put(v_heap[i],i) ;
				p.put(v_heap[j],j);
				i = j;
				
			}
			else{
				break;
			}
		}
	
	}
	
	public  int Extract_min(){
		int min;
		min = v_heap[1];
		v_heap[1] = v_heap[size];
		p.put(v_heap[1],1);
		size = size -1 ;
		
		if(size>=1){
			Heapify_down(1);
			
		}
		
		return min;
	
	}
	
	public  void Dec_key(int v , int key_value){
		
		key[v] = key_value;
		Heapify_up(p.get(v));
	
	}
	
	}
	
	
	
	
	/**
	 * @param args
	 * @throws IOException
	 */
	
	
	public static void main(String[] args) throws  IOException {
		
		
		String outputfile = "output.txt"; 
		FileWriter filew = new FileWriter(outputfile,true);
		
		
		BufferedReader br = new BufferedReader(new FileReader("test5"));
		String line;
		String[] split_lin = new String[3];
	
		int u,v = 0;
		int tot_wt = 0;
		
		HashMap<Integer, Integer> d = new HashMap<Integer,Integer>();
		
		int lno = 1;
		int u_m = 0;
		
		
		ArrayList<Integer> vertices = new ArrayList<Integer>();
		ArrayList<Integer> s_set = new ArrayList<Integer>();
		
		vertices.add(4444); //vertices[0] is dummy value
		
		while( (line = br.readLine() ) != null) {
			String tr_line = line.trim();
			split_lin = tr_line.split("\\s");
			
			
			
		    if(lno == 1){      //First line in  input.txt
				n = Integer.parseInt(split_lin[0]);
				m = Integer.parseInt(split_lin[1]);
				w = new int[n+1][n+1];
				for(int i=1; i < n+1 ; i++){    
					for(int j=1; j < n+1 ; j++ ){
						
						w[i][j]=0;
					}
				}
			
				lno++;
			}  
			else            //Rest of lines in input.txt
			{  
			     u = Integer.parseInt(split_lin[0]);   // u, v are vertices from input.txt
			     v = Integer.parseInt(split_lin[1]);
			     w[u][v] = Integer.parseInt(split_lin[2]);   
			     w[v][u] = Integer.parseInt(split_lin[2]);
			     if(!vertices.contains(u)){
			    	 vertices.add(u);                 //u and v are stored in vertices - V set
			     }
			     if(!vertices.contains(v)){
			    	 vertices.add(v);
			     }
			}
		} //END of formation of vertices and weights
		
		
		
		
	
		//PRIM'S ALGORITHM IMPLEMENTATION:
		
		int start = (int) vertices.get(1);
		s_set.add(4444) ; //dummy value at s_set[0]
		d.put(start, 0);  //d(s)←0
		
		for(int i = 2 ; i<vertices.size(); i++){   //d(v)←infinity for every v in V other than {s}
			d.put(vertices.get(i), 999999999);
			
		}
		
		MST_ArunChandra_Pendyala_50207136.Heap q = new MST_ArunChandra_Pendyala_50207136.Heap();
		
		q.Init(q.v_heap, q.key ); //initialize v_heap and key in q
		
		Out output = new Out();
		
		
		
		for(int i = 1 ; i  < vertices.size(); i++){
			int v1 = vertices.get(i);
			q.Insert(v1,d.get(v1));
			
			
		} 
		
		
		while( s_set.size() != vertices.size() ){
			
			u_m = q.Extract_min();
			
			if(!s_set.contains(u_m)){   //add u only if it is not present - UNION operation
				
				s_set.add(u_m);
			}
			
			for(int i = 1 ; i < vertices.size(); i++){
				int ver = vertices.get(i);
				
				if((!s_set.contains(ver)) && w[u_m][ver] != 0 && w[ver][u_m] != 0){
					if(w[u_m][ver]  < (int) d.get(ver)){
						d.put(ver, w[u_m][ver]);
						q.Dec_key(ver, d.get(ver));
						
						output.Out_store(u_m,ver);
					}
				}
			}
			
			
		}
		
		
		
		
		for(int i= 1 ; i< output.pi.length ; i++){
			if(output.pi[i]!=0){
		
		
		output.weight += w[i][output.pi[i]];   //cumulative of individual weights
			}
		}
		System.out.println(output.weight);
		tot_wt = output.weight;
		filew.write(tot_wt+"");
		filew.write("\n");
		
		
		
		for(int i= 1 ; i< output.pi.length ; i++){
			if(output.pi[i]!=0){
		System.out.println(output.pi[i] + " "+ i);
		filew.write(output.pi[i] + " "+ i);
		filew.write("\n");
		
		
			}
		}
		
		
		
		
		filew.close();
		
		
	}
		
		
	
	
		
	}

		
	













