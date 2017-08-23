package nl.sogyo.mancala;

import org.junit.*;

public class MancalaTest {
	
	private Player player1;
	private Player player2;
	
	private int numberOfFields;
	private Field[] fields;
		
	@Before
	public void setup() {
		
		numberOfFields = 14;
		fields = new Field[numberOfFields];
		
		Hole firstHole = new Hole();
		firstHole.initiateChain(fields.length);
		fields[0] = firstHole;
		
		for(int i = 1 ; i < fields.length ; ++i) {
			fields[i] = fields[i-1].getNeighbor();
		}

		player1 = fields[0].getPlayer();
		player2 = player1.getNemesis();
	}
	
	@Test
    public void amountOfStonesInAHoleIsFourAfterStart() {
    	Assert.assertEquals("Expect 4", 4, fields[0].getStones());
    }
	
	@Test
    public void fieldIsEmptyAfterPlayTurn() {
		fields[0].play();
    	Assert.assertEquals("Expect 0", 0, fields[0].getStones());
    }
     
    @Test
    public void amountOfStonesAfterPassOnceIsOneMore() {
    	fields[0].play();
    	Assert.assertEquals(5, fields[0].getNeighbor().getStones());
    }
    
    @Test
    public void neighborOfLastHoleIsFirstHole() {
    	Assert.assertEquals(fields[0], fields[fields.length-1].getNeighbor());
    }
    
    @Test
    public void amountOfStonesAfterPassingFullCircleOnceIsOne() {
    	fields[0].addStones(fields.length-4);
    	((Hole)fields[0]).play();
    	Assert.assertEquals(1, fields[0].getStones());
    }
    
    @Test 
    public void playerKalahaIsNotSkippedWhenPassingStones() {
    	fields[fields.length/2 - 2].play();
    	Assert.assertEquals(1, fields[fields.length/2 - 1].getStones());
    }
    
    @Test
    public void opponentKalahaIsSkippedWhenPassingStones() {
    	fields[fields.length-2].play();
    	Assert.assertEquals(0, fields[fields.length-1].getStones());
    }
    
    @Test
    public void playersHaveOppositeTurns() {
    	Assert.assertNotEquals(player1.getTurn(), player1.getNemesis().getTurn());
    }
    
    @Test
    public void oppositeHoleIsNeighborOfOppositeOfNeighbor() {
    	Assert.assertEquals(fields[0].getOpposite(), fields[fields.length-2]);
    }
    
    @Test
    public void playerIsSwitchedAfterEndOfTurn() {
    	boolean previousTurn = player1.getTurn();
    	fields[0].play();
    	Assert.assertNotEquals(previousTurn, player1.getTurn());
    }
    
    @Test
    public void holeHasSamePlayerAsItsKalaha() {
    	Assert.assertEquals(fields[0].getPlayer(), fields[0].getKalaha().getPlayer());
    }
    
    @Test
    public void kalahaHasCorrectAmountOfStonesAfterCapture() {
    	int stonesBeforeKalaha = fields[fields.length/2 - 1].getStones();
    	int stonesBeforeHole = fields[0].getStones();
    	((Hole)fields[0]).getCaptured((Kalaha)fields[fields.length/2 - 1]);
    	Assert.assertEquals(fields[fields.length/2 - 1].getStones(), stonesBeforeKalaha + stonesBeforeHole);
    }
    
    @Test
    public void playerHasNoMoreMovesIsDetected() {      
    	fields[0].play();
    	
    	fields[0].resetStones();
    	fields[1].resetStones();
    	fields[2].resetStones();
    	fields[3].resetStones();
    	fields[4].resetStones();
    	fields[5].resetStones();
    	  	
    	fields[8].play();

    	Assert.assertEquals(false, fields[0].hasMovesLeft(fields[0].getPlayer(), fields[0]));
    }
    
    @Test
    public void allFieldsAreEmptyAfterSweep() {
    	fields[0].sweep();
    	fields[0].getKalaha().getNeighbor().sweep();

    	for(Field f : fields) {
    		if(!(f instanceof Kalaha)) {
    			Assert.assertEquals(0, f.getStones());
    		}
    	}
    }
    
    @Test
    public void winnerIsDeterminedCorrectly() {
    	fields[0].resetStones();
    	fields[1].resetStones();
    	fields[2].resetStones();
    	fields[3].resetStones();
    	fields[4].resetStones();
    	fields[5].resetStones();
    	fields[6].resetStones();
    	fields[7].resetStones();
    	fields[8].resetStones();
    	fields[9].resetStones();
    	fields[10].resetStones();
    	fields[11].resetStones();
    	fields[12].resetStones();
    	fields[13].resetStones();
    	Assert.assertEquals("Both", fields[0].determineWinner());
	}

	@Test
	public void GameRunsAsExpectedWhenPlayingFieldWithHighNumberStones(){
		fields[0].addStones(8);
		fields[1].resetStones();
		fields[2].resetStones();
		fields[3].resetStones();
		fields[4].resetStones();
		fields[5].resetStones();
		fields[6].resetStones();
		fields[7].resetStones();
		fields[8].resetStones();
		fields[9].resetStones();
		fields[10].resetStones();
		fields[11].resetStones();
		fields[12].resetStones();
		fields[12].addStones(7);
		fields[0].play();
		fields[12].play();
		Assert.assertEquals(2, fields[5].getStones());
	}
}
