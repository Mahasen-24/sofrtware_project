/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package person;

import java.util.Scanner;
import java.util.Vector;
import system.available_hours;
import system.playground;

/**
 *
 * @author pc
 */
public class owner extends User{

    /**
     *
     */
    Vector<playground> ground=new Vector<>() ; 

    /**
     *
     */
    Scanner input=new Scanner(System.in);

    /**
     *
     * @return
     * @throws Exception
     */
    public owner sign_up() throws Exception
    {
        String Name,Email,Location,phone,Password;
        float Ewallet;
        int Id;
        int code,recode;
       owner new_owner=new owner();
        
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
            System.out.println("Invalid password , please enter another valid password");
            Password=input.next();
        }
        
        Id= getId();
        code=getEmailCode();
        setcode(code);
        code=getEmailCode();
        send_verification(Email,code);
        System.out.println("Enter the code:");
        recode=input.nextInt();
        if(enterCode(recode))
        {
            new_owner.enterDetails(Name, Id, Email, Password, phone, Location, "playground owner", Ewallet);
             System.out.println("create owner successfully");
        }
        else
        {
            System.out.println("Wrong code , signup failed");
            return null;
        }
       
        return new_owner;
        
    }

    /**
     *
     * @param all_owners
     * @return
     */
    public owner owner_login(Vector<owner>all_owners)
    {
        String Email,Password;    
        
        System.out.println("**This is the playground owner login**");
        System.out.println("enter your email");
        Email=input.next();
        System.out.println("enter your password");
        Password=input.next();
        owner current_player=new owner();
        for(int i=0 ; i<all_owners.size();i++)
        {
            if(search(all_owners.elementAt(i),Email,Password))
            {
                return all_owners.elementAt(i);  
            }
            
        }
        return null;
    }

    /**
     *
     * @param current_owner
     * @param Email
     * @param Password
     * @return
     */
    public Boolean search(owner current_owner,String Email,String Password)
  {
      String current_email=current_owner.getEmail();
      String current_password=current_owner.getPassword();
      if(current_email.equals(Email)&&current_password.equals(Password))
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
     */
    public playground add_playground()
   {
       
       String name,location;
       Double size,price_per_hour,cancelation_period;
       Boolean active;
       int num;
        String day,time;
       
        Vector<available_hours> availableHours=new Vector<>() ;  
       
       playground new_playground=new playground();
      
       System.out.println("Enter the playground`s name:");
        name=input.next();
        
        
        System.out.println("Enter the playground`s location:");
        location=input.next();
       
        System.out.println("Enter the playground`s size:");
        size=input.nextDouble();
        System.out.println("Enter the playground`s price per hour:");
        price_per_hour=input.nextDouble();
        System.out.println("Enter the playground`s cancelation period :");
        cancelation_period=input.nextDouble();
        
       
        System.out.println("How many times the playground is available for a weak?");
        num=input.nextInt();
        
        for (int i=0 ; i<num ;i++)
        {
            available_hours available=new available_hours();
            System.out.println("Enter the available day ");
            day=input.next();
            available.day=day;
            System.out.println("Enter the available time in form (00:00-00:00) ");
            time=input.next();
            available.time=time;
            available.stat="avaliable";
            
           availableHours.add(i, available);
            
        }
        
        new_playground.enterDetails(name, location, size, availableHours,  price_per_hour, cancelation_period);
        ground.add(new_playground);
        System.out.println("added successfully!!!");

        return new_playground;
       
   }

    /**
     *
     */
    public void playgroundApproved()
   {
       System.out.println("playground approved by adminstrator");
       int n=0; 
       for(int i=0; i<ground.size();i++)
       {
       if(ground.elementAt(i).getactive()==true)
       {
           n=1;
          System.out.println(i+1+"] "+"Name:"+ground.elementAt(i).getName()+"    Location: "+ground.elementAt(i).getLocation()); 
       }
       }
       if(n==0)
       {
           System.out.println(" adminstrator doest not approve playground yet");
       }
       
   }

    /**
     *
     */
    public void displayBooking()
   {
       System.out.println("all Booking: ");
       for(int i=0;i<ground.size();i++)
       {
           if(ground.elementAt(i).getBooking().getBookingState().equalsIgnoreCase("confirm"))
           {
               for(int z=0;z<ground.elementAt(i).getAvaHours().size();z++)
               {
                   if(ground.elementAt(i).getAvaHours().elementAt(z).stat.equalsIgnoreCase("unavaliable"))
                   {
                      System.out.println(ground.elementAt(i).getName()+": ("+ground.elementAt(i).getAvaHours().elementAt(z).time+"/"+ground.elementAt(i).getAvaHours().elementAt(z).stat+")"); 
                      seteWallet(getEWallet()+ground.elementAt(i).getBooking().getPrice());
                      ground.elementAt(i).getBooking().setPrice(0);
                      
                   }
               } 
           }
       }
        System.out.println("YOUR eWallet ="+getEWallet());
       
   }
    
}
