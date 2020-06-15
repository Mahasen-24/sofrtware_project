/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_software;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import java.util.Scanner;
import java.util.Vector;
import person.User;
import person.adminstrator;
import person.owner;
import person.player;
import system.available_hours;
import system.playground;
/**
 *
 * @author pc
 */

public class project_software {

    /**
     * @param args the command line arguments
     * @throws com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws MessagingException, Exception{
  
      Vector<player> all_players =new Vector<>();
       Vector<owner> all_owners =new Vector<>();
       Vector<playground> all_playground =new Vector<>();
       
       
       adminstrator admin=new adminstrator();
       String admin_email="admain@gmail.com";
       String admin_password="ADMAIN2018";
      
       
       String again="yes";
        int choice;
        while(again.equals("yes")||again.equals("Yes"))
        {
          Scanner inp=new Scanner(System.in);
        System.out.println("Choose one of the following options :");
        System.out.println("    1)Sign up as player");
        System.out.println("    2)Sign up as playground owner");
        System.out.println("    3)Log in");
        System.out.println("    4)Exit");
        choice=inp.nextInt();
        
        switch(choice)
        {
            case 1:
            {
                player new_player=new player();
                new_player=new_player.sign_up();
                    if(new_player==null)
                     {
                         again="yes";
                         
                     } 
                     else
                     {
                        all_players.add(new_player);
                     }
                      
                
                break;
            }
            case 2:
            {
                owner new_owner=new owner();
                new_owner=new_owner.sign_up();
                    if(new_owner==null)
                     {
                         again="yes";
                     } 
                     else
                     {
                        all_owners.add(new_owner);
                     }
                      
                
                break;
            }   
            case 3:
            {
                String role;
             
                System.out.println("Enter role(player / playground_owner / admin) :");
                role=inp.next();
              
                 
                if(role.equals("player")||role.equals("Player"))
                {
                    player new_player=new player();
                     new_player=new_player.player_login(all_players);
                    if( new_player!=null)
                    {
                     String Again="yes";
                     int Choice;
                    while(Again.equalsIgnoreCase("yes"))
                    {
                        System.out.println("Choice one of the following :");
                        System.out.println("    1) Book a time slot of a playground.");
                        System.out.println("    2) View playgrounds and filter them.");
                        System.out.println("    3)Create team.");
                        System.out.println("    4)Check invetations.");
                        System.out.println("    5)view my team.");
                        System.out.println("    6) Log out.");
                        Choice=inp.nextInt();
                        switch(Choice)
                        {
                            case 1:
                            {
                                new_player.viewPlayground(all_playground);
                                System.out.println("Do you want to do any other operation? (Enter yes or no) ");
                                Again=inp.next();
                                break;
                            }
                            case 2:
                            {
                                new_player.filterPlayground(all_playground);
                                System.out.println("Do you want to do any other operation? (Enter yes or no) ");
                                Again=inp.next();
                                break;
                            }
                            case 3:
                            {
                                //create_team();
                                new_player.createTeam(all_players);
                                System.out.println("Do you want to do any other operation? (Enter yes or no) ");
                                Again=inp.next();

                                break;
                            }                          
                            case 4:
                            {
                                //check_invetation();
                                new_player.displayInvetation(all_players);
                                System.out.println("Do you want to do any other operation? (Enter yes or no) ");
                                Again=inp.next();
                                break;

                            }
                            case 5:
                            {
                                new_player.getMyTeam().displayMyTeam();
                                System.out.println("Do you want to do any other operation? (Enter yes or no) ");
                                Again=inp.next();
                                break;
                            }
                            case 6:
                            {
                                Again="no";
                                break;
                            }
                        }
                      }
                    }
                    else
                    {
                        System.out.println("login faild");
                    }
                }
                else if("admin".equals(role)||"Admin".equals(role))
                {
                    admin.admin_login(admin_email,admin_password);
                    String Again="yes";
                    int Choice;
                    while(Again.equals("yes")||Again.equals("Yes"))
                    {
                        System.out.println("Choice one of the following :");
                        System.out.println("    1) Approve playground.");
                        System.out.println("    2) Log out");
                        Choice=inp.nextInt();
                        switch(Choice)
                        {
                            case 1:
                            {
                                //approve_playground(Vector<playground> ground);
                                admin.approve_playground(all_playground);
                                System.out.println("Do you want to do any other operation? (Enter yes or no) ");
                                Again=inp.next();
                                break;
                            }
                            
                            case 2:
                            {
                                Again="no";
                                break;
                            }
                        }
                    }
                }
                else if("playground_owner".equals(role))
                {
                    owner new_owner=new owner();
                     new_owner=new_owner.owner_login(all_owners);
                    if( new_owner!=null)
                    {
                       String Again="yes";
                       int Choice;
                       while(Again.equalsIgnoreCase("yes"))
                       {

                           System.out.println("Choose one of the following :");
                           System.out.println(" 1) Add playground.");
                           System.out.println(" 2) View booking");
                           System.out.println(" 3) View playground approved");
                           System.out.println(" 4) Log out.");
                           Choice=inp.nextInt();
                           switch(Choice)
                           {
                               case 1:
                               {
                                   all_playground.add(new_owner.add_playground());
                                    System.out.println("Do you want to do any other operation? (Enter yes or no) ");
                                    Again=inp.next();
                                   break;
                               }
                               case 2:
                               {
                                   new_owner.displayBooking();
                                    System.out.println("Do you want to do any other operation? (Enter yes or no) ");
                                    Again=inp.next();
                                   break;
                               }
                               case 3:
                               {
                                   new_owner.playgroundApproved();
                                    System.out.println("Do you want to do any other operation? (Enter yes or no) ");
                                    Again=inp.next();
                                   break;
                               }
                               case 4:
                                {
                                    Again="no";
                                    break;
                                }
                           }                          
                       }
                       break;
                    }
                    else
                    {
                        System.out.println("login faild");
                    }
                }
                else
                {
                    System.out.println("Invalid role");
                
                }
                
                         
                 break;
            }
            case 4:
            {
                System.exit(0);
            }
               
            }
     
        }
         
        }
      }
       
     