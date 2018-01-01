/**
 * Created by liuyandong on 2017/6/22.
 */
function EasyUIExt(dataGrid, url) {
    //DataGrid默认属性参数定义
    /**
     * dataGrid数据表格
     * */
    this.dataGrid = dataGrid;
    /**
     * 数据表格加载数据的URL
     * */
    this.dataUrl = url;

    /**
     * 数据表格的添加或编辑弹出窗口
     * */
    this.window = null;
    /**
     * 数据表格的添加或编辑Form
     * */
    this.form = null;
    this.iconCls = "icon-edit";
    this.width = "auto";
    this.height = "auto";
    this.nowrap = false;
    this.striped = true;
    this.border = true;
    this.collapsible = false;
    this.fit = true;
    this.remoteSort = false;
    this.rownumbers = true;
    this.singleSelect = false;
    this.pagination = true;
    this.showFooter = false;
    this.ajaxcache = false;
    this.queryParams = null;
    this.page_size = 20;
    /**
     * 行点击事件函数接口;
     **/
    this.clickRow;
    /**
     * 行双击点击事件函数接口;
     **/
    this.dblClickRow;
    /**
     * DataGrid加载完成事件函数接口;
     **/
    this.loadSuccess;
    /**
     *单击datagrid单元格时触发事件
     */
    this.clickCell;
    /**
     * 勾选一行时触发
     */
    this.check;
    this.uncheck;
    /**
     * 全选时触发
     */
    this.selectAll;
    /**
     * 取消全选时触发
     */
    this.unSelectAll;
    /**
     * 设置行样式
     */
    this.rowStyle;
    this.endEdit;
    this.formAction = "";
    this.reset = true
    this.mainEasyUIJs = function () {
        var dom = this;
        this.dataGrid.datagrid({
            title: this.title,
            iconCls: this.iconCls,// 图标
            width: this.width,
            height: this.height,
            nowrap: this.nowrap,
            striped: this.striped,
            border: this.border,
            collapsible: this.collapsible,// 是否可折叠的
            fit: this.fit,// 自动大小
            singleSelect: this.singleSelect, // 是否单选
            url: this.dataUrl,
            checkOnSelect: true,
            method: "GET",
            onLoadSuccess: function (data) {
                $(this).datagrid('clearSelections');
                var selector = dom.dataGrid.selector;
                if (dom.singleSelect) {
                    $(selector).parent().find(".datagrid-header-check").empty()
                }
                //这处理公用业务
                if (dom.loadSuccess) {
                    dom.loadSuccess(data);
                }
            },
            onClickRow: this.clickRow,
            onClickCell: this.clickCell,
            onDblClickRow: this.dblClickRow,
            onEndEdit: this.endEdit,
            onCheck: this.check,
            onUncheck: this.uncheck,
            onSelectAll: this.selectAll,
            onUnselectAll: this.unSelectAll,
            rowStyler: this.rowStyle,
            onLoadError: function () {
                $.messager.alert('系统提示', "操作失败，数据加载错误请稍后再试！", 'error');
            },
            remoteSort: this.remoteSort,
            showFooter: this.showFooter,
            pagination: this.pagination,// 分页控件
            rownumbers: this.rownumbers,// 行号
            queryParams: this.queryParams,
            pageSize: this.page_size,
            pageList: [5, 10, 20, 35, 50, 100]
        }).datagrid('unselectAll');
        var pg = this.dataGrid.datagrid("getPager");
        var opts = this.dataGrid.datagrid("options");
        var tempDataGrid = this.dataGrid;
        pg.pagination({
            onSelectPage: function (pageNumber, pageSize) {
                opts.pageNumber = pageNumber;
                opts.pageSize = pageSize;
                tempDataGrid.datagrid('reload').datagrid('unselectAll');
            }
        });
    };
}