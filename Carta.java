public class Carta{
	final int SALTO = 10;
	final int REVERSA = 11;
	final int TOMA2 = 12;
	final int COMODIN = 13;
	final int TOMA4BICH = 14;
	
	int value;
	String color;
	
	public Carta(int inValue, String inColor){
		this.value = inValue;
		this.color = inColor;
	}
	
	public int getValue(){
		return value;
	}
	
	public String getColor(){
		return color;
	}
	
	public String toString(){
		if(value < 10){
			return color + " " + value;
		}
		switch(value){
		case SALTO:
			return color + " salto";
		case REVERSA:
			return color + " reversa";
		case TOMA2:
			return color + " toma 2";
		case COMODIN:
			return color + " comodin";
		case TOMA4BICH:
			return color + " comodin toma 4";
		default:
			return "null";
		}
	}
}
