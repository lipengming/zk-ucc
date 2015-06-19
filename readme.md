## ucc-zookeeper:
基于zookeeper的统一配置中心实现

## 项目说明
由agent和console组成

    agent:作为基础jar包，被接入程序使用
    console:后台运营、管理、配置的地方

##如何使用统一配置呢？
   1、Annotaion 这无疑是最简洁、优雅的方式        
   2、控制台配置      
   3、java原生    
   
## 项目如何接入统一配置？
   主要通过三种方式：这三种都需要添加相关的mvn依赖（jar包），
   然后就可以在你的项目使用Annotation了
   
   1、spring接入   
   2、修改启动脚本   
   3、在控制台配置相关信息   
       
### spring接入说明:
##### 第一步
    
    添加mvn依赖：
    
    <dependency>
        <groupId>com.bitium10.ucc</groupId>
        <artifactId>ucc-spring</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
    
##### 第二步
    
    配置xml方式：
    
            <!--annotation扫描-->
            <context:component-scan base-package="com.bitium10.ucc.spring.usage" />
            <context:annotation-config/>
        
            <!--声明支持扩展的annotation配置-->
            <bean class="com.bitium10.ucc.spring.ConfigAnnotationBeanPostProcessor">
                <constructor-arg index="0" value="${zk.servers}"/>
                <constructor-arg index="1" value="${zk.forceWhenNull}"/>
            </bean>
    
##### 第三步
    spring接入实例   
    
    @Service("kw")
    @ZkTypeConfigurable(useOwnServer = false,path = "/conf/test/demo")
    public class KeyWord implements Config {
        @ZkExtendConfigurable(path = "keyWords",update = true,tempKey = "key_words",dataStroe = CacheDataStore.class)
        public static Map<String,String> config = new HashMap<String, String>(0);
    
        @Override
        public void print() {
            System.out.println(config);
        }
    }



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


###### 方式三：
除以上的启动但是外，还支持一下两种agent方式：

1、启动参数
    
    -javaagent:agent.jar=zk@localhost:2181#class@com.bitium10.ucc.Demo
    
2、jvm环境变量

    -javaagent:agent.jar -DZK=localhost:2181 -DCLASS=c1,c2,c3

### 扩展配置接入说明
    1、定义扩展配置存储方案
    
    实现ExtendDataStore接口即可。例如：
    
    public class CacheDataStore implements ExtendDataStore<Map<String,String>> {
        public final static Map<String,Map<String,String>> _CACHE = new HashMap<String, Map<String,String>>(10);
        static {
            //init value
            Map<String,String> map = new HashMap<String, String>(4);
            map.put("d1","d1");
            map.put("d2","d2");
            map.put("d3","d3");
            map.put("d4","d4");
            _CACHE.put("key_words",map);
    
            Map<String,String> map1 = new HashMap<String, String>(4);
            map1.put("a1","a1");
            map1.put("a2","a2");
            map1.put("a3","a3");
            map1.put("a4","a4");
            _CACHE.put("key_words1",map1);
    
            Map<String,String> map2= new HashMap<String, String>(4);
            map2.put("咚咚1","咚咚1");
            map2.put("咚咚2","咚咚2");
            map2.put("咚咚3","咚咚3");
            map2.put("咚咚4","咚咚4");
            _CACHE.put("key_words2",map2);
        }
    
        @Override
        public void setValue(String key, Map<String, String> map) {
            _CACHE.put(key,map);
        }
    
        @Override
        public Map<String, String> getValue(String key) {
            return _CACHE.get(key);
        }
    }
    
    说明：Map<String,String>为扩展配置字段的类型
    
    2、对扩展配置的字段注解
    
    @ZkTypeConfigurable(useOwnServer = false,path = "/conf/test/demo")
    public class KeyWord {
        @ZkExtendConfigurable(path = "keyWords",update = true,tempKey = "key_words",dataStroe = CacheDataStore.class)
        public static Map<String,String> config = new HashMap<String, String>(0);
    
    }
    
### console配置使用说明:

##### zk节点管理
    
1、更新，删除节点
    
![DataV logo](https://github.com/cncduLee/zk-ucc/blob/master/doc/zk-update.gif)

2、创建新节点
    
![DataV logo](https://github.com/cncduLee/zk-ucc/blob/master/doc/zk-create.gif)

##### 配置任务管理

    TODO
    
## TO LIST
    
    一、配置管理
    
        1、配置管理console部分的开发
        2、配置管理的agent部分开发
            
    二、使用doc完善
        
    三、流程管理，项目管理
    
    四、细节调优
    
    五、异常方案
    
    六、性能测试
        
  