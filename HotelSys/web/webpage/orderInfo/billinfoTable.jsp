<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/26
  Time: 13:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <meta charset="utf-8">
    <title>酒店管理系统</title>
    <link rel="stylesheet" href="../../js/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="../../MAIN/component/font-awesome-4.7.0/css/font-awesome.min.css">
    <script src="../../js/layui/layui.js"></script>
    <script src="../../js/jquery.js"></script>
    <script src="../../js/global.js"></script>
    <script src="../../js/Cookie.js"></script>
    <style>
        body {
            margin: 10px;
        }

        .layui-elem-field legend {
            font-size: 14px;
        }

        .layui-field-title {
            margin: 25px 0 15px;
        }
    </style>
</head>

<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend>
        <div>
            <div class="layui-inline">
                <button class="layui-btn fa fa-refresh layui-btn-normal" id="refreshButton"> 刷新</button>
            </div>
<%--            <div class="layui-inline">--%>
<%--                <button class="layui-btn fa fa-save layui-btn-normal" id="toXlsButton"> 导出</button>--%>
<%--            </div>--%>
        </div>
    </legend>
</fieldset>
<div id="toxlsTable">
    <table id="tableID"></table>
</div>
<script type="text/html" id="barAuth">
    <a class="layui-btn layui-btn-xs" lay-event="view">查看</a>
</script>
<script>
    layui.use(['util', 'layer', 'table'], function() {
        $(document).ready(function() {
            var table = layui.table,
                layer = layui.layer,
                util = layui.util;
            var countNum;
            var tableIns = table.render({
                elem: '#tableID',
                id: 'tableID',
                url:  'http://localhost:8080/web_war_exploded/BillInfoServlet',
                cols: [
                    [{
                        field: 'billId',
                        title: '账单ID',
                        width: 180,
                        sort: true
                    },{
                        field: 'checkedId',
                        title: '入住单号',
                        width: 180,
                        sort: true
                    }, {
                        field: 'costMoney',
                        title: '账单金额',
                        sort: true,
                        width: 180
                    }, {
                        field: 'costDate',
                        title: '账单时间',
                        width: 180
                    },  {
                        field: 'remark',
                        title: '备注',
                        width: 400
                    }, {
                        field: 'right',
                        title: '管理',
                        align: 'center',
                        toolbar: '#barAuth',
                        width: 150,
                        fixed: 'right'
                    }]
                ],
                page: true,
                where: {
                    make: 0
                },
                done: function(res, curr, count) {
                    countNum = count;
                }
            });

            //监听工具条
            table.on('tool', function(obj) {
                var data = obj.data,
                    layEvent = obj.event;
                var checkId = data.checkedId;
                if(layEvent === 'view') {
                    layer.alert(
                        '账单ID：' + data.billId + '<br>入住单号：' + checkId + '<br>账单金额：' + data.costMoney +
                        '<br>账单时间：' + data.costDate + '<br>备注：' + data.remark
                        , {
                            skin: 'layui-layer-lan',
                            closeBtn: 0,
                            title: '您当前选择的账单信息',
                            anim: 4,
                            offset: '180px'
                        });
                }
            });


            //刷新
            $('#refreshButton').click(function() {
                tableIns.reload({
                    where: {
                        make: 0,
                        page: 1
                    }
                });
            });

            // //导出
            // $('#toXlsButton').click(function() {
            //     location.href = baseUrl + '/BillInfoExcelServlet';
            //     layer.alert('Excel文件生成完成！', {
            //         title: '成功',
            //         icon: 6,
            //         anim: 1,
            //         offset: '250px'
            //     });
            // });

            //回到顶端
            util.fixbar({
                showHeight: 2,
                click: function(type) {
                    console.log(type);
                }
            });
        });
    });
</script>
</body>

</html>