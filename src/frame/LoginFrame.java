package frame;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JFrame;

import bean.User;
import dao.UserDao;
import panel.BackgroundPanel;

import java.net.URL;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

/**
 * 登陆面板
 *
 */
public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	
	private URL url = null;// 声明图片的URL
	private Image image = null;// 声明图像对象
	private BackgroundPanel jPanel = null;// 声明自定义背景面板对象
	
	private JLabel jLabel = null;
	private JLabel passwordLabel = null;// 密码标签
	private JTextField tf_username = null;
	private JPasswordField pf_password = null;
	
	private JButton btn_login = null;
	private JButton btn_reset = null;
	private JButton btn_exit = null;

	/**
	 * 
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			passwordLabel = new JLabel();
			passwordLabel.setBounds(new Rectangle(221, 176, 63, 18));
			passwordLabel.setText("密    码：");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(220, 141, 63, 18));
			jLabel.setText("用    户：");
			url = UpdateExpressFrame.class.getResource("/image/登录.jpg"); 
			// 获得图片的URL
			image = new ImageIcon(url).getImage(); // 创建图像对象
			jPanel = new BackgroundPanel(image);
			jPanel.setLayout(null);
			jPanel.add(jLabel, null);
			jPanel.add(passwordLabel, null);
			jPanel.add(getTf_username(), null);
			jPanel.add(getPf_password(), null);
			jPanel.add(getBtn_login(), null);
			jPanel.add(getBtn_reset(), null);
			jPanel.add(getBtn_exit(), null);
		}
		return jPanel;
	}

	/**
	 * 
	 */
	private JTextField getTf_username() {
		if (tf_username == null) {
			tf_username = new JTextField();
			tf_username.setBounds(new Rectangle(290, 140, 143, 22));
			tf_username.addActionListener(e -> pf_password.requestFocus());
		}
		return tf_username;
	}

	/**
	 * 
	 */
	private JPasswordField getPf_password() {
		if (pf_password == null) {
			pf_password = new JPasswordField();
			pf_password.setBounds(new Rectangle(290, 175, 141, 22));
			pf_password.setEchoChar('*');
			pf_password.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					userLogin();// 用户登陆触发方法
				}
			});
		}
		return pf_password;
	}

	/**
	 * 
	 */
	private JButton getBtn_login() {
		if (btn_login == null) {
			btn_login = new JButton();
			btn_login.setBounds(new Rectangle(221, 211, 56, 22));
			btn_login.setRolloverIcon(new ImageIcon(getClass().getResource(
					"/image/dl.jpg")));
			btn_login.setIcon(new ImageIcon(getClass().getResource(
					"/image/dl01.jpg")));
			btn_login.setMargin(new Insets(0, 0, 0, 0));
			btn_login.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					userLogin();// 用户登陆触发方法
				}
			});
		}
		return btn_login;
	}

	/**
	 * 用户登陆触发方法
	 */
	private void userLogin() {
		String username = tf_username.getText().trim();
		String password = new String(pf_password.getPassword());
		User user = new User();
		user.setName(username);
		user.setPwd(password);
		if (UserDao.userLogin(user)) {
			MainFrame thisClass = new MainFrame();
			thisClass.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
			Toolkit tookit = thisClass.getToolkit();
			Dimension dm = tookit.getScreenSize();
			thisClass.setLocation((dm.width - thisClass.getWidth()) / 2,
					(dm.height - thisClass.getHeight()) / 2);
			thisClass.setVisible(true);
			dispose();
		}
	}

	/**
	 * This method initializes btn_reset
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtn_reset() {
		if (btn_reset == null) {
			btn_reset = new JButton();
			btn_reset.setBounds(new Rectangle(299, 211, 55, 23));
			btn_reset.setRolloverIcon(new ImageIcon(getClass().getResource(
					"/image/cz.jpg")));
			btn_reset.setIcon(new ImageIcon(getClass().getResource(
					"/image/cz01.jpg")));
			btn_reset.setMargin(new Insets(0, 0, 0, 0));
			btn_reset.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					tf_username.setText("");
					pf_password.setText("");
					tf_username.requestFocus();
				}
			});
		}
		return btn_reset;
	}

	/**
	 * This method initializes btn_exit
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtn_exit() {
		if (btn_exit == null) {
			btn_exit = new JButton();
			btn_exit.setBounds(new Rectangle(374, 211, 53, 22));
			btn_exit.setRolloverIcon(new ImageIcon(getClass().getResource(
					"/image/tc.jpg")));
			btn_exit.setIcon(new ImageIcon(getClass().getResource(
					"/image/tc01.jpg")));
			btn_exit.setMargin(new Insets(0, 0, 0, 0));
			btn_exit.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return btn_exit;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				LoginFrame thisClass = new LoginFrame();
				thisClass.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
				Toolkit tookit = thisClass.getToolkit();
				Dimension dm = tookit.getScreenSize();
				thisClass.setLocation((dm.width - thisClass.getWidth()) / 2,
						(dm.height - thisClass.getHeight()) / 2);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public LoginFrame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(476, 301);
		this.setContentPane(getJContentPane());
		this.setTitle("系统登录");
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
		}
		return jContentPane;
	}

} // @jve:decl-index=0:visual-constraint="10,10"
