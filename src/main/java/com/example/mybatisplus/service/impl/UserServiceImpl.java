package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.mapper.HotelMapper;
import com.example.mybatisplus.mapper.RoomMapper;
import com.example.mybatisplus.mapper.UserorderMapper;
import com.example.mybatisplus.model.domain.*;
import com.example.mybatisplus.mapper.UserMapper;
import com.example.mybatisplus.service.RoomService;
import com.example.mybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.List;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalTime;

import com.sun.mail.util.MailSSLSocketFactory;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author gzx
 * @since 2022-02-27
 */


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired(required = false)
    private RoomService roomService;
    @Autowired(required = false)
    private RoomMapper roomMapper;
    @Autowired(required = false)
    private HotelMapper hotelMapper;
    @Autowired(required = false)
    private UserorderMapper userorderMapper;

    @Override
    public User login(User a) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        /*wrapper.lambda().eq(User::getPassword,a.getPassword())
                .eq(User::getUsername,a.getUsername())
                .eq(User::getUsertype,a.getUsertype());*/
        wrapper.eq("username", a.getUsername())
                .eq("password", a.getPassword())
                .eq("usertype", a.getUsertype());
        return userMapper.selectOne(wrapper);
    }

    @Override
    public String register(HttpSession session, String code, User a) {
        if (a.getUsername() == null || a.getPassword() == null ||
                a.getUsername().equals("") || a.getPassword().equals("")) {
            return "用户名和密码不可为空";
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //wrapper.lambda().eq(User::getUsername,a.getUsername());
        wrapper.eq("username", a.getUsername());
        User result = userMapper.selectOne(wrapper);
        QueryWrapper<User> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("mail", a.getMail());
        User result1 = userMapper.selectOne(wrapper1);
        System.out.println("result:" + result);
        System.out.println("result:1" + result1);
        System.out.println(a);
        if (result != null) {//存在用户则返回空
            return "用户已存在！";
        } else if (result1 != null) {
            return "邮箱已注册！";
        } else if (verify(session, a.getMail(), code) == null) {//不存在则录入返回登陆信息
            return "邮箱或验证码错误！";
        } else {
            //设置默认头像
            a.setPhotoUrl("./image/202203/-352ab266285b4dd28e22dafe5c1b2e9f.jpeg");
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
            ts.connect("smtp.qq.com", "2468038761@qq.com", "bngxuyganfkbdijj");
            // 创建邮件
            Message message = createMail(session, sessions, account);
            // 发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
        } catch (Exception e) {
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
            message.setFrom(new InternetAddress("2468038761@qq.com"));
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
        System.out.println(email);
        System.out.println(code);
//       失败之后重新注册
        if (email == null || email.isEmpty()) {
            return null;
        } else if (!(email.equals(account) && code.equals(codeInput))) {
            return null;
        }
        System.out.println((account));
        System.out.println(codeInput);
//        保存数据
        return "验证成功";
    }

    @Override
    public User showdetail(Long uid) {
        User curruser = userMapper.selectById(uid);
        System.out.println(curruser);
        System.out.println(curruser);
        return curruser;
    }

    @Override
    public void newPassword(Long uid, String newpaw) {
        userMapper.newPassword(uid, newpaw);
    }

    @Override
    public String forgetPassword(HttpSession request, String mail, String password, String code) {

        try {
            if (verify(request, mail, code) == null) {
                return "验证码或邮箱错误";
            }
            userMapper.updatePassword(mail, password);
        } catch (Exception e) {
            return "找回失败";
        }
        return "找回成功";
    }

    @Override
    public String sendMail(HttpSession session, String account) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("mail", account);
        if (userMapper.selectOne(wrapper) == null) {
            return "邮箱未注册";
        }
        try {
            sendVerification(session, account);
        } catch (Exception e) {
            return "邮件发送失败";
        }
        return "邮件发送成功请注意查收";
    }

    @Override
    public String addUser(User user) {
        try {

            if (userMapper.updateUser(user) == false) {
                QueryWrapper<User> wrapper = new QueryWrapper<>();
                wrapper.eq("mail", user.getMail());
                QueryWrapper<User> wrapper2 = new QueryWrapper<>();
                wrapper2.eq("username", user.getUsername());
                try {
                    if (userMapper.selectOne(wrapper) != null) {
                        return "邮箱已存在";
                    }
                    if (userMapper.selectOne(wrapper2) != null) {
                        return "用户名已存在";
                    }
                } catch (Exception e) {
                    return "数据库错误";
                }
                //添加用户默认头像
                user.setPhotoUrl("./image/202203/-352ab266285b4dd28e22dafe5c1b2e9f.jpeg");
                user.setUsertype(1);
                saveOrUpdate(user);
            }
        } catch (Exception e) {
            return ("添加失败");
        }
        return "添加成功";
    }


    @Override
    public List<User> getalluser() {
        List<User> allUsers = userMapper.getalluser();
        return allUsers;
    }

    @Override
    public void newPass(Long uid, String pass) {
        userMapper.newPass(uid, pass);
    }

    @Override
    public Map<String, List<Float>> salesByWeek() {
        Calendar begin = Calendar.getInstance();// 得到一个Calendar的实例
        begin.setTime(java.util.Date.from((LocalDate.now()).atStartOfDay(ZoneOffset.ofHours(8)).toInstant())); // 设置时间为当前时间
        begin.add(Calendar.DATE, -7);// 日期加-7
        Calendar end = Calendar.getInstance();
        end.setTime(java.util.Date.from((LocalDate.now()).atStartOfDay(ZoneOffset.ofHours(8)).toInstant()));
        //获得起止时间
        //查询数据
        Set<String> hotelDataSet = new HashSet<>();
        List<Userorder> userorder = userorderMapper.salesByWeek(new Date(begin.getTimeInMillis()),new Date(end.getTimeInMillis()));
        for(int i=0;i<userorder.size();i++){
            hotelDataSet.add(userorder.get(i).getHotelName());
        }
        Map<String, List<Float>> hotelDataMap=new HashMap<>();
        for(String name:hotelDataSet){
            Map<LocalDate, Float> dayprice=new HashMap<>();
            for(int i=0;i<7;i++){
                dayprice.put(((new Date(begin.getTimeInMillis()+i*1000 * 60 * 60 * 24L)).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()),0.0F);
            }
            for(int i=0;i<userorder.size();i++){
                if(Objects.equals(userorder.get(i).getHotelName(), name)){
                    dayprice.remove(userorder.get(i).getSalesTime());
                    dayprice.put(userorder.get(i).getSalesTime(),userorder.get(i).getTotalprice());
                }
            }
            List<Float> priceList=new ArrayList<>();
            for(int i=0;i<7;i++){
                priceList.add(dayprice.get((new Date(begin.getTimeInMillis()+i*1000 * 60 * 60 * 24L)).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
            }
            hotelDataMap.put(name,priceList);
        }
        return hotelDataMap;
    }

    @Override
    public String updatePrice(Long rId, Float price) {
        QueryWrapper<Room> wrapper = new QueryWrapper<>();
        wrapper.eq("r_id", rId);
        Room room = roomMapper.selectOne(wrapper);
        if (room == null) {
            return "房间不存在";
        }
        try {
            room.setPrice(price);
            roomMapper.update(room, wrapper);
        } catch (Exception e) {
            return "房间价格更新失败";
        }
        return "房间价格更新成功";
    }

}
