import java.util.ArrayList;
import java.util.Scanner;


public class PhoneBookMain
{
	
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		ArrayList<Group> phoneBook=new ArrayList<Group>();
		phoneBook.add(new Group());//이름이 없는 그룹을 하나 만든다
		
		String select=null;
		String name=null;
		String phoneNum=null;
		String group=null;
		while(select!="8" || select!="quit")
		{
			System.out.println("------------------------------------");
			System.out.println("         원하는 작업을 선택하세요");
			System.out.println("1.list 2.add 3.search 4.group 5.quit");
			System.out.println("------------------------------------");
			
			//작업선택 
			select=sc.nextLine();
			switch(select)
			{
			case "5":
			case "quit":
				break;
			case "1":
			case "list":
				for(Group igroup:phoneBook)//전체 리스트를 출력
					igroup.listPhone();
				break;
			case "2":
			case "add":
				System.out.print("이름을 입력하세요:");
				name=sc.nextLine();
				System.out.print("전화번호를 입력하세요:");
				phoneNum=sc.nextLine();
				System.out.println("저장할 그룹을 선택하세요");
				int i;
				for(i=1;i<=phoneBook.size();i++)//모든 그룹명을 출력
					System.out.print(i+"."+phoneBook.get(i).groupName+" ");
				System.out.println(i+".no group");
				group=sc.nextLine();
				
				String temp;
				temp.valueOf(i);//그룹이 없는것을 선택한 경우
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
				 System.out.print("이름을 입력하세요:");
				 name=sc.nextLine();
				 group.deletePhone(name);
				 
				 //작업선택 
				 select=sc.nextLine();
				 System.out.println("-----------------------------------------");
				 System.out.println("다음 중 검색된 전화번호에 대해 수행할 작업을 선택하세요");
				 System.out.println("1.change 2.delete 3.message 4.call 5.quit");
				 System.out.println("-----------------------------------------");
				 switch(select)
				 {
				 case "5":
				 case "quit":
					 break;
				 case "1":
				 case "change":
					 name=sc.nextLine();//name이지만 이름 혹은 전화번호를 모두 입력 가능
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
				System.out.println("올바른 값을 입력하세요");
				break;
			}
		}
	}
}