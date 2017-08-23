package nl.sogyo.mancala;

public class Hole extends Field {

	public Hole() {
		super(new Player());
		addStones(4);
	}
	
	public Hole(int s, Player p) {
		super(p);
		addStones(s);
	}
	
	public void play() {
		if(getPlayer().getTurn()) {
			int passStones = getStones();
			resetStones();
			getNeighbor().getOnePassRest(passStones);
		}
	}
	
	public void getOnePassRest(int passStones) {
		super.getOnePassRest(passStones);
		if(passStones == 1) {
			if(getStones() == 1 && getPlayer().getTurn()) {
				((Hole)getOpposite()).getCaptured(getKalaha());
				getCaptured(getKalaha());
			}
			endTurn();
		}
	}
	
	public void initiateChain(int length) {
		if (length > 1) {
			setNeighbor(new Hole(4, getPlayer()));
			getNeighbor().continueChain(3, length, this);
		}
	}
	
	public void continueChain(int length, int maxLength, Hole firstHole) {		 
		
		if(length == maxLength/2) {
			setNeighbor(new Kalaha(0, firstHole.getPlayer()));
		} else 
		if(length == maxLength) {
			setNeighbor(new Kalaha(0, firstHole.getPlayer().getNemesis()));
		} else 
		if(length < maxLength/2) {
			setNeighbor(new Hole(4, firstHole.getPlayer()));
		} else
		if(length > maxLength/2) {
			setNeighbor(new Hole(4, firstHole.getPlayer().getNemesis()));
		}
		getNeighbor().continueChain(++length, maxLength, firstHole);
	}
	
	public void getCaptured(Kalaha kalaha) {
		int stones = getStones();
		resetStones();
		kalaha.addStones(stones);
	}
	
	public Field getOpposite() { 
		return getNeighbor().getOpposite().getNeighbor();
	}
	
	public Kalaha getKalaha() {
		return getNeighbor().getKalaha();
	}
	
	public boolean hasMovesLeft(Player p, Field initialField) {
		if(p.equals(getPlayer()) && getPlayer().getTurn() && getStones() > 0) {
			return true;
		}
		if(this.getNeighbor().equals(initialField)) {
			return false;
		}
		return getNeighbor().hasMovesLeft(p, initialField);
	}
	
	public void sweep() {
		getKalaha().addStones(getStones());
		resetStones();
		getNeighbor().sweep();
	}
	
	public void print() {
		System.out.print("(" + getStones() + ")");
	}
}
