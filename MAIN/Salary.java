package MAIN;

import java.util.*;

public class Salary {
     Scanner sc = new Scanner(System.in);
    SAlaries[] sl = new SAlaries[100];
    Set<String>  nameSet = new HashSet<>();
    Set<Integer>  idSet = new HashSet<>();
    
    String  name, choice, format;
    int rh, hw, deduc, num , id;
    double gross, nPay, tSalary = 0.0, tDeduc = 0.0, tNpay = 0.0;
    public void addSalaries(){
        
            do{ 
        
        System.out.println("--------------------------");
        System.out.println("WELCOME TO SALARY APP");
        System.out.println("--------------------------");
        System.out.println("1. ADD");
        System.out.println("2. VIEW");
        System.out.println("3. EDIT");
        System.out.println("4. DELETE");
        System.out.println("5. EXIT");
        System.out.println("--------------------------");
            
            System.out.print("Enter Option: ");
        int option = sc.nextInt();

        while(option > 5){
            System.out.print("Invalid Selection, Try Again: ");
            option = sc.nextInt();
        }
        
        switch(option){
            case 1:
             while(true){
                System.out.printf("Enter no. of Employee: ");
                if (sc.hasNextInt()){
                     num = sc.nextInt();
                     
                     if (num > 100){
                    System.out.println("You can only enter 100 employee");
                  
                     }else {
                         break;
                     }
                }else{
                      System.out.println(" INVALID INPUT, Please try Again");;
                      sc.next();
                    }
             }
         
            for (int j = 0; j < num; j++){
            System.out.println("-------------------------------------------");
            System.out.println("Details of employee no. " + (j+1) + ":");
            
            id = getValid("ID");
            
            while(true){
             System.out.printf("Name: ");
              name = sc.next();
                    if(isValidName(name)){
                        System.out.println("I don't think " + name + " is valid.");
                    }else if(nameSet.contains(name)){
                        System.out.println("This NAME already exist. Please enter a new name.");
                    }else{
                        nameSet.add(name);
                     break;
                }
             }
            
            
            rh = getValid("Rate Hourly");
            hw = getValid("Hours Worked");
            deduc = getValid("Deduction");
            
            sl[j] = new SAlaries();
            sl[j].addSalary(id, name, rh, hw, deduc);
            
            tSalary += sl[j].getGross();
            tDeduc += sl[j].getDeduc();
            tNpay += sl[j].getNpay();
            }
            break;
            
            case 2:
                    if(num == 0){
                        System.out.println("It's Empty! Add employee first.");
                    }else {
                        System.out.printf("%-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s\n", "ID", "Name", "Rate", "Hour", "Gross", "Deduction", "Net Pay");
                    
            
                        for(int j = 0; j < num; j++){
                            sl[j].viewSalary();
                         }
                        System.out.printf("Total Salary Request: %.2f\n", tSalary);
                        System.out.printf("Total Employee Deduction: %.2f\n", tDeduc);
                        System.out.printf("Total Net Pay: %.2f \n", tNpay);
                        System.out.println("No. of Employees: " + num);
                    }
            break;
            
            case 3:
                        if (num == 0) {
                            System.out.println("No employees to edit.");
                        }    else   {
                            System.out.println("Enter ID of the Employee: ");
                            int idin = sc.nextInt();

                            boolean found = false;
                       for (int i = 0; i < num; i++) {
                            if (sl[i].id == idin) {
                                tSalary -= sl[i].getGross();
                                tDeduc -= sl[i].getDeduc();
                                tNpay -= sl[i].getNpay();

                                System.out.print("New Rate: ");
                                int nrh = sc.nextInt();

                                System.out.print("New Hour: ");
                                int nhw = sc.nextInt();

                                System.out.print("New Deduction: ");
                                int ndeduc = sc.nextInt();

                                sl[i].editSalary(idin, nrh, nhw, ndeduc);
                                sl[i].calcuGross();  
                                sl[i].calcuNPay();  

                                tSalary += sl[i].getGross();
                                tDeduc += sl[i].getDeduc();
                                tNpay += sl[i].getNpay();

                        found = true;
                    break;
                        }
                    }
        
                    if (!found) {
                        System.out.println("Employee with ID " + idin + " not found.");
                    }   
                   }
                break;

            case 4:
                if(num == 0){
                        System.out.println("It's Empty! Add employee first.");
                }   else     {
                    System.out.println("Enter ID of the Employee to delete: ");
                    int deleteId = sc.nextInt();
                    boolean found = false;
        
                        for (int i = 0; i < num; i++) {
                             if (sl[i].id == deleteId) {
                                 tSalary -= sl[i].getGross();
                                 tDeduc -= sl[i].getDeduc();
                                 tNpay -= sl[i].getNpay();
                
                        for (int j = i; j < num - 1; j++) {
                             sl[j] = sl[j + 1];
                        }
                
                             sl[num - 1] = null;
                             num--;  
                             
                            found = true;
                            
                            System.out.println("Employee with ID " + deleteId + " has been deleted.");
                        break;
                        }
                    }   
                        if (!found) {
                          System.out.println("Employee with ID " + deleteId + " not found.");
                    }
                }
            break;

            case 5:
                
                System.exit (0);
                
                break;
            default:
                System.out.println("Invalid Option!");
                break;
        }
        
                System.out.println("Still want to try(yes/no)?: ");
                choice = sc.next();
                
    }while(choice.equals("yes")  || choice.equals("Yes"));
            System.out.println("Thank you for using the app.");
            
    }
    
    
    
    public boolean isValidName (String name){
         return name.isEmpty() || !name.matches("[a-zA-Z]+");
     }
    
    
    
    public int getValid(String validType) {
        int valid;
        while (true) {
            System.out.printf("%s: ", validType);
            if (sc.hasNextInt()) {
                valid = sc.nextInt();
                break;
            } else {
                System.out.println("INVALID, Please input a number.");
                sc.next(); 
            }
        }
        return valid;
    }
}