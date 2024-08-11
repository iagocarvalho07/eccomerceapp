package com.iagocarvalho.eccomerceapp.product;

import java.util.List;
import java.util.Objects;

public class ProductResponse {
    private List<ProductDTO> content;
    private Integer PageNumbe;

    private Integer pageSize;

    private Long totalElements;
    private Integer totalPgs;
    private Boolean lastPage;


    public ProductResponse() {
    }

    public ProductResponse(List<ProductDTO> content, Integer pageNumbe, Integer pageSize, Long totalElements, Integer totalPgs, Boolean lastPage) {
        this.content = content;
        PageNumbe = pageNumbe;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPgs = totalPgs;
        this.lastPage = lastPage;
    }

    public List<ProductDTO> getContent() {
        return content;
    }

    public void setContent(List<ProductDTO> content) {
        this.content = content;
    }

    public Integer getPageNumbe() {
        return PageNumbe;
    }

    public void setPageNumbe(Integer pageNumbe) {
        PageNumbe = pageNumbe;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPgs() {
        return totalPgs;
    }

    public void setTotalPgs(Integer totalPgs) {
        this.totalPgs = totalPgs;
    }

    public Boolean getLastPage() {
        return lastPage;
    }

    public void setLastPage(Boolean lastPage) {
        this.lastPage = lastPage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductResponse that = (ProductResponse) o;
        return Objects.equals(content, that.content) && Objects.equals(PageNumbe, that.PageNumbe) && Objects.equals(pageSize, that.pageSize) && Objects.equals(totalElements, that.totalElements) && Objects.equals(totalPgs, that.totalPgs) && Objects.equals(lastPage, that.lastPage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, PageNumbe, pageSize, totalElements, totalPgs, lastPage);
    }
}
