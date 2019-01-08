/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lendle.courses.wp.finalexam_wp;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author lendle
 */
public class TaskFrame extends JInternalFrame {

    private JTextField textTitle = null;
    private JTextArea textContent = null;
    private boolean modified = false;
    JTextArea jTextArea = new JTextArea();

    public TaskFrame() {
        this.setSize(500, 300);
        //Q4: layout 出如圖所示的樣子，
        //記得 JTextArea 要放在捲軸裡面 (30%)
        JTextField jTextField = new JTextField();
        JTextArea jTextArea = new JTextArea();
        BorderLayout borderLayout = new BorderLayout();
        jTextField.setLayout(borderLayout);
        jTextArea.setLayout(borderLayout);
        this.add(jTextArea,"south");
        this.add(jTextArea,"south");
        ////////////////////////////
        this.setClosable(true);
        this.setResizable(true);
        this.setVisible(true);

        JPanel southPanel = new JPanel();
        this.add(southPanel, "South");
        JButton saveButton = new JButton("Save");
        southPanel.add(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TaskDB.save(getNoteTitle(), getNoteContent());
                modified = false;
                setTitle("");
            }
        });

        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                modified = true;
                setTitle("*");
            }

        };
        textContent.addKeyListener(keyListener);
        this.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                if (modified) {
                    //Q5: 發現變更，顯示 confirm dialog 詢問是否要儲存 (20%)
                    int result = JOptionPane.showConfirmDialog(southPanel,"是否要儲存?","test",JOptionPane.YES_NO_CANCEL_OPTION);
                    int ret = -1;
                    /////////////////////////////////////////////
                    if (ret == JOptionPane.YES_OPTION) {
                        TaskDB.save(getNoteTitle(), getNoteContent());
                        modified = false;
                        setTitle("");
                    }
                }
            }

        });
    }

    public String getNoteTitle() {
        return textTitle.getText();
    }

    public void setNoteTitle(String title) {
        this.textTitle.setText(title);
    }

    public String getNoteContent() {
        return textContent.getText();
    }

    public void setNoteContent(String content) {
        this.textContent.setText(content);
    }
}
