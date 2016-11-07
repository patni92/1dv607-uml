package BlackJack.controller;

import BlackJack.view.IView;
import BlackJack.model.Game;
import BlackJack.model.IDealObserver;

public class PlayGame implements IDealObserver {
	private IView a_view;
	private Game a_game;
	
	
  public boolean Play(Game g, IView v) {
	  
	  this.a_game = g;
	  this.a_view = v;
	  this.a_game.addObserver(this);


  
	    a_view.DisplayWelcomeMessage();

	    a_view.DisplayDealerHand(a_game.GetDealerHand(), a_game.GetDealerScore());

	    a_view.DisplayPlayerHand(a_game.GetPlayerHand(), a_game.GetPlayerScore());

    if (a_game.IsGameOver())
    {
        a_view.DisplayGameOver(a_game.IsDealerWinner());
    }

    a_view.GetInput();
    
    if (a_view.newGame())
    {
        a_game.NewGame();
    }
    else if (a_view.canHit())
    {
        a_game.Hit();
    }
    else if (a_view.canStand())
    {
        a_game.Stand();
    }

    return ! a_view.canQuit();
  }


public void update() {
	
	a_view.emptyView();
	 a_view.DisplayDealerHand(a_game.GetDealerHand(), a_game.GetDealerScore());
	  a_view.DisplayPlayerHand(a_game.GetPlayerHand(), a_game.GetPlayerScore());
	try {

       Thread.sleep(2000);

   } catch (Exception e) {

       e.printStackTrace();

   }
	
	 
	 
	
}
}