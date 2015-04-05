import java.util.ArrayList;
import java.util.Scanner;


public class PhoneBookMain
{
	
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		ArrayList<Group> phoneBook=new ArrayList<Group>();
		phoneBook.add(new Group());//이름이 없는 그룹을 하나 만든다. 이름이 없는 그룹은 언제나 첫번째
		
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
			case "add":
				System.out.print("이름을 입력하세요:");
				name=sc.nextLine();
				System.out.print("전화번호를 입력하세요:");
				phoneNum=sc.nextLine();
				System.out.println("저장할 그룹을 입력하세요. 입력한 그룹이 목록에 없는 경우 새로 생성합니다.");
				
				int indexOfGroup;//아래 for문 수행 후 현재 그룹의 총 수가 된다
				for(indexOfGroup=1;indexOfGroup<=phoneBook.size();indexOfGroup++)//모든 그룹명을 출력
					System.out.print(indexOfGroup+"."+phoneBook.get(indexOfGroup).groupName+" ");
				System.out.println(indexOfGroup+".no group");
				group=sc.nextLine();
				
				if(String.valueOf(indexOfGroup)==group || "no group"==group)//그룹이 없는것을 선택한 경우
					phoneBook.get(0).addPhone(name, phoneNum);
				else//그룹을 지정한 경우
				{
					boolean noSuchGroup=true;
					
					int groupNum=Integer.parseInt(group);//그룹 번호로 입력한 경우
					if(groupNum>=1 && groupNum<indexOfGroup)
					{
						phoneBook.get(groupNum).addPhone(name, phoneNum);
						noSuchGroup=false;
					}
					
					for(Group igroup:phoneBook)//그룹 이름으로 입력한 경우
					{
						if(igroup.groupName==group)
						{
							igroup.addPhone(name, phoneNum);
							noSuchGroup=false;
							break;
						}
					}
					
					if(noSuchGroup)//목록에 입력한 그룹이 없는 경우 새로운 그룹 생성
					{
						phoneBook.add(new Group(group));
						phoneBook.get(phoneBook.size()-1).addPhone(name, phoneNum);
					}
				}
				break;
				
			case "2":
			case "search":
				 System.out.print("검색할 이름 혹은 전화번호를 입력하세요:");
				 name=sc.nextLine();
				 
				 System.out.println("-----------------------------------------");
				 System.out.println("다음 중 검색된 전화번호에 대해 수행할 작업을 선택하세요");
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
				
			case "3":
			case "group":
				break;
			default:
				System.out.println("올바른 값을 입력하세요");
				break;
			}
		}
	}
}