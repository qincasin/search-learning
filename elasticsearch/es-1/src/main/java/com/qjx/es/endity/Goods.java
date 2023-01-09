package com.qjx.es.endity;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author qinjiaxing on 2023/1/9
 */
@Data
@Accessors(chain = true)   // 链式赋值(连续set方法)
@AllArgsConstructor        // 全参构造
@NoArgsConstructor         // 无参构造
public class Goods {

    /**
     * 商品编号
     */
    private Long id;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品库存
     */
    private Integer stock;

    /**
     * 商品销售数量
     */
    private Integer saleNum;

    /**
     * 商品分类
     */
    private String categoryName;

    /**
     * 商品品牌
     */
    private String brandName;

    /**
     * 上下架状态
     */
    private Integer status;

    /**
     * 商品创建时间
     */
    private Date createTime;
}