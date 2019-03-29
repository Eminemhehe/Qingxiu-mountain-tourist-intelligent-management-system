/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Dao.VisitorDao;
import com.jlrfid.service.AntStruct;
import com.jlrfid.service.GetReadData;
import com.jlrfid.service.MainHandler;
import com.jlrfid.service.RFIDException;
import com.mysql.jdbc.PreparedStatement;
//import static com.sun.xml.internal.ws.api.message.Packet.Status.Request;
import entity.Visitor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import sun.swing.table.DefaultTableCellHeaderRenderer;
import utils.JDBCUtils;

/**
 *
 * @author Administrator
 */
public class SystemGUI extends javax.swing.JFrame implements GetReadData {

    private String address = "";
    private static String readData;
    int strLength = 0;
    int num=0;
    Vector result = new Vector();
    int over =0;

    public void getReadData(String data, int antNo) {
        if ("F0".equals(data)) {
            System.out.println("天线1寻卡结束！");
        } else if ("F1".equals(data)) {
            System.out.println("天线2寻卡结束！");
        } else if ("F2".equals(data)) {
            System.out.println("天线3寻卡结束！");
        } else if ("F3".equals(data)) {
            System.out.println("天线4寻卡结束！");
        } else if (!"".equals(data)) {
            readData += data + ",";
            System.out.println("数据：" + data + "  天线：" + antNo);
            String[] readDataString = readData.split(",");
            strLength = readDataString.length;
            HashSet<String> readDataSet = new HashSet<String>();
            for (int i = 0; i < readDataString.length; i++) {
            readDataSet.add(readDataString[i]);
            }
            System.out.println("长度：" +readDataSet.size() );
                 
		try {
                    Connection conn =null;
                    PreparedStatement ps =null;
			  conn = JDBCUtils.getConnection();
			  String sql = "update visitor_copy set num=?";
			  ps = (PreparedStatement) conn.prepareStatement(sql);
			  ps.setInt(1, readDataSet.size());
			  int r = ps.executeUpdate();
			  if(r>0) {
				  System.out.println("更新成功");
			  }else {
				  System.out.println("更新失败");
			  }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

        
    }

    /**
     * Creates new form SystemGUI
     */
    public SystemGUI() {
        initComponents();
        setTitle("青秀山风景旅游区智能RFID管理系统  （研发单位：广西大学）");
        final int WIDTH1 = 700;
        final int HEIGHT1 = 600;
        // final int WIDTH6 = 400;
        //final int HEIGHT6 = 320;
        final JFXPanel webBrowser1 = new JFXPanel();
        final JFXPanel webBrowser6 = new JFXPanel();
        jPanel2.setLayout(new BorderLayout());
        jPanel2.add(webBrowser1);
        //jPanel6.setLayout(new BorderLayout());
        //jPanel6.add(webBrowser6);
        final EventHandler<ActionEvent> handler = null;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                final Group root1 = new Group();
               // final Group root6 = new Group();
                Scene scene1   = new Scene(root1, WIDTH1, HEIGHT1);
                //Scene scene6   = new Scene(root6, WIDTH6, HEIGHT6);
                webBrowser1.setScene(scene1);
               //webBrowser6.setScene(scene6);
                Double widthDouble1 = new Integer(WIDTH1).doubleValue();
                Double heightDouble1 = new Integer(HEIGHT1).doubleValue();
                final WebView view1 = new WebView();
               // Double widthDouble6 = new Integer(WIDTH6).doubleValue();
                //Double heightDouble6 = new Integer(HEIGHT6).doubleValue();
                final WebView view6 = new WebView();

                view1.setMinSize(widthDouble1, heightDouble1);
                view1.setPrefSize(widthDouble1, heightDouble1);
                final WebEngine eng1 = view1.getEngine();
                eng1.load(SystemGUI.class.getResource("design_map.html").toString());
                root1.getChildren().add(view1);
                System.out.println(eng1.isJavaScriptEnabled());
                
//                view6.setMinSize(widthDouble6, heightDouble6);
//                view6.setPrefSize(widthDouble6, heightDouble6);
//                final WebEngine eng6 = view6.getEngine();
//                eng6.load(SystemGUI.class.getResource("design_map.html").toString());
//                root6.getChildren().add(view6);
//                System.out.println(eng6.isJavaScriptEnabled());
                //eng.executeScript("getName()");

                eng1.setOnAlert(new EventHandler<WebEvent<String>>() {
                    @Override
                    public void handle(WebEvent<String> event) {
                        address = "南宁市青秀山风景区";
                        System.out.println("this is event" + event);
                        System.out.println(address + "dff");
                        eng1.executeScript("setlocation('" + address + "')");

                    }
                });

                // 加载新的地址
                final EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("...");
                        System.out.println(eng1.executeScript("getName()"));
                        eng1.executeScript("getName('南宁市青秀山风景区')");

                    }
                };

            }

        });
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        jPanel2.setSize(WIDTH, HEIGHT);
        jPanel2.setLocation((screenWidth - WIDTH) / 2, (screenHeight - HEIGHT) / 2);
        jPanel2.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel(){ };
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jComboBox1_ip1 = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        jTextField1_port1 = new javax.swing.JTextField();
        jButton4_connect2 = new javax.swing.JButton();
        jButton4_break1 = new javax.swing.JButton();
        jButton4_reset1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField4_replayConnect1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1_ip = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jTextField1_port = new javax.swing.JTextField();
        jButton4_connect = new javax.swing.JButton();
        jButton4_break = new javax.swing.JButton();
        jButton4_reset = new javax.swing.JButton();
        jTextField4_replayConnect = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1_ant1Time = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox2_ant1Power = new javax.swing.JComboBox<>();
        jCheckBox1_selectAnt1 = new javax.swing.JCheckBox();
        jCheckBox1_selectAnt2 = new javax.swing.JCheckBox();
        jCheckBox2_selectAnt3 = new javax.swing.JCheckBox();
        jCheckBox3_selectAnt4 = new javax.swing.JCheckBox();
        jComboBox1_ant2Time = new javax.swing.JComboBox<>();
        jComboBox2_ant2Power = new javax.swing.JComboBox<>();
        jComboBox3_ant3Time = new javax.swing.JComboBox<>();
        jComboBox4_ant3Power = new javax.swing.JComboBox<>();
        jComboBox5_ant4Time = new javax.swing.JComboBox<>();
        jComboBox6_ant4Power = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jComboBox1_operateArea = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jTextField1_dataReplay = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField1_passwords = new javax.swing.JTextField();
        jButton6_readData = new javax.swing.JButton();
        jButton6_clearData = new javax.swing.JButton();
        jButton6_writeData = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jTextField1_passwordLock = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextField1_passwordVisitor = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextField2_passwordCancel = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        jCheckBox1_input1 = new javax.swing.JCheckBox();
        jCheckBox2_input2 = new javax.swing.JCheckBox();
        jButton8 = new javax.swing.JButton();
        jCheckBox1_output1 = new javax.swing.JCheckBox();
        jCheckBox2_output2 = new javax.swing.JCheckBox();
        jButton9 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jTextField1_deviceNum = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton11 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jTextField1_nearDecide = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton4_connect1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setBounds(new java.awt.Rectangle(280, 120, 1360, 700));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("宋体", 0, 10)); // NOI18N
        setIconImage(new ImageIcon("../xh.png").getImage());
        setMaximumSize(new java.awt.Dimension(1360, 700));
        setMinimumSize(new java.awt.Dimension(1360, 700));
        setPreferredSize(new java.awt.Dimension(1360, 700));
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));

        jPanel1.setMaximumSize(new java.awt.Dimension(643, 1096));
        jPanel1.setMinimumSize(new java.awt.Dimension(643, 1096));

        jButton1.setText("人员定位");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jButton2.setText("更多信息");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("游客信息");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setEditingColumn(0);
        jTable1.setEditingRow(0);
        jTable1.setRequestFocusEnabled(false);
        jTable1.setShowHorizontalLines(false);
        jTable1.setShowVerticalLines(false);
        jTable1.setSurrendersFocusOnKeystroke(true);
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel31.setText("连接设置");

        jLabel32.setText("IP地址：");

        jComboBox1_ip1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10.10.100.254" }));
        jComboBox1_ip1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1_ip1ActionPerformed(evt);
            }
        });

        jLabel33.setText("端口号：");

        jTextField1_port1.setText("8899");

        jButton4_connect2.setText("连接");
        jButton4_connect2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4_connect2ActionPerformed(evt);
            }
        });

        jButton4_break1.setText("断开");
        jButton4_break1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4_break1ActionPerformed(evt);
            }
        });

        jButton4_reset1.setText("复位");
        jButton4_reset1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4_reset1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "青秀山云天阁", "青秀山步云门", "青秀山兰园", "青秀山龙象塔" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextField4_replayConnect1.setBackground(new java.awt.Color(214, 217, 223));
        jTextField4_replayConnect1.setBorder(null);
        jTextField4_replayConnect1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4_replayConnect1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("宋体", 1, 15)); // NOI18N
        jLabel1.setText(" 青秀山景点名称：");

        jTextField1.setBackground(new java.awt.Color(214, 217, 223));
        jTextField1.setFont(new java.awt.Font("宋体", 1, 16)); // NOI18N
        jTextField1.setBorder(null);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("宋体", 1, 16)); // NOI18N
        jLabel34.setText("人数共计：");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel33)
                                    .addComponent(jLabel32))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox1_ip1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1_port1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jTextField4_replayConnect1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jButton4_connect2)
                                .addGap(14, 14, 14)
                                .addComponent(jButton4_break1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton4_reset1)))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(30, 30, 30)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel34)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)))
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel31)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1_ip1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(jTextField1_port1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4_reset1)
                            .addComponent(jButton4_connect2)
                            .addComponent(jButton3)
                            .addComponent(jButton4_break1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jTextField4_replayConnect1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(230, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(514, Short.MAX_VALUE))
        );

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("I/O操作");
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(754, 18, -1, -1));

        jLabel3.setText("通讯模块");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 18, -1, -1));

        jComboBox1_ip.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10.10.100.254" }));
        jPanel5.add(jComboBox1_ip, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 44, -1, -1));

        jLabel4.setText("端口号：");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 80, -1, -1));

        jTextField1_port.setText("20058");
        jPanel5.add(jTextField1_port, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 77, 52, -1));

        jButton4_connect.setText("连接");
        jButton4_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4_connectActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4_connect, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 109, -1, -1));

        jButton4_break.setText("断开");
        jButton4_break.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4_breakActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4_break, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 109, -1, -1));

        jButton4_reset.setText("复位");
        jButton4_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4_resetActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4_reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(161, 109, -1, -1));

        jTextField4_replayConnect.setBorder(null);
        jTextField4_replayConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4_replayConnectActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField4_replayConnect, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 478, 217, -1));

        jLabel5.setText("天线参数");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 172, -1, -1));

        jComboBox1_ant1Time.setEditable(true);
        jComboBox1_ant1Time.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "100", "200", "300", "400", "500", "600", "700", "800", "900", "1000", "1100", "1200", "1300", "1400", "1500", "1600", "1700", "1800", "1900", "2000", "2100", "2200", "2300", "2400", "2500", "2600", "2700", "2800", "2900", "3000", "3100", "3200", "3300", "3400", "3500", "3600", "3700", "3800", "3900", "4000", "4100", "4200", "4300", "4400", "4500", "4600", "4700", "4800", "4900", "5000" }));
        jComboBox1_ant1Time.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1_ant1TimeActionPerformed(evt);
            }
        });
        jPanel5.add(jComboBox1_ant1Time, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 221, 72, -1));

        jLabel6.setText("工作时间(ms)");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 196, -1, -1));

        jLabel7.setText("功率(dbm)");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(181, 196, -1, -1));

        jComboBox2_ant1Power.setEditable(true);
        jComboBox2_ant1Power.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "30", "29", "28", "27", "26", "25", "24", "23", "22", "21", "20" }));
        jPanel5.add(jComboBox2_ant1Power, new org.netbeans.lib.awtextra.AbsoluteConstraints(181, 221, 45, -1));

        jCheckBox1_selectAnt1.setText("1号");
        jPanel5.add(jCheckBox1_selectAnt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 220, -1, -1));

        jCheckBox1_selectAnt2.setText("2号");
        jPanel5.add(jCheckBox1_selectAnt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 261, -1, -1));

        jCheckBox2_selectAnt3.setText("3号");
        jPanel5.add(jCheckBox2_selectAnt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 302, -1, -1));

        jCheckBox3_selectAnt4.setText("4号");
        jPanel5.add(jCheckBox3_selectAnt4, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 344, -1, -1));

        jComboBox1_ant2Time.setEditable(true);
        jComboBox1_ant2Time.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "100", "200", "300", "400", "500", "600", "700", "800", "900", "1000", "1100", "1200", "1300", "1400", "1500", "1600", "1700", "1800", "1900", "2000", "2100", "2200", "2300", "2400", "2500", "2600", "2700", "2800", "2900", "3000", "3100", "3200", "3300", "3400", "3500", "3600", "3700", "3800", "3900", "4000", "4100", "4200", "4300", "4400", "4500", "4600", "4700", "4800", "4900", "5000" }));
        jPanel5.add(jComboBox1_ant2Time, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 261, 73, -1));

        jComboBox2_ant2Power.setEditable(true);
        jComboBox2_ant2Power.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "30", "29", "28", "27", "26", "25", "24", "23", "22", "21", "20" }));
        jPanel5.add(jComboBox2_ant2Power, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 261, 45, -1));

        jComboBox3_ant3Time.setEditable(true);
        jComboBox3_ant3Time.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "100", "200", "300", "400", "500", "600", "700", "800", "900", "1000", "1100", "1200", "1300", "1400", "1500", "1600", "1700", "1800", "1900", "2000", "2100", "2200", "2300", "2400", "2500", "2600", "2700", "2800", "2900", "3000", "3100", "3200", "3300", "3400", "3500", "3600", "3700", "3800", "3900", "4000", "4100", "4200", "4300", "4400", "4500", "4600", "4700", "4800", "4900", "5000" }));
        jPanel5.add(jComboBox3_ant3Time, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 302, 73, -1));

        jComboBox4_ant3Power.setEditable(true);
        jComboBox4_ant3Power.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "30", "29", "28", "27", "26", "25", "24", "23", "22", "21", "20" }));
        jPanel5.add(jComboBox4_ant3Power, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 302, 45, -1));

        jComboBox5_ant4Time.setEditable(true);
        jComboBox5_ant4Time.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "100", "200", "300", "400", "500", "600", "700", "800", "900", "1000", "1100", "1200", "1300", "1400", "1500", "1600", "1700", "1800", "1900", "2000", "2100", "2200", "2300", "2400", "2500", "2600", "2700", "2800", "2900", "3000", "3100", "3200", "3300", "3400", "3500", "3600", "3700", "3800", "3900", "4000", "4100", "4200", "4300", "4400", "4500", "4600", "4700", "4800", "4900", "5000" }));
        jPanel5.add(jComboBox5_ant4Time, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 346, 73, -1));

        jComboBox6_ant4Power.setEditable(true);
        jComboBox6_ant4Power.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "30", "29", "28", "27", "26", "25", "24", "23", "22", "21", "20" }));
        jPanel5.add(jComboBox6_ant4Power, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 346, 45, -1));

        jButton4.setText("读取");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 389, 62, -1));

        jButton5.setText("设置");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 389, 65, -1));

        jLabel8.setText("IP地址：");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 47, -1, -1));

        jLabel9.setText("标签读写");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 18, -1, -1));

        jLabel10.setText("操作区域");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(311, 47, -1, -1));

        jComboBox1_operateArea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Reserve", "EPC", "TID", "User" }));
        jComboBox1_operateArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1_operateAreaActionPerformed(evt);
            }
        });
        jPanel5.add(jComboBox1_operateArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 44, 80, -1));

        jLabel11.setText("起始位置");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(474, 47, -1, -1));

        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jPanel5.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(526, 44, 54, -1));

        jLabel12.setText("长度");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(598, 47, -1, -1));

        jPanel5.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(626, 44, 50, -1));

        jLabel13.setText("数据");
        jPanel5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(311, 80, -1, -1));
        jPanel5.add(jTextField1_dataReplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 77, 319, -1));

        jLabel14.setText("访问密码");
        jPanel5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(311, 113, -1, -1));
        jPanel5.add(jTextField1_passwords, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 110, 84, -1));

        jButton6_readData.setText("读取");
        jButton6_readData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6_readDataActionPerformed(evt);
            }
        });
        jPanel5.add(jButton6_readData, new org.netbeans.lib.awtextra.AbsoluteConstraints(463, 109, 68, -1));

        jButton6_clearData.setText("清空数据");
        jButton6_clearData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6_clearDataActionPerformed(evt);
            }
        });
        jPanel5.add(jButton6_clearData, new org.netbeans.lib.awtextra.AbsoluteConstraints(537, 109, -1, -1));

        jButton6_writeData.setText("写入");
        jButton6_writeData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6_writeDataActionPerformed(evt);
            }
        });
        jPanel5.add(jButton6_writeData, new org.netbeans.lib.awtextra.AbsoluteConstraints(628, 109, 60, -1));

        jLabel15.setText("锁卡");
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, -1, -1));

        jLabel16.setText("操作区域");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(312, 196, -1, -1));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Reserve", "EPC", "TID", "User" }));
        jPanel5.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 193, 80, -1));

        jLabel17.setText("类型");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(487, 196, -1, -1));

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Unlock", "Permanece writable", "Security lock", "Permanece unwritable" }));
        jPanel5.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(521, 193, 93, -1));

        jLabel18.setText("访问密码");
        jPanel5.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(312, 224, -1, -1));
        jPanel5.add(jTextField1_passwordLock, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 221, 80, -1));

        jButton6.setText("锁卡");
        jPanel5.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 220, 68, -1));

        jLabel19.setText("注销卡片");
        jPanel5.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 270, 59, -1));

        jLabel20.setText("访问密码");
        jPanel5.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(312, 289, -1, -1));
        jPanel5.add(jTextField1_passwordVisitor, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 286, 79, -1));

        jLabel21.setText("注销密码");
        jPanel5.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 289, -1, -1));
        jPanel5.add(jTextField2_passwordCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(534, 286, 80, -1));

        jLabel22.setText("寻卡区域");
        jPanel5.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 345, 56, 20));

        jLabel23.setText("读卡区域");
        jPanel5.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(312, 365, -1, -1));

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EPC", "TID", "User" }));
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });
        jPanel5.add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 362, 79, -1));

        jLabel24.setText("起始位置");
        jPanel5.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 365, -1, -1));

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0" }));
        jComboBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox7ActionPerformed(evt);
            }
        });
        jPanel5.add(jComboBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(534, 362, 80, -1));

        jLabel25.setText("数据长度");
        jPanel5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(312, 393, -1, -1));

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1" }));
        jPanel5.add(jComboBox8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 390, 79, -1));

        jButton7.setText("设置");
        jPanel5.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(467, 389, 61, -1));

        jCheckBox1_input1.setText("输入1");
        jPanel5.add(jCheckBox1_input1, new org.netbeans.lib.awtextra.AbsoluteConstraints(792, 43, -1, -1));

        jCheckBox2_input2.setText("输入2");
        jPanel5.add(jCheckBox2_input2, new org.netbeans.lib.awtextra.AbsoluteConstraints(865, 43, -1, -1));

        jButton8.setText("获取");
        jPanel5.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(938, 43, 61, -1));

        jCheckBox1_output1.setText("输出1");
        jPanel5.add(jCheckBox1_output1, new org.netbeans.lib.awtextra.AbsoluteConstraints(792, 76, -1, -1));

        jCheckBox2_output2.setText("输出2");
        jPanel5.add(jCheckBox2_output2, new org.netbeans.lib.awtextra.AbsoluteConstraints(865, 76, -1, -1));

        jButton9.setText("设置");
        jPanel5.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(938, 76, 61, -1));

        jLabel26.setText("设备号");
        jPanel5.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 130, -1, -1));

        jTextField1_deviceNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1_deviceNumActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField1_deviceNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(797, 161, 50, -1));

        jLabel27.setText("(小于254的整数)");
        jPanel5.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(851, 164, -1, -1));

        jButton10.setText("设置");
        jPanel5.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(938, 160, 61, -1));

        jLabel28.setText("AB模式");
        jPanel5.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 230, -1, -1));

        jCheckBox1.setText("AB寻卡模式");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel5.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(797, 252, -1, -1));

        jButton11.setText("设置");
        jPanel5.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(936, 252, 63, -1));

        jLabel29.setText("相邻判断");
        jPanel5.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 310, -1, -1));
        jPanel5.add(jTextField1_nearDecide, new org.netbeans.lib.awtextra.AbsoluteConstraints(797, 343, 51, -1));

        jLabel30.setText("(秒,0为取消)");
        jPanel5.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(852, 346, -1, -1));

        jButton12.setText("设置");
        jPanel5.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(942, 342, -1, -1));

        jButton13.setText("注销");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 310, 70, -1));

        jButton4_connect1.setText("连接");
        jButton4_connect1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4_connect1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4_connect1, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 109, -1, -1));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenuBar1.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 48)); // NOI18N
        jMenuBar1.setMaximumSize(new java.awt.Dimension(300, 32769));

        jMenu1.setText("基本信息");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("阅读器设置");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        jPanel2.validate();
        jPanel1.validate();
        jPanel1.setVisible(true);
        jPanel2.setVisible(true);
        jPanel3.setVisible(true);
        jPanel5.setVisible(false);
//       WebView view = new WebView();
//       view.loadUrl("file:///android_asset/demo.html");  

// TODO add your handling code here:
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jButton4_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4_connectActionPerformed
        String ip = jComboBox1_ip.getSelectedItem().toString();
        int port = Integer.parseInt(jTextField1_port.getText());

        MainHandler handler = new MainHandler();
        if (handler.dllInit("R2k.dll")) {
            if (handler.deviceInit(ip, 0, port)) {
                AntStruct struct = handler.GetAnt();
                for (int i = 0; i < 4; i++) {
                    System.out.println("天线" + (i + 1) + (struct.antEnable[i] == 1 ? "——已连接" : "——未连接") + "——工作时间:" + struct.dwellTime[i] + "ms——功率:" + struct.power[i].longValue() / 10 + "dBm");
                }
                jTextField4_replayConnect.setText("天线已连接！");
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_jButton4_connectActionPerformed

    private void jButton4_breakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4_breakActionPerformed
        String ip = jComboBox1_ip.getSelectedItem().toString();
        int port = Integer.parseInt(jTextField1_port.getText());
        MainHandler handler = new MainHandler();
        if (handler.dllInit("R2k.dll")) {
            if (handler.deviceInit(ip, 0, port)) {
                handler.deviceDisconnect();
            }
            jTextField4_replayConnect.setText("");
        }//// TODO add your handling code here:
    }//GEN-LAST:event_jButton4_breakActionPerformed

    private void jButton4_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4_resetActionPerformed
        String ip = jComboBox1_ip.getSelectedItem().toString();
        int port = Integer.parseInt(jTextField1_port.getText());
        MainHandler handler = new MainHandler();
        if (handler.dllInit("R2k.dll")) {
            if (handler.deviceInit(ip, 0, port)) {
                handler.deviceUnInit();
            }
            jTextField4_replayConnect.setText("");
        }// TODO add your handling code here:
    }//GEN-LAST:event_jButton4_resetActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        MainHandler handler = new MainHandler();
        if (handler.dllInit("R2k.dll")) {
            if (jTextField4_replayConnect.getText().equals("天线已连接！") || jTextField4_replayConnect.getText().equals("读取天线成功！")) {
                byte[] antEnable = new byte[]{jCheckBox1_selectAnt1.isSelected() ? (byte) 1 : 0, jCheckBox1_selectAnt2.isSelected() ? (byte) 1 : 0,
                    jCheckBox2_selectAnt3.isSelected() ? (byte) 1 : 0, jCheckBox3_selectAnt4.isSelected() ? (byte) 1 : 0};
                long[] dwellTime = new long[]{Integer.parseInt(String.valueOf(jComboBox1_ant1Time.getSelectedItem())),
                    Integer.parseInt(String.valueOf(jComboBox1_ant2Time.getSelectedItem())),
                    Integer.parseInt(String.valueOf(jComboBox3_ant3Time.getSelectedItem())),
                    Integer.parseInt(String.valueOf(jComboBox5_ant4Time.getSelectedItem()))};
                long[] power = new long[]{Integer.parseInt(String.valueOf(jComboBox2_ant1Power.getSelectedItem())) * 10,
                    Integer.parseInt(String.valueOf(jComboBox2_ant2Power.getSelectedItem())) * 10,
                    Integer.parseInt(String.valueOf(jComboBox4_ant3Power.getSelectedItem())) * 10,
                    Integer.parseInt(String.valueOf(jComboBox6_ant4Power.getSelectedItem())) * 10};
                try {
                    if (handler.SetAnt(antEnable, dwellTime, power)) {
                        System.out.println("设置天线参数成功！");
                        AntStruct struct = handler.GetAnt();
                        for (int i = 0; i < 4; i++) {
                            System.out.println("天线" + (i + 1) + (struct.antEnable[i] == 1 ? "——已连接" : "——未连接")
                                    + "——工作时间:" + struct.dwellTime[i] + "ms——功率:" + struct.power[i].longValue() / 10 + "dBm");
                        }
                        jTextField4_replayConnect.setText("设置天线参数成功！");
                    } else {
                        System.out.println("设置天线参数失败！");
                    }
                } catch (RFIDException ex) {
                    Logger.getLogger(SystemGUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        MainHandler handler = new MainHandler();
        if (jTextField4_replayConnect.getText().equals("天线已连接！") || jTextField4_replayConnect.getText().equals("设置天线参数成功！")) {
            if (handler.dllInit("R2k.dll")) {
                if (handler.deviceInit("10.10.100.254", 0, 20058)) {
                    AntStruct struct = handler.GetAnt();
                    for (int i = 0; i < 4; i++) {
                        System.out.println("天线" + (i + 1) + (struct.antEnable[i] == 1 ? "——已连接" : "——未连接")
                                + "——工作时间:" + struct.dwellTime[i] + "ms——功率:" + struct.power[i].longValue() / 10 + "dBm");
                        jCheckBox1_selectAnt1.setSelected(true);
                        jComboBox1_ant1Time.setSelectedItem(struct.dwellTime[0]);
                        jComboBox2_ant1Power.setSelectedItem(struct.power[0].longValue() / 10);
                    }
                    jTextField4_replayConnect.setText("读取天线成功！");
                }
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField1_deviceNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1_deviceNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1_deviceNumActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jComboBox1_operateAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1_operateAreaActionPerformed
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("Reserve")) {
            String[] arr = {"0", "1", "2", "3"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox2.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("EPC")) {
            String[] arr = {"2", "3", "4", "5", "6", "7"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox2.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("TID")) {
            String[] arr = {"0", "1", "2", "3", "4", "5"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox2.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User")) {
            String[] arr = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox2.setModel(cbm);
        }
// TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1_operateAreaActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("Reserve") && jComboBox2.getSelectedItem().toString().equals("0")) {
            String[] arr = {"1", "2", "3", "4"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("Reserve") && jComboBox2.getSelectedItem().toString().equals("1")) {
            String[] arr = {"1", "2", "3"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("Reserve") && jComboBox2.getSelectedItem().toString().equals("2")) {
            String[] arr = {"1", "2"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("Reserve") && jComboBox2.getSelectedItem().toString().equals("3")) {
            String[] arr = {"1"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }

        if (jComboBox1_operateArea.getSelectedItem().toString().equals("EPC") && jComboBox2.getSelectedItem().toString().equals("2")) {
            String[] arr = {"1", "2", "3", "4", "5", "6"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("EPC") && jComboBox2.getSelectedItem().toString().equals("3")) {
            String[] arr = {"1", "2", "3", "4", "5"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("EPC") && jComboBox2.getSelectedItem().toString().equals("4")) {
            String[] arr = {"1", "2", "3", "4"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("EPC") && jComboBox2.getSelectedItem().toString().equals("5")) {
            String[] arr = {"1", "2", "3"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("EPC") && jComboBox2.getSelectedItem().toString().equals("6")) {
            String[] arr = {"1", "2"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("EPC") && jComboBox2.getSelectedItem().toString().equals("7")) {
            String[] arr = {"1"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }

        if (jComboBox1_operateArea.getSelectedItem().toString().equals("TID") && jComboBox2.getSelectedItem().toString().equals("0")) {
            String[] arr = {"1", "2", "3", "4", "5", "6"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("TID") && jComboBox2.getSelectedItem().toString().equals("1")) {
            String[] arr = {"1", "2", "3", "4", "5"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("TID") && jComboBox2.getSelectedItem().toString().equals("2")) {
            String[] arr = {"1", "2", "3", "4"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("TID") && jComboBox2.getSelectedItem().toString().equals("3")) {
            String[] arr = {"1", "2", "3"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("TID") && jComboBox2.getSelectedItem().toString().equals("4")) {
            String[] arr = {"1", "2"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("TID") && jComboBox2.getSelectedItem().toString().equals("5")) {
            String[] arr = {"1"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }

        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("0")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("1")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("2")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("3")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("4")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("5")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("6")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("7")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("8")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("9")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("10")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("11")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("11")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("12")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("13")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("14")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("15")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("16")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("17")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("18")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("19")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("20")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("21")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("22")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("23")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("24")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("25")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("26")) {
            String[] arr = {"1", "2", "3", "4", "5", "6"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("27")) {
            String[] arr = {"1", "2", "3", "4", "5"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("28")) {
            String[] arr = {"1", "2", "3", "4"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("29")) {
            String[] arr = {"1", "2", "3"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("30")) {
            String[] arr = {"1", "2"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User") && jComboBox2.getSelectedItem().toString().equals("31")) {
            String[] arr = {"1"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox3.setModel(cbm);
        }

/// TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton6_readDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6_readDataActionPerformed
        int bank = 1;
        int begin;
        int size;
        String password;
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("Reserve")) {
            bank = 0;
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("EPC")) {
            bank = 1;
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("TID")) {
            bank = 2;
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User")) {
            bank = 3;
        }
        begin = Integer.parseInt(jComboBox2.getSelectedItem().toString());
        size = Integer.parseInt(jComboBox3.getSelectedItem().toString());
        password = jTextField1_passwords.getText();
        MainHandler handler = new MainHandler();
        if (handler.dllInit("R2k.dll")) {
            if (handler.deviceInit("10.10.100.254", 0, 20058)) {
                try {
                    jTextField1_dataReplay.setText(handler.ReadTagData(bank, begin, size, password));
// TODO add your handling code here:
                } catch (RFIDException ex) {
                    Logger.getLogger(SystemGUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    }//GEN-LAST:event_jButton6_readDataActionPerformed

    private void jButton6_clearDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6_clearDataActionPerformed
        jTextField1_dataReplay.setText("");// TODO add your handling code here:
    }//GEN-LAST:event_jButton6_clearDataActionPerformed

    private void jButton6_writeDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6_writeDataActionPerformed
        char bank = 1;
        char begin;
        char size;
        String data = "";
        String password;
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("Reserve")) {
            bank = 0;
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("EPC")) {
            bank = 1;
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("TID")) {
            bank = 2;
        }
        if (jComboBox1_operateArea.getSelectedItem().toString().equals("User")) {
            bank = 3;
        }
        begin = (char) Integer.parseInt(jComboBox2.getSelectedItem().toString());
        size = (char) Integer.parseInt(jComboBox3.getSelectedItem().toString());
        data = jTextField1_dataReplay.getText();
        password = jTextField1_passwords.getText();
        MainHandler handler = new MainHandler();
        if (handler.dllInit("R2k.dll")) {
            if (handler.deviceInit("10.10.100.254", 0, 20058)) {
                try {
                    jTextField4_replayConnect.setText("写入数据" + handler.WriteTagData(bank, begin, size, data, password));
// TODO add your handling code here:
                } catch (RFIDException ex) {
                    Logger.getLogger(SystemGUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } // TODO add your handling code here:
    }//GEN-LAST:event_jButton6_writeDataActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        String pKill_pwd = jTextField2_passwordCancel.getText();
        String access_pwd = jTextField1_passwordVisitor.getText();
        MainHandler handler = new MainHandler();
        if (handler.dllInit("R2k.dll")) {
            if (handler.deviceInit("10.10.100.254", 0, 20058)) {
                try {
                    jTextField4_replayConnect.setText("写入数据" + handler.KillTag(pKill_pwd, access_pwd));
                } catch (RFIDException ex) {
                    Logger.getLogger(SystemGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed
        if (jComboBox6.getSelectedItem().toString().equals("EPC")) {
            String[] arr = {"2", "3", "4", "5", "6", "7"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox7.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("TID")) {
            String[] arr = {"0", "1", "2", "3", "4", "5"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox7.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User")) {
            String[] arr = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox7.setModel(cbm);
        } // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox6ActionPerformed

    private void jComboBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox7ActionPerformed
        if (jComboBox6.getSelectedItem().toString().equals("EPC") && jComboBox7.getSelectedItem().toString().equals("2")) {
            String[] arr = {"1", "2", "3", "4", "5", "6"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("EPC") && jComboBox7.getSelectedItem().toString().equals("3")) {
            String[] arr = {"1", "2", "3", "4", "5"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("EPC") && jComboBox7.getSelectedItem().toString().equals("4")) {
            String[] arr = {"1", "2", "3", "4"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("EPC") && jComboBox7.getSelectedItem().toString().equals("5")) {
            String[] arr = {"1", "2", "3"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("EPC") && jComboBox7.getSelectedItem().toString().equals("6")) {
            String[] arr = {"1", "2"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("EPC") && jComboBox7.getSelectedItem().toString().equals("7")) {
            String[] arr = {"1"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }

        if (jComboBox6.getSelectedItem().toString().equals("TID") && jComboBox7.getSelectedItem().toString().equals("0")) {
            String[] arr = {"1", "2", "3", "4", "5", "6"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("TID") && jComboBox7.getSelectedItem().toString().equals("1")) {
            String[] arr = {"1", "2", "3", "4", "5"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("TID") && jComboBox7.getSelectedItem().toString().equals("2")) {
            String[] arr = {"1", "2", "3", "4"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("TID") && jComboBox7.getSelectedItem().toString().equals("3")) {
            String[] arr = {"1", "2", "3"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("TID") && jComboBox7.getSelectedItem().toString().equals("4")) {
            String[] arr = {"1", "2"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("TID") && jComboBox7.getSelectedItem().toString().equals("5")) {
            String[] arr = {"1"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }

        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("0")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("1")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("2")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("3")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("4")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("5")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("6")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("7")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("8")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("9")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("10")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("11")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("11")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("12")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("13")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("14")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("15")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("16")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("17")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("18")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("19")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("20")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("21")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("22")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("23")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("24")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("25")) {
            String[] arr = {"1", "2", "3", "4", "5", "6", "7"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("26")) {
            String[] arr = {"1", "2", "3", "4", "5", "6"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("27")) {
            String[] arr = {"1", "2", "3", "4", "5"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("28")) {
            String[] arr = {"1", "2", "3", "4"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("29")) {
            String[] arr = {"1", "2", "3"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("30")) {
            String[] arr = {"1", "2"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }
        if (jComboBox6.getSelectedItem().toString().equals("User") && jComboBox7.getSelectedItem().toString().equals("31")) {
            String[] arr = {"1"};
            final ComboBoxModel cbm = new DefaultComboBoxModel(arr);
            jComboBox8.setModel(cbm);
        }

// TODO add your handling code here:
    }//GEN-LAST:event_jComboBox7ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        
        
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        model.setRowCount(0);
        result = new Vector();
        readData = "";
        

        MainHandler handler = new MainHandler();
        if (handler.dllInit("R2k.dll")) {
            if (handler.deviceInit("10.10.100.254", 0, 8899)) {

                String s=(String)jComboBox1.getSelectedItem(); 
                byte[] antEnable = new byte[]{1,0,0,0};
               //byte[] antEnable = new byte[]{1,1,1,0};
                long[] dwellTime = new long[]{1000,1000,1000,1000};
                long[] power = new long[]{330, 330, 330, 330};
                try {
                    if (handler.SetAnt(antEnable, dwellTime, power)) {
                        System.out.println("设置天线参数成功！");
                        AntStruct struct = handler.GetAnt();
                        for (int i = 0; i < 4; i++) {
                            System.out.println("天线" + (i + 1) + (struct.antEnable[i] == 1 ? "——已连接" : "——未连接")
                                + "——工作时间:" + struct.dwellTime[i] + "ms——功率:" + struct.power[i].longValue() / 10 + "dBm");
                        }
                    } else {

                    }
                } catch (RFIDException ex) {
                    Logger.getLogger(SystemGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                try {
                    //                    System.out.println(handler.StopInv());
                    handler.BeginInv(this);  //回调函数
                    //handler.InvOnce(this);
                    try {
                        Thread.sleep(3000);
                        handler.StopInv();
                        
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SystemGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } catch (RFIDException ex) {
                    Logger.getLogger(SystemGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        String[] readDataString = readData.split(",");
        strLength = readDataString.length;
        

        HashSet<String> readDataSet = new HashSet<String>();
        for (int i = 0; i < readDataString.length; i++) {
            readDataSet.add(readDataString[i]);
        }
        Vector colNames = new Vector();
        colNames.add("姓名");
        colNames.add("身份证号");
        colNames.add("手机号");
        colNames.add("储值卡余额");
        colNames.add("门票类型");

        String[] str = new String[readDataSet.size()];
        int m = 0;
        Iterator<String> iter = readDataSet.iterator();
        //        for(int i=0;i<readDataSet.size();i++){
            //             str[i]=readDataSet.
            //        }
        while (iter.hasNext()) {
            str[m] = iter.next();
            m++;
        }
        //System.out.println("str的长度为" + str.length + " " + str[1]);
        for (int i = 0; i < str.length; i++) {
            System.out.println("str为" + str[i]);
        }

        for (int i = 0; i < str.length; i++) {
            if (!str[i].equals("")) {
                if (str[i].substring(0, 2).equals("E2")) {
                    //                    setTitle("制糖业管理系统");
                    if (str[i].substring(20, 24).equals("1678")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("1");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("2089")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("2");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("5B07")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("3");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("2994")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("4");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("2095")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("5");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("3931")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("6");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("3330")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("7");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("27B0")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("8");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("2D5E")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("9");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("0F09")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("10");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("4588")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("11");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("3F5E")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("12");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("3735")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("13");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("6CB0")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("14");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("619B")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("15");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("63CB")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("16");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("14E2")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("17");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("1818")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("18");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("5035")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("19");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("043B")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("20");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("1369")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("21");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("0F02")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("22");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("0750")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("23");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("2251")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("24");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("6A70")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("25");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("0348")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("26");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("87B8")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("27");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("7C74")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("28");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("063F")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("29");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("4163")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("30");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("0DA1")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("31");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("2F4A")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("32");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("7C78")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("33");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("58DE")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("34");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("14EA")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("35");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("034C")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("36");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("1B66")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("37");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("75B8")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("38");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("47A4")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("39");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("89F4")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("40");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("2B7A")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("41");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("524E")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("42");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("224A")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("43");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("1D0C")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("44");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("0C4F")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("45");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("027F")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("46");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("09AC")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("47");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("087F")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("48");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                    if (str[i].substring(20, 24).equals("1362")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("49");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                 
                   
                        if (str[i].substring(20, 24).equals("3B3A")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("50");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                         if (str[i].substring(20, 24).equals("0000")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("51");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                          if (str[i].substring(20, 24).equals("0001")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("52");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                           if (str[i].substring(20, 24).equals("0002")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("53");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                            if (str[i].substring(20, 24).equals("0003")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("54");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                             if (str[i].substring(20, 24).equals("0004")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("55");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                    }
                        if (str[i].substring(20, 24).equals("0005")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("56");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                        
                    }
                       if (str[i].substring(20, 24).equals("0006")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("57");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                        
                    }
                        if (str[i].substring(20, 24).equals("0007")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("58");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                        
                    }
                       if (str[i].substring(20, 24).equals("0008")) {
                        VisitorDao sugarDao = new VisitorDao();
                        Visitor sugar = new Visitor();
                        sugar = sugarDao.queryUniqueObject("59");
                        Vector readNoRepeatData = new Vector();
                        readNoRepeatData.add(sugar.getName());
                        readNoRepeatData.add(sugar.getIdCardNum());
                        readNoRepeatData.add(sugar.getTelNum());
                        readNoRepeatData.add(sugar.getTicketCardBalance());
                        readNoRepeatData.add(sugar.getTicketType());
                        readNoRepeatData.add(sugar.getReamrks());
                        result.add(readNoRepeatData);
                        
                    }
                    TableModel model1 = new DefaultTableModel(result, colNames) {
                        public Class<?> getColumnClass(int column) {
                            //                            Class returnValue;
                            //                            if (getRowCount()>0)
                            //                               {
                                //                                   returnValue = getValueAt(0,column).getClass();
                                //                                }
                            //                                else{
                                //                                    returnValue = int.class;
                                //                                }
                            return Integer.class;
                        }
                    };
                    //jTable1.setEnabled(false);
                    jTable1.setModel(model1);
                   
                    RowSorter sorter = new TableRowSorter(model1);
                    jTable1.setRowSorter(sorter);

                    //jTable1.setAutoCreateRowSorter (true);
                    jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    
                    
                    
                   
                    
                    DefaultTableCellRenderer r   = new DefaultTableCellRenderer();   
                    r.setHorizontalAlignment(JLabel.CENTER);   
                    jTable1.setDefaultRenderer(Object.class, r);
                    DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
                    hr.setHorizontalAlignment(JLabel.CENTER);
                    jTable1.getTableHeader().setDefaultRenderer(hr);
                    
                    
                    jTable1.setRowHeight(30); 
                    jTable1.getTableHeader().setPreferredSize(new Dimension(1, 33));

                    jTable1.getColumnModel()
                    .getColumn(0).setPreferredWidth(100);
                    jTable1.getColumnModel()
                    .getColumn(1).setPreferredWidth(145);
                    jTable1.getColumnModel()
                    .getColumn(2).setPreferredWidth(120);
                    jTable1.getColumnModel()
                    .getColumn(3).setPreferredWidth(110);
                    jTable1.getColumnModel()
                    .getColumn(4).setPreferredWidth(95);
                   
                    
                   over = 1;
                   if(over==1){
             
                 
                        num = jTable1.getRowCount();
                            System.out.println("nihao1");
             
                        jTextField1.setText(String.valueOf(num)+"  人");
             }
                    //                    jTable1.getColumnModel()
                    //                            .getColumn(5).setPreferredWidth(60);
                    //                    jTable1.getColumnModel()
                    //                            .getColumn(6).setPreferredWidth(60);

                }
            }
           
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int x = jTable1.getSelectedRow();
        if (jTable1.getValueAt(x, 1).toString().equals("")) {
            address = "";
        } else {
            address = jTable1.getValueAt(x, 0).toString();
        }
        JFrame frame = new JFrame(address + "历史游览信息");
        JPanel jp = new JPanel();
        //        frame.setPreferredSize(new Dimension(800,600));
        //        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        //        frame.setBounds(new Rectangle(
            //                (int)this.getBounds().getX(),
            //                (int)this.getBounds().getY(),
            //                (int)this.getBounds().getWidth(),
            //                (int)this.getBounds().getHeight()
            //        ));
    frame.setBounds(300, 300, 730, 220);

    Vector result = new Vector();
    List query_result = new ArrayList();
    Vector colNames = new Vector();
    colNames.add("姓名");
    colNames.add("身份证号码");
    colNames.add("手机号码");

    colNames.add("门票类型");
    //        colNames.add("位置信息");
    colNames.add("游玩时间");
    colNames.add("景点名称");

    VisitorDao visitorDao = new VisitorDao();
    Visitor visitor = new Visitor();
    int[] index = jTable1.getSelectedRows();
    String str = jTable1.getValueAt(index[0], 1).toString();
    System.out.println(str);
    query_result = visitorDao.queryObjectListByHql(str);
    for (int i = 0; i < query_result.size(); i++) {
        visitor = (Visitor) query_result.get(i);
        Vector readNoRepeatData = new Vector();
        readNoRepeatData.add(visitor.getName());
        readNoRepeatData.add(visitor.getIdCardNum());
        readNoRepeatData.add(visitor.getTelNum());
        //        readNoRepeatData.add(visitor.getLocal());
        readNoRepeatData.add(visitor.getTicketType());
        //            String date_str=visitor.getVisitDate().toString();
        //            String[] date=date_str.split(".");
        //            if (date.length>0){
            //            String date_use=date[0];
            //            readNoRepeatData.add(date_use);
            //            }
        readNoRepeatData.add(visitor.getVisitDate());
        readNoRepeatData.add(visitor.getVisitPlace());
        result.add(readNoRepeatData);
        }
        TableModel model = new DefaultTableModel(result, colNames) {
            public Class<?> getColumnClass(int column) {
                return Integer.class;
            }
        };
        JTable jt = new JTable();

        jt.setModel(model);
        RowSorter sorter = new TableRowSorter(model);
        jt.setRowSorter(sorter);
        jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        DefaultTableCellRenderer r   = new DefaultTableCellRenderer();   
        r.setHorizontalAlignment(JLabel.CENTER);   
        jt.setDefaultRenderer(Object.class, r);
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        jt.getTableHeader().setDefaultRenderer(hr);
        jt.setRowHeight(30); 
        jt.getTableHeader().setPreferredSize(new Dimension(1, 33));


        jt.getColumnModel()
        .getColumn(0).setPreferredWidth(55);
        jt.getColumnModel()
        .getColumn(1).setPreferredWidth(165);
        jt.getColumnModel()
        .getColumn(2).setPreferredWidth(130);
        jt.getColumnModel()
        .getColumn(3).setPreferredWidth(65);
        jt.getColumnModel()
        .getColumn(4).setPreferredWidth(170);
        jt.getColumnModel()
        .getColumn(5).setPreferredWidth(120);
        jp.add(jt);
        JScrollPane jsp = new JScrollPane(jt);
        frame.getContentPane().add(jp);
        frame.getContentPane().add(jsp);
        frame.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                jPanel2.removeAll();
               // jPanel6.removeAll();

                
                 String s=(String)jComboBox1.getSelectedItem(); 
                if (s.equals("")) {
                    address = "青秀山";
                } else {
                    address = s.toString();
                }
                System.out.println(address);

                System.out.println("11234");
                final int WIDTH1 = 700;
                final int HEIGHT1 = 600;

                final JFXPanel webBrowser1 = new JFXPanel();

                jPanel2.setLayout(new BorderLayout());
               // jPanel6.setLayout(new BorderLayout());
                jPanel2.add(webBrowser1);

                final EventHandler<ActionEvent> handler = null;
                System.out.println("11222234");
                final Group root1 = new Group();

                Scene scene1 = new Scene(root1, WIDTH1, HEIGHT1);

                webBrowser1.setScene(scene1);

                Double widthDouble1 = new Integer(WIDTH1).doubleValue();
                Double heightDouble1 = new Integer(HEIGHT1).doubleValue();

                //                Double heightDouble6 = new Integer(HEIGHT6).doubleValue();
                final WebView view1 = new WebView();

                view1.setMinSize(widthDouble1, heightDouble1);
                view1.setPrefSize(widthDouble1, heightDouble1);
                //                view6.setMinSize(widthDouble6, heightDouble6);
                //                view6.setPrefSize(widthDouble6, heightDouble6);
                final WebEngine eng1 = view1.getEngine();

                eng1.load(SystemGUI.class.getResource("design_map.html").toString());
                root1.getChildren().add(view1);
                System.out.println(eng1.isJavaScriptEnabled());
                //eng6.load(SystemGUI.class.getResource("index.html").toString());
                //root6.getChildren().add(view6);
                //System.out.println(eng6.isJavaScriptEnabled());
                //eng.executeScript("getName()");

                eng1.setOnAlert(new EventHandler<WebEvent<String>>() {
                    @Override
                    public void handle(WebEvent<String> event) {
                        System.out.println("this is event" + event);
                        System.out.println(address + "dff");
                        eng1.executeScript("setlocation('" + address + "')");
                        eng1.executeScript("getLocation('" + address + "')");
                    }
                });
                eng1.load(SystemGUI.class.getResource("design_map.html").toString());

//               // jPanel6.removeAll();
//                final int WIDTH6 =400;
//                final int HEIGHT6 =420;
//                final JFXPanel webBrowser6 = new JFXPanel();
//                jPanel6.setLayout(new BorderLayout());
//                jPanel6.add(webBrowser6);
//                final Group root6 = new Group();
//                Scene scene6 = new Scene(root6, WIDTH6, HEIGHT6);
//                webBrowser6.setScene(scene6);
//                Double widthDouble6 = new Integer(WIDTH6).doubleValue();
//                Double heightDouble6 = new Integer(HEIGHT6).doubleValue();
//                final WebView view6 = new WebView();
//
//                view6.setMinSize(widthDouble6, heightDouble6);
//                view6.setPrefSize(widthDouble6, heightDouble6);
//                final WebEngine eng6 = view6.getEngine();
//                String url = "";
//                if("青秀山桃花岛".equals(address)){
//                    url= "http://www.tudou.com";
//                }else if("青秀山状元坊".equals(address)){
//                    url= "https://www.baidu.com";
//                }else if("青秀山兰园".equals(address)){
//                    url= "http://android.taobao.com";
//                }else if("青秀山龙象塔".equals(address)){
//                    url= "http://192.168.1.100";
//                }
//                eng6.load(url);
//                root6.getChildren().add(view6);
                //                System.out.println(eng.isJavaScriptEnabled());
                //eng.executeScript("getName()");
            }
            //                eng6.load(SystemGUI.class.getResource("index1.html").toString());
        });

        System.out.println("333333");
        int screenWidth1 = Toolkit.getDefaultToolkit().getScreenSize().height;
        int screenHeight1 = Toolkit.getDefaultToolkit().getScreenSize().height;
        jPanel2.setSize(WIDTH, HEIGHT);
        jPanel2.setLocation((screenWidth1 - WIDTH) / 2, (screenHeight1 - HEIGHT) / 2);
        jPanel2.validate();
        jPanel2.setVisible(true);

        //                JPanel jPanel=(JPanel)frame.getContentPane();
        //                jPanel6.add(jPanel);
        //       Point point =new Point(50,50);
        //       JLabel label= new JLabel("这里");
        //       JButton  button = new JButton ("这里");
        //       button.setBounds(50, 100, 30, 40);
        //       label.setBackground(Color.yellow);
        //       label.setForeground(Color.red);
        //       label.setBounds(0, 0, 30, 40);
        //       jPanel2.add(label);
        //       jPanel2.add(button);
        //       Graphics g=jPanel2.getGraphics();
        //        try
        //        {
            //            Thread.sleep(10);
            //        }catch(Exception e)
        //        {
            //        }
        //       paintComponents(g);
        //       jPanel3.validate();
        //       jPanel2.validate();
        //       jPanel1.setVisible(false);
        //       jPanel3.setVisible(true);
        //       jPanel2.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton4_connect1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4_connect1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4_connect1ActionPerformed

    private void jButton4_connect2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4_connect2ActionPerformed
        String ip = jComboBox1_ip1.getSelectedItem().toString();
        int port = Integer.parseInt(jTextField1_port1.getText());

        MainHandler handler = new MainHandler();
        if (handler.dllInit("R2k.dll")) {
            if (handler.deviceInit(ip, 0, port)) {
                AntStruct struct = handler.GetAnt();
                for (int i = 0; i < 4; i++) {
                    System.out.println("天线" + (i + 1) + (struct.antEnable[i] == 1 ? "——已连接" : "——未连接") + "——工作时间:" + struct.dwellTime[i] + "ms——功率:" + struct.power[i].longValue() / 10 + "dBm");
                }
                jTextField4_replayConnect1.setText("天线已连接！");
            }
        }// TODO add your handling code here:// TODO add your handling code here:
    }//GEN-LAST:event_jButton4_connect2ActionPerformed

    private void jButton4_break1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4_break1ActionPerformed
        String ip = jComboBox1_ip1.getSelectedItem().toString();
        int port = Integer.parseInt(jTextField1_port1.getText());
        MainHandler handler = new MainHandler();
        if (handler.dllInit("R2k.dll")) {
            if (handler.deviceInit(ip, 0, port)) {
                handler.deviceDisconnect();
            }
            jTextField4_replayConnect1.setText("天线已断开！");// TODO add your handling code here:
    }//GEN-LAST:event_jButton4_break1ActionPerformed
    }
    private void jButton4_reset1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4_reset1ActionPerformed
        String ip = jComboBox1_ip1.getSelectedItem().toString();
        int port = Integer.parseInt(jTextField1_port1.getText());
        MainHandler handler = new MainHandler();
        if (handler.dllInit("R2k.dll")) {
            if (handler.deviceInit(ip, 0, port)) {
                handler.deviceUnInit();
            }
            jTextField4_replayConnect1.setText("天线已复位！");
        }//// TODO add your handling code here:
    }//GEN-LAST:event_jButton4_reset1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        //this.jComboBox1.DrawMode = System.Windows.Forms.DrawMode.OwnerDrawFixed;
// TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField4_replayConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4_replayConnectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4_replayConnectActionPerformed

    private void jTextField4_replayConnect1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4_replayConnect1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4_replayConnect1ActionPerformed

    private void jComboBox1_ant1TimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1_ant1TimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1_ant1TimeActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
             
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        jPanel1.setVisible(false);
        jPanel5.setVisible(true);
        //        jPanel4.setVisible(false);// TODO add your handling code here:
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jComboBox1_ip1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1_ip1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1_ip1ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(SystemGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SystemGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SystemGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SystemGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SystemGUI().setVisible(true);
                System.out.println("11");
//                ImageIcon icon=new ImageIcon("icon.jpg");
//                Frame f=new Frame("我的第一个窗口程序");
//                f.setBounds(0,0,100,200);
//                f.setIconImage(icon.getImage());
//                f.show();
                 System.out.println("2");
            }
        });
    }

    public void paintComponents(Graphics g) {
        try {
            // 设置线条颜色为红色
            g.setColor(Color.red);
            g.fillOval(100, 100, 10, 10);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton4_break;
    private javax.swing.JButton jButton4_break1;
    private javax.swing.JButton jButton4_connect;
    private javax.swing.JButton jButton4_connect1;
    private javax.swing.JButton jButton4_connect2;
    private javax.swing.JButton jButton4_reset;
    private javax.swing.JButton jButton4_reset1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton6_clearData;
    private javax.swing.JButton jButton6_readData;
    private javax.swing.JButton jButton6_writeData;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox1_input1;
    private javax.swing.JCheckBox jCheckBox1_output1;
    private javax.swing.JCheckBox jCheckBox1_selectAnt1;
    private javax.swing.JCheckBox jCheckBox1_selectAnt2;
    private javax.swing.JCheckBox jCheckBox2_input2;
    private javax.swing.JCheckBox jCheckBox2_output2;
    private javax.swing.JCheckBox jCheckBox2_selectAnt3;
    private javax.swing.JCheckBox jCheckBox3_selectAnt4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox1_ant1Time;
    private javax.swing.JComboBox<String> jComboBox1_ant2Time;
    private javax.swing.JComboBox<String> jComboBox1_ip;
    private javax.swing.JComboBox<String> jComboBox1_ip1;
    private javax.swing.JComboBox<String> jComboBox1_operateArea;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox2_ant1Power;
    private javax.swing.JComboBox<String> jComboBox2_ant2Power;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox3_ant3Time;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox4_ant3Power;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox5_ant4Time;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox6_ant4Power;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField1_dataReplay;
    private javax.swing.JTextField jTextField1_deviceNum;
    private javax.swing.JTextField jTextField1_nearDecide;
    private javax.swing.JTextField jTextField1_passwordLock;
    private javax.swing.JTextField jTextField1_passwordVisitor;
    private javax.swing.JTextField jTextField1_passwords;
    private javax.swing.JTextField jTextField1_port;
    private javax.swing.JTextField jTextField1_port1;
    private javax.swing.JTextField jTextField2_passwordCancel;
    private javax.swing.JTextField jTextField4_replayConnect;
    private javax.swing.JTextField jTextField4_replayConnect1;
    // End of variables declaration//GEN-END:variables


}
