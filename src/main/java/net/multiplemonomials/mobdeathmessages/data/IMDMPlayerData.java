package net.multiplemonomials.mobdeathmessages.data;

import net.multiplemonomials.mobdeathmessages.chat.KillingSpree;

public interface IMDMPlayerData
{
	/**
	 * Get the kill score (the number of kills minus the number of deaths) for this player 
	 * @return
	 */
    public int getKillScore();
    
	public void setKillScore(int killScore);

	/**
	 * Get the killing spree level for this player
	 * @return
	 */
	public KillingSpree getCurrentKillingSpree();
	
	public void setCurrentKillingSpree(KillingSpree currentKillingSpree);
}
