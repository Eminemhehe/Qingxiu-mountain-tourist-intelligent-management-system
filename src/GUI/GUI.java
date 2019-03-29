/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Dao.VisitorDao;
import entity.Visitor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.sql.Date;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import netscape.javascript.JSObject;

/**
 * 
 * @author Administrator
 */
public class GUI extends javax.swing.JFrame
{

	/**
	 * Creates new form SystemGUI
	 */

	// Variables declaration - do not modify//GEN-BEGIN:variables
	public  static javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JFrame jFrame1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenu jMenu2;
	private javax.swing.JMenu jMenu4;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTable jTable1;
	private javax.swing.JTable jTable1_information_display;
	// End of variables declaration//GEN-END:variables
	
	public GUI()
	{
		initComponents();
		setTitle("游客人员信息管理系统");
		final int WIDTH = 700;
		final int HEIGHT = 600;
		final JFXPanel webBrowser = new JFXPanel();
		jPanel2.setLayout(new BorderLayout());
		jPanel2.add(webBrowser);

		
		//我已经 开始了。。。。。。。。。。。。。
//		Group root = new Group();
//		Scene scene = new Scene(root, WIDTH, HEIGHT);
//		webBrowser.setScene(scene);
//		Double widthDouble = new Integer(WIDTH).doubleValue();
//		Double heightDouble = new Integer(HEIGHT).doubleValue();
//		WebView view = new WebView();
//		view.setMinSize(widthDouble, heightDouble);
//		view.setPrefSize(widthDouble, heightDouble);
//		final WebEngine eng = view.getEngine();
//		eng.load(SystemGUI.class.getResource("design_map.html").toString());
//		root.getChildren().add(view);
//		System.out.println(eng.isJavaScriptEnabled());
		//eng.executeScript("getName");
		final EventHandler<ActionEvent> handler =null;
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{

				final Group root = new Group();
				Scene scene = new Scene(root, WIDTH, HEIGHT);
				webBrowser.setScene(scene);
				Double widthDouble = new Integer(WIDTH).doubleValue();
				Double heightDouble = new Integer(HEIGHT).doubleValue();
				WebView view = new WebView();

				view.setMinSize(widthDouble, heightDouble);
				view.setPrefSize(widthDouble, heightDouble);
				final WebEngine eng = view.getEngine();
				eng.load(SystemGUI.class.getResource("design_map.html").toString());
				root.getChildren().add(view);
				System.out.println(eng.isJavaScriptEnabled());
				//eng.executeScript("getName()");
				
				eng.setOnAlert(new EventHandler<WebEvent<String>>()
				{
					@Override
					public void handle(WebEvent<String> event)
					{
						
						System.out.println("this is event" + event);
						
						eng.executeScript("setlocation('凤凰塔')");
					}
				});
                                
      			// 加载新的地址
				 final EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event)
					{
						System.out.println("...");
						System.out.println(eng.executeScript("getName()"));
						eng.executeScript("getName('凤凰塔')");						
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
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents()
	{

		jFrame1 = new javax.swing.JFrame();
		jPanel3 = new javax.swing.JPanel();
		jPanel1 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1_information_display = new javax.swing.JTable();
		jButton1 = new javax.swing.JButton();
		jPanel2 = new javax.swing.JPanel()
		{
		};
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		jPanel6 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jPanel5 = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		jPanel4 = new javax.swing.JPanel();
		jLabel3 = new javax.swing.JLabel();
		jMenuBar1 = new javax.swing.JMenuBar();
		jMenu1 = new javax.swing.JMenu();
		jMenu2 = new javax.swing.JMenu();
		jMenu4 = new javax.swing.JMenu();

		javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
		jFrame1.getContentPane().setLayout(jFrame1Layout);
		jFrame1Layout.setHorizontalGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0,
				400, Short.MAX_VALUE));
		jFrame1Layout.setVerticalGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0,
				300, Short.MAX_VALUE));

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jTable1_information_display.setModel(new javax.swing.table.DefaultTableModel(new Object[][]
		{
		{ null, null },
		{ null, null },
		{ null, null },
		{ null, null } }, new String[]
		{ "阅读器编号", "景点名称" }));
		jTable1_information_display.setToolTipText("");
		jScrollPane1.setViewportView(jTable1_information_display);

		jButton1.setText("人员定位");
		jButton1.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				 System.out.println("点击了");
				jButton1ActionPerformed(evt);
				
			}
			
		});
		
		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0,
				677, Short.MAX_VALUE));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 0,
				Short.MAX_VALUE));

		jButton2.setText("更多信息");
		jButton2.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				jButton2ActionPerformed(evt);
			}
		});

		jButton3.setText("读取游客信息");

		jTable1.setModel(new javax.swing.table.DefaultTableModel(new Object[][]
		{

		}, new String[]
		{

		}));
		jScrollPane2.setViewportView(jTable1);

		jLabel1.setText("地图区");

		javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
		jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel6Layout.createSequentialGroup().addGap(170, 170, 170).addComponent(jLabel1)
						.addContainerGap(193, Short.MAX_VALUE)));
		jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel6Layout.createSequentialGroup().addGap(147, 147, 147).addComponent(jLabel1)
						.addContainerGap(160, Short.MAX_VALUE)));

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								jPanel1Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												jPanel1Layout.createSequentialGroup().addComponent(jButton3).addGap(39, 39, 39)
														.addComponent(jButton2).addGap(54, 54, 54).addComponent(jButton1))
										.addGroup(
												jPanel1Layout
														.createSequentialGroup()
														.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(59, 59, 59)
														.addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 627,
												javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE).addContainerGap()));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel1Layout
										.createSequentialGroup()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel1Layout
																										.createSequentialGroup()
																										.addComponent(
																												jPanel6,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE))
																						.addGroup(
																								jPanel1Layout
																										.createSequentialGroup()
																										.addComponent(
																												jScrollPane1,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												338,
																												Short.MAX_VALUE)
																										.addGap(18, 18, 18)
																										.addGroup(
																												jPanel1Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.BASELINE)
																														.addComponent(
																																jButton3)
																														.addComponent(
																																jButton2)
																														.addComponent(
																																jButton1))
																										.addGap(28, 28, 28)))
																		.addComponent(jScrollPane2,
																				javax.swing.GroupLayout.PREFERRED_SIZE, 214,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGap(134, 134, 134)));

		jLabel2.setText("读写设置");

		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				jPanel5Layout.createSequentialGroup().addContainerGap(43, Short.MAX_VALUE).addComponent(jLabel2)
						.addGap(33, 33, 33)));
		jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel5Layout.createSequentialGroup().addGap(43, 43, 43).addComponent(jLabel2)
						.addContainerGap(42, Short.MAX_VALUE)));

		jLabel3.setText("标签访问");

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel4Layout.createSequentialGroup().addGap(24, 24, 24).addComponent(jLabel3)
						.addContainerGap(34, Short.MAX_VALUE)));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel4Layout.createSequentialGroup().addGap(42, 42, 42).addComponent(jLabel3)
						.addContainerGap(43, Short.MAX_VALUE)));

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				jPanel3Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								jPanel3Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING,
												javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING,
												javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap(28, Short.MAX_VALUE)));
		jPanel3Layout.setVerticalGroup(jPanel3Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel3Layout
								.createSequentialGroup()
								.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 73, Short.MAX_VALUE))
				.addGroup(
						jPanel3Layout
								.createSequentialGroup()
								.addGap(79, 79, 79)
								.addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(41, 41, 41)
								.addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		jMenu1.setText("基本信息");
		jMenu1.addMouseListener(new java.awt.event.MouseAdapter()
		{
			public void mouseClicked(java.awt.event.MouseEvent evt)
			{
				jMenu1MouseClicked(evt);
			}
		});
		jMenu1.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				jMenu1ActionPerformed(evt);
			}
		});
		jMenuBar1.add(jMenu1);

		jMenu2.setText("读写设置");
		jMenu2.addMouseListener(new java.awt.event.MouseAdapter()
		{
			public void mouseClicked(java.awt.event.MouseEvent evt)
			{
				jMenu2MouseClicked(evt);
			}
		});
		jMenuBar1.add(jMenu2);

		jMenu4.setText("标签访问");
		jMenu4.addMouseListener(new java.awt.event.MouseAdapter()
		{
			public void mouseClicked(java.awt.event.MouseEvent evt)
			{
				jMenu4MouseClicked(evt);
			}
		});
		jMenuBar1.add(jMenu4);

		setJMenuBar(jMenuBar1);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addGap(0, 0, 0)
						.addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(328, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt)
	{// GEN-FIRST:event_jMenu1ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jMenu1ActionPerformed

	private void jMenu1MouseClicked(java.awt.event.MouseEvent evt)
	{// GEN-FIRST:event_jMenu1MouseClicked
		jPanel2.validate();
		jPanel1.validate();
		jPanel1.setVisible(true);
		jPanel2.setVisible(true);
		jPanel3.setVisible(true);
		jPanel5.setVisible(false);
		// WebView view = new WebView();
		// view.loadUrl("file:///android_asset/demo.html");

		// TODO add your handling code here:
	}// GEN-LAST:event_jMenu1MouseClicked

	public  void jButton1ActionPerformed(java.awt.event.ActionEvent evt)
	{// GEN-FIRST:event_jButton1ActionPerformed
		// JFrame frame = new JFrame();
		
		jPanel2.removeAll();
		final int WIDTH = 697;
		final int HEIGHT = 600;
		final JFXPanel webBrowser = new JFXPanel();
		jPanel2.setLayout(new BorderLayout());
		jPanel2.add(webBrowser);
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				Group root = new Group();
				Scene scene = new Scene(root, WIDTH, HEIGHT);
				webBrowser.setScene(scene);
				Double widthDouble = new Integer(WIDTH).doubleValue();
				Double heightDouble = new Integer(HEIGHT).doubleValue();
				WebView view = new WebView();
				view.setMinSize(widthDouble, heightDouble);
				view.setPrefSize(widthDouble, heightDouble);
				final WebEngine eng = view.getEngine();
				eng.load(SystemGUI.class.getResource("1111.html").toString());
				root.getChildren().add(view);
				eng.setJavaScriptEnabled(true);

			}
		});
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		jPanel2.setSize(WIDTH, HEIGHT);
		jPanel2.setLocation((screenWidth - WIDTH) / 2, (screenHeight - HEIGHT) / 2);
		jPanel2.setVisible(true);
		// JPanel jPanel=(JPanel)frame.getContentPane();
		// jPanel6.add(jPanel);
		// Point point =new Point(50,50);
		// JLabel label= new JLabel("这里");
		// JButton button = new JButton ("这里");
		// button.setBounds(50, 100, 30, 40);
		// label.setBackground(Color.yellow);
		// label.setForeground(Color.red);
		// label.setBounds(0, 0, 30, 40);
		// jPanel2.add(label);
		// jPanel2.add(button);
		// Graphics g=jPanel2.getGraphics();
		// try
		// {
		// Thread.sleep(10);
		// }catch(Exception e)
		// {
		// }
		// paintComponents(g);
		// jPanel3.validate();
		// jPanel2.validate();
		// jPanel1.setVisible(false);
		// jPanel3.setVisible(true);
		// jPanel2.setVisible(true);
		// TODO add your handling code here:
		
		
		
		
		
		
	}// GEN-LAST:event_jButton1ActionPerformed

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt)
	{// GEN-FIRST:event_jButton2ActionPerformed
		Vector result = new Vector();
		List query_result = new ArrayList();
		Vector colNames = new Vector();
		colNames.add("姓名");
		colNames.add("身份证号码");
		colNames.add("手机号码");
		colNames.add("储值卡余额");
		colNames.add("门票类型");
		// colNames.add("位置信息");
		colNames.add("游玩时间");
		colNames.add("景点名称");
		colNames.add("备注");

		VisitorDao visitorDao = new VisitorDao();
		Visitor visitor = new Visitor();
		int[] index = jTable1_information_display.getSelectedRows();
		String str = jTable1_information_display.getValueAt(index[0], 1).toString();
		query_result = visitorDao.queryObjectListByHql(str);
		for (int i = 0; i < query_result.size(); i++)
		{
			visitor = (Visitor) query_result.get(i);
			Vector readNoRepeatData = new Vector();
			readNoRepeatData.add(visitor.getName());
			readNoRepeatData.add(visitor.getIdCardNum());
			readNoRepeatData.add(visitor.getTelNum());
			readNoRepeatData.add(visitor.getTicketCardBalance());
			// readNoRepeatData.add(visitor.getLocal());
			readNoRepeatData.add(visitor.getTicketType());
			readNoRepeatData.add(new Date(visitor.getVisitDate().getTime()));
			readNoRepeatData.add(visitor.getVisitPlace());
			readNoRepeatData.add(visitor.getReamrks());
			result.add(readNoRepeatData);
		}
		TableModel model = new DefaultTableModel(result, colNames)
		{
			public Class<?> getColumnClass(int column)
			{
				return Integer.class;
			}
		};

		jTable1.setModel(model);
		RowSorter sorter = new TableRowSorter(model);
		jTable1.setRowSorter(sorter);
		jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// TODO add your handling code here:
	}// GEN-LAST:event_jButton2ActionPerformed

	private void jMenu2MouseClicked(java.awt.event.MouseEvent evt)
	{// GEN-FIRST:event_jMenu2MouseClicked
		jPanel1.setVisible(false);
		jPanel5.setVisible(true);
		jPanel4.setVisible(false);// TODO add your handling code here:
	}// GEN-LAST:event_jMenu2MouseClicked

	private void jMenu4MouseClicked(java.awt.event.MouseEvent evt)
	{// GEN-FIRST:event_jMenu4MouseClicked
		jPanel1.setVisible(false);
		jPanel5.setVisible(false);
		jPanel4.setVisible(true);// TODO add your handling code here:
	}// GEN-LAST:event_jMenu4MouseClicked

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[])
	{
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed"
		// desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		 */
		try
		{
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
			{
				if ("Nimbus".equals(info.getName()))
				{
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex)
		{
			java.util.logging.Logger.getLogger(SystemGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex)
		{
			java.util.logging.Logger.getLogger(SystemGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex)
		{
			java.util.logging.Logger.getLogger(SystemGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex)
		{
			java.util.logging.Logger.getLogger(SystemGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				new SystemGUI().setVisible(true);
			}
		});
	}

	public void paintComponents(Graphics g)
	{
		try
		{
			// 设置线条颜色为红色
			g.setColor(Color.red);
			g.fillOval(100, 100, 10, 10);
			// }
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}

