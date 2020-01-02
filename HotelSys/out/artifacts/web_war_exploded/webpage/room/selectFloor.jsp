<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/24
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
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
        <th>ID</th>
        <th>楼层名称</th>
    </tr>
</table>
<div class="fixDiv">
    <label class="layui-form-label">当前选中：</label>
    <div class="layui-input-inline">
        <input type="text" id="fId" class="layui-input" placeholder="ID" readonly>
    </div>
    <div class="layui-input-inline">
        <input type="text" id="fName" class="layui-input" placeholder="价格" readonly>
    </div>
</div>

<script>
    //网页加载完毕
    $(document).ready(function() {

        //发出ajax请求，调用后端数据
        $.getJSON('http://localhost:8080/web_war_exploded/selectFloorIdServlet', function(data) {

            //遍历响应的json数组
            $.each(data, function(index, el) {
                var fId = el.floorId;
                var fName = el.floorName;
                var html = '';
                html += '<tr onclick="fId.value=\'' + fId + '\',fName.value=\'' + fName + '\'" >';
                html += '	<td>' + el.floorId + '</td>';
                html += '	<td>' + el.floorName + '</td>';
                html += '</tr>';

                //追加到表格中
                $('#dataTable').append(html);

            });

        });
    });
</script>
</body>

</html>
