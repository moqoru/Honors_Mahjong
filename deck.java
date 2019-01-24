public class deck
{
	int[] cards = new int[28];
	String[] ranks = {"E","S","W","N","O","G","R"};
	int num = -1;
	public deck()//deck reset : first time only
	{
		for (int i = 0; i < cards.length; i++) {cards[i] = i;}
	}
	public void shuffle()
	{
		num = -1;
		for (int i = 0; i < cards.length; i++)
		{
			int index = (int)(Math.random() * cards.length);
			int temp = cards[i];
			cards[i] = cards[index];
			cards[index] = temp;
		}
	}
	public String getcards() throws Runoutcards//contains run out of cards
	{
		num++;
		if (num>=cards.length) {throw new Runoutcards();}
		else {return ranks[cards[num]%7];}
	}
	public int numinfo()
	{
		return (27-num);
	}
}