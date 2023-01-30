import java.util.Scanner;

public class Main {
    public static boolean flagToContinue = true;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        YearlyReport yearlyReport = new YearlyReport();
        MonthlyReport monthlyReport = new MonthlyReport();
        Comparator сomparator = new Comparator(monthlyReport, yearlyReport);
        while(flagToContinue) {
            String[] menu = {"1 - Считать все месячные отчеты", "2 - Считать годовой отчет", "3 - Сверить отчеты",
                             "4 - Вывести информацию о всех месячных отчётах", "5 - Вывести информацию о годовом отчёте",
                             "exit - Выход"};
            System.out.println("Выберите действие:");
            for (String item : menu) {
                System.out.println(item);
            }
            String itemMenu = scanner.next();
            actionOfMenu(itemMenu, yearlyReport, monthlyReport, сomparator);
        }
        scanner.close();
    }

    static void actionOfMenu(String itemMenu, YearlyReport yearlyReport, MonthlyReport monthlyReport, Comparator сomparator) {
        switch (itemMenu) {
            case "1":
                for (int i = 1; i < 4; i++) {
                    String fileName = "resources/m.20210" + i +".csv";
                    monthlyReport.readReport(i, fileName);
                }
                break;
            case "2":
                for (int i = 1; i < 2; i++) {
                    String fileName = "resources/y.202" + i + ".csv";
                    yearlyReport.readReport(fileName);
                }
                break;
            case "3":
                if (!monthlyReport.allMonthlyReport.isEmpty() && !yearlyReport.allYearlyReport.isEmpty()) {
                    сomparator.compare();
                } else {
                    System.out.println("Сначала считайте месячные и годовые отчеты!");
                }
                break;
            case "4":
                if (!monthlyReport.allMonthlyReport.isEmpty()) {
                    for (int i = 1; i < 4; i++) {
                        monthlyReport.getInfo(i);
                    }
                } else {
                        System.out.println("Сначала считайте месячные отчеты!");
                    }
                break;
            case "5":
                if (!yearlyReport.allYearlyReport.isEmpty()) {
                    yearlyReport.printInfo();
                } else {
                    System.out.println("Сначала считайте годовые отчеты!");
                }
                break;
            case "exit":
                flagToContinue = false;
                return;
            default:
                System.out.println("Такой команды нет!");
                break;
        }
    }

    public static String getNameOfMonth(int month) {
        String name = null;
        switch (month) {
            case 1:
                name = "Январь";
                break;
            case 2:
                name = "Февраль";
                break;
            case 3:
                name = "Март";
                break;
            case 4:
                name = "Аперль";
                break;
        }
        return name;
    }
}

