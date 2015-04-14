import java.util.ArrayList;

/**
 * �ּҷ� ��ü�� �����մϴ�. 
 * @author createrlee
 *
 */
public class PhoneBook
{
	private int GroupIndex=-1;//���õ� �׷��� ��ġ. -1�� �ƴ� ��쿡�� ���õ� �׷��� �ִ� ������ �����Ͽ� �� �׷쿡 ���ؼ��� �޼ҵ� ����.
	private ArrayList<Group> contacts;
	private ArrayList<Person> entireList;//�׷� ���� ���� ��ü��� ǥ�ø� ����
	
	public PhoneBook()
	{
		contacts = new ArrayList<Group>();
		contacts.add(new Group());// �̸��� ���� �׷��� �ϳ� �����. �̸��� ���� �׷��� ������ ù��°
		/*�⺻ �׷���� ��� �����Ѵ�. ���߿� �� �߰��Ҽ��� �ִ�.*/
		contacts.add(new Group("family"));
		contacts.add(new Group("friend"));
		contacts.add(new Group("company"));
	}
	
	/**��ü ����ó ����� ����մϴ�*/
	public void list()
	{
		entireList=new ArrayList<Person>();
		for(Group iGroup:contacts)//�� �׷쿡 ����
		{
			for(int i=0;i<iGroup.groupSize();i++)//�׷���� �� ����� ����
			{
				if(entireList.size()==0)//��ü����� ��������� �׳� ����ó ����
					entireList.add(iGroup.getContact(i));
				else//��ҵ��� �����ϸ鼭 ��ü��Ͽ� �����Ѵ�
				{
					int entireListSize=entireList.size();
					boolean isInserted=false;
					for(int iList=0;i<entireListSize;i++)
					{
						//�ϳ��� ���Ͽ� �´� ��ġ�� ����
						if(Person.strCmp(iGroup.getContact(i).name(),entireList.get(iList).name()))
						{
							entireList.add(iList, iGroup.getContact(i));
							isInserted=true;
							break;
						}
						if(!isInserted)//�߰��� ���� ���� ��� ���� ����
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
	
	/**��ü �׷� ����� ����մϴ�*/
	public void listGroup()
	{
		int i=0;
		for(Group iGroup:contacts)
		{
			if(i>0)//ù��° �̸����� ����� �������� �ʴ´�
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
		System.out.println("�ش� �׷��� �������� �ʽ��ϴ�!");
	}
	
	/**�׷��� �����մϴ�*/
	public void deleteGroup(String groupName)
	{
		for(int i=0;i<contacts.size();i++)
		{
			if(contacts.get(i).groupName()==groupName)
			{
				contacts.remove(i);
				System.out.println("�׷� "+groupName+"��(��) ���ŵǾ����ϴ�");
				return;
			}
		}
		System.out.println("�ش� �׷��� �������� �ʽ��ϴ�!");
	}
	
	/**���ο� ����ó�� �߰��մϴ�*/
	public void add(String name,String phoneNum,String group)
	{
		if(group=="")//�ƹ� �׷��� �Է����� ���� ���
			contacts.get(0).addPhone(name, phoneNum);
		else
		{
			boolean isSuchGroup=false;
			for(Group iGroup:contacts)//�׷��� ã�´�
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
			if(!isSuchGroup)//�Է��� �׷��� �������� ���� ��
			{
				contacts.add(new Group(group));//�׷��߰�
				contacts.get(contacts.size()-1).addPhone(name, phoneNum);//�߰��� �׷쿡 ����ó �߰�
			}
		}
	}
	
	/**����ó�� �����մϴ�*/
	public void delete(String name)	
	{
		for(Group iGroup:contacts)
			iGroup.deletePhone(name);
	}
	
	/**����ó�� �˻��Ͽ� ����ó�� ������ ��� ǥ���մϴ�*/
	public void search(String name)
	{
		for(Group iGroup:contacts)
			iGroup.searchPhone(name);
	}
	
	/**����ó�� ã�� ������ �����մϴ�
	 * @param name : ã�� ����ó
	 * @param toChange : �ٲ� �̸� Ȥ�� ��ȭ��ȣ
	 * @param select : 1.�̸� ���� 2.��ȭ��ȣ ���� 3.�׷� ����
	 */
	public void change(String name,String toChange,int select)
	{
		
	}
	
	/**���õ� ����ó�� �޼��� ��ȭâ�� ���ϴ�*/
	public void message(String name)
	{
		
	}
	
	/**���õ� ����ó�� ��ȭ â�� ���ϴ�*/
	public void call(String name)
	{
		
	}
}