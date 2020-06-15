package person;



import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.concurrent.ThreadLocalRandom;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pc
 */
public class User {

    /**
     *
     */
    private String name;

    /**
     *
     */
    private int id;

    /**
     *
     */
    private String email;

    /**
     *
     */
    private String password;

    /**
     *
     */
    private String phone;

    /**
     *
     */
    private String location;

    /**
     *
     */
    private String role;

    /**
     *
     */
    private double eWallet;

    /**
     *
     */
    private int code;
    
    /**
     *
     */
    public User(){
        name= "null";
        id=00000000;
        email="null";
        password="0000";
        phone="0111111";
        location="cairo";
        role="player";
        eWallet=0;
    }

    /**
     *
     * @param n
     * @param i
     * @param e
     * @param p
     * @param ph
     * @param l
     * @param r
     * @param w
     */
    public void enterDetails(String n,int i,String e,String p,String ph,String l,String r,float w) // to set details
    {
    name= n;
    id=i;
    email=e;
    password=p;
    phone=ph;
    location=l;
    role=r;
    eWallet=w;
}

    /**
     *
     * @param c
     */
    public void setcode(int c)
   {
       code=c;
   }

    /**
     *
     * @return
     */
    public String getName()
    {
        return name;
    }
   
    /**
     *
     * @return
     */
    public int getId()
    {
        id++;
        return id;
    }

    /**
     *
     * @return
     */
    public String getEmail()
    {
        return email;
    }

    /**
     *
     * @return
     */
    public String getPassword()
    {
        return password;
    }

    /**
     *
     * @return
     */
    public String getPHone()
    {
        return phone;
    }

    /**
     *
     * @return
     */
    public String getLocation()
    {
        return location;
    }

    /**
     *
     * @return
     */
    public String getRole()
    {
        return role;
    }

    /**
     *
     * @return
     */
    public double getEWallet()
    {
        return eWallet;
    }

    /**
     *
     * @param e
     * @return
     */
    public static int emailIsValid(String e) //to check email is valid or no
    { 
        int n=0;
        String emRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern p = Pattern.compile(emRegex); 
        if (e == null)  n=1; 
        if(p.matcher(e).matches())  n=1; 
        return n;
    } 

    /**
     *
     * @param ch
     * @return
     */
    public static boolean isLetter(char ch) //to check if password have letter or no
    {
        ch = Character.toUpperCase(ch);
        return (ch >= 'A' && ch <= 'Z');
    }

    /**
     *
     * @param num
     * @return
     */
    public static boolean isNum(char num)//to check if password have number or no
    {

        return (num >= '0' && num <= '9');
    }
    
    /**
     *
     * @param password
     * @return
     */
    public static int passwordisValid(String password)//to check password
    {
        int passLen=8; //the minimum length should be
        int n=0;
        if (password.length() < passLen) 
        {
            n=1;
            return n;
        }

        int chCount = 0;
        int numCount = 0;
        for (int i = 0; i < password.length(); i++) {

            char ch = password.charAt(i);

            if (isNum(ch)) numCount++;
            else if (isLetter(ch)) chCount++;
            else return n;
        }
        if(chCount >= 2 && numCount >= 2) n=1;
        
        return n;
    }

    /**
     *
     * @param email
     * @param password
     * @return
     */
    public int checkAvailability(String email,String password) // to check availability if it is 1 is valid else it is no
    {
        int e=emailIsValid(email);
        int p=passwordisValid(password);
        int check=0;
        
        if(e==1 && p==1)
        {
            check=1;
        }
         if(e!=1 && p==1)
        {
            check=2;
        }
          if(e==1 && p!=1)
        {
            check=3;
        }
       return check;
        
    }
   
    /**
     *
     */
    public void LogIn()
    {
        Scanner scan=new Scanner(System.in);
        System.out.println("enter your email");
        String em=scan.next();
        System.out.println("enter your password");
        String pass=scan.next();
        
    }

    /**
     *
     * @return
     */
    public int getEmailCode()
    {
        int Num = ThreadLocalRandom.current().nextInt(2000, 9999);
        code=Num;
        return code;
    }

    /**
     *
     * @param c
     * @return
     */
    public boolean enterCode(int c)
    {
        if(code==c) return true;
        else return false;
    }

    /**
     *
     * @param e
     */
    public void seteWallet(double e)
    {
        eWallet=e;
    }

    /**
     *
     * @param use
     * @return
     */
    public User getData(User use)
   {
   return use;
   }
 
    /**
     *
     * @param reciever
     * @param code
     * @throws Exception
     */
    public void send_verification(String reciever ,int code ) throws Exception 
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
          message.setSubject("verification code");
          message.setText("Your code is :"+code);
      } catch (Exception ex) {
          Logger.getLogger(player.class.getName()).log(Level.SEVERE, null, ex);
      }
        javax.mail.Transport.send(message);
      System.out.println("We sent a code to your mail,please check your mail...");   
    }
}
    
