package others;

public class FizzBuzz {

	public static void main(String[] args) {
		for(int i = 1; i<=100;i++){
			if(i%15==0){//if something is a multiple of 3 and 5, the multiplication can always be simplified to 15*something else
				System.out.println("FizzBuzz");
			}
			else if(i%3==0){
				System.out.println("Fizz");
			}
			else if(i%5==0){
				System.out.println("Buzz");
			}
			else{
				System.out.println(i);
			}
		}
	}

}
