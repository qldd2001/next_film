package com.next.jiangzh.film.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.next.jiangzh.film.dao.entity.NextUser;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Shaka
 * @since 2019-09-11
 */
public interface NextUserMapper extends BaseMapper<NextUser> {
   public List<NextUser> getUsers();

}
