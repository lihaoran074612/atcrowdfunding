package com.atguigu.atcrowdfunding.service;

import com.atguigu.atcrowdfunding.bean.TMenu;

import java.util.List;

public interface TMenuService {
    List<TMenu> listMenuAll();

    List<TMenu> listMenuAllTree();

    void saveTMenu(TMenu menu);
}
