package system;

import java.util.Scanner;
import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pc
 */

public class playground {

    /**
     *
     */
    Scanner input=new Scanner(System.in);
    
    /**
     *
     */
    private String NAME;

    /**
     *
     */
    private String LOCATION;

    /**
     *
     */
    private double SIZE;

    /**
     *
     */
    private Vector<available_hours> availableHours=new Vector<>() ;      

    /**
     *
     */
    private double pricePerHour ;

    /**
     *
     */
    private double cancelationPeriod ;

    /**
     *
     */
    private boolean active;

    /**
     *
     */
    private booking booking ;
    
    /**
     *
     */
    public playground()
    {
        NAME=null;
        LOCATION=null;
        SIZE=0;        
        availableHours=null;
        pricePerHour=0;
        cancelationPeriod=0; 
        active=false;
        booking=new booking();
    }

    /**
     *
     * @param N
     * @param L
     * @param S
     * @param AH
     * @param PricePH
     * @param cancel
     */
    public void enterDetails(String N, String L,double S,Vector<available_hours> AH ,double PricePH ,double cancel)
    {
        NAME=N;
        LOCATION=L;
        SIZE=S;        
        availableHours=AH;
        pricePerHour=PricePH;
        cancelationPeriod=cancel;     
    }

    /**
     *
     */
    public void setactive()
    {
        active=true;
    }

    /**
     *
     * @return
     */
    public boolean getactive()
    {
        return active;
    }

    /**
     *
     * @return
     */
    public String getName() 
    {
      return NAME;
    }
    
    /**
     *
     * @return
     */
    public String getLocation() 
    {
        return LOCATION;
    }
    
    /**
     *
     * @return
     */
    public double getSize ()
    {
        return SIZE;
    }

    /**
     *
     * @return
     */
    public Vector<available_hours> getAvaHours()
    {
        return availableHours;
    }

    /**
     *
     * @return
     */
    public Vector<Integer> printAvaliableHours()
    {
        Vector<Integer> numChoice=new Vector<>();
        System.out.println("Name Playground:- "+NAME);
        String again = "yes";
        while(again.equalsIgnoreCase("yes"))
        {
         System.out.println("Time Slot"+"[");
         for(int i=0;i<availableHours.size();i++)
         {
              System.out.println(i+1+"]"+"("+availableHours.elementAt(i).time+"/"+availableHours.elementAt(i).day
                      +"/"+availableHours.elementAt(i).stat+")");
         }
         System.out.println(availableHours.size()+1+"]go back to manu");
         System.out.println("]");
         System.out.println("Enter your choice to booking");
         int choice;
         choice=input.nextInt();
         if(choice!=availableHours.size()+1)
         {  
             numChoice.add(choice);
             booking.calculateTotal(pricePerHour);
         }
          System.out.println("do you want take time slot anthor?(yes/no)");
          again=input.next();
         }
        System.out.println("YOUR TOTAL PRICE : "+booking.getPrice());
        return numChoice;
    }
    
    /**
     *
     * @return
     */
    public booking getBooking()
    {
        return booking;
    }

    /**
     *
     * @return
     */
    public double getPriceperHour() 
    {
       return pricePerHour;
    }

    /**
     *
     * @return
     */
    public double getCancellationPeriod()
    {
        return cancelationPeriod;
    }

}
