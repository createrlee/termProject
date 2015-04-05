import java.util.ArrayList;
import java.util.Scanner;


public class PhoneBookMain
{
	
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		ArrayList<Group> phoneBook=new ArrayList<Group>();
		phoneBook.add(new Group());//�̸��� ���� �׷��� �ϳ� �����. �̸��� ���� �׷��� ������ ù��°
		
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
			case "add":
				System.out.print("�̸��� �Է��ϼ���:");
				name=sc.nextLine();
				System.out.print("��ȭ��ȣ�� �Է��ϼ���:");
				phoneNum=sc.nextLine();
				System.out.println("������ �׷��� �Է��ϼ���. �Է��� �׷��� ��Ͽ� ���� ��� ���� �����մϴ�.");
				
				int indexOfGroup;//�Ʒ� for�� ���� �� ���� �׷��� �� ���� �ȴ�
				for(indexOfGroup=1;indexOfGroup<=phoneBook.size();indexOfGroup++)//��� �׷���� ���
					System.out.print(indexOfGroup+"."+phoneBook.get(indexOfGroup).groupName+" ");
				System.out.println(indexOfGroup+".no group");
				group=sc.nextLine();
				
				if(String.valueOf(indexOfGroup)==group || "no group"==group)//�׷��� ���°��� ������ ���
					phoneBook.get(0).addPhone(name, phoneNum);
				else//�׷��� ������ ���
				{
					boolean noSuchGroup=true;
					
					int groupNum=Integer.parseInt(group);//�׷� ��ȣ�� �Է��� ���
					if(groupNum>=1 && groupNum<indexOfGroup)
					{
						phoneBook.get(groupNum).addPhone(name, phoneNum);
						noSuchGroup=false;
					}
					
					for(Group igroup:phoneBook)//�׷� �̸����� �Է��� ���
					{
						if(igroup.groupName==group)
						{
							igroup.addPhone(name, phoneNum);
							noSuchGroup=false;
							break;
						}
					}
					
					if(noSuchGroup)//��Ͽ� �Է��� �׷��� ���� ��� ���ο� �׷� ����
					{
						phoneBook.add(new Group(group));
						phoneBook.get(phoneBook.size()-1).addPhone(name, phoneNum);
					}
				}
				break;
				
			case "2":
			case "search":
				 System.out.print("�˻��� �̸� Ȥ�� ��ȭ��ȣ�� �Է��ϼ���:");
				 name=sc.nextLine();
				 
				 System.out.println("-----------------------------------------");
				 System.out.println("���� �� �˻��� ��ȭ��ȣ�� ���� ������ �۾��� �����ϼ���");
				 System.out.println("1.change 2.delete 3.message 4.call 5.quit");
				 System.out.println("-----------------------------------------");
				 select=sc.nextLine();
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
				
			case "3":
			case "group":
				break;
			default:
				System.out.println("�ùٸ� ���� �Է��ϼ���");
				break;
			}
		}
	}
}