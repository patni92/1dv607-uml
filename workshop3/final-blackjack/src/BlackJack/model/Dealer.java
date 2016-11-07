package BlackJack.model;

import java.util.ArrayList;

import BlackJack.model.rules.*;

public class Dealer extends Player {

  private Deck m_deck;
  private INewGameStrategy m_newGameRule;
  private IHitStrategy m_hitRule;
  private IWinStrategy m_winStrategy;
  private ArrayList<IDealObserver> m_subscribers;

  public Dealer(RulesFactory a_rulesFactory) {
  
    m_newGameRule = a_rulesFactory.GetNewGameRule();
    m_hitRule = a_rulesFactory.GetHitRule();
    m_subscribers = new ArrayList<IDealObserver>(); 
    m_winStrategy = a_rulesFactory.GetWinRule();
    
    /*for(Card c : m_deck.GetCards()) {
      c.Show(true);
      System.out.println("" + c.GetValue() + " of " + c.GetColor());
    }    */
  }
  
  
  public void addObserver(IDealObserver o) {
	  m_subscribers.add(o);
  }
  
  
  public boolean NewGame(Player a_player) {
    if (m_deck == null || IsGameOver()) {
      m_deck = new Deck();
      ClearHand();
      a_player.ClearHand();
      return m_newGameRule.NewGame(m_deck, this, a_player);   
    }
    return false;
  }
  
  public void dealCard(Player a_player, boolean show) {
	  Card c = m_deck.GetCard();
      c.Show(show);
      a_player.DealCard(c);
      
      for (IDealObserver observer : m_subscribers ) {
    	    observer.update();
    	}
  }

  public boolean Hit(Player a_player) {
    if (m_deck != null && a_player.CalcScore() < g_maxScore && !IsGameOver()) {
     dealCard(a_player, true);
      
      return true;
    }
    return false;
  }
  
  public boolean Stand() {
	    if(m_deck != null) {
	      ShowHand();

	      while(m_hitRule.DoHit(this)) {
	    	  dealCard(this, true);
	      }

	      return true;
	    }
	    return false;
	  }

  public boolean IsDealerWinner(Player a_player) {
	  return m_winStrategy.dealerWin(a_player, this);
  }

  public boolean IsGameOver() {
    if (m_deck != null && m_hitRule.DoHit(this) != true) {
        return true;
    }
    return false;
  }
  
}