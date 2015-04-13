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
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		PhoneBook phoneBook = new PhoneBook();
		
		/*console application을 이용하기 위해 몇가지 변수를 정의*/
		ConsoleApplication app = new ConsoleApplication();
		ApplicationStateDefinition newDef;
		Rule newRule;
		
		/*state 0: 메인메뉴*/
		newDef = new ApplicationStateDefinition(0);
		newDef.menuText = "-----------메인 메뉴-----------\n"
				+ "     원하는 작업을 선택하세요\n"
				+ "1.add 2.search 3.group 4.quit\n"
				+ "------------------------------\n"
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
		
		// 1 -> state 11로 이동
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
			System.out.println(name + "의 연락처가 삭제되었습니다");
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
		newRule.Execute = tokens -> app.state = 0;
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
		newDef.menuText = "-------------그룹 관리--------------\n"
				+ "다음 중 수행할 작업을 선택하세요\n"
				+ "1.list group 2.add group\n"
				+ "3.change group name 4.delete group\n"
				+ "5.return to main menu 6. quit\n"
				+ "------------------------------------\n"
				+ "->";
		
		// 1 -> 그룹 목록을 출력하고 특정 그룹을 선택한 후 state 21(그룹별 연락처 관리 메뉴)로 이동
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("1");
		newRule.Execute = tokens ->
		{
			phoneBook.listGroup();
			System.out.print("*관리할 그룹을 입력하세요\n->");
			group=sc.nextLine();
			app.state=21;
		};
		newDef.rules.add(newRule);
		
		// 2 -> 새로운 그룹을 추가
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("2");
		newRule.Execute = tokens ->
		{
			System.out.print("*새롭게 추가하려는 그룹의 이름을 입력하세요\n->");
			group=sc.nextLine();
			phoneBook.addGroup(group);
		};
		newDef.rules.add(newRule);
		
		// 3 -> 그룹 이름 변경
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("3");
		newRule.Execute = tokens ->
		{
			System.out.println("***프로그램을 종료합니다***");
			System.exit(0);
		};
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
		
		// 5 -> 프로그램 종료
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("5");
		newRule.Execute = tokens ->
		{
			System.out.println("***프로그램을 종료합니다***");
			System.exit(0);
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
		
		// 1 -> 이름을 변경한 후 state 1(검색한 연락처에 대한 작업선택 메뉴)로 이동
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("1");
		newRule.Execute = tokens ->
		{
			System.out.print("변경할 이름을 입력하세요\n->");
			temp = sc.nextLine();
			phoneBook.change(name, temp, 1);
			name = temp;//이름을 바꾸었으므로
			app.state = 1;
		};
		newDef.rules.add(newRule);
		
		// 2 -> 전화번호를 변경한 후 state 1(검색한 연락처에 대한 작업선택 메뉴)로 이동
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("2");
		newRule.Execute = tokens ->
		{
			System.out.print("변경할 전화번호를 입력하세요\n->");
			temp = sc.nextLine();
			phoneBook.change(name, temp, 2);
			phoneNum = temp;//전화번호를 바꾸었으므로
			app.state = 1;
		};
		newDef.rules.add(newRule);
		
		// 맨 마지막에 오류 규칙 추가
		newDef.rules.add(Parser.CommonRules.rule_error);
		
		// 응용 프로그램에 지금 만든 state 정의 추가
		app.stateDefinitions.add(newDef);
		
		/*프로그램 시작*/
		app.Start();
		
		/*

		while (select != "8" || select != "quit")
		{
			for (Group iGroup : phoneBook)
				iGroup.listPhone();
			System.out.println("-----------------------------");
			System.out.println("     원하는 작업을 선택하세요");
			System.out.println("1.add 2.search 3.group 4.quit");
			System.out.println("-----------------------------");
			
			// 작업선택
			select = sc.nextLine();
			switch (select)
			{
			case "5":
			case "quit":
				break;
			
			case "1":
			case "add":
				System.out.print("이름을 입력하세요:");
				name = sc.nextLine();
				System.out.print("전화번호를 입력하세요:");
				phoneNum = sc.nextLine();
				System.out.println("저장할 그룹을 입력하세요. 입력한 그룹이 목록에 없는 경우 새로 생성합니다.");
				
				for (int indexOfGroup = 1; indexOfGroup < phoneBook.size(); indexOfGroup++)
					// 모든 그룹명을 출력
					System.out.print((indexOfGroup) + "." + phoneBook.get(indexOfGroup).groupName + " ");
				System.out.println(phoneBook.size() + ".no group");
				group = sc.nextLine();
				
				if (phoneBook.size() == Integer.parseInt(group) || "no group" == group)// 그룹이 없는것을 선택한 경우
					phoneBook.get(0).addPhone(name, phoneNum);
				else
				// 그룹을 지정한 경우
				{
					boolean noSuchGroup = true;
					
					for (Group igroup : phoneBook)// 그룹 이름으로 입력한 경우
					{
						if (igroup.groupName == group)
						{
							igroup.addPhone(name, phoneNum);
							noSuchGroup = false;
							break;
						}
					}
					
					if (noSuchGroup)
					{
						int groupNum = Integer.parseInt(group);// 그룹 번호로 입력한 경우
						if (groupNum >= 1 && groupNum < phoneBook.size())
						{
							phoneBook.get(groupNum).addPhone(name, phoneNum);
							noSuchGroup = false;
						}
					}
					
					if (noSuchGroup)// 목록에 입력한 그룹이 없는 경우 새로운 그룹 생성
					{
						phoneBook.add(new Group(group));
						phoneBook.get(phoneBook.size() - 1).addPhone(name, phoneNum);
					}
				}
				break;
			
			case "2":
			case "search":
				System.out.print("검색할 이름 혹은 전화번호를 입력하세요:");
				name = sc.nextLine();
				
				int Gindex = 0;// 어떤 그룹에 들어있는가
				int index = 0;// 그룹 안에서 어디에 들어있는가
				for (Group iGroup : phoneBook)
				{
					if ((index = iGroup.searchPhone(name)) >= 0)
						break;
					Gindex++;
				}
				
				System.out.println("-----------------------------------------");
				System.out.println("다음 중 검색된 전화번호에 대해 수행할 작업을 선택하세요");
				System.out.println("1.change 2.delete 3.message 4.call 5.quit");
				System.out.println("-----------------------------------------");
				select = sc.nextLine();
				
				switch (select)
				{
				case "5":
				case "quit":
					break;
				case "1":
				case "change":
					System.out.println("어떤것을 변경하시겠습니까? 1.name 2.phone number");
					select = sc.nextLine();
					
					switch (select)
					{
					case "1":
					case "name":
						System.out.print("변경할 이름을 입력하세요: ");
						name = sc.nextLine();
						phoneBook.get(Gindex).changePhoneName(index, name);
						break;
					case "2":
					case "phone number":
						System.out.print("변경할 전화번호를 입력하세요: ");
						phoneNum = sc.nextLine();
						phoneBook.get(Gindex).changePhoneNum(index, phoneNum);
						break;
					}
					
					break;
				case "2":
				case "delete":
					System.out.println("삭제할 연락처의 이름을 입력하세요: ");
					name = sc.nextLine();
					phoneBook.get(Gindex).deletePhone(name);
					break;
				case "3":
				case "message":
					break;
				case "4":
				case "call":
					break;
				}
				
				break;
			case "3":
			case "group":
				break;
			default:
				System.out.println("올바른 값을 입력하세요");
				break;
			}
		}*/
	}
}