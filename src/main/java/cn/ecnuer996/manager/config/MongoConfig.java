package cn.ecnuer996.manager.config;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

/**
 * 为了去除Mongo中_class字段
 */
@Configuration
public class MongoConfig {

//    @Value("${spring.data.mongodb.authentication-database}")
//    String db;
//
//    @Bean
//    public GridFSBucket getGridFSBucket(MongoClient mongoClient) {
//        MongoDatabase database = mongoClient.getDatabase(db);
//        GridFSBucket gridFSBucket = GridFSBuckets.create(database);
//        return gridFSBucket;
//    }
//    @Autowired
//    private MongoDbFactory mongoDbFactory;
//    @Autowired
//    private GridFSBucket gridFSBucket;
//
//
//    @Bean
//    public GridFSBucket getGridFSBuckets() {
//        MongoDatabase db = mongoDbFactory.getDb();
//        return GridFSBuckets.create(db);
//    }



    @Bean
    public MappingMongoConverter mappingMongoConverter(MongoDbFactory factory, MongoMappingContext context, BeanFactory beanFactory) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
        MappingMongoConverter mappingConverter = new MappingMongoConverter(dbRefResolver, context);
        try {
            mappingConverter.setCustomConversions(beanFactory.getBean(CustomConversions.class));
        } catch (NoSuchBeanDefinitionException ignore) {
        }

        // Don't save _class to mongo
        mappingConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return mappingConverter;
    }
}
