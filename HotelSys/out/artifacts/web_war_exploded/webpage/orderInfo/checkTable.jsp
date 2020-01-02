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
                <div class="layui-input-inline">
                    <input class="layui-input" id="inputSearch1" placeholder="入住人">
                </div>
                <button class="layui-btn fa fa-search layui-btn-normal" id="searchButton1"> 搜索</button>
            </div>

            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input class="layui-input" id="inputSearch2" placeholder="房间类型">
                </div>
                <button class="layui-btn fa fa-search layui-btn-normal" id="searchButton2"> 搜索</button>
            </div>

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
    <a class="layui-btn layui-btn-xs" lay-event="checkin">退房</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
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
                url: 'http://localhost:8080/web_war_exploded/CheckInfoServlet',
                cols: [
                    [{
                        field: 'checkId',
                        title: '入住单号',
                        width: 180,
                        sort: true
                    },{
                        field: 'orderId',
                        title: '预定单号',
                        width: 180,
                        sort: true
                    }, {
                        field: 'checkName',
                        title: '入住人',
                        sort: true,
                        width: 180
                    }, {
                        field: 'checkPhone',
                        title: '客人电话',
                        width: 180
                    }, {
                        field: 'checkIDcard',
                        title: '身份证',
                        width: 200
                    }, {
                        field: 'arrireTime',
                        title: '抵店时间',
                        width: 180
                    }, {
                        field: 'leaveTime',
                        title: '离店时间',
                        width: 180
                    }, {
                        field: 'typeId',
                        title: '房间类型',
                        sort: true,
                        width: 180
                    }, {
                        field: 'roomId',
                        title: '房间编号',
                        sort: true,
                        width: 180
                    }, {
                        field: 'checkNum',
                        title: '入住人数',
                        width: 100
                    }, {
                        field: 'price',
                        title: '客房价格',
                        width: 100
                    }, {
                        field: 'checkPrice',
                        title: '入住价格',
                        width: 100
                    }, {
                        field: 'discount',
                        title: '折扣',
                        width: 100
                    }, {
                        field: 'discountReason',
                        title: '折扣原因',
                        width: 180
                    }, {
                        field: 'addBed',
                        title: '是否加床',
                        sort: true,
                        width: 100
                    }, {
                        field: 'addBedPrice',
                        title: '加床价格',
                        width: 100
                    }, {
                        field: 'orderMoney',
                        title: '预收款',
                        width: 100
                    }, {
                        field: 'checkState',
                        title: '单据状态',
                        sort: true,
                        width: 100
                    }, {
                        field: 'isCheck',
                        title: '是否结算',
                        sort: true,
                        width: 100
                    }, {
                        field: 'remark',
                        title: '备注',
                        width: 400
                    }, {
                        field: 'operatorId',
                        title: '操作员',
                        sort: true,
                        width: 100
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
                var orderId = data.orderId;
                var checkId = data.checkId;
                var roomId = data.roomId;
                if(layEvent === 'del') {
                    layer.confirm('您确定要删除该条数据吗？', {
                        offset: '180px',
                        btn: ['是滴', '手滑']
                    }, function() {
                        table.reload('tableID', {
                            where: {
                                make: 4,
                                checkId: checkId
                            }
                        });
                        layer.msg('删除结果如下', {
                            offset: '250px',
                            icon: 1
                        });
                        tableIns.reload({
                            where: {
                                make: 0,
                                page: 1
                            }
                        });
                    }, function() {
                        layer.msg('删除操作已取消', {
                            offset: '250px'
                        });
                    });
                } else if(layEvent === 'edit') {

                    // emmm 父子传参只会写儿子传递给父亲的
                    // 其实 用jsp那套session啥的传参或许更好
                    setCookie("orderId", orderId);

                    //编辑
                    layer.open({
                        title: "提交",
                        btn: ['关闭'],
                        yes: function(index) {
                            tableIns.reload({
                                where: {
                                    make: 0
                                }
                            });
                            layer.close(index); //关闭弹窗
                        },
                        type: 2,
                        area: ['1080px', '620px'],
                        fixed: false,
                        maxmin: true,
                        content: 'updateCheck.jsp?checkId='+checkId,
                        cancel: function() {
                            tableIns.reload({
                                where: {
                                    make: 0
                                }
                            });
                        }
                    });

                }else if(layEvent === 'checkin'){
                    if(data.checkState == '结算'){
                        layer.alert('已经结算过了！', {
                            title: '成功',
                            icon: 6,
                            anim: 1,
                            offset: '250px'
                        });
                        return;
                    }

                    layer.prompt({
                        formType: 0,
                        value: data.money,
                        title: '请输入结账金额'
                    }, function(value, index, elem){
                        //alert(value); //得到value
                        $.ajax({
                            url:'http://localhost:8080/web_war_exploded/DoCheckInServlet',
                            type:'post',
                            dataType:'json',
                            data:{checkId:checkId,orderId:orderId,roomId:roomId,checkMoney:value},
                            success:function(rst){
                                if(rst.type == 'success'){
                                    tableIns.reload({
                                        where: {
                                            make: 0,
                                            page: 1
                                        }
                                    });
                                    layer.msg('已结账', {
                                        offset: '250px'
                                    });
                                }else{
                                    layer.msg(rst.msg, {
                                        offset: '250px'
                                    });
                                }
                            }
                        });
                        layer.close(index);
                    });
                }
            });

            //搜索
            $('#searchButton1').click(function() {
                var select = $('#inputSearch1').val();
                tableIns.reload({
                    where: {
                        make: 1,
                        page: 1,
                        select: select
                    }
                });
            });

            $('#searchButton2').click(function() {
                var select = $('#inputSearch2').val();
                tableIns.reload({
                    where: {
                        make: 2,
                        page: 1,
                        select: select
                    }
                });
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
            //     location.href = baseUrl + '/CheckInfoExcelServlet';
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