package com.shanjupay.merchant.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shanjupay.common.domain.BusinessException;
import com.shanjupay.common.domain.CommonErrorCode;
import com.shanjupay.common.util.PhoneUtil;
import com.shanjupay.merchant.api.MerchantService;
import com.shanjupay.merchant.api.dto.MerchantDTO;
import com.shanjupay.merchant.entity.Merchant;
import com.shanjupay.merchant.convert.MerchantConvert;
import com.shanjupay.merchant.mapper.MerchantMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@org.apache.dubbo.config.annotation.Service
@Slf4j
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    MerchantMapper merchantMapper;

    @Override
    public MerchantDTO queryMerchantById(Long id) {
        Merchant merchant = merchantMapper.selectById(id);
        MerchantDTO merchantDTO = new MerchantDTO();
        merchantDTO.setId(merchant.getId());
        merchantDTO.setMerchantName(merchant.getMerchantName());
        //....
        return merchantDTO;
    }

    @Override
    @Transactional
    public MerchantDTO createMerchant(MerchantDTO merchantDTO)  throws RuntimeException{

        if(merchantDTO == null){
            throw new BusinessException(CommonErrorCode.E_100108);
        }
        if(StringUtils.isBlank(merchantDTO.getMobile())){
            throw new BusinessException(CommonErrorCode.E_100112);
        }

        //手机号格式校验
        if(!PhoneUtil.isMatches(merchantDTO.getMobile())){
            throw new BusinessException(CommonErrorCode.E_100109);
        }
        //校验手机号的唯一性
        //根据手机号查询商户表，如果存在记录则说明手机号已存在
        Integer count = merchantMapper.selectCount(new LambdaQueryWrapper<Merchant>().eq(Merchant::getMobile, merchantDTO.getMobile()));
        if(count>0){
            throw new BusinessException(CommonErrorCode.E_100113);
        }

        //将dto转成entity
        Merchant merchant = MerchantConvert.INSTANCE.dto2entity(merchantDTO);
        //设置审核状态0‐未申请,1‐已申请待审核,2‐审核通过,3‐审核拒绝
        merchant.setAuditStatus("0");
        //设置手机号
        merchant.setMobile(merchantDTO.getMobile());
        //保存商户信息
        merchantMapper.insert(merchant);
        //将entity转成 dto
//        MerchantDTO merchantDTONew = MerchantConvert.INSTANCE.entity2dto(entity);
//        return merchantDTONew;
        return MerchantConvert.INSTANCE.entity2dto(merchant);
    }

}
