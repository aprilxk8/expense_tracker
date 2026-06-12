import java.time.LocalDate;
public class Expense{
    private int id;
    private double amount;
    private String category;
    private String description;
    private LocalDate date;

    public Expense(int id, double amt, String category, String description, LocalDate date){
        this.id=id;
        this.amount=amt;
        this.category=category;
        this.description=description;
        this.date=date;
    }

    public int getId(){
        return id;
    }
    public double getAmount(){
        return amount;
    }
    public String getCategory(){
        return category;
    }
    public String getDescription(){
        return description;
    }
    public LocalDate getDate(){
        return date;
    }

    
    public void setAmount(double amount){
        this.amount=amount;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public void setDescription(String description){
        this.description = description;
    }

    @Override
    public String toString(){
        return String.format(
            "%-5d %-10.2f %-12s %-15s %-12s",
            id, amount, category, description, date
        );

    }
}