package de.hf.testhelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;


public abstract class MongoDbTestBase {
    private static final MongoDBContainer database = new MongoDBContainer("mongo:5.0.6");
    protected static final Logger LOG = LoggerFactory.getLogger(MongoDbTestBase.class);

    static {
        database.start();
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.host", database::getHost);
        registry.add("spring.data.mongodb.port", () -> database.getMappedPort(27017));
        registry.add("spring.data.mongodb.database", () -> "test");
    }
}