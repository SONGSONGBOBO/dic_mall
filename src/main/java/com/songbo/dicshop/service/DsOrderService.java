package com.songbo.dicshop.service;

import com.songbo.dicshop.entity.DsOrder;

import java.util.List;

public interface DsOrderService {

    DsOrder getOrderById(int id);

    List<DsOrder> getOdersByUserId(int userId);

    boolean updateOrder(DsOrder dsOrder);

    boolean saveOrder(DsOrder dsOrder);
}
