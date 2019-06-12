package com.se418.ratingbookservice;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

@EnableRedisHttpSession
public class SessionConfiguration
        extends AbstractHttpSessionApplicationInitializer {
}
