package com.yzy.dao;

import org.apache.ibatis.annotations.Param;

import com.yzy.entity.SysRole;

import java.util.List;

public interface SysRoleMapper {
    //新增
    public Long insert(SysRole SysRole);

    //更新
    public void update(SysRole SysRole);

    //通过对象进行查询
    public SysRole select(SysRole SysRole);

    //通过id进行查询
    public SysRole selectById(@Param("id") Long id);

    //查询全部
    public List<SysRole> selectAll();

    //查询数量
    public int selectCounts();

    boolean isExsitName(@Param("name") String name);

    boolean isExsitNameExcludeId(@Param("id") long id, @Param("name") String name);

	public SysRole selectByUserId(@Param("userId") long userId);

	public int deleteById(@Param("id") long id);

	public SysRole selectByLogAdminUserId(@Param("logAdminUserId") String logAdminUserId);

    List<SysRole> selectByParentId(@Param("parentId") Long parentId);
}