import java.util.ArrayList;

/**
 * phonebook을 구현하는 클래스입니다. 여러명의 Person들이 저장되게 됩니다.
 * @author createrlee
 *
 */
class PhoneBook
{
	private ArrayList<Person> people;

	private void saveToFile()
	{

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

	public PhoneBook()
	{
		people = new ArrayList<Person>();
	}

	/**전체 주소록을 출력합니다*/
	public void listPhone()
	{
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
			for(int i=0;i<people.size();i++)
			{
				if(this.strCmp(name, people.get(i).name()))
				{
					people.add(i, new Person(name,phoneNum));
					break;
				}
				if(i==people.size()-1)
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

	public void changePhone()
	{

	}

	public void searchPhone()
	{

	}

}