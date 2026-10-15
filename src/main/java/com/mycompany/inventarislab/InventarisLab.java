package com.mycompany.inventarislab;
import view.TampilkanInventarisLab;
public class InventarisLab {
    public static void main(String[] args) {
        
        System.setProperty("java.util.logging.config.file", "");
        java.util.logging.LogManager.getLogManager().reset();
        java.util.logging.Logger rootLogger = java.util.logging.Logger.getLogger("");
        rootLogger.setLevel(java.util.logging.Level.SEVERE); 
        
        TampilkanInventarisLab view = new TampilkanInventarisLab();
        view.tampilkanMenu();
    }
}