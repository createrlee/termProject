package oodp;

/**
 * 여러 state에 대한 실행 루틴을 단 하나의 메서드로 퉁치고 싶은 변태같은 학생들을 위해 마련된 열거자입니다.
 * 
 * @author Racin
 *
 */
public enum StateCallbackType
{
	/**
	 * state에 처음 진입할 때를 위해 콜백 메서드가 호출되었음을 나타냅니다.
	 */
	Entering,
	
	/**
	 * 같은 state를 반복(처음 제외)할 때를 위해 콜백 메서드가 호출되었음을 나타냅니다.
	 */
	Repeating,

	/**
	 * state를 떠날 때를 위해 콜백 메서드가 호출되었음을 나타냅니다.
	 */
	Leaving
}
