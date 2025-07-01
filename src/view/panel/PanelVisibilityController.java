package view.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import java.util.HashMap;
import java.util.Map;

public class PanelVisibilityController extends JPanel {
    private static final Font REGULAR_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    
    private JButton dropdownButton;
    private JPopupMenu popup;
    private Map<String, JPanel> panelMap = new HashMap<>();
    private Color backgroundColor = Color.WHITE;

    /**
     * Creates a panel visibility controller
     */
    public PanelVisibilityController() {
        this(Color.WHITE);
    }
    
    /**
     * Creates a panel visibility controller with specified background color
     * @param backgroundColor The background color for the controller
     */
    public PanelVisibilityController(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(backgroundColor);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        // Create the label
        JLabel selectorLabel = new JLabel("Visible Panels: ");
        selectorLabel.setFont(REGULAR_FONT);
        add(selectorLabel);
        
        // Create dropdown button with styling
        dropdownButton = new JButton("Toggle Panels ▼");
        dropdownButton.setFont(REGULAR_FONT);
        dropdownButton.setBackground(Color.WHITE);
        dropdownButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        dropdownButton.setFocusPainted(false);
        dropdownButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(dropdownButton);
        
        // Create popup for showing the checklist
        popup = new JPopupMenu();
        popup.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        // Create a panel that will contain our checkboxes
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
        checkBoxPanel.setBackground(Color.WHITE);
        popup.add(checkBoxPanel);
        
        // Add action to dropdown button
        dropdownButton.addActionListener(e -> {
            popup.show(dropdownButton, 0, dropdownButton.getHeight());
        });
    }
    
    /**
     * Registers a panel to be controlled by this visibility controller
     * @param panelName The name to display in the checkbox
     * @param panel The panel to control
     * @param initiallyVisible Whether the panel should be initially visible
     */
    public void registerPanel(String panelName, JPanel panel, boolean initiallyVisible) {
        if (panel == null) {
            return;
        }
        
        // Get the checkbox panel from the popup
        JPanel checkBoxPanel = (JPanel) popup.getComponent(0);
        
        // Create and add checkbox for the panel
        JCheckBox checkBox = new JCheckBox(panelName, initiallyVisible);
        checkBox.setFont(REGULAR_FONT);
        
        checkBox.addItemListener(e -> {
            panel.setVisible(e.getStateChange() == ItemEvent.SELECTED);
            
            // Find the parent container and call revalidate and repaint
            Container parent = panel.getParent();
            if (parent != null) {
                parent.revalidate();
                parent.repaint();
            }
        });
        
        // Set initial visibility
        panel.setVisible(initiallyVisible);
        
        // Add checkbox to panel
        checkBoxPanel.add(checkBox);
        panelMap.put(panelName, panel);
        
        // Update popup size
        popup.pack();
    }
    
    /**
     * Registers a panel to be controlled by this visibility controller
     * (Initially visible by default)
     * @param panelName The name to display in the checkbox
     * @param panel The panel to control
     */
    public void registerPanel(String panelName, JPanel panel) {
        registerPanel(panelName, panel, true);
    }
    
    /**
     * Sets the button text
     * @param text The text to display on the button
     */
    public void setButtonText(String text) {
        dropdownButton.setText(text);
    }
    
    /**
     * Updates the visibility state of a panel
     * @param panelName The name of the panel to update
     * @param visible Whether the panel should be visible
     */
    public void setPanelVisibility(String panelName, boolean visible) {
        JPanel panel = panelMap.get(panelName);
        if (panel != null) {
            panel.setVisible(visible);
            
            // Update the checkbox state
            JPanel checkBoxPanel = (JPanel) popup.getComponent(0);
            for (Component comp : checkBoxPanel.getComponents()) {
                if (comp instanceof JCheckBox) {
                    JCheckBox checkBox = (JCheckBox) comp;
                    if (checkBox.getText().equals(panelName)) {
                        checkBox.setSelected(visible);
                        break;
                    }
                }
            }
            
            // Find the parent container and call revalidate and repaint
            Container parent = panel.getParent();
            if (parent != null) {
                parent.revalidate();
                parent.repaint();
            }
        }
    }
    
    /**
     * Gets the current visibility state of a panel
     * @param panelName The name of the panel
     * @return true if the panel is visible, false otherwise
     */
    public boolean isPanelVisible(String panelName) {
        JPanel panel = panelMap.get(panelName);
        return panel != null && panel.isVisible();
    }
}