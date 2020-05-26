package org.example.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

import java.util.concurrent.TimeUnit;

@Configuration
public class MongoConfig {
//    @Bean
//    public MongoClientSettings mongoClient() {
//        MongoClientSettings settings = MongoClientSettings.builder()
//                // 添加WriteConcern参数配置：majority
//                .writeConcern(WriteConcern.MAJORITY.withJournal(true).withWTimeout(3000, TimeUnit.MILLISECONDS))
//                // 添加ReadConcern参数配置：majority
//                .readConcern(ReadConcern.MAJORITY)
//                // 添加ReadPreference参数配置：secondaryPreferred 优先读取secondary，达到读写分离
//                .readPreference(ReadPreference.secondaryPreferred())
//                .build();
//        return settings;
//    }

    @Bean
    public MongoTransactionManager mongoTransactionManager(MongoDatabaseFactory factory) {
        return new MongoTransactionManager(factory);
    }
}
