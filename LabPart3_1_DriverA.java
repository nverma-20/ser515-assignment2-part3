import java.text.NumberFormat;
import java.util.*;

class PartTimeEmployee {
    public PartTimeEmployee(String name, int dept, double salary) {
        this.name = name;
        this.dept = dept;
        this.salary = salary;
    }

    public double computeBonus() {
        return 0.01 * salary;
    }

    public void raise(double amount) {
        salary = salary + amount;
    }

    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        return "PartTime - Name: " + name + " Dept: " + dept + " Wage: " + nf.format(salary);
    }

    private int dept;
    private double salary;
    private String name;
}

class FullTimeEmployee {
    public FullTimeEmployee(String name, int dept, double salary) {
        this.name = name;
        this.dept = dept;
        this.salary = salary;
    }

    public double computeBonus() {
        return 0.03 * salary + 100 * numOfOptions;
    }

    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        return "FullTime - Name: " + name + " Dept: " + dept + " Wage: " + nf.format(salary) + " Options: " + numOfOptions;
    }

    public void increaseOptions(int number) {
        numOfOptions += number;
    }

    private int numOfOptions = 0;
    private int dept;
    private double salary;
    private String name;
}

public class LabPart3_1_DriverA { 
    public enum Departments { HR, ENGINEERING, SALES, ACCOUNTING }

    public static void main(String args[]) {
        Random random = new Random();
        Object list[] = new Object[5];
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);

        list[0] = new PartTimeEmployee("PTE1", random.nextInt(3), random.nextDouble() * 50000);
        list[1] = new FullTimeEmployee("FTE2", random.nextInt(3), random.nextDouble() * 100000);
        list[2] = new FullTimeEmployee("FTE3", random.nextInt(3), random.nextDouble() * 100000);
        list[3] = new PartTimeEmployee("PTE4", random.nextInt(3), random.nextDouble() * 50000);
        list[4] = new FullTimeEmployee("FTE5", random.nextInt(3), random.nextDouble() * 100000);

        System.out.println("THE EMPLOYEES");
        for (Object employee : list) {
            System.out.println(employee);
        }

        System.out.println("THE EMPLOYEES WITH INCREASED OPTIONS");
        for (Object employee : list) {
            if (employee instanceof FullTimeEmployee) {
                FullTimeEmployee fte = (FullTimeEmployee) employee;
                fte.increaseOptions(random.nextInt(100));
                System.out.println(fte);
            }
        }

        System.out.println("THE EMPLOYEES WITH BONUSES");
        for (Object employee : list) {
            if (employee instanceof FullTimeEmployee) {
                FullTimeEmployee fte = (FullTimeEmployee) employee;
                System.out.println(fte + " bonus: " + nf.format(fte.computeBonus()));
            } else if (employee instanceof PartTimeEmployee) {
                PartTimeEmployee pte = (PartTimeEmployee) employee;
                System.out.println(pte + " bonus: " + nf.format(pte.computeBonus()));
            }
        }

        System.out.println("THE EMPLOYEES AFTER RAISES");
        for (Object employee : list) {
            if (employee instanceof PartTimeEmployee) {
                PartTimeEmployee pte = (PartTimeEmployee) employee;
                pte.raise(random.nextDouble() * 9000.0 + 1000.0);
                System.out.println(pte);
            }
        }

        System.out.println("FINAL STATE OF THE EMPLOYEES");
        for (Object employee : list) {
            System.out.println(employee);
        }
    }
}