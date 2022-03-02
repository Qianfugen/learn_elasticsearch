package fun.qianfg.learn_elasticsearch.service.impl;

import fun.qianfg.learn_elasticsearch.entity.User;
import fun.qianfg.learn_elasticsearch.mapper.UserMapper;
import fun.qianfg.learn_elasticsearch.query.PageData;
import fun.qianfg.learn_elasticsearch.query.UserDto;
import fun.qianfg.learn_elasticsearch.repo.UserRepository;
import fun.qianfg.learn_elasticsearch.service.UserService;
import fun.qianfg.learn_elasticsearch.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qianfg
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IdWorker idWorker;

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public Long save(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user.setId(String.valueOf(idWorker.nextId()));
        return userRepository.save(user).getUserId();
    }

    @Override
    public void delete(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        userRepository.delete(user);
    }

    @Override
    public PageData<UserDto> findByCondition(UserDto userDto) {
        Page<User> result = userRepository.findPageByCondition(userDto);
        List<UserDto> data = userMapper.toDtos(result.getContent());
        return new PageData<>(data, result.getNumber() + 1, result.getSize(), (int) result.getTotalElements());
    }
}
