package com.example.application;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.example.eldenring.EldenRingProgressTrackerGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Set the FlatLaf theme
        FlatDarculaLaf.setup();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EldenRingProgressTrackerGUI().showMainMenu();
            }
        });
    }
}
