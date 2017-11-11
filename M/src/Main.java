import java.awt.Graphics;
import java.awt.HeadlessException;
import java.io.File;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Main extends javax.swing.JFrame {
	
	public Main() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addFileButton = new javax.swing.JButton();
        myScrollPane = new javax.swing.JScrollPane();
        myTextArea = new javax.swing.JTextArea();
        addFileMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(243, 202, 161));
        setName("myFrame"); // NOI18N

        addFileButton.setBackground(new java.awt.Color(243, 243, 62));
        addFileButton.setText("Add file...");
        addFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFileButtonActionPerformed(evt);
            }
        });

        myTextArea.setBackground(new java.awt.Color(250, 237, 224));
        myTextArea.setColumns(20);
        myTextArea.setFont(new java.awt.Font("Ubuntu Mono", 0, 14)); // NOI18N
        myTextArea.setLineWrap(true);
        myTextArea.setRows(5);
        myTextArea.setTabSize(4);
        myTextArea.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        myScrollPane.setViewportView(myTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(myScrollPane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addFileButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addFileMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 684, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addFileButton)
                    .addComponent(addFileMessage))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(myScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
	public Main(JButton addFileButton, JLabel addFileMessage, JScrollPane myScrollPane, JTextArea myTextArea) throws HeadlessException {
		this.addFileButton = addFileButton;
		this.addFileMessage = addFileMessage;
		this.myScrollPane = myScrollPane;
		this.myTextArea = myTextArea;
	}

    private void addFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFileButtonActionPerformed
		StringParsing sp = new StringParsing();
		String message = new String();
		
		try {
			message = sp.openFile();
			sp.parsing();
		} catch (Exception ie) {
			ie.printStackTrace();
			message = new String("File is not loaded!");
		}
		
		ObjectParsing myObject = new ObjectParsing(sp.getParsedCode());
		
		addFileMessage.setText(message);
		myTextArea.setText(null);
		myTextArea.append(myObject.output());
		myTextArea.setEditable(false);
		myTextArea.setVisible(true);
    }//GEN-LAST:event_addFileButtonActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addFileButton;
    private javax.swing.JLabel addFileMessage;
    private javax.swing.JScrollPane myScrollPane;
    private javax.swing.JTextArea myTextArea;
    // End of variables declaration//GEN-END:variables
}