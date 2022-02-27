### 1.创建基本的springboot项目（或打开导入本项目）
创建项目需Lombok、web、thymeleaf、mybatis依赖
### 2.按照目录结构初始化项目
只需要复制main下的common、test下的CodeGenerator、test下的resources
- BaseController为所有控制层方法的父类，定义了基本的crud方法
- JsonResponse为json返回值
- config/MybatisPlusConfig作用是开启分页器插件
- CodeGenerator为mybatisplus代码生成器，生成domain,mapper,service,controller代码
- mybatis-plus.properties代码生成器所需的参数，根据实际情况调整
- templates下为生成器所生成的代码模板，可做修改
### 3.根据自身环境修改参数
- CodeGenerator中策略配置中，修改需要继承的BaseController的路径（186行左右），
- CodeGenerator中策略配置中，修改生成的表名（若需去前缀，打开去前缀代码）（189行左右）
- mybatis-plus.properties中的各种参数，
- 若能理解templates中各个模板的含义，可做相应修改
### 4.运行生成器，检查代码
### 附：
Mybatis-Plus文档地址：https://mp.baomidou.com/guide/

LayerAdmin文档地址：https://www.layui.com/doc