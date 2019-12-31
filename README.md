# Patient-Data-Manager

## 一、介绍

这是一个管理冠心病患者科研数据的数据管理系统。本系统为研究冠心病的科研人员提供了如下功能：

* 实验室人员管理： 冻结/解封账户，查看操作记录
* 病患数据管理： 数据查询，数据导入
* 实验数据管理： 数据查询，数据导入

## 二、开发环境

* Spring boot
* MongoDB
* Vue.js
* JDK 1.8

## 三、运行

### 线上访问

<a>https://ecnuer996.cn/hospital-data-manager</a>

github仓库地址：<a>https://github.com/DavidLuo216/Patient-Data-Manager</a>

### 本地运行

由于本项目采用前后端分离的开发形式，需要将前端代码的打包文件夹patient-data-manager
放到网络服务器（apache httpd,nginx）的www/html目录下，再运行后端代码打包的jar文件