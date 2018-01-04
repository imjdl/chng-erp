package cn.com.chng.erp.models.powerstation;

import cn.com.chng.erp.basic.BasicModel;

import javax.validation.constraints.NotNull;

public class ListModel extends BasicModel {
    @NotNull
    private Integer page;

    @NotNull
    private Integer rows;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
