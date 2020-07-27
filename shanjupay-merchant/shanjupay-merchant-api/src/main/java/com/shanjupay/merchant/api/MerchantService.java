package com.shanjupay.merchant.api;

import com.shanjupay.merchant.api.dto.MerchantDTO;

/**
 * Created by Administrator.
 */
public interface MerchantService {

    //根据 id查询商户
    public MerchantDTO queryMerchantById(Long id);

    /**
     * 商户注册
     * @param merchantDTO
     * @return
     */
    MerchantDTO createMerchant(MerchantDTO merchantDTO) throws RuntimeException ;
}
