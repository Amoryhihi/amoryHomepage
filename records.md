homeview中的文章展示swagger：

responses:

```
{
code:200,
data: [
{
id:0;
title: xxx;
thumvnail: xxx;
create_time: xxx-xx-xx;
}
]
}
```

写道articleserviceimpl和前端的

<article>blog-right</article>

serviceimpl里写querywrapper

问题：mybatisplus中的page不好用，写的(1,3)出来仍是四篇文章

已解决

11.28

后端分页功能不好用，在blog页面中使用前端分页

使用VO对查到的数据进行优化

11.29 分页的实现和category分类

{

code:

data:{

rows:[categoryId:

createtime:

id:

.....

]

total:}

}

msg:

}

12.1 文章详情

get    /article/{id}

```xml
{
code:200,
data: {
categoryId
content
createtime
id
title
viewcount
}
}
```

12.2评论

DB 用户对评论是一对多的关系

评论要做父子

id(评论id)

username 发起此评论的人输入的name

root_id 根评论id(父亲) 没有是-1

content评论内容

create_time

to_comment_id回复了哪条



GET  /comment/commentList

参数：pageNum ，pageSize

```xml
{
code:200,
data: {
rows: [
{
    children: [{
    id:3,
    rootId:1,
    createTime:xxxx-xx-xx
    username:sg,
    },{}],
	content:"",
	username:"",
	id:1,
	rootId:-1,

}
],
total:15,
}
msg:okresult
}
```

后台：实现文章的增删改

首页是查

12.5后台

搞根据categoryId显示

userServiceDetailImpl中找查询一条数据方法

对于登录问题postman可以但是开了前端就cors错误：

```java
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(request.getMethod().equals("OPTIONS")){
            return true;
        }
}
```

