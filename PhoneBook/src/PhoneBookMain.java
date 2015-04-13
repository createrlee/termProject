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
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		PhoneBook phoneBook = new PhoneBook();
		
		/*console application�� �̿��ϱ� ���� ��� ������ ����*/
		ConsoleApplication app = new ConsoleApplication();
		ApplicationStateDefinition newDef;
		Rule newRule;
		
		/*state 0: ���θ޴�*/
		newDef = new ApplicationStateDefinition(0);
		newDef.menuText = "-----------���� �޴�-----------\n"
				+ "     ���ϴ� �۾��� �����ϼ���\n"
				+ "1.add 2.search 3.group 4.quit\n"
				+ "------------------------------\n"
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
		
		// 1 -> state 11�� �̵�
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
			System.out.println(name + "�� ����ó�� �����Ǿ����ϴ�");
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
		newRule.Execute = tokens -> app.state = 0;
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
		newDef.menuText = "-------------�׷� ����--------------\n"
				+ "���� �� ������ �۾��� �����ϼ���\n"
				+ "1.list group 2.add group\n"
				+ "3.change group name 4.delete group\n"
				+ "5.return to main menu 6. quit\n"
				+ "------------------------------------\n"
				+ "->";
		
		// 1 -> �׷� ����� ����ϰ� Ư�� �׷��� ������ �� state 21(�׷캰 ����ó ���� �޴�)�� �̵�
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("1");
		newRule.Execute = tokens ->
		{
			phoneBook.listGroup();
			System.out.print("*������ �׷��� �Է��ϼ���\n->");
			group=sc.nextLine();
			app.state=21;
		};
		newDef.rules.add(newRule);
		
		// 2 -> ���ο� �׷��� �߰�
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("2");
		newRule.Execute = tokens ->
		{
			System.out.print("*���Ӱ� �߰��Ϸ��� �׷��� �̸��� �Է��ϼ���\n->");
			group=sc.nextLine();
			phoneBook.addGroup(group);
		};
		newDef.rules.add(newRule);
		
		// 3 -> �׷� �̸� ����
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("3");
		newRule.Execute = tokens ->
		{
			System.out.println("***���α׷��� �����մϴ�***");
			System.exit(0);
		};
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
		
		// 5 -> ���α׷� ����
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("5");
		newRule.Execute = tokens ->
		{
			System.out.println("***���α׷��� �����մϴ�***");
			System.exit(0);
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
		
		// 1 -> �̸��� ������ �� state 1(�˻��� ����ó�� ���� �۾����� �޴�)�� �̵�
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("1");
		newRule.Execute = tokens ->
		{
			System.out.print("������ �̸��� �Է��ϼ���\n->");
			temp = sc.nextLine();
			phoneBook.change(name, temp, 1);
			name = temp;//�̸��� �ٲپ����Ƿ�
			app.state = 1;
		};
		newDef.rules.add(newRule);
		
		// 2 -> ��ȭ��ȣ�� ������ �� state 1(�˻��� ����ó�� ���� �۾����� �޴�)�� �̵�
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("2");
		newRule.Execute = tokens ->
		{
			System.out.print("������ ��ȭ��ȣ�� �Է��ϼ���\n->");
			temp = sc.nextLine();
			phoneBook.change(name, temp, 2);
			phoneNum = temp;//��ȭ��ȣ�� �ٲپ����Ƿ�
			app.state = 1;
		};
		newDef.rules.add(newRule);
		
		// �� �������� ���� ��Ģ �߰�
		newDef.rules.add(Parser.CommonRules.rule_error);
		
		// ���� ���α׷��� ���� ���� state ���� �߰�
		app.stateDefinitions.add(newDef);
		
		/*���α׷� ����*/
		app.Start();
		
		/*

		while (select != "8" || select != "quit")
		{
			for (Group iGroup : phoneBook)
				iGroup.listPhone();
			System.out.println("-----------------------------");
			System.out.println("     ���ϴ� �۾��� �����ϼ���");
			System.out.println("1.add 2.search 3.group 4.quit");
			System.out.println("-----------------------------");
			
			// �۾�����
			select = sc.nextLine();
			switch (select)
			{
			case "5":
			case "quit":
				break;
			
			case "1":
			case "add":
				System.out.print("�̸��� �Է��ϼ���:");
				name = sc.nextLine();
				System.out.print("��ȭ��ȣ�� �Է��ϼ���:");
				phoneNum = sc.nextLine();
				System.out.println("������ �׷��� �Է��ϼ���. �Է��� �׷��� ��Ͽ� ���� ��� ���� �����մϴ�.");
				
				for (int indexOfGroup = 1; indexOfGroup < phoneBook.size(); indexOfGroup++)
					// ��� �׷���� ���
					System.out.print((indexOfGroup) + "." + phoneBook.get(indexOfGroup).groupName + " ");
				System.out.println(phoneBook.size() + ".no group");
				group = sc.nextLine();
				
				if (phoneBook.size() == Integer.parseInt(group) || "no group" == group)// �׷��� ���°��� ������ ���
					phoneBook.get(0).addPhone(name, phoneNum);
				else
				// �׷��� ������ ���
				{
					boolean noSuchGroup = true;
					
					for (Group igroup : phoneBook)// �׷� �̸����� �Է��� ���
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
						int groupNum = Integer.parseInt(group);// �׷� ��ȣ�� �Է��� ���
						if (groupNum >= 1 && groupNum < phoneBook.size())
						{
							phoneBook.get(groupNum).addPhone(name, phoneNum);
							noSuchGroup = false;
						}
					}
					
					if (noSuchGroup)// ��Ͽ� �Է��� �׷��� ���� ��� ���ο� �׷� ����
					{
						phoneBook.add(new Group(group));
						phoneBook.get(phoneBook.size() - 1).addPhone(name, phoneNum);
					}
				}
				break;
			
			case "2":
			case "search":
				System.out.print("�˻��� �̸� Ȥ�� ��ȭ��ȣ�� �Է��ϼ���:");
				name = sc.nextLine();
				
				int Gindex = 0;// � �׷쿡 ����ִ°�
				int index = 0;// �׷� �ȿ��� ��� ����ִ°�
				for (Group iGroup : phoneBook)
				{
					if ((index = iGroup.searchPhone(name)) >= 0)
						break;
					Gindex++;
				}
				
				System.out.println("-----------------------------------------");
				System.out.println("���� �� �˻��� ��ȭ��ȣ�� ���� ������ �۾��� �����ϼ���");
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
					System.out.println("����� �����Ͻðڽ��ϱ�? 1.name 2.phone number");
					select = sc.nextLine();
					
					switch (select)
					{
					case "1":
					case "name":
						System.out.print("������ �̸��� �Է��ϼ���: ");
						name = sc.nextLine();
						phoneBook.get(Gindex).changePhoneName(index, name);
						break;
					case "2":
					case "phone number":
						System.out.print("������ ��ȭ��ȣ�� �Է��ϼ���: ");
						phoneNum = sc.nextLine();
						phoneBook.get(Gindex).changePhoneNum(index, phoneNum);
						break;
					}
					
					break;
				case "2":
				case "delete":
					System.out.println("������ ����ó�� �̸��� �Է��ϼ���: ");
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
				System.out.println("�ùٸ� ���� �Է��ϼ���");
				break;
			}
		}*/
	}
}