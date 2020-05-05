# Online-Cat-Supplies-Shop
Demo project of online cat supplies shop (Servlet+JDBC+Hibernate)  
(Images included were for in classroom demo purpose only and not for other use)

資策會期中小專的 DEMO 作品  
基於 JSP + Servlet + JDBC + Hibernate 所製作  
<這個作品會維持半成品樣貌並不再更新>  
  
功能:  
1. 使用者註冊與修改資料 (含簡易 Email 認證)  
2. 購物車 (寫在 cookies, 訪客與登入使用者分開處理)  
3. 訂單 (付款後會寄 Email 通知管理者)  
4. 付款 (使用綠界 ECPay API)  
5. 意見回饋 (會寄 Email 給管理者)  
6. 簡易後台  
  
設定  
_01_.account.register.controller.CheckAccountAvailabilityServlet.java 須設定 JDBC SQL 密碼  
_01_.account.register.controller.SendVerifyEmail.java 須設定系統 SMTP Email 帳號/密碼  
_02_.account.login.controller.SendPwdResetMail.java 須設定系統 SMTP Email 帳號/密碼  
_05_.getHelp.controller.SendGetHelpMail.java 須設定系統 SMTP Email 帳號/密碼、管理者收件 Email Address  
_09_.payment.controller.SendPaidMail 須設定系統 SMTP Email 帳號/密碼、管理者收件 Email Address  
src/hibernate.cfg.xml 設定 SQL 密碼  
另外需要在 Tomcat 9 conf/context.xml 加入 JDBC resources:  
<Resource   
	auth="Container"  
	driverClassName="com.microsoft.sqlserver.jdbc.SQLServerDriver"  
	name="jndiJdbcConnSQLServer/ContainerLogin"  
	password=""     <!-- SQL 密碼 -->  
	type="javax.sql.DataSource"   
	url="jdbc:sqlserver://localhost:1433;databaseName=LeonPower"   
	username="SA"/>  
  
簡易後台系統(半成品)  
啟動後須於 http://localhost:xxxx/Mid_Project/BackEnd/insert_product.jsp 加入產品資訊  
http://localhost:xxxx/Mid_Project/BackEnd/doSolution.jsp 可以查詢意見回饋  

