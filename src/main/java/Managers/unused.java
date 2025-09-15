/*
public class unused {

public void lightTheme(JButton menu, JPanel sidePanel,JLabel dashIcon, JLabel homeworkIcon, JLabel progressIcon,
                           JLabel parentControlIcon, JLabel settingsIcon, JLabel logoutIcon){

        /**try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            SwingUtilities.updateComponentTreeUI(new MainMenu());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }*\

import javax.swing.*;SwingUtilities.invokeLater(new Runnable() {
    @Override
    public void run() {
        sidePanel.setBackground(new java.awt.Color(204, 204, 204));
        dashIcon.setForeground(new java.awt.Color(31, 30, 38));
        homeworkIcon.setForeground(new java.awt.Color(31, 30, 38));
        progressIcon.setForeground(new java.awt.Color(31, 30, 38));
        parentControlIcon.setForeground(new java.awt.Color(31, 30, 38));
        settingsIcon.setForeground(new java.awt.Color(31, 30, 38));
        logoutIcon.setForeground(new java.awt.Color(31, 30, 38));

        menu.setIcon(new ImageIcon(getClass().getResource("/darkIcons/menu.png")));
        dashIcon.setIcon(new ImageIcon(getClass().getResource("/darkIcons/dashboard.png")));
        homeworkIcon.setIcon(new ImageIcon(getClass().getResource("/darkIcons/assignment.png")));
        progressIcon.setIcon(new ImageIcon(getClass().getResource("/darkIcons/progress.png")));
        parentControlIcon.setIcon(new ImageIcon(getClass().getResource("/darkIcons/parental_control.png")));
        settingsIcon.setIcon(new ImageIcon(getClass().getResource("/darkIcons/settings.png")));
        logoutIcon.setIcon(new ImageIcon(getClass().getResource("/darkIcons/logout.png")));
    }
});

        }

public void darkTheme(JButton menu, JPanel sidePanel,JLabel dashIcon, JLabel homeworkIcon, JLabel progressIcon,
                      JLabel parentControlIcon, JLabel settingsIcon, JLabel logoutIcon){

    /**try {
     UIManager.setLookAndFeel(new FlatDarkLaf());
     SwingUtilities.updateComponentTreeUI(new MainMenu());
     } catch (UnsupportedLookAndFeelException ex) {
     Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
     }*\

    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            sidePanel.setBackground(new java.awt.Color(31, 30, 34));
            dashIcon.setForeground(new java.awt.Color(204, 204, 204));
            homeworkIcon.setForeground(new java.awt.Color(204, 204, 204));
            progressIcon.setForeground(new java.awt.Color(204, 204, 204));
            parentControlIcon.setForeground(new java.awt.Color(204, 204, 204));
            settingsIcon.setForeground(new java.awt.Color(204, 204, 204));
            logoutIcon.setForeground(new java.awt.Color(204, 204, 204));

            menu.setIcon(new ImageIcon(getClass().getResource("/lightIcons/menu.png")));
            dashIcon.setIcon(new ImageIcon(getClass().getResource("/lightIcons/dashboard.png")));
            homeworkIcon.setIcon(new ImageIcon(getClass().getResource("/lightIcons/assignment.png")));
            progressIcon.setIcon(new ImageIcon(getClass().getResource("/lightIcons/progress.png")));
            parentControlIcon.setIcon(new ImageIcon(getClass().getResource("/lightIcons/parental_control.png")));
            settingsIcon.setIcon(new ImageIcon(getClass().getResource("/lightIcons/settings.png")));
            logoutIcon.setIcon(new ImageIcon(getClass().getResource("/lightIcons/logout.png")));
        }
    });

}

}
*/

/// I could use tokens to split the data that is going to be input into the JTables

/**
 * public ServerManager()
 *     {
 *        try
 *        {     open the file for reading
 *            Scanner sc = new Scanner(new File("servers.txt"));
 *
 *             loop through all the lines
 *            while(sc.hasNextLine())
 *            {
 *                 read the next line from the file
 *                String line = sc.nextLine();
 *
 *                split the line into the required parts
 *                String tokens[] = line.split("#");
 *
 *                String sid = tokens[0];
 *                String location = tokens[1];
 *                String fault = tokens[2];
 *                String role = tokens[3];
 *
 *                 create server object
 *
 *                Server s = new Server(sid, location, fault , role);
 *                 add server to array
 *                sArr[size] = s;
 *                 increment size
 *                size++;
 *
 *            }
 *            sc.close();
 *        }
 *        catch(FileNotFoundException e)
 *        {
 *
 *            System.out.println("File Missing");  handle exception
 *        }
 *     }
 */
/**
 *     public void RoundedTextField(JTextField textField, int radius) {
 *         textField.setOpaque(false);
 *         textField.setBorder(new RoundedBorder(radius));
 *         textField.setBackground(new Color(255, 255, 255, 230));
 *         textField.setForeground(Color.BLACK);
 *         textField.setCaretColor(Color.BLACK);
 *     }
 *
 *     public void roundedTable(JScrollPane scrollPane, JTable table, int radius){
 *
 *         scrollPane.setBorder(new RoundedBorder(radius));
 *         scrollPane.setOpaque(false);
 *         scrollPane.getViewport().setOpaque(false);
 *
 *         table.setOpaque(false);
 *         table.setBorder(new RoundedBorder(radius));
 *         table.setBackground(new Color(255, 255, 255, 230));
 *         table.setForeground(Color.BLACK);
 *
 *     }
 *
 *     // Custom Border class with rounded corners
 *     class RoundedBorder implements Border {
 *
 *         private int radius;
 *
 *         public RoundedBorder(int radius) {
 *             this.radius = radius;
 *         }
 *
 *         @Override
 *         public Insets getBorderInsets(Component c) {
 *             return new Insets(this.radius + 1, this.radius + 1, this.radius + 1, this.radius + 1);
 *         }
 *
 *         @Override
 *         public boolean isBorderOpaque() {
 *             return false;
 *         }
 *
 *         @Override
 *         public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
 *             Graphics2D g2 = (Graphics2D) g.create();
 *             g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
 *
 *             // Draw rounded rectangle border
 *             g2.setColor(Color.GRAY);
 *             g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
 *             g2.dispose();
 *         }
 *     }
 */