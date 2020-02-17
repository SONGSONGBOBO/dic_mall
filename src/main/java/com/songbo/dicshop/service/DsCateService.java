package com.songbo.dicshop.service;

import com.songbo.dicshop.entity.DsCate;

public interface DsCateService {

    boolean saveCate(DsCate dsCate);

    boolean updateCates(String userId);
}
