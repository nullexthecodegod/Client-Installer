package me.nullex.installer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public final class Prompt extends JFrame {

	private static final long serialVersionUID = 4881382640228277156L;
	
	public static boolean profile = true;
	
	public Prompt() {
        this.setSize(400, 200);
        this.setTitle(Installer.client_name + " Installer");
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.init();
    }

    public void init() {
        JPanel v1 = new JPanel();
        v1.setLayout(new BorderLayout(5, 5));
        v1.add((Component)this.getTop(), "North");
        v1.add((Component)this.getMiddle(), "Center");
        v1.add((Component)this.getSetting(), "East");
        v1.add((Component)this.getBottom(), "South");
        this.add(v1);
    }
    
    public JCheckBox getSetting() {
    	JCheckBox v1 = new JCheckBox();
    	v1.setSelected(true);
    	v1.setToolTipText("Creates a Launcher profile in your minecarft launcher");
        v1.setFont(new Font("Dialog", 1, 16));
    	v1.setText("Creat Launcher Profile");
    	v1.addActionListener(e -> {
    		Prompt.profile = !Prompt.profile;
    		System.out.println(String.valueOf(Prompt.profile));
    	});
		return v1;
    }
    
    public JPanel getTop() {
        JPanel v1 = new JPanel();
        v1.setLayout(new FlowLayout());
        v1.setPreferredSize(new Dimension(395, 100));
        v1.add(this.getLabel());
        return v1;
    }

    public JLabel getLabel() {
        JLabel v1 = new JLabel();
        v1.setHorizontalAlignment(0);
        v1.setVerticalAlignment(1);
        v1.setBounds(0, 0, 395, 30);
        v1.setPreferredSize(new Dimension(395, 30));
        v1.setFont(new Font("Dialog", 1, 26));
        v1.setText(Installer.client_name + " Installer");
        return v1;
    }

    public JPanel getMiddle() {
        JPanel v1 = new JPanel();
        v1.setLayout(new FlowLayout());
        v1.setBounds(0, 0, 395, 100);
        v1.setPreferredSize(new Dimension(395, 100));
        v1.add(this.getInstall());
        v1.add(this.getCancel());
        return v1;
    }

    public JButton getInstall() {
        JButton v1 = new JButton();
        v1.setText("Install");
        v1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Installer.install();
                Prompt.this.dispose();
            }
        });
        return v1;
    }

    public JButton getCancel() {
        JButton v1 = new JButton();
        v1.setText("Cancel");
        v1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Prompt.this.dispose();
            }
        });
        return v1;
    }

    public JPanel getBottom() {
        JPanel v1 = new JPanel();
        v1.add(this.getVersion());
        v1.add(this.getCredits());
        return v1;
    }

    public JLabel getVersion() {
        JLabel v1 = new JLabel();
        v1.setHorizontalAlignment(2);
        v1.setVerticalAlignment(1);
        v1.setBounds(2, 0, 388, 12);
        v1.setPreferredSize(new Dimension(388, 12));
        v1.setForeground(new Color(150, 150, 150));
        v1.setFont(new Font("Dialog", 1, 10));
        v1.setText(Installer.client_name + " " + Installer.client_version);
        return v1;
    }

    public JLabel getCredits() {
        JLabel v1 = new JLabel();
        v1.setHorizontalAlignment(4);
        v1.setVerticalAlignment(1);
        v1.setBounds(2, 0, 388, 12);
        v1.setPreferredSize(new Dimension(388, 12));
        v1.setForeground(new Color(150, 150, 150));
        v1.setFont(new Font("Dialog", 1, 10));
        v1.setText("Made by NullEX");
        return v1;
    }

    public static void report(String a1, String a2, int a3) {
        JOptionPane.showMessageDialog(null, a2, a1, a3);
    }

}

