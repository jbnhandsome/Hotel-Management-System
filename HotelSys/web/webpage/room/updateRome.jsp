<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/25
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.jbn.room.pojo.Room" %>
<%@ page import="com.jbn.room.service.RoomService" %>
<%@ page import="com.jbn.room.service.RoomServiceImpl" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>编辑房间</title>
    <link rel="stylesheet" href="../../js/layui/css/layui.css" media="all">
    <script src="../../js/layui/layui.js"></script>
    <script src="../../js/jquery.js"></script>
    <script src="../../js/global.js"></script>
</head>
<%
    String roomId = request.getParameter("roomId");
    Room room = new Room();
    if(roomId != null){
        RoomService roomService = new RoomServiceImpl();
        room = roomService.query(roomId);
    }
%>
<body>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>编辑房间</legend>
</fieldset>
<form class="layui-form" action="">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">房间编号</label>
            <div class="layui-input-inline">
                <input type="text" readonly="readonly" name="roomId" value="<%=room.getRoomId()%>" lay-verify="required" autocomplete="off" placeholder="房间编号" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">可住人数</label>
            <div class="layui-input-inline">
                <input type="text" name="ratedNum" value="<%=room.getRatedNum() %>" lay-verify="required|number" autocomplete="off" placeholder="可住人数" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">床位数</label>
            <div class="layui-input-inline">
                <input type="text" name="bedNum" value="<%=room.getBedNum() %>" lay-verify="required|number" autocomplete="off" placeholder="床位数" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">房间描述</label>
            <div class="layui-input-inline">
                <input type="text" name="roomDescription" value="<%=room.getRoomDescription() %>" lay-verify="required" autocomplete="off" placeholder="房间描述" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">房间楼层</label>
            <div class="layui-input-inline">
                <input type="text" id="floorId" value="<%=room.getFloorId() %>" name="floorId" lay-verify="required" autocomplete="off" placeholder="房间楼层" class="layui-input" readonly>
            </div>
            <button type="button" class="layui-btn layui-btn-primary" id="buttonFloorId"><i class="layui-icon">&#xe654;</i> 选择</button>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">房间类型</label>
            <div class="layui-input-inline">
                <input type="text" id="typeId" value="<%=room.getTypeId() %>" name="typeId" lay-verify="required" autocomplete="off" placeholder="房间类型" class="layui-input" readonly>
                <input type="hidden" id="typeName" value="<%=room.getTypeName() %>" name="typeName">
            </div>
            <button type="button" class="layui-btn layui-btn-primary" id="buttonTypeId"><i class="layui-icon">&#xe654;</i> 选择</button>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">是否可拼房</label>
        <div class="layui-input-block">
            <input type="radio" name="isSplice" value="Y" title="是" checked>
            <input type="radio" name="isSplice" value="N" title="否">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">房间状态</label>
            <div class="layui-input-inline">
                <select name="status" class="layui-input-inline" id="roomState">
                    <option value="已入住">已入住</option>
                    <option value="可入住">可入住</option>
                    <option value="打扫中">打扫中</option>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea id="remark" name="remark" placeholder="请输入内容" class="layui-textarea"><%=room.getRemark() %></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="insertRome">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script>
    layui.use(['form', 'layedit', 'laydate'], function() {
        var form = layui.form,
            layer = layui.layer;

        //自定义验证规则
        form.verify({
            typeName: function(value) {
                if(value.length < 3) {
                    return '房间类型至少3个字符';
                }

                if(value.length > 10) {
                    return '房间类型至多10个字符'
                }
            }
        });

        //监听提交
        form.on('submit(insertRome)', function(data) {
            $.post(baseUrl + '/UpdateRoomServlet', JSON.stringify(data.field), function(code) {
                if(code === 1) {
                    layer.alert('更新成功！', {
                        title: '成功',
                        icon: 6,
                        anim: 5
                    });
                } else if(code === 0) {
                    layer.alert('已存在同名项！', {
                        title: '重复',
                        icon: 4,
                        anim: 6
                    });
                } else {
                    layer.alert('插入失败！', {
                        title: '异常',
                        icon: 6,
                        anim: 6
                    });
                }
            });
            return false;
        });

        //房间类型的选择
        $('#buttonTypeId').on('click', function() {
            layer.open({
                type: 2,
                title: '请选择房间类型',
                btn: ['确定', '取消'],
                area: ['780px', '440px'],
                fixed: form,
                content: '../orderInfo/selectRoomType.jsp',
                yes: function(index, layero) {
                    $("#typeId").val($(layero).find('iframe')[0].contentWindow.tId.value); //将子窗口中的 tId 抓过来
                    $("#typeName").val($(layero).find('iframe')[0].contentWindow.tName.value);
                    layer.close(index); //关闭弹窗
                },
                btn2: function(index) {
                    layer.close(index);
                },
                success: function(layero, index) {
                    var obj = $(layero).find('iframe')[0].contentWindow;
                }
            });
        });

        //房间楼层的选择
        $('#buttonFloorId').on('click', function() {
            layer.open({
                type: 2,
                title: '请选择房间楼层',
                btn: ['确定', '取消'],
                area: ['480px', '440px'],
                fixed: form,
                content: '../room/selectFloor.jsp',
                yes: function(index, layero) {
                    $("#floorId").val($(layero).find('iframe')[0].contentWindow.fId.value); //将子窗口中的 tId 抓过来
                    //price.value = $(layero).find('iframe')[0].contentWindow.tPrice.value;
                    layer.close(index); //关闭弹窗
                },
                btn2: function(index) {
                    layer.close(index);
                },
                success: function(layero, index) {
                    var obj = $(layero).find('iframe')[0].contentWindow;
                }
            });
        });
        var isSplice = '<%=room.getIsSplice()%>';
        var status = '<%=room.getStatus()%>';
        $("#roomState").val(status);
        form.render('select'); //重新渲染
        if(isSplice == 'N'){
            $("input[name='isSplice'][value='N']").prop("checked", true); //把 是 给主动选上
            form.render('radio'); //重新渲染
        }
    });
</script>
</body>
</html>
