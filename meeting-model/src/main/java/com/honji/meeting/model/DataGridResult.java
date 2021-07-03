package com.honji.meeting.model;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DataGridResult implements Serializable {

    private long total;
    private List<?> rows;

    public DataGridResult(IPage page) {
        this.setTotal(page.getTotal());
        this.setRows(page.getRecords());
    }

    public DataGridResult(long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }
}
