import java.time.LocalDate;
import java.util.Scanner;

public class Main{
    public static void main (String[] args){
        ExpenseManager manager= new ExpenseManager();

        Scanner sc= new Scanner(System.in);

        while(true){
            System.out.println("------------------------------------------------------------");
            System.out.println("TRACK EXPENSES\t\t");
            System.out.println("------------------------------------------------------------");
            System.out.println("1.Add an Expense \n2.View Expenses \n3.Calculate Total \n4.Delete Expense \n5.Update \n6.Search -by Category \n7.Category Summary \n8.Sort \n9.Expense Statistics \n10.Exit");
            System.out.println("------------------------------");
            int choice=sc.nextInt();
            System.out.println("------------------------------");

            switch(choice){
                case 1:
                    System.out.println("Enter details of expense: ");
                    sc.nextLine();
    
                    String amountInput =
                        getInput(sc, "Enter amount:");

                    if(amountInput == null){
                        System.out.println("Operation cancelled.");
                        break;
                    }

                    double amount;

                    try{
                        amount = Double.parseDouble(amountInput);
                    }
                    catch(NumberFormatException ex){
                        System.out.println("Invalid amount.");
                        break;
                    }

                    if(amount <= 0){
                        System.out.println(
                            "Amount must be greater than 0."
                        );
                        break;
                    }

                    String category =
                        getInput(sc, "Enter category:");

                    if(category == null){
                        System.out.println("Operation cancelled.");
                        break;
                    }

                    String description =
                        getInput(sc, "Enter description:");

                    if(description == null){
                        System.out.println("Operation cancelled.");
                        break;
                    }

                    int id = manager.generateId();

                    Expense e = new Expense(
                            id,
                            amount,
                            category,
                            description,
                            LocalDate.now()
                    );

                    manager.addExpense(e);

                    System.out.println(
                        "Expense added successfully!"
                    );

                    break;


                case 2:
                    manager.viewExpense();
                    break;
                case 3:
                    double total=manager.calculateTotal();
                    System.out.println("------------------------------------------------------------");
                    System.out.println("TOTAL EXPENSE: "+ total);
                    System.out.println("------------------------------------------------------------");
                    break;
                case 4:
                    sc.nextLine();
                    String deleteInput= getInput(sc, "Enter expense id to delete: ");

                    if(deleteInput==null){
                        System.out.println("Operation cancelled");
                        break;
                    }

                    try{
                        int deleteId=Integer.parseInt(deleteInput);
                        manager.deleteExpense(deleteId);
                    }
                    catch(NumberFormatException ex){
                        System.out.println("Invalid ID");
                    }
                    
                    break;
                case 5:
                    sc.nextLine();
                    String idInput=getInput(sc, "Enter expense id to update: ");
                    if(idInput==null){
                        System.out.println("Operation cancelled");
                        break;
                    }
                    amountInput=getInput(sc, "Enter new amount: ");
                    if(amountInput==null){
                        System.out.println("Operation cancelled");
                        break;
                    }
                    
                    String categoryInput =
                        getInput(sc,
                                "Enter new category:");

                    if(categoryInput == null){
                        System.out.println("Operation cancelled.");
                        break;
                    }

                    String descInput =
                        getInput(sc,
                                "Enter new description:");

                    if(descInput == null){
                        System.out.println("Operation cancelled.");
                        break;
                    }

                    try{
                        int updateId=Integer.parseInt(idInput);
                        double newAmount= Double.parseDouble(amountInput);
                        manager.updateExpense(updateId, newAmount, categoryInput, descInput);

                    }
                    catch(NumberFormatException ex){
                        System.out.println("Invalid numeric input.");
                    }
                    
                    break;
                case 6:
                    sc.nextLine();

                    String searchCategory =
                        getInput(sc,
                                "Enter category:");

                    if(searchCategory == null){
                        System.out.println("Operation cancelled.");
                        break;
                    }

                    manager.searchByCategory(
                            searchCategory
                    );
                    break;
                case 7:
                    manager.categorySummary();
                    break;
                case 8:

                    sc.nextLine();

                    String sortInput =
                        getInput(sc,
                                """
                                Sort by:
                                1. Amount
                                2. Date
                                """);
                    System.out.println("------------------------------");
                    if(sortInput == null){
                        System.out.println("Operation cancelled.");
                        break;
                    }

                    try{

                        int sort =
                            Integer.parseInt(sortInput);

                        switch(sort){

                            case 1:
                                manager.sortByAmount();
                                break;

                            case 2:
                                manager.sortByDate();
                                break;

                            default:
                                System.out.println(
                                    "Invalid choice."
                                );
                        }

                    }
                    catch(NumberFormatException ex){
                        System.out.println(
                            "Invalid choice."
                        );
                        System.out.println("------------------------------");
                    }

                    break;


                case 9:
                    manager.expenseStatistics();
                    break;    
                case 10:
                    //System.exit(0);
                    System.out.println("Exiting...");
                    System.out.println("------------------------------------------------------------");
                    return;
                default:
                    System.out.println("Invalid choice.");
                    System.out.println("------------------------------");
            }
        }
    }

    public static String getInput(Scanner sc, String prompt)
    {
        System.out.println(prompt);

        String input=sc.nextLine();
        if (input.equalsIgnoreCase("menu")){
            
            return null;
        }
        return input;
    }
} 
