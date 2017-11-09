
public class IfTooMuch {
	public void fo() {
		int i = 0;
		if (i >1 ) {

		}else if(i>2){
			
		}
		else {
			if (i > 3) {

			} else {
				if (i >4) {   // Noncompliant 
					System.out.println("asdf");
				}else{
					System.out.println("asd22f");
				}
			}
		}
	}
}
