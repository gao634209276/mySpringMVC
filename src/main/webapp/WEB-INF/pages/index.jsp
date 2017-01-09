<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%--<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Spring MVC</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<h1>这里是SpringMVC Demo首页</h1>

<h3>出现此页面，说明配置成功。</h3>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>--%>
<head>

    <title>智慧服务接口</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <script type="text/javascript">
        function createNews() {
            document.getElementById('fieldForm').submit();
        }
        function backButton() {
            window.close();
            //$.nmTop().close();
            window.location.reload();
        }
    </script>
</head>

<body>
<form name="fieldForm" id="fieldForm" action="submit" method="post">

    <div class="content wrap clearfix" style="width: 1060px;">
        <div class="">
            <div class="versions">请输入请求参数：</div>
            <div class="bills_list" style="padding-left: 20px">
                <dl>
                    <dt>SayHello：</dt>
                    <dd><input type="text" id="args" name="args" value=""/></dd>
                </dl>
            </div>
            <div>
                <input id="btnNextStep2" onclick="createNews();" type="submit" value="查询"/>
            </div>
        </div>
    </div>
</form>
</body>
</html>
