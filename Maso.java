import java.io.*;
import java.util.*;

public class Maso{
	private Carta drawPile[] = new Carta[108]; //Crea el mazo de 108 cartas
	private int numberOfCards; //LLeva cuenta de cuantas cartas estan disponibles en el mazo
	private Carta discardPile[] = new Carta[108]; //Solo soporta 108 cartas
	private int numberDiscarded; //LLeva cuenta de cuantas cartas se han jugado
	
	public Maso() throws FileNotFoundException{
		 int i = 0;  // Numero de carta actual
		 String[] colors = {"amarillo", "verde", "azul", "rojo"};
		for (String color: colors) {
			drawPile[i++] = new Carta(0, color);
			for (int v = 1; v <= 12; v++) {
				drawPile[i++] = new Carta(v, color);
				drawPile[i++] = new Carta(v, color);
			}
		}
		for (int v = 1; v <= 4; v++) {
			drawPile[i++] = new Carta(13, "negro");
			drawPile[i++] = new Carta(14, "negro");
		}
		numberOfCards = i; // pone el numero de cartas igual al generado 
	}
	
	public Carta nextCard(){
		Carta nextCard = drawPile[0]; 
		for(int i = 0; i < numberOfCards - 2; i++){
			drawPile[i] = drawPile[i+1];
		}
		drawPile[numberOfCards - 1] = new Carta(-1, "null");
		numberOfCards--; //actualiza el numero de cartas disponibles
		if(numberOfCards == 0){
			for(int i = 0; i < numberDiscarded - 1; i++){
				drawPile[i] = discardPile[i];
				numberOfCards++; //Actualiza el numero de cartas
				
			}
			numberDiscarded = 0; //Actualiza el el monton
			shuffle(); 
		}
		return nextCard;
	}
	public void shuffle(){    
		for (int i = numberOfCards - 1; i > 0; i--){
			int j = (int)(new Random().nextInt(numberOfCards));
			Carta t = drawPile[j];
			drawPile[j] = drawPile[i];
			drawPile[i] = t;
		}
	}
	public void wildSetColor(String color) {//(se activa solo cuando el comodin es tirado)
		discardPile[numberDiscarded - 1].color = color;
	}

	public void discard(Carta discard){
		discardPile[numberDiscarded] = discard;
		numberDiscarded++;
	}
	public Carta getTopCard(){ 
		return discardPile[numberDiscarded-1];
	}
}
