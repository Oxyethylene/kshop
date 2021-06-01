package oxy.kshop.model.result;

import java.util.List;

public class PageResult<T> {
    private int page;
    private int size;
    private long total;
    private List<T> data;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "page=" + page +
                ", size=" + size +
                ", total=" + total +
                ", data=" + data +
                '}';
    }
}
