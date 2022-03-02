package fun.qianfg.learn_elasticsearch.service.impl;

import fun.qianfg.learn_elasticsearch.query.PageData;
import fun.qianfg.learn_elasticsearch.query.UserDto;
import fun.qianfg.learn_elasticsearch.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void count() {
        long count = userService.count();
        System.out.println("现有人数：" + count);
    }

    @Test
    public void save() {
        long startTime = System.currentTimeMillis();
        int counts = 100000;
        for (long i = 40620; i <= counts; i++) {
            Map userInfo = RandomValue.getUserInfo();
            UserDto userDto = new UserDto();
            userDto.setUserId(i);
            userDto.setName((String) userInfo.get("name"));
            userDto.setGender((Boolean) userInfo.get("sex").equals("男"));
            userDto.setBirth((Date) userInfo.get("birth"));
            userDto.setAge((Integer) userInfo.get("age"));
            userDto.setTall((Double) userInfo.get("tall"));
            userDto.setAddress((String) userInfo.get("address"));
            userDto.setPhoneNumber((String) userInfo.get("tel"));
            userDto.setEmail((String) userInfo.get("email"));
            userService.save(userDto);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("插入 " + counts + " 条数据，共耗时 " + (endTime - startTime) / 1000 + " s");
    }

    @Test
    public void delete() {
        UserDto condition = new UserDto();
        condition.setUserId(1024L);
        List<UserDto> list = userService.findByCondition(condition).getList();
        list.forEach(s -> userService.delete(s));
    }

    @Test
    public void findByCondition() throws ParseException {
        UserDto condition = new UserDto();
        condition.setUserId(1024L);
//        condition.setName("钱");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        condition.setStartTime(sdf.parse("2002-01-01 12:00:00"));
//        condition.setEndTime(sdf.parse("2003-01-01 12:00:00"));
        PageData<UserDto> result = userService.findByCondition(condition);
        System.out.println(result);
    }

}