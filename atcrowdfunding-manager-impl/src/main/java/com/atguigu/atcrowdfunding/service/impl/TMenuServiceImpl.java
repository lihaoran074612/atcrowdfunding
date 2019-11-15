package com.atguigu.atcrowdfunding.service.impl;

import com.atguigu.atcrowdfunding.bean.TMenu;
import com.atguigu.atcrowdfunding.mapper.TMenuMapper;
import com.atguigu.atcrowdfunding.service.TMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TMenuServiceImpl implements TMenuService {

    @Autowired
    TMenuMapper tMenuMapper;


    @Override
    public List<TMenu> listMenuAll() {
        return null;
    }
}
