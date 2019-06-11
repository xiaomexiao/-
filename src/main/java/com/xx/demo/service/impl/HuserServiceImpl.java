package com.xx.demo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xx.demo.entity.Huser;
import com.xx.demo.mapper.HuserMapper;
import com.xx.demo.service.HuserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaoxiao
 * @since 2019-03-28
 */
@Service
public class HuserServiceImpl extends ServiceImpl<HuserMapper, Huser> implements HuserService {

          @Autowired
          private HuserMapper huserMapper;

          @Override
          public String getLogin(Huser huser) {
                    Huser huser1 = new Huser();
                    huser1.setUsername(huser.getUsername());
                    Huser huser2 = huserMapper.selectOne(huser1);
                    if (huser2 != null) {
                              if (huser2.getPassword().equals(huser.getPassword())) {
                                        return "yes";
                              } else {
                                        return "no";
                              }
                    } else {
                              return "no";
                    }

          }
}
