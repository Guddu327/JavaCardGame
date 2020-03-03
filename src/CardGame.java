import java.util.*;

public class CardGame {

	ArrayList<Card> deck;
	ArrayList<ArrayList<Card>> players;
	static String[] value_name = {"Ace","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King"};
	static String[] house_name = {"SPADES","HEART","CLUB","DIAMONDS"};
	static int options;
	
	private void fillDeck(ArrayList<Card> deck) {

		int house;
		int value = 0, k = 0;
		for(int i = 0; i < 52; i ++)
		{
			house = k;
			value = (i+1) % 13 == 0? 13: (i + 1) % 13;
			if((i+1) % 13 == 0)
				k++;
			Card c = new Card(i+1, house, value);
			deck.add(c);
		}		
	}
	private void printDeck(CardGame cg) {

		if(cg.deck.size() == 0)
		{
			System.out.println("Deck not filled..");
			return;
		}
		else {
			for(int i = 0; i < 52; i ++)
			{
				System.out.println(cg.deck.get(i).id + " " + cg.deck.get(i).house + " " + cg.deck.get(i).value);
			}
		}
	}
	private void shuffleDeck(ArrayList<Card> deck) {

		if(deck.size() == 0)
		{
			System.out.println("Deck not filled..");
			return;
		}
		
		Random rand = new Random(); 
        
        for (int i = 0; i < 52; i++) 
        { 
            // Random for remaining positions. 
            int r = i + rand.nextInt(52 - i); 
              
             //swapping the elements 
             Card temp = deck.get(r); 
             deck.set(r,deck.get(i)); 
             deck.set(i,temp); 
               
        }
    }
	private static void giveCardToPlayer(CardGame cg, int i, ArrayList<Card> deck) {

		cg.players.get(i).add(deck.get(deck.size()-1));
		deck.remove(deck.size()-1);
	}
	
	private static void returnCardFromPlayer(CardGame cg,int i) {

		for(int j=0;j<cg.players.get(i).size();j++)
		{
			cg.deck.add(0, cg.players.get(i).get(j));
		}
		cg.players.get(i).remove(0);
	}
	
	private void addPlayers(ArrayList<ArrayList<Card>> players) {

		players.add(new ArrayList<Card>());
	}
	
	private static int playSingleCardGame(CardGame cg) {

		int winner = 0;
		Card winnerCard = new Card(-1,5,0);
		int i = 0;
		while(true)
		{
			giveCardToPlayer(cg, i, cg.deck);
			System.out.println("Player " + (i+1) + " has " + value_name[cg.players.get(i).get(0).value-1] + " of " + house_name[cg.players.get(i).get(0).house]);
			if(cg.players.get(i).get(0).house < winnerCard.house)
			{
				winner = i;
				winnerCard = cg.players.get(i).get(0);
				String number = "";// + cg.players.get(i).get(0).value;
			}
			else if(cg.players.get(i).get(0).house == winnerCard.house && cg.players.get(i).get(0).value > winnerCard.value)
				winner = i;
			i++;
			if(i == cg.players.size())
				break;
		}
		while(i>0)
		{
			returnCardFromPlayer(cg, i-1);
			i--;
			System.out.println("Cards added back to the bottom of the deck...");
		}
		return winner;
	}
	
	private void removePlayer(CardGame cg, int playerNumber) {

		if(cg.players.size() == 1)
			System.out.println("Removed the only player left...");
		else
			System.out.println("Removed player number : " + playerNumber);
		cg.players.remove(playerNumber - 1);
	}
	
	public static void main(String[] args) {
		
		CardGame cg = new CardGame();
		cg.deck = new ArrayList<Card>();
		cg.players = new ArrayList<ArrayList<Card>>();
		Scanner sc = new Scanner(System.in);
				
		do {
			System.out.println("\nOptions :");
			System.out.println("0 : Fill deck");
			System.out.println("1 : Print deck");
			System.out.println("2 : Shuffle deck");
			System.out.println("3 : Add player");
			System.out.println("4 : Play single card game");
			System.out.println("5 : Remove player");
			System.out.println("6 : Check no. of players");
			System.out.println("7 : Check the deck size");
			System.out.println("-1 : Exit");
			try {
				System.out.println("Enter option:");
				options = sc.nextInt();
				switch(options) {
					case 0:
						cg.fillDeck(cg.deck);
						break;
					case 1:
						cg.printDeck(cg);
						break;
					case 2:
						cg.shuffleDeck(cg.deck);
						break;
					case 3:
						cg.addPlayers(cg.players);
						System.out.println("No of players : " + cg.players.size());
						break;
					case 4:
						int winner = playSingleCardGame(cg);
						System.out.println("Winner : Player " + (winner+1));
						break;
					case 5:
						if(cg.players.size()==0)
						{
							System.out.println("There are no players added yet");
							break;
						}
						else if(cg.players.size() == 1)
						{
							cg.removePlayer(cg, 1);
							break;
						}
						System.out.println("Please enter player to be removed {value > 1} : ");
						int playerNumber = sc.nextInt();
						if (playerNumber < 1)
						{
							System.out.println("Select a correct player number...");
							break;
						}
						cg.removePlayer(cg, playerNumber);
						break;
					case 6:
						System.out.println(cg.players.size());
						break;
					case 7:
						System.out.println("Size of deck : " + cg.deck.size());
						break;
					case -1:
						System.out.println("Thank you for playing...");
						break;
					default:
						System.out.println("Please enter the correct option...");
						break;
				}
			}
			catch(InputMismatchException ime)
			{
				System.out.println("Please enter a valid option.. Options are between numbers 0 to 7...");
				options = sc.nextInt();
			}
			
			}while(options != -1);
		sc.close();
	}


	
}
