layui.use(['layer', 'form', 'table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 系统管理--分类管理
     */
    var Catalog = {
        tableId: "CatalogTable"    //表格id
    };

    /**
     * 初始化表格的列
     */
    Catalog.initColumn = function () {
        return [[
            {type: 'checkbox'},
            
            {title: '主键', field: 'id', sort: true},
            {title: '封面图片', field: 'cover', sort: true},
            {title: '名称', field: 'name', sort: true},
            {title: '链接地址', field: 'url', sort: true}
            ,{align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Catalog.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(Catalog.tableId, {where: queryData});
    };

    /**
     * 弹出添加分类管理
     */
    Catalog.openAddCatalog = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加分类管理',
            content: Feng.ctxPath + '/Catalog/Catalog_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Catalog.tableId);
            }
        });
    };

    /**
     * 点击编辑分类管理
     *
     * @param data 点击按钮时候的行数据
     */
    Catalog.onEditCatalog = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '分类管理详情',
            content: Feng.ctxPath + '/Catalog/Catalog_update/' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(Catalog.tableId);
            }
        });
    };

    /**
     * 点击删除分类管理
     *
     * @param data 点击按钮时候的行数据
     */
    Catalog.onDeleteCatalog = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/Catalog/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Catalog.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除分类管理 ?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Catalog.tableId,
        url: Feng.ctxPath + '/Catalog/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: Catalog.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Catalog.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Catalog.openAddCatalog();
    });

    // 工具条点击事件
    table.on('tool(' + Catalog.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Catalog.onEditCatalog(data);
        } else if (layEvent === 'delete') {
            Catalog.onDeleteCatalog(data);
        }
    });
});

