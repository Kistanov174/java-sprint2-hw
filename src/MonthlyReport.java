import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MonthlyReport {
    List<String> report;
    List<RecordMthReport> allMonthlyReport = new ArrayList<>();

    void readReport(int month, String path) {
        report = readReportFile(path);
        for (int i = 1; i < report.size(); i++) {
            String[] line = report.get(i).split(",");
            String itemName = line[0];
            boolean isExpense = Boolean.parseBoolean(line[1]);
            int quantity = Integer.parseInt(line[2]);
            int sumOfOne = Integer.parseInt(line[3]);
            RecordMthReport record = new RecordMthReport(month, itemName, isExpense, quantity, sumOfOne);
            allMonthlyReport.add(record);
        }
    }
    private List<String> readReportFile(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        }
        catch(IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }

    void getInfo(int month) {
        System.out.println(Main.getNameOfMonth(month));
        getMaxExpenseAndIncomeInMonth(month);
    }

    private void getMaxExpenseAndIncomeInMonth(int month) {
        int maxExpense = 0;
        int maxIncome = 0;
        String expenseName = null;
        String incomeName = null;
        HashMap<String, Integer> monthlyExpense = new HashMap<>();
        HashMap<String, Integer> monthlyIncome = new HashMap<>();
        for (RecordMthReport line : allMonthlyReport) {
            if (line.month != month) {
                continue;
            }
            if (line.isExpense) {
                int expense = monthlyExpense.getOrDefault(line.itemName, 0) + line.quantity * line.sumOfOne;
                monthlyExpense.put(line.itemName, expense);
            }
            if (!line.isExpense) {
                int income = monthlyIncome.getOrDefault(line.itemName, 0) + line.quantity * line.sumOfOne;
                monthlyIncome.put(line.itemName, income);
            }
        }
        for (String itemName : monthlyExpense.keySet()) {
            int expense = monthlyExpense.get(itemName);
            if (expense > maxExpense) {
                maxExpense = expense;
                expenseName = itemName;
            }
        }
        for (String itemName : monthlyIncome.keySet()) {
            int expense = monthlyIncome.get(itemName);
            if (expense > maxIncome) {
                maxIncome = expense;
                incomeName = itemName;
            }
        }
        System.out.println("Самая большая трата: " + maxExpense + " на " + "'" + expenseName + "'");
        System.out.println("Самый прибыльный товар: " + "'" + incomeName + "'" + " на сумму " + maxIncome);
    }
}
