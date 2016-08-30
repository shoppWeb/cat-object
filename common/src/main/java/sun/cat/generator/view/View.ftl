<%--
Created by IntelliJ IDEA.
User: 小小工作者
Date: ${.now}
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Matrix Admin</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <jsp:include page="/view/pc/common/matrix.jsp"></jsp:include>
    <script src="/resource/matrix/js/jquery.min.js"></script>
    <jsp:include page="/view/pc/common/dtGrid.jsp"></jsp:include>
    <jsp:include page="/view/pc/common/layer.jsp"></jsp:include>
    <script src="/resource/plugin/laydate/laydate.js"></script>
    <link rel="stylesheet" href="/resource/matrix/css/clueschool.css"/>
    <link rel="stylesheet" href="/resource/matrix/css/zhuanyong.css"/>
</head>
<body>
<div id="content">
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <button class="btn btn-success" id="add">
                    <i class="icon-plus align-top bigger-125"></i>
                    添加
                    </button>
                    <button class="btn btn-danger" id="delete">
                        <i class="icon-remove align-top bigger-125"></i>
                        删除
                    </button>

                    <button class="btn btn-warning" id="updateClueSchool">
                    <i class="icon-pencil align-top bigger-125"></i>
                    修改
                    </button>
                    <button class="btn btn-primary" id="uploading">
                        <i class="icon-pencil align-top bigger-125"></i>
                        导入
                    </button>

                </div>
                <div class="main">

                </div>
                <div id="dtGridContainer" class="dt-grid-container"></div>
                <div id="dtGridToolBarContainer" class="dt-grid-toolbar-container"></div>
            </div>
        </div>
    </div>
</div>
</div>
<script>
    var returnDate = '<a href="javascript:void(0);" title="返回首页" class="tip-bottom"><i class="icon-home"></i> 首页</a><a href="javascript:void(0);" title="幼儿园页面" class="tip-bottom"> 幼儿园页面</a>';
    $('#breadcrumb', parent.document).html(returnDate);
    var dtGridColumns = [
        {
            id: 'id',
            title: '主键',
            hide: true,
            type: 'string',
            columnClass: 'text-center',
            headerStyle: 'background:#00a2ca;color:white;'
        },
        {
            id: 'schoolName',
            title: '学校名称',
            type: 'string',
            columnClass: 'text-center',
            headerStyle: 'background:#00a2ca;color:white;width:190px;'
        },
        {
            id: 'stautsIntro',
            title: '状态',
            type: 'string',
            columnClass: 'text-center',
            headerStyle: 'background:#00a2ca;color:white;'
        },
        {
            id: 'dicNatureValue',
            title: '园所性质',
            type: 'string',
            columnClass: 'text-center',
            headerStyle: 'background:#00a2ca;color:white;width:100px;'
        },
        {
            id: 'classNumber',
            title: '班级数量',
            type: 'int',
            columnClass: 'text-center',
            headerStyle: 'background:#00a2ca;color:white;'
        },
        {
            id: 'studentNumber',
            title: '学生数量',
            type: 'int',
            columnClass: 'text-center',
            headerStyle: 'background:#00a2ca;color:white;'
        },
        {
            id: 'dicTeacherCapacityValue',
            title: '师资力量',
            type: 'string',
            columnClass: 'text-center',
            headerStyle: 'background:#00a2ca;color:white;'
        },
        {
            id: 'grade',
            title: '园所级别',
            type: 'string',
            columnClass: 'text-center',
            headerStyle: 'background:#00a2ca;color:white;'
        },
        {
            id: 'tbBaseProvinceName',
            title: '省',
            type: 'string',
            columnClass: 'text-center',
            headerStyle: 'background:#00a2ca;color:white;'
        },
        {
            id: 'tbBaseCityName',
            title: '市',
            type: 'string',
            columnClass: 'text-center',
            headerStyle: 'background:#00a2ca;color:white;'
        },
        {
            id: 'tbRbacUserName',
            title: '城市经理',
            type: 'string',
            columnClass: 'text-center',
            headerStyle: 'background:#00a2ca;color:white;'
        },
    ];
    var dtGridOption = {
        lang: 'zh-cn',
        ajaxLoad: true,
        check: true,
        loadURL: '/pc/clueSchool/pagingFindClueSchool',
        exportFileName: '幼儿园列表',
        columns: dtGridColumns,
        gridContainer: 'dtGridContainer',
        toolbarContainer: 'dtGridToolBarContainer',
        tools: '',
        pageSize: 20,
        pageSizeLimit: [10, 20, 50]
    };
    var grid = $.fn.DtGrid.init(dtGridOption);
    $(function () {
        grid.load();
    });

</script>

</body>
</html>

