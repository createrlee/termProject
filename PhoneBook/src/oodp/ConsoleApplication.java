package oodp;

import java.util.ArrayList;

/**
 * Java 콘솔 환경에서 돌아가는 응용 프로그램을 만들기 위해 필요한 기본 요소들을 캡슐화합니다.<br>
 * <br>
 * <b>과제 채점자께 드리는 당부 말씀:</b><br>
 * 이 클래스는 2015년도 객체지향설계및프로그래밍 텀 프로젝트#2로 출제되었으며
 * 당시 <b>모든 수강생들</b>이 이 클래스 및 종속된 다른 클래스들의 <b>모든 기능들</b>을 사용하여 꽤 멋진 응용 프로그램을 만들어 제출하였습니다.
 * 따라서, 이 클래스는 당시 실습조교가 정규화한 것임에도 불구하고
 * 세부 내용은 학생들 모두 각자 직접 작성해 본 <b>자신의 코드</b>를 <b>있는 그대로</b> 라이브러리화한 것에 준하므로,
 * 이후 학생들(당시 수강생들)이 다른 수업 그리고/또는 다른 과제에서 이 클래스를 import하여 사용하는 경우에도
 * 이 클래스 및 종속된 다른 클래스의 코드 파일이 유사성 검사 등을 수반한 copy 패널티의 대상으로 작용하지 않도록 배려해 주시기 바랍니다.
 * 
 * @author Racin
 *
 */
public class ConsoleApplication
{
	/**
	 * 콘솔 입력을 처리하기 위한 친구입니다.
	 * 이 필드는 private이므로 클래스 외부에서 보이지 않습니다.
	 */
	private Parser parser;

	/**
	 * 각 state 번호별 정의 목록입니다.
	 * 자세한 내용은 <code>ApplicationStateDefinition.java</code>를 참고하세요.<br>
	 * <br>
	 * Note: 대문자로 <code>ASD</code> 치고 Ctrl + Space를 누르면 왠만한 경우 <code>ApplicationStateDefinition</code>이 딱 뜹니다.
	 */
	public ArrayList<ApplicationStateDefinition> stateDefinitions;

	/**
	 * 프로그램의 현재 상태를 나타내는 정수 값입니다.
	 * 여러분은 특정 state에서 특정 명령어를 입력받았을 때 이 필드의 값을 바꾸도록 코드를 구성함으로써
	 * 전체 프로그램의 실행 흐름을 구성하게 됩니다.
	 * 
	 * 초기값은 0입니다.
	 */
	public int state;

	/**
	 * 맨 처음 응용 프로그램이 걸려 있는 state입니다.
	 * 이 state는 사실 아무 곳에도 쓰이지 않습니다.
	 * 게다가 이 필드는 private이므로 클래스 외부에서 보이지 않습니다.
	 */
	private ApplicationStateDefinition nullState;

	public ConsoleApplication()
	{
		parser = new Parser(System.in);
		stateDefinitions = new ArrayList<ApplicationStateDefinition>();
		state = 0;
		nullState = new ApplicationStateDefinition(-1);
	}

	/**
	 * Java 콘솔 응용프로그램을 시작합니다.<br>
	 * 정의되지 않은 state로 점프하는 경우 오류 메시지를 출력하고 장비를 정지합니다.<br>
	 * <br>
	 * <b>주의 1: </b>
	 * 이 메서드를 호출하기 전에 필요한 모든 정보(각 state 정의)를 입력해 두어야 합니다!
	 * state 정의를 빼 먹은 경우엔, 보통 그냥 오류 났을 때 머리 한 대 치고 추가하면 됩니다.<br>
	 * <br>
	 * <b>주의 2: </b>
	 * 이 메서드를 호출하고 난 다음에는 가급적 state 정의 자체는 더 건드리지 마세요.
	 * 실행 도중 실행 구조를 바꾸는 코드는 컴퓨터 3대 재앙 중 하나에 해당하며,
	 * 여러분이 악성코드를 만들고 싶은 게 아닌 이상 그런 시도는 자제해야만 합니다.
	 */
	public void Start()
	{
		// 등록된 state가 없는 경우 장비를 정지
		if ( stateDefinitions.isEmpty() == true )
		{
			System.err.printf("Error. state가 하나도 정의되어 있지 않습니다. 장비를 정지합니다.\n", state);
			System.exit(-1);
		}

		// previousDef에는 '이전에 실행한 state 정의가 들어 있음
		ApplicationStateDefinition previousDef = nullState;

		// currentDef에는 '이번에 실행할 state 정의를 찾아 담아 사용함
		ApplicationStateDefinition currentDef = null;


		while ( true )
		{
			/* -------------------------------------------------------------------------------------------
			 * Phase 1. 이번에 실행할 state 정의 탐색
			 */
			currentDef = null;

			// state 정의 목록에서 현재 state값이 ID인 정의 선택
			for ( ApplicationStateDefinition def : stateDefinitions )
			{
				if ( def.ID == state )
				{
					currentDef = def;
					break;
				}
			}

			// 탐색에 실패한 경우 오류 메시지를 출력하고 장비를 정지
			if ( currentDef == null )
			{
				System.err.printf("Error. 정의되지 않은 state(%d)로 전이를 시도했습니다. 장비를 정지합니다.\n", state);
				System.exit(-1);
			}


			/* -------------------------------------------------------------------------------------------
			 * Phase 2. 상태 전이 처리용 루틴 실행
			 */
			// 이전 state 정의와 이번 state 정의가 다른 경우 '이전 state를 떠날 때 실행할 루틴'과 '이번 state에 도착할 때 실행할 루틴'을 실행
			if ( previousDef != currentDef && previousDef != null )
			{
				if ( previousDef.callback_Leaving != null )
					previousDef.callback_Leaving.accept(previousDef.ID, StateCallbackType.Leaving);

				if ( currentDef.callback_Entering != null )
					currentDef.callback_Entering.accept(currentDef.ID, StateCallbackType.Entering);
			}
			// 그렇지 않은 경우 '이번 state를 반복할 때 실행할 루틴'을 실행
			else
			{
				if ( currentDef.callback_Repeating != null )
					currentDef.callback_Repeating.accept(currentDef.ID, StateCallbackType.Repeating);
			}


			/* -------------------------------------------------------------------------------------------
			 * Phase 3. 메뉴 텍스트 출력
			 */
			// 그 때 그 때 바뀌는 메뉴 텍스트를 쓰는 경우 콜백 메서드를 호출하여 메뉴 텍스트 갱신
			if ( currentDef.callback_UpdateMenuText != null )
				currentDef.menuText = currentDef.callback_UpdateMenuText.apply(currentDef.ID);

			// 출력
			System.out.print(currentDef.menuText);


			/* -------------------------------------------------------------------------------------------
			 * Phase 4. state 실행
			 */
			// Go-Through state가 아니라면 명령어 입력받기 시작
			if ( currentDef.isGoThroughState == false )
			{
				parser.rules = currentDef.rules;
				parser.Accept();
			}
			// Go-Through state가 맞다면 즉시 다음 state로 이동
			else
			{
				state = currentDef.nextStateToGoThrough;
			}

			/* -------------------------------------------------------------------------------------------
			 * Phase 5. 실행이 끝나면 전이 여부 체크를 위해 이번 state 정의를 이전 state 정의가 되도록 빽업
			 */
			previousDef = currentDef;
		}
	}
}
