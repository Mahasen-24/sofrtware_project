/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;
import java.util.Vector;
import person.player;

/**
 *
 * @author pc
 */
public class team {

    /**
     *
     */
    private Vector<player> players;
 
    /**
     *
     */
    public team()
 {
    players= new Vector<>();
 }

    /**
     *
     * @param player
     */
    public void setTeam(Vector<player> player)
 {
     players=player;
     
 }
 
    /**
     *
     * @return
     */
    public Vector<player> getTeam()
 {
     return players;
     
 }

    /**
     *
     */
    public void displayMyTeam()
    {
        if(players.size()!=0)
        {
        System.out.println(" your team:");
        System.out.println( "leader: "+players.elementAt(0).getName());
         System.out.println("member:-");
        for(int k=1;k<players.size();k++)
        {
            System.out.println("Name:"+players.elementAt(k).getName()+"  Email: "+players.elementAt(k).getEmail()+"response: "+players.elementAt(k).getResponse());
        }
        }
        else{
             System.out.println(" you do not have team yet");
        }
    }
    
  
      
  }
  
  

