public class CFG{
	String[] cfg;
	
	public CFG(String input){
		this.cfg = input.split(";");
	}
	
	public String First(){
		String first = "";
		for(int i = 0 ; i < this.cfg.length ; i++){
			String tmp = this.cfg[i];
			String[] rule_parsed = tmp.split(",");
			String start = rule_parsed[0];
			first+=start+",";
			for(int j = 1 ; j < rule_parsed.length ; j++){
				char y = rule_parsed[j].charAt(0);
				//System.out.println(y);
			   	if(Character.isLowerCase(y)==true){
			   		first+=y;
			   	}else if(Character.isUpperCase(y)==true&&
			   	String.valueOf(y)!=start&&rule_parsed[rule_parsed.length-1].charAt(0)!='e' ){
			   		int position = search(this.cfg ,rule_parsed[j].charAt(0));
			   		//System.out.println(String.valueOf(y));
			   		String c = First_helper(this.cfg[position]);
			   		first+=c;
			   		//System.out.println(first);	
			   	}else if(Character.isLowerCase(y)==false&&
			   			String.valueOf(y)!=start){
			   		int position = search(this.cfg ,rule_parsed[j].charAt(0));
			   		String c = First_helper(this.cfg[position]);
			   		first+=c;
			   		
			   	}else if(String.valueOf(y) == start&&rule_parsed[rule_parsed.length-1]!="e"){
			   		int position = search(this.cfg ,rule_parsed[j+1].charAt(0));
			   		String c = First_helper(this.cfg[position]);
			   		System.out.println(c);
			   		if(c.contains("e")){
			   			char z = rule_parsed[j].charAt(1);
			   			System.out.println(z);
			   			if(Character.isLowerCase(z)==true){
			   				first+=z;
			   			}else{
			   				break;
			   			}
			   		}
			   
			   	}
			   	
			}
			
			first+=";";
		}
		
		return first;
		
	}
	
	 
	public  String Follow(){
		String follow = "";
		String followb = "";
		String h = "";
		int counter = 0 ;
		while(counter < this.cfg.length){
			char start = this.cfg[counter].charAt(0);
			follow+=start+",";
		 for(int i = 0 ; i < this.cfg.length ; i++){
			String x = this.cfg[i];
			for(int j = 2 ;j<x.length(); j++ ){
				char tmp = x.charAt(j);
				if(tmp == start&&j<x.length()-1){
					char t = x.charAt(j+1);
					if(Character.isLowerCase(t)==true){
						follow+=t+",";
					}else if(t != ','&& Character.isUpperCase(t)==true){
						int position = search(this.cfg , t);
						h = First_helper(this.cfg[position]);
						if(h.contains("e")==false){
							follow+=h;
						}else{
							String f =String.valueOf(x.charAt(0));
							h =h.replace("e", f);
							follow+=h;
					}
					
					}else if(t==','||j==x.length()-1){
						follow+=x.charAt(0);
						
						}
				}
			}//for loop over string
		}//for loop over el rules
		 if(counter == 0){
				follow += "$";
			}
		counter++;
		follow+=";";
		}
		return follow;
	}
	
	
	
	public int search(String[] x , char y){
		int position = 0;
		for(int i = 0 ; i < x.length ; i++){
			if(x[i].charAt(0)==y){
				position = i;
			}
		}
		return position;
	}
	
	
	public static String First_helper(String x){
		String first = "";
		String[] rule = x.split(",");
		for(int i = 1 ; i < rule.length ; i++){
			first+= rule[i].charAt(0);
		}
		return first;
	}
	
	
	public static void main(String[] args){
		String input = "S,ScT,T;T,aSb,iaLb,e;L,SdL,S";
		CFG cfg = new CFG(input);
		String firstEncoding = cfg.First();
		String followEncoding = cfg.Follow();
		System.out.println("First: " + firstEncoding);
		System.out.println("Follow: " + followEncoding);
        System.out.println("the capitale letter in both first and follow it means to get the first of follow of this rule ");
	}
}