package test;

public class Practice {
	static int count;
	public static void main(String[] args) {
		int i;
		//StringBuffer s= new StringBuffer("promotion");
		String s="promotion";
		char[] letters=s.toCharArray();
		for( i=0;i<letters.length;i++) {
			
			
			if(letters[i]=='o') {
				count++;
				s=s.replace('o', '$');
				//System.out.print(letters[i]);
				System.out.println(s);
			}if(count==2){
				s=s.replaceAll("o", "$$");
			}
			
			/*if(s.charAt[i]=='o') {
				count++;
				System.out.println(count);
				if(count==1) {
				s=s.replace('o', '$');
				//s.re
				}
				
					
				}if(count==2){
					s=s.replaceAll("o", "$$$");
				}*/
				//System.out.println(s);
				}
			
			}
			
		}
		
		
	


	


