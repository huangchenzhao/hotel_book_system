# Team15Hotel_Book_System

#### 介绍
本项目是四川大学软件学院实训项目：酒店辅助订购系统，欢迎fork和star。

项目演示地址：http://122.9.143.17:8888/index.html

管理员账户：admin

管理员密码：admin

可使用的用户账户：1

可使用的用户密码：1

可以自行注册用户的账号来使用酒店辅助订购系统

#### 项目结构
本项目是前后端分离项目

后端框架为：SpringBoot + MyBatis-Plus + Mysql

前端框架为：Vue + Element UI + Echarts UI + BaiduMap api

#### 使用教程
- 本项目编译器为idea，后端源码在back分支中，前端源码在front分支中
- git clone -b back https://github.com/huangchenzhao/hotel_book_system.git
- git clone -b front https://github.com/huangchenzhao/hotel_book_system.git
- 以上两个命令分别拷贝后端源码和前端源码

#### 功能
1. 两种角色，用户和管理员
<img width="454" alt="image" src="https://user-images.githubusercontent.com/69411580/158507320-e5a99817-07c4-4686-a02b-d0801907fcad.png">
2. 首页
<img width="454" alt="image" src="https://user-images.githubusercontent.com/69411580/158508483-0800dc41-b247-4696-a131-8bf3e516581a.png">
3. 酒店搜索结果
<img width="454" alt="image" src="https://user-images.githubusercontent.com/69411580/158509411-9e5118cd-da4e-4d73-a610-198344fe37a6.png">
4. 酒店详情
<img width="454" alt="image" src="https://user-images.githubusercontent.com/69411580/158509444-a7e277c4-a63d-4bbc-ba61-a16be97d18db.png">
5. 用户下单
<img width="453" alt="image" src="https://user-images.githubusercontent.com/69411580/158509480-9eb7a23a-e821-41c8-9328-3c7cf5af358e.png">
6. 账户信息
<img width="454" alt="image" src="https://user-images.githubusercontent.com/69411580/158509825-fb77e152-242d-4298-9335-87660d5a1baf.png">
7. 我的订单
<img width="454" alt="image" src="https://user-images.githubusercontent.com/69411580/158509855-53b5bc0d-e9b5-4d9a-a1c4-8f5c1789831b.png">
8. 管理员查看酒店
<img width="453" alt="image" src="https://user-images.githubusercontent.com/69411580/158509900-a4e0e872-e2e3-47e5-9e89-f781813c50eb.png">
9. 订单分析
<img width="454" alt="image" src="https://user-images.githubusercontent.com/69411580/158509946-e5d3e4a8-48cc-43c5-829a-eebca07cea89.png">


#### 使用说明
##### 后端配置
1.  拉取后端代码后，首先配置mysql数据库，注意，mysql的版本最好为5.6，若mysql为5.7及以上版本，请按链接网站中配置mysql的sql模式。https://stackoverflow.com/questions/41887460/select-list-is-not-in-group-by-clause-and-contains-nonaggregated-column-inc
2.  在mysql中导入back分支中hotelbook.sql。这个是项目运行所依赖的数据库文件。随后配置后端源码中的application.yml文件，更改数据库连接的url、用户名和密码
3.  修改UserServiceImpl中createMail方法的DataHandler dh = new DataHandler(new FileDataSource("/home/hcz/file/image/202203/-02a12b8d264c4616af8bb516a140ec6e.jpg"));这一行语句中图片的url，只有配置了这一句话才能保证发送邮箱验证码功能成功运行
##### 前端配置
1.  拉取前端代码后，在控制台执行npm install命令下载所需的包。修改config目录下index.js文件，配置前端端口和与其匹配的后端端口。这里已经写好了，后端运行端口为8888，前端运行端口为3000
##### 运行
1.  运行前后端项目
2.  浏览器输入127.0.0.1:3000。即可访问酒店辅助订购系统

#### 参与贡献

- 姬茗瑞
  - github id: roooo-Jming
- 黄晨钊
  - github id: huangchenzhao
- 岳平勇
- 谷子昕 
  - github id: guzixin
- 感谢大家的付出，由于此github项目由gitlab的url导入，因此contributors的显示会有问题，显示一个空白的账户，特将所有贡献者的github id标明

#### 说明
本项目仅为大学本科期间的实训项目，因为时间仓促，并没有进行算法优化。开源仅供学习交流，如有问题请联系18292166117@163.com
