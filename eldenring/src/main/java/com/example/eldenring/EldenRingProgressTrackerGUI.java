package com.example.eldenring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EldenRingProgressTrackerGUI {
    private JFrame frame;
    private List<Area> areas;

    public EldenRingProgressTrackerGUI() {
        areas = new ArrayList<>();
        initializeAreas();
        initializeFrame();
    }

    private void initializeAreas() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("areas.dat"))) {
            areas = (List<Area>) ois.readObject();
        } catch (FileNotFoundException e) {
            areas.add(new Area("West Limgrave", List.of(
                    new Task("Buy essential gear"),
                    new Task("Obtain Limgrave West Map Fragment"),
                    new Task("Obtain Whetstone Knife"),
                    new Task("Unlock Torrent & Spirit Bell"),
                    new Task("Do Boc the Seamster's Quest"),
                    new Task("Defeat Night's Cavalry"),
                    new Task("Find Sorceress Sellen (if you're a mage)"),
                    new Task("Talk to Roderika"),
                    new Task("Find Ashes of War Merchant"),
                    new Task("Meet Potboy (Alexander)"),
                    new Task("Talk to D, Hunter of the Dead"),
                    new Task("Complete Murkwater Cave")
            ), "West Limgrave Description",
                    "West Limgrave Quests Description"));
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Data not found, Initializing with default data.");
        }
    }

    private void initializeFrame() {
        frame = new JFrame("Elden Ring Progress Tracker");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
    }

    public void showMainMenu() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        JButton routesButton = new JButton("Routes");
        JButton questsButton = new JButton("Quests");
        JButton exitButton = new JButton("Exit");

        routesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAreas();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveAreas();
                frame.dispose();
            }
        });

        panel.add(routesButton);
        panel.add(questsButton);
        panel.add(exitButton);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void saveAreas() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("areas.dat"))) {
            oos.writeObject(areas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAreas() {
        JPanel panel = new JPanel(new GridLayout(areas.size() + 1, 1, 10, 10));

        for (Area area : areas) {
            JButton areaButton = new JButton(area.getName());
            areaButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showAreaDetails(area);
                }
            });
            panel.add(areaButton);
        }

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMainMenu();
            }
        });
        panel.add(backButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showAreaDetails(Area area) {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        JButton tasksButton = new JButton("See diminished route");
        JButton detailedDescriptionButton = new JButton("See detailed route");
        JButton backButton = new JButton("Back");

        tasksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showTasks(area);
            }
        });

        detailedDescriptionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDetailedDescription(area);
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAreas();
            }
        });

        panel.add(tasksButton);
        panel.add(detailedDescriptionButton);
        panel.add(backButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showTasks(Area area) {
        JPanel panel = new JPanel(new GridLayout(area.getTasks().size() + 1, 1, 10, 10));

        for (Task task : area.getTasks()) {
            JCheckBox taskCheckBox = new JCheckBox(task.getDescription(), task.isCompleted());
            taskCheckBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    task.toggleCompleted();
                }
            });
            panel.add(taskCheckBox);
        }

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAreaDetails(area);
            }
        });
        panel.add(backButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showDetailedDescription(Area area) {
        JTextArea textArea = new JTextArea(area.getDetailedDescription());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAreaDetails(area);
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }
}
