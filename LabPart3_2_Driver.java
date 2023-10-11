import java.util.*;

public class LabPart3_2_Driver {
    public static void main(String[] args) {
 
        String factoryType = System.getProperty("factory");
        if (factoryType == null) {
            System.out.println("Invalid factory");
            return;
        }

              ICompanyFactory companyFactory = ICompanyFactory.getCompanyFactory(factoryType);
        if (companyFactory == null) {
            System.out.println("Invalid factory type: " + factoryType);
            return;
        }

        Company company = companyFactory.createCompany();

        System.out.println("Company Information:\n" + company.toString());

        System.out.println("B.d.iv. Compute Yearly Payroll for Full-Time Employees: " + company.computeYearlyPayroll("FTE"));
        System.out.println("B.d.v. Compute Yearly Payroll for HE Employee: " + company.computeYearlyPayroll("HE"));
        System.out.println("B.d.v. Compute Yearly Payroll for FTE Employee: " + company.computeYearlyPayroll("FTE"));
        System.out.println("B.d.v. Compute Yearly Payroll for PTE Employee: " + company.computeYearlyPayroll("PTE"));

        for (int i = 1; i <= 3; i++) {
            System.out.println("B.d.vi. Compute Yearly Payroll for Department " + Departments.values()[i - 1] + ": " +
                    company.computeYearlyPayroll(Departments.values()[i - 1]));
        }

                System.out.println("Now printing Hourly Employees:");
        for (EmployeeC employee : company.getEmployeesByType("HE")) {
            System.out.println(employee.toString());
        }

        System.out.println("Now printing Full-Time Employees:");
        for (EmployeeC employee : company.getEmployeesByType("FTE")) {
            System.out.println(employee.toString());
        }

        System.out.println("Now printing Part-Time Employees:");
        for (EmployeeC employee : company.getEmployeesByType("PTE")) {
            System.out.println(employee.toString());
        }
    }
}

