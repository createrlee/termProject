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
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		PhoneBook phoneBook=new PhoneBook();
		
		/*console application�� �̿��ϱ� ���� ��� ������ ����*/
		ConsoleApplication app = new ConsoleApplication();
		ApplicationStateDefinition newDef;
		Rule newRule;
		
		/*state 0: ���θ޴�*/
		newDef = new ApplicationStateDefinition(0);
		newDef.menuText = "-----------------------------\n"
				+ "     ���ϴ� �۾��� �����ϼ���\n"
				+ "1.add 2.search 3.group 4.quit\n"
				+ "-----------------------------\n"
				+ "����->";
		
		// 1 -> state 1: ����ó �߰�
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("1");
		newRule.Execute = tokens -> 
		{
			System.out.print("�̸��� �Է��ϼ���\n->");
			name = sc.nextLine();
			System.out.print("��ȭ��ȣ�� �Է��ϼ���\n->");
			phoneNum = sc.nextLine();
			System.out.println("������ �׷��� �Է��ϼ���. �Է��� �׷��� ��Ͽ� ���� ��� ���� �����մϴ�.");
			System.out.println("�ƹ� �׷��� �Է����� ���� ��� �׷� ���� ����˴ϴ�.");
			phoneBook.listGroup();
			System.out.print("->");
			group=sc.nextLine();
			phoneBook.add(name, phoneNum, group);//����ó �߰�
		};
		newDef.rules.add(newRule);
		
		// 2 -> ����ó �˻��� ���� �� state 2�� �̵�
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("2");
		newRule.Execute = tokens -> 
		{
			
			app.state = 2;
		};
		newDef.rules.add(newRule);
		
		// 3 -> state 3�� �̵�
		newRule = new Rule();
		newRule.Match = input -> input.startsWith("3");
		newRule.Execute = tokens -> app.state = 3;
		newDef.rules.add(newRule);
		
		// �� �������� ���� ��Ģ �߰�
		newDef.rules.add(Parser.CommonRules.rule_error);
		
		// ���� ���α׷��� ���� ���� state ���� �߰�
		app.stateDefinitions.add(newDef);
		
		
		
		
		
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