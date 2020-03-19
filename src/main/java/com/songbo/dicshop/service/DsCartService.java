package com.songbo.dicshop.service;

import com.songbo.dicshop.entity.DsCart;

public interface DsCartService {

    boolean savecart(DsCart dsCart);

    boolean updatecarts(String userId);
}
