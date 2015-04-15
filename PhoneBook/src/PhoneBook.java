import java.util.ArrayList;

/**
 * 주소록 전체를 구현합니다. 
 * @author createrlee
 *
 */
public class PhoneBook
{
	private int groupIndex = -1;//선택된 그룹의 위치. -1이 아닌 경우에는 선택된 그룹이 있는 것으로 간주하여 그 그룹에 대해서만 메소드 실행.
	private ArrayList<Group> contacts;
	private ArrayList<Person> entireList;//그룹 구분 없는 전체목록 표시를 위해
	
	public PhoneBook()
	{
		contacts = new ArrayList<Group>();
		entireList = new ArrayList<Person>();
		
		contacts.add(new Group());// 이름이 없는 그룹을 하나 만든다. 이름이 없는 그룹은 언제나 첫번째
		/*기본 그룹들을 몇가지 정의한다. 나중에 더 추가할수도 있다.*/
		contacts.add(new Group("family"));
		contacts.add(new Group("friend"));
		contacts.add(new Group("company"));
	}
	
	/**
	 * 그룹 관리를 용이하게 하기 위한 메소드입니다.
	 * 이 메소드를 실행하고 나면, 특정 그룹에 대해서만 다른 메소드들이 실행되게 됩니다.
	 * 그룹 관리가 끝나면 escapeGroupMode()를 호출해 주는것을 잊지 마세요.<br>
	 * 그룹이 존재하면 true, 존재하지 않으면 false를 반환합니다.
	 * @param groupName : 작업을 수행할 특정 그룹
	 */
	public boolean getIntoGroupMode(String groupName)
	{
		int i = -1;
		for (Group iGroup : contacts)
		{
			i++;
			if (iGroup.groupName().equals(groupName))
			{
				groupIndex = i;
				return true;
			}
		}
		System.out.println("그룹이 존재하지 않습니다!");
		return false;
	}
	
	/**
	 * 그룹 모드를 끝냅니다.
	 */
	public void escapeGroupMode()
	{
		this.groupIndex = -1;
	}
	
	/**전체 연락처 목록을 출력합니다*/
	public void list()
	{
		/*그룹모드가 아닐 때*/
		if (groupIndex == -1)
		{
			entireList.clear();
			for (Group iGroup : contacts)//각 그룹에 대해
			{
				for (int i = 0; i < iGroup.groupSize(); i++)//그룹안의 각 사람에 대해
				{
					if (entireList.isEmpty())//전체목록이 비어있으면 그냥 연락처 삽입
						entireList.add(iGroup.getContact(i));
					else
					//요소들을 정렬하면서 전체목록에 저장한다
					{
						int entireListSize = entireList.size();
						boolean isInserted = false;
						
						for (int iList = 0; iList < entireListSize; iList++)
						{
							//하나씩 비교하여 맞는 위치에 저장
							if (Person.strCmp(iGroup.getContact(i).name(), entireList.get(iList).name()))
							{
								entireList.add(iList, iGroup.getContact(i));
								isInserted = true;
								
								break;
								
							}
						}
						if (!isInserted)//중간에 들어가지 않은 경우 끝에 저장
							entireList.add(iGroup.getContact(i));
					}
				}
			}
			//리스트 출력
			for (Person iPerson : entireList)
			{
				System.out.println(iPerson.name() + " " + iPerson.phoneNum());
			}
			
		}
		/*그룹모드*/
		else
		{
			contacts.get(groupIndex).listPhone();
		}
	}
	
	/**전체 그룹 목록을 출력합니다*/
	public void listGroup()
	{
		System.out.println("<현재 그룹 목록>");
		
		int i = 0;
		for (Group iGroup : contacts)
		{
			if (i > 0)//첫번째 이름없는 목록은 보여주지 않는다
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
	public void changeGroupName(String groupName, String toChange)
	{
		for (Group iGroup : contacts)
		{
			if (iGroup.groupName().equals(groupName))
			{
				System.out.println(groupName + "->" + toChange);
				iGroup.changeGroupName(toChange);
				return;
			}
		}
		System.out.println("해당 그룹이 존재하지 않습니다!");
	}
	
	/**그룹을 삭제합니다*/
	public void deleteGroup(String groupName)
	{
		if(groupName=="")//이름없는 기본그룹은 삭제할 수 없도록 한다
		{
			System.out.println("해당 그룹이 존재하지 않습니다!");
			return;
		}
		for (int i = 0; i < contacts.size(); i++)
		{
			if (contacts.get(i).groupName().equals(groupName))
			{
				contacts.remove(i);
				System.out.println("그룹 " + groupName + "이(가) 제거되었습니다");
				return;
			}
		}
		System.out.println("해당 그룹이 존재하지 않습니다!");
	}
	
	/**새로운 연락처를 추가합니다*/
	public void add(String name, String phoneNum, String group)
	{
		/*그룹모드가 아닐 때*/
		if (groupIndex == -1)
		{
			if (group == "")//아무 그룹을 입력하지 않은 경우
				contacts.get(0).addPhone(name, phoneNum);
			else
			{
				boolean isSuchGroup = false;
				for (Group iGroup : contacts)//그룹을 찾는다
				{
					if (iGroup.groupName().equals(group))
					{
						iGroup.addPhone(name, phoneNum);
						isSuchGroup = true;
						break;
					}
				}
				if (!isSuchGroup)//입력한 그룹이 존재하지 않을 때
				{
					contacts.add(new Group(group));//그룹추가
					contacts.get(contacts.size() - 1).addPhone(name, phoneNum);//추가한 그룹에 연락처 추가
				}
			}
		}
		/*그룹모드*/
		else
		{
			contacts.get(groupIndex).addPhone(name, phoneNum);
		}
	}
	
	/**연락처를 삭제합니다*/
	public void delete(String name)
	{
		/*그룹모드가 아닐때*/
		if (groupIndex == -1)
		{
			for (Group iGroup : contacts)
				iGroup.deletePhone(name);
		}
		else
		{
			contacts.get(groupIndex).deletePhone(name);
		}
	}
	
	/**연락처를 검색하여 연락처의 정보를 모두 표시합니다*/
	public void search(String name)
	{
		Person contact;//검색된 연락처 임시저장
		int searched;//검색된 연락처의 index
		for (Group iGroup : contacts)
		{
			searched = iGroup.searchPhone(name);//검색을 수행한다
			if (searched != -1)//연락처가 발견되면
			{
				contact = iGroup.getContact(searched);
				System.out.println(contact.name() + " " + contact.phoneNum());
				break;
			}
		}
	}
	
	/**연락처를 찾아 정보를 변경합니다
	 * @param name : 찾을 연락처
	 * @param toChange : 바꿀 이름 혹은 전화번호
	 * @param select : 1.이름 변경 2.전화번호 변경 3.그룹 변경
	 */
	public void change(String name, String toChange, int select)
	{
		int gLoc = -1;//검색된 연락처가 있는 그룹의 index
		int searched = -1;//검색된 연락처의 index
		for (Group iGroup : contacts)
		{
			searched = iGroup.searchPhone(name);//검색을 수행한다
			gLoc++;
			if (searched != -1)//연락처가 발견되면
				break;
		}
		if (searched == -1)//발견된 연락처가 없는 경우
		{
			return;
		}
		System.out.println("group: "+gLoc+" name: "+searched);
		
		/*select는 1,2,3이외의 값 들어오지 않으므로 예외처리 필요없다*/
		switch (select)
		{
		case 1:
			contacts.get(gLoc).changeName(searched, toChange);
			break;
		case 2:
			contacts.get(gLoc).changePhoneNum(searched, toChange);
			break;
		case 3:
			/*
			 * 1.검색된 연락처를 임시로 저장
			 * 2.검색된 연락처를 삭제
			 * 3.새로운 그룹에 임시 저장한 연락처를 추가
			 */
			Person temp = contacts.get(gLoc).getContact(searched);
			contacts.get(gLoc).deletePhone(name);
			this.add(temp.name(), temp.phoneNum(), toChange);
			break;
		}
	}
	
	/**선택된 연락처의 메세지 대화창을 띄웁니다*/
	public void message(String name)
	{
		System.out.println("<-----message with "+name+"----->");
		for(Group iGroup:contacts)
		{
			iGroup.message(name);
		}
	}
	
	/**선택된 연락처의 전화 창을 띄웁니다*/
	public void call(String name)
	{
		System.out.println("<-----phonecall with "+name+"----->");
		for(Group iGroup:contacts)
		{
			iGroup.call(name);
		}
	}
}