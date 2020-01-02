<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/25
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="../../js/layui/css/layui.css" media="all">
    <script src="../../js/layui/layui.js"></script>
    <script src="../../js/jquery.js"></script>
    <script src="../../js/global.js"></script>
    <style>
        .fixDiv {
            position: sticky;
            bottom: 0;
            background-color: white;
            BORDER-BOTTOM: #e1e1e1 1px solid;
            BORDER-TOP: #e1e1e1 1px solid;
            BORDER-RIGHT: #e1e1e1 1px solid;
            BORDER-LEFT: #e1e1e1 1px solid;
            border-radius: 10px
        }
    </style>
</head>
<body>
<table id="dataTable" class='layui-table'>
    <tr>
        <th>预定单号</th>
        <th>预定人</th>
        <th>预定电话</th>
        <th>身份证</th>
        <th>房间类型</th>
        <th>客房价格</th>
    </tr>
</table>
<div class="fixDiv">
    <label class="layui-form-label">当前选中：</label>
    <div class="layui-input-inline">
        <input type="text" id="oId" class="layui-input" placeholder="ID" readonly>
    </div>
    <div class="layui-input-inline">
        <input type="text" id="oName" class="layui-input" placeholder="name" readonly>
    </div>
    <div class="layui-input-inline">
        <input type="text" id="oPrice" class="layui-input" placeholder="价格" readonly>
    </div>
    <div class="layui-input-inline">
        <input type="text" id="oTypeId" class="layui-input" placeholder="房型" readonly>
        <input type="hidden" id="checkName">
        <input type="hidden" id="checkPhone">
        <input type="hidden" id="checkIDcard">
        <input type="hidden" id="arrireTime">
        <input type="hidden" id="leaveTime">
        <input type="hidden" id="checkNum">
        <input type="hidden" id="discount">
        <input type="hidden" id="discountReason">
    </div>
</div>
<script>
    //网页加载完毕
    $(document).ready(function() {

        //发出ajax请求，调用后端数据
        $.getJSON('http://localhost:8080/web_war_exploded/selectOrderServlet', function(data) {

            //遍历响应的json数组

            $.each(data, function(index, el) {
                var oId = el.orderId;
                var oPrice = el.price;
                var html = '';
                var val = 'checkName.value=\''+el.orderName+'\',';
                val += 'checkPhone.value=\''+el.orderPhone+'\',';
                val += 'checkIDcard.value=\''+el.orderIDcard+'\',';
                val += 'arrireTime.value=\''+el.arrireDate+'\',';
                val += 'leaveTime.value=\''+el.leaveDate+'\',';
                val += 'checkNum.value=\''+el.checkNum+'\',';
                val += 'discount.value=\''+el.discount+'\',';
                val += 'discountReason.value=\''+el.discountReason+'\'';
                html += '<tr onclick="oTypeId.value=\''+el.typeId.typeId+'\','+val+',oId.value=\'' + oId + '\',oPrice.value=\'' + oPrice + '\',oName.value=\'' + el.orderName + '\'" >';
                html += '	<td>' + el.orderId + '</td>';
                html += '	<td>' + el.orderName + '</td>';
                html += '	<td>' + el.orderPhone + '</td>';
                html += '	<td>' + el.orderIDcard + '</td>';
                html += '	<td>' + el.typeId.typeId + '</td>';
                html += '	<td>' + el.price + '</td>';
                html += '</tr>';

                //追加到表格中
                $('#dataTable').append(html);

            });

        });
    });
</script>
</body>
</html>
