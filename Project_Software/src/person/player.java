/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package person;
import java.util.Properties;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import system.playground;
import system.team;

/**
 *
 * @author pc
 */
public class player extends User{

    /**
     *
     */
    Scanner input=new Scanner(System.in);

    /**
     *
     */
    private team myteam=new team(); 

    /**
     *
     */
    private String accept;

    /**
     *
     */
    private Vector<player> sends=new Vector<>();
    
    /**
     *
     */
    public player()
    {
        accept="unread";
    }
   
    /**
     *
     * @param sender_name
     * @param reciever
     * @throws MessagingException
     */
    public void send_invetation(String sender_name,String reciever) throws MessagingException
    {
        Properties properties=new Properties();
      properties.put("mail.smtp.auth", "true");
      properties.put("mail.smtp.starttls.enable","true");
      properties.put("mail.smtp.host","smtp.gmail.com");
      properties.put("mail.smtp.port", "587");
      properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
      properties.put("mail.smtp.host", "smtp.gmail.com");    
        properties.put("mail.smtp.socketFactory.port", "587");       
       
      
      final String sender="gofoapplication@gmail.com";
      final String password="go12345fo";
      
     
      Session session = Session.getDefaultInstance(properties,    
         new javax.mail.Authenticator() {    
         protected PasswordAuthentication getPasswordAuthentication() {    
         return new PasswordAuthentication(sender,password);  
         }    
        });
        MimeMessage message=new MimeMessage(session);
      try {
          message.setFrom(new InternetAddress(sender));
          message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(reciever));
          message.setSubject("Team invetation");
          message.setText("Hello\n"+sender_name+" invite you to join his team,\n"+
                  "go to your account at GoFo Application to accept or refuse his invetation");
      } catch (Exception ex) {
          Logger.getLogger(player.class.getName()).log(Level.SEVERE, null, ex);
      }
        javax.mail.Transport.send(message);
        System.out.println("We send invetation to ("+reciever+" ) with your invetation");
    }
    
    /**
     *
     * @param p
     */
    public void setSends(player p)
    {
        sends.add(p);
    }

    /**
     *
     * @return
     */
    public team getMyTeam()
    {
        return myteam;
    }

    /**
     *
     * @param t
     */
    public void setMyTeam(team t)
    {
       myteam=t;
    }

    /**
     *
     * @param player
     * @throws MessagingException
     */
    public void createTeam(Vector<player> player) throws MessagingException
    {
        
        Vector<player> players=new Vector<>();
        for(int i=0;i<player.size();i++)
        {
            if(player.elementAt(i).getEmail()==getEmail())
            {
                players.add(0,player.elementAt(i));
            }
            else{
            System.out.println(i+1+") " + player.elementAt(i).getName()+ "  "+player.elementAt(i).getEmail());
            }
        }
        System.out.println("select your team:");
         int choice;
        for(int k=1;k<4;k++)
        {
            choice=input.nextInt();
            send_invetation(players.elementAt(0).getName(),player.elementAt(choice-1).getEmail());
            player.elementAt(choice-1).setSends(players.elementAt(0));
            players.add(k, player.elementAt(choice-1));
            
        }
        
        myteam.setTeam(players);
    }

    /**
     *
     * @return
     */
    public String getResponse()
    {
        return accept;
    }

    /**
     *
     * @param a
     */
    public void setResponse(String a)
    {
       accept=a;
    }
   
    /**
     *
     * @param player
     */
    public void displayInvetation(Vector<player> player)
    {
        if(sends.size()!=0)
        {
            for(int i=0;i<sends.size();i++)
            {
                 System.out.println(i+1+")"+"Name:"+sends.elementAt(i).getName()+"  Email: "+sends.elementAt(i).getEmail());
            }
             System.out.println("select your invetation  you want to accept");
             int choice;
             choice=input.nextInt();
             for(int i=1;i<4;i++)
             {
                 for(int z=0;z<player.size();z++)
                 {
                     if(player.elementAt(z).getEmail()==getEmail())
                     {
                         if(player.elementAt(z)== sends.elementAt(choice-1).getMyTeam().getTeam().elementAt(i))
                         {
                          sends.elementAt(choice-1).getMyTeam().getTeam().elementAt(i).setResponse("accept");
                         player.elementAt(z).setMyTeam(sends.elementAt(choice-1).getMyTeam()); 
                         }
                        
                     }
                 }

             }
        }
        else{
            System.out.println("you do not have any invetation");
        }
    }
    
    /**
     *
     * @param p
     */
    public void viewPlayground(Vector<playground> p)
    {
        int n=0;
        System.out.println("All Playgroung:-");
       for(int i=0;i<p.size();i++)
       {
           if(p.elementAt(i).getactive()==true)
           {
               n=1;
           System.out.println(i+1+"--> "+"Name:"+p.elementAt(i).getName()+"    Location: "+p.elementAt(i).getLocation()+"   Size: "+p.elementAt(i).getSize()+"   price Per Hour:"+p.elementAt(i).getPriceperHour());
           }
       }
       if(n==1)
       {
        System.out.println("enter your playground you want ");
          int choice;
          choice=input.nextInt();
          Vector<Integer> numChoice=new Vector<>();
          numChoice=p.elementAt(choice-1).printAvaliableHours();
          System.out.println("do you want confirm booking or cancel");
          String ans;
          ans=input.next();

          if(ans.equalsIgnoreCase("confirm"))
          {
              if(p.elementAt(choice-1).getBooking().getPrice()<=getEWallet())
              {
                  p.elementAt(choice-1).getBooking().confirmBooking();
                   seteWallet(getEWallet()-p.elementAt(choice-1).getBooking().getPrice());
                   for(int z=0;z<numChoice.size();z++)
                   {
                      p.elementAt(choice-1).getAvaHours().elementAt(numChoice.elementAt(z)-1).stat="unavaliable";
          
                   }
                  System.out.println("you booking successfully");
                  
              }
              else
              {
                 p.elementAt(choice-1).getBooking().cancelBooking();
                  System.out.println("you booking cancel you ewallet is not enough"); 
              }
          }
       }
       else if(n==0)
       {
            System.out.println("there is no playground now"); 
       }
    
    }
   
    /**
     *
     * @param p
     */
    public void filterPlayground(Vector<playground> p)
    {
          System.out.println("choose your filter:-\n"+"1)by location \n"+"2)by day \n"+"3)by price range per hours ");
          int choice;
          choice=input.nextInt();
          if(choice==1)
          {
              int in=0;
            System.out.println("Enter location");
            String LOC;
            LOC=input.next();
            for(int i=0;i<p.size();i++)
            {
                if(LOC.equalsIgnoreCase(p.elementAt(i).getLocation()))
                {
                 in=1;
                 System.out.println(i+1+"] "+"Name: "+p.elementAt(i).getName()+"    Location: "+p.elementAt(i).getLocation()+"   Size: "+p.elementAt(i).getSize()+"   price Per Hour:"+p.elementAt(i).getPriceperHour());
                }
            }
            if(in==1)
            {
               System.out.println("Enter your playground then enter [a]to time slot ");
               String ch;
               int c;
               c=input.nextInt();
               ch=input.next();
                if(ch.equals("a"))
                {
                    Vector<Integer> numChoice=new Vector<>();
                    numChoice=p.elementAt(c-1).printAvaliableHours();
                    System.out.println("do you want confirm booking or cancel");
                    String ans;
                    ans=input.next();

                    if(ans.equalsIgnoreCase("confirm"))
                    {
                        if(p.elementAt(c-1).getBooking().getPrice()<=getEWallet())
                        {
                            p.elementAt(c-1).getBooking().confirmBooking();
                             seteWallet(getEWallet()-p.elementAt(c-1).getBooking().getPrice());
                             for(int z=0;z<numChoice.size();z++)
                             {
                                p.elementAt(c-1).getAvaHours().elementAt(numChoice.elementAt(z)-1).stat="unavaliable";

                             }
                            System.out.println("you booking successfully");

                        }
                        else
                        {
                           p.elementAt(c-1).getBooking().cancelBooking();
                            System.out.println("you booking cancel you ewallet is not enough"); 
                        }
                    }    
                }
            }
            else if(in!=1)
            {
                System.out.println("Sorry, We didn't find this Location in our registered playgrounds");
            }
           
          }
          if(choice==2)
          {
          
            int in=0;
            System.out.println("Enter day:");
            String day;
            day=input.next();
          
            for(int i=0;i<p.size();i++)
            {
                for(int z=0;z<p.elementAt(i).getAvaHours().size();z++)
                {
                   if(day.equalsIgnoreCase(p.elementAt(i).getAvaHours().elementAt(z).day))
                {
                 in=1;
                 System.out.println(i+1+"] "+"Name: "+p.elementAt(i).getName()+"    Location: "+p.elementAt(i).getLocation()+"   Size: "+p.elementAt(i).getSize()+"   price Per Hour:"+p.elementAt(i).getPriceperHour()
                 +"day:"+p.elementAt(i).getAvaHours().elementAt(z).day);
                } 
                }
                
            }
            if(in==1)
            {
                    System.out.println("Enter your playground to print time slot ");
                    int c;
                    c=input.nextInt();
                    Vector<Integer> numChoice=new Vector<>();
                    numChoice=p.elementAt(c-1).printAvaliableHours();
                    System.out.println("do you want confirm booking or cancel");
                    String ans;
                    ans=input.next();

                    if(ans.equalsIgnoreCase("confirm"))
                    {
                        if(p.elementAt(c-1).getBooking().getPrice()<=getEWallet())
                        {
                            p.elementAt(c-1).getBooking().confirmBooking();
                             seteWallet(getEWallet()-p.elementAt(c-1).getBooking().getPrice());
                             for(int z=0;z<numChoice.size();z++)
                             {
                                p.elementAt(c-1).getAvaHours().elementAt(numChoice.elementAt(z)-1).stat="unavaliable";

                             }
                            System.out.println("you booking successfully");

                        }
                        else
                        {
                           p.elementAt(c-1).getBooking().cancelBooking();
                            System.out.println("you booking cancel you ewallet is not enough"); 
                        }
                    } 
                
            }
            else if(in!=1)
            {
                System.out.println("Sorry, We didn't find this Time in our registered playgrounds");
            }
 
          }
          if(choice==3)
          {
            int in=0;
            System.out.println("Enter price Range per Hours ");
            double x1,x2;
            String f;
            x1=input.nextDouble();
            f=input.next();
            x2=input.nextDouble();
            
            for(int i=0;i<p.size();i++)
            {
                if(x1<=p.elementAt(i).getPriceperHour()&&p.elementAt(i).getPriceperHour()<=x2)
                {
                 in=1;
                 System.out.println(i+1+"] "+"Name: "+p.elementAt(i).getName()+"    Location: "+p.elementAt(i).getLocation()+"   Size: "+p.elementAt(i).getSize()+"   price Per Hour:"+p.elementAt(i).getPriceperHour());
                }
            }
            if(in==1)
            {
               System.out.println("Enter your playground then enter [a]to time slot ");
               String ch;
               int c;
               c=input.nextInt();
               ch=input.next();
                if(ch.equals("a"))
                {
                     Vector<Integer> numChoice=new Vector<>();
                    numChoice=p.elementAt(c-1).printAvaliableHours();
                   
                    System.out.println("do you want confirm booking or cancel");
                    String ans;
                    ans=input.next();

                    if(ans.equalsIgnoreCase("confirm"))
                    {
                        if(p.elementAt(c-1).getBooking().getPrice()<=getEWallet())
                        {
                            p.elementAt(c-1).getBooking().confirmBooking();
                             seteWallet(getEWallet()-p.elementAt(c-1).getBooking().getPrice());
                             for(int z=0;z<numChoice.size();z++)
                             {
                                p.elementAt(c-1).getAvaHours().elementAt(numChoice.elementAt(z)-1).stat="unavaliable";

                             }
                            System.out.println("you booking successfully");

                        }
                        else
                        {
                           p.elementAt(c-1).getBooking().cancelBooking();
                            System.out.println("you booking has been canceled, you ewallet has not enough money"); 
                        }
                    }    
                }
            }
            else if(in!=1)
            {
                System.out.println("Sorry, We didn't find this Range of price in our registered playgrounds");
            }
           
          }
          
    }

    /**
     *
     * @param all_players
     * @return
     */
    public player player_login(Vector<player>all_players)
    {
        String Email,Password;
        
        
        
        System.out.println("**This is the player login**");
        System.out.println("enter your email");
        Email=input.next();
        System.out.println("enter your password");
        Password=input.next();
        
        for(int i=0 ; i<all_players.size();i++)
        {
            if(search(all_players.elementAt(i),Email,Password)==true)
            {
                
                return all_players.elementAt(i);  
            }
            
        }
        return null;
    }

    /**
     *
     * @param current_player
     * @param Email
     * @param Password
     * @return
     */
    public Boolean search(player current_player,String Email,String Password)
  {
      String current_email=current_player.getEmail();
      String current_password=current_player.getPassword();
      if(current_email.equals(Email)==true&&current_password.equals(Password)==true)
      {
          return true;
      }
      else
      {
          return false;
      }
  }  
 
    /**
     *
     * @return
     * @throws Exception
     */
    public player sign_up() throws Exception 
    {
        
        String Name,Email,Location,phone,Password;
        float Ewallet;
        String Role="player";
        int Id;
        int code,recode;
        player new_player=new player();
        System.out.println("Enter your name:");
        Name=input.nextLine();
        System.out.println("Enter your Email:");
        Email=input.next();
        while(emailIsValid(Email)!=1)
        {
                System.out.println("Invalid email , please enter your email again");
                Email=input.next();
       
        }
    
        System.out.println("Enter your location:");
        Location=input.next();
        
        System.out.println("Enter your phone:");
        phone=input.next();
        System.out.println("Enter your E-wallet:");
        Ewallet=input.nextFloat();
        System.out.println("Enter your password(your password is allawed to be letters and numbers only , at least 8elements includes at least 2 capital letters and 2 numbers):");
        Password=input.next();
      
        while(passwordisValid(Password)!=1)
        {
            System.out.println("Invalid password , please enter another valid password(your password is allawed to be letters and numbers only , at least 8elements includes at least 2 capital letters and 2 numbers)");
            Password=input.next();
        }
         Id=getId();
         code=getEmailCode();
         
        setcode(code);
        send_verification(Email,code);
        System.out.println("Enter the code:");
        recode=input.nextInt();
        
        if(enterCode(recode))
        {
             new_player.enterDetails(Name,Id,Email,Password,phone,Location,Role,Ewallet);   
             System.out.println("create player successfully");
        }
        else
        {
            System.out.println("Wrong code , signup failed");
            return null;
        }
        
        return new_player;
    }   
    
}