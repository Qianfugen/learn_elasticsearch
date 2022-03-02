package fun.qianfg.learn_elasticsearch.controller;

import fun.qianfg.learn_elasticsearch.query.PageData;
import fun.qianfg.learn_elasticsearch.query.UserDto;
import fun.qianfg.learn_elasticsearch.response.BaseResult;
import fun.qianfg.learn_elasticsearch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qianfg
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save.do")
    public BaseResult<Long> save(@RequestBody UserDto userDto) {
        return new BaseResult<>(userService.save(userDto));
    }

    @PostMapping("/delete.do")
    public BaseResult delete(@RequestBody UserDto userDto) {
        userService.delete(userDto);
        return new BaseResult();
    }

    @PostMapping("/findByCondition.do")
    public BaseResult<PageData<UserDto>> findByCondition(@RequestBody UserDto userDto) {
        return new BaseResult<>(userService.findByCondition(userDto));
    }
}
