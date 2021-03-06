装饰者模式(Decorator Pattern)是指在不改变原来对象的基础上,将功能附加到对象上,提供了比继承更有弹性的方案(扩展原有对象的功能),属于结构型模式

装饰者模式适用场景:
    1.扩展一个类的功能或给一个类添加附加职责
    2.动态给一个对象添加功能,这些功能可以在动态的撤销

装饰者模式和适配器模式都是包装模式(Wrapper Pattern),装饰者模式是一种特殊的代理模式

装饰者模式的优点:
    1.装饰者模式是继承的有力补充,且比继承灵活,可以在不改变原有对象的情况下动态地给一个对象扩展,即插即用
    2.使用不同的装饰类及这些类的排列组合,可以实现不同的效果
    3.装饰者模式完全符合开闭原则
装饰者模式的缺点:
    1.会出现更多的代码,更多的类,增加程序的复杂性
    2.动态装饰时,多层装饰会更复杂