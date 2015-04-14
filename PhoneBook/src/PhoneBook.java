import java.util.ArrayList;

/**
 * �ּҷ� ��ü�� �����մϴ�. 
 * @author createrlee
 *
 */
public class PhoneBook
{
	private int GroupIndex=-1;//���õ� �׷��� ��ġ. -1�� �ƴ� ��쿡�� ���õ� �׷��� �ִ� ������ �����Ͽ� �� �׷쿡 ���ؼ��� �޼ҵ� ����.
	private ArrayList<Group> phoneBook;
	private ArrayList<Person> entireList;//�׷� ���� ���� ��ü��� ǥ�ø� ����
	
	public PhoneBook()
	{
		ArrayList<Group> phoneBook = new ArrayList<Group>();
		phoneBook.add(new Group());// �̸��� ���� �׷��� �ϳ� �����. �̸��� ���� �׷��� ������ ù��°
		/*�⺻ �׷���� ��� �����Ѵ�. ���߿� �� �߰��Ҽ��� �ִ�.*/
		phoneBook.add(new Group("family"));
		phoneBook.add(new Group("friend"));
		phoneBook.add(new Group("company"));
	}
	
	/**��ü ����ó ����� ����մϴ�*/
	public void list()
	{
		entireList=new ArrayList<Person>();
		for(Group iGroup:phoneBook)//�� �׷쿡 ����
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
	}
	
	/**��ü �׷� ����� ����մϴ�*/
	public void listGroup()
	{
		int i=0;
		for(Group iGroup:phoneBook)
		{
			System.out.println(i+"."+iGroup.groupName());
		}
	}
	
	/**���ο� �׷��� �߰��մϴ�*/
	public void addGroup(String groupName)
	{
		
	}
	
	/**�׷��� �̸��� �ٲߴϴ�*/
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
		System.out.println("�ش� �׷��� �������� �ʽ��ϴ�!");
	}
	
	/**�׷��� �����մϴ�*/
	public void deleteGroup(String groupName)
	{
		
	}
	
	/**���ο� ����ó�� �߰��մϴ�*/
	public void add(String name,String phoneNum,String group)
	{
		
		
	}
	
	/**����ó�� �����մϴ�*/
	public void delete(String name)	
	{
		
	}
	
	/**����ó�� �˻��Ͽ� ����ó�� ������ ��� ǥ���մϴ�*/
	public void search(String name)
	{
		
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