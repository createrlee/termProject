import java.util.ArrayList;

/**
 * 하나의 group을 구현하는 클래스입니다. 여러명의 Person들이 저장되게 됩니다.
 * @author createrlee
 */
class Group
{
	public String groupName;
	private ArrayList<Person> people;

	private void saveToFile()
	{
		 //XMLSerializer<Person>	
	}
	
	/**문자열 2개의 sorting 우선순위 비교, a가 크거나 같으면 false, b가 크면 true*/
	private boolean strCmp(String a,String b)
	{
		int cmpSize=(a.length()<b.length() ? a.length():b.length());//둘중 길이가 짧은 것을 기준으로 비교
		
		for(int i=0;i<cmpSize;i++)
			if(a.charAt(i)>b.charAt(i))
				return false;
			else if(a.charAt(i)<b.charAt(i))
				return true;
		
		return false;
	}

	public Group()
	{
		this.groupName=null;
		people = new ArrayList<Person>();
	}
	
	public Group(String groupName)
	{
		this.groupName=groupName;
		people = new ArrayList<Person>();
	}

	/**전체 주소록을 출력합니다*/
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
				if(this.strCmp(name, people.get(i).name()))
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

	/**주소록에서 index의 연락처의 혹은 전화번호를 변경합니다*/
	public void changePhone(int index,String contact)
	{
	}

	/**그룹 내에서 원하는 연락처를 찾아 index를 반환합니다. 없을 경우에 -1을 반환합니다.*/
	public int searchPhone(String contact)
	{
		
	}

}