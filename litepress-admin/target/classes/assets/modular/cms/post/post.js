layui.use(['table', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;

    /**
     * 基础字典管理
     */
    var Post = {
        tableId: "postTable"
    };

    /**
     * 初始化表格的列
     */
    Post.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'postId', hide: true, title: '文章id'},
            {field: 'name', sort: true, title: '文章名称'},
            {
            	field: 'cover', sort: false, title: '文章图片',
            	templet: function (d) {
            		return "<img src='"+d.cover+"' />"
            	}
            },
            {field: 'url', sort: false, title: '链接地址'},
            {field: 'source', sort: false, title: '文章来源'},
            {
                field: 'status', sort: true, title: '状态', templet: function (d) {
                    if (d.status === 'ENABLE') {
                        return "启用";
                    } else {
                        return "禁用";
                    }
                }
            },
            {field: 'createTime', sort: true, title: '创建时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Post.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        Post.initTable(Post.tableId, queryData);
    };

    /**
     * 弹出添加对话框
     */
    Post.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/cms/post/add';
    };

    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    Post.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/cms/post/edit?postId=' + data.postId;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Post.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/cms/post/delete", function (data) {
                Feng.success("删除成功!");
                Post.search();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("postId", data.postId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    /**
     * 渲染表格
     */
    Post.initTable = function (postId, data) {
    	return table.render({
            elem: '#' + postId,
            url: Feng.ctxPath + '/cms/post/list',
            where: data,
            height: "full-98",
            cellMinWidth: 100,
            cols: Post.initColumn(),
            page: false
        });

    };

    Post.initTable(Post.tableId);

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
    	Post.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
    	Post.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
    	Post.exportExcel();
    });

    // 关闭页面
    $('#btnBack').click(function () {
        window.location.href = Feng.ctxPath + "/postType";
    });

    // 工具条点击事件
    table.on('tool(' + Post.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
        	Post.openEditDlg(data);
        } else if (layEvent === 'delete') {
        	Post.onDeleteItem(data);
        }
    });
});
