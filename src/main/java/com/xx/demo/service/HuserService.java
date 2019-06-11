package com.xx.demo.service;

import com.xx.demo.entity.Huser;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaoxiao
 * @since 2019-03-28
 */
public interface HuserService extends IService<Huser> {

          /**
           * 后台登录
           */
          public String getLogin(Huser huser);
}
