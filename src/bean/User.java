package bean;

public class User {
	private String name = null;// 用户名
	private String pwd = null;// 用户密码
	private String okPwd = null;// 用户确认的密码

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setOkPwd(String okPwd) {
		this.okPwd = okPwd;
	}

	public String getOkPwd() {
		return okPwd;
	}

}
