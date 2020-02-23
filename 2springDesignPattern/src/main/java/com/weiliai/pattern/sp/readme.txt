单例模式(Singleton Pattern)是指确保一个类在任何情况下都绝对只有一个实例,并提供一个全局的访问点,单例模式是创建型模式

例如:
    1.J2EE标准中的ServletContext/ServletContextConfig等
    2.Spring框架中的ApplicationContext,数据库连接池等

单例模式可以保证内存中只有一个实例,减少内存开销,还可以避免资源的多重占用.