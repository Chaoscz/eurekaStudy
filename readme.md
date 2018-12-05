2018-11-27

#eureka server 高可用

3个server实例相互注册


2018-12-05

#eureka client ribbon

##ribbon 负载均衡器

###核心

服务发现

服务选择规则

服务监听

###主要组件
ServerList

IRule

ServerListFilter


##RestTemplate
    
    第一种方式 直接使用 RestTemplate url 写死
    
    第二种方式 利用loadBalancerClient spring.application.name 获取 信息 在使用RestTemplate
    
    第三种方式 创建config 并使用@LoadBalanced 创建 RestTemplate 的bean

##Feign
声明式REST客户端

采用了基于接口的注解


##Zuul



