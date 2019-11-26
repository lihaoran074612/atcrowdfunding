package com.atguigu.atcrowdfunding.service;

import java.util.List;

public interface TRolePermissionService {
    void doAssignPermissionToRoleDelete(List<Integer> permissionIds, Integer roleId);
}
