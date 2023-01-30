import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class YearlyReport {
    List<String> report;
    List<RecordYearReport> allYearlyReport = new ArrayList<>();
    HashMap<Integer, Integer> expenseInMonth = new HashMap<>();
    HashMap<Integer, Integer> incomeInMonth = new HashMap<>();

    void readReport(String path) {
        report = readReportFile(path);
        for (int i = 1; i < report.size(); i++) {
            String[] dataOfLine = report.get(i).split(",");
            int month = Integer.parseInt(dataOfLine[0]);
            int amount = Integer.parseInt(dataOfLine[1]);
            boolean isExpense = Boolean.parseBoolean(dataOfLine[2]);
            RecordYearReport newRecord = new RecordYearReport(month, amount, isExpense);
            allYearlyReport.add(newRecord);
        }
        for (RecordYearReport record : allYearlyReport) {
            if (record.isExpense) {
                expenseInMonth.put(record.month, record.amount);
            } else {
                incomeInMonth.put(record.month, record.amount);
            }
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

    void printInfo() {
        System.out.println("Отчет за 2021 год");
        for(int i = 1; i <= incomeInMonth.size(); i++) {
            System.out.println("Прибыль за " + Main.getNameOfMonth(i) + " месяц " + getProfit(i));
        }
        System.out.println("Средний расход за все месяцы в году составил: " + avrValueOf(expenseInMonth));
        System.out.println("Средний доход за все месяцы в году составил: " + avrValueOf(incomeInMonth));
    }

    private int getProfit(int month) {
        return incomeInMonth.get(month) - expenseInMonth.get(month);
    }

    private int avrValueOf(HashMap<Integer, Integer> monthlyData) {
        int sum = 0;
        for (Integer monthValue : monthlyData.values()) {
            sum += monthValue;
        }
        return sum / monthlyData.size();
    }
}
