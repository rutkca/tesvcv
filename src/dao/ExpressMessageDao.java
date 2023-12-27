package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import bean.ExpressMessage;

/**
 * 快递单业务持久化实现类
 */
public class ExpressMessageDao {
	/**
	 * 添加快递单信息
	 * 
	 * @param m
	 *            -快递单对象
	 */
	public static void insertExpress(ExpressMessage m) {
		// 信息为空校验
		if (m.getSendName() == null || m.getSendName().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "寄件人信息必须填写。");
			return;
		}
		if (m.getSendTelephone() == null
				|| m.getSendTelephone().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "寄件人信息必须填写。");
			return;
		}
		if (m.getSendCompary() == null || m.getSendCompary().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "寄件人信息必须填写。");
			return;
		}
		if (m.getSendAddress() == null
				|| m.getSendAddress().trim().equals("||")) {
			JOptionPane.showMessageDialog(null, "寄件人信息必须填写。");
			return;
		}
		if (m.getSendPostcode() == null
				|| m.getSendPostcode().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "寄件人信息必须填写。");
			return;
		}

		if (m.getReceiveName() == null || m.getReceiveName().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "收件人信息必须填写。");
			return;
		}
		if (m.getReceiveTelephone() == null
				|| m.getReceiveTelephone().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "收件人信息必须填写。");
			return;
		}
		if (m.getReceiveCompary() == null
				|| m.getReceiveCompary().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "收件人信息必须填写。");
			return;
		}
		if (m.getReceiveAddress() == null
				|| m.getReceiveAddress().trim().equals("||")) {
			JOptionPane.showMessageDialog(null, "收件人信息必须填写。");
			return;
		}
		if (m.getReceivePostcode() == null
				|| m.getReceivePostcode().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "收件人信息必须填写。");
			return;
		}
		Connection conn = null; // 声明数据库连接
		// 声明PreparedStatement对象
		PreparedStatement ps = null;
		try {
			conn = DAO.getConn(); // 获得数据库连接
			// 创建PreparedStatement对象，并传递SQL语句
			ps = conn
					.prepareStatement("insert into tb_receiveSendMessage (sendName,sendTelephone,sendCompary,sendAddress,sendPostcode,receiveName,recieveTelephone,recieveCompary,receiveAddress,receivePostcode,ControlPosition,expressSize)  values(?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, m.getSendName()); // 为sql中的参数赋值
			ps.setString(2, m.getSendTelephone());
			ps.setString(3, m.getSendCompary());
			ps.setString(4, m.getSendAddress());
			ps.setString(5, m.getSendPostcode());
			ps.setString(6, m.getReceiveName());
			ps.setString(7, m.getReceiveTelephone());
			ps.setString(8, m.getReceiveCompary());
			ps.setString(9, m.getReceiveAddress());
			ps.setString(10, m.getReceivePostcode());
			ps.setString(11, m.getControlPosition());
			ps.setString(12, m.getExpressSize());
			int flag = ps.executeUpdate();// 获取sql执行结果
			if (flag > 0) {// 如果有至少一行数据插入成功
				JOptionPane.showMessageDialog(null, "添加成功。");
			} else {
				JOptionPane.showMessageDialog(null, "添加失败。");
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "添加失败！");
			ex.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 修改快递单信息
	 * 
	 * @param m
	 */
	public static void updateExpress(ExpressMessage m) {
		Connection conn = null; // 声明数据库连接
		// 声明PreparedStatement对象
		PreparedStatement ps = null;
		try {
			conn = DAO.getConn(); // 获得数据库连接
			// 创建PreparedStatement对象，并传递SQL语句
			ps = conn
					.prepareStatement("update tb_receiveSendMessage set sendName=?,sendTelephone=?,sendCompary=?,sendAddress=?,sendPostcode=?,receiveName=?,recieveTelephone=?,recieveCompary=?,receiveAddress=?,receivePostcode=?,ControlPosition=?,expressSize=? where id = ?");
			ps.setString(1, m.getSendName()); // 为参数赋值
			ps.setString(2, m.getSendTelephone());
			ps.setString(3, m.getSendCompary());
			ps.setString(4, m.getSendAddress());
			ps.setString(5, m.getSendPostcode());
			ps.setString(6, m.getReceiveName()); // 为参数赋值
			ps.setString(7, m.getReceiveTelephone());
			ps.setString(8, m.getReceiveCompary());
			ps.setString(9, m.getReceiveAddress());
			ps.setString(10, m.getReceivePostcode());
			ps.setString(11, m.getControlPosition()); // 为参数赋值
			ps.setString(12, m.getExpressSize());
			ps.setInt(13, m.getId());
			int flag = ps.executeUpdate();// 获取sql执行结果
			if (flag > 0) {// 如果有至少一行数据修改成功
				JOptionPane.showMessageDialog(null, "修改成功。");
			} else {
				JOptionPane.showMessageDialog(null, "修改失败。");
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "修改失败！" + ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取所有的快递单数据
	 * 
	 * @return -快递单数据Vector数组
	 */
	public static Vector queryAllExpress() {
		Connection conn = null; // 声明数据库连接
		// 声明PreparedStatement对象
		PreparedStatement ps = null;
		try {
			conn = DAO.getConn(); // 获得数据库连接
			// 创建PreparedStatement对象，并传递SQL语句
			ps = conn
					.prepareStatement("select id,sendName,sendTelephone,sendCompary,sendAddress,sendPostcode,receiveName,recieveTelephone,recieveCompary,receiveAddress,receivePostcode,ControlPosition,expressSize from tb_receiveSendMessage");
			ResultSet rs = ps.executeQuery();
			Vector<ExpressMessage> v = new Vector<ExpressMessage>();// 创建动态数组
			while (rs.next()) {
				ExpressMessage m = new ExpressMessage();
				// 给快递对象属性赋值
				m.setId(rs.getInt(1));
				m.setSendName(rs.getString(2));
				m.setSendTelephone(rs.getString(3));
				m.setSendCompary(rs.getString(4));
				m.setSendAddress(rs.getString(5));
				m.setSendPostcode(rs.getString(6));
				m.setReceiveName(rs.getString(7));
				m.setReceiveTelephone(rs.getString(8));
				m.setReceiveCompary(rs.getString(9));
				m.setReceiveAddress(rs.getString(10));
				m.setReceivePostcode(rs.getString(11));
				m.setControlPosition(rs.getString(12));
				m.setExpressSize(rs.getString(13));
				v.add(m);// 数组中添加快递单
			}
			return v;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
