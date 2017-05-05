# BookStore
这是我刚学完Java web 部分，按照教程视频给的开发文档写的，只用了servlet、jsp、filter等技术，没有使用struts2等框架。
程序入口：在tomcat上发布该程序，访问该程序主页，不登录状态可以浏览商品列表，添加购物车，不能结账，结账时会跳转到登陆界面，如果没登陆点击我的
账户也会跳转到登陆界面，登陆时判定是否为管理员(admin),如果是则跳转到后台管理系统，模拟对仓库的管理，可以对books数据表进行增删改查。
登陆后会生成一个存放用户信息的对象的session，直到程序关闭或用户退出该账户该session才会消失，购物车也是一个session，存放Map<Book,String>,String
存放的是购买的数量加""生成的字符串。

底层数据库：bookstore
  建立四个数据表
  1:图书表：
    CREATE TABLE `books` (
      `id` varchar(100) NOT NULL DEFAULT '',
      `name` varchar(40) DEFAULT NULL,
      `price` double DEFAULT NULL,
      `category` varchar(40) DEFAULT NULL,
      `pnum` int(11) DEFAULT NULL,
      `imgurl` varchar(100) DEFAULT NULL,
      `description` varchar(255) DEFAULT NULL,
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8
   2：用户表：
   CREATE TABLE `user` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `username` varchar(20) DEFAULT NULL,
      `PASSWORD` varchar(20) DEFAULT NULL,
      `gender` varchar(10) DEFAULT NULL,
      `email` varchar(50) DEFAULT NULL,
      `telephone` varchar(20) DEFAULT NULL,
      `introduce` varchar(100) DEFAULT NULL,
      `activeCode` varchar(50) DEFAULT NULL,
      `state` int(11) DEFAULT NULL,
      `role` varchar(10) DEFAULT '普通用户',
      `registTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8
   3：订单表
   CREATE TABLE `orders` (
      `id` varchar(100) NOT NULL DEFAULT '',
      `money` double DEFAULT NULL,
      `receiverAddress` varchar(255) DEFAULT NULL,
      `receiverName` varchar(20) DEFAULT NULL,
      `receiverPhone` varchar(20) DEFAULT NULL,
      `paystate` int(11) DEFAULT NULL,
      `ordertime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      `user_id` int(11) DEFAULT NULL,
      PRIMARY KEY (`id`),
      KEY `user_id` (`user_id`),
      CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8
    4：订单项表
    CREATE TABLE `orderitem` (
        `order_id` varchar(100) NOT NULL DEFAULT '',
        `product_id` varchar(100) NOT NULL DEFAULT '',
        `buynum` int(11) DEFAULT NULL,
        PRIMARY KEY (`order_id`,`product_id`),
        KEY `product_id` (`product_id`),
        CONSTRAINT `orderitem_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
        CONSTRAINT `orderitem_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `books` (`id`)
      ) ENGINE=InnoDB DEFAULT CHARSET=utf8

      
      


   
   
   
   
   
   
   
   
   
   
   
   


  
