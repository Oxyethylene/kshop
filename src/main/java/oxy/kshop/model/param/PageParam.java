package oxy.kshop.model.param;

public class PageParam {
    private int page;
    private int size;

    public PageParam(String s) {
        String[] split = s.split(",");
        if (split.length < 2) {
            split = new String[]{"1", "10"};
        }
        this.page = Integer.parseInt(split[0]);
        this.size = Integer.parseInt(split[1]);
    }

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

    @Override
    public String toString() {
        return "PageParam{" +
                "page=" + page +
                ", size=" + size +
                '}';
    }
}
