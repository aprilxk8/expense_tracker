import java.time.LocalDate;
import java.util.Scanner;

public class Main{
    public static void main (String[] args){
        ExpenseManager manager= new ExpenseManager();

        Scanner sc= new Scanner(System.in);

        while(true){
            System.out.println("Enter your Choice: \n1.Add an Expense \n2.View Expenses \n3.Calculate Total \n4.Delete Expense \n5.Update \n6.Exit");
            int choice=sc.nextInt();
            
            switch(choice){
                case 1:
                    System.out.println("Enter details of expense: ");
                    int id=manager.generateId();
                    System.out.println("Enter amount: ");
                    double amount= sc.nextDouble();
                    System.out.println("Enter category: ");
                    sc.nextLine();
                    String category=sc.nextLine();
                    
                    System.out.println("Enter description: ");
                    String description= sc.nextLine();

                    LocalDate date= LocalDate.now();
                    
                    Expense e= new Expense(id, amount, category, description, date);
                    
                    manager.addExpense(e);
                    //manager.saveExpenses();
                    System.out.println("Expense added successfully!");
                    break;
                case 2:
                    manager.viewExpense();
                    break;
                case 3:
                    double total=manager.calculateTotal();
                    System.out.println("Total Expense: "+ total);
                    break;
                case 4:
                    System.out.println("Enter expense id to delete: ");
                    int deleteId=sc.nextInt();
                    manager.deleteExpense(deleteId);
                    //manager.saveExpenses();
                    break;
                case 5:
                    System.out.println("Enter expense id to update: ");
                    int newId= sc.nextInt();
                    System.out.println("Enter new amount: ");
                    double newAmount= sc.nextDouble();
                    System.out.println("Enter new category: ");
                    sc.nextLine();
                    String newCategory= sc.nextLine();
                    System.out.println("Enter new description: ");
                    String newDesc=sc.nextLine();

                    manager.updateExpense(newId, newAmount, newCategory, newDesc);
                    //manager.saveExpenses();
                    break;
                case 6:
                    //System.exit(0);
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}