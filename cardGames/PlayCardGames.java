package cardGames;

public class PlayCardGames {

	public static void main(String[] args) {
		CardGame pok = new Poker();
		CardGame pickup = new FiftyTwoCardPickup();
		
		pok.dispDescription();
		pok.deal();
		System.out.println();
		pickup.dispDescription();
		pickup.deal();
		

	}

}
