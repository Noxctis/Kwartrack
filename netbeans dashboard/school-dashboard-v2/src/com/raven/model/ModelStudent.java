package com.raven.model;

//import com.raven.swing.table.EventAction;
//import com.raven.swing.table.ModelAction;
import com.raven.swing.table.ModelProfile;
import java.text.DecimalFormat;
//import javax.swing.Icon;

public class ModelStudent {

//    public Icon getIcon() {
//        return icon;
//    }
                                        //plus or minus for later
//    public void setIcon(Icon icon) {
//        this.icon = icon;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getdate() {
        return date;
    }

    public void setdate(String date) {
        this.date = date;
    }

//    public String getCourse() {
//        return course;
//    }
//
//    public void setCourse(String course) {
//        this.course = course;
//    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ModelStudent(String type, String name, String date, double amount) {
        //this.icon = icon;
        this.type = type;
        this.name = name;
        this.date = date;
        this.amount = amount;
        //this.fees = fees;
    }

    public ModelStudent() {
    }
    private String type;
    private String name;
    private String date;
    private double amount;
   

    public Object[] toRowTable(/*EventAction event*/) {
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        //return new Object[]{new ModelProfile(/*icon,*/name), date, df.format(amount)/*, new ModelAction(this, event)*/};
        //return new Object[]{type, new ModelProfile(name), date, df.format(amount)}; //with icon thing
        return new Object[]{type, name, date, df.format(amount)};
    }
}
