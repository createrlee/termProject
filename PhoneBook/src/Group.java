import java.util.ArrayList;

/**
 * �ϳ��� group�� �����ϴ� Ŭ�����Դϴ�. �������� Person���� ����ǰ� �˴ϴ�.
 * @author createrlee
 */
class Group
{
	private String groupName;
	private ArrayList<Person> people;

	public Group()
	{
		this.groupName="no group";
		people = new ArrayList<Person>();
	}
	
	public Group(String groupName)
	{
		this.groupName=groupName;
		people = new ArrayList<Person>();
	}
	
	/**�׷� �̸��� ��ȯ�մϴ�*/
	public String groupName()
	{
		return this.groupName;
	}
	
	/**�׷� �̸��� �ٲߴϴ�*/
	public void changeGroupName(String toChange)
	{
		this.groupName=toChange;
	}

	/**��ü �ּҷ��� ����մϴ�*/
	public void listPhone()
	{
		System.out.println("Group : "+groupName);
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
	
	/**
	 * Group ���� people ��ü�� ũ�⸦ ��ȯ�մϴ�
	 */
	public int groupSize()
	{
		return people.size();
	}
	
	/**
	 * �ּҷϿ��� index��°�� Person��ü�� ��ȯ�մϴ�
	 */
	public Person getContact(int index)
	{
		return people.get(index);
	}

	/**�ּҷϿ��� index��° ����� �̸��� �����մϴ�
	 * @param index :����� ��ġ
	 **/
	public void changePhoneName(int index,String name)
	{
		people.get(index).changeName(name);
	}
	
	/**�ּҷϿ��� index��° ����� ��ȭ��ȣ�� �����մϴ�
	 * @param index :����� ��ġ
	 **/
	public void changePhoneNum(int index,String phoneNum)
	{
		people.get(index).changeNum(phoneNum);
	}

	/**�׷� ������ ���ϴ� ����ó�� ã�� index�� ��ȯ�մϴ�. ���� ��쿡 -1�� ��ȯ�մϴ�.
	 * @param contact : �̸� Ȥ�� ��ȭ��ȣ
	 **/
	public int searchPhone(String contact)
	{
		for(int i=0;i<people.size();i++)
		{
			if(people.get(i).name()==contact)
				return i;
			if(people.get(i).phoneNum()==contact)
				return i;
		}
		return -1;//ã�� ������ ��
	}

}