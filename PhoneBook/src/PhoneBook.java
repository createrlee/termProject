import java.util.ArrayList;

/**
 * �ּҷ� ��ü�� �����մϴ�. 
 * @author createrlee
 *
 */
public class PhoneBook
{
	private int groupIndex = -1;//���õ� �׷��� ��ġ. -1�� �ƴ� ��쿡�� ���õ� �׷��� �ִ� ������ �����Ͽ� �� �׷쿡 ���ؼ��� �޼ҵ� ����.
	private ArrayList<Group> contacts;
	private ArrayList<Person> entireList;//�׷� ���� ���� ��ü��� ǥ�ø� ����
	
	public PhoneBook()
	{
		contacts = new ArrayList<Group>();
		entireList = new ArrayList<Person>();
		
		contacts.add(new Group());// �̸��� ���� �׷��� �ϳ� �����. �̸��� ���� �׷��� ������ ù��°
		/*�⺻ �׷���� ��� �����Ѵ�. ���߿� �� �߰��Ҽ��� �ִ�.*/
		contacts.add(new Group("family"));
		contacts.add(new Group("friend"));
		contacts.add(new Group("company"));
	}
	
	/**
	 * �׷� ������ �����ϰ� �ϱ� ���� �޼ҵ��Դϴ�.
	 * �� �޼ҵ带 �����ϰ� ����, Ư�� �׷쿡 ���ؼ��� �ٸ� �޼ҵ���� ����ǰ� �˴ϴ�.
	 * �׷� ������ ������ escapeGroupMode()�� ȣ���� �ִ°��� ���� ������.<br>
	 * �׷��� �����ϸ� true, �������� ������ false�� ��ȯ�մϴ�.
	 * @param groupName : �۾��� ������ Ư�� �׷�
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
		System.out.println("�׷��� �������� �ʽ��ϴ�!");
		return false;
	}
	
	/**
	 * �׷� ��带 �����ϴ�.
	 */
	public void escapeGroupMode()
	{
		this.groupIndex = -1;
	}
	
	/**��ü ����ó ����� ����մϴ�*/
	public void list()
	{
		/*�׷��尡 �ƴ� ��*/
		if (groupIndex == -1)
		{
			entireList.clear();
			for (Group iGroup : contacts)//�� �׷쿡 ����
			{
				for (int i = 0; i < iGroup.groupSize(); i++)//�׷���� �� ����� ����
				{
					if (entireList.isEmpty())//��ü����� ��������� �׳� ����ó ����
						entireList.add(iGroup.getContact(i));
					else
					//��ҵ��� �����ϸ鼭 ��ü��Ͽ� �����Ѵ�
					{
						int entireListSize = entireList.size();
						boolean isInserted = false;
						
						for (int iList = 0; iList < entireListSize; iList++)
						{
							//�ϳ��� ���Ͽ� �´� ��ġ�� ����
							if (Person.strCmp(iGroup.getContact(i).name(), entireList.get(iList).name()))
							{
								entireList.add(iList, iGroup.getContact(i));
								isInserted = true;
								
								break;
								
							}
						}
						if (!isInserted)//�߰��� ���� ���� ��� ���� ����
							entireList.add(iGroup.getContact(i));
					}
				}
			}
			//����Ʈ ���
			for (Person iPerson : entireList)
			{
				System.out.println(iPerson.name() + " " + iPerson.phoneNum());
			}
			
		}
		/*�׷���*/
		else
		{
			contacts.get(groupIndex).listPhone();
		}
	}
	
	/**��ü �׷� ����� ����մϴ�*/
	public void listGroup()
	{
		System.out.println("<���� �׷� ���>");
		
		int i = 0;
		for (Group iGroup : contacts)
		{
			if (i > 0)//ù��° �̸����� ����� �������� �ʴ´�
				System.out.println(iGroup.groupName());
			i++;
		}
	}
	
	/**���ο� �׷��� �߰��մϴ�*/
	public void addGroup(String groupName)
	{
		contacts.add(new Group(groupName));
	}
	
	/**�׷��� �̸��� �ٲߴϴ�*/
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
		System.out.println("�ش� �׷��� �������� �ʽ��ϴ�!");
	}
	
	/**�׷��� �����մϴ�*/
	public void deleteGroup(String groupName)
	{
		if(groupName=="")//�̸����� �⺻�׷��� ������ �� ������ �Ѵ�
		{
			System.out.println("�ش� �׷��� �������� �ʽ��ϴ�!");
			return;
		}
		for (int i = 0; i < contacts.size(); i++)
		{
			if (contacts.get(i).groupName().equals(groupName))
			{
				contacts.remove(i);
				System.out.println("�׷� " + groupName + "��(��) ���ŵǾ����ϴ�");
				return;
			}
		}
		System.out.println("�ش� �׷��� �������� �ʽ��ϴ�!");
	}
	
	/**���ο� ����ó�� �߰��մϴ�*/
	public void add(String name, String phoneNum, String group)
	{
		/*�׷��尡 �ƴ� ��*/
		if (groupIndex == -1)
		{
			if (group == "")//�ƹ� �׷��� �Է����� ���� ���
				contacts.get(0).addPhone(name, phoneNum);
			else
			{
				boolean isSuchGroup = false;
				for (Group iGroup : contacts)//�׷��� ã�´�
				{
					if (iGroup.groupName().equals(group))
					{
						iGroup.addPhone(name, phoneNum);
						isSuchGroup = true;
						break;
					}
				}
				if (!isSuchGroup)//�Է��� �׷��� �������� ���� ��
				{
					contacts.add(new Group(group));//�׷��߰�
					contacts.get(contacts.size() - 1).addPhone(name, phoneNum);//�߰��� �׷쿡 ����ó �߰�
				}
			}
		}
		/*�׷���*/
		else
		{
			contacts.get(groupIndex).addPhone(name, phoneNum);
		}
	}
	
	/**����ó�� �����մϴ�*/
	public void delete(String name)
	{
		/*�׷��尡 �ƴҶ�*/
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
	
	/**����ó�� �˻��Ͽ� ����ó�� ������ ��� ǥ���մϴ�*/
	public void search(String name)
	{
		Person contact;//�˻��� ����ó �ӽ�����
		int searched;//�˻��� ����ó�� index
		for (Group iGroup : contacts)
		{
			searched = iGroup.searchPhone(name);//�˻��� �����Ѵ�
			if (searched != -1)//����ó�� �߰ߵǸ�
			{
				contact = iGroup.getContact(searched);
				System.out.println(contact.name() + " " + contact.phoneNum());
				break;
			}
		}
	}
	
	/**����ó�� ã�� ������ �����մϴ�
	 * @param name : ã�� ����ó
	 * @param toChange : �ٲ� �̸� Ȥ�� ��ȭ��ȣ
	 * @param select : 1.�̸� ���� 2.��ȭ��ȣ ���� 3.�׷� ����
	 */
	public void change(String name, String toChange, int select)
	{
		int gLoc = -1;//�˻��� ����ó�� �ִ� �׷��� index
		int searched = -1;//�˻��� ����ó�� index
		for (Group iGroup : contacts)
		{
			searched = iGroup.searchPhone(name);//�˻��� �����Ѵ�
			gLoc++;
			if (searched != -1)//����ó�� �߰ߵǸ�
				break;
		}
		if (searched == -1)//�߰ߵ� ����ó�� ���� ���
		{
			return;
		}
		System.out.println("group: "+gLoc+" name: "+searched);
		
		/*select�� 1,2,3�̿��� �� ������ �����Ƿ� ����ó�� �ʿ����*/
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
			 * 1.�˻��� ����ó�� �ӽ÷� ����
			 * 2.�˻��� ����ó�� ����
			 * 3.���ο� �׷쿡 �ӽ� ������ ����ó�� �߰�
			 */
			Person temp = contacts.get(gLoc).getContact(searched);
			contacts.get(gLoc).deletePhone(name);
			this.add(temp.name(), temp.phoneNum(), toChange);
			break;
		}
	}
	
	/**���õ� ����ó�� �޼��� ��ȭâ�� ���ϴ�*/
	public void message(String name)
	{
		System.out.println("<-----message with "+name+"----->");
		for(Group iGroup:contacts)
		{
			iGroup.message(name);
		}
	}
	
	/**���õ� ����ó�� ��ȭ â�� ���ϴ�*/
	public void call(String name)
	{
		System.out.println("<-----phonecall with "+name+"----->");
		for(Group iGroup:contacts)
		{
			iGroup.call(name);
		}
	}
}