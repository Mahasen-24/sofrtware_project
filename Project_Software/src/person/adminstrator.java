/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package person;
import java.util.Scanner;
import java.util.Vector;
import system.playground;

/**
 *
 * @author pc
 */
public class adminstrator extends User{

    /**
     *
     */
    private boolean status=false;
    
    /**
     *
     */
    Scanner input=new Scanner(System.in);

    /**
     *
     * @param Email
     * @param Password
     */
    public void admin_login(String Email,String Password)
    {
        String mail,pass;
        System.out.println("Enter your email :");
        mail=input.next();
        System.out.println("Enter your password :");
        pass=input.next();
        
        while(!Email.equals(mail)||!Password.equals(pass))
        {
            System.out.println("Login faild Please again:");
            System.out.println("Enter your email :");
            mail=input.next();
            System.out.println("Enter your password :");
            pass=input.next();
        }
        if(Email.equals(mail)&&Password.equals(pass))
        {
          System.out.println("***This is admin screen***");
        }
       
  
    }
      
    /**
     *
     * @param ground
     */
    public void approve_playground(Vector<playground> ground)
    {
 
        for(int i =0; i<ground.size();i++)
        {
         if(ground.elementAt(i).getactive()!=true)
         {
          System.out.println(i+1 + "--->"+ground.elementAt(i).getName());   
         }
           
       }
        System.out.println("select playground to check it's data");
        int y=input.nextInt();
        if(ground.elementAt(y-1).getName()==null||ground.elementAt(y-1).getLocation()==null||ground.elementAt(y-1).getSize()==0.0||ground.elementAt(y-1).getPriceperHour()>500||ground.elementAt(y-1).getAvaHours()==null)
        {
            System.out.println("playground disapproved!");
            delete_playground(ground,y-1);
        }
        else
        {
            System.out.println("playground approved!");
            ground.elementAt(y-1).setactive();
            
        }
        
    }
    
    /**
     *
     * @param ground
     * @param i
     */
    public void delete_playground(Vector<playground> ground,int i)
    {
        for(int j=i; j<ground.size()-1;j++)
            {
                ground.add(j, ground.elementAt(j+1));
            }
    }
  }
 
