package com.songbo.dicshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.songbo.dicshop.entity.DsOption;

public interface DsOptionMapper extends BaseMapper<DsOption> {

    DsOption getOption();
}
