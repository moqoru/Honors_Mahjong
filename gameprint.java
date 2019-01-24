import java.util.Scanner;
public class gameprint
{
	Scanner input=new Scanner(System.in);
	public gameprint(){}
	public void mainprint(CPU_deck c_deck, Player_deck p_deck, deck tiles, boolean check, String GET)
	{
		if (check==true)
		{
			System.out.println();
			System.out.println("---------------------------------------------");
			System.out.println(c_deck.getinfo(false,false,""));
			System.out.println(c_deck.throwns());
			System.out.println("CPU - " + c_deck.scoreget() + " : " + p_deck.scoreget() + " - Player");
			System.out.println("Remain Cards : " + tiles.numinfo());
			System.out.println(p_deck.throwns());
			System.out.println(p_deck.getinfo(true,GET));
			System.out.println("---------------------------------------------");
		}
		else
		{
			System.out.println();
			System.out.println("---------------------------------------------");
			System.out.println(c_deck.getinfo(false,false,""));
			System.out.println(c_deck.throwns());
			System.out.println("CPU - " + c_deck.scoreget() + " : " + p_deck.scoreget() + " - Player");
			System.out.println("Remain Cards : " + tiles.numinfo());
			System.out.println(p_deck.throwns());
			System.out.println(p_deck.getinfo(false,""));
			System.out.println("---------------------------------------------");
		}
	}
	public boolean vspoint(CPU_deck c_deck, Player_deck p_deck)
	{
		boolean finish;
		System.out.println();
		if (c_deck.scoreget()<=0)
		{
			System.out.println("Game Set. Player WIN!");
			System.out.println("CPU - " + c_deck.scoreget() + " : " + p_deck.scoreget() + " - Player\n");
			System.out.println("Go back to title after 5 seconds...");
			try { Thread.sleep(5000); } catch (InterruptedException e) {}
			finish = true;
		}
		else if (p_deck.scoreget()<=0)
		{
			System.out.println("Game Set. CPU WIN!");
			System.out.println("CPU - " + c_deck.scoreget() + " : " + p_deck.scoreget() + " - Player\n");
			System.out.println("Go back to title after 5 seconds...");
			try { Thread.sleep(5000); } catch (InterruptedException e) {}
			finish = true;
		}
		else
		{
			System.out.println("Game goes on. It ain't over until it's over!");
			System.out.println("CPU - " + c_deck.scoreget() + " : " + p_deck.scoreget() + " - Player\n");
			System.out.println("Next game will start in 5 seconds...");
			try { Thread.sleep(5000); } catch (InterruptedException e) {}
			finish = false;
		}
		System.out.println();
		if (finish==true) { return false; }
		else { return true;}
	}
	public boolean cardthrow_p(Player_deck p_deck)
	{
		System.out.print("Enter the Card's Alphabet you wants to throw (press Q if you want to stop the game): ");
		boolean comp = false;
		String GET;
		do{
			GET = input.next();
			GET = GET.toUpperCase();
			if (GET.equalsIgnoreCase("Q")) {break;}
			try {p_deck.discard(GET); comp=true;}
			catch (Invalidcards exe) {System.out.print(exe); comp=false;}
			if (comp==true) break;
		}while(true);
		if (GET.equalsIgnoreCase("Q")) return true;
		else return false;
	}
	public void cardthrow_c(CPU_deck c_deck)
	{
		String GET="";
		GET = c_deck.AI(0,GET);
		try {c_deck.discard(GET);}
		catch (Invalidcards exe) {System.out.println("CPU?!"+exe);}
	}
	public int tsumoprint_p(Player_deck p_deck, deck tiles, boolean flower)
	{
		int scoremove;
		System.out.println();
		System.out.println("---------------------------------------------");
		System.out.println("Player's Tsumo!");
		System.out.println(p_deck.getinfo(false,""));
		System.out.println();
		scoremove = p_deck.scoreprint(tiles.numinfo(),true,flower,false);
		System.out.println("You get " + scoremove + " points!");
		System.out.println("---------------------------------------------");
		return scoremove;
	}
	public int ronprint_p(Player_deck p_deck, deck tiles, boolean flower, String GET, boolean chang)
	{
		int scoremove;
		System.out.println();
		System.out.println("---------------------------------------------");
		p_deck.pick(GET);
		System.out.println("Player's Ron!");
		System.out.println(p_deck.getinfo(true,GET));
		System.out.println();
		scoremove = p_deck.scoreprint(tiles.numinfo(),false,false,chang);
		System.out.println("You get " + scoremove + " points!");
		System.out.println("---------------------------------------------");
		return scoremove;
	}
	public int tsumoprint_c(CPU_deck c_deck, deck tiles, boolean flower)
	{
		int scoremove;
		System.out.println();
		System.out.println("---------------------------------------------");
		System.out.println("CPU's Tsumo!");
		System.out.println(c_deck.getinfo(true,false,""));
		System.out.println();
		scoremove = c_deck.scoreprint(tiles.numinfo(),true,flower,false);
		System.out.println("CPU get " + scoremove + " points!");
		System.out.println("---------------------------------------------");
		return scoremove;
	}
	public int ronprint_c(CPU_deck c_deck, deck tiles, boolean flower, String GET, boolean chang)
	{
		int scoremove;
		System.out.println();
		System.out.println("---------------------------------------------");
		c_deck.pick(GET);
		System.out.println("CPU's Ron!");
		System.out.println(c_deck.getinfo(true,true,GET));
		System.out.println();
		scoremove = c_deck.scoreprint(tiles.numinfo(),false,false,chang);
		System.out.println("CPU get " + scoremove + " points!");
		System.out.println("---------------------------------------------");
		return scoremove;
	}
}