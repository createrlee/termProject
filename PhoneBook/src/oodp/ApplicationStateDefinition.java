package oodp;

import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * 응용 프로그램의 state 하나에 대한 동작을 정의합니다.
 * 이 클래스는 ConsoleApplication class와 함께 사용됩니다.
 * 
 * @author Racin
 *
 */
public class ApplicationStateDefinition
{
	/**
	 * 이 state의 일련 번호입니다.
	 * ID를 state마다 고유하게 부여할 의무는 여러분에게 있습니다.
	 * ID가 0인 state는 응용 프로그램의 시작 state가 됩니다.
	 */
	public int ID;
	
	/**
	 * 이 state가 사용자 입력 없이 단 한 번만 실행되는 state인지를 나타냅니다.
	 * 이 값이 true면 이 state는 Parser를 통한 사용자 입력 처리 과정 없이
	 * 다음 state를 <code>nextStateToGoThrough</code> 필드의 값으로 설정합니다.<br>
	 * <br>
	 * Note: <code>callback_Entering</code> 및 <code>callback_Looping</code>을 통해
	 * 특정 횟수, 특정 조건만큼 조용히 반복하는 state를 만들 수도 있습니다.
	 * 상상의 나래를 펼쳐 보세요!
	 */
	public boolean isGoThroughState;
	
	/**
	 * <code>isGoThroughState</code> 필드의 값이 true일 때 사용되는
	 * '실행 후 전이할 다음 state' 번호입니다.<br>
	 * <br>
	 * <b>주의:</b> 특별한 처리 루틴 없이 이 값을 '이 state 번호'로 지정하면
	 * 당연히 무한 루프를 돌게 됩니다!
	 */
	public int nextStateToGoThrough;
	
	/**
	 * <code>isGoThroughState</code> 필드의 값이 false일 때 사용되는
	 * '이 state에서의 명령어 처리 규칙 목록'입니다.
	 * 예전에 대충 몇 개 만들어 본 그 <code>Rule</code> 맞습니다.
	 * 그러니 그냥 쓰면 됩니다.
	 */
	public ArrayList<Rule> rules;
	
	/**
	 * '고정' 메뉴 문자열입니다.
	 * <code>callback_UpdateMenuText</code>를 지정하여 '유동' 메뉴 문자열을 쓰는 경우
	 * 이 필드의 원래 값은 지워지고 해당 콜백 메서드의 반환값이 대신 채워집니다.
	 */
	public String menuText;
	
	/**
	 * 실행 상황에 맞게 메뉴 문자열을 다르게 표시하고 싶을 때 사용하는 메서드 필드입니다.
	 * 이 필드에는 'int 하나를 받아 string을 뱉는 메서드'를 넣어 주면 됩니다.
	 * 여기서 여러분에게 보내 줄 'int 하나'는 현재 실행중인 state 번호입니다.<br>
	 * <br>
	 * Note: 따라서, argument로 받는 state 번호로 switch문을 돌리면서
	 * 여러 state에 대한 서로 다른 메뉴 문자열을 반환하는 하나의 메서드를 만들어 쓸 수도 있습니다.
	 * 아마 몇몇 변태 학생들은 그런 코드 스타일을 좋아할테니, 미리 준비해 두었습니다.
	 */
	public Function<Integer, String> callback_UpdateMenuText;
	
	/**
	 * 이 state에 처음 진입할 때 호출할 메서드 필드입니다.
	 * 이 필드에는 'int 하나와 SCT 열거자 하나를 받고 반환은 안 하는 메서드'를 넣어 주면 됩니다.
	 * 여기서 여러분에게 보내 줄 'int 하나'는 현재 실행중인 state 번호고,
	 * 'SCT 열거자 하나'는 해당 메서드가 어떤 목적으로 호출되었는지를 나타냅니다.<br>
	 * <br>
	 * Note: 따라서, argument로 받는 내용들로 switch문을 돌리면서
	 * 여러 state의 여러 동작을 처리하는 하나의 메서드를 만들어 쓸 수도 있습니다.
	 * 아마 몇몇 변태 학생들은 그런 코드 스타일을 좋아할테니, 미리 준비해 두었습니다.
	 */
	public BiConsumer<Integer, StateCallbackType> callback_Entering;
	
	/**
	 * 이 state를 반복(처음 제외) 실행할 때 호출할 메서드 필드입니다.
	 * 이 필드에는 'int 하나와 SCT 열거자 하나를 받고 반환은 안 하는 메서드'를 넣어 주면 됩니다.
	 * 여기서 여러분에게 보내 줄 'int 하나'는 현재 실행중인 state 번호고,
	 * 'SCT 열거자 하나'는 해당 메서드가 어떤 목적으로 호출되었는지를 나타냅니다.<br>
	 * <br>
	 * Note: 따라서, argument로 받는 내용들로 switch문을 돌리면서
	 * 여러 state의 여러 동작을 처리하는 하나의 메서드를 만들어 쓸 수도 있습니다.
	 * 아마 몇몇 변태 학생들은 그런 코드 스타일을 좋아할테니, 미리 준비해 두었습니다.
	 */
	public BiConsumer<Integer, StateCallbackType> callback_Repeating;
	
	/**
	 * 이 state를 떠날 때 호출할 메서드 필드입니다.
	 * 이 필드에는 'int 하나와 SCT 열거자 하나를 받고 반환은 안 하는 메서드'를 넣어 주면 됩니다.
	 * 여기서 여러분에게 보내 줄 'int 하나'는 현재 실행중인 state 번호고,
	 * 'SCT 열거자 하나'는 해당 메서드가 어떤 목적으로 호출되었는지를 나타냅니다.<br>
	 * <br>
	 * Note: 따라서, argument로 받는 내용들로 switch문을 돌리면서
	 * 여러 state의 여러 동작을 처리하는 하나의 메서드를 만들어 쓸 수도 있습니다.
	 * 아마 몇몇 변태 학생들은 그런 코드 스타일을 좋아할테니, 미리 준비해 두었습니다.
	 */
	public BiConsumer<Integer, StateCallbackType> callback_Leaving;
	
	/**
	 * 새로운 <code>ApplicationStateDefinition class</code>의 인스턴스를 초기화합니다.
	 * 이 생성자를 사용하는 경우 <code>isGoThroughState</code> 필드의 값은 자동으로 false로 초기화됩니다.
	 * 
	 * @param ID 이 state의 일련 번호입니다.
	 */
	public ApplicationStateDefinition(int ID)
	{
		this.ID = ID;
		isGoThroughState = false;
		rules = new ArrayList<Rule>();
		menuText = String.format("State#%d에 대한 기본 메뉴 텍스트입니다.", ID);
		callback_UpdateMenuText = null;
		callback_Entering = null;
		callback_Repeating = null;
		callback_Leaving = null;
	}
	
	/**
	 * 새로운 <code>ApplicationStateDefinition class</code>의 인스턴스를 초기화합니다.
	 * 이 생성자를 사용하는 경우 <code>isGoThroughState</code> 필드의 값은 자동으로 <b>true</b>로 초기화됩니다.
	 * 
	 * @param ID 이 state의 일련 번호입니다.
	 * @param nextStateToGoThrough 이 state의 실행이 끝나고 전이할 다음 state 번호입니다.
	 */
	public ApplicationStateDefinition(int ID, int nextStateToGoThrough)
	{
		this.ID = ID;
		this.isGoThroughState = true;
		this.nextStateToGoThrough = nextStateToGoThrough;
		rules = new ArrayList<Rule>();
		menuText = String.format("State#%d에 대한 기본 메뉴 텍스트입니다.", ID);
		callback_UpdateMenuText = null;
		callback_Entering = null;
		callback_Leaving = null;
	}
}
