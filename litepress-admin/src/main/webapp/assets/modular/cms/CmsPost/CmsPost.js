layui.use(['layer', 'form', 'table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 系统管理--文章管理
     */
    var CmsPost = {
        tableId: "CmsPostTable"    //表格id
    };

    /**
     * 初始化表格的列
     */
    CmsPost.initColumn = function () {
        return [[
            {type: 'checkbox'},
            
            {title: '主键id', field: 'id', hide: true, sort: true},
            {title: '简称', field: 'name', sort: true},
         
            {title: '封面图片地址', field: 'cover', templet:function (d) {
            	return d == null ? '' : "<img src='"+ d.cover + "' />";
            }
            },
            {title: '媒体地址mp3', field: 'media', sort: true},
            {title: '来源(荔枝/ienglisth)', field: 'source', sort: true},
            {title: '链接地址', field: 'url', sort: true},
            {title: '排序', field: 'sort', sort: true},
            {title: '状态', field: 'status', sort: true,templet:function (d) {
            	if(d.status == 'ENABLE') return '启用'; else return '停用';
            }},
            {title: '专辑表id', field: 'albumId', sort: true}
            ,{align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    CmsPost.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(CmsPost.tableId, {where: queryData});
    };

    /**
     * 弹出添加文章管理
     */
    CmsPost.openAddCmsPost = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加文章管理',
            content: Feng.ctxPath + '/CmsPost/CmsPost_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(CmsPost.tableId);
            }
        });
    };

    /**
     * 点击编辑文章管理
     *
     * @param data 点击按钮时候的行数据
     */
    CmsPost.onEditCmsPost = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '文章管理详情',
            content: Feng.ctxPath + '/CmsPost/CmsPost_update/' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(CmsPost.tableId);
            }
        });
    };

    /**
     * 点击删除文章管理
     *
     * @param data 点击按钮时候的行数据
     */
    CmsPost.onDeleteCmsPost = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/CmsPost/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(CmsPost.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除文章管理 ?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + CmsPost.tableId,
        url: Feng.ctxPath + '/CmsPost/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: CmsPost.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        CmsPost.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        CmsPost.openAddCmsPost();
    });

    // 工具条点击事件
    table.on('tool(' + CmsPost.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            CmsPost.onEditCmsPost(data);
        } else if (layEvent === 'delete') {
            CmsPost.onDeleteCmsPost(data);
        }
    });
});

