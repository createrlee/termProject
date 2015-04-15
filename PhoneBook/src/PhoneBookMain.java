import java.util.ArrayList;
import java.util.Scanner;

import oodp.*;

/**
 * 프로그램을 실행시켜 주는 메인 클래스입니다.
 * @author createrlee
 *
 */
public class PhoneBookMain
{
	static String name = null;
	static String phoneNum = null;
	static String group = null;
	static String temp = null;
	static boolean isGroupMenu = false;
	static PhoneBook phoneBook;
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		phoneBook = new PhoneBook();
		
		/*console application을 이용하기 위해 몇가지 변수를 정의*/
		ConsoleApplication app = new ConsoleApplication();
		ApplicationStateDefinition newDef;
		Rule newRule;
		
		/*state 0: 메인메뉴*/
		newDef = new ApplicationStateDefinition(0);
		newDef.callback_Entering = (state, type) -> 
			phoneBook.list();//전체 주소록의 리스트를 출력해준다
		newDef.callback_Repeating = (state, type) -> phoneBook.list();
		newDef.menuText = "-----------메인 메뉴-----------\n"
				+ "     원하는 작업을 선택하세요\n"
				+ "1.add 2.search 3.group 4.quit\n"
				+ "-----------------------------\n"
				+ "선택->";
		
		// 1 -> 연락처 추가
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("1");
		newRule.Execute = tokens ->
		{
			System.out.print("*이름을 입력하세요->");
			name = sc.nextLine();
			System.out.print("*전화번호를 입력하세요->");
			phoneNum = sc.nextLine();
			System.out.println("*저장할 그룹을 입력하세요. 입력한 그룹이 목록에 없는 경우 새로 생성합니다.");
			System.out.println("아무 그룹을 입력하지 않은 경우 그룹 없이 저장됩니다.");
			phoneBook.listGroup();//그룹 목록 출력
			System.out.print("->");
			group = sc.nextLine();
			phoneBook.add(name, phoneNum, group);//연락처 추가
		};
		newDef.rules.add(newRule);
		
		// 2 -> 검색할 연락처 이름을 입력받은 후 state 1(선택된 연락처에 대한 작업선택 메뉴)으로 이동
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("2");
		newRule.Execute = tokens ->
		{
			System.out.println("검색할 연락처의 이름을 입력하세요");
			name = sc.nextLine();
			phoneBook.search(name);
			app.state = 1;
		};
		newDef.rules.add(newRule);
		
		// 3 -> 그룹 목록을 출력한 후 state 2(그룹 관리 메뉴)로 이동
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("3");
		newRule.Execute = tokens ->
		{
			phoneBook.listGroup();
			isGroupMenu = true;//그룹메뉴에 진입하므로
			app.state = 2;
		};
		newDef.rules.add(newRule);
		
		// 4 -> 종료
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("4");
		newRule.Execute = tokens ->
		{
			System.out.println("***프로그램을 종료합니다***");
			System.exit(0);
		};
		newDef.rules.add(newRule);
		
		// 맨 마지막에 오류 규칙 추가
		newDef.rules.add(Parser.CommonRules.rule_error);
		
		// 응용 프로그램에 지금 만든 state 정의 추가
		app.stateDefinitions.add(newDef);
		
		/*state 1: 선택된 연락처에 대한 작업선택 메뉴*/
		newDef = new ApplicationStateDefinition(1);
		newDef.menuText = "-----------------------------------------\n"
				+ "다음 중 검색된 전화번호에 대해 수행할 작업을 선택하세요\n"
				+ "1.change 2.delete 3.message 4.call\n"
				+ "5.return to main menu 6.quit\n"
				+ "-----------------------------------------\n"
				+ "->";
		
		// 1 -> state 11(어떤 정보를 변경할 것인지를 선택하는 메뉴)로 이동
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("1");
		newRule.Execute = tokens -> app.state = 11;
		newDef.rules.add(newRule);
		
		// 2 -> 선택된 연락처를 삭제한 후 state 0(메인메뉴)로 돌아간다
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("2");
		newRule.Execute = tokens ->
		{
			phoneBook.delete(name);
			app.state = 0;
		};
		newDef.rules.add(newRule);
		
		// 3 -> 선택된 연락처의 메세지 메소드 실행
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("3");
		newRule.Execute = tokens -> phoneBook.message(name);
		newDef.rules.add(newRule);
		
		// 4 -> 선택된 연락처의 전화 메소드 실행
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("4");
		newRule.Execute = tokens -> phoneBook.call(name);
		newDef.rules.add(newRule);
		
		// 5 -> state 0(메인메뉴)로 이동
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("5");
		newRule.Execute = tokens -> 
			app.state = 0;
		newDef.rules.add(newRule);
		
		// 6 -> 프로그램 종료
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("6");
		newRule.Execute = tokens ->
		{
			System.out.println("***프로그램을 종료합니다***");
			System.exit(0);
		};
		newDef.rules.add(newRule);
		
		// 맨 마지막에 오류 규칙 추가
		newDef.rules.add(Parser.CommonRules.rule_error);
		
		// 응용 프로그램에 지금 만든 state 정의 추가
		app.stateDefinitions.add(newDef);
		
		/*state 2: 그룹 관리 메뉴*/
		newDef = new ApplicationStateDefinition(2);
		newDef.callback_Entering=(state,type)->phoneBook.listGroup();
		newDef.callback_Repeating=(state,type)->phoneBook.listGroup();
		newDef.menuText = "-------------그룹 관리--------------\n"
				+ "다음 중 수행할 작업을 선택하세요\n"
				+ "1.select group 2.add group\n"
				+ "3.change group name 4.delete group\n"
				+ "5.return to main menu 6. quit\n"
				+ "----------------------------------\n"
				+ "->";
		
		// 1 -> 특정 그룹을 선택한 후 state 21(그룹별 연락처 관리 메뉴)로 이동
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("1");
		newRule.Execute = tokens ->
		{
			System.out.print("*관리할 그룹을 입력하세요\n->");
			group = sc.nextLine();
			if (phoneBook.getIntoGroupMode(group))
				app.state = 21;
		};
		newDef.rules.add(newRule);
		
		// 2 -> 새로운 그룹을 추가
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("2");
		newRule.Execute = tokens ->
		{
			System.out.print("*새롭게 추가하려는 그룹의 이름을 입력하세요\n->");
			group = sc.nextLine();
			phoneBook.addGroup(group);
			System.out.println("그룹 " + group + "이(가) 추가되었습니다");
		};
		newDef.rules.add(newRule);
		
		// 3 -> 그룹 이름 변경
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("3");
		newRule.Execute = tokens ->
		{
			System.out.print("*이름을 변경하고자 하는 그룹을 입력하세요\n->");
			group = sc.nextLine();
			System.out.print("*바꿀 이름을 입력하세요\n->");
			temp = sc.nextLine();
			phoneBook.changeGroupName(group, temp);
			System.out.println(group + "->" + temp + " 그룹 이름이 변경되었습니다");
			group = temp;
		};
		newDef.rules.add(newRule);
		
		// 4 -> 그룹 삭제
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("4");
		newRule.Execute = tokens ->
		{
			System.out.print("*삭제하려는 그룹을 입력하세요\n->");
			group = sc.nextLine();
			phoneBook.deleteGroup(group);
		};
		newDef.rules.add(newRule);
		
		// 5 -> state 0(메인 메뉴)로 이동
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("5");
		newRule.Execute = tokens ->
		{
			isGroupMenu = false;//그룹메뉴에서 나가므로
			app.state = 0;
		};
		newDef.rules.add(newRule);
		
		// 6 -> 프로그램 종료
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("6");
		newRule.Execute = tokens ->
		{
			System.out.println("***프로그램을 종료합니다***");
			System.exit(0);
		};
		newDef.rules.add(newRule);
		
		// 맨 마지막에 오류 규칙 추가
		newDef.rules.add(Parser.CommonRules.rule_error);
		
		// 응용 프로그램에 지금 만든 state 정의 추가
		app.stateDefinitions.add(newDef);
		
		/*state 11 : 어떤 정보를 변경할 것인지를 선택하는 메뉴*/
		newDef = new ApplicationStateDefinition(11);
		newDef.menuText = "----------------------------\n"
				+ "   어떤 정보를 변경하시겠습니까?\n"
				+ "1.name 2.phone number 3.group\n"
				+ "-----------------------------\n"
				+ "->";
		
		// 1 -> 이름을 변경한 후 state 1(검색한 연락처에 대한 작업선택 메뉴)또는 state 211(그룹에서 선택된 연락처에 대한 작업선택 메뉴)로 이동
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("1");
		newRule.Execute = tokens ->
		{
			System.out.print("변경할 이름을 입력하세요\n->");
			temp = sc.nextLine();
			phoneBook.change(name, temp, 1);
			name = temp;//이름을 바꾸었으므로
			if (!isGroupMenu)
				app.state = 1;
				else
					app.state = 211;
			};
		newDef.rules.add(newRule);
		
		// 2 -> 전화번호를 변경한 후 state 1(검색한 연락처에 대한 작업선택 메뉴)또는 state 211(그룹에서 선택된 연락처에 대한 작업선택 메뉴)로 이동
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("2");
		newRule.Execute = tokens ->
		{
			System.out.print("변경할 전화번호를 입력하세요\n->");
			temp = sc.nextLine();
			phoneBook.change(name, temp, 2);
			phoneNum = temp;//전화번호를 바꾸었으므로
			if (!isGroupMenu)
				app.state = 1;
				else
					app.state = 211;
			};
		newDef.rules.add(newRule);
		
		// 3 -> 그룹을 변경한 후 state 1(검색한 연락처에 대한 작업선택 메뉴)또는 state 211(그룹에서 선택된 연락처에 대한 작업선택 메뉴)로 이동
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("3");
		newRule.Execute = tokens ->
		{
			System.out.print("변경할 그룹을 입력하세요\n->");
			temp = sc.nextLine();
			phoneBook.change(name, temp, 3);
			group = temp;//전화번호를 바꾸었으므로
			if (!isGroupMenu)
				app.state = 1;
				else
					app.state = 211;
			};
		newDef.rules.add(newRule);
		
		// 맨 마지막에 오류 규칙 추가
		newDef.rules.add(Parser.CommonRules.rule_error);
		
		// 응용 프로그램에 지금 만든 state 정의 추가
		app.stateDefinitions.add(newDef);
		
		/*state 21 : 그룹별 연락처 관리 메뉴*/
		newDef = new ApplicationStateDefinition(21);
		newDef.callback_Entering = (state, type) -> 
		{
			phoneBook.list();
		};
		newDef.callback_Repeating = (state, type) -> phoneBook.list();
		newDef.callback_Leaving=(state,type)->phoneBook.escapeGroupMode();
		newDef.menuText = "--------------------------------------\n"
				+ "선택된 그룹의 연락처에 대해 원하는 작업을 선택하세요\n"
				+ "1.add 2.search 3.return to group menu\n"
				+ "4.quit\n"
				+ "---------------------------------------\n"
				+ "선택->";
		
		// 1 -> 연락처 추가
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("1");
		newRule.Execute = tokens ->
		{
			System.out.print("*이름을 입력하세요->");
			name = sc.nextLine();
			System.out.print("*전화번호를 입력하세요->");
			phoneNum = sc.nextLine();
			phoneBook.add(name, phoneNum, "");//연락처 추가(그룹은 따로 지정할 필요 없음)
			System.out.println(name + "의 연락처가 추가되었습니다");
		};
		newDef.rules.add(newRule);
		
		// 2 -> 검색할 연락처 이름을 입력받은 후 state 211(그룹에서 선택된 연락처에 대한 작업선택 메뉴)으로 이동
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("2");
		newRule.Execute = tokens ->
		{
			System.out.println("검색할 연락처의 이름을 입력하세요");
			name = sc.nextLine();
			phoneBook.search(name);
			app.state = 211;
		};
		newDef.rules.add(newRule);
		
		// 3 -> state 2(그룹 관리 메뉴)로 이동
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("3");
		newRule.Execute = tokens -> app.state = 2;
		newDef.rules.add(newRule);
		
		// 4 -> 프로그램 종료
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("4");
		newRule.Execute = tokens ->
		{
			System.out.println("***프로그램을 종료합니다***");
			System.exit(0);
		};
		newDef.rules.add(newRule);
		
		// 맨 마지막에 오류 규칙 추가
		newDef.rules.add(Parser.CommonRules.rule_error);
		
		// 응용 프로그램에 지금 만든 state 정의 추가
		app.stateDefinitions.add(newDef);
		
		/*state 211: 그룹에서 선택된 연락처에 대한 작업선택 메뉴*/
		newDef = new ApplicationStateDefinition(211);
		newDef.menuText = "-----------------------------------------\n"
				+ "다음 중 검색된 전화번호에 대해 수행할 작업을 선택하세요\n"
				+ "1.change 2.delete 3.message 4.call\n"
				+ "5.return to group menu 6.quit\n"
				+ "-----------------------------------------\n"
				+ "->";
		
		// 1 -> state 11로 이동
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("1");
		newRule.Execute = tokens -> app.state = 11;
		newDef.rules.add(newRule);
		
		// 2 -> 선택된 연락처를 삭제한 후 state 21(그룹별 연락처 관리 메뉴)로 돌아감
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("2");
		newRule.Execute = tokens ->
		{
			phoneBook.delete(name);
			System.out.println(name + "의 연락처가 삭제되었습니다");
			app.state = 21;
		};
		newDef.rules.add(newRule);
		
		// 3 -> 선택된 연락처의 메세지 메소드 실행
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("3");
		newRule.Execute = tokens -> phoneBook.message(name);
		newDef.rules.add(newRule);
		
		// 4 -> 선택된 연락처의 전화 메소드 실행
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("4");
		newRule.Execute = tokens -> phoneBook.call(name);
		newDef.rules.add(newRule);
		
		// 5 -> state 2(그룹 관리 메뉴)로 이동
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("5");
		newRule.Execute = tokens -> app.state = 2;
		newDef.rules.add(newRule);
		
		// 6 -> 프로그램 종료
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("6");
		newRule.Execute = tokens ->
		{
			System.out.println("***프로그램을 종료합니다***");
			System.exit(0);
		};
		newDef.rules.add(newRule);
		
		// 맨 마지막에 오류 규칙 추가
		newDef.rules.add(Parser.CommonRules.rule_error);
		
		// 응용 프로그램에 지금 만든 state 정의 추가
		app.stateDefinitions.add(newDef);
		
		/*프로그램 시작*/
		app.Start();
	}
}