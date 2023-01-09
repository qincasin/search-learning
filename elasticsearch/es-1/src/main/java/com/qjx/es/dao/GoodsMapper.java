package com.qjx.es.dao;

import com.qjx.es.endity.Goods;
import java.util.List;
import org.apache.ibatis.annotations.Select;

/**
 * @author qinjiaxing on 2023/1/9
 */
public interface GoodsMapper {
    @Select("SELECT * FROM goods WHERE id = #{id}")
    Goods getGoods(int id);

    @Select("SELECT * FROM goods")
    List<Goods> findAll();
}