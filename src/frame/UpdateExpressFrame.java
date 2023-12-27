package frame;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Rectangle;
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

public class UpdateExpressFrame extends JFrame {
    
    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private URL url = null;// 声明图片的URL
    private Image image=null;// 声明图像对象
    private BackgroundPanel jPanel = null;// 声明自定义背景面板对象
    private JTextField tf_sendName = null;
    private JTextField tf_sendTelephone = null;
    private JTextField tf_sendCompany = null;
    private JTextField tf_sendAddress1 = null;
    private JTextField tf_sendAddress2 = null;
    private JTextField tf_sendAddress3 = null;
    private JTextField tf_sendPostcode = null;
    private JTextField tf_receiveName = null;
    private JTextField tf_receiveTelephone = null;
    private JTextField tf_receiveCompany = null;
    private JTextField tf_receiveAddress1 = null;
    private JTextField tf_receiveAddress2 = null;
    private JTextField tf_receiveAddress3 = null;
    private JTextField tf_receivePostcode = null;
    private JPanel jPanel1 = null;
    private JButton btn_next = null;
    private JButton btn_update = null;
    private JButton jButton2 = null;
    private JButton btn_pre = null;
    private int queryRow = -1;
    private Vector queryResultVector = null;
    private String controlPosition = null;  //  @jve:decl-index=0:
    private String expressSize = null;  //  @jve:decl-index=0:
    private int id = 0;
    /**
     * This method initializes jPanel1  
     *  
     * @return javax.swing.JPanel   
     */
    private JPanel getJPanel1() {
        if (jPanel1 == null) {
            jPanel1 = new JPanel();
            jPanel1.setLayout(new FlowLayout());
            jPanel1.add(getBtn_pre(), null);
            jPanel1.add(getBtn_next(), null);
            jPanel1.add(getBtn_update(), null);
            jPanel1.add(getJButton2(), null);
        }
        return jPanel1;
    }

    /**
     * This method initializes btn_next  
     *  
     * @return javax.swing.JButton  
     */
    private JButton getBtn_next() {
        if (btn_next == null) {
            btn_next = new JButton();
            btn_next.setText("下一条");
            btn_next.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    //queryResultVector = ExpressMessageDao.queryExpress();
                    if (queryResultVector!=null){
                        queryRow++;
                        if (queryRow > queryResultVector.size() - 1){
                            queryRow = queryResultVector.size() - 1;
                            JOptionPane.showMessageDialog(null, "已经是最后一条信息。");
                        }
                        ExpressMessage m = (ExpressMessage) queryResultVector.get(queryRow);
                        showResultValue(m);
                    }
                }
            });
        }
        return btn_next;
    }
    /**
     * This method initializes btn_pre	
     * 	
     * @return javax.swing.JButton	
     */
    private JButton getBtn_pre() {
        if (btn_pre == null) {
            btn_pre = new JButton();
            btn_pre.setText("上一条");
            btn_pre.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    //queryResultVector = ExpressMessageDao.queryExpress();
                    if (queryResultVector!=null){
                        queryRow--;
                        if (queryRow < 0) {
                            queryRow = 0;
                            JOptionPane.showMessageDialog(null, "已经是第一条信息。");
                        }
                        ExpressMessage m = (ExpressMessage) queryResultVector.get(queryRow);
                        showResultValue(m);
                    }
                }
            });
        }
        return btn_pre;
    }
    /**
     * This method initializes btn_update 
     *  
     * @return javax.swing.JButton  
     */
    private JButton getBtn_update() {
        if (btn_update == null) {
            btn_update = new JButton();
            btn_update.setText("修    改");
            btn_update.addActionListener(new java.awt.event.ActionListener() {   
            	public void actionPerformed(java.awt.event.ActionEvent e) {    
            	    StringBuffer buffer = new StringBuffer();
                    ExpressMessage m = new ExpressMessage();
                    m.setId(id);
                    m.setSendName(tf_sendName.getText().trim());
                    m.setSendTelephone(tf_sendTelephone.getText().trim());
                    m.setSendCompary(tf_sendCompany.getText().trim());
                    m.setSendAddress(tf_sendAddress1.getText().trim()+"|"+tf_sendAddress2.getText().trim()+"|"+tf_sendAddress3.getText().trim());
                    m.setSendPostcode(tf_sendPostcode.getText().trim());
                    m.setReceiveName(tf_receiveName.getText().trim());
                    m.setReceiveTelephone(tf_receiveTelephone.getText().trim());
                    m.setReceiveCompary(tf_receiveCompany.getText().trim());
                    m.setReceiveAddress(tf_receiveAddress1.getText().trim()+"|"+tf_receiveAddress2.getText().trim()+"|"+tf_receiveAddress3.getText().trim());
                    m.setReceivePostcode(tf_receivePostcode.getText().trim());
                    buffer.append(tf_sendName.getX()+","+tf_sendName.getY()+"/");
                    buffer.append(tf_sendTelephone.getX()+","+tf_sendTelephone.getY()+"/");
                    buffer.append(tf_sendCompany.getX()+","+tf_sendCompany.getY()+"/");
                    buffer.append(tf_sendAddress1.getX()+","+tf_sendAddress1.getY()+"/");
                    buffer.append(tf_sendAddress2.getX()+","+tf_sendAddress2.getY()+"/");
                    buffer.append(tf_sendAddress3.getX()+","+tf_sendAddress3.getY()+"/");
                    buffer.append(tf_sendPostcode.getX()+","+tf_sendPostcode.getY()+"/");
                    buffer.append(tf_receiveName.getX()+","+tf_receiveName.getY()+"/");
                    buffer.append(tf_receiveTelephone.getX()+","+tf_receiveTelephone.getY()+"/");
                    buffer.append(tf_receiveCompany.getX()+","+tf_receiveCompany.getY()+"/");
                    buffer.append(tf_receiveAddress1.getX()+","+tf_receiveAddress1.getY()+"/");
                    buffer.append(tf_receiveAddress2.getX()+","+tf_receiveAddress2.getY()+"/");
                    buffer.append(tf_receiveAddress3.getX()+","+tf_receiveAddress3.getY()+"/");
                    buffer.append(tf_receivePostcode.getX()+","+tf_receivePostcode.getY());
                    m.setControlPosition(new String(buffer));
                    m.setExpressSize(jPanel.getWidth()+","+jPanel.getHeight());
                    ExpressMessageDao.updateExpress(m);
                    ////
                    queryResultVector = ExpressMessageDao.queryAllExpress();
                    if (queryResultVector!=null){
                        m = (ExpressMessage) queryResultVector.get(queryRow);
                    }
                    ////
            	}
            
            });
        }
        return btn_update;
    }

    /**
     * This method initializes jButton2 
     *  
     * @return javax.swing.JButton  
     */
    private JButton getJButton2() {
        if (jButton2 == null) {
            jButton2 = new JButton();
            jButton2.setText("返    回");
            jButton2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    dispose();
                }
            });
        }
        return jButton2;
    }

    

    
    /**
     * @param owner
     */
    public UpdateExpressFrame() {
        super();
        initialize();
    }
    
    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
    	this.setResizable(false);//不可调整大小
        this.setSize(1017, 575);
        this.setTitle("修改快递信息");
        this.setContentPane(getJContentPane());
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent e) {
                queryResultVector = ExpressMessageDao.queryAllExpress();
                if (queryResultVector!=null){
                    queryRow++;
                    ExpressMessage m = (ExpressMessage) queryResultVector.get(queryRow);
                    showResultValue(m);
                }
            }
        });
    }
    
    private void showResultValue(ExpressMessage m){
        id = m.getId();
        tf_sendName.setText(m.getSendName());
        tf_sendTelephone.setText(m.getSendTelephone());
        tf_sendCompany.setText(m.getSendCompary());
        String addressValue1 = m.getSendAddress();
        tf_sendAddress1.setText(addressValue1.substring(0,addressValue1.indexOf("|")));
        tf_sendAddress2.setText(addressValue1.substring(addressValue1.indexOf("|") + 1,addressValue1.lastIndexOf("|")));
        tf_sendAddress3.setText(addressValue1.substring(addressValue1.lastIndexOf("|") + 1));
        tf_sendPostcode.setText(m.getSendPostcode());
        tf_receiveName.setText(m.getReceiveName());
        tf_receiveTelephone.setText(m.getReceiveTelephone());
        tf_receiveCompany.setText(m.getReceiveCompary());
        String addressValue2 = m.getReceiveAddress();
        tf_receiveAddress1.setText(addressValue2.substring(0,addressValue2.indexOf("|")));
        tf_receiveAddress2.setText(addressValue2.substring(addressValue2.indexOf("|") + 1,addressValue2.lastIndexOf("|")));
        tf_receiveAddress3.setText(addressValue2.substring(addressValue2.lastIndexOf("|") + 1));
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
            url = UpdateExpressFrame.class.getResource("/image/追封快递单.jpg");    // 获得图片的URL
            image=new ImageIcon(url).getImage();                       // 创建图像对象 
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
            //149, 123, 139, 22
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
}
