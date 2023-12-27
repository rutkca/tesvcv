package bean;

/**
 * 快递单类
 *
 */
public class ExpressMessage {
	private int id = 0;// 快递单编号
	private String sendName = null;// 发件人姓名
	private String sendTelephone = null;// 发件人电话
	private String sendCompary = null;// 发件人公司
	private String sendAddress = null;// 发件人地址
	private String sendPostcode = null;// 发送人邮编
	private String receiveName = null;// 收件人名称
	private String receiveTelephone = null;// 收件人电话
	private String receiveCompary = null;// 收件人公司
	private String receiveAddress = null;// 收件人地址
	private String receivePostcode = null;// 收件人邮编
	private String ControlPosition = null; // 快递单上添加信息的组件位置（坐标）
	private String expressSize = null; // 快递单的尺寸（大小）

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public String getSendTelephone() {
		return sendTelephone;
	}

	public void setSendTelephone(String sendTelephone) {
		this.sendTelephone = sendTelephone;
	}

	public String getSendCompary() {
		return sendCompary;
	}

	public void setSendCompary(String sendCompary) {
		this.sendCompary = sendCompary;
	}

	public String getSendAddress() {
		return sendAddress;
	}

	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}

	public String getSendPostcode() {
		return sendPostcode;
	}

	public void setSendPostcode(String sendPostcode) {
		this.sendPostcode = sendPostcode;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getReceiveTelephone() {
		return receiveTelephone;
	}

	public void setReceiveTelephone(String recieveTelephone) {
		this.receiveTelephone = recieveTelephone;
	}

	public String getReceiveCompary() {
		return receiveCompary;
	}

	public void setReceiveCompary(String recieveCompary) {
		this.receiveCompary = recieveCompary;
	}

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public String getReceivePostcode() {
		return receivePostcode;
	}

	public void setReceivePostcode(String receivePostcode) {
		this.receivePostcode = receivePostcode;
	}

	public String getControlPosition() {
		return ControlPosition;
	}

	public void setControlPosition(String controlPosition) {
		ControlPosition = controlPosition;
	}

	public String getExpressSize() {
		return expressSize;
	}

	public void setExpressSize(String expressSize) {
		this.expressSize = expressSize;
	}
}
