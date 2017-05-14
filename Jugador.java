public class Jugador{
	private Carta hand[] = new Carta[50]; //La mano del jugador (soporta solo 50)
	private int numberOfCards; //El numeo de cartas que tiene 
	public Jugador(){// Constructor que incia en 0 la mano del jugador
		this.numberOfCards = 0; 
	}
	public Carta getThisCardFromHand(int c){
		return hand[c]; 
	}
	public void takeCard(Carta c){
		hand[numberOfCards] = c;
		numberOfCards++;
	}
	public int getNumCardsInHand(){
		return numberOfCards;
	}
	public Carta playCard(int c){
		Carta cardPlayed = hand[c];
		for(int i = c; i < numberOfCards; i++){
			hand[i] = hand[i+1]; 
		}
		hand[numberOfCards] = new Carta(-1, "null");
		numberOfCards--; 
		return cardPlayed; 
	}
}
