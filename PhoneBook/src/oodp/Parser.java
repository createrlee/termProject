package oodp;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 텍스트 입력을 줄 단위로 분석하여 명령을 수행하는 기능을 노출하는 클래스입니다.<br>
 * <br>
 * <b>과제 채점자께 드리는 당부 말씀:</b><br>
 * 이 클래스는 2015년도 객체지향설계및프로그래밍 실습과제#2로 출제되었으며
 * 당시 <b>모든 수강생들</b>이 이 클래스 및 종속된 다른 클래스들의 <b>모든 기능들</b>을 성공적으로 구현하여 제출하였습니다.
 * 따라서, 이 클래스는 당시 실습조교가 정규화한 것임에도 불구하고
 * 세부 내용은 학생들 모두 각자 직접 작성해 본 <b>자신의 코드</b>를 <b>있는 그대로</b> 라이브러리화한 것에 준하므로,
 * 이후 학생들(당시 수강생들)이 다른 수업 그리고/또는 다른 과제에서 이 클래스를 import하여 사용하는 경우에도
 * 이 클래스 및 종속된 다른 클래스의 코드 파일이 유사성 검사 등을 수반한 copy 패널티의 대상으로 작용하지 않도록 배려해 주시기 바랍니다.
 * 
 * @author Racin
 *
 */
public class Parser
{
	protected ArrayList<Rule> rules;

	private Scanner scanner;

	/**
	 * 자주 사용되는 Rule들을 미리 정의해 둔 정적 내부 클래스입니다.
	 * 
	 * @author Racin
	 *
	 */
	public static class CommonRules
	{
		/**
		 * 오류 메세지를 내보내는 야매(pseudo) 명령어입니다.
		 * 
		 * 일반적인 Rule은 특정 키워드에 매핑되지만,
		 * 이 Rule은 '모든 입력'을 다 자신의 것이라고 우깁니다.
		 * 따라서, 이 Rule을 규칙 목록 맨 위에 놓으면 (가장 먼저 <code>add()</code>하거나 하면)
		 * 우리 성실한 Parser는 '모든 입력'을 다 오류라고 뱉어내는 희대의 츤츤머신이 됩니다.
		 * 
		 * 따라서, 이 Rule은 규칙 목록 맨 아래에 깔아 놓고
		 * 다른 Rule들이 다 <code>Match()</code>되지 않았을 때 이를 확인하고 대처하는 용도로 쓰면 좋겠습니다.<br>
		 * <br>
		 * <code>Match()</code>: 항상 성공합니다.<br>
		 * <code>Split()</code>: 단어 단위 기본 분할을 수행합니다.<br>
		 * <code>Execute()</code>: 분할된 token들을 다시 이어 붙이고 에러 메세지와 함께 출력합니다.
		 */
		public static Rule rule_error;

		/**
		 * 프로그램을 즉시 종료하는 명령어입니다.
		 * 
		 * 키워드는 <code>quit</code>입니다.
		 * 더 설명할 것도 없는, Parser가 실행할 수 있는 가장 강력한 명령어입니다.<br>
		 * <br>
		 * <code>Match()</code>: 줄 입력이 "quit"으로 시작하는 경우 성공합니다.<br>
		 * <code>Split()</code>: 분할하지 않습니다(전체 텍스트를 단어 하나로 간주합니다).<br>
		 * <code>Execute()</code>: 프로그램을 즉시 종료합니다.
		 */
		public static Rule rule_quit;

		/**
		 * new 쓸 때 '생성자'가 호출되어 새로운 인스턴스를 초기화하듯,
		 * 프로그램을 시작할 때 '정적 생성자'같은 놈이 호출되어 static 요소들을 초기화하게 되는데,
		 * 클래스 안에 static 하고 바로 중괄호 나오는 부분이,
		 * 해당 클래스에 있는 static 요소들을 초기화하는 바로 그 부분입니다.
		 */
		static
		{
			rule_error = new Rule();
			rule_error.Match = input -> true;
			rule_error.Execute = tokens -> System.out.println("Error. 알 수 없는 명령어입니다: " + String.join(" ", tokens));

			rule_quit = new Rule(false);
			rule_quit.Match = input -> input.startsWith("quit");
			rule_quit.Execute = tokens -> System.exit(0);
		}
	}

	/**
	 * <code>Parser class</code>의 새로운 인스턴스를 초기화합니다.
	 * 
	 * @param is
	 *            줄 입력을 받을 스트림입니다. 콘솔을 통해 키보드 입력을 받으려는 경우 <code>System.in</code>을 담으세요.
	 */
	public Parser(InputStream is)
	{
		rules = new ArrayList<Rule>();
		scanner = new Scanner(is);
	}

	/**
	 * 입력 스트림에서 다음 줄을 읽어 분석하고 명령을 수행합니다.
	 */
	public void Accept()
	{
		String input = scanner.nextLine();

		//Note: foreach문으로 ArrayList<>를 순회하는 경우 각 요소들은 0번째부터 차례대로 끌려 나옵니다.
		for ( Rule rule : rules )
		{
			if ( rule.Match.test(input) == true )
			{
				String[] tokens = rule.Split.apply(input);
				rule.Execute.accept(tokens);
				break;
			}
		}
		
		/*
		 * Note: 우리가 쓸 대부분의 응용 환경에서
		 *       rules 배열 맨 마지막에는 아마 오류 뱉는 rule_error가 들어 있을 것입니다.
		 *       따라서 위의 foreach문은 'if문이 true고 정상적으로 break되는' 상황이 반드시 딱 한 번 발생합니다.
		 *       우리가 Accept()에서 원하던 작업이 바로 '적당한 Rule 골라 Execute() 호출'이었으므로
		 *       우리는 저 foreach문에 대한 추가 검증 작업 없이 즉시 메서드 실행을 종료할 수 있습니다.
		 *       
		 *       나중에 여러분이 새로운 메서드를 짤 때는
		 *       어떤 코드가,
		 *       
		 *       1. 항상 원하는 순서대로,
		 *       2. 항상 원하는 부분만,
		 *       3. 항상 원하는 횟수만큼 실행되는지 확인하고
		 *       
		 *       -1. 순서가 뒤바뀌거나,
		 *       -2. 원하지 않는 부분이 실행되거나,
		 *       -3. 원하는 횟수보다 덜/더 실행되는 경우를 열심히 막아 내야 합니다.
		 *       
		 *       위의 여섯 단계를 검증하지 않고 실행하다가 오류 나면
		 *       이건 Eclipse도 어떻게 잡아 줄 수 없는 부분인데다 당연히 여러분 눈으로도 캐치하기 매우 난감하니
		 *       좀 번거롭더라도 한 칸 한 칸 확실히 가늠해 보고, 필요하면 주석으로 찍어 놓으며 전진하는 습관을 들여 봅시다. 
		 */
	}
}
