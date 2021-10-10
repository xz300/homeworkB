package com.mybatis;


import org.apache.ibatis.session.SqlSession;
        import org.apache.ibatis.session.SqlSessionFactory;
        import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class mybatisHelper {

    private final SqlSession session;

    public mybatisHelper() {
        SqlSessionFactory sessionFactory =
                new SqlSessionFactoryBuilder()
                        .build(getClass().getResourceAsStream("com/gb/mybatis/mybatis-config.xml"));
        session = sessionFactory.openSession();
    }

}
