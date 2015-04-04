import java.util.Scanner;


public class PhoneBookMain
{
	
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		Group group=new Group();
		
		String select=null;
		String name=null;
		String phoneNum=null;
		while(select!="8" || select!="quit")
		{
			System.out.println("----------------------------");
			System.out.println("     원하는 작업을 선택하세요");
			System.out.println("1.list 2.add 3.search 4.quit");
			System.out.println("----------------------------");
			
			//작업선택 
			select=sc.nextLine();
			switch(select)
			{
			case "5":
			case "quit":
				break;
			case "1":
			case "list":
				group.listPhone();
				break;
			case "2":
			case "add":
				System.out.print("이름을 입력하세요:");
				name=sc.nextLine();
				System.out.print("전화번호를 입력하세요:");
				phoneNum=sc.nextLine();
				group.addPhone(name, phoneNum);
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
			default:
				System.out.println("올바른 값을 입력하세요");
				break;
			}
		}
	}
}