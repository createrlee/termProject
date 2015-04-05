import java.util.ArrayList;
import java.util.Scanner;


public class PhoneBookMain
{
	
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		ArrayList<Group> phoneBook=new ArrayList<Group>();
		phoneBook.add(new Group());//�̸��� ���� �׷��� �ϳ� �����
		
		String select=null;
		String name=null;
		String phoneNum=null;
		String group=null;
		while(select!="8" || select!="quit")
		{
			System.out.println("------------------------------------");
			System.out.println("         ���ϴ� �۾��� �����ϼ���");
			System.out.println("1.list 2.add 3.search 4.group 5.quit");
			System.out.println("------------------------------------");
			
			//�۾����� 
			select=sc.nextLine();
			switch(select)
			{
			case "5":
			case "quit":
				break;
			case "1":
			case "list":
				for(Group igroup:phoneBook)//��ü ����Ʈ�� ���
					igroup.listPhone();
				break;
			case "2":
			case "add":
				System.out.print("�̸��� �Է��ϼ���:");
				name=sc.nextLine();
				System.out.print("��ȭ��ȣ�� �Է��ϼ���:");
				phoneNum=sc.nextLine();
				System.out.println("������ �׷��� �����ϼ���");
				int i;
				for(i=1;i<=phoneBook.size();i++)//��� �׷���� ���
					System.out.print(i+"."+phoneBook.get(i).groupName+" ");
				System.out.println(i+".no group");
				group=sc.nextLine();
				
				String temp;
				temp.valueOf(i);//�׷��� ���°��� ������ ���
				if(temp==group || "no group"==group)
					phoneBook.get(0).addPhone(name, phoneNum);
				else
				{
					boolean noSuchGroup=true;
					for(Group igroup:phoneBook)
					{
						if(igroup.groupName==group)
						{
							igroup.addPhone(name, phoneNum);
							noSuchGroup=false;
							break;
						}
					}
					if(noSuchGroup)
					{
						phoneBook.add(new Group(group));
					}
				}
				break;
			case "3":
			case "search":
				 System.out.print("�̸��� �Է��ϼ���:");
				 name=sc.nextLine();
				 group.deletePhone(name);
				 
				 //�۾����� 
				 select=sc.nextLine();
				 System.out.println("-----------------------------------------");
				 System.out.println("���� �� �˻��� ��ȭ��ȣ�� ���� ������ �۾��� �����ϼ���");
				 System.out.println("1.change 2.delete 3.message 4.call 5.quit");
				 System.out.println("-----------------------------------------");
				 switch(select)
				 {
				 case "5":
				 case "quit":
					 break;
				 case "1":
				 case "change":
					 name=sc.nextLine();//name������ �̸� Ȥ�� ��ȭ��ȣ�� ��� �Է� ����
					 group.changePhone(name);
					 break;
				 case "2":
				 case "delete":
					 break;
				 case "3":
				 case "message":
					 break;
				 case "4":
				 case "call":
					 break;
				 }
				break;
			case "4":
			case "group":
				break;
			default:
				System.out.println("�ùٸ� ���� �Է��ϼ���");
				break;
			}
		}
	}
}