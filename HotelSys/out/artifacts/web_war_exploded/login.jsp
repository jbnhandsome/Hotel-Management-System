<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/9
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <title>酒店管理界面</title>
    <link rel="stylesheet Icon" type=" image/x-icon" href="img/windows.ico">
    <link rel="stylesheet" type="text/css" href="css/login/register-login.css">
    <script src="./js/global.js"></script>
</head>

<body>
<div id="box"></div>

<%--主栏--%>
<div class="cent-box">
    <%--标题--%>
    <div class="cent-box-header">
        <h1 class="main-title" style="width:180px;"> 酒店管理系统</h1>
        <h2 class="sub-title">Hotel Book Sys</h2>
    </div>
        <%--中间内容--%>
        <div class="cont-main clearfix">

            <!--登录区域开始-->
            <div class="login form">
                <!--文本输入框-->
                <div class="group">
                    <!--用户名输入框-->
                    <div class="group-ipt loginName">
                        <input type="text" name="loginName" id="loginName" class="ipt" placeholder="输入您的用户名" required lay-verify="required">

                    </div>
                    <!--密码输入框-->
                    <div class="group-ipt loginPwd">
                        <input type="password" name="loginPwd" id="loginPwd" class="ipt" placeholder="输入您的登录密码" required lay-verify="required">
                    </div>
                </div>
                <!--登录按钮-->
                <div class="button" id="btnLogin">
                    <button type="submit" class="login-btn register-btn button" id="embed-submit">登录</button>
                </div>
            </div>
            <!--登录区域结束-->
            <!--尾注-->
            <div class="remember clearfix">
                <label class="remember-me">
                    <a href="#"></a>
                </label>
                <label class="forgot-password">
                    <a href="#"></a>
                </label>
            </div>
        </div>


</div>


<script type="text/javascript" src="./js/login/particles.js"></script>
<script type="text/javascript" src="./js/login/background.js"></script>
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/Cookie.js" charset="utf-8"></script>

<%--导入layUI--%>
<script type="text/javascript" src="${ctx}/js/layui/layui.js"></script>


<%--引入win10UI--%>
<script type="text/javascript" src="${ctx}/MAIN/js/win10.child.js" ></script>
<script type="text/javascript" src="${ctx}/MAIN/js/jquery-2.2.4.min.js" ></script>


<script>
    layui.use('layer', function(){
        var layer = layui.layer;
        $(document).ready(function () {
          //网页加载完毕

            //点击按钮时 发生的事件
            $('#btnLogin').click(function () {
                var loginName=$('#loginName').val();
                var loginPwd=$('#loginPwd').val();
                //传递给servlet的请求串
                var params = "loginName=" + loginName + "&loginPwd=" + loginPwd;

                //账号密码不可为空
                if(loginName ==""){
                    //在容器旁边弹
                    layer.tips("请输入用户名", "#loginName");//layer.tips(“string","#吸附容器")
                }else if (loginPwd == ""){
                    layer.tips("请输入密码", "#loginPwd");
                } else{
                    //该向后台请求数据了
                    $.post('http://localhost:8080/web_war_exploded/QueryLoginNameServlet',params,function (data) {
                        //data返回-1则表示未查到，0表示密码不正确 1表示
                        //alert(data); //传回来的格式
                        if (data==-1){
                            layer.msg("用户名不存在",{
                                //弹出动画
                                anim:6
                            })
                        } else if (data==0){
                            layer.msg("密码不正确",{
                                //弹出动画
                                anim:6
                            })
                        } else{
                            layer.msg("登陆成功",{
                                anim:6,
                                //消息左边图像
                                icon:1
                            })

                            //根据写入的session得到的结果  设置cookie
                            $.post('http://localhost:8080/web_war_exploded/QueryLoginInfoServlet',function (loginInfo) {
                                //数据返回样例  要求返回json格式
                                <%--{"loginId":1,"loginName":"root","loginPwd":"toor","loginNickName":"管理员","loginAdmin":0}--%>
                               var obj=JSON.parse(loginInfo);
                                //设置COOKIE
                                setCookie("loginName",loginName);
                                setCookie("loginNickName",obj.loginNickName);
                                setCookie("loginAdmin",obj.loginAdmin);

                            })



                            //等待一会 进入主页面
                            setTimeout(function () {
                                location.href='MAIN/main.html';
                            },100)
                        }
                    })

                }

            })
        })

    });
</script>

</body>
</html>
