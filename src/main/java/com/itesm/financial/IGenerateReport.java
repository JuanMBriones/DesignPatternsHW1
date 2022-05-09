package com.itesm.financial;

import java.io.IOException;
import java.util.List;

public interface IGenerateReport {
    String createContent(List<Ride> rides);
    void generateReport(List<Ride> result) throws IOException;
    public String formatAmount(double amount);
}
