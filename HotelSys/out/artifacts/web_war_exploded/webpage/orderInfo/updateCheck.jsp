<%@ page import="com.jbn.orderinfo.pojo.CheckInfo" %>
<%@ page import="com.jbn.orderinfo.service.CheckInfoService" %>
<%@ page import="com.jbn.orderinfo.service.CheckInfoServiceImpl" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <meta charset="utf-8">
    <title>编辑入住单</title>
    <link rel="stylesheet" href="../../js/layui/css/layui.css" media="all">
    <script src="../../js/layui/layui.js"></script>
    <script src="../../js/jquery.js"></script>
    <script src="../../js/global.js"></script>
    <script src="../../js/getTime.js"></script>
    <script src="../../js/Cookie.js"></script>
</head>
<%
    String checkId = request.getParameter("checkId");
    CheckInfo checkInfo = new CheckInfo();
    if(checkId != null){
        CheckInfoService checkInfoService = new CheckInfoServiceImpl();
        checkInfo = checkInfoService.query(checkId);
    }

%>
<body>
<fieldset class="layui-elem-field layui-field-title " style="margin-top: 20px;">
    <legend>酒店管理 - 编辑入住单</legend>
</fieldset>

<form class="layui-form">

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">入住单号</label>
            <div class="layui-input-block">
                <input type="text" id="checkId" value="<%=checkInfo.getCheckId() %>" lay-verify="required" class="layui-input" readonly>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">预定单号</label>
            <div class="layui-input-inline">
                <input type="text" id="orderId" value="<%=checkInfo.getOrderId() %>" lay-verify="required" autocomplete="off" placeholder="预订单号" class="layui-input" readonly>
            </div>
            <button type="button" class="layui-btn layui-btn-primary" id="buttonOrderId"><i class="layui-icon">&#xe654;</i> 选择</button>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">预定人</label>
            <div class="layui-input-inline">
                <input type="text" id="checkName" value="<%=checkInfo.getCheckName() %>" lay-verify="required" autocomplete="off" placeholder="预定人姓名" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">预定电话</label>
            <div class="layui-input-inline">
                <input type="tel" id="checkPhone" value="<%=checkInfo.getCheckPhone() %>" lay-verify="required|phone" autocomplete="off" placeholder="预定人电话" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">身份证</label>
        <div class="layui-input-block">
            <input type="text" id="checkIDcard" value="<%=checkInfo.getCheckIDcard() %>" lay-verify="required|identity" placeholder="公民身份证号" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">

        <div class="layui-inline">
            <label class="layui-form-label">入住时长</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input"value="<%=checkInfo.getArrireTime() %> | <%=checkInfo.getLeaveTime() %>" lay-verify="required" id="orderAllTime" placeholder="抵店时间 - 离店时间" readonly>
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">入住人数</label>
            <div class="layui-input-inline">
                <input type="text" id="checkNum" value="<%=checkInfo.getCheckNum() %>" lay-verify="required" autocomplete="off" placeholder="实际入住人数" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">房间类型</label>
            <div class="layui-input-inline">
                <input type="text" id="typeId" value="<%=checkInfo.getTypeId() %>" lay-verify="required" autocomplete="off" placeholder="房间类型" class="layui-input" readonly>
            </div>
            <button type="button" class="layui-btn layui-btn-primary" id="buttonTypeId"><i class="layui-icon">&#xe654;</i> 选择</button>
        </div>

    </div>

    <div class="layui-form-item">

        <div class="layui-inline">
            <label class="layui-form-label">客房价格</label>
            <div class="layui-input-inline">
                <input type="text" id="price" value="<%=checkInfo.getPrice() %>" lay-verify="required" autocomplete="off" placeholder="￥" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">入住价格</label>
            <div class="layui-input-inline">
                <input type="text" id="checkPrice" value="<%=checkInfo.getCheckPrice() %>" lay-verify="required" autocomplete="off" placeholder="￥" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">入住房间</label>
            <div class="layui-input-inline">
                <input type="text" id="roomId" value="<%=checkInfo.getRoomId() %>" lay-verify="required" autocomplete="off" placeholder="房间ID" class="layui-input" readonly>
            </div>
            <button type="button" class="layui-btn layui-btn-primary" id="buttonRoomId"><i class="layui-icon">&#xe654;</i> 选择</button>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">折扣</label>
            <div class="layui-input-inline">
                <input type="text" id="discount" value="<%=checkInfo.getDiscount() %>" autocomplete="off" placeholder="折扣请输入，无折扣置空" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">折扣原因</label>
            <div class="layui-input-inline">
                <input type="text" id="discountReason" value="<%=checkInfo.getDiscountReason() %>" autocomplete="off" placeholder="请输入折扣原因" class="layui-input">
            </div>
        </div>

    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">是否加床</label>
            <div class="layui-input-inline">
                <input type="radio" name="addBed" value="Y" title="是" lay-filter="addBedYes">
                <input type="radio" name="addBed" value="N" title="否" lay-filter="addBedNo" checked="">
            </div>
        </div>
        <div class="layui-inline">
            <div id="addBed" class="layui-inline layui-hide">
                <label class="layui-form-label">加床价格</label>
                <div class="layui-input-inline">
                    <input type="text" id="addBedPrice" value="<%=checkInfo.getAddBedPrice() %>"  autocomplete="off" placeholder="￥" class="layui-input">
                </div>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">预收款</label>
            <div class="layui-input-inline">
                <input type="text" id="orderMoney" value="<%=checkInfo.getOrderMoney() %>" lay-verify="required" autocomplete="off" placeholder="￥" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">应收款</label>
            <div class="layui-input-inline">
                <input type="text" id="money" value="<%=checkInfo.getMoney() %>" lay-verify="required" autocomplete="off" placeholder="￥" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-inline">
                <select name="city" class="layui-input-inline" id="orderState">
                    <option value="入住">入住</option>
                    <option value="结算">结算</option>
                    <option value="延期">延期</option>
                </select>
            </div>
        </div>

    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea id="remark" placeholder="请输入内容" class="layui-textarea"><%=checkInfo.getRemark() %></textarea>
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
            layer = layui.layer,
            layedit = layui.layedit,
            laydate = layui.laydate;
        var isAddBed = false;

        //日期
        laydate.render({
            elem: '#arrireDate'
        });
        laydate.render({
            elem: '#leaveDate'
        });
        laydate.render({
            elem: '#orderAllTime',
            type: 'datetime',
            min: 0,
            range: '|',
            format: 'yyyy-MM-dd',
            calendar: true
        });

        //设置ID（读取的时间）
        var time = new Date().getTime();
        $(document).ready(function() {
            //$("#checkId").val("CK" + time);
        });

        //一个属性的显隐，直接通过修改class实现，使用了layui的class属性
        form.on('radio(addBedYes)', function() {
            $('#addBed').removeClass("layui-hide");
            $('#addBed').addClass("layui-show");
            isAddBed = true;
        });
        form.on('radio(addBedNo)', function() {
            $('#addBed').removeClass("layui-show");
            $('#addBed').addClass("layui-hide");
            isAddBed = false;
        });

        //预定订单选择
        $('#buttonOrderId').on('click', function() {
            layer.open({
                type: 2,
                title: '请选择预定订单',
                btn: ['确定', '取消'],
                area: ['880px', '440px'],
                fixed: form,
                content: './selectOrder.jsp',
                yes: function(index, layero) {
                    $("#orderId").val($(layero).find('iframe')[0].contentWindow.oId.value); //将子窗口中的 tId 抓过来
                    price.value = $(layero).find('iframe')[0].contentWindow.oPrice.value;
                    $("#checkName").val($(layero).find('iframe')[0].contentWindow.checkName.value);
                    $("#checkPhone").val($(layero).find('iframe')[0].contentWindow.checkPhone.value);
                    $("#checkIDcard").val($(layero).find('iframe')[0].contentWindow.checkIDcard.value);
                    $("#orderAllTime").val($(layero).find('iframe')[0].contentWindow.arrireTime.value + ' | ' + $(layero).find('iframe')[0].contentWindow.leaveTime.value);
                    $("#checkNum").val($(layero).find('iframe')[0].contentWindow.checkNum.value);
                    $("#discount").val($(layero).find('iframe')[0].contentWindow.discount.value);
                    $("#discountReason").val($(layero).find('iframe')[0].contentWindow.discountReason.value);
                    //$("#checkName").val($(layero).find('iframe')[0].contentWindow.checkName.value);
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

        //房间类型的选择
        $('#buttonTypeId').on('click', function() {
            layer.open({
                type: 2,
                title: '请选择房间类型',
                btn: ['确定', '取消'],
                area: ['880px', '440px'],
                fixed: form,
                content: './selectRoomType.jsp',
                yes: function(index, layero) {
                    typeId.value = $(layero).find('iframe')[0].contentWindow.tId.value; //将子窗口中的 tId 抓过来
                    price.value = $(layero).find('iframe')[0].contentWindow.tPrice.value;
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

        //房间选择
        $('#buttonRoomId').on('click', function() {
            layer.open({
                type: 2,
                title: '请选择房间',
                btn: ['确定', '取消'],
                area: ['880px', '440px'],
                fixed: form,
                content: './selectRoom.jsp',
                yes: function(index, layero) {
                    roomId.value = $(layero).find('iframe')[0].contentWindow.rId.value; //将子窗口中的 tId 抓过来
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

        //监听提交
        form.on('submit(insertRome)', function(data) {

            //先获取值
            var checkId = $('#checkId').val();
            var orderId = $('#orderId').val();
            var orderName = $('#checkName').val();
            var orderPhone = $('#checkPhone').val();
            var orderIDcard = $('#checkIDcard').val();
            var typeId = $('#typeId').val();
            var roomId = $('#roomId').val();

            //返回数据类型： yyyy-mm-dd hh:mm:ss
            var orderAllTime = ($('#orderAllTime').val()).split(" | ");
            var arrireDate = orderAllTime[0];
            var leaveDate = orderAllTime[1];

            var orderState = $('#orderState').val();
            var checkNum = $('#checkNum').val();

            // var roomId = $('#roomId').val(); 后台处理 -->直接放一个空类就行了

            var price = $('#price').val();
            var checkPrice = $('#checkPrice').val();
            var discount = $('#discount').val();
            var discountReason = $('#discountReason').val();

            //加床：true 不加：false
            //var addBed = isAddBed;
            var addBed = $("input[type='radio']:checked").val();

            var addBedPrice = $('#addBedPrice').val();
            var orderMoney = $('#orderMoney').val();
            var money = $('#money').val();
            var operatorId = getCookie("loginName");
            var remark = $('#remark').val();

            var params = "roomId=" + roomId + "&money=" + money + "&checkId=" + checkId + "&orderId=" + orderId + "&orderName=" + orderName + "&orderPhone=" + orderPhone +
                "&orderIDcard=" + orderIDcard + "&typeId=" + typeId + "&arrireDate=" + arrireDate +
                "&leaveDate=" + leaveDate + "&orderState=" + orderState + "&checkNum=" + checkNum +
                "&price=" + price + "&checkPrice=" + checkPrice + "&discount=" + discount +
                "&discountReason=" + discountReason + "&addBed=" + addBed + "&addBedPrice=" + addBedPrice +
                "&orderMoney=" + orderMoney + "&operatorId=" + operatorId + "&remark=" + remark + "&make=2";

            $.post('http://localhost:8080/web_war_exploded/InsertAndUpdateCheckServlet', params, function(data) {
                if(data === '1') {
                    layer.alert('入住单登记成功！', {
                        title: '新增成功',
                        icon: 6,
                        shade: 0.6,
                        anim: 3,
                        offset: '220px'
                    });
                } else if(data === '0') {
                    layer.alert('存在相同字段！', {
                        title: '新增失败',
                        icon: 5,
                        shade: 0.6,
                        anim: 6,
                        offset: '220px'
                    });
                } else {
                    layer.alert('入住单登记失败！', {
                        title: '新增失败',
                        icon: 2,
                        shade: 0.6,
                        anim: 6,
                        offset: '220px'
                    });
                }
            });
            return false;
        });
    });
</script>
</body>

</html>