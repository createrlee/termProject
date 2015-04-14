import java.util.ArrayList;

/**
 * 주소록 전체를 구현합니다. 
 * @author createrlee
 *
 */
public class PhoneBook
{
	private int GroupIndex=-1;//선택된 그룹의 위치. -1이 아닌 경우에는 선택된 그룹이 있는 것으로 간주하여 그 그룹에 대해서만 메소드 실행.
	private ArrayList<Group> phoneBook;
	private ArrayList<Person> entireList;//그룹 구분 없는 전체목록 표시를 위해
	
	public PhoneBook()
	{
		ArrayList<Group> phoneBook = new ArrayList<Group>();
		phoneBook.add(new Group());// 이름이 없는 그룹을 하나 만든다. 이름이 없는 그룹은 언제나 첫번째
		/*기본 그룹들을 몇가지 정의한다. 나중에 더 추가할수도 있다.*/
		phoneBook.add(new Group("family"));
		phoneBook.add(new Group("friend"));
		phoneBook.add(new Group("company"));
	}
	
	/**전체 연락처 목록을 출력합니다*/
	public void list()
	{
		entireList=new ArrayList<Person>();
		for(Group iGroup:phoneBook)//각 그룹에 대해
		{
			for(int i=0;i<iGroup.groupSize();i++)//그룹안의 각 사람에 대해
			{
				if(entireList.size()==0)//전체목록이 비어있으면 그냥 연락처 삽입
					entireList.add(iGroup.getContact(i));
				else//요소들을 정렬하면서 전체목록에 저장한다
				{
					int entireListSize=entireList.size();
					boolean isInserted=false;
					for(int iList=0;i<entireListSize;i++)
					{
						//하나씩 비교하여 맞는 위치에 저장
						if(Person.strCmp(iGroup.getContact(i).name(),entireList.get(iList).name()))
						{
							entireList.add(iList, iGroup.getContact(i));
							isInserted=true;
							break;
						}
						if(!isInserted)//중간에 들어가지 않은 경우 끝에 저장
							entireList.add(iGroup.getContact(i));
					}
				}
			}
		}
	}
	
	/**전체 그룹 목록을 출력합니다*/
	public void listGroup()
	{
		int i=0;
		for(Group iGroup:phoneBook)
		{
			System.out.println(i+"."+iGroup.groupName());
		}
	}
	
	/**새로운 그룹을 추가합니다*/
	public void addGroup(String groupName)
	{
		
	}
	
	/**그룹의 이름을 바꿉니다*/
	public void changeGroupName(String groupName,String toChange)
	{
		for(Group iGroup:phoneBook)
		{
			if(iGroup.groupName()==groupName)
			{
				System.out.println(groupName+"->"+toChange);
				iGroup.changeGroupName(toChange);
				return;
			}
		}
		System.out.println("해당 그룹이 존재하지 않습니다!");
	}
	
	/**그룹을 삭제합니다*/
	public void deleteGroup(String groupName)
	{
		
	}
	
	/**새로운 연락처를 추가합니다*/
	public void add(String name,String phoneNum,String group)
	{
		
		
	}
	
	/**연락처를 삭제합니다*/
	public void delete(String name)	
	{
		
	}
	
	/**연락처를 검색하여 연락처의 정보를 모두 표시합니다*/
	public void search(String name)
	{
		
	}
	
	/**연락처를 찾아 정보를 변경합니다
	 * @param name : 찾을 연락처
	 * @param toChange : 바꿀 이름 혹은 전화번호
	 * @param select : 1.이름 변경 2.전화번호 변경 3.그룹 변경
	 */
	public void change(String name,String toChange,int select)
	{
		
	}
	
	/**선택된 연락처의 메세지 대화창을 띄웁니다*/
	public void message(String name)
	{
		
	}
	
	/**선택된 연락처의 전화 창을 띄웁니다*/
	public void call(String name)
	{
		
	}
}