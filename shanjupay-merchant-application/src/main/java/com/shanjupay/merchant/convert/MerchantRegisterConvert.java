package com.shanjupay.merchant.convert;

import com.shanjupay.merchant.api.dto.MerchantDTO;
import com.shanjupay.merchant.vo.MerchantRegisterVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 将商户注册vo和dto进行转换
 * Created by Administrator.
 */
@Mapper
public interface MerchantRegisterConvert {

    MerchantRegisterConvert INSTANCE = Mappers.getMapper(MerchantRegisterConvert.class);

    //将dto转成vo
    MerchantRegisterVO dto2vo(MerchantDTO merchantDTO);
    //将vo转成dto
    MerchantDTO vo2dto(MerchantRegisterVO merchantRegisterVO);

    public static void main(String[] args) {
        // 将dto 转 vo
        MerchantDTO merchantDTO = new MerchantDTO();
        merchantDTO.setMobile("18679427254");
        merchantDTO.setPassword("123456");

        MerchantRegisterVO merchantRegisterVO = MerchantRegisterConvert.INSTANCE.dto2vo(merchantDTO);
        System.out.println(merchantRegisterVO);

    }

}
