import java.util.Scanner;

public class Main {
    public static boolean flagToContinue = true;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        YearlyReport yearlyReport = new YearlyReport();
        MonthlyReport monthlyReport = new MonthlyReport();
        Comparator comparator = new Comparator(monthlyReport, yearlyReport);
        while(flagToContinue) {
            getMenu();
            System.out.println("Выберите действие:");
            String itemMenu = scanner.next();
            actionOfMenu(itemMenu, yearlyReport, monthlyReport, comparator);
        }
        scanner.close();
    }

    private static void actionOfMenu(String itemMenu, YearlyReport yearlyReport, MonthlyReport monthlyReport, Comparator comparator) {
        switch (itemMenu) {
            case "1":
                readMonthlyReport(monthlyReport);
                break;
            case "2":
                readYearlyReport(yearlyReport);
                break;
            case "3":
                compareReports(monthlyReport, yearlyReport, comparator);
                break;
            case "4":
                showMonthlyReports(monthlyReport);
                break;
            case "5":
                showYearlyReports(yearlyReport);
                break;
            case "exit":
                flagToContinue = false;
                return;
            default:
                System.out.println("Такой команды нет!");
                break;
        }
    }

    private static void getMenu() {
        String[] menu = {"1 - Считать все месячные отчеты", "2 - Считать годовой отчет", "3 - Сверить отчеты",
                "4 - Вывести информацию о всех месячных отчётах", "5 - Вывести информацию о годовом отчёте",
                "exit - Выход"};
        for (String item : menu) {
            System.out.println(item);
        }
    }

    private static void readMonthlyReport(MonthlyReport monthlyReport) {
        for (int i = 1; i < 4; i++) {
            String fileName = "resources/m.20210" + i +".csv";
            monthlyReport.readReport(i, fileName);
        }
        System.out.println("Месячеые отчеты успешно считаны!");
    }

    private static void readYearlyReport(YearlyReport yearlyReport) {
        for (int i = 1; i < 2; i++) {
            String fileName = "resources/y.202" + i + ".csv";
            yearlyReport.readReport(fileName);
        }
        System.out.println("Годовой отчет успешно считан!");
    }

    private static void compareReports(MonthlyReport monthlyReport, YearlyReport yearlyReport, Comparator comparator) {
        if (!monthlyReport.allMonthlyReport.isEmpty() && !yearlyReport.allYearlyReport.isEmpty()) {
            comparator.compare();
        } else {
            System.out.println("Сначала считайте месячные и годовые отчеты!");
        }
    }

    private static void showMonthlyReports(MonthlyReport monthlyReport) {
        if (!monthlyReport.allMonthlyReport.isEmpty()) {
            for (int i = 1; i < 4; i++) {
                monthlyReport.getInfo(i);
            }
        } else {
            System.out.println("Сначала считайте месячные отчеты!");
        }
    }

    private static void showYearlyReports(YearlyReport yearlyReport) {
        if (!yearlyReport.allYearlyReport.isEmpty()) {
            yearlyReport.getInfo();
        } else {
            System.out.println("Сначала считайте годовые отчеты!");
        }
    }

    public static String getNameOfMonth(int month) {
        String name;
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
            default:
                name = "Unknown thing!";
                break;
        }
        return name;
    }
}

