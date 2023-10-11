import java.text.NumberFormat;
import java.util.*;

class EmployeeC {
    protected String name;
    protected int dept;
    protected double salary;

    public EmployeeC(String name, int dept, double salary) {
        this.name = name;
        this.dept = dept;
        this.salary = salary;
    }

    public double computeBonus() {
        return 0.0; 
    }

    public void raise(double amount) {
        salary = salary + amount;
    }

    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        return "Name: " + name + " Dept: " + dept + " Wage: " + nf.format(salary);
    }
}

class PartTimeEmployeeC extends EmployeeC {
    public PartTimeEmployeeC(String name, int dept, double salary) {
        super(name, dept, salary);
    }

    @Override
    public double computeBonus() {
        return 0.01 * salary;
    }

    @Override
    public String toString() {
        return "PartTime - " + super.toString();
    }
}

class FullTimeEmployeeC extends EmployeeC {
    private int numOfOptions;

    public FullTimeEmployeeC(String name, int dept, double salary) {
        super(name, dept, salary);
        this.numOfOptions = 0;
    }

    public void increaseOptions(int number) {
        numOfOptions += number;
    }

    @Override
    public double computeBonus() {
        return 0.03 * salary + 100 * numOfOptions;
    }

    @Override
    public String toString() {
        return "FullTime - " + super.toString() + " Options: " + numOfOptions;
    }
}

class HourlyEmployeeC extends EmployeeC {
    protected int hoursPerWeek;
    protected double payRate;

    public HourlyEmployeeC(String name, int dept, int hoursPerWeek, double payRate) {
        super(name, dept, 0); 
        this.hoursPerWeek = Math.max(5, Math.min(40, hoursPerWeek)); 
        this.payRate = payRate;
    }

    @Override
    public void raise(double percentageIncrease) {
        payRate += payRate * (percentageIncrease / 100);
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        double yearlyWage = hoursPerWeek * payRate * 52;
        return "Hourly - " + super.toString() + " Yearly Wage: " + nf.format(yearlyWage);
    }
}

enum Departments { HR, ENGINEERING, SALES, ACCOUNTING }

public class LabPart3_1_DriverC {

    public static void main(String args[]) {
        Random random = new Random();
        EmployeeC list[] = new EmployeeC[7]; 
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);

        list[0] = new PartTimeEmployeeC("PTE1", random.nextInt(3), random.nextDouble() * 50000);
        list[1] = new FullTimeEmployeeC("FTE2", random.nextInt(3), random.nextDouble() * 100000);
        list[2] = new FullTimeEmployeeC("FTE3", random.nextInt(3), random.nextDouble() * 100000);
        list[3] = new PartTimeEmployeeC("PTE4", random.nextInt(3), random.nextDouble() * 50000);
        list[4] = new FullTimeEmployeeC("FTE5", random.nextInt(3), random.nextDouble() * 100000);

        
        list[5] = new HourlyEmployeeC("HE1", random.nextInt(3), 40, 20.0); 
        list[6] = new HourlyEmployeeC("HE2", random.nextInt(3), 30, 18.0); 

        System.out.println("THE EMPLOYEES");
        for (EmployeeC employee : list) {
            System.out.println(employee);
        }

        System.out.println("THE EMPLOYEES WITH INCREASED OPTIONS");
        for (EmployeeC employee : list) {
            if (employee instanceof FullTimeEmployeeC) {
                FullTimeEmployeeC fte = (FullTimeEmployeeC) employee;
                fte.increaseOptions(random.nextInt(100));
                System.out.println(fte);
            }
        }

        System.out.println("THE EMPLOYEES WITH BONUSES");
        for (EmployeeC employee : list) {
            System.out.println(employee + " bonus: " + nf.format(employee.computeBonus()));
        }

        System.out.println("THE EMPLOYEES AFTER RAISES");
        for (EmployeeC employee : list) {
            if (employee instanceof HourlyEmployeeC) {
                HourlyEmployeeC he = (HourlyEmployeeC) employee;
                he.raise(5.0); 
                System.out.println(he);
            } else {
                employee.raise(random.nextDouble() * 9000.0 + 1000.0);
                System.out.println(employee);
            }
        }

        System.out.println("FINAL STATE OF THE EMPLOYEES");
        for (EmployeeC employee : list) {
            System.out.println(employee);
        }
    }
}