package nl.sogyo.mancala;

public class Player {
	
	private String name;
	private Player nemesis;
	private boolean hasTurn;
	
	public Player() {
		name = "Trump";
		nemesis = new Player(this);
		hasTurn = true;
	}
	
	public Player(Player firstPlayer) {
		name = "Clinton";
		nemesis = firstPlayer;
		hasTurn = false;
	}
	
	public String getName() {
		return name;
	}
	
	public Player getNemesis() {
		return nemesis;
	}
	
	public boolean getTurn() {
		return hasTurn;
	}
	
	public void setTurn(boolean hasTurn) {
		this.hasTurn = hasTurn;
	}
	
	public Player switchTurn() {
		hasTurn = !hasTurn;
		nemesis.setTurn(!nemesis.getTurn());
		if(hasTurn) {
			return this;
		} else {
			return nemesis;
		}
	}
}



