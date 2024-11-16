import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.JFrame;

public class ExpensePieChart extends JFrame {

    public ExpensePieChart(String title) {
        super(title);

        // Create dataset
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Groceries", 217);
        dataset.setValue("Transport", 12);
        dataset.setValue("Leisure", 0);
        dataset.setValue("Restaurant", 0);
        dataset.setValue("Health", 0);
        dataset.setValue("Family", 15);
        dataset.setValue("Gifts", 0);
        dataset.setValue("Shopping", 0);
        dataset.setValue("Pets", 0);
        dataset.setValue("Education", 0);
        dataset.setValue("Traveling", 270);

        // Create chart
        JFreeChart chart = ChartFactory.createPieChart(
                "Expense Categories Overview",
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
        ExpensePieChart example = new ExpensePieChart("Kwartrack Expense Overview");
        example.setSize(800, 600);
        example.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        example.setVisible(true);
    }
}
