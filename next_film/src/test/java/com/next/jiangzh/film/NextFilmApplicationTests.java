package com.next.jiangzh.film;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.next.jiangzh.film.dao.entity.NextUser;
import com.next.jiangzh.film.dao.mapper.NextUserMapper;
import com.next.jiangzh.film.example.dao.UserMapper;
import com.next.jiangzh.film.example.dao.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NextFilmApplicationTests {

    //	@Autowired
    @Resource
    private UserMapper userMapper;

    @Resource
    private NextUserMapper nextUserMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void mybatisHelloWorld() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void mybatisGenerator() {
        AbstractWrapper abstractWrapper = new QueryWrapper();
        abstractWrapper.eq("user_name","admin02");
        List<NextUser> nextUsers = nextUserMapper.selectList(abstractWrapper);
        nextUsers.forEach(System.out::println);
    }

    @Test
    public void addUser() {
        NextUser nextUser = new NextUser();
        nextUser.setUserName("Shaka嘿嘿");
        nextUser.setUserPwd("looks good!");

        int isSeccess = nextUserMapper.insert(nextUser);
        System.out.println("(isSeccess==1?true:false) = " + (isSeccess == 1 ? true : false));
    }

    @Test
    public void updateUser() {
        NextUser nextUser = new NextUser();
//        nextUser.setUuid(6);
//        nextUser.setUserName("Shaka嘿嘿");
        nextUser.setUserPwd("looks very good!!!!===aaa");

//        int isSeccess = nextUserMapper.updateById(nextUser);

        AbstractWrapper abstractWrapper = new UpdateWrapper();
        abstractWrapper.eq("user_name", "Shaka嘿嘿");

        int isSeccess = nextUserMapper.update(nextUser, abstractWrapper);
        System.out.println("(isSeccess==1?true:false) = " + (isSeccess == 1 ? true : false));
    }

    @Test
    public void deleteUser() {
        int id = 6;
        int i = nextUserMapper.deleteById(id);
        System.out.println("i = " + i);
    }

    @Test
    public void queryUserById() {
        int id = 5;
        NextUser nextUser = nextUserMapper.selectById(id);
        System.out.println("i = " + nextUser);
    }

    @Test
    public void getUsers() {
        List<NextUser> users = nextUserMapper.getUsers();
        users.forEach(System.out::println);
    }

    @Test
    public void pageTest() {
        IPage<NextUser> iPage= new Page<>();
        iPage.setCurrent(1);
        iPage.setSize(2);

        IPage<NextUser> iPage1 = nextUserMapper.selectPage(iPage, null);
        System.out.println("iPage1 = " + iPage1.getTotal());
        iPage1.getRecords().forEach(System.out::println);

    }


}
