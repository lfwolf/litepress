layui.use(['layer', 'form', 'table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 系统管理--横幅管理
     */
    var CmsBanner = {
        tableId: "CmsBannerTable"    //表格id
    };

    /**
     * 初始化表格的列
     */
    CmsBanner.initColumn = function () {
        return [[
            {type: 'checkbox'},
            
            {title: '主键id', field: 'id', hide: true, sort: true},
            {title: '简称', field: 'name', sort: true},
            {title: '横幅图片', field: 'cover', sort: true},
            {title: '链接地址', field: 'url', sort: true},
            {title: '排序', field: 'sort', sort: true},
            {title: '状态', field: 'status', sort: true, templet:function(d){
            	return d.status == "ENABLE" ? "启用" : "停用" ;
            }}
            ,{align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    CmsBanner.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(CmsBanner.tableId, {where: queryData});
    };

    /**
     * 弹出添加横幅管理
     */
    CmsBanner.openAddCmsBanner = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加横幅管理',
            content: Feng.ctxPath + '/CmsBanner/CmsBanner_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(CmsBanner.tableId);
            }
        });
    };

    /**
     * 点击编辑横幅管理
     *
     * @param data 点击按钮时候的行数据
     */
    CmsBanner.onEditCmsBanner = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '横幅管理详情',
            content: Feng.ctxPath + '/CmsBanner/CmsBanner_update/' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(CmsBanner.tableId);
            }
        });
    };

    /**
     * 点击删除横幅管理
     *
     * @param data 点击按钮时候的行数据
     */
    CmsBanner.onDeleteCmsBanner = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/CmsBanner/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(CmsBanner.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除横幅管理 ?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + CmsBanner.tableId,
        url: Feng.ctxPath + '/CmsBanner/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: CmsBanner.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        CmsBanner.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        CmsBanner.openAddCmsBanner();
    });

    // 工具条点击事件
    table.on('tool(' + CmsBanner.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            CmsBanner.onEditCmsBanner(data);
        } else if (layEvent === 'delete') {
            CmsBanner.onDeleteCmsBanner(data);
        }
    });
});

