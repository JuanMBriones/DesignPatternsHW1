package com.itesm.financial;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PrintReport extends GenerateReport {
    private final static String START_SEPARATOR = "\t";
    private final static String END_SEPARATOR = "\t";
    private final static String OPEN_CLOSURE = "";
    private final static String CLOSE_CLOSURE = "";
    private final static String OPEN_ROW = "";
    private final static String CLOSE_ROW = "\t\n";
    private final static String START_TITLE = "\n-------- ";
    private final static String END_TITLE = " --------\n";
    private final static String START_SEPARATOR_HEADER = "\t | ";
    private final static String END_SEPARATOR_HEADER = "";
    

    public PrintReport() {
        super(START_SEPARATOR, END_SEPARATOR, OPEN_CLOSURE, CLOSE_CLOSURE, OPEN_ROW, CLOSE_ROW, START_TITLE, END_TITLE, START_SEPARATOR_HEADER, END_SEPARATOR_HEADER);
    }
    
    @Override
    public String formatAmount(double amount) {
        if(amount < 0) {
            return "(" + amount + ")";
        }
        return Double.toString(amount);
    }

    @Override
    public void generateReport(List<Ride> result) throws IOException {
        String textReport = createContent(result);
        System.out.println(textReport);
    }    

}
