import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.*;

public class Uno{
	final static int SKIP = 10; //Así que no tengo que recordar el número de estas cosas.
	final static int REVERSE = 11;
	final static int DRAW2 = 12;
	final static int WILD = 13;
	final static int WILDDRAW4 = 14;
	
	public static void main(String[] args) throws FileNotFoundException{
		
		boolean gameOver = false;
		Maso draw = new Maso(); //Crea la pila de cartas
		draw.shuffle(); //Mezcla la baraja
		
		Scanner input = new Scanner(System.in); //Declaras un escaner
		System.out.println("UNO");
		System.out.println("Por favor ingrese el numero de jugadores: "); //Se ingresa el numero de jugadores
		int numPlayers = input.nextInt(); //Ingreso del numero de jugadores
		Jugador[] players = new Jugador[numPlayers]; //Se crea lista de jugadores
		for(int i = 0; i < numPlayers ; i++){
		    players[i] = new Jugador(); //Crea cada jugador
		}
		for(int i = 0; i < 7; i++){ //Reparte las cartas
			for(int j = 0; j < numPlayers; j++){ //A cada jugador
				players[j].takeCard(draw.nextCard());
			}
		}
		int t = 0,i,choice; 		//indica el turno a cada jugador //usado  para la lectura
		boolean clockwise = true;	//indica la direccion del juego
		int nextPlayerDraw = 0;		// Puede valer de 2 a 4, dependiendo en que el siguiente jugador tiene que tirar despues de  2 o 4 son jugadas
		boolean nextPlayerSkip = false; 
		do
		{
			draw.discard(draw.nextCard()); 
		}
		while((draw.getTopCard().getColor().equalsIgnoreCase("black")) || (draw.getTopCard().getValue() > 9));
		
		while(!gameOver)
		{
			System.out.println("------------------------------------");
			System.out.println("Jugador " + (t + 1) + " en turno...");
			System.out.println();
			System.out.println("Carta de hasta arriba....    " + draw.getTopCard()); 
			System.out.println();
			System.out.println("Selecciona la carta por numero: ");
			
			for(i = 0; i < players[t].getNumCardsInHand(); i++){
				System.out.println(i + ") " + players[t].getThisCardFromHand(i) + " ");
			}
			
			System.out.println(i + ") Robar carta"); 
			choice = input.nextInt();
			if(choice == i){
				players[t].takeCard(draw.nextCard()); 
			}
			if((players[t].getThisCardFromHand(choice).getColor().equalsIgnoreCase("black"))){
				draw.discard(players[t].playCard(choice));
				while(draw.getTopCard().getColor().equalsIgnoreCase("black")){
					System.out.println();
					System.out.println("Select a color");
					System.out.println("1) rojo");
					System.out.println("2) azul");
					System.out.println("3) verde");
					System.out.println("4) amarillo");
					int wildColor = input.nextInt();
					switch(wildColor){
					case 1:
						draw.wildSetColor("rojo");
						break;
					case 2:
						draw.wildSetColor("azul");
						break;
					case 3:
						draw.wildSetColor("verde");
						break;
					case 4:
						draw.wildSetColor("amarillo");
						break;
					default:
						draw.wildSetColor("negro");
					}
				}
				if(draw.getTopCard().getValue() == WILDDRAW4){
					nextPlayerDraw = 4;
					nextPlayerSkip = true;
				}
			}
			else if((players[t].getThisCardFromHand(choice).getColor().equalsIgnoreCase(draw.getTopCard().getColor())) || (players[t].getThisCardFromHand(choice).getValue() == draw.getTopCard().getValue())){
				System.out.println("Jugador jugo... " + players[t].getThisCardFromHand(choice)); 
				System.out.println("\n\n\n\n\n\n\n");
				draw.discard(players[t].playCard(choice)); 
				if(draw.getTopCard().getValue() == SKIP){
					nextPlayerSkip = true;	
				}
				if(draw.getTopCard().getValue() == REVERSE){
					clockwise = !clockwise;
				}
				if(draw.getTopCard().getValue() == DRAW2) {
					nextPlayerDraw = 2;
					nextPlayerSkip = true;
				}
			}
			
			if(players[t].getNumCardsInHand() == 0) {
				System.out.println("********************************");
				System.out.println("*        Jugador " + (t + 1) + " gana!        *");
				System.out.println("********************************");
				gameOver = true; 
			}
			
			if(clockwise){
				t++;
			}
			else{
				t--;
			}
			if(t == numPlayers){
				t = 0;
			}
			if(t == -1){
				t = numPlayers - 1;
			}
			for(i = 0; i < nextPlayerDraw; i++){
				players[t].takeCard(draw.nextCard());
				System.out.println("Jugador " + (t + 1) + " Toma una carta");
			}
			System.out.println();
			
			nextPlayerDraw = 0; 
			
			if(nextPlayerSkip){
				if(clockwise){
					System.out.println("Jugador " + (t + 1) + " fue saltado"); 
					t = t + 1;
				}else{
					System.out.println("Jugador " + (t + 1) + " fue escapado");
					t = t - 1;
				}
			}
			System.out.println();
			nextPlayerSkip = false; 
			if(t == numPlayers) {
				t = 0;
			}
			if(t == -1){
				t = numPlayers - 1;
			}
		}
	}
}
