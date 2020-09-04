package com.chinadass.api.service.impl;

import com.chinadass.api.common.enums.ResultCode;
import com.chinadass.api.common.exception.BusinessException;
import com.chinadass.api.entity.UserInfo;
import com.chinadass.api.entity.UserInfoExample;
import com.chinadass.api.mapper.UserInfoMapper;
import com.chinadass.api.service.IUserService;
import com.chinadass.api.utils.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
public class UserServiceImp implements IUserService {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public Map<String, String> userLogin(String userAccount, String password) {
        Map<String, String> map=new HashMap<String,String>();
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andUserAccountEqualTo(userAccount).andPasswordEqualTo(password);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);
        if(null != userInfos && userInfos.size()>0){
            String token = JwtUtil.createToken(userInfos.get(0).getUserAccount());
            map.put("token",token);
        }else{
            throw new BusinessException(ResultCode.USER_LOGIN_FAIL);
        }

        return map;
    }

}
