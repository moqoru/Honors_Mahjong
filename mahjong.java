import java.util.Scanner;
import java.io.*;
public class mahjong
{
	public static void main(String args[]) throws Exception
	{
		gameprint ot = new gameprint();
		Scanner input = new Scanner(System.in);
		File file = new File("mahjongStore.txt");//stored information of last game
		if (!file.exists()) try (PrintWriter output = new PrintWriter(file);) {}
		String nothing = "";//for loading garbage
		String quit = "";
		do{
			deck tiles = new deck();
			CPU_deck c_deck = new CPU_deck();
			Player_deck p_deck = new Player_deck();
			rulebook book = new rulebook();
			System.out.println("KNU 2017 1st Semester Java Programming Team Project");
			System.out.println();
			System.out.println("--------------------------------------------------------------------");
			System.out.println("                          <HONORS MAHJONG>");
			System.out.println("--------------------------------------------------------------------");
			System.out.println();
			System.out.println("                                      Made by 2014104166, 2012104207");
			System.out.println();
			System.out.print("Enter the function (Start Game - S, Load - L, Rule - R, Quit - Q) : ");
			String start = input.next();
			if (start.equalsIgnoreCase("S") || start.equalsIgnoreCase("L"))
			{
				Scanner sc = new Scanner(file);
				int round=0; boolean dealer=true;
				System.out.println();
				if (start.equalsIgnoreCase("L"))
				{
					if(sc.hasNext()){
					nothing = sc.next();
					round = sc.nextInt()-1;	nothing = sc.next();
					dealer = sc.hasNextInt(); nothing = sc.next(); nothing = sc.next();
					int no = sc.nextInt();
					c_deck.scorestore(no); nothing = sc.next();
					no = sc.nextInt(); p_deck.scorestore(no); nothing = sc.next();
					}
					else {System.out.println("No game stored. Please restart the game."); break;}
				}
				System.out.println("Starting the game.");
				System.out.println("If you quits the game, the game state will saved automatically.");
				try { Thread.sleep(5000); } catch (InterruptedException e) {}
				c_deck.scorereset(); p_deck.scorereset();
				do{ //game start
					round++;
					System.out.println();
					System.out.print("< ROUND " + round + " > ");
					if(start.equalsIgnoreCase("S")){
					if (dealer==true) { System.out.println("- Player First."); }
					else { System.out.println("- CPU First."); }}
					tiles.shuffle(); c_deck.reset(); p_deck.reset();
					String GET=""; boolean flag = false; boolean flower=false;
					int win=0, scoremove=0;
					for (int i=0; i<7; i++)//cpu's first seven cards
					{
						try {GET = tiles.getcards(); c_deck.pick(GET);}
						catch (Runoutcards ex) {System.out.println(ex);}
					}
					for (int i=0; i<7; i++)//player's first seven cards
					{
						try {GET = tiles.getcards(); p_deck.pick(GET);}
						catch (Runoutcards ex) {System.out.println(ex);}
					}
					int dealch=0;
					if (start.equalsIgnoreCase("L"))
					{
						dealch = sc.nextInt(); nothing = sc.next();
						for (int i = 0; i < tiles.cards.length; i++) tiles.cards[i] = sc.nextInt(); nothing = sc.next();
						tiles.num = sc.nextInt();  nothing = sc.next();
						for (int i = 0; i < 7; i++)	c_deck.number[i] = sc.nextInt(); nothing = sc.next();
						for (int i = 0; i < 7; i++)	c_deck.furiten[i] = sc.nextInt(); nothing = sc.next();
						nothing = sc.next();
						if(nothing.equals("nothing")) c_deck.thrown_cards = "";
						else c_deck.thrown_cards = nothing; nothing = sc.next();
						for (int i = 0; i < 7; i++)	p_deck.number[i] = sc.nextInt(); nothing = sc.next();
						for (int i = 0; i < 7; i++)	p_deck.furiten[i] = sc.nextInt(); nothing = sc.next();
						nothing = sc.next();
						if(nothing.equals("nothing")) p_deck.thrown_cards = "";
						else p_deck.thrown_cards = nothing; nothing = sc.next();
						GET = sc.next();
					}
					sc.close();
					do{
						flower=false;
						if (dealer==true)//player's turn
						{
							if(start.equalsIgnoreCase("S")){
							try {GET = tiles.getcards(); p_deck.pick(GET);}
							catch (Runoutcards ex) {System.out.println(ex); dealer=false; flag=true; break;}}
							start = "S";
							ot.mainprint(c_deck,p_deck,tiles,true,GET);
							if ((p_deck.ankancheck()).equals("")==false)//ankan check (player)
							{
								System.out.print("You can " + p_deck.ankancheck() + " card kan. Kan? (Y/N) : ");
								String wantkan = input.next();
								if (wantkan.equalsIgnoreCase("Y"))
								{
									String ANK = p_deck.ankancheck();
									p_deck.kan(ANK);
									if (c_deck.wincheck(false, ANK)==true)//ankan-ron check (cpu)
									{
										scoremove = ot.ronprint_c(c_deck,tiles,flower,ANK,true);
										win=-1; dealer=false; flag=true; break;
									}
									try {GET = tiles.getcards(); p_deck.pick(GET);}
									catch (Runoutcards ex) {System.out.println(ex); dealer=false; flag=true; break;}
									ot.mainprint(c_deck,p_deck,tiles,true,GET);
									flower=true;
								}
							}
							else if (p_deck.sidekancheck(GET))//sidekan check (player)
							{
								ot.mainprint(c_deck,p_deck,tiles,false,"");
								System.out.print("You can " + GET + " card kan. Kan? (Y/N) : ");
								String wantkan = input.next();
								if (wantkan.equalsIgnoreCase("Y"))
								{
									p_deck.kan(GET);
									try {GET = tiles.getcards(); p_deck.pick(GET);}
									catch (Runoutcards ex) {System.out.println(ex); dealer=false; flag=true; break;}
									ot.mainprint(c_deck,p_deck,tiles,true,GET);
									flower=true;	
								}
							}
							if (p_deck.wincheck(true,GET)==true)//tsumo check (player)
							{
								System.out.print("You can tsumo to win. Tsumo? (Y/N) : ");
								String tsumocheck = input.next();
								if (tsumocheck.equalsIgnoreCase("Y"))
								{
									scoremove = ot.tsumoprint_p(p_deck,tiles,flower);
									win=1; dealer=true; flag=true; break;
								}
							}
							dealch=0;
							boolean b = ot.cardthrow_p(p_deck);//card throw (player) + quit if player wants
							if (b) {store(round, dealer, c_deck, p_deck, dealch, tiles, GET); quit = "Q"; break;}
							GET = Character.toString(p_deck.lastthrow());
							if (c_deck.wincheck(false,GET)==true)//ron check (cpu)
							{
								scoremove = ot.ronprint_c(c_deck,tiles,flower,GET,false);
								win=-1; dealer=false; flag=true; break;
							}
							else
							{
								if (c_deck.kancheck(GET)==true && (c_deck.AI(1,GET)).equals(""))//kan check (cpu)
								{
									c_deck.kan(GET);
									p_deck.called();
									try {GET = tiles.getcards(); c_deck.pick(GET);}
									catch (Runoutcards ex) {System.out.println(ex); dealer=false; flag=true; break;}
									flower=true;
									if (c_deck.wincheck(true,GET)==true)//kan-tsumo check (cpu)
									{
										scoremove = ot.tsumoprint_c(c_deck,tiles,flower);
										win=-1; dealer=false; flag=true; break;
									}
									System.out.println("CPU's kan for your " + GET + " card.");
									ot.cardthrow_c(c_deck);
									dealch=1;
								}
								if (c_deck.poncheck(GET)==true && (c_deck.AI(2,GET)).equals(""))//pon check (cpu)
								{
									c_deck.pon(GET);
									p_deck.called();
									System.out.println("CPU's pon for your " + GET + " card.");
									ot.cardthrow_c(c_deck);
									dealch=1;
								}
							}
							if (dealch==0) {dealer=false;}
						}
						else//cpu's turn
						{
							try {GET = tiles.getcards(); c_deck.pick(GET);}
							catch (Runoutcards ex) {System.out.println(ex); dealer=true; flag=true; break;}
							if ((c_deck.ankancheck()).equals("")==false)//ankan check (cpu)
							{
								String ANK = c_deck.ankancheck();
								c_deck.kan(ANK);
								if (p_deck.wincheck(false, ANK)==true)//ankan-ron check (player)
								{
									ot.mainprint(c_deck,p_deck,tiles,false,"");
									System.out.print("You can " + ANK + " card ron to win. Ron? (Y/N): ");
									String roncheck = input.next();
									if (roncheck.equalsIgnoreCase("Y"))
									{
										scoremove = ot.ronprint_p(p_deck,tiles,flower,ANK,true);
										win=1; dealer=true; flag=true; break;
									}
								}
								try {GET = tiles.getcards(); c_deck.pick(GET);}
								catch (Runoutcards ex) {System.out.println(ex); dealer=true; flag=true; break;}
								flower=true;
							}
							else if (c_deck.sidekancheck(GET))//sidekan check (cpu)
							{
								c_deck.kan(GET);
								try {GET = tiles.getcards(); c_deck.pick(GET);}
								catch (Runoutcards ex) {System.out.println(ex); dealer=true; flag=true; break;}
								flower=true;
							}
							if (c_deck.wincheck(true,GET)==true)//tsumo check (cpu)
							{
								scoremove = ot.tsumoprint_c(c_deck,tiles,flower);
								win=-1; dealer=false; flag=true; break;
							}
							dealch=0;
							
							ot.cardthrow_c(c_deck);
							
							GET = Character.toString(c_deck.lastthrow());
							if (p_deck.wincheck(false,GET)==true)//ron check (player)
							{
								ot.mainprint(c_deck,p_deck,tiles,false,"");
								System.out.print("You can " + GET + " card ron to win. Ron? (Y/N) : ");
								String roncheck = input.next();
								if (roncheck.equalsIgnoreCase("Y"))
								{
									scoremove = ot.ronprint_p(p_deck,tiles,flower,GET,false);
									win=1; dealer=true; flag=true; break;
								}
							}
							else
							{
								if (p_deck.kancheck(GET)==true)//kan check (player)
								{
									ot.mainprint(c_deck,p_deck,tiles,false,"");
									System.out.print("You can " + GET + " card kan. Kan? (Y/N) : ");
									String wantkan = input.next();
									if (wantkan.equalsIgnoreCase("Y"))
									{
										p_deck.kan(GET);
										c_deck.called();
										try {GET = tiles.getcards(); p_deck.pick(GET);}
										catch (Runoutcards ex) {System.out.println(ex); dealer=false; flag=true; break;}
										ot.mainprint(c_deck,p_deck,tiles,true,GET);
										flower=true;
										if (p_deck.wincheck(true,GET)==true)
										{
											System.out.print("You can tsumo to win. Tsumo? (Y/N) : "); //kan-tsumo check (player)
											String tsumocheck = input.next();
											if (tsumocheck.equalsIgnoreCase("Y"))
											{
												scoremove = ot.tsumoprint_p(p_deck,tiles,flower);
												win=1; dealer=true; flag=true; break;
											}
										}
										ot.cardthrow_p(p_deck);
										dealch=1;
									}
								}
								if (p_deck.poncheck(GET)==true)//pon check (player)
								{
									ot.mainprint(c_deck,p_deck,tiles,false,"");
									System.out.print("You can " + GET + " card pon. Pon? (Y/N) : ");
									String wantpon = input.next();
									if (wantpon.equalsIgnoreCase("Y"))
									{
										p_deck.pon(GET);
										c_deck.called();
										ot.mainprint(c_deck,p_deck,tiles,false,"");
										ot.cardthrow_p(p_deck);
										dealch=1;
									}
								}
							}
							if (dealch==0) dealer=true;
						}
					}while(flag==false);
					if(quit.equalsIgnoreCase("Q"))	break;
					c_deck.scorechange((-1)*win*scoremove);
					p_deck.scorechange(win*scoremove);
				}while(ot.vspoint(c_deck,p_deck)==true);
			}
			if(quit.equalsIgnoreCase("Q"))
				{
					System.out.println("Good bye!");
					break;
				}
			else if (start.equalsIgnoreCase("R"))
			{
				book.rule();
			}
			else if (start.equalsIgnoreCase("Q"))
			{
				System.out.println("Good bye!");
				break;
			}
			else
			{
				System.out.println("Wrong Alphabet. Type Again.");
			}
		System.out.println();
		}while(true);
	}
	
	public static void store(int round, boolean dealer, CPU_deck c_deck, Player_deck p_deck, int dealch, deck tiles, String GET) throws Exception
	{
		File file = new File("mahjongStore.txt");
		try(PrintWriter op = new PrintWriter(file);)
		{
			op.println("round: " + round);
			op.print("dealer: ");
			if (dealer) op.println(1);
			else op.println(0.5);
			op.println("CPU_score: " + c_deck.score);
			op.println("player_score: " + p_deck.score);
			op.println("dealch: " + dealch);
			op.print("deck_array: ");
			for (int i = 0; i < tiles.cards.length; i++) op.printf("%d ", tiles.cards[i]);
			op.println("    number: " + tiles.num);
			op.print("CPU_number_info: ");
			for (int i = 0; i < 7; i++) op.printf("%d ", c_deck.number[i]); op.println("");
			op.print("CPU_furiten_info: ");
			for (int i = 0; i < 7; i++) op.printf("%d ", c_deck.furiten[i]); op.println("");
			if (c_deck.thrown_cards.equals("")) {op.print("thrown_cards_by_CPU: "); op.println("nothing");}
			else op.println("thrown_cards_by_CPU: " + c_deck.thrown_cards);
			op.print("plyer_number_info: ");
			for (int i = 0; i < 7; i++) op.printf("%d ", p_deck.number[i]); op.println("");
			op.print("plyer_furiten_info: ");
			for (int i = 0; i < 7; i++) op.printf("%d ", p_deck.furiten[i]); op.println("");
			if (p_deck.thrown_cards.equals("")) {op.print("thrown_cards_by_player: "); op.println("nothing");}
			else op.println("thrown_cards_by_player: " + p_deck.thrown_cards);
			op.println("card_that_player_got: " + GET);
		}
	}
}