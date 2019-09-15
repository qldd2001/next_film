package com.next.jiangzh.film.service.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.next.jiangzh.film.common.utils.MD5Util;
import com.next.jiangzh.film.common.utils.ToolUtils;
import com.next.jiangzh.film.controller.user.vo.EnrollUserVO;
import com.next.jiangzh.film.controller.user.vo.UserInfoVO;
import com.next.jiangzh.film.dao.entity.NextUserT;
import com.next.jiangzh.film.dao.mapper.NextUserTMapper;
import com.next.jiangzh.film.service.common.exception.CommonServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserServiceAPI {
    @Resource
    private NextUserTMapper userTMapper;

    @Override
    public void userEnroll(EnrollUserVO enrollUserVO) throws CommonServiceException {
        //校验：电话唯一

        //EnrollUserVO -- >NextUserT 数据转换
        NextUserT nextUserT = new NextUserT();

        BeanUtils.copyProperties(enrollUserVO, nextUserT);//复制属性
        nextUserT.setUserName(enrollUserVO.getUsername());
        nextUserT.setUserPwd(MD5Util.encrypt(enrollUserVO.getPassword()));

        //数据插入
        int isSuccess = userTMapper.insert(nextUserT);

        //判断插入是否成功
        if (isSuccess != 1) {
            throw new CommonServiceException(501, "用户登记失败!");
        }
    }

    @Override
    public boolean checkUserName(String userName) throws CommonServiceException {

//        Optional.ofNullable(userName).orElseThrow( CommonServiceException::new );

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name", userName);

        int hasUserName = userTMapper.selectCount(queryWrapper);

        return hasUserName == 0 ? false : true;
    }

    @Override
    public boolean userAuth(String userName, String userPwd) throws CommonServiceException {
        if (ToolUtils.isEmpty(userName) || ToolUtils.isEmpty(userPwd)) {
            throw new CommonServiceException(400, "用户验证失败！");
        }

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name", userName);

        //1 ，判断用户是否存在
        List<NextUserT> list = userTMapper.selectList(queryWrapper);
        if (list.size() == 0) {
            return false;
        } else {
            //2，如果存在，则判断密码是否正确
            NextUserT nextUserT = list.get(0);
            //3，对密码进行md5机密，然后判断2个密码是否相等
            if (MD5Util.encrypt(userPwd).equals(nextUserT.getUserPwd())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public UserInfoVO describeUserInfo(String userId) throws CommonServiceException {
        NextUserT nextUserT = userTMapper.selectById(userId);
        if (nextUserT != null && nextUserT.getUuid() != null) {
            UserInfoVO userInfoVO = user2InfoVO(nextUserT);
            return userInfoVO;
        } else {
            throw new CommonServiceException(404, "用户编号为[" + userId + "]的用户不存在！");
        }
    }

    @Override
    public UserInfoVO updateUserInfo(UserInfoVO userInfoVO) throws CommonServiceException {
        NextUserT nextUserT = info2user(userInfoVO);
        if (nextUserT != null && nextUserT.getUuid() != null) {
            int isSuccess = userTMapper.updateById(nextUserT);
            if (isSuccess == 1) {
                UserInfoVO result = describeUserInfo(userInfoVO.getUuid() + "");
                return result;
            } else {
                throw new CommonServiceException(500, "用户信息修改失败！");
            }


        } else {
            throw new CommonServiceException(404, "用户编号为[" + userInfoVO.getUuid() + "]的用户不存在！");
        }

    }


    /**
     * ============以下都是自定义方法====================
     */
    private UserInfoVO user2InfoVO(NextUserT nextUserT) {
        UserInfoVO userInfoVO = new UserInfoVO();

        userInfoVO.setUsername(nextUserT.getUserName());
        userInfoVO.setNickname(nextUserT.getNickName());

        userInfoVO.setBeginTime(nextUserT.getBeginTime().toEpochSecond(ZoneOffset.of("+8")));
        userInfoVO.setUpdateTime(nextUserT.getUpdateTime().toEpochSecond(ZoneOffset.of("+8")));
        userInfoVO.setLifeState(nextUserT.getLifeState() + "");

        BeanUtils.copyProperties(nextUserT, userInfoVO);
        return userInfoVO;
    }

    private NextUserT info2user(UserInfoVO userInfoVO) {
        NextUserT vo = new NextUserT();

        vo.setUserName(userInfoVO.getUsername());
        vo.setNickName(userInfoVO.getNickname());

        vo.setUpdateTime(LocalDateTime.now());
        if (Optional.ofNullable(userInfoVO.getLifeState()).isPresent()) {
            vo.setLifeState(Integer.parseInt(userInfoVO.getLifeState()));
        }

        BeanUtils.copyProperties(userInfoVO, vo);
        return vo;
    }


}
