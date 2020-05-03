package com.bdpanajoto.ws.rest.repository;

public class ReportResult {
    private Integer age;
    private Long count;

    public ReportResult(Integer age, Long count) {
        this.age = age;
        this.count = count;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}