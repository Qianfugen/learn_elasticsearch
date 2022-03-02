package fun.qianfg.learn_elasticsearch.service;

import fun.qianfg.learn_elasticsearch.query.PageData;
import fun.qianfg.learn_elasticsearch.query.UserDto;

/**
 * @author qianfg
 */
public interface UserService {
    /**
     * 计算人数
     *
     * @return 人数
     */
    long count();

    /**
     * 保存用户
     *
     * @param userDto 用户信息
     * @return 用户id
     */
    Long save(UserDto userDto);

    /**
     * 删除用户
     *
     * @param userDto 用户信息，必须包含id
     */
    void delete(UserDto userDto);

    /**
     * 根据条件查询
     *
     * @param userDto 查询条件
     * @return 匹配的用户列表
     */
    PageData<UserDto> findByCondition(UserDto userDto);
}
