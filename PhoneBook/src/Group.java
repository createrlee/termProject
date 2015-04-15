import java.util.ArrayList;

/**
 * 하나의 group을 구현하는 클래스입니다. 여러명의 Person들이 저장되게 됩니다.
 * @author createrlee
 */
class Group
{
	private String groupName;
	private ArrayList<Person> people;

	public Group()
	{
		this.groupName="";
		people = new ArrayList<Person>();
	}
	
	public Group(String groupName)
	{
		this.groupName=groupName;
		people = new ArrayList<Person>();
	}
	
	/**그룹 이름을 반환합니다*/
	public String groupName()
	{
		return this.groupName;
	}
	
	/**그룹 이름을 바꿉니다*/
	public void changeGroupName(String toChange)
	{
		this.groupName=toChange;
	}

	/**그룹의 전체 주소록을 출력합니다*/
	public void listPhone()
	{
		System.out.println("Group : "+groupName);
		for(Person iPer:people)
			System.out.println(iPer.name()+" "+iPer.phoneNum());
	}

	/**이름 순으로 sorting 하며 새로운 연락처를 저장합니다*/
	public void addPhone(String name, String phoneNum)
	{
		if (people.size() == 0)
			people.add(new Person(name, phoneNum));
		else
		{
			int peopleSize=people.size();
			for(int i=0;i<peopleSize;i++)
			{
				if(Person.strCmp(name, people.get(i).name()))
				{
					people.add(i, new Person(name,phoneNum));
					break;
				}
				if(i==(peopleSize-1))
					people.add(new Person(name,phoneNum));
			}
		}
	}

	/**주소록에서 이름이 name인 사람을 삭제합니다*/
	public void deletePhone(String name)
	{
		for(int i=0;i<people.size();i++)
			if(people.get(i).name()==name)
			{
				people.remove(i);
				break;
			}
	}
	
	/**
	 * Group 내의 people 객체의 크기를 반환합니다
	 */
	public int groupSize()
	{
		return people.size();
	}
	
	/**
	 * 주소록에서 index번째의 Person객체를 반환합니다
	 */
	public Person getContact(int index)
	{
		return people.get(index);
	}

	/**
	 * 연락처의 이름을 변경합니다
	 * @param index : 변경할 연락처의 index
	 * @param toChange : 바꿀 이름
	 */
	public void changeName(int index,String toChange)
	{
		people.get(index).changeName(toChange);
	}
	
	/**
	 * 연락처의 전화번호를 변경합니다
	 * @param index :변경할 연락처의 index
	 * @param toChange : 바꿀 전화번호
	 */
	public void changePhoneNum(int index,String toChange)
	{
		people.get(index).changeNum(toChange);
	}

	/**
	 * 그룹 내에서 원하는 연락처를 찾아 index를 반환합니다. 없을 경우에 -1을 반환합니다.
	 * @param contact : 이름 혹은 전화번호
	 */
	public int searchPhone(String contact)
	{
		for(int i=0;i<people.size();i++)
		{
			if(people.get(i).name()==contact)
				return i;
			if(people.get(i).phoneNum()==contact)
				return i;
		}
		return -1;//찾지 못했을 때
	}

}