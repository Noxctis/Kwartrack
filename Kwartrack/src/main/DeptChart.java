import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.JFrame;

public class DebtPieChart extends JFrame {

    public DebtPieChart(String title, double paidDebt, double unpaidDebt) {
        super(title);

        // Create dataset
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Paid Debt", paidDebt);
        dataset.setValue("Unpaid Debt", unpaidDebt);

        // Create chart
        JFreeChart chart = ChartFactory.createPieChart(
                "Debt Status Overview",
                dataset,
                true, // Include legend
                true,
                false
        );

        // Customize the chart panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    public static void main(String[] args) {
        // Example data - you would fetch and calculate these from your database
        double paidDebt = 5000; // Sum of all paid debts
        double unpaidDebt = 15000; // Sum of all unpaid debts

        DebtPieChart chart = new DebtPieChart("Kwartrack Debt Analysis", paidDebt, unpaidDebt);
        chart.setSize(800, 600);
        chart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chart.setVisible(true);
    }
}
