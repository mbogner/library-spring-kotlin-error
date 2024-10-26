package dev.mbo.springkotlinerror

import dev.mbo.springkotlinerror.exctest0.ErrorCodeA
import dev.mbo.springkotlinerror.exctest1.ErrorCodeC
import dev.mbo.springkotlinerror.exctest2.ErrorCodeE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [TestApplication::class])
class GlobalErrorCodeValidatorTest {

    @Test
    fun toOkClasses() {
        val actual = GlobalErrorCodeValidator(ErrorCodeA::class.java.packageName)
        actual.init()
        assertThat(actual.subclasses).isEqualTo(2)
    }

    @Test
    fun sameIdInTwoClasses() {
        val actual = GlobalErrorCodeValidator(ErrorCodeC::class.java.packageName)
        assertThrows(IllegalStateException::class.java) {
            actual.init()
        }
        assertThat(actual.subclasses).isEqualTo(2)
    }

    @Test
    fun sameNameInTwoClasses() {
        val actual = GlobalErrorCodeValidator(ErrorCodeE::class.java.packageName)
        assertThrows(IllegalStateException::class.java) {
            actual.init()
        }
        assertThat(actual.subclasses).isEqualTo(2)
    }


}