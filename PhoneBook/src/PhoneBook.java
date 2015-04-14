import java.util.ArrayList;

/**
 * 주소록 전체를 구현합니다. 
 * @author createrlee
 *
 */
public class PhoneBook
{
	private int GroupIndex=-1;//선택된 그룹의 위치. -1이 아닌 경우에는 선택된 그룹이 있는 것으로 간주하여 그 그룹에 대해서만 메소드 실행.
	private ArrayList<Group> contacts;
	private ArrayList<Person> entireList;//그룹 구분 없는 전체목록 표시를 위해
	
	public PhoneBook()
	{
		contacts = new ArrayList<Group>();
		contacts.add(new Group());// 이름이 없는 그룹을 하나 만든다. 이름이 없는 그룹은 언제나 첫번째
		/*기본 그룹들을 몇가지 정의한다. 나중에 더 추가할수도 있다.*/
		contacts.add(new Group("family"));
		contacts.add(new Group("friend"));
		contacts.add(new Group("company"));
	}
	
	/**전체 연락처 목록을 출력합니다*/
	public void list()
	{
		entireList=new ArrayList<Person>();
		for(Group iGroup:contacts)//각 그룹에 대해
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
		
		for(Person iPerson:entireList)
		{
			System.out.println(iPerson.name()+" "+iPerson.phoneNum());
		}
	}
	
	/**전체 그룹 목록을 출력합니다*/
	public void listGroup()
	{
		int i=0;
		for(Group iGroup:contacts)
		{
			if(i>0)//첫번째 이름없는 목록은 보여주지 않는다
				System.out.println(iGroup.groupName());
			i++;
		}
	}
	
	/**새로운 그룹을 추가합니다*/
	public void addGroup(String groupName)
	{
		contacts.add(new Group(groupName));
	}
	
	/**그룹의 이름을 바꿉니다*/
	public void changeGroupName(String groupName,String toChange)
	{
		for(Group iGroup:contacts)
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
		for(int i=0;i<contacts.size();i++)
		{
			if(contacts.get(i).groupName()==groupName)
			{
				contacts.remove(i);
				System.out.println("그룹 "+groupName+"이(가) 제거되었습니다");
				return;
			}
		}
		System.out.println("해당 그룹이 존재하지 않습니다!");
	}
	
	/**새로운 연락처를 추가합니다*/
	public void add(String name,String phoneNum,String group)
	{
		if(group=="")//아무 그룹을 입력하지 않은 경우
			contacts.get(0).addPhone(name, phoneNum);
		else
		{
			boolean isSuchGroup=false;
			for(Group iGroup:contacts)//그룹을 찾는다
			{
				System.out.println(iGroup.groupName());
				System.out.println(group);
				if(iGroup.groupName().equals(group))
				{
					iGroup.addPhone(name, phoneNum);
					isSuchGroup=true;
					break;
				}
			}
			if(!isSuchGroup)//입력한 그룹이 존재하지 않을 때
			{
				contacts.add(new Group(group));//그룹추가
				contacts.get(contacts.size()-1).addPhone(name, phoneNum);//추가한 그룹에 연락처 추가
			}
		}
	}
	
	/**연락처를 삭제합니다*/
	public void delete(String name)	
	{
		for(Group iGroup:contacts)
			iGroup.deletePhone(name);
	}
	
	/**연락처를 검색하여 연락처의 정보를 모두 표시합니다*/
	public void search(String name)
	{
		for(Group iGroup:contacts)
			iGroup.searchPhone(name);
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