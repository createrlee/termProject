import java.util.ArrayList;
import java.util.Scanner;

import oodp.*;

/**
 * ���α׷��� ������� �ִ� ���� Ŭ�����Դϴ�.
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
		
		/*console application�� �̿��ϱ� ���� ��� ������ ����*/
		ConsoleApplication app = new ConsoleApplication();
		ApplicationStateDefinition newDef;
		Rule newRule;
		
		/*state 0: ���θ޴�*/
		newDef = new ApplicationStateDefinition(0);
		newDef.callback_Entering = (state, type) -> 
			phoneBook.list();//��ü �ּҷ��� ����Ʈ�� ������ش�
		newDef.callback_Repeating = (state, type) -> phoneBook.list();
		newDef.menuText = "-----------���� �޴�-----------\n"
				+ "     ���ϴ� �۾��� �����ϼ���\n"
				+ "1.add 2.search 3.group 4.quit\n"
				+ "-----------------------------\n"
				+ "����->";
		
		// 1 -> ����ó �߰�
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("1");
		newRule.Execute = tokens ->
		{
			System.out.print("*�̸��� �Է��ϼ���->");
			name = sc.nextLine();
			System.out.print("*��ȭ��ȣ�� �Է��ϼ���->");
			phoneNum = sc.nextLine();
			System.out.println("*������ �׷��� �Է��ϼ���. �Է��� �׷��� ��Ͽ� ���� ��� ���� �����մϴ�.");
			System.out.println("�ƹ� �׷��� �Է����� ���� ��� �׷� ���� ����˴ϴ�.");
			phoneBook.listGroup();//�׷� ��� ���
			System.out.print("->");
			group = sc.nextLine();
			phoneBook.add(name, phoneNum, group);//����ó �߰�
		};
		newDef.rules.add(newRule);
		
		// 2 -> �˻��� ����ó �̸��� �Է¹��� �� state 1(���õ� ����ó�� ���� �۾����� �޴�)���� �̵�
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("2");
		newRule.Execute = tokens ->
		{
			System.out.println("�˻��� ����ó�� �̸��� �Է��ϼ���");
			name = sc.nextLine();
			phoneBook.search(name);
			app.state = 1;
		};
		newDef.rules.add(newRule);
		
		// 3 -> �׷� ����� ����� �� state 2(�׷� ���� �޴�)�� �̵�
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("3");
		newRule.Execute = tokens ->
		{
			phoneBook.listGroup();
			isGroupMenu = true;//�׷�޴��� �����ϹǷ�
			app.state = 2;
		};
		newDef.rules.add(newRule);
		
		// 4 -> ����
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("4");
		newRule.Execute = tokens ->
		{
			System.out.println("***���α׷��� �����մϴ�***");
			System.exit(0);
		};
		newDef.rules.add(newRule);
		
		// �� �������� ���� ��Ģ �߰�
		newDef.rules.add(Parser.CommonRules.rule_error);
		
		// ���� ���α׷��� ���� ���� state ���� �߰�
		app.stateDefinitions.add(newDef);
		
		/*state 1: ���õ� ����ó�� ���� �۾����� �޴�*/
		newDef = new ApplicationStateDefinition(1);
		newDef.menuText = "-----------------------------------------\n"
				+ "���� �� �˻��� ��ȭ��ȣ�� ���� ������ �۾��� �����ϼ���\n"
				+ "1.change 2.delete 3.message 4.call\n"
				+ "5.return to main menu 6.quit\n"
				+ "-----------------------------------------\n"
				+ "->";
		
		// 1 -> state 11(� ������ ������ �������� �����ϴ� �޴�)�� �̵�
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("1");
		newRule.Execute = tokens -> app.state = 11;
		newDef.rules.add(newRule);
		
		// 2 -> ���õ� ����ó�� ������ �� state 0(���θ޴�)�� ���ư���
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("2");
		newRule.Execute = tokens ->
		{
			phoneBook.delete(name);
			app.state = 0;
		};
		newDef.rules.add(newRule);
		
		// 3 -> ���õ� ����ó�� �޼��� �޼ҵ� ����
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("3");
		newRule.Execute = tokens -> phoneBook.message(name);
		newDef.rules.add(newRule);
		
		// 4 -> ���õ� ����ó�� ��ȭ �޼ҵ� ����
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("4");
		newRule.Execute = tokens -> phoneBook.call(name);
		newDef.rules.add(newRule);
		
		// 5 -> state 0(���θ޴�)�� �̵�
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("5");
		newRule.Execute = tokens -> 
			app.state = 0;
		newDef.rules.add(newRule);
		
		// 6 -> ���α׷� ����
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("6");
		newRule.Execute = tokens ->
		{
			System.out.println("***���α׷��� �����մϴ�***");
			System.exit(0);
		};
		newDef.rules.add(newRule);
		
		// �� �������� ���� ��Ģ �߰�
		newDef.rules.add(Parser.CommonRules.rule_error);
		
		// ���� ���α׷��� ���� ���� state ���� �߰�
		app.stateDefinitions.add(newDef);
		
		/*state 2: �׷� ���� �޴�*/
		newDef = new ApplicationStateDefinition(2);
		newDef.callback_Entering=(state,type)->phoneBook.listGroup();
		newDef.callback_Repeating=(state,type)->phoneBook.listGroup();
		newDef.menuText = "-------------�׷� ����--------------\n"
				+ "���� �� ������ �۾��� �����ϼ���\n"
				+ "1.select group 2.add group\n"
				+ "3.change group name 4.delete group\n"
				+ "5.return to main menu 6. quit\n"
				+ "----------------------------------\n"
				+ "->";
		
		// 1 -> Ư�� �׷��� ������ �� state 21(�׷캰 ����ó ���� �޴�)�� �̵�
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("1");
		newRule.Execute = tokens ->
		{
			System.out.print("*������ �׷��� �Է��ϼ���\n->");
			group = sc.nextLine();
			if (phoneBook.getIntoGroupMode(group))
				app.state = 21;
		};
		newDef.rules.add(newRule);
		
		// 2 -> ���ο� �׷��� �߰�
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("2");
		newRule.Execute = tokens ->
		{
			System.out.print("*���Ӱ� �߰��Ϸ��� �׷��� �̸��� �Է��ϼ���\n->");
			group = sc.nextLine();
			phoneBook.addGroup(group);
			System.out.println("�׷� " + group + "��(��) �߰��Ǿ����ϴ�");
		};
		newDef.rules.add(newRule);
		
		// 3 -> �׷� �̸� ����
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("3");
		newRule.Execute = tokens ->
		{
			System.out.print("*�̸��� �����ϰ��� �ϴ� �׷��� �Է��ϼ���\n->");
			group = sc.nextLine();
			System.out.print("*�ٲ� �̸��� �Է��ϼ���\n->");
			temp = sc.nextLine();
			phoneBook.changeGroupName(group, temp);
			System.out.println(group + "->" + temp + " �׷� �̸��� ����Ǿ����ϴ�");
			group = temp;
		};
		newDef.rules.add(newRule);
		
		// 4 -> �׷� ����
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("4");
		newRule.Execute = tokens ->
		{
			System.out.print("*�����Ϸ��� �׷��� �Է��ϼ���\n->");
			group = sc.nextLine();
			phoneBook.deleteGroup(group);
		};
		newDef.rules.add(newRule);
		
		// 5 -> state 0(���� �޴�)�� �̵�
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("5");
		newRule.Execute = tokens ->
		{
			isGroupMenu = false;//�׷�޴����� �����Ƿ�
			app.state = 0;
		};
		newDef.rules.add(newRule);
		
		// 6 -> ���α׷� ����
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("6");
		newRule.Execute = tokens ->
		{
			System.out.println("***���α׷��� �����մϴ�***");
			System.exit(0);
		};
		newDef.rules.add(newRule);
		
		// �� �������� ���� ��Ģ �߰�
		newDef.rules.add(Parser.CommonRules.rule_error);
		
		// ���� ���α׷��� ���� ���� state ���� �߰�
		app.stateDefinitions.add(newDef);
		
		/*state 11 : � ������ ������ �������� �����ϴ� �޴�*/
		newDef = new ApplicationStateDefinition(11);
		newDef.menuText = "----------------------------\n"
				+ "   � ������ �����Ͻðڽ��ϱ�?\n"
				+ "1.name 2.phone number 3.group\n"
				+ "-----------------------------\n"
				+ "->";
		
		// 1 -> �̸��� ������ �� state 1(�˻��� ����ó�� ���� �۾����� �޴�)�Ǵ� state 211(�׷쿡�� ���õ� ����ó�� ���� �۾����� �޴�)�� �̵�
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("1");
		newRule.Execute = tokens ->
		{
			System.out.print("������ �̸��� �Է��ϼ���\n->");
			temp = sc.nextLine();
			phoneBook.change(name, temp, 1);
			name = temp;//�̸��� �ٲپ����Ƿ�
			if (!isGroupMenu)
				app.state = 1;
				else
					app.state = 211;
			};
		newDef.rules.add(newRule);
		
		// 2 -> ��ȭ��ȣ�� ������ �� state 1(�˻��� ����ó�� ���� �۾����� �޴�)�Ǵ� state 211(�׷쿡�� ���õ� ����ó�� ���� �۾����� �޴�)�� �̵�
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("2");
		newRule.Execute = tokens ->
		{
			System.out.print("������ ��ȭ��ȣ�� �Է��ϼ���\n->");
			temp = sc.nextLine();
			phoneBook.change(name, temp, 2);
			phoneNum = temp;//��ȭ��ȣ�� �ٲپ����Ƿ�
			if (!isGroupMenu)
				app.state = 1;
				else
					app.state = 211;
			};
		newDef.rules.add(newRule);
		
		// 3 -> �׷��� ������ �� state 1(�˻��� ����ó�� ���� �۾����� �޴�)�Ǵ� state 211(�׷쿡�� ���õ� ����ó�� ���� �۾����� �޴�)�� �̵�
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("3");
		newRule.Execute = tokens ->
		{
			System.out.print("������ �׷��� �Է��ϼ���\n->");
			temp = sc.nextLine();
			phoneBook.change(name, temp, 3);
			group = temp;//��ȭ��ȣ�� �ٲپ����Ƿ�
			if (!isGroupMenu)
				app.state = 1;
				else
					app.state = 211;
			};
		newDef.rules.add(newRule);
		
		// �� �������� ���� ��Ģ �߰�
		newDef.rules.add(Parser.CommonRules.rule_error);
		
		// ���� ���α׷��� ���� ���� state ���� �߰�
		app.stateDefinitions.add(newDef);
		
		/*state 21 : �׷캰 ����ó ���� �޴�*/
		newDef = new ApplicationStateDefinition(21);
		newDef.callback_Entering = (state, type) -> 
		{
			phoneBook.list();
		};
		newDef.callback_Repeating = (state, type) -> phoneBook.list();
		newDef.callback_Leaving=(state,type)->phoneBook.escapeGroupMode();
		newDef.menuText = "--------------------------------------\n"
				+ "���õ� �׷��� ����ó�� ���� ���ϴ� �۾��� �����ϼ���\n"
				+ "1.add 2.search 3.return to group menu\n"
				+ "4.quit\n"
				+ "---------------------------------------\n"
				+ "����->";
		
		// 1 -> ����ó �߰�
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("1");
		newRule.Execute = tokens ->
		{
			System.out.print("*�̸��� �Է��ϼ���->");
			name = sc.nextLine();
			System.out.print("*��ȭ��ȣ�� �Է��ϼ���->");
			phoneNum = sc.nextLine();
			phoneBook.add(name, phoneNum, "");//����ó �߰�(�׷��� ���� ������ �ʿ� ����)
			System.out.println(name + "�� ����ó�� �߰��Ǿ����ϴ�");
		};
		newDef.rules.add(newRule);
		
		// 2 -> �˻��� ����ó �̸��� �Է¹��� �� state 211(�׷쿡�� ���õ� ����ó�� ���� �۾����� �޴�)���� �̵�
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("2");
		newRule.Execute = tokens ->
		{
			System.out.println("�˻��� ����ó�� �̸��� �Է��ϼ���");
			name = sc.nextLine();
			phoneBook.search(name);
			app.state = 211;
		};
		newDef.rules.add(newRule);
		
		// 3 -> state 2(�׷� ���� �޴�)�� �̵�
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("3");
		newRule.Execute = tokens -> app.state = 2;
		newDef.rules.add(newRule);
		
		// 4 -> ���α׷� ����
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("4");
		newRule.Execute = tokens ->
		{
			System.out.println("***���α׷��� �����մϴ�***");
			System.exit(0);
		};
		newDef.rules.add(newRule);
		
		// �� �������� ���� ��Ģ �߰�
		newDef.rules.add(Parser.CommonRules.rule_error);
		
		// ���� ���α׷��� ���� ���� state ���� �߰�
		app.stateDefinitions.add(newDef);
		
		/*state 211: �׷쿡�� ���õ� ����ó�� ���� �۾����� �޴�*/
		newDef = new ApplicationStateDefinition(211);
		newDef.menuText = "-----------------------------------------\n"
				+ "���� �� �˻��� ��ȭ��ȣ�� ���� ������ �۾��� �����ϼ���\n"
				+ "1.change 2.delete 3.message 4.call\n"
				+ "5.return to group menu 6.quit\n"
				+ "-----------------------------------------\n"
				+ "->";
		
		// 1 -> state 11�� �̵�
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("1");
		newRule.Execute = tokens -> app.state = 11;
		newDef.rules.add(newRule);
		
		// 2 -> ���õ� ����ó�� ������ �� state 21(�׷캰 ����ó ���� �޴�)�� ���ư�
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("2");
		newRule.Execute = tokens ->
		{
			phoneBook.delete(name);
			System.out.println(name + "�� ����ó�� �����Ǿ����ϴ�");
			app.state = 21;
		};
		newDef.rules.add(newRule);
		
		// 3 -> ���õ� ����ó�� �޼��� �޼ҵ� ����
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("3");
		newRule.Execute = tokens -> phoneBook.message(name);
		newDef.rules.add(newRule);
		
		// 4 -> ���õ� ����ó�� ��ȭ �޼ҵ� ����
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("4");
		newRule.Execute = tokens -> phoneBook.call(name);
		newDef.rules.add(newRule);
		
		// 5 -> state 2(�׷� ���� �޴�)�� �̵�
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("5");
		newRule.Execute = tokens -> app.state = 2;
		newDef.rules.add(newRule);
		
		// 6 -> ���α׷� ����
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("6");
		newRule.Execute = tokens ->
		{
			System.out.println("***���α׷��� �����մϴ�***");
			System.exit(0);
		};
		newDef.rules.add(newRule);
		
		// �� �������� ���� ��Ģ �߰�
		newDef.rules.add(Parser.CommonRules.rule_error);
		
		// ���� ���α׷��� ���� ���� state ���� �߰�
		app.stateDefinitions.add(newDef);
		
		/*���α׷� ����*/
		app.Start();
	}
}