import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.time.LocalDate;

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
        saveExpenses();
        if(found){
            System.out.println("Expense deleted.");
        }else{
            System.out.println("Expense not found.");
        }
    }

    public void updateExpense(int id, double amount, String category, String description){
        boolean found=false;
        for (Expense e: expenses){
            if(e.getId()==id){
                e.setAmount(amount);
                e.setCategory(category);
                e.setDescription(description);
                System.out.println("Expense updated.");
                found=true;
            }
        }
        saveExpenses();
        if (!found){
            System.out.println("Expense not found.");
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
                System.out.println(e);
                found=true;
            }
        }
        if(!found){
            System.out.println("No expenses found in this category.");
        }
    }
}