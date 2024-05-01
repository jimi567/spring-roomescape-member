package roomescape.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import roomescape.domain.Theme;
import roomescape.domain.ThemeRepository;

@SpringBootTest
@Transactional
class JdbcThemeRepositoryTest {

    @Autowired
    private ThemeRepository themeRepository;

    @DisplayName("테마 정보를 DB에 저장한다.")
    @Test
    void save() {
        Theme theme = new Theme("방탈출", "방탈출을 한다.", "url");
        Theme savedTheme = themeRepository.save(theme);

        assertThat(savedTheme).isEqualTo(savedTheme);
    }

    @DisplayName("전체 테마 정보를 불러온다.")
    @Test
    void findAll() {
        Theme theme1 = new Theme("방탈출1", "방탈출1을 한다.", "url1");
        Theme theme2 = new Theme("방탈출2", "방탈출2을 한다.", "url2");

        Theme saved1 = themeRepository.save(theme1);
        Theme saved2 = themeRepository.save(theme2);

        assertThat(themeRepository.findAll()).containsExactlyInAnyOrder(saved1, saved2);
    }

    @DisplayName("ID를 받아와 테마를 DB에서 삭제한다.")
    @Test
    void deleteById() {
        Theme theme1 = new Theme("방탈출1", "방탈출1을 한다.", "url1");
        Theme theme2 = new Theme("방탈출2", "방탈출2을 한다.", "url2");

        Theme saved1 = themeRepository.save(theme1);
        Theme saved2 = themeRepository.save(theme2);

        themeRepository.deleteById(saved1.getId());

        assertThat(themeRepository.findAll()).hasSize(1);
    }
}