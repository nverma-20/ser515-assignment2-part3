import java.io.*;
import java.util.*;
public interface ICompanyFactory {
    static ICompanyFactory getCompanyFactory(String factoryType) {
        if ("interactive".equals(factoryType)) {
            return new InteractiveCompanyFactory();
        } else if ("configurable".equals(factoryType)) {
            return new ConfigurableCompanyFactory();
        } else {
            return null;
        }
    }

    Company createCompany();
}


class InteractiveCompanyFactory implements ICompanyFactory {
    @Override
    public Company createCompany() {
                Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of hourly employees: ");
        int numHourly = scanner.nextInt();

        System.out.print("Enter the number of full-time employees: ");
        int numFullTime = scanner.nextInt();

        System.out.print("Enter the number of part-time employees: ");
        int numPartTime = scanner.nextInt();

        System.out.print("Enter the company name: ");
        scanner.nextLine(); // Consume the newline
        String companyName = scanner.nextLine();

        Company company = new Company(companyName);

        for (int i = 0; i < numHourly; i++) {
            company.addEmployee(new HourlyEmployeeC("HE" + i, new Random().nextInt(4), 40, 20.0));
        }

        for (int i = 0; i < numFullTime; i++) {
            company.addEmployee(new FullTimeEmployeeC("FTE" + i, new Random().nextInt(4), new Random().nextDouble() * 100000));
        }

        for (int i = 0; i < numPartTime; i++) {
            company.addEmployee(new PartTimeEmployeeC("PTE" + i, new Random().nextInt(4), new Random().nextDouble() * 50000));
        }

        return company;
    }
}

class ConfigurableCompanyFactory implements ICompanyFactory {
    @Override
    public Company createCompany() {
    Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("LabPart3_2.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int numHourly = Integer.parseInt(properties.getProperty("company.hourly"));
        int numFullTime = Integer.parseInt(properties.getProperty("company.fulltime"));
        int numPartTime = Integer.parseInt(properties.getProperty("company.parttime"));
        String companyName = properties.getProperty("company.name");

        Company company = new Company(companyName);

        for (int i = 0; i < numHourly; i++) {
            company.addEmployee(new HourlyEmployeeC("HE" + i, new Random().nextInt(4), 40, 20.0));
        }

        for (int i = 0; i < numFullTime; i++) {
            company.addEmployee(new FullTimeEmployeeC("FTE" + i, new Random().nextInt(4), new Random().nextDouble() * 100000));
        }

        for (int i = 0; i < numPartTime; i++) {
            company.addEmployee(new PartTimeEmployeeC("PTE" + i, new Random().nextInt(4), new Random().nextDouble() * 50000));
        }

               return company;
    }
}



        

