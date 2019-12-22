package me.khmoon.inflearnthejavatest;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {
  @BeforeAll
  static void beforeAll() {
    System.out.println("before all");
  }

  @AfterAll
  static void afterAll() {
    System.out.println("after all");
  }

  @Test
  @DisplayName("스터디 만들기 fast")
  @Tag("fast")
  void create_new_study() {
    Study actual = new Study(100);
    assertThat(actual.getLimit()).isGreaterThan(0);
  }

  @Test
  @DisplayName("스터디 만들기 slow")
  @Tag("slow")
  void create_new_study_again() {
    System.out.println("create1");
  }

  @BeforeEach
  void beforeEach() {
    System.out.println("Before each");
  }

  @AfterEach
  void afterEach() {
    System.out.println("After each");
  }
}