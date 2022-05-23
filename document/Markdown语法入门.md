## 说明

Markdown是一种轻量级标记语言，排版语法简洁，让人们更多地关注内容本身而非排版。它使用易读易写的纯文本格式编写文档，可与HTML混编，可导出 HTML、PDF 以及本身的 .md 格式的文件。因简洁、高效、易读、易写，Markdown被大量使用，如Github、Wikipedia、简书等。Markdown的语法十分简单，常用的标记符号不超过十个，用于日常写作记录绰绰有余，不到半小时就能完全掌握。

### 一、基本符号：* - +. >

基本上所有的markdown标记都是基于这四个符号或组合，需要注意的是，如果以基本符号开头的标记，注意基本符号后有一个用于分割标记符和内容的空格。

### 二、标题

1. 前面带#号，后面带文字，分别表示h1-h6,只到h6，而且h1下面会有一条横线

```markdown
# 一级标题
## 二级标题
### 三级标题
#### 四级标题
##### 五级标题
###### 六级标题
```

2. 相当于标签闭合

```markdown
# 一级标题 #
## 二级标题 ##
### 三级标题 ###
#### 四级标题 ####
##### 五级标题 #####
###### 六级标题 #####
```

### 三、列表

1. 无序列表

```markdown
//形式一
+ a
+ b
+ c
//形式二
- d
- e
- f
//形式三
* g
* h
* i
```

以上三种形式，效果其实都是一样的

2. 有序列表

```markdown
//正常形式
1. abc
2. bcd
3. cde
//错序效果
2. fgh
3. ghi
5. hij
```

> 注意，数字后面的点只能是英文的点，有序列表的序号是根据第一行列表的数字顺序来的，错序列表的序号本来是序号是乱的， 但是还是显示 2 3 5

3. 嵌套列表

```markdown
//无序列表嵌套
+ 123
    + abc
    + bcd
    + cde
+ 465
+ 789
//有序列表嵌套
1. abcd
    1. abcde
    2. abcde
    3. abcde
2. bcde
3. cdef
```

> 列表可以嵌套，使用时在嵌套列表前按 tab 或 空格 来缩进,去控制列表的层数

### 四、引用说明区块

对某个部分做的内容做一些说明或者引用某某的话等，可以用这个语法。

1. 正常形式

```markdown
> 引用内容、说明内容。在语句前面加一个 > ，注意是英文的那个右尖括号，注意空格，引用因为是一个区块，理论上是应该什么内容都可以放，比如说：标题，列表，引用等等。
```

2. 嵌套区块

```markdown
> 一级引用
>> 二级引用
>>> 三级引用
>>>> 四级引用
>>>>> 五级引用
>>>>>> 六级引用
```

### 五、代码块

在发布一些技术文章会涉及展示代码的问题，这时候代码块就显得尤为重要。

1. 少量代码，单行使用，直接用`包裹起来就行了

```markdown
` shaoliangdaima,danhangshiyong `
```

2. 大量代码，需要多行使用，用```包裹起来

```markdown
    ```
        daliangdaima,xuyaoduohangshiyong
        daliangdaima,xuyaoduohangshiyong
        daliangdaima,xuyaoduohangshiyong
        daliangdaima,xuyaoduohangshiyong
        daliangdaima,xuyaoduohangshiyong
    ```
```

### 六、链接

1. 行内式

链接的文字放在[]中，链接地址放在随后的()中，链接也可以带title属性，链接地址后面空一格，然后用引号引起来

```markdown
[百度](https://www.baidu.com "百度一下，你就知道"),
是一个互联网搜索引擎综合服务提供商，提供搜索引擎、地图、百科、贴吧、金融等服务，并且支持云计算和云储存、百度快照、百度移动、百度联盟等各类功能。
```

2. 参数式

链接的文字放在[]中，链接地址放在随后的:后，链接地址后面空一格，然后用引号引起来

```markdown
[百度]: https://www.baidu.com "百度一下，你就知道"
是一个互联网搜索引擎综合服务提供商，提供搜索引擎、地图、百科、贴吧、金融等服务，并且支持云计算和云储存、百度快照、百度移动、百度联盟等各类功能。
//参数定义的其他写法
[百度]: https://www.baidu.com '百度一下，你就知道'
[百度]: https://www.baidu.com (百度一下，你就知道)
[百度]: <https://www.baidu.com> "百度一下，你就知道"
```

### 七、图片

1. 行内式

和链接的形式差不多，图片的名字放在[]中，图片地址放在随后的()中，title属性（图片地址后面空一格，然后用引号引起来）,注意的是[]前要加上!

```markdown
![baidu-logo.png](https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png "baidu-logo")
```

2. 参数式

图片的文字放在[]中，图片地址放在随后的:后，title属性（图片地址后面空一格，然后用引号引起来）,注意引用图片的时候在[]前要加上!

```markdown
[baidu-logo.png]: https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png "baidu-logo"
![baidu-logo.png]
//参数定义的其他写法
[baidu-logo.png]: https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png 'baidu-logo'
[baidu-logo.png]: https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png (baidu-logo)
[baidu-logo.png]: <https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png> "baidu-logo"
```
### 八、分割线

分割线可以由* - _（星号，减号，底线）这3个符号的至少3个符号表示，注意至少要3个，且不需要连续，有空格也可以

```markdown
---
- - -
------
***
* * *
******
___
_ _ _
______
```

### 九、其他

1. 强调字体

一个星号或者是一个下划线包起来，会转换为<em>倾斜，如果是2个，会转换为<strong>加粗

```markdown
*md*    
**md**
_md_   
 __md__
```

2. 转义

基本上和js转义一样,\加需要转义的字符

```markdown
\\
\*
\+
\-
\`
\_
```

3. 删除线

用~~把需要显示删除线的字符包裹起来

```markdown
~~删除~~
```

### 十、表格

```markdown
//例子一
|123|234|345|
|:-|:-:|-:|
|abc|bcd|cde|
|abc|bcd|cde|
|abc|bcd|cde|
//例子二
|123|234|345|
|:---|:---:|---:|
|abc|bcd|cde|
|abc|bcd|cde|
|abc|bcd|cde|
//例子三
123|234|345
:-|:-:|-:
abc|bcd|cde
abc|bcd|cde
abc|bcd|cde
```

> 上面三个例子的效果一样，由此可得：
> 1. 表格的格式不一定要对的非常起，但是为了良好的变成风格，尽量对齐是最好的
> 2. 分割线后面的冒号表示对齐方式，写在左边表示左对齐，右边为右对齐，两边都写表示居中