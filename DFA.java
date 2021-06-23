import java.util.ArrayList;
public class DFA {
    String[] accept_states;
    String[][] transition;
    String[] zeros ;
    String[]  ones ;
    String[]  epsilon ;

    
    public DFA(String NFA){
        ArrayList<String> q_dash = new ArrayList<String>();
        ArrayList<String> q = new ArrayList<String>();

       
        String[] tokens = NFA.split("#");
        if(tokens.length == 4){ 
          String accept = tokens[tokens.length-1];
          this.accept_states = accept.split(",");
          String zeros = tokens[0];
          String ones = tokens[1];
          String epsilon = tokens[2];
          this.zeros = zeros.split(";");
          this.ones = ones.split(";");
          this.epsilon = epsilon.split(";"); 
          }
      
        	
        }
         
    
    
    public void run(String input){
    	char state = '0'; 
    	for(int i = 0 ; i < input.length(); i++){
    		if(input.charAt(i)=='0'){
    			for(int j = 0 ; j < zeros.length ; j++){
    				String tmp = zeros[j];
    				if(tmp.charAt(0) == state ){
    					state = tmp.charAt(2);
    				}else{
    					break;
    				}
    			}}
            if(input.charAt(i)=='1'){
            	for(int j = 0 ; j < ones.length ; j++){
    				String tmp = ones[j];
    				if(tmp.charAt(0) == state ){
    					state = tmp.charAt(2);
    				}else{
    					break;
    				}}
    		}
            if(input.charAt(i)=='e'){
            	for(int j = 0 ; j < epsilon.length ; j++){
    				String tmp = epsilon[j];
    				if(tmp.charAt(0) == state ){
    					state = tmp.charAt(2);
    				}else{
    					break;
    				}}
    		}
            
    	}//for loop input
    	
    	boolean flag = false;
		for(int j = 0 ; j < this.accept_states.length ; j++){
			String acc = this.accept_states[j];
			//int accp=Integer.parseInt(acc);
			if(state == acc.charAt(0)){
				flag = true;
			}
		}
		if(flag ==true){
			System.out.print("accepted");
		}else{
			System.out.print("rejected");
		}
    }
    
    public static void main(String[] args){
       DFA  x = new DFA("0,0;0,1#0,0;1,2#1,2#2");
       x.run("111111");
      
    }
}
