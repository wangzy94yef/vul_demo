/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.vul_prediction;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author zm
 */
public class Start_JFrame extends javax.swing.JFrame {

    /**
     * Creates new form Start_JFrame
     */
    public Start_JFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ML_jButton = new javax.swing.JButton();
        DL_jButton = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ML_jButton.setText("Classique Machine Learning");
        ML_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ML_jButtonActionPerformed(evt);
            }
        });

        DL_jButton.setText("Deep Learning");
        DL_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DL_jButtonActionPerformed(evt);
            }
        });

        jButton3.setText("Exit");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ML_jButton, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                            .addComponent(DL_jButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(ML_jButton)
                .addGap(43, 43, 43)
                .addComponent(DL_jButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ML_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ML_jButtonActionPerformed
        //this.setVisible(false);
        this.dispose();
        new ML_JFrame().setVisible(true);
    }//GEN-LAST:event_ML_jButtonActionPerformed

    private void DL_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DL_jButtonActionPerformed
        //this.setVisible(false);
        this.dispose();
        new DL_JFrame().setVisible(true);
    }//GEN-LAST:event_DL_jButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
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
            java.util.logging.Logger.getLogger(Start_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Start_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Start_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Start_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        
        if (args.length == 0)
        {
            //start the GUI version
            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new Start_JFrame().setVisible(true);
                }
            });
        
        }
        else
        {
            String HiddenLayer = args[0];
            String Dataset_File_Name = args[1];
            String Balanced = args[2];
            int Num_Folds = 3;
            String Options = "-L 0.01 -M 0.01 -N 2500 -V 0 -S 0 -E 20 -batch-size 128 -H " + HiddenLayer;
            
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now;
            String Start_Time = "";
            String End_Time="";
            
                
            boolean Balance_Dataset = false;
                
            dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            now = LocalDateTime.now();  
            Start_Time = dtf.format(now);
            
            System.out.println("\n\nProcessing, please wait...");  
            
            if( "true".equals(Balanced))
            {
                Balance_Dataset = true;
            }
            else if( "false".equals(Balanced))
            {
                Balance_Dataset = false;
            }
            
            MLP My_MLP = new MLP(Options,
                    Dataset_File_Name);
            My_MLP.Build_MLP(Balance_Dataset);
                
            now = LocalDateTime.now();  
            End_Time = dtf.format(now);
                
            
                
            String Exprement_Details = "Dataset : " + Dataset_File_Name + "\n" +
            "Balanced data : " + Balance_Dataset + "\n"+
            "Cross-Validation :"+ Num_Folds +"Folds" + "\n" +
            "NN Options : " + Options + "\n";
            
            
            
            String Results = My_MLP.Evaluate_MLP_CV(Num_Folds, Balance_Dataset);
                
            Exprement_Details = Exprement_Details +"\n"+ Results +
            "\n" + "Start Time :" + Start_Time +"\n" + "End Time :" + End_Time ;
            
            // save model to file
            dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");  
            now = LocalDateTime.now();  
            String Results_Time = dtf.format(now);
                
            
            try (ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream("MODEL_"+Results_Time+".model"))) {
            oos.writeObject(My_MLP.Mlp);
            oos.flush();
                
            }
                              
            // save results to txt file    
            try (FileWriter outFile = new FileWriter("RESULTS_"+Results_Time+".txt",true)) {
                outFile.write(Exprement_Details);
            }                
            
            System.out.println("Processing terminated.");
            System.out.println("*******************************************************************");
            System.out.println("***************             RESULTS           *********************");
            System.out.println("*******************************************************************");
            System.out.println(Exprement_Details);
            
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DL_jButton;
    private javax.swing.JButton ML_jButton;
    private javax.swing.JButton jButton3;
    // End of variables declaration//GEN-END:variables
}
