layui.use(['layer', 'form', 'table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 系统管理--专辑管理
     */
    var CmsAlbum = {
        tableId: "CmsAlbumTable"    //表格id
    };

    /**
     * 初始化表格的列
     */
    CmsAlbum.initColumn = function () {
        return [[
            {type: 'checkbox'},
            
            {title: '主键id', field: 'id',hide: true, sort: true},
            {title: '简称', field: 'name', sort: true},
            {title: '描述', field: 'description', sort: true},
            {title: '专辑图片', field: 'cover', sort: true},
            {title: '来源', field: 'source', sort: true},
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
    CmsAlbum.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(CmsAlbum.tableId, {where: queryData});
    };

    /**
     * 弹出添加专辑管理
     */
    CmsAlbum.openAddCmsAlbum = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加专辑管理',
            content: Feng.ctxPath + '/CmsAlbum/CmsAlbum_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(CmsAlbum.tableId);
            }
        });
    };

    /**
     * 点击编辑专辑管理
     *
     * @param data 点击按钮时候的行数据
     */
    CmsAlbum.onEditCmsAlbum = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '专辑管理详情',
            content: Feng.ctxPath + '/CmsAlbum/CmsAlbum_update/' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(CmsAlbum.tableId);
            }
        });
    };

    /**
     * 点击删除专辑管理
     *
     * @param data 点击按钮时候的行数据
     */
    CmsAlbum.onDeleteCmsAlbum = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/CmsAlbum/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(CmsAlbum.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除专辑管理 ?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + CmsAlbum.tableId,
        url: Feng.ctxPath + '/CmsAlbum/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: CmsAlbum.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        CmsAlbum.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        CmsAlbum.openAddCmsAlbum();
    });

    // 工具条点击事件
    table.on('tool(' + CmsAlbum.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            CmsAlbum.onEditCmsAlbum(data);
        } else if (layEvent === 'delete') {
            CmsAlbum.onDeleteCmsAlbum(data);
        }
    });
});

