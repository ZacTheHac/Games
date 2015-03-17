package dieGames;



public class Die {//ch4 - GZ1 & ch6 GZ4 & ch8 gz2
	protected int value = 0; //the value of this particular die
	private int highestDieValue = 6; //highest value the die can be. by default: 6
	private int lowestDieValue = 1; //lowest allowed value. by default: 1
	
	public Die(){//creates a new 6-sided Die with a random value
		this(1,6);
		this.setValue(this.genRandomValue());//had to put it in 2 lines or the method doesn't exist yet
	}
	
	public Die(int value){//creates a new 6-sided Die with a set value
		this(1,6,value);//if I know the value, I don't have to use genRandomValue()
		//however: due to the way the constructor this calls works: it still uses the 2-line method
	}
	
	public Die(int lowValue, int highValue){//creates a random (highValue - lowValue)-sided die
		if(lowValue < highValue){//check that lowvalue is lower than highvalue - so it doesn't break later
			this.lowestDieValue = lowValue;//change the lower bound for this instance
			this.highestDieValue = highValue;//change this instance's upper bound
			this.setValue(this.genRandomValue());//and give it a random value
		}
		else{
			throw new IllegalArgumentException("lowValue must be lower than highValue");
			//if they gave a highValue > lowValue
		}

	}//I use this as the base constructor, because I can't use my genRandomValue while using another constructor
	
	public Die(int lowValue, int highValue, int value){//creates a (highValue - lowValue)-sided die with the declared value
		this(lowValue,highValue);
		this.setValue(value);//again: have to do it in 2 lines. it wastes a few cycles, but no repeated code
	}
	
	public void setValue(int value){//used to manually set the value after instantiation
		if(value >= this.lowestDieValue && value <= this.highestDieValue){//check the new value is between the bounds
			this.value = value;//if it is, set it
		}
		else{
			throw new IndexOutOfBoundsException("value must be between "+this.lowestDieValue+" and "+this.highestDieValue);
			//if not: throw an error
		}
	}
	
	public int getValue(){//just spits the value back out, no filtering needed
		return this.value;
	}
	
	public int getSides(){//returns the number of sides on this particular die
		return this.highestDieValue-this.lowestDieValue;
	}
	
	public int getHighest(){//returns the highest value a die can be
		return this.highestDieValue;
	}
	
	public int getLowest(){//returns the lowest value a die can be
		return this.lowestDieValue;
	}
	
	public void changeBounds(int lowValue, int highValue){//changes the bounds of the die
		if(lowValue < highValue){//checks that the bounds can be valid
			this.lowestDieValue = lowValue;//sets the new lower
			this.highestDieValue = highValue;//and upper values
		}
		else{
			throw new IllegalArgumentException("lowValue must be lower than highValue");
			//or throw an error
		}
	}
	
	public void reRoll(){//generates a new random value for the die
		this.setValue(this.genRandomValue());
		//useful if you change the bounds and want a new value, or if you just want to keep the same die
	}
	
	protected int genRandomValue(){//only accessible within this class: just generates a random value between lowest and highest value
		//users can call reRoll if they want to randomize the die
		return (int) ((Math.random() * (this.highestDieValue - this.lowestDieValue + 1))+this.lowestDieValue);
	}
	
	

}
