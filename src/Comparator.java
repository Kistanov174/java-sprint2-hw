import java.util.HashMap;
import java.util.Map;

public class Comparator {
    private final MonthlyReport monthlyReport;
    private final YearlyReport yearlyReport;

    public Comparator(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        this.monthlyReport = monthlyReport;
        this.yearlyReport = yearlyReport;
    }

    public void compare() {
        Map<Integer, Integer> expenseInMonth = new HashMap<>();
        Map<Integer, Integer> incomeInMonth = new HashMap<>();
        for (int i = 1; i < 4; i++) {
            int expenses = 0;
            int incomes = 0;
            for (RecordMthReport record : monthlyReport.allMonthlyReport) {
                if (record.isExpense && record.month == i) {
                    expenses += record.quantity * record.sumOfOne;
                }
                if (!record.isExpense && record.month == i) {
                    incomes += record.quantity * record.sumOfOne;
                }
            }
            expenseInMonth.put(i, expenses);
            incomeInMonth.put(i, incomes);
            boolean isExpensesCorrect = expenseInMonth.get(i).equals(yearlyReport.expenseInMonth.get(i));
            boolean isIncomesCorrect = incomeInMonth.get(i).equals(yearlyReport.incomeInMonth.get(i));
            if (!isExpensesCorrect || !isIncomesCorrect) {
                System.out.println("В месяце " + Main.getNameOfMonth(i) + " обнаружено несоответсвия данных!");
            }
        }
        System.out.println("Операция сверки завершена!");
    }
}
