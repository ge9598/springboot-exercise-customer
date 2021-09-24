package com.xiaoge.springbootdemo.customer.response;

import com.xiaoge.springbootdemo.customer.Pojo.Customer;
import lombok.Data;

import java.util.List;
@Data
public class PageResponse {
    private int totalRows;
    private int pageNum;
    private List<Customer> data;

}

