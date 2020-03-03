
public class Card { 
    	int id;
    	int value; 
        int house;
        // Constructor to create a new Card 
        // Next is by default initialized 
        Card() { id = 0; value = 0; house = 0;}
        //Next parameterized constructor for initializing with value.
        Card(int id, int h, int v) { this.id = id; house = h; value = v; } 
    }