
public class Task5 {
     
	  public Task5(String cfg){
         String[] c = cfg.split(";");
         int i = 0;
		 int j = 1 ;
		 int k = 0 ;
		 while(i <  c.length){
			 String rule = c[i];
			 StringBuilder sb = new StringBuilder(rule);
			 if(i == 0 ){
				 LRE(sb);
				 i++;
			 }
			 else{
			 while(j < i-1){
		         String second_rule = c[j];
				 StringBuilder sb2 = new StringBuilder(second_rule);
		         while(k < rule.length()){
					if( second_rule.charAt(k)==rule.charAt(0)&& second_rule.charAt(k-1)==','&& LRE(sb)==true){
						sb2.deleteCharAt(k); 
						sb.delete(0, 2);
						sb2 = sb.append(sb);
						LRE(sb2);	
					}
					k++;
				}//while string el rule
				 j++; 
			 }   
			  i++;
			  LRE(sb);
			 }//else
			 }
		 
		 
			 }
				 
			 
		 
		  
     
      public  boolean LRE(StringBuilder rule){
    	 // System.out.print(rule);
    	  String[] rule_parsed = rule.toString().split(",");
    	  String sdash = "";
    	  String s = "";
    	  boolean flag = false;
    	  char start = rule.charAt(0);
    	  for(int i = 1 ; i < rule_parsed.length ; i++){
    		    if(rule_parsed[i].charAt(0) == start){
    		    	flag=true;
				   sdash = start + "'" +"," + rule_parsed[i].substring(1)+start+"'"+","+";";
			    	System.out.print(sdash);		
			    }else if(flag==true){
				  s = start +"," + rule_parsed[i] + start +"'";
			 	  System.out.print(s + ";" );
				}
			    else{
			    	 System.out.print(start + "," +rule_parsed[i]+";" );
			    }
    	  }
    	  System.out.print(";");
    	  if(flag == true){
    		  return true;
    	  }else{
    		  return false;
    	  }
    	  
      }
      public static void main(String[] args){
    	  String x = "S,Sa,b";
  		  Task5 z = new Task5(x);
      }
}
