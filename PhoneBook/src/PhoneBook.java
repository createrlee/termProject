import java.util.ArrayList;

/**
 * �ּҷ� ��ü�� �����մϴ�. 
 * @author createrlee
 *
 */
public class PhoneBook
{
	private int GroupIndex=-1;//Ư�� �׷��� �����ؼ� �۾��� �����Ҷ� �̿�. -1�� �ƴ� ��쿡�� ���õ� �׷��� �ִ� ������ �����Ͽ� �� �׷쿡 ���ؼ��� �޼ҵ� ����.
	private ArrayList<Group> phoneBook;
	
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
		
	}
	
	/**��ü �׷� ����� ����մϴ�*/
	public void listGroup()
	{
		
	}
	
	/**���ο� ����ó�� �߰��մϴ�*/
	public void add(String name,String phoneNum,String group)
	{
		
	}
	
	/**���ο� �׷��� �߰��մϴ�*/
	public void addGroup(String groupName)
	{
		
	}
	
	/**�׷��� �̸��� �ٲߴϴ�*/
	public void groupChange(String groupName,String toChange)
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