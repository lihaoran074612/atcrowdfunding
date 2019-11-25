<%--
  Created by IntelliJ IDEA.
  User: haoran
  Date: 2019/11/15
  Time: 23:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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
    <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
                <h4 class="modal-title" id="myModalLabel"> 添加菜单 </h4>
                </div>
                <div class="modal-body">
                    <form role="form">
                        <div class="form-group">
                            <label for="name">菜单名称</label>
                            <input type="hidden" name="pid">
                            <input type="text" class="form-control" id="name" name="name" placeholder="请输入菜单名称">
                        </div>
                        <div class="form-group">
                        <label for="url">菜单url</label>
                        <input type="text" class="form-control" id="url" name="url" placeholder="请输入菜单URL">
                        </div>
                        <div class="form-group">
                        <label for="icon">菜单图标</label>
                        <input type="text" class="form-control" id="icon" name="icon" placeholder="请输入菜单图标">
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
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
                <h4 class="modal-title" id="myModalLabel"> 修改菜单 </h4>
                </div>
            <div class="modal-body">
                <form role="form">
                    <div class="form-group">
                        <label for="name">菜单名称</label>
                        <input type="hidden" name="id">
                        <input type="text" class="form-control" id="name" name="name" placeholder="请输入菜单名称">
                    </div>
                    <div class="form-group">
                        <label for="url">菜单url</label>
                        <input type="text" class="form-control" id="url" name="url" placeholder="请输入菜单URL">
                    </div>
                    <div class="form-group">
                        <label for="icon">菜单图标</label>
                        <input type="text" class="form-control" id="icon" name="icon" placeholder="请输入菜单图标">
                    </div>
                </form>
            </div>
                <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="updateBtn" class="btn btn-primary">修改</button>
                </div>
            </div>
        </div>
    </div>


<jsp:include page="/WEB-INF/jsp/common/top.jsp"></jsp:include>

<div class="container-fluid">
    <div class="row">
        <%@ include file="/WEB-INF/jsp/common/sidebar.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i>菜单树</h3>
                </div>
                <div class="panel-body">
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
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
        initTree();
    });

    function initTree() {
        var setting = {
            data: {
                simpleData: {
                    enable: true,
                    pIdKey:"pid"
                }
            },
            view: {
                addDiyDom:function(treeId,treeNode) {
                    $("#"+treeNode.tId+"_ico").removeClass();
                    $("#"+treeNode.tId+"_span").before("<span class='"+treeNode.icon+"'></span>")
                },
                addHoverDom: function(treeId, treeNode){
                    var aObj = $("#" + treeNode.tId + "_a"); // tId = permissionTree_1, ==> $("#permissionTree_1_a")
                    aObj.attr("href", "javascript:;");
                    if (treeNode.editNameFlag || $("#btnGroup"+treeNode.tId).length>0) return;
                    var s = '<span id="btnGroup'+treeNode.tId+'">';
                    if ( treeNode.level == 0 ) {
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick="addBtn('+treeNode.id+')">&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
                    } else if ( treeNode.level == 1 ) {
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  onclick="updateBtn('+treeNode.id+')" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
                        if (treeNode.children.length == 0) {
                            s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick= "deletBtn('+treeNode.id+')" >&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
                    }
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick="addBtn('+treeNode.id+')" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
                    } else if ( treeNode.level == 2 ) {
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  onclick="updateBtn('+treeNode.id+')" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick= "deleteBtn('+treeNode.id+')">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
                }

                    s += '</span>';
                    aObj.after(s);
                },
                removeHoverDom: function(treeId, treeNode){
                    $("#btnGroup"+treeNode.tId).remove();
                }
            }
        };

        $.get(
            "${PATH}/menu/loadTree",
             {},
             function(result) {
                var zNodes = result;
                zNodes.push({id:0, name:"root",icon:"glyphicon glyphicon-th-list"});
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                treeObj.expandAll(true);
             }
        )
    }

    //================添加开始===============================
    function addBtn(id) {
        $("#addModal").modal({
            show: true,
            backdrop: 'static',
            keyBoard: false,
        });
        $("#addModal input[name='pid']").val(id);
        }

    $("#saveBtn").click(function() {
        var pid = $("#addModal input[name='pid']").val();
        var name = $("#addModal input[name='name']").val();
        var url = $("#addModal input[name='url']").val();
        var icon = $("#addModal input[name='icon']").val();
        $.ajax({
            type:"post",
            url:"${PATH}/menu/doAdd",
            data : {
                name:name,
                pid: pid,
                url:url,
                icon:icon
            },
            beforeSend:function() {
                return true;
            },
            success:function(result) {
                if('ok' == result){
                    layer.msg("保存成功" ,{time:1000},function(){
                    $("#addModal").modal('hide');
                    $("#addModal input[name='name']").val("");
                    $("#addModal input[name='icon']").val("");
                    $("#addModal input[name='url']").val("");
                    $("#addModal input[name='pid']").val("");
                    initTree();
                 })
                }else{
                    layer.msg("保存失败")
                }
            }
        });
    });

    //================添加结束===============================

    //================修改开始===============================

    function updateBtn(id) {
        $.get("${PATH}/menu/getMenuById",{id:id},function(reslut) {
            $("#updateModal").modal({
                show:true,
                backdrop:'static',
                keyboard:false
            });
            $("#updateModal input[name='name']").val(reslut.name);
            $("#updateModal input[name='id']").val(reslut.id);
            $("#updateModal input[name='icon']").val(reslut.icon);
            $("#updateModal input[name='url']").val(reslut.url);
        });
    }

    $("#updateBtn").click(function() {
        var id = $("#updateModal input[name='id']").val();
        var name = $("#updateModal input[name='name']").val();
        var url = $("#updateModal input[name='url']").val();
        var icon = $("#updateModal input[name='icon']").val();
        $.ajax({
            type:"post",
            url:"${PATH}/menu/doUpdate",
            data : {
                name:name,
                id: id,
                url:url,
                icon:icon
            },
            beforeSend:function() {
                return true;
            },
            success:function(result) {
                if('ok' == result){
                    layer.msg("修改成功" ,{time:1000},function(){
                        $("#updateModal").modal('hide');
                        $("#updateModal input[name='name']").val("");
                        $("#updateModal input[name='icon']").val("");
                        $("#updateModal input[name='url']").val("");
                        $("#updateModal input[name='pid']").val("");
                        initTree();
                    })
                }else{
                    layer.msg("修改失败")
                }
            }
        });
    });

    //================修改结束===============================

    //================删除开始===============================
    function deleteBtn(id) {
        layer.confirm("您确定删除嘛？",{btn:['确定','取消']},function (index) {
            $.post("${PATH}/menu/doDelete",{id:id},function(result){
                if ("ok" == result){
                layer.msg("删除成功",{time:1000},function () {
                    initTree();
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
    }
    //================删除结束===============================

    </script>
</body>
</html>