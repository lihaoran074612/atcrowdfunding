<%--
  Created by IntelliJ IDEA.
  User: haoran
  Date: 2019/11/15
  Time: 23:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <%@ include file="/WEB-INF/jsp/common/css.jsp"%>
    <style>
        .tree li {
            list-style-type: none;
            cursor:pointer;
        }
        table tbody tr:nth-child(odd){background:#F4F4F4;}
        table tbody td:nth-child(even){color:#C00;}
    </style>
</head>

<body>

<jsp:include page="/WEB-INF/jsp/common/top.jsp"></jsp:include>

<div class="container-fluid">
    <div class="row">
        <%@ include file="/WEB-INF/jsp/common/sidebar.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input class="form-control has-success" id="condition" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="button" id = "queryBtn" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button type="button" class="btn btn-primary" style="float:right;" id="addBtn" ><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                                <tr >
                                    <th width="30">#</th>
                                    <th width="30"><input type="checkbox"></th>
                                    <th>名称</th>
                                    <th width="100">操作</th>
                                </tr>
                            </thead>
                            <tbody>

                            </tbody>
                            <tfoot>
                                <tr >
                                    <td colspan="6" align="center">
                                        <ul class="pagination">

                                        </ul>
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    添加角色
                </h4>
            </div>
            <div class="modal-body">
                <form role="form">
                    <div class="form-group">
                        <label for="name">角色名称</label>
                        <input type="text" class="form-control" id="name" name="name" placeholder="请输入角色名称">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="saveBtn" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    修改角色
                </h4>
            </div>
            <div class="modal-body">
                <form role="form">
                    <div class="form-group">
                        <label for="name">角色名称</label>
                        <input type="hidden" id="id" name="id">
                        <input type="text" class="form-control" id="name" name="name" placeholder="请输入角色名称">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="updateBtn" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>


//分配权限模态框
<div class="modal fade" id="assignModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">给角色分配权限</h4>
            </div>
            <div class="modal-body">
                <ul id="treeDemo" class="ztree"></ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="assignBtn" type="button" class="btn btn-primary">分配</button>
            </div>
        </div>
    </div>
</div>



<%@ include file="/WEB-INF/jsp/common/js.jsp"%>
<script type="text/javascript">
    $(function () {
        $(".list-group-item").click(function(){
            if ( $(this).find("ul") ) {
                $(this).toggleClass("tree-closed");
                if ( $(this).hasClass("tree-closed") ) {
                    $("ul", this).hide("fast");
                } else {
                    $("ul", this).show("fast");
                }
            }
        });
        initData(1);
    });

    $("tbody .btn-success").click(function(){
        window.location.href = "assignPermission.html";
    });

    var json = {
        pageNum:1,
        pageSize:2
    };

    
    function initData(pageNum) {
        $("tbody").empty();
        json.pageNum = pageNum;
        var index = -1;

        $.ajax({
            type:'post',
            url:"${PATH}/role/loadData",
            data:json,
            beforeSend:function () {
                index = layer.load(0,{time:3*1000});
                return true;
            },
            success:function (result) {
                layer.close(index);
                initShow(result);
                initNavg(result);
            }
        })
    }

    function initShow(result) {
        var content = '';
        var list = result.list;
        $.each(list,function (i,e) {
            content+='<tr>';
            content+=' <td>'+(i+1)+'</td>';
            content+=' <td><input type="checkbox"></td>';
            content+=' <td>'+e.name+'</td>';
            content+=' <td>';
            content+='     <button type="button" roleId = "'+e.id+'" class="assignPermissionClass btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>';
            content+='     <button type="button" roleId = "'+e.id+'" class="updateClass btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>';
            content+='     <button type="button" roleId = "'+e.id+'" class="deleteClass btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
            content+=' </td>';
            content+='</tr>';
        });
        $("tbody").html(content);
    }

    function  initNavg(result){
        $(".pagination").empty();
        var navigatepageNums = result.navigatepageNums;

        if (result.isFirstPage){
            $(".pagination").append($('<li class="disabled"><a href="#">上一页</a></li>'));
        }else {
            $(".pagination").append($('<li ><a onclick="initData('+(result.pageNum-1)+')">上一页</a></li>'));
        }

        $.each(navigatepageNums,function (i,num) {
            if (num == result.pageNum){
                $(".pagination").append($('<li class="active"><a href="#">'+(num)+ '<span class="sr-only">(current)</span></a></li>'));
            }else {
                $(".pagination").append($('<li><a onclick="initData('+num+')">'+num+'</a></li>'));
            }
        });

        if (result.isLastPage){
            $(".pagination").append($('<li class="disabled"><a href="#">下一页</a></li>'));
        }else{
            $(".pagination").append($('<li><a onclick="initData('+(result.pageNum+1)+')">下一页</a></li>'));
        }
    }
    
    $("#queryBtn").click(function(){
        var condition = $("#condition").val();
        console.log(condition);
        json.condition = condition;
        initData(1);
    });

    //====模态框添加 开始===============================================
        $("#addBtn").click(function () {
            $("#addModal").modal({
               show:true,
                backdrop:'static',
                keyboard:false
            });
        });

        $("#saveBtn").click(function(){
            var name = $("#addModal input[name='name']").val();
            $.ajax({
               type:"post",
               url:"${PATH}/role/doAdd",
               data : { name:name},
               beforeSend:function() {
                   return true;
               },
               success:function(result) {
                   if('ok' == result){
                       layer.msg("保存成功" ,{time:1000},function(){
                           $("#addModal").modal('hide');
                           $("#addModal input[name='name']").val("");
                           initData(1);
                       })
                   }else{
                       layer.msg("保存失败")
                   }
               }
            });
        });
    //====模态框添加 结束===============================================

    //=======修改 开始=================================================
        $("tbody").on('click','.updateClass',function () {
            var roleId = $(this).attr("roleId");
            $.get("${PATH}/role/getRoleById",{id:roleId},function(reslut) {
                $("#updateModal").modal({
                    show:true,
                    backdrop:'static',
                    keyboard:false
                });
                $("#updateModal input[name='name']").val(reslut.name);
                $("#updateModal input[name='id']").val(reslut.id);
            });
        });
        $("#updateBtn").click(function(){
            var name = $("#updateModal input[name='name']").val();
            var id = $("#updateModal input[name='id']").val();
            $.post("${PATH}/role/doUpdate",{id:id,name:name},function(result){
                    if ("ok" == result){
                        layer.msg("修改成功",{time:1000},function () {
                            $("#updateModal").modal('hide');
                            $("#updateModal input[name='name']").val("");
                            initData(json.pageNum);
                        });
                    }else{
                        layer.msg("修改失败");
                    }
                }
            );
        })
    //=======修改 结束==================================================

    //=======删除 开始==================================================
        $("tbody").on('click','.deleteClass',function () {
            var id = $(this).attr("roleId");
            layer.confirm("您确定删除嘛？",{btn:['确定','取消']},function (index) {
                $.post("${PATH}/role/doDelete",{id:id},function(result){
                        if ("ok" == result){
                            layer.msg("删除成功",{time:1000},function () {
                                initData(json.pageNum);
                            });
                        }else{
                            layer.msg("删除失败");
                        }
                    }
                );
                layer.close(index);
            },function (index) {
                layer.close(index);
            })
        });
    //=======删除 结束==================================================

    //============给角色分配权限 开始====================================
    var roleId = '';

    $("tbody").on("click",".assignPermissionClass",function(){
        $("#assignModal").modal({
            show:true,
            backdrop:'static',
            keyboard:false
        });

        roleId = $(this).attr("roleId");

        initTree();

    });

    function initTree(){

        var setting = {
            check: {
                enable: true
            },
            data: {
                simpleData: {
                    enable: true,
                    pIdKey: "pid"
                },
                key: {
                    url: "xxx",
                    name:"title"
                }
            },
            view: {
                addDiyDom: function(treeId,treeNode){
                    $("#"+treeNode.tId+"_ico").removeClass();
                    $("#"+treeNode.tId+"_span").before('<span class="'+treeNode.icon+'"></span>');
                }
            }

        };


        //多个异步请求，执行先后顺序问题。

        //1.加载数据
        $.get("${PATH}/permission/listAllPermissionTree",function(data){
            //data.push({"id":0,"title":"系统权限","icon":"glyphicon glyphicon-asterisk"});

            var treeObj = $.fn.zTree.init($("#treeDemo"), setting, data);
            //var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
            treeObj.expandAll(true);

            //2.回显已分配许可
            $.get("${PATH}/role/listPermissionIdByRoleId",{roleId:roleId},function(data){
                $.each(data,function(i,e){
                    var permissionId = e ;
                    var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                    var node = treeObj.getNodeByParam("id", permissionId, null);
                    treeObj.checkNode(node, true, false , false);
                });
            });
        });



    }

    $("#assignBtn").click(function(){

        var json = {
            roleId:roleId
        };

        console.log(roleId);
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        var nodes = treeObj.getCheckedNodes(true);
        $.each(nodes,function(i,e){
            var permissionId = e.id ;
            console.log(permissionId);

            json['ids['+i+']'] = permissionId;

            //多条数据提交和接收
            //json['userList[i].name'] = 'xxx';
            //json['userList[i].age'] = 23;
        });



        $.ajax({
            type:"post",
            url:"${PATH}/role/doAssignPermissionToRole",
            data:json,
            success:function(result){
                if("ok"==result){
                    layer.msg("分配成功",{time:1000},function(){
                        $("#assignModal").modal('hide');
                    });
                }else{
                    layer.msg("分配失败");
                }
            }
        });

    });



    //============给角色分配权限 结束====================================
</script>
</body>
</html>
