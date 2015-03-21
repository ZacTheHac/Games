package others;

public class MyCharacter {//for ch 3 GZ#2, did it just because I could
	String name;
	short health;
	short walkSpeed;
	short runSpeed;
	
	MyCharacter(){//default values
		this.setName("Tim...");
		this.setWalkSpeed((short) 50);
		this.setRunSpeed((short) 250);//he may not walk very quickly, but that sprint will be legendary
		this.setHealth((short) 100);
	}
	
	MyCharacter(String Name, short Health, short WalkSpeed, short RunSpeed){
		this.setName(Name);
		this.setWalkSpeed(WalkSpeed);
		this.setRunSpeed(RunSpeed);//he may not walk very quickly, but that sprint will be legendary
		this.setHealth(Health);
	}
	
	public void setName(String Name){
		if(Name.length() <= 20 && Name.length() >= 0){//if the char's name is between 0 and 20 characters (inclusive)
			this.name = Name;
		}
		else{
			throw new IndexOutOfBoundsException("Character's name must be between 1 and 20 characters long.");//or throw an exception
		}
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setWalkSpeed(short wS){
		if(wS >=0 && wS <= 200){//walkspeed is limited between 0 and 200
			this.walkSpeed=wS;
		}
		else{
			throw new IndexOutOfBoundsException("Character's walk speed must be between 0 and 200, inclusive.");//throw an error if they can't respect that
		}
	}
	
	public short getWalkSpeed(){
		return this.walkSpeed;
	}
	
	public void setRunSpeed(short rS){
		if(rS >=0 && rS <= 250){//run speed can be between 0 and 250 (slightly faster than walking, but 0 disables running)
			this.runSpeed=rS;
		}
		else{
			throw new IndexOutOfBoundsException("Character's run speed must be between 0 and 250, inclusive.");//it's only a short, so I couldn't make it much larger
		}
	}
	
	public short getRunSpeed(){
		return this.runSpeed;
	}
	
	public void setHealth(short Health){
		if(Health >=0 && Health <= 100){//health must be between 0 and 100
			this.health=Health;
		}
		else{
			throw new IndexOutOfBoundsException("Character's health must be between 0 and 100, inclusive.");
		}
	}
	
	public short getHealth(){
		return this.health;
	}
	
	public String toString(){//the most complex method of this class
		String message = "";
		if(this.getName() == ""){//if their name is blank
			message = "This nameless character";
		}
		else{
			message = this.getName();//otherwise start the string with their name
		}//get an apropriate name
		
		
		if(this.getHealth() == 0){//if their health is 0, they're dead
			message +=" is dead, could";//make sure it's past tense
		}
		else{
			message +=" has " + this.getHealth() + " HP, can";//if their not dead, make it present tense and insert their HP
		}//get their health, and concatenate it
		
		
		if(this.getWalkSpeed()==0){//if they can't walk
			message += " not walk, and";
		}
		else{
			message += " walk up to " + this.getWalkSpeed() + " units/sec, and";//if they can walk
		}//get their walkspeed
		
		
		if(this.getRunSpeed()==0){//if they can't run
			if(this.getHealth() == 0){
				message += " couldn't run.";//and they're dead: make it past tense
			}
			else{
				message += " cannot run.";//and alive, make it current tense
			}
		}
		else{
			if(this.getHealth() == 0){//if they can run
				message+= " ran up to " + this.getRunSpeed() + " units/sec."; //use past-tense if they're dead
			}
			else{
				message+= " can run up to " + this.getRunSpeed() + " units/sec.";//or current tense if they're not
			}
		}//get their run speed
		
		return message;
		//return this.getName() + " has " + this.getHealth() + " HP, and can walk up to " + this.getWalkSpeed() + " units/sec, and run up to " + this.getRunSpeed() + " units/sec.";
	}
	

}
