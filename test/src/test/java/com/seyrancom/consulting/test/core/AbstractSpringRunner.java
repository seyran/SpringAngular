package com.seyrancom.consulting.test.core;

import com.seyrancom.consulting.config.root.AppRoot;
import com.seyrancom.consulting.config.root.PersistenceConfigBase;
import com.seyrancom.consulting.config.root.WebRoot;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RunWith(SpringJUnit4ClassRunner.class)
//@ActiveProfiles("test")
//@TestPropertySource("/test.properties")
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@WebAppConfiguration
@ActiveProfiles({"dev"})
@Transactional
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {AppRoot.class, WebRoot.class})
// ApplicationContext will be loaded from the static inner ContextConfiguration class
//@ContextConfiguration(classes={RootConfig.class, WebConfig.class}, loader=AnnotationConfigContextLoader.class)
//@ContextConfiguration(classes={RootConfig.class}, loader=AnnotationConfigContextLoader.class)
public abstract class AbstractSpringRunner {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private MockMvc mockMvc;

    @PersistenceContext(name = PersistenceConfigBase.PERSISTENCE_UNIT)
    //@Autowired
    protected EntityManager entityManager;

/*    @Autowired
    protected SessionFactory sessionFactory;*/

    protected Session getSession() {
        Session session = entityManager.unwrap(Session.class);
        return session;
    };

    protected SessionFactory getSessionFactory() {
        return getSession().getSessionFactory();
    }

    @Autowired
    protected WebApplicationContext wac;

/*    @Before
    public void setUp() throws Exception {
    }*/

    protected MockMvc getMockMvc() {
        if (mockMvc == null) {
            mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        }
        return mockMvc;
    }

}