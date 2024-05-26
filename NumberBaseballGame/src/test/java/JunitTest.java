import org.example.baseballGame.BaseballGame;
import org.example.validator.Validator;
import org.example.view.InputView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("숫자 야구 게임 테스트")
public class JunitTest {

    static BaseballGame baseballGame = new BaseballGame();

    @Test
    @DisplayName("사용자가 입력한 숫자가 1~9 사이의 서로 다른 숫자인지 검증한다.")
    public void testNumbersInRange(){
        // given (주어진 값)
//        int[] arrA = new int[]{0, 1, 2}; // '0'이 들어간 숫자
//        int[] arrB = new int[]{1, 3, 3}; // 중복된 숫자
        int[] arrC = new int[]{1, 2, 3}; // 서로 다른 숫자

        // when (기능 적용)
//        boolean resA = Validator.isValidTypeInputNumber(3, arrA);
//        boolean resB = Validator.isValidTypeInputNumber(3, arrB);
        boolean resC = Validator.isValidTypeInputNumber(3, arrC);

        // then (기능 작동 후 검증)
//        Assertions.assertTrue(resA);
//        Assertions.assertTrue(resB);
        Assertions.assertTrue(resC);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "456"})
    @DisplayName("사용자가 3개의 숫자를 입력했는지 확인한다.")
    public void testHasCorrectLength(String number){
        Assertions.assertEquals(true, Validator.isValidLenInputNumber(number, 3));
    }

    @Test
    @DisplayName("컴퓨터와 사용자의 숫자를 세팅해서 게임을 실행해서 결과를 확인한다.")
    public void testGameResultWithMatchingNumbers(){
        Assertions.assertEquals(new int[]{3, 0}, baseballGame.baseballGameResult(new int[]{1, 2, 3}, new int[]{1, 2, 3}));
    }


}
