package fun.qianfg.learn_elasticsearch.mapper;

import fun.qianfg.learn_elasticsearch.entity.User;
import fun.qianfg.learn_elasticsearch.query.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author qianfg
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    /**
     * Dto转Entity
     *
     * @param userDto dto
     * @return entity
     */
    User toEntity(UserDto userDto);

    /**
     * Dtos转Entities
     *
     * @param userDtos dtos
     * @return entities
     */
    List<User> toEntities(List<UserDto> userDtos);

    /**
     * Entity转Dto
     *
     * @param user entity
     * @return dto
     */
    UserDto toDto(User user);

    /**
     * Entities转Dtos
     *
     * @param users entities
     * @return dtos
     */
    List<UserDto> toDtos(List<User> users);
}
