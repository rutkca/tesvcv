package frame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import panel.BackgroundPanel;

import java.net.URL;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private URL url = null;// 声明图片的URL
	private Image image = null;// 声明图像对象
	private BackgroundPanel jPanel = null;// 声明自定义背景面板对象
	private JMenuBar jJMenuBar = null;
	private JMenu jMenu = null;// 快递单管理
	private JMenuItem addExpressMI = null;// 添加快递单
	private JMenuItem updateExpressMI = null;// 修改快递单
	private JMenu printManagementMenu = null;// 打印管理菜单
	private JMenu systemMenu = null;// 系统菜单
	private JMenuItem printMI = null;// 打印菜单
	private JMenuItem changePasswordMI = null;// 修改密码菜单
	private JMenuItem exitMI = null;// 退出菜单
	private JMenuItem addUserMI = null;// 添加用户菜单

	/**
	 * 主窗体构造方法
	 */
	public MainFrame() {
		super();
		initialize();
	}

	/**
	 * 窗体初始化
	 */
	private void initialize() {
		this.setSize(1017, 584);// 设置大小
		this.setJMenuBar(getJJMenuBar());// 设置菜单栏
		this.setTitle("快递打印系统");// 标题
		this.setContentPane(getJContentPane());// 设置主容器
	}

	/**
	 * 获取窗体主容器面板
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJPanel(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
     * 	背景面板
     */
	private BackgroundPanel getJPanel() {
		if (jPanel == null) {
			url = MainFrame.class.getResource("/image/主界面.jpg"); // 获得图片的URL
			image = new ImageIcon(url).getImage(); // 创建图像对象
			jPanel = new BackgroundPanel(image);
			jPanel.setLayout(null);
		}
		return jPanel;
	}

	/**
     * 	获取菜单栏
     */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJMenu());
			jJMenuBar.add(getprintManagementMenu());
			jJMenuBar.add(getsystemMenu());
		}
		return jJMenuBar;
	}

	/**
     * 	获取“快递单管理”菜单
     */
	private JMenu getJMenu() {
		if (jMenu == null) {
			jMenu = new JMenu();
			jMenu.setText("快递单管理");
			jMenu.add(getJMenuItem());
			jMenu.add(getupdateExpressMI());
		}
		return jMenu;
	}

	/**
     * 	获取“添加快递单”菜单
     */
	private JMenuItem getJMenuItem() {
		if (addExpressMI == null) {
			addExpressMI = new JMenuItem();
			addExpressMI.setText("添加快递单");
			addExpressMI.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					AddExpressFrame thisClass = new AddExpressFrame();
					thisClass.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					Toolkit tookit = thisClass.getToolkit();
					Dimension dm = tookit.getScreenSize();
					thisClass.setLocation(
							(dm.width - thisClass.getWidth()) / 2,
							(dm.height - thisClass.getHeight()) / 2);
					thisClass.setVisible(true);
				}
			});
		}
		return addExpressMI;
	}

	/**
	 * 获取“修改快递单”菜单
	 */
	private JMenuItem getupdateExpressMI() {
		if (updateExpressMI == null) {
			updateExpressMI = new JMenuItem();
			updateExpressMI.setText("修改快递单");
			updateExpressMI
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							UpdateExpressFrame thisClass = new UpdateExpressFrame();
							thisClass
									.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							Toolkit tookit = thisClass.getToolkit();
							Dimension dm = tookit.getScreenSize();
							thisClass.setLocation(
									(dm.width - thisClass.getWidth()) / 2,
									(dm.height - thisClass.getHeight()) / 2);
							thisClass.setVisible(true);
						}
					});
		}
		return updateExpressMI;
	}

	/**
	 * 获取“打印管理”菜单
	 */
	private JMenu getprintManagementMenu() {
		if (printManagementMenu == null) {
			printManagementMenu = new JMenu();
			printManagementMenu.setText("打印管理");
			printManagementMenu.add(getprintMI());
		}
		return printManagementMenu;
	}

	/**
	 * 获取“系统”菜单
	 */
	private JMenu getsystemMenu() {
		if (systemMenu == null) {
			systemMenu = new JMenu();
			systemMenu.setText("系统");
			systemMenu.add(getaddUserMI());
			systemMenu.add(getchangePasswordMI());
			systemMenu.add(getexitMI());
		}
		return systemMenu;
	}

	/**
	 * 获取“打印快递单”菜单
	 */
	private JMenuItem getprintMI() {
		if (printMI == null) {
			printMI = new JMenuItem();
			printMI.setText("打印快递单");
			printMI.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					PrintAndPrintSetFrame thisClass = new PrintAndPrintSetFrame();
					thisClass.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					Toolkit tookit = thisClass.getToolkit();
					Dimension dm = tookit.getScreenSize();
					thisClass.setLocation(
							(dm.width - thisClass.getWidth()) / 2,
							(dm.height - thisClass.getHeight()) / 2);
					thisClass.setVisible(true);
				}
			});
		}
		return printMI;
	}

	/**
	 * 获取“修改用户密码”菜单
	 */
	private JMenuItem getchangePasswordMI() {
		if (changePasswordMI == null) {
			changePasswordMI = new JMenuItem();
			changePasswordMI.setText("修改用户密码");
			changePasswordMI
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							UpdatePasswordFrame thisClass = new UpdatePasswordFrame();
							thisClass
									.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							Toolkit tookit = thisClass.getToolkit();
							Dimension dm = tookit.getScreenSize();
							thisClass.setLocation(
									(dm.width - thisClass.getWidth()) / 2,
									(dm.height - thisClass.getHeight()) / 2);
							thisClass.setVisible(true);
						}
					});
		}
		return changePasswordMI;
	}

	/**
	 * 获取“退出系统”菜单
	 */
	private JMenuItem getexitMI() {
		if (exitMI == null) {
			exitMI = new JMenuItem();
			exitMI.setText("退出系统");
			exitMI.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return exitMI;
	}

	/**
	 * 获取“添加用户”菜单
	 */
	private JMenuItem getaddUserMI() {
		if (addUserMI == null) {
			addUserMI = new JMenuItem();
			addUserMI.setText("添加用户");
			addUserMI.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					AddUserFrame thisClass = new AddUserFrame();
					thisClass.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					Toolkit tookit = thisClass.getToolkit();
					Dimension dm = tookit.getScreenSize();
					thisClass.setLocation(
							(dm.width - thisClass.getWidth()) / 2,
							(dm.height - thisClass.getHeight()) / 2);
					thisClass.setVisible(true);
				}
			});
		}
		return addUserMI;
	}
}
