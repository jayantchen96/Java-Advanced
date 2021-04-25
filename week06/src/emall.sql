# 6.（必做）基于电商交易场景（用户、商品、订单），设计一套简单的表结构，提交 DDL 的 SQL 文件到 Github

# 创建数据库
create database if not exists emall;
# 使用数据库
use emall;
# 创建用户表
create table `user` (
                        `id` int auto_increment not null comment '用户ID',
                        `username` varchar(32) not null comment '用户名',
                        `password` varchar(32) not null comment '密码',
                        `nickname` varchar(32) comment '昵称',
                        `idCard` varchar(17) unique not null comment '身份证',
                        primary key (`id`)
)engine=Innodb default charset=utf8;

# 创建商品表
create table `product` (
                           `id` int auto_increment not null comment '商品ID',
                           `name` varchar(32) not null comment '商品名',
                           `category` varchar(32) comment '商品类别',
                           `weight` decimal(16, 2) unique not null comment '商品重量',
                           primary key (`id`)
)engine=Innodb default charset=utf8;

# 创建订单表
create table `order` (
                         `id` int not null comment '订单ID',
                         `userId` int not null comment '用户ID',
                         `amount` decimal(16, 2) comment '金额',
                         `status` varchar(10) unique not null comment '订单状态',
                         primary key (`id`),
                         foreign key (`userId`) references user(`id`)
)engine=Innodb default charset=utf8;