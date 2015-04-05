## ucc-zookeeper:
基于zookeeper的统一配置中心实现

## 项目说明
由agent和console组成

    agent:作为基础jar包，被接入程序使用
    console:后台运营、关联、配置的地方

### agent接入说明:
提供三种接入方式

##### 方式一：
控制台配置方案，客户端自动加载。


##### 方式二：
通过agent的方式：

1、配置注解：

    @ZkTypeConfigurable(servers = "localhost:2181",useOwnServer = true,path = "/conf/test/demo")
    public class Requirements {
        @ZkFieldConfigurable(path = "name",update = true)
        public static String name = "default_name";
    
        @ZkFieldConfigurable(path = "cpu",update = true,resolver = CpuResolver.class)
        public static Cpu cpu = new Cpu("default","default","default");
    }
2、修改jvm启动参数加上：    

    -javaagent:cuc-agent-1.0-SNAPSHOT.jar 


###### 扩展方式
除以上的启动但是外，还支持一下两种agent方式：

1、启动参数
    
    -javaagent:agent.jar=zk@localhost:2181#class@com.jdpay.ucc.Demo
    
2、jvm环境变量

    -javaagent:agent.jar -DZK=localhost:2181 -DCLASS=c1,c2,c3


### console配置使用说明:

##### zk节点管理
    
1、更新，删除节点
    
![DataV logo](https://github.com/cncduLee/zk-ucc/blob/master/doc/zk-update.gif)

2、创建新节点
    
![DataV logo](https://github.com/cncduLee/zk-ucc/blob/master/doc/zk-create-gif)

##### 配置任务管理

    TODO
    
    

## TO LIST
  
    1、配置管理console部分的开发
    2、配置管理的agent部分开发
    3、使用扩展配置的支持开发（配置信息支持扩展存储，如redis，memcached等）
  
  