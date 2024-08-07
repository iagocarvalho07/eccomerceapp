package com.iagocarvalho.eccomerceapp.categories;

import java.util.List;


public class CategoryResponse {

    private List<CategoryDTO> content;
    private Integer PageNumbe;

    private Integer pageSize;

    private Long totalElements;
    private Integer totalPgs;
    private Boolean lastPage;

    public CategoryResponse() {
    }

    public CategoryResponse(List<CategoryDTO> content, Integer pageNumbe, Integer pageSize, Long totalElements, Integer totalPgs, Boolean lastPage) {
        this.content = content;
        PageNumbe = pageNumbe;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPgs = totalPgs;
        this.lastPage = lastPage;
    }

    public List<CategoryDTO> getContent() {
        return content;
    }

    public void setContent(List<CategoryDTO> content) {
        this.content = content;
    }

    public Integer getPageNumbe() {
        return PageNumbe;
    }

    public void setPageNumbe(Integer pageNumbe) {
        PageNumbe = pageNumbe;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
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
}
