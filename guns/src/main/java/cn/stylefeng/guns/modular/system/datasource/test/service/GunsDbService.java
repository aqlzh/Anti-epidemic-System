package cn.stylefeng.guns.modular.system.datasource.test.service;

import cn.stylefeng.guns.modular.system.datasource.test.factory.NormalUserFactory;
import cn.stylefeng.roses.kernel.dsctn.api.annotation.DataSource;
import cn.stylefeng.roses.kernel.system.modular.user.entity.SysUser;
import cn.stylefeng.roses.kernel.system.modular.user.mapper.SysUserMapper;
import cn.stylefeng.roses.kernel.system.modular.user.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Guns数据库操作
 *
 * @author fengshuonan
 * @date 2021/1/23 23:29
 */
@Service
public class GunsDbService extends ServiceImpl<SysUserMapper, SysUser> {

    @Resource
    private SysUserService sysUserService;

    @DataSource(name = "master")
    public void gunsDb() {
        sysUserService.save(NormalUserFactory.createAnUser());
    }

}
