package roomescape.controller;

import static roomescape.Fixture.COOKIE_NAME;
import static roomescape.Fixture.VALID_ADMIN_EMAIL;
import static roomescape.Fixture.VALID_ADMIN_NAME;
import static roomescape.Fixture.VALID_ADMIN_PASSWORD;
import static roomescape.Fixture.VALID_ADMIN_ROLE;
import static roomescape.Fixture.VALID_USER_EMAIL;
import static roomescape.Fixture.VALID_USER_NAME;
import static roomescape.Fixture.VALID_USER_PASSWORD;
import static roomescape.Fixture.VALID_USER_ROLE;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AdminPageControllerTest extends ControllerTest {
    @BeforeEach
    void setInitialData() {
        jdbcTemplate.update(
            "INSERT INTO member(name,email,password,role) VALUES (?,?,?,?)",
            VALID_USER_NAME.getValue(),
            VALID_USER_EMAIL.getValue(),
            VALID_USER_PASSWORD.getValue(),
            VALID_USER_ROLE.getValue());
        jdbcTemplate.update(
            "INSERT INTO member(name,email,password,role) VALUES (?,?,?,?)",
            VALID_ADMIN_NAME.getValue(),
            VALID_ADMIN_EMAIL.getValue(),
            VALID_ADMIN_PASSWORD.getValue(),
            VALID_ADMIN_ROLE.getValue());
    }

    @DisplayName("admin 권한을 갖고 있으면 admin 페이지 접속이 가능하다. -> 200")
    @ParameterizedTest
    @ValueSource(strings = {"/admin", "/admin/reservation"})
    void getAdminPage(String endPoint) {
        RestAssured.given().log().all()
            .cookie(COOKIE_NAME, getAdminToken())
            .when().get(endPoint)
            .then().log().all()
            .statusCode(200);
    }

    @DisplayName("user 권한을 갖고 있으면 admin 페이지 접속할 수 없다. -> 401")
    @ParameterizedTest
    @ValueSource(strings = {"/admin", "/admin/reservation"})
    void getAdminPage_ByUser(String endPoint) {
        RestAssured.given().log().all()
            .cookie(COOKIE_NAME, getUserToken())
            .when().get(endPoint)
            .then().log().all()
            .statusCode(401);
    }
}