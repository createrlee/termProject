import java.util.ArrayList;

/**
 * phonebook�� �����ϴ� Ŭ�����Դϴ�. �������� Person���� ����ǰ� �˴ϴ�.
 * @author createrlee
 *
 */
class PhoneBook
{
	private ArrayList<Person> people;

	private void saveToFile()
	{
		 //XMLSerializer<Person>	
	}
	
	/**���ڿ� 2���� sorting �켱���� ��, a�� ũ�ų� ������ false, b�� ũ�� true*/
	private boolean strCmp(String a,String b)
	{
		int cmpSize=(a.length()<b.length() ? a.length():b.length());//���� ���̰� ª�� ���� �������� ��
		
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

	/**��ü �ּҷ��� ����մϴ�*/
	public void listPhone()
	{
		for(Person iPer:people)
			System.out.println(iPer.name()+" "+iPer.phoneNum());
	}

	/**�̸� ������ sorting �ϸ� ���ο� ����ó�� �����մϴ�*/
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
				{
					people.add(new Person(name,phoneNum));
					System.out.println(i);
					System.out.println("here");
				}
			}
		}
	}

	/**�ּҷϿ��� �̸��� name�� ����� �����մϴ�*/
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