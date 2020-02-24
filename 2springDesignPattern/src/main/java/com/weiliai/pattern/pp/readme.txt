原型模式(Prototype Pattern):是指原型实例指定创建对象的种类,并且通过复制这些原型创建新的对象.

适用于:
    1.类初始化消耗资源较多
    2.使用new生成一个对象需要非常繁琐的过程(数据准备,访问权限等)
    3.构造函数比较复杂
    4.在循环体中产生大量对象

例如:
    1.spring中的scope="prototype"
    2.JSON.parseObject()

防止克隆破坏单例两个方法:
    1.不实现Cloneable接口
    2.重写clone,return 单例对象