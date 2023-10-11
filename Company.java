import java.util.*;

public class Company {
    private String companyName;
    private List<EmployeeC> employees;

    public Company(String companyName) {
        this.companyName = companyName;
        this.employees = new ArrayList<>();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void addEmployee(EmployeeC employee) {
        employees.add(employee);
    }

    public EmployeeC getEmployeeByNumber(int n) {
    for (EmployeeC employee : employees) {
        if (employee.name.endsWith(String.valueOf(n))) {
            return employee;
        }
    }
    return null;
}

    public EmployeeC getEmployeeByName(String name) {
        for (EmployeeC employee : employees) {
            if (employee.name.equals(name)) {
                return employee;
            }
        }
        return null;
    }

    public List<EmployeeC> getEmployeesByType(String type) {
        List<EmployeeC> employeesOfType = new ArrayList<>();
        for (EmployeeC employee : employees) {
            if ((employee instanceof HourlyEmployeeC && "HE".equals(type)) ||
                (employee instanceof FullTimeEmployeeC && "FTE".equals(type)) ||
                (employee instanceof PartTimeEmployeeC && "PTE".equals(type))) {
                employeesOfType.add(employee);
            }
        }
        return employeesOfType;
    }

    public double computeYearlyPayroll() {
        double totalPayroll = 0.0;
        for (EmployeeC employee : employees) {
            totalPayroll += employee instanceof HourlyEmployeeC ? 
            ((HourlyEmployeeC) employee).payRate * ((HourlyEmployeeC) employee).hoursPerWeek* 52: 
            employee.salary;
        }
        return totalPayroll;
    }

    public double computeYearlyPayroll(String empType) {
        double totalPayroll = 0.0;
        for (EmployeeC employee : employees) {
            if ((employee instanceof HourlyEmployeeC && "HE".equals(empType)) ||
                (employee instanceof FullTimeEmployeeC && "FTE".equals(empType)) ||
                (employee instanceof PartTimeEmployeeC && "PTE".equals(empType))) {
                totalPayroll += employee instanceof HourlyEmployeeC ? 
                ((HourlyEmployeeC) employee).payRate * ((HourlyEmployeeC) employee).hoursPerWeek* 52: 
                employee.salary;
            }
        }
        return totalPayroll;
    }

    public double computeYearlyPayroll(Departments dept) {
        double totalPayroll = 0.0;
        for (EmployeeC employee : employees) {
            if (employee.dept== dept.ordinal()) {
                totalPayroll += employee instanceof HourlyEmployeeC ? 
                ((HourlyEmployeeC) employee).payRate * ((HourlyEmployeeC) employee).hoursPerWeek* 52: 
                employee.salary;
            }
        }
        return totalPayroll;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Company Name: ").append(companyName).append("\n");

        int numHourly = getEmployeesByType("HE").size();
        int numFullTime = getEmployeesByType("FTE").size();
        int numPartTime = getEmployeesByType("PTE").size();

        builder.append("Number of Hourly Employees: ").append(numHourly).append("\n");
        builder.append("Number of Full-Time Employees: ").append(numFullTime).append("\n");
        builder.append("Number of Part-Time Employees: ").append(numPartTime).append("\n");

        for (Departments dept : Departments.values()) {
            int numInDept = 0;
            for (EmployeeC employee : employees) {
                if (employee.dept == dept.ordinal()) {
                    numInDept++;
                }
            }
            builder.append("Number of Employees in ").append(dept.toString()).append(" Department: ").append(numInDept).append("\n");
        }

        return builder.toString();
    }
}


