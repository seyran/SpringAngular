package com.seyrancom.consulting.config.root;

import org.springframework.context.annotation.Configuration;

@Configuration
/*@PropertySources({
        @PropertySource("classpath:app.properties"),
})*/
public class InitConfig extends AbstractConfigBase {

    /**
     * with spring boot
     */
/*    @Component
    static class Runner implements CommandLineRunner {

        @Autowired
        @Qualifier("cachedBookRepository")
        private BookRepository bookRepository;

        @Override
        public void run(String... args) throws Exception {
            logger.info(".... Fetching books");
            logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
            logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
            logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
        }
    }*/

/*    @Bean
    CommandLineRunner init(AccountRepository accountRepository,
                           BookmarkRepository bookmarkRepository) {
        return (evt) -> Arrays.asList(
                "jhoeller,dsyer,pwebb,ogierke,rwinch,mfisher,mpollack,jlong".split(","))
                .forEach(
                        a -> {
                            Account account = accountRepository.save(new Account(a,
                                    "password"));
                            bookmarkRepository.save(new Bookmark(account,
                                    "http://bookmark.com/1/" + a, "A description"));
                            bookmarkRepository.save(new Bookmark(account,
                                    "http://bookmark.com/2/" + a, "A description"));
                        });
    }*/
}