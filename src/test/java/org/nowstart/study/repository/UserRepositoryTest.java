package org.nowstart.study.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nowstart.study.domain.entity.UserEntity;
import org.nowstart.study.presentation.config.JpaAuditingConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(JpaAuditingConfig.class)
class UserRepositoryTest {

    private static final String TEST_ID = "testId";
    private static final String TEST_PASSWORD = "1234";
    private static final String TEST_NAME = "testName";
    private static final String UPDATE_TEST_PASSWORD = "5678";

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void init() {
        userRepository.save(UserEntity.builder()
                .id(TEST_ID)
                .password(TEST_PASSWORD)
                .name(TEST_NAME)
                .build());
    }

    @Test
    @DisplayName("select 테스트")
    void selectTest() {
        //when
        UserEntity result = userRepository.findById(TEST_ID).orElseThrow();

        //then
        assertThat(result.getId()).isEqualTo(TEST_ID);
        assertThat(result.getPassword()).isEqualTo(TEST_PASSWORD);
        assertThat(result.getName()).isEqualTo(TEST_NAME);
        assertThat(result.getCreatedDate()).isNotNull();
        assertThat(result.getModifiedDate()).isNotNull();
    }

    @Test
    @DisplayName("insert 테스트")
    void createTest() {
        //given

        //when
        UserEntity result = userRepository.findById(TEST_ID).orElseThrow();

        //then
        assertThat(result.getId()).isEqualTo(TEST_ID);
        assertThat(result.getPassword()).isEqualTo(TEST_PASSWORD);
        assertThat(result.getName()).isEqualTo(TEST_NAME);
    }

    @Test
    @DisplayName("delete 테스트")
    void deleteTest() {
        //given
        //when
        userRepository.deleteById(TEST_ID);

        //then
        try {
            userRepository.findById(TEST_ID).orElseThrow();
        } catch (NoSuchElementException e) {
            assertThat(e.getMessage()).isEqualTo("No value present");
        }
    }
}