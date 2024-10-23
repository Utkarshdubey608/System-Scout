 package com.mymavenprojects;

 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.io.BufferedReader;
 import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
 import java.io.OutputStreamWriter;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.util.HashMap;
 import java.util.Map;
 import java.util.List;
import java.util.ArrayList;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

 import com.google.auth.oauth2.GoogleCredentials;
 import com.google.firebase.FirebaseApp;
 import com.google.firebase.FirebaseOptions;
 import com.google.firebase.database.DataSnapshot;
 import com.google.firebase.database.DatabaseError;
 import com.google.firebase.database.DatabaseReference;
 import com.google.firebase.database.FirebaseDatabase;
 import com.google.firebase.database.ValueEventListener;


 import javax.swing.*;
 import javax.swing.border.EmptyBorder;
 


 import java.awt.*;


 import io.github.cdimascio.dotenv.Dotenv;

 class ImagePanel extends JPanel {
    private Image backgroundImage;

    public ImagePanel(String imagePath) {
        backgroundImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
 

public class DeviceInfoGUI {

    public static String userid="";
    private static DatabaseReference databaseReference;

    public DeviceInfoGUI() {
        try {

            FileInputStream serviceAccount = new FileInputStream("C:\\codes\\aoodp_maven_project(try)beta\\mydemoproject\\src\\main\\resources\\aoop-project-fde23-firebase-adminsdk-pvvl2-cc88314796.json"); // Update with your path

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://aoop-project-fde23-default-rtdb.firebaseio.com/") // Update with your Firebase DB URL
                    .build();




            FirebaseApp.initializeApp(options);
            databaseReference = FirebaseDatabase.getInstance().getReference();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




















public void sell_window() {
    JFrame sellFrame = new JFrame("Sell Parts");
    sellFrame.setSize(600, 400);
    sellFrame.setLayout(new BorderLayout());
    sellFrame.getContentPane().setBackground(new Color(30, 30, 30));

    // Set font for labels
    Font labelFont = new Font("Arial", Font.BOLD, 14);
    Font fieldFont = new Font("Arial", Font.PLAIN, 12);

    // Title label
    JLabel sellLabel = new JLabel("Enter the details of the part you want to sell:");
    sellLabel.setHorizontalAlignment(SwingConstants.CENTER);
    sellLabel.setForeground(Color.WHITE);
    sellLabel.setFont(new Font("Arial", Font.BOLD, 16));
    sellLabel.setBorder(new EmptyBorder(20, 0, 10, 0));

    // Input panel with GridLayout and spacing
    JPanel inputPanel = new JPanel();
    inputPanel.setLayout(new GridLayout(4, 2, 10, 10)); // Add spacing
    inputPanel.setBackground(new Color(30, 30, 30));
    inputPanel.setBorder(new EmptyBorder(20, 40, 20, 40)); // Padding around panel

    // Input fields for part details
    JLabel codeLabel = new JLabel("Part Code (see from prev window):");
    codeLabel.setForeground(Color.LIGHT_GRAY);
    codeLabel.setFont(labelFont);
    JTextField codeField = new JTextField();
    codeField.setForeground(Color.WHITE);
    codeField.setBackground(new Color(50, 50, 50));
    codeField.setCaretColor(Color.WHITE);
    codeField.setFont(fieldFont);
    codeField.setToolTipText("Enter part code from the previous window");

    JLabel nameLabel = new JLabel("Part Name:");
    nameLabel.setForeground(Color.LIGHT_GRAY);
    nameLabel.setFont(labelFont);
    JTextField nameField = new JTextField();
    nameField.setForeground(Color.WHITE);
    nameField.setBackground(new Color(50, 50, 50));
    nameField.setCaretColor(Color.WHITE);
    nameField.setFont(fieldFont);
    nameField.setToolTipText("Enter the name of the part");

    JLabel descLabel = new JLabel("Description:");
    descLabel.setForeground(Color.LIGHT_GRAY);
    descLabel.setFont(labelFont);
    JTextField descField = new JTextField();
    descField.setForeground(Color.WHITE);
    descField.setBackground(new Color(50, 50, 50));
    descField.setCaretColor(Color.WHITE);
    descField.setFont(fieldFont);
    descField.setToolTipText("Provide a short description of the part");

    // Add input components to panel
    inputPanel.add(codeLabel);
    inputPanel.add(codeField);
    inputPanel.add(nameLabel);
    inputPanel.add(nameField);
    inputPanel.add(descLabel);
    inputPanel.add(descField);

    // Modern button styling with rounded corners and hover effect
    JButton sellButton = new JButton("Sell");
    sellButton.setForeground(Color.WHITE);
    sellButton.setBackground(new Color(0, 153, 0));
    sellButton.setFocusPainted(false);
    sellButton.setFont(new Font("Arial", Font.BOLD, 14));
    sellButton.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 0), 2));
    sellButton.setPreferredSize(new Dimension(100, 40));

    // Hover effect for the button
    sellButton.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            sellButton.setBackground(new Color(0, 204, 0));
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            sellButton.setBackground(new Color(0, 153, 0));
        }
    });

    // Action listener for selling part
    sellButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String code = codeField.getText();
            String name = nameField.getText();
            String description = "";
            
            // Append specific part information based on code
            if (code.equals("0")) {
                description = descField.getText() +"\n"+ getGPUInfo();
            } else if (code.equals("1")) {
                description = descField.getText() +"\n"+ getDiskInfo();
            } else if (code.equals("2")) {
                description = descField.getText() + "\n"+getDisplayInfo();
            } else if (code.equals("3")) {
                description = descField.getText() + "\n"+getProcessorInfo();
            } else if (code.equals("4")) {
                description = descField.getText() +"\n"+ getBatteryInfo();
            } else {
                description = descField.getText() + "\n[Original info failed to retrieve]";
            }

            if (code.isEmpty() || name.isEmpty() || description.isEmpty()) {
                JOptionPane.showMessageDialog(sellFrame, "All fields must be filled out.");
                return;
            }

            // Add part to Firebase with a unique key
            String uniqueKey = databaseReference.child("parts").push().getKey();
            if (uniqueKey != null) {
                Map<String, Object> part = new HashMap<>();
                part.put("partcode", code);
                part.put("partname", name);
                part.put("description", description);
                part.put("soldby", userid);

                databaseReference.child("parts").child(uniqueKey).setValue(part, (error, ref) -> {
                    if (error != null) {
                        JOptionPane.showMessageDialog(sellFrame, "Failed to sell part: " + error.getMessage());
                    } else {
                        JOptionPane.showMessageDialog(sellFrame, "Part listed successfully!");
                        sellFrame.dispose();
                    }
                });
            }
        }
    });

    // Add components to frame
    sellFrame.add(sellLabel, BorderLayout.NORTH);
    sellFrame.add(inputPanel, BorderLayout.CENTER);
    sellFrame.add(sellButton, BorderLayout.SOUTH);

    sellFrame.setVisible(true);
}


    



public void buy_window() {
    JFrame buyFrame = new JFrame("Buy Parts");
    buyFrame.setSize(600, 500);
    buyFrame.setLayout(new BorderLayout());
    buyFrame.getContentPane().setBackground(new Color(30, 30, 30));

    // Font for labels
    Font labelFont = new Font("Arial", Font.BOLD, 14);
    Font buttonFont = new Font("Arial", Font.PLAIN, 12);

    // Panel to hold parts
    JPanel partsPanel = new JPanel();
    partsPanel.setLayout(new BoxLayout(partsPanel, BoxLayout.Y_AXIS));
    partsPanel.setBackground(new Color(30, 30, 30));
    partsPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding around panel

    // HashMap to group parts by seller
    Map<String, List<JPanel>> sellerPartsMap = new HashMap<>();

    // Fetch data from Firebase (parts)
    databaseReference.child("parts").addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot snapshot) {
            if (snapshot.exists()) {
                for (DataSnapshot partSnapshot : snapshot.getChildren()) {
                    String partcode = partSnapshot.child("partcode").getValue(String.class);
                    String partname = partSnapshot.child("partname").getValue(String.class);
                    String description = partSnapshot.child("description").getValue(String.class);
                    String soldby = partSnapshot.child("soldby").getValue(String.class);

                    if (partcode != null && partname != null && soldby != null) {
                        JPanel partPanel = new JPanel();
                        partPanel.setLayout(new BorderLayout()); // Set layout to BorderLayout
                        partPanel.setBackground(new Color(45, 45, 45));
                        partPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Padding inside panel
                        partPanel.setMaximumSize(new Dimension(500, 250)); // Limit size of each panel

                        // Panel for part details (text)
                        JPanel detailsPanel = new JPanel();
                        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
                        detailsPanel.setBackground(new Color(45, 45, 45));

                        // Part details
                        JLabel partCodeLabel = new JLabel("Part Code: " + partcode);
                        partCodeLabel.setForeground(Color.LIGHT_GRAY);
                        partCodeLabel.setFont(labelFont);

                        JLabel partNameLabel = new JLabel("Part Name: " + partname);
                        partNameLabel.setForeground(Color.LIGHT_GRAY);
                        partNameLabel.setFont(labelFont);

                        // Add text labels to details panel
                        detailsPanel.add(partCodeLabel);
                        detailsPanel.add(partNameLabel);
                        detailsPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Space between labels and button

                        // Modern "See More" button
                        JButton buyButton = new JButton("See More");
                        buyButton.setForeground(Color.WHITE);
                        buyButton.setBackground(new Color(0, 153, 0)); // Green button
                        buyButton.setFont(buttonFont);
                        buyButton.setFocusPainted(false);
                        buyButton.setPreferredSize(new Dimension(100, 30));
                        buyButton.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 0), 2));

                        // Hover effect for the "See More" button
                        buyButton.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                buyButton.setBackground(new Color(0, 204, 0));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
                                buyButton.setBackground(new Color(0, 153, 0));
                            }
                        });

                        // Show description when button is clicked
                        buyButton.addActionListener(e -> 
                            JOptionPane.showMessageDialog(buyFrame, "Description: " + description, "Part Details", JOptionPane.INFORMATION_MESSAGE));

                        // Add button to detailsPanel
                        detailsPanel.add(buyButton);

                        // Image logic
                        String image = "";
                        if (partcode.equals("0")) {
                            image = "C:\\codes\\aoodp_maven_project(try)beta\\mydemoproject\\src\\main\\java\\com\\mymavenprojects\\assets\\graphiccard-removebg-preview.png";
                        } else if (partcode.equals("1")) {
                            image = "C:\\codes\\aoodp_maven_project(try)beta\\mydemoproject\\src\\main\\java\\com\\mymavenprojects\\assets\\ssd-removebg-preview.png";
                        } else if (partcode.equals("2")) {
                            image = "C:\\codes\\aoodp_maven_project(try)beta\\mydemoproject\\src\\main\\java\\com\\mymavenprojects\\assets\\display-removebg-preview.png";
                        } else if (partcode.equals("3")) {
                            image = "C:\\codes\\aoodp_maven_project(try)beta\\mydemoproject\\src\\main\\java\\com\\mymavenprojects\\assets\\cpu-removebg-preview.png";
                        } else if (partcode.equals("4")) {
                            image = "C:\\codes\\aoodp_maven_project(try)beta\\mydemoproject\\src\\main\\java\\com\\mymavenprojects\\assets\\battery-removebg-preview.png";
                        }

                        ImageIcon imageIcon = new ImageIcon(image);
                        JLabel imageLabel = new JLabel(imageIcon);

                        // Add the detailsPanel to the CENTER of partPanel
                        partPanel.add(detailsPanel, BorderLayout.CENTER);
                        // Add the imageLabel to the EAST side of partPanel
                        partPanel.add(imageLabel, BorderLayout.EAST);

                        // Add partPanel to the corresponding seller's list in the HashMap
                        sellerPartsMap.computeIfAbsent(soldby, k -> new ArrayList<>()).add(partPanel);
                    }
                }

                // Now add each seller's parts to the main panel
                for (String seller : sellerPartsMap.keySet()) {
                    JLabel sellerLabel = new JLabel("Listed By: " + seller);
                    sellerLabel.setForeground(new Color(255, 69, 0)); // Highlight the seller in red
                    sellerLabel.setFont(labelFont);
                    partsPanel.add(sellerLabel);
                    partsPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Space between seller label and parts

                    // Add all parts for this seller
                    for (JPanel partPanel : sellerPartsMap.get(seller)) {
                        partsPanel.add(partPanel);
                        partsPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Space between different part panels
                    }
                }

            } else {
                // If no parts available
                JOptionPane.showMessageDialog(buyFrame, "No parts available for sale.");
            }
        }

        @Override
        public void onCancelled(DatabaseError error) {
            JOptionPane.showMessageDialog(buyFrame, "Failed to load data: " + error.getMessage());
        }
    });

    // Scroll pane for partsPanel to make it scrollable
    JScrollPane scrollPane = new JScrollPane(partsPanel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.setBorder(null);
    scrollPane.getViewport().setBackground(new Color(30, 30, 30)); 

    // Add scrollPane to the frame
    buyFrame.add(scrollPane, BorderLayout.CENTER);

    // Set visible
    buyFrame.setVisible(true);
}


public static void mainui(){
    DeviceInfoGUI deviceInfo = new DeviceInfoGUI();

    // Main frame
    JFrame frame = new JFrame("Device Information");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1000, 600);
    frame.setLayout(new BorderLayout());
    frame.getContentPane().setBackground(new Color(34, 34, 34));  // Dark grey background

    // Text area for device info
    JTextArea textArea = new JTextArea();
    textArea.setEditable(false);
    textArea.setFont(new Font("Consolas", Font.PLAIN, 14));  // Sleek monospaced font
    textArea.setForeground(new Color(0, 255, 128));  // Neon green text
    textArea.setBackground(new Color(24, 24, 24));  // Darker background for contrast
    textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Padding around text

    JScrollPane scrollPane = new JScrollPane(textArea);
    scrollPane.setBorder(null);  // Remove borders for a clean look

    // Info panel
    JPanel infoPanel = new JPanel(new BorderLayout());
    infoPanel.add(scrollPane, BorderLayout.CENTER);
    infoPanel.setBackground(new Color(34, 34, 34));  // Match background color

    // Summary area
    JTextArea summaryArea = new JTextArea();
    summaryArea.setEditable(false);
    summaryArea.setFont(new Font("Consolas", Font.PLAIN, 14));
    summaryArea.setForeground(new Color(255, 99, 71));  // Bright red for summary
    summaryArea.setBackground(new Color(24, 24, 24));  // Darker background
    summaryArea.setLineWrap(true);
    summaryArea.setWrapStyleWord(true);
    summaryArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    JScrollPane summaryScrollPane = new JScrollPane(summaryArea);
    summaryScrollPane.setBorder(null);

    // Summary panel
    JPanel summaryPanel = new JPanel(new BorderLayout());
    summaryPanel.setPreferredSize(new Dimension(300, 0));  // Set fixed width
    summaryPanel.add(summaryScrollPane, BorderLayout.CENTER);
    summaryPanel.setBackground(new Color(34, 34, 34));

    // Buttons panel
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.setBackground(new Color(34, 34, 34));  // Consistent background

    // Sell button
    JButton sellBtn = new JButton("LIST PARTS");
    sellBtn.setForeground(Color.WHITE);
    sellBtn.setBackground(new Color(255, 140, 0));  // Orange button for a call-to-action
    sellBtn.setFocusPainted(false);
    sellBtn.setPreferredSize(new Dimension(120, 40));
    sellBtn.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 0), 2));

    // Hover effect for sell button
    sellBtn.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            sellBtn.setBackground(new Color(255, 165, 0));
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            sellBtn.setBackground(new Color(255, 140, 0));
        }
    });

    // Buy button
    JButton buyBtn = new JButton("BUY PARTS");
    buyBtn.setForeground(Color.WHITE);
    buyBtn.setBackground(new Color(0, 153, 76));  // Green button for buy action
    buyBtn.setFocusPainted(false);
    buyBtn.setPreferredSize(new Dimension(120, 40));
    buyBtn.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 76), 2));

    // Hover effect for buy button
    buyBtn.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            buyBtn.setBackground(new Color(0, 204, 102));
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            buyBtn.setBackground(new Color(0, 153, 76));
        }
    });

    // Add buttons to button panel
    buttonPanel.add(sellBtn);
    buttonPanel.add(buyBtn);

    // Add components to frame
    frame.add(infoPanel, BorderLayout.CENTER);
    frame.add(summaryPanel, BorderLayout.EAST);
    frame.add(buttonPanel, BorderLayout.SOUTH);

    // Retrieve device information
    StringBuilder info = new StringBuilder();
    info.append(getGPUInfo());
    info.append(getDiskInfo());
    info.append(getDisplayInfo());
    info.append(getProcessorInfo());
    info.append(getBatteryInfo());

    // Set device info to text area
    textArea.setText(info.toString());

    // Worker to summarize the information and display in summaryArea
    SwingWorker<String, Void> worker = new SwingWorker<>() {
        @Override
        protected String doInBackground() {
            String prompt = info.toString() + " summarize it and grade the system on scale of 1 to 10 also make the response more attractive leave some space different points etc";
            return gemini(prompt);
        }

        @Override
        protected void done() {
            try {
                summaryArea.setText(get());
            } catch (Exception e) {
                summaryArea.setText("Failed to get a summary from AI.");
            }
        }
    };
    worker.execute();

    // Add action listeners for sell and buy buttons
    sellBtn.addActionListener(e -> deviceInfo.sell_window());
    buyBtn.addActionListener(e -> deviceInfo.buy_window());

    // Make frame visible
    frame.setVisible(true);
}




    public static void main(String[] args) {
    
        login();




        

    }

    public static String getGPUInfo() {
        // Similar code for fetching GPU Info
        StringBuilder info = new StringBuilder();
        info.append("=== GPU Information (code:0) ===\n" +
               "       .------------------.\n" +
               "      /                    /|\n" +
               "     /       GPU          / |\n" +
               "    /                    /  |\n" +
               "   /____________________/   |\n" +
               "   |                    |   |\n" +
               "   |    .---------.     |   |\n" +
               "   |   /           \\   |   |\n" +
               "   |  |   _______   |   |   |\n" +
               "   |  |  |       |  |   |   |\n" +
               "   |  |  |_______|  |   |   |\n" +
               "   |  \\___________/    |   |\n" +
               "   |____________________|   |\n" +
               "   |________________________|\n\n");
        try {
            ProcessBuilder pb = new ProcessBuilder("powershell", "Get-WmiObject Win32_VideoController | Select-Object Caption, AdapterRAM");
            pb.redirectErrorStream(true);
            Process process = pb.start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    info.append(line).append("\n");
                }
            }
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        info.append("\n");
        return info.toString();

    }

    public static String getDiskInfo() {
        // Similar code for fetching Disk Info
        StringBuilder info = new StringBuilder();
        info.append("=== Disk (Storage) Information (code:1) ===\n" +
                "        .------------------.\n" +
                "       /                    /|\n" +
                "      /     Disk Storage   / |\n" +
                "     /                    /  |\n" +
                "    /____________________/   |\n" +
                "    |                    |   |\n" +
                "    |     ___________    |   |\n" +
                "    |    |           |   |   |\n" +
                "    |    |   HDD/SSD |   |   |\n" +
                "    |    |___________|   |   |\n" +
                "    |                    |   |\n" +
                "    |____________________|   |\n" +
                "    |________________________|\n\n");
        try {
            ProcessBuilder pb = new ProcessBuilder("powershell", "Get-PhysicalDisk | Select-Object DeviceID, Model, Size, @{Name='ReadSpeed';Expression={(Get-PhysicalDisk -DeviceID $_.DeviceID).LinkSpeed}}");
            pb.redirectErrorStream(true);
            Process process = pb.start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    info.append(line).append("\n");
                }
            }
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        info.append("\n");
        return info.toString();
        
    }

    public static String getDisplayInfo() {
        // Similar code for fetching Display Info
        StringBuilder info = new StringBuilder();
        info.append("=== Display Information (code:2) ===\n" +
                    "        .------------------.\n" +
                    "       /                    /|\n" +
                    "      /      Display       / |\n" +
                    "     /                    /  |\n" +
                    "    /____________________/   |\n" +
                    "    |                    |   |\n" +
                    "    |     ___________    |   |\n" +
                    "    |    |           |   |   |\n" +
                    "    |    |   Screen  |   |   |\n" +
                    "    |    |___________|   |   |\n" +
                    "    |                    |   |\n" +
                    "    |____________________|   |\n" +
                    "    |________________________|\n\n");
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        for (GraphicsDevice g : gs) {
            DisplayMode dm = g.getDisplayMode();
            info.append("Screen Device: ").append(g.getIDstring()).append("\n")
                .append("Width: ").append(dm.getWidth()).append("\n")
                .append("Height: ").append(dm.getHeight()).append("\n")
                .append("Bit Depth: ").append(dm.getBitDepth()).append("\n")
                .append("Refresh Rate: ").append(dm.getRefreshRate()).append("\n")
                .append("\n");
        }
        return info.toString();

    }

    public static String getProcessorInfo() {
        // Similar code for fetching Processor Info
        StringBuilder info = new StringBuilder();
        info.append("=== CPU Information (code:3) ===\n" +
               "        .------------------.\n" +
               "       /                    /|\n" +
               "      /      CPU           / |\n" +
               "     /                    /  |\n" +
               "    /____________________/   |\n" +
               "    |                    |   |\n" +
               "    |    ________        |   |\n" +
               "    |   |        |       |   |\n" +
               "    |   |________|       |   |\n" +
               "    |                    |   |\n" +
               "    |____________________|   |\n" +
               "    |________________________|\n\n");

        String arch = System.getProperty("os.arch");
        String processors = System.getenv("NUMBER_OF_PROCESSORS");
        info.append("Architecture: ").append(arch).append("\n")
            .append("Available processors (cores): ").append(Runtime.getRuntime().availableProcessors()).append("\n");
        if (processors != null) {
            info.append("Processor count (from environment): ").append(processors).append("\n");
        }
        info.append("\n");
        return info.toString();
    }

    public static String getBatteryInfo() {
        // Similar code for fetching Battery Info
        StringBuilder info = new StringBuilder();
        info.append("=== Battery Information (code:4) ===\n" +
                   "        .------------------.\n" +
                   "       /                    /|\n" +
                   "      /      Battery       / |\n" +
                   "     /                    /  |\n" +
                   "    /____________________/   |\n" +
                   "    |                    |   |\n" +
                   "    |    .___________.   |   |\n" +
                   "    |   |             |  |   |\n" +
                   "    |   |    [===]    |  |   |\n" +
                   "    |   |_____________|  |   |\n" +
                   "    |                    |   |\n" +
                   "    |____________________|   |\n" +
                   "    |________________________|\n");
        try {
            ProcessBuilder pb = new ProcessBuilder("powershell", "Get-WmiObject Win32_Battery | Select-Object Name, EstimatedChargeRemaining, BatteryStatus");
            pb.redirectErrorStream(true);
            Process process = pb.start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    info.append(line).append("\n");
                }
            }
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        info.append("\n");
        return info.toString();
    }

    public static String gemini(String prompt) {

         // Load the .env file
         Dotenv dotenv = Dotenv.load();
         CloseableHttpClient httpClient = HttpClients.createDefault();

        // Your Google API key
        String apiKey = dotenv.get("api_key"); // Replace with your actual API key

        // API endpoint for Google Generative Language API
        String endpoint = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key=" + apiKey;

        // Prepare the POST request
        HttpPost httpPost = new HttpPost(endpoint);
        httpPost.addHeader("Content-Type", "application/json");

        // JSON payload for the request

        String jsonPayload = String.format( "{\"contents\":[{\"parts\":[{\"text\":\"%s\"}]}]}", prompt);

        try {
            // Set the request body
            StringEntity entity = new StringEntity(jsonPayload);
            httpPost.setEntity(entity);

            // Execute the request
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    // Process the successful response
                    String responseBody = EntityUtils.toString(response.getEntity());
                    JSONObject jsonresponse=new JSONObject(responseBody);

                    System.out.println("Response: " + responseBody);
                    return jsonresponse.getJSONArray("candidates")
                    .getJSONObject(0)
                    .getJSONObject("content")
                    .getJSONArray("parts")
                    .getJSONObject(0)
                    .getString("text");
        
                } else {
                    // Handle error
                    System.out.println("Request failed with status code: " + statusCode);
                    String errorResponse = EntityUtils.toString(response.getEntity());
                    System.out.println("Error response: " + errorResponse);
                    return "error";
                }
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }







    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public static void login() {
        JFrame frame = new JFrame("SYSTEM SCOUT");
        frame.setSize(700, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImagePanel panel = new ImagePanel("C:\\codes\\aoodp_maven_project(try)beta\\mydemoproject\\src\\main\\java\\com\\mymavenprojects\\assets\\loginbg.gif");  // Set your image path here

        frame.add(panel);

        // Adding components to the panel
        placeComponents(panel);

        frame.setVisible(true);
    }













     private static void placeComponents(JPanel panel) {
        // Setting the layout to null
        panel.setLayout(null);

        // Creating JLabel for username and setting its text color
        JLabel userLabel = new JLabel("User:");
        userLabel.setBounds(210, 395, 80, 40);
        userLabel.setForeground(Color.WHITE);  // Set the text color to white
        userLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        panel.add(userLabel);

        // Creating text field for user name
        JTextField userText = new JTextField(20);
        userText.setForeground(Color.WHITE);
        userText.setBackground(Color.BLACK);
        userText.setBounds(270, 400, 165, 40);
        panel.add(userText);

        // Creating JLabel for password and setting its text color
        JLabel passwordLabel = new JLabel("Pass:");
        passwordLabel.setBounds(205, 465, 80, 40);
        passwordLabel.setForeground(Color.WHITE);  // Set the text color to white
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        panel.add(passwordLabel);

        // Creating password field
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setForeground(Color.WHITE);
        passwordText.setBackground(Color.BLACK);
        passwordText.setBounds(270, 470, 165, 40);
        panel.add(passwordText);

        // Creating login button
        JButton loginButton = new JButton("Login");
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(Color.BLACK);
        loginButton.setBounds(310, 550, 80, 25);
        panel.add(loginButton);


        //signup btn
        JButton signupButton = new JButton("Sign Up");
        signupButton.setForeground(Color.WHITE);
        signupButton.setBackground(Color.BLACK);
        signupButton.setBounds(310, 590, 80, 25);
        panel.add(signupButton);






        // Load an image from file (Ensure the file path is correct)
        ImageIcon imageIcon = new ImageIcon("C:\\codes\\aoodp_maven_project(try)beta\\mydemoproject\\src\\main\\java\\com\\mymavenprojects\\assets\\icon_profile.png");  // Replace with your image path
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(225, 120, 200, 200);
        panel.add(imageLabel);

        






// Adding action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the text from the userText and passwordText fields
                String username = userText.getText();
                String password = new String(passwordText.getPassword());  // getPassword() returns a char array


                
                try {

                    FileInputStream serviceAccount = new FileInputStream("C:\\codes\\aoodp_maven_project(try)beta\\mydemoproject\\src\\main\\resources\\aoop-project-fde23-firebase-adminsdk-pvvl2-cc88314796.json"); // Update with your path
        
                    FirebaseOptions options = new FirebaseOptions.Builder()
                            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                            .setDatabaseUrl("https://aoop-project-fde23-default-rtdb.firebaseio.com/") // Update with your Firebase DB URL
                            .build();
        
        
        
        
                    FirebaseApp.initializeApp(options);
                    databaseReference = FirebaseDatabase.getInstance().getReference();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }

                databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot partSnapshot : snapshot.getChildren()) {
                                String pass = partSnapshot.child("pass").getValue(String.class);
                                String user = partSnapshot.child("user").getValue(String.class);

                                if(user.equals(username) && pass.equals(password)){
                                    System.out.println("pass(credential matched)");
                                    userid=username;
                                    mainui();
                                    JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(panel);
                                    topFrame.dispose();
                                    
                                    
                                }

                                else{
                                    System.out.println("fail(credential not match)");
                                }
                               
        
                              
                            }
                        } else {
                            System.out.println("no user found");
                        }
                    }
        
                    @Override
                    public void onCancelled(DatabaseError error) {
                        System.out.println("failed to connect");
                    }
                });




            }
        });

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////





        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the text from the userText and passwordText fields
                String username = userText.getText();
                String password = new String(passwordText.getPassword());  // getPassword() returns a char array

                // Display the input in the console
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);

                try {

                    FileInputStream serviceAccount = new FileInputStream("C:\\codes\\aoodp_maven_project(try)beta\\mydemoproject\\src\\main\\resources\\aoop-project-fde23-firebase-adminsdk-pvvl2-cc88314796.json"); // Update with your path
        
                    FirebaseOptions options = new FirebaseOptions.Builder()
                            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                            .setDatabaseUrl("https://aoop-project-fde23-default-rtdb.firebaseio.com/") // Update with your Firebase DB URL
                            .build();
        
        
        
        
                    FirebaseApp.initializeApp(options);
                    databaseReference = FirebaseDatabase.getInstance().getReference();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                String uniqueuserKey = databaseReference.child("users").push().getKey();
                if (uniqueuserKey != null) {
                    Map<String, Object> user = new HashMap<>();
                    user.put("user", username);
                    user.put("pass", password);
                   

                    databaseReference.child("users").child(uniqueuserKey).setValue(user, (error, ref) -> {
                        if (error != null) {
                            System.out.println("fail");
                        } else {
                            System.out.println("success signup");
                        }
                    });
            }}
        });

    

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
}
