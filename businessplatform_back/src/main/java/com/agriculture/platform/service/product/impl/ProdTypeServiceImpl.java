package com.agriculture.platform.service.product.impl;

import com.agriculture.platform.dao.product.ProductTypeDao;
import com.agriculture.platform.pojo.base.Do.ProductTypeDo;
import com.agriculture.platform.service.product.ProdTypeService;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author Junbo Wang
 * @description
 * @date 2019/5/5
 */
@Service
public class ProdTypeServiceImpl implements ProdTypeService {

    @Resource
    private ProductTypeDao productTypeDao;

    @Override
    public List<ProductTypeDo> selectProdTypeList(ProductTypeDo productTypeDo) {
        if (productTypeDo == null) {
            return productTypeDao.selectProdTypeList(new ProductTypeDo());
        } else {
            return productTypeDao.selectProdTypeList(productTypeDo);
        }
    }

    @Override
    public ProductTypeDo selectProdType(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        ProductTypeDo queryProdType = new ProductTypeDo();
        queryProdType.setCode(code);
        List<ProductTypeDo> resultList = this.selectProdTypeList(queryProdType);
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }
}
