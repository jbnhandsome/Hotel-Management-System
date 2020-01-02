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
        <th>房间ID</th>
        <th>类型名称</th>
        <th>所属楼层</th>
        <th>状态</th>
    </tr>
</table>
<div class="fixDiv">
    <label class="layui-form-label">当前选中：</label>
    <div class="layui-input-inline">
        <input type="text" id="rId" class="layui-input" placeholder="ID" readonly>
    </div>
    <div class="layui-input-inline">
        <input type="text" id="tName" class="layui-input" placeholder="name" readonly>
    </div>
    <div class="layui-input-inline">
        <input type="text" id="tStatus" class="layui-input" placeholder="价格" readonly>
    </div>
</div>

<script>
    //网页加载完毕
    $(document).ready(function() {

        //发出ajax请求，调用后端数据
        $.getJSON('http://localhost:8080/web_war_exploded/selectRomeServlet', function(data) {

            //遍历响应的json数组

            $.each(data, function(index, el) {
                var rId = el.roomId;
                var tStatus = el.status;
                var html = '';
                html += '<tr onclick="rId.value=\'' + rId + '\',tStatus.value=\'' + tStatus + '\',tName.value=\'' + el.typeName + '\'" >';
                html += '	<td>' + el.roomId + '</td>';
                html += '	<td>' + el.typeName + '</td>';
                html += '	<td>' + el.floorId + '</td>';
                html += '	<td>' + el.status + '</td>';
                html += '</tr>';

                //追加到表格中
                $('#dataTable').append(html);


            });

        });
    });
</script>
</body>

</html>