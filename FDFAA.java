	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.Map;
	import java.util.Stack;

	public class FDFAA {

		Map<String, String> hashmap;
		
		public  String[][] table(String DFA){
			String [] tokens = DFA.split("#");
			String dfa = tokens[0];
			String accept = tokens[1];
			
			hashmap = new HashMap<>();
			for(int i = 0; i < accept.length(); i++)
				if(accept.charAt(i) != ',')
					hashmap.put(accept.charAt(i)+"", accept.charAt(i)+"");
			String clean = "";
			ArrayList<String> binary = new ArrayList<>();
	        ArrayList<Character> states = new ArrayList<>(); 
	        String [] dfa_states = dfa.split(";");
	        
	        String bi = "";
	       for(int i = 0; i < dfa_states.length; i++)
	       {
	       	int counter = 0;
	       	for(int j = 0; j < dfa_states[i].length(); j++){
	       		if(j == 0)
	       			states.add(dfa_states[i].charAt(j) );
	       		if(dfa_states[i].charAt(j) != ',' && counter < 3)
	       		{
	       			clean += dfa_states[i].charAt(j);	
	       		}
	       		else if(dfa_states[i].charAt(j) == ','){
	       			counter++;
	       		}
	       		else {
	       			bi += dfa_states[i].charAt(j);
	       		}
	       	}
	       	binary.add(bi);
	       	bi = "";
	       }
	       String[][] dfaTable = new String[states.size()][3];
			for(int i = 0, j = 0; i < clean.length(); i += 3, j++){
				dfaTable[j][0] = clean.charAt(i+1) + "";
				dfaTable[j][1] = clean.charAt(i+2) + "";
				dfaTable[j][2] = binary.get(j);
			}
	        return dfaTable;
		}

		public void run(String[][] dfaTable,String testcase){
			Stack<String> stack = new Stack<>();
			int  r = 0;
			int state = 0;
			int k = 0;
			for(int l = 0; l < testcase.length(); l++){
			 	if(stack.isEmpty())
			 		stack.push("0");
				state =  Integer.parseInt(dfaTable[k][Integer.parseInt(testcase.charAt(l) + "")] + "");
			 	stack.push(state + "");
				k = state;
				if(l == testcase.length()-1)
				{
			         String top = stack.peek();
					while(!stack.isEmpty()){
						String dead = stack.pop();
						if(hashmap.containsKey(dead))
						{
							System.out.print(dfaTable[Integer.parseInt(dead)][2]);
							stack.clear();
							r = l;
							k = 0;
						}
						else if(stack.isEmpty()){
							System.out.print(dfaTable[Integer.parseInt(top)][2]);
							return;
						}	
						 else
							l--;
					}
			   }}}

		public static void main(String[] args) {
			String dfa1 = "0,0,1,00;1,2,1,01;2,0,3,10;3,3,3,11#0,1,2";
			String testcase1 = "1011100";
			FDFAA fdfa = new FDFAA();
			String[][] table1 = fdfa.table(dfa1);
			fdfa.run(table1, testcase1);
			System.out.println();
			

		}

	}


