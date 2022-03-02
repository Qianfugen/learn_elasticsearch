package fun.qianfg.learn_elasticsearch.repo;

import fun.qianfg.learn_elasticsearch.entity.User;
import fun.qianfg.learn_elasticsearch.query.UserDto;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;

/**
 * @author qianfg
 */
@Repository
public interface UserRepository extends ElasticsearchRepository<User, String> {
    default Page<User> findPageByCondition(UserDto condition) {
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (condition.getStartTime() != null) {
            builder.must(QueryBuilders.rangeQuery("birth").from(sdf.format(condition.getStartTime())));
        }
        if (condition.getEndTime() != null) {
            builder.must(QueryBuilders.rangeQuery("birth").to(sdf.format(condition.getEndTime())));
        }
        if (condition.getUserId() != null) {
            builder.must(QueryBuilders.termQuery("userId", condition.getUserId()));
        }
        if (condition.getGender() != null) {
            builder.must(QueryBuilders.termQuery("gender", condition.getGender()));
        }
        if (condition.getName() != null) {
            builder.must(QueryBuilders.boolQuery().should(
                    QueryBuilders.wildcardQuery("name", "*" + condition.getName() + "*")
            ));
        }
        FieldSortBuilder userIdSort = SortBuilders.fieldSort("userId").order(SortOrder.ASC);
        FieldSortBuilder ageSort = SortBuilders.fieldSort("age").order(SortOrder.DESC);

        NativeSearchQuery query = new NativeSearchQueryBuilder().withQuery(builder).withSort(userIdSort).withSort(ageSort).withPageable(condition.of()).build();
        return search(query);
    }
}
