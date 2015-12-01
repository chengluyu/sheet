## 语句

### 模块语句

#### 模块声明语句

模块声明语句用于声明一个模块，格式如下：

```
module ModuleName
```

没有模块声明语句的模块为匿名模块。

模块声明语句必须放在模块作用域（不包括其子作用域）的开始，在任何语句之前。

#### 引用语句

引用语句用于引用其它模块或者其他模块中的类、函数或变量。格式如下：

```
import OtherModule
```

这样将导入所有在那个模块中导出的对象，如果只想导入指定的几个对象，可以使用`from`子句。

```
import {
	ClassA, FunctionB
} from OtherModule
```

也可以指定引用对象的别名：

```
import {
	ClassA as MyClass,
	FunctionB as MyFunction
} from OtherModule
```

导入语句必须放在作用域的开始，在任何语句之前。

#### 导出语句

模块的内部对全局环境是隐藏的。导出语句用于导出本模块中的类、函数或全局变量。

```
export { ClassA, FunctionB, ConstantC }
```

类似于引用语句，导出语句也可以指定导出的别名：

```
export {
	ClassA as MyClass,
	FunctionB as MyFunction
}
```

导出语句可以放在模块作用域的任何一个位置，但是不能在其子作用域里。

### 函数声明

### 类型声明

#### 类型别名

类型别名为一个类型赋予一个新的名字：

```
type MyInt = int
type Point = (int, int)
type Vector3D = (int, int, int)
```

新类型与原有类型之间可以实现隐式转换。

#### 枚举类型声明

枚举类型分为无类型枚举和有类型枚举。

有类型枚举定义了一组相同类型的常量集合。

```
enum Direction of int {
	Up = 0,
	Down = 1,
	Left = 2,
	Right = 3
}
enum Color of String {
	Red = "red",
	Blue = "blue",
	Green = "green"
}
```

无类型枚举定义了一组符号的集合。

```
enum Token {
	EOF, Number, Operator
}
```

访问枚举类型的值与访问对象成员的方式一样：

```
Direction.Up
Color.Green
Token.EOF
```

#### 类类型声明

```
class TreeNode {
	public TreeNode(int v') {
		lc = rc = null
		v = v'
	}
	
	private int v
	private TreeNode lc, rc
}
```

### 变量和常量声明

### 关键词列表

1. 模块：`module`、`import`、`export`、`as`和`from`
2. 类型声明：`type`、`enum`、`of`、`class`、`public`、`private`、`protected`、