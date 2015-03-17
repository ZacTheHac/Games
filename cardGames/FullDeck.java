package cardGames;

public class FullDeck {//basically ch8 GZ3, but it's for a lab

	public static void main(String[] args) {
		Card[] cards = new Card[52];//holds all the cards
		char[] suits = {'s','d','h','c'};//possible suits
		int index = 0;//where we are in the list
		for(char suit:suits){//build the deck
			for(int v = 1;v<=13;++v){
				cards[index]=new Card(suit,v);
				index++;
			}
		}
		System.out.println("I HOPE YOU LIEK SPAM!");
		//int counter = 1;
		for(Card currCard:cards){//print the cards
			System.out.println(currCard.toString());
		}

	}

}
