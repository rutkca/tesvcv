package frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.net.URL;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;

import bean.ExpressMessage;
import dao.ExpressMessageDao;
import panel.BackgroundPanel;
import javax.swing.JLabel;

public class PrintAndPrintSetFrame extends JFrame {
	private JPanel jContentPane = null;// 窗体容器面板
	private URL url = null;// 声明图片的URL
	private Image image = null;// 声明图像对象
	private BackgroundPanel jPanel = null;// 声明自定义背景面板对象
	private JTextField tf_sendName = null;// 发件人姓名
	private JTextField tf_sendTelephone = null;// 发件人电话
	private JTextField tf_sendCompany = null;// 发件单位
	private JTextField tf_sendAddress1 = null;// 发件人地址1
	private JTextField tf_sendAddress2 = null;// 发件人地址2
	private JTextField tf_sendAddress3 = null;// 发件人地址3
	private JTextField tf_sendPostcode = null;// 发件邮编
	private JTextField tf_receiveName = null;// 收件人姓名
	private JTextField tf_receiveTelephone = null;// 收件人电话
	private JTextField tf_receiveCompany = null;// 收件单位
	private JTextField tf_receiveAddress1 = null;// 收件人地址1
	private JTextField tf_receiveAddress2 = null;// 收件人地址2
	private JTextField tf_receiveAddress3 = null;// 收件人地址3
	private JTextField tf_receivePostcode = null;// 收件邮编
	private JPanel jPanel1 = null;// 窗体底部面板
	private JButton btn_next = null;// 下一条按钮
	private JButton btn_print = null;// 打印按钮
	private JButton btn_return = null;// 返回按钮
	private JButton btn_pre = null;// 上一条按钮
	private int queryRow = -1;// 当前读取快递单索引
	private Vector queryResultVector = null;
	private String controlPosition = null; // @jve:decl-index=0:
	private String expressSize = null; // @jve:decl-index=0:
	private JLabel jLabel = null;// X标签
	private JTextField tf_x = null;// X值输入框
	private JLabel jLabel1 = null;// Y标签
	private JTextField tf_y = null;// Y值输入框
	private JButton btn_printSet = null;// 设置按钮
	private static Graphics2D graphic = null;// 绘图类对象
	private int addX = -15;// 文字信息在图片上的横坐标偏移量
	private int addY = 30;// 文字在图片上的纵坐标偏移量

	/**
	 * 初始化并获取底部面板
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Y：");
			jLabel = new JLabel();
			jLabel.setText("X：");
			jPanel1 = new JPanel();
			jPanel1.setLayout(new FlowLayout());
			jPanel1.add(jLabel, null);
			jPanel1.add(getTf_x(), null);
			jPanel1.add(jLabel1, null);
			jPanel1.add(getTf_y(), null);
			jPanel1.add(getBtn_printSet(), null);
			jPanel1.add(getBtn_pre(), null);
			jPanel1.add(getBtn_next(), null);
			jPanel1.add(getBtn_print(), null);
			jPanel1.add(getBtn_return(), null);
		}
		return jPanel1;
	}

	/**
	 * 初始化并获取下一条按钮
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtn_next() {
		if (btn_next == null) {
			btn_next = new JButton();
			btn_next.setText("下一条");
			btn_next.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					queryResultVector = ExpressMessageDao.queryAllExpress();
					if (queryResultVector != null) {
						queryRow++;
						if (queryRow > queryResultVector.size() - 1) {
							queryRow = queryResultVector.size() - 1;
							JOptionPane.showMessageDialog(null, "已经是最后一条信息。");
						}
						ExpressMessage m = (ExpressMessage) queryResultVector
								.get(queryRow);
						showResultValue(m);
					}
				}
			});
		}
		return btn_next;
	}

	/**
	 * 初始化并获取上一条按钮
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtn_pre() {
		if (btn_pre == null) {
			btn_pre = new JButton();
			btn_pre.setText("上一条");
			btn_pre.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					queryResultVector = ExpressMessageDao.queryAllExpress();
					if (queryResultVector != null) {
						queryRow--;
						if (queryRow < 0) {
							queryRow = 0;
							JOptionPane.showMessageDialog(null, "已经是第一条信息。");
						}
						ExpressMessage m = (ExpressMessage) queryResultVector
								.get(queryRow);
						showResultValue(m);
					}
				}
			});
		}
		return btn_pre;
	}

	/**
	 * 初始化并获取打印按钮
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtn_print() {
		if (btn_print == null) {
			btn_print = new JButton();
			btn_print.setText("打    印");
			btn_print.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						PrinterJob job = PrinterJob.getPrinterJob();
						if (!job.printDialog())
							return;
						job.setPrintable(new Printable() {
							public int print(Graphics graphics,
									PageFormat pageFormat, int pageIndex) {
								if (pageIndex > 0) {
									return Printable.NO_SUCH_PAGE;
								}
								int x = (int) pageFormat.getImageableX();
								int y = (int) pageFormat.getImageableY();
								int ww = (int) pageFormat.getImageableWidth();
								int hh = (int) pageFormat.getImageableHeight();
								Graphics2D g2 = (Graphics2D) graphics;
								URL ur = UpdateExpressFrame.class
										.getResource("/image/追封快递单.jpg"); // 获得图片的URL
								Image img = new ImageIcon(ur).getImage(); // 创建图像对象
								int w = Integer.parseInt(expressSize.substring(
										0, expressSize.indexOf(",")));
								int h = Integer.parseInt(expressSize
										.substring(expressSize.indexOf(",") + 1));
								if (w > ww) {
									w = ww;
								}
								if (h > hh) {
									h = hh;
								}
								g2.drawImage(img, x, y, w, h, null);
								String[] pos = controlPosition.split("/");
								int px = Integer.parseInt(pos[0].substring(0,
										pos[0].indexOf(",")));
								int py = Integer.parseInt(pos[0]
										.substring(pos[0].indexOf(",") + 1));
								String sendName = tf_sendName.getText();
								g2.drawString(sendName, px + addX, py + addY);
								px = Integer.parseInt(pos[1].substring(0,
										pos[1].indexOf(",")));
								py = Integer.parseInt(pos[1].substring(pos[1]
										.indexOf(",") + 1));
								String sendTelephone = tf_sendTelephone
										.getText();
								g2.drawString(sendTelephone, px + addX, py
										+ addY);
								px = Integer.parseInt(pos[2].substring(0,
										pos[2].indexOf(",")));
								py = Integer.parseInt(pos[2].substring(pos[2]
										.indexOf(",") + 1));
								String sendCompory = tf_sendCompany.getText();
								g2.drawString(sendCompory, px + addX, py + addY);
								px = Integer.parseInt(pos[3].substring(0,
										pos[3].indexOf(",")));
								py = Integer.parseInt(pos[3].substring(pos[3]
										.indexOf(",") + 1));
								String sendAddress1 = tf_sendAddress1.getText();
								g2.drawString(sendAddress1, px + addX, py
										+ addY);
								px = Integer.parseInt(pos[4].substring(0,
										pos[4].indexOf(",")));
								py = Integer.parseInt(pos[4].substring(pos[4]
										.indexOf(",") + 1));
								String sendAddress2 = tf_sendAddress2.getText();
								g2.drawString(sendAddress2, px + addX, py
										+ addY);
								px = Integer.parseInt(pos[5].substring(0,
										pos[5].indexOf(",")));
								py = Integer.parseInt(pos[5].substring(pos[5]
										.indexOf(",") + 1));
								String sendAddress3 = tf_sendAddress3.getText();
								g2.drawString(sendAddress3, px + addX, py
										+ addY);
								px = Integer.parseInt(pos[6].substring(0,
										pos[6].indexOf(",")));
								py = Integer.parseInt(pos[6].substring(pos[6]
										.indexOf(",") + 1));
								String sendPostCode = tf_sendPostcode.getText();
								g2.drawString(sendPostCode, px + addX, py
										+ addY);
								px = Integer.parseInt(pos[7].substring(0,
										pos[7].indexOf(",")));
								py = Integer.parseInt(pos[7].substring(pos[7]
										.indexOf(",") + 1));
								String receiveName = tf_receiveName.getText();
								g2.drawString(receiveName, px + addX, py + addY);
								px = Integer.parseInt(pos[8].substring(0,
										pos[8].indexOf(",")));
								py = Integer.parseInt(pos[8].substring(pos[8]
										.indexOf(",") + 1));
								String receiveTelephone = tf_receiveTelephone
										.getText();
								g2.drawString(receiveTelephone, px + addX, py
										+ addY);
								px = Integer.parseInt(pos[9].substring(0,
										pos[9].indexOf(",")));
								py = Integer.parseInt(pos[9].substring(pos[9]
										.indexOf(",") + 1));
								String receiveCompory = tf_receiveCompany
										.getText();
								g2.drawString(receiveCompory, px + addX, py
										+ addY);
								px = Integer.parseInt(pos[10].substring(0,
										pos[10].indexOf(",")));
								py = Integer.parseInt(pos[10].substring(pos[10]
										.indexOf(",") + 1));
								String receiveAddress1 = tf_receiveAddress1
										.getText();
								g2.drawString(receiveAddress1, px + addX, py
										+ addY);
								px = Integer.parseInt(pos[11].substring(0,
										pos[11].indexOf(",")));
								py = Integer.parseInt(pos[11].substring(pos[11]
										.indexOf(",") + 1));
								String receiveAddress2 = tf_receiveAddress2
										.getText();
								g2.drawString(receiveAddress2, px + addX, py
										+ addY);
								px = Integer.parseInt(pos[12].substring(0,
										pos[12].indexOf(",")));
								py = Integer.parseInt(pos[12].substring(pos[12]
										.indexOf(",") + 1));
								String receiveAddress3 = tf_receiveAddress3
										.getText();
								g2.drawString(receiveAddress3, px + addX, py
										+ addY);
								px = Integer.parseInt(pos[13].substring(0,
										pos[13].indexOf(",")));
								py = Integer.parseInt(pos[13].substring(pos[13]
										.indexOf(",") + 1));
								String receivePostCode = tf_receivePostcode
										.getText();
								g2.drawString(receivePostCode, px + addX, py
										+ addY);
								return Printable.PAGE_EXISTS;
							}
						});
						job.setJobName("打印快递单");
						job.print();
					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}
			});
		}
		return btn_print;
	}

	/**
	 * 初始化并获取返回按钮
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtn_return() {
		if (btn_return == null) {
			btn_return = new JButton();
			btn_return.setText("返    回");
			btn_return.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
				}
			});
		}
		return btn_return;
	}

	/**
	 * 初始化并获取横向偏移量输入框
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTf_x() {
		if (tf_x == null) {
			tf_x = new JTextField("-15           ");
		}
		return tf_x;
	}

	/**
	 * 初始化并获取纵向偏移量输入框
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTf_y() {
		if (tf_y == null) {
			tf_y = new JTextField("30           ");
		}
		return tf_y;
	}

	/**
	 * 初始化并获取返设置按钮
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtn_printSet() {
		if (btn_printSet == null) {
			btn_printSet = new JButton();
			btn_printSet.setText("设    置");
			btn_printSet.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						addX = Integer.parseInt(tf_x.getText().trim());
						addY = Integer.parseInt(tf_y.getText().trim());
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "x和y的值必须为数值。");
						addX = 0;
						addY = 0;
					}
				}
			});
		}
		return btn_printSet;
	}

	/**
	 * 打印面板构造方法
	 * 
	 * @param owner
	 */
	public PrintAndPrintSetFrame() {
		super();
		initialize();
	}

	/**
	 * 打印面板组件初始化
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setResizable(false);// 不可调整大小
		this.setSize(1017, 575);
		this.setTitle("打印快递单与打印设置");
		this.setContentPane(getJContentPane());
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowOpened(java.awt.event.WindowEvent e) {
				queryResultVector = ExpressMessageDao.queryAllExpress();
				if (queryResultVector != null) {
					queryRow++;
					ExpressMessage m = (ExpressMessage) queryResultVector
							.get(queryRow);
					showResultValue(m);
				}
			}
		});
	}

	/**
	 * 将快递单信息写入到界面的输入框中
	 * 
	 * @param m
	 */
	private void showResultValue(ExpressMessage m) {
		tf_sendName.setText(m.getSendName());
		tf_sendTelephone.setText(m.getSendTelephone());
		tf_sendCompany.setText(m.getSendCompary());
		String sendAddressValueALL = m.getSendAddress();
		

		tf_sendAddress1.setText("");
		tf_sendAddress2.setText("");
		tf_sendAddress3.setText("");
		String sendAddressValues[] = sendAddressValueALL.split("\\|");
		switch (sendAddressValues.length) {
		case 3:
			tf_sendAddress3.setText(sendAddressValues[2]);
		case 2:
			tf_sendAddress2.setText(sendAddressValues[1]);
		case 1:
			tf_sendAddress1.setText(sendAddressValues[0]);
		}

		tf_sendPostcode.setText(m.getSendPostcode());
		tf_receiveName.setText(m.getReceiveName());
		tf_receiveTelephone.setText(m.getReceiveTelephone());
		tf_receiveCompany.setText(m.getReceiveCompary());
		String receiveAddressALL = m.getReceiveAddress();
		String receiveAddresss[] = receiveAddressALL.split("\\|");
		switch (sendAddressValues.length) {
		case 3:
			tf_receiveAddress3.setText(receiveAddresss[2]);
		case 2:
			tf_receiveAddress2.setText(receiveAddresss[1]);
		case 1:
			tf_receiveAddress1.setText(receiveAddresss[0]);
		}
		
		tf_receivePostcode.setText(m.getReceivePostcode());
		controlPosition = m.getControlPosition();
		expressSize = m.getExpressSize();
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJPanel(), BorderLayout.CENTER);
			jContentPane.add(getJPanel1(), BorderLayout.SOUTH);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private BackgroundPanel getJPanel() {
		if (jPanel == null) {
			url = PrintAndPrintSetFrame.class.getResource("/image/追封快递单.jpg"); // 获得图片的URL
			image = new ImageIcon(url).getImage(); // 创建图像对象
			jPanel = new BackgroundPanel(image);
			jPanel.setLayout(null);
			jPanel.add(getTf_sendName(), null);
			jPanel.add(getTf_sendTelephone(), null);
			jPanel.add(getTf_sendCompany(), null);
			jPanel.add(getTf_sendAddress1(), null);
			jPanel.add(getTf_sendAddress2(), null);
			jPanel.add(getTf_sendAddress3(), null);
			jPanel.add(getTf_sendPostcode(), null);
			jPanel.add(getTf_receiveName(), null);
			jPanel.add(getTf_receiveTelephone(), null);
			jPanel.add(getTf_receiveCompany(), null);
			jPanel.add(getTf_receiveAddress1(), null);
			jPanel.add(getTf_receiveAddress2(), null);
			jPanel.add(getTf_receiveAddress3(), null);
			jPanel.add(getTf_receivePostcode(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes tf_sendName
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTf_sendName() {
		if (tf_sendName == null) {
			tf_sendName = new JTextField();
			tf_sendName.setBounds(new Rectangle(150, 114, 139, 22));
			// 149, 123, 139, 22
		}
		return tf_sendName;
	}

	/**
	 * This method initializes tf_sendTelephone
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTf_sendTelephone() {
		if (tf_sendTelephone == null) {
			tf_sendTelephone = new JTextField();
			tf_sendTelephone.setBounds(new Rectangle(347, 114, 131, 22));
		}
		return tf_sendTelephone;
	}

	/**
	 * This method initializes tf_sendCompany
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTf_sendCompany() {
		if (tf_sendCompany == null) {
			tf_sendCompany = new JTextField();
			tf_sendCompany.setBounds(new Rectangle(150, 141, 328, 22));
		}
		return tf_sendCompany;
	}

	/**
	 * This method initializes tf_sendAddress1
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTf_sendAddress1() {
		if (tf_sendAddress1 == null) {
			tf_sendAddress1 = new JTextField();
			tf_sendAddress1.setBounds(new Rectangle(115, 179, 362, 22));
		}
		return tf_sendAddress1;
	}

	/**
	 * This method initializes tf_sendAddress2
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTf_sendAddress2() {
		if (tf_sendAddress2 == null) {
			tf_sendAddress2 = new JTextField();
			tf_sendAddress2.setBounds(new Rectangle(114, 205, 362, 22));
		}
		return tf_sendAddress2;
	}

	/**
	 * This method initializes tf_sendAddress3
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTf_sendAddress3() {
		if (tf_sendAddress3 == null) {
			tf_sendAddress3 = new JTextField();
			tf_sendAddress3.setBounds(new Rectangle(114, 230, 361, 22));
		}
		return tf_sendAddress3;
	}

	/**
	 * This method initializes tf_sendPostcode
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTf_sendPostcode() {
		if (tf_sendPostcode == null) {
			tf_sendPostcode = new JTextField();
			tf_sendPostcode.setBounds(new Rectangle(366, 256, 109, 22));
		}
		return tf_sendPostcode;
	}

	/**
	 * This method initializes tf_receiveName
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTf_receiveName() {
		if (tf_receiveName == null) {
			tf_receiveName = new JTextField();
			tf_receiveName.setBounds(new Rectangle(151, 311, 142, 22));
		}
		return tf_receiveName;
	}

	/**
	 * This method initializes tf_receiveTelephone
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTf_receiveTelephone() {
		if (tf_receiveTelephone == null) {
			tf_receiveTelephone = new JTextField();
			tf_receiveTelephone.setBounds(new Rectangle(349, 312, 126, 22));
		}
		return tf_receiveTelephone;
	}

	/**
	 * This method initializes tf_receiveCompany
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTf_receiveCompany() {
		if (tf_receiveCompany == null) {
			tf_receiveCompany = new JTextField();
			tf_receiveCompany.setBounds(new Rectangle(150, 339, 325, 22));
		}
		return tf_receiveCompany;
	}

	/**
	 * This method initializes tf_receiveAddress1
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTf_receiveAddress1() {
		if (tf_receiveAddress1 == null) {
			tf_receiveAddress1 = new JTextField();
			tf_receiveAddress1.setBounds(new Rectangle(115, 377, 363, 22));
		}
		return tf_receiveAddress1;
	}

	/**
	 * This method initializes tf_receiveAddress2
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTf_receiveAddress2() {
		if (tf_receiveAddress2 == null) {
			tf_receiveAddress2 = new JTextField();
			tf_receiveAddress2.setBounds(new Rectangle(115, 404, 362, 22));
		}
		return tf_receiveAddress2;
	}

	/**
	 * This method initializes tf_receiveAddress3
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTf_receiveAddress3() {
		if (tf_receiveAddress3 == null) {
			tf_receiveAddress3 = new JTextField();
			tf_receiveAddress3.setBounds(new Rectangle(116, 430, 362, 22));
		}
		return tf_receiveAddress3;
	}

	/**
	 * This method initializes tf_receivePostcode
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTf_receivePostcode() {
		if (tf_receivePostcode == null) {
			tf_receivePostcode = new JTextField();
			tf_receivePostcode.setBounds(new Rectangle(366, 456, 112, 22));
		}
		return tf_receivePostcode;
	}

	public static Graphics2D getGraphic() {
		return graphic;
	}

	public static void setGraphic(Graphics2D graphic) {
		PrintAndPrintSetFrame.graphic = graphic;
	}
}
