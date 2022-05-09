package com.itesm.financial;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class WebReport extends GenerateReport {
    private final static String START_SEPARATOR = "<td>";
    private final static String END_SEPARATOR = "</td>";
    private final static String OPEN_CLOSURE = "<table>";
    private final static String CLOSE_CLOSURE = "</table>";
    private final static String OPEN_ROW = "<tr>";
    private final static String CLOSE_ROW = "</tr>";
    private final static String START_TITLE = "<h1>";
    private final static String END_TITLE = "</h1>";
    private final static String START_SEPARATOR_HEADER = "<th>";
    private final static String END_SEPARATOR_HEADER = "</th>";

    public WebReport() {
        super(START_SEPARATOR, END_SEPARATOR, OPEN_CLOSURE, CLOSE_CLOSURE, OPEN_ROW, CLOSE_ROW, START_TITLE, END_TITLE, START_SEPARATOR_HEADER, END_SEPARATOR_HEADER);
    }
    @Override
    public String formatAmount(double amount) {
        if(amount < 0) {
            return "<span style='color:red'>" + amount + "</span>";
        }
        return Double.toString(amount);
    }

    @Override
    public void generateReport(List<Ride> result) throws IOException {
        String htmlReport = createContent(result);
        System.out.println(htmlReport);
        FileManager.createFile(htmlReport);
    }

}
