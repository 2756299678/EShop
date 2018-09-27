# Eshop
[功能描述]：
电子商城管理平台是一个对商品进行网上售卖的管理平台，分为前台和后台两个方面，
后台负责对商品，商品信息，管理员信息进行全方位的管理，也是这个平台的主要部分，
前台负责对商品进行出售，记账，生成购物清单的内容。

[开发环境]：
系统：Windows
环境配置：Eclipes+Tomcat+mysql

[项目结构简介]：

src/com/oracle/jsp/bean       存放实体类
src/com/oracle/jsp/dao        存放方法类
src/com/oracle/jsp/filter     存放过滤器
src/com/oracle/jsp/servlet/admin      后台的Servlet
src/com/oracle/jsp/servlet/front      前台的Servlet
src/com/oracle/jsp/tag        分页实现类
src/com/oracle/jsp/util       工具类

[程序思路]：
程序的流程并不懂，通过对电子商务管理平台的实现，进行javaWeb的系统性的学习。
系统整体上采用MVC分层的形式，
前端将填写数据传到servlet层，servlet层调用Dao层的方法，Dao方法引用数据库和实体的内容。

[作者列表]：
Lighter


[历史版本]：
1.0版本（2017-7-4）

[联系方式]：
QQ:2756299678
