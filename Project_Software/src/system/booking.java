/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;
import system.playground;

/**
 *
 * @author pc
 */
public class booking {

    /**
     *
     */
    private String bookingState;

    /**
     *
     */
    private double price;
    
    /**
     *
     */
    public booking()
    {
        bookingState="null";
        price=0;
    }

    /**
     *
     * @param b
     */
    public void setPrice(double b)
   {
        price=b;
   }

    /**
     *
     * @param p
     */
    public void calculateTotal(double p)
   {
       price=price+p;
   }

    /**
     *
     * @return
     */
    public double getPrice()
   {
       return price;
   }

    /**
     *
     * @return
     */
    public String getBookingState()
   {
       return bookingState;
   }

    /**
     *
     */
    public void confirmBooking()
   {
       bookingState="confirm";
   }

    /**
     *
     */
    public void cancelBooking()
   {
       price=0;
   }
    
  
}
