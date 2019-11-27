package com.atguigu.atcrowdfunding.service;

import com.atguigu.atcrowdfunding.bean.TPermission;

import java.util.List;

public interface TPermissionService {
    List<TPermission> listMenuAllTree();

    void savePermission(TPermission permission);

    void deletePermission(Integer id);

    void editPermission(TPermission permission);

    TPermission getPermissionById(Integer id);

    List<TPermission> getPermissionByMenuid(Integer mid);

    void assignPermissionToMenu(Integer mid, List<Integer> perIdArray);
}
