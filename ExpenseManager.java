import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Collections;


public class ExpenseManager{
    private int nextId = 1;
    
    private ArrayList<Expense> expenses=new ArrayList<>();
    public ExpenseManager(){
        loadExpenses();
    }

    public int generateId(){
        return nextId++;
    }

    public void addExpense(Expense e){
        expenses.add(e);
        saveExpenses();
    }

    public void viewExpense(){
        if(expenses.isEmpty()){
            System.out.println("No expenses found.");
            return;
        }

        
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-5s %-10s %-12s %-15s %-12s%n",
                "ID", "Amount", "Category", "Description", "Date");
        System.out.println("------------------------------------------------------------");
        
        for (Expense e: expenses){
            System.out.println(e);
        }
    }

    public void deleteExpense(int id){
        boolean found=false;
        for (int i=0; i<expenses.size(); i++){
            if (expenses.get(i).getId()== id){
                expenses.remove(i);
                found = true;
                break;
            }
        }
        
        if(found){
            saveExpenses();
            System.out.println("Expense deleted.");
            System.out.println("------------------------------------------------------------");
        }else{
            System.out.println("Expense not found.");
            System.out.println("------------------------------------------------------------");
        }
    }

    public void updateExpense(int id, double amount, String category, String description){
        boolean found=false;
        for (Expense e: expenses){
            if(e.getId()==id){
                e.setAmount(amount);
                e.setCategory(category);
                e.setDescription(description);

                saveExpenses();
                System.out.println("Expense updated.");
                System.out.println("------------------------------------------------------------");
                found=true;
                break;
            }
        }
        
        if (!found){
            System.out.println("Expense not found.");
            System.out.println("------------------------------------------------------------");
        }
    }

    public double calculateTotal(){
        double total=0;
        for (Expense e : expenses){
            total+= e.getAmount();
        }
        return total;
    }

    public void saveExpenses(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("expenses.csv"))){
            for (Expense e : expenses){
                writer.write (
                    e.getId() + "," +
                    e.getAmount() + "," +
                    e.getCategory() + "," +
                    e.getDescription() + "," +
                    e.getDate()
                );
                writer.newLine();
            }
        }
        catch(IOException ex){
            System.out.println("Error saving expenses.");
        }
    }

    public void loadExpenses(){
        try (BufferedReader reader=new BufferedReader(new FileReader("expenses.csv"))){
            String line;
            while((line=reader.readLine())!=null){
                String[] parts=line.split(",");

                int id= Integer.parseInt(parts[0]);
                double amount=Double.parseDouble(parts[1]);
                String category = parts[2];
                String description= parts[3];
                LocalDate date = LocalDate.parse(parts[4]);

                Expense exp=new Expense(id, amount, category, description, date);

                expenses.add(exp);

                if(id>=nextId){
                    nextId=id+1;
                }
            }
        }
        catch(IOException e){
            System.out.println("No previous expenses found.");
        }
    }

    public void searchByCategory(String category){
        boolean found=false;
        
        for(Expense e: expenses){
            if (category.equalsIgnoreCase(e.getCategory())){
                
                if (!found) {
                    System.out.println("Search Results- Category: "+category+" ");
                    System.out.printf("%-5s %-10s %-12s %-15s %-12s%n",
                "ID", "Amount", "Category", "Description", "Date");
                    System.out.println("------------------------------------------------------------");
                }
                System.out.println(e);
                found=true;
            }
        }
        if(!found){
            System.out.println("No expenses found in this category.");
        }
        System.out.println("------------------------------------------------------------");
    }

    public void categorySummary(){
        if(expenses.isEmpty()){
            System.out.println("No expenses found.");
            System.out.println("------------------------------------------------------------");
            return;
        }
        HashMap<String, Double> summary= new HashMap<>();
        for (Expense e: expenses){
            String categoryKey= e.getCategory();
            // if (summary.containsKey(e.getCategory())){
            //     summary.put(categoryKey, summary.get(categoryKey)+e.getAmount());
            // }

            // else{
            //     summary.put(categoryKey, e.getAmount());
            // }

            summary.put(
                categoryKey,
                summary.getOrDefault(categoryKey, 0.0)
                    + e.getAmount()
            );
        }
        System.out.printf("%-10s %-12s", "Category" , "Total Expense\n");
        for(String category: summary.keySet()){
            System.out.printf("%-15s %-15.2f%n", category, summary.get(category));

        }
        System.out.println("------------------------------------------------------------");
    }

    public void sortByAmount(){
        ArrayList<Expense> sortedExpenses= new ArrayList<>(expenses);
        Collections.sort(sortedExpenses, (e1, e2)->
            Double.compare(
                e1.getAmount(), 
                e2.getAmount()
            )
        );
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-5s %-10s %-12s %-15s %-12s%n",
                "ID", "Amount", "Category", "Description", "Date");
        System.out.println("------------------------------------------------------------");
        for (Expense exp: sortedExpenses){
            System.out.println(exp);
        }
        
        System.out.println("------------------------------------------------------------");
    }

    public void sortByDate(){
        ArrayList<Expense> sortedListByDate=new ArrayList<>(expenses);
        Collections.sort(sortedListByDate, (e1,e2)->
            
            e1.getDate().compareTo(e2.getDate())
            
        );
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-5s %-10s %-12s %-15s %-12s%n",
                "ID", "Amount", "Category", "Description", "Date");
        System.out.println("------------------------------------------------------------");
        for (Expense exp: sortedListByDate){
            System.out.println(exp);
        }
        System.out.println("------------------------------------------------------------");
    }

    public void expenseStatistics(){
        if(expenses.isEmpty()){
            System.out.println("No expenses found.");
            return;
        }
        Expense highest=expenses.get(0);
        Expense lowest=expenses.get(0);
        double total=0;

        for(Expense e: expenses){
            total+=e.getAmount();
            if(e.getAmount()>highest.getAmount()){
                highest=e;
            }
            if(e.getAmount()<lowest.getAmount()){
                lowest=e;
            }

        }

        Double average= total/expenses.size();

        System.out.println(
            "------------------------------------------------------------"
        );

        System.out.println("EXPENSE STATISTICS");

        System.out.println(
            "------------------------------------------------------------"
        );

        System.out.println("Highest Expense:");
        System.out.printf("%-5s %-10s %-12s %-15s %-12s%n",
                "ID", "Amount", "Category", "Description", "Date");
        System.out.println(highest);

        System.out.println();

        System.out.println("Lowest Expense:");
        System.out.printf("%-5s %-10s %-12s %-15s %-12s%n",
                "ID", "Amount", "Category", "Description", "Date");
        System.out.println(lowest);

        System.out.println();

        System.out.printf(
            "Average Expense: %.2f%n",
            average
        );

        System.out.println(
            "------------------------------------------------------------"
        );
    }
}