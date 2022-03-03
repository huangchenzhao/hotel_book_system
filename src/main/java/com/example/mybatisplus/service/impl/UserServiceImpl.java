package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.model.domain.User;
import com.example.mybatisplus.mapper.UserMapper;
import com.example.mybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpSession;

import com.sun.mail.util.MailSSLSocketFactory;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gzx
 * @since 2022-02-27
 */



@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired(required=false)
    private UserMapper userMapper;

    @Override
    public User login(User a) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        /*wrapper.lambda().eq(User::getPassword,a.getPassword())
                .eq(User::getUsername,a.getUsername())
                .eq(User::getUsertype,a.getUsertype());*/
        wrapper.eq("username",a.getUsername())
                .eq("password", a.getPassword())
                .eq("usertype",a.getUsertype());
        return userMapper.selectOne(wrapper);
    }

    @Override
    public String register(HttpSession session,String email,String code,User a) {
        if(a.getUsername()==null||a.getPassword()==null)
        {
            return "用户名和密码不可为空";
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //wrapper.lambda().eq(User::getUsername,a.getUsername());
        wrapper.eq("username",a.getUsername());
        User result = userMapper.selectOne(wrapper);
        if(result!=null){//存在用户则返回空
            return "用户已存在！";
        }else if(verify(session,email,code)==null){//不存在则录入返回登陆信息
            return "邮箱或验证码错误！";
        }else{
            userMapper.insert(a);
            return "注册成功！";
        }
    }

    @Override
    public String sendVerification(HttpSession sessions, String account) throws Exception {
        try {
            Properties prop = new Properties();
            // 开启debug调试，以便在控制台查看
            //prop.setProperty("mail.debug", "true");
            // 设置邮件服务器主机名
            prop.setProperty("mail.host", "smtp.qq.com");
            // 发送服务器需要身份验证
            prop.setProperty("mail.smtp.auth", "true");
            // 发送邮件协议名称
            prop.setProperty("mail.transport.protocol", "smtp");
            // 开启SSL加密，否则会失败
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            prop.put("mail.smtp.ssl.enable", "true");
            prop.put("mail.smtp.ssl.socketFactory", sf);
            // 创建session
            Session session = Session.getInstance(prop);
            // 通过session得到transport对象
            Transport ts = session.getTransport();
            // 连接邮件服务器：邮箱类型，帐号，POP3/SMTP协议授权码 163使用：smtp.163.com
            ts.connect("smtp.qq.com", "1030430640@qq.com", "irvjjfijjszubcai");
            // 创建邮件
            Message message = createMail(session, sessions, account);
            // 发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
        }catch(Exception e){
            return null;
        }
        return "true";
    }

    public static MimeMessage createMail(Session session, HttpSession sessions, String account) throws Exception {
        //  获取6为随机验证码
        try {
            String[] letters = new String[]{
                    "q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m",
                    "A", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M",
                    "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
            String stringBuilder = "";
            for (int i = 0; i < 6; i++) {
                stringBuilder = stringBuilder + letters[(int) Math.floor(Math.random() * letters.length)];
            }
            sessions.setAttribute("email", account);
            sessions.setAttribute("code", stringBuilder);
            // 创建邮件对象
            MimeMessage message = new MimeMessage(session);
            // 指明邮件的发件人
            message.setFrom(new InternetAddress("1030430640@qq.com"));
            // 指明邮件的收件人，发件人和收件人如果是一样的，那就是自己给自己发
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(account));
            // 邮件的标题
            message.setSubject("微旅");
            // 邮件的文本内容
            MimeBodyPart text = new MimeBodyPart();
            String returnText = "<h2>" + account + "，您好！</h3>" + "欢迎使用微旅，您的邮箱验证码为：<br></br>" +
                    "<h3><font color='pink'> " + stringBuilder + "</font></h3>" +
                    "<br></br>如果您不知道为什么收到此消息，可能您的邮箱账号已被他人使用，" +
                    "您可以考虑更改您的账号密码，以确保您的账号安全。<br></br>" +
                    "微旅祝您生活愉快！" + "";//此处留一个string以放入网站url
            //message.setContent(returnText, "text/html;charset=UTF-8");

            text.setContent("<img src='cid:test.jpg' width=60 height=60" + returnText, "text/html;charset=UTF-8");
            // 邮件图片数据
            MimeBodyPart image = new MimeBodyPart();
            DataHandler dh = new DataHandler(new FileDataSource(
                    "D:\\image\\3.png"));
            image.setDataHandler(dh);
            image.setContentID("test.jpg");
            // 描述数据关系
            MimeMultipart mmp = new MimeMultipart();
            mmp.addBodyPart(text);
            mmp.addBodyPart(image);
            mmp.setSubType("relate");

            message.setContent(mmp);
            message.saveChanges();
            //将创建好的邮件写入到E盘以文件的形式进行保存
            //message.writeTo(new FileOutputStream("E:\\ImageMail.eml"));
            // 返回创建好的邮件对象
            return message;
        } catch (Exception e) {
            return null;
        }
    }

    public String verify(HttpSession session, String account, String codeInput) {
        String email = (String) session.getAttribute("email");
        String code = (String) session.getAttribute("code");

//       失败之后重新注册
        if (email == null || email.isEmpty()) {
            return null;
        } else if (!(email.equals(account)&&code.equals(codeInput))) {
            return null;
        }
        System.out.println((account));
        System.out.println(codeInput);
//        保存数据
        return "true";
    }

    @Override
    public User showdetail(Long uid){
        User curruser = userMapper.selectById(uid);
        System.out.println(curruser);
        System.out.println(curruser);
        return curruser;
    }
}
