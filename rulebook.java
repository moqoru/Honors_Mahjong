public class rulebook
{
	public rulebook() {}
	public void rule()
	{
		System.out.println("<The rule of Honors Mahjong>");
		System.out.println();
		System.out.println("Honors Mahjong is a card game use 4 units each for E,S,W,N,O,G,R cards.");
		System.out.println("If some player make special form by cards, he wins for the game.");
		System.out.println("CPU and Player have 15 points each when first game starts.");
		System.out.println("When one player wins, winner takes loser's point.");
		System.out.println("If one player's score is lower than zero, game is over and the other player is winner.");
		System.out.println();
		System.out.println("When Game Starts, after shuffling card deck, CPU and Player get 7 cards each.");
		System.out.println("Previous game winner first get one more card, than he throws his needless card.");
		System.out.println("Than the other player get one more card, than he throws his needless card.");
		System.out.println("In this way, players get and throw cards in rotation until card run out.");
		System.out.println("If the card runs out, the game draw and second player first starts for next game.");
		System.out.println();
		System.out.println("If someone can win the game with his receive card, he can announce 'Tsumo' for win.");
		System.out.println("And, if someone can win the game with the other player's thrown card, he can announce 'Ron' for win.");
		System.out.println("But you CAN'T Ron if you already throw the card of a piece with the other player's thrown card.");
		System.out.println();
		System.out.println("Sometimes you can take the other player's card to make your card.");
		System.out.println("But you need to show some of your cards that you are in situation for making forms.");
		System.out.println();
		System.out.println("If you have a pair and the other player throws same card, you can announce 'Pon'.");
		System.out.println("Than you take that card and show card to other player with 'XX+X' form.");
		System.out.println("If you announce pon, that means you get the other player's card instead of card deck.");
		System.out.println("So after you throw one card, next time the other player gets another card.");
		System.out.println();
		System.out.println("If you have a triplet and the other player throws same card, you can announce 'Kan'.");
		System.out.println("This is called 'Daiminkan'. You take that card and show card to other player with 'XXX+X' form.");
		System.out.println();
		System.out.println("And sometimes you can Kan with your cards.");
		System.out.println("If you have a quadruplet, you also can announce 'Kan'.");
		System.out.println("This is called 'Ankan'. You take that card and show card to other player with 'XXXX' form.");
		System.out.println("If you have one pon and you have the same card with pon, again you can announce 'Kan'.");
		System.out.println("This is called 'Gakan', and you take that card and show card to other player with 'XX+X+X' form.");
		System.out.println();
		System.out.println("If you announce all types of Kan, you treats a quadruplet as same as a triplet, that means you need one more card.");
		System.out.println("So, in contrast for Pon, you get one more card right after Kan.");
		System.out.println();
		System.out.println("<Criterias of Honors Mahjong>");
		System.out.println();
		System.out.println("1. Two triplets or quadruplets + one pair");
		System.out.println("- One closed triplet : 1 point. Make one triplets without Pon.");
		System.out.println("- Two closed triplets : 3 points. Make two triplets without Pon.");
		System.out.println("- One closed kan : 2 point. Announce one Ankan.");
		System.out.println("- Two kans : 3 points. Announce two Kan.");
		System.out.println("- Two closed kans : 5 points. Announce two Ankan.");
		System.out.println("- Three dragons : 4 points. Make your card form only use O, G, or R.");
		System.out.println("- Two dragons : 3 points. Make two triplets or quadruplets using O, G, or R.");
		System.out.println("- Three winds : 3 points. Make your card form only use E, S, W, or N.");
		System.out.println("- Two winds : 2 points. Make two triplets or quadruplets using E, S, W, or N.");
		System.out.println("(* Caution : If you don't have ANY points, you can't win!)");
		System.out.println("(- Example : If you have SS WW+W OOO+O, You have 0 points, so you can't win.)");
		System.out.println();
		System.out.println("2. Four pairs");
		System.out.println("- Dragon pairs : 3 points. Make your card form only use O, G, or R.");
		System.out.println("- Wind pairs : 3 points. Make your card form only use E, S, W, or N.");
		System.out.println("- Four pairs : 1 point. Dosen't apply for above two forms.");
		System.out.println();
		System.out.println("3. All one card each + one pair");
		System.out.println("- Seven orphans 7 wait : 3 points / 2 points (furiten tsumo). Before Tsumo or Ron, you already got all 7 different cards.");
		System.out.println("- Seven orphans : 1 point. Dosen't apply for above form.");
		System.out.println();
		System.out.println("4. Special criteria");
		System.out.println("- Heavenly hand : 4 points. First player's first get card for Tsumo.");
		System.out.println("- Earthly hand : 2 points. Second player's first get card for Tsumo.");
		System.out.println("- Hand of man : 2 points. First player's first thrown card for Second player's Ron.");
		System.out.println("- Last draw : 1 point. Last card for Tsumo.");
		System.out.println("- Last discard : 1 point. Last card for Ron.");
		System.out.println("- Dead wall draw : 1 point. Get card right after Kan for Tsumo.");
		System.out.println("- Robbing a quad : 1 point. The other player's ankan card is your Seven Orphans Ron card.");
	}
}