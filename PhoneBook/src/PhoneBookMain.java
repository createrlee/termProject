import java.util.Scanner;


public class PhoneBookMain
{
	
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		PhoneBook phoneBook=new PhoneBook();
		
		String select=null;
		String name=null;
		String phoneNum=null;
		while(select!="8" || select!="quit")
		{
			System.out.println("���ϴ� �۾��� �����ϼ���");
			System.out.println("---------------------------");
			System.out.println("1.list 2.add 3.delete 4.change");
			System.out.println("5.search 6.message 7.call 8.quit");
			System.out.println("---------------------------");
			
			select=sc.nextLine();
			switch(select)
			{
			case "1":
			case "list":
				phoneBook.listPhone();
				break;
			case "2":
			case "add":
				System.out.print("�̸��� �Է��ϼ���:");
				name=sc.nextLine();
				System.out.print("��ȭ��ȣ�� �Է��ϼ���:");
				phoneNum=sc.nextLine();
				phoneBook.addPhone(name, phoneNum);
				break;
			case "3":
			case "delete":
				 System.out.print("�̸��� �Է��ϼ���:");
				 name=sc.nextLine();
				 phoneBook.deletePhone(name);
				break;
			case "4":
			case "change":
				break;
			case "5":
			case "search":
				break;
			case "6":
			case "message":
				break;
			case "7":
			case "call":
				break;
			case "8":
			case "quit":
				break;
			default:
				System.out.println("�ùٸ� ���� �Է��ϼ���");
				break;
			}
		}
	}
}